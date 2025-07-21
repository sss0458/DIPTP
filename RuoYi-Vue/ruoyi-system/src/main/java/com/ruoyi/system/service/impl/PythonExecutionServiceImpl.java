package com.ruoyi.system.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.system.domain.ImageProcessResult;
import com.ruoyi.system.service.PythonExecutionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Python脚本执行服务实现类
 * 使用RestTemplate与Python微服务交互
 */
@Service
public class PythonExecutionServiceImpl implements PythonExecutionService {

    // 从配置文件中注入Python微服务的URL，默认值为http://localhost:8000
    @Value("${python.microservice.url:http://localhost:8000}")
    private String pythonMicroserviceUrl;

    // Spring的RestTemplate，用于发送HTTP请求
    private final RestTemplate restTemplate;

    // Jackson的ObjectMapper，用于JSON序列化和反序列化
    private final ObjectMapper objectMapper;

    /**
     * 构造方法，依赖注入
     *
     * @param restTemplate RestTemplate实例
     * @param objectMapper ObjectMapper实例
     */
    public PythonExecutionServiceImpl(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    /**
     * 执行Python脚本（直接提供脚本内容）
     */
    @Override
    public ImageProcessResult executePythonScript(MultipartFile imageFile, String pythonScript) {
        try {
            // 1. 设置请求头，指定内容类型为multipart/form-data
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            // 2. 构建multipart请求体
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

            // 添加图像文件部分
            body.add("file", new ByteArrayResource(imageFile.getBytes()) {
                @Override
                public String getFilename() {
                    // 重写getFilename方法，确保服务端能获取原始文件名
                    return imageFile.getOriginalFilename();
                }
            });

            // 添加脚本内容部分
            body.add("script", pythonScript);

            // 3. 创建请求实体
            HttpEntity<MultiValueMap<String, Object>> requestEntity =
                    new HttpEntity<>(body, headers);

            // 4. 发送POST请求到Python微服务
            ResponseEntity<String> response = restTemplate.exchange(
                    pythonMicroserviceUrl + "/execute", // 请求URL
                    HttpMethod.POST,                   // 请求方法
                    requestEntity,                     // 请求实体
                    String.class                       // 响应类型
            );

            // 5. 解析响应结果
            return parseExecutionResult(response.getBody());
        } catch (IOException e) {
            throw new RuntimeException("读取图像文件失败", e);
        }
    }

    /**
     * 执行Python脚本（上传脚本文件）
     */
    @Override
    public ImageProcessResult executePythonScript(MultipartFile imageFile, MultipartFile pythonFile) {
        try {
            // 1. 读取Python脚本文件内容
            String scriptContent = new String(pythonFile.getBytes());

            // 2. 调用直接执行脚本内容的方法
            return executePythonScript(imageFile, scriptContent);
        } catch (IOException e) {
            throw new RuntimeException("读取Python脚本文件失败", e);
        }
    }

    /**
     * 解析Python微服务的响应结果
     *
     * @param jsonResponse JSON格式的响应字符串
     * @return 图像处理结果对象
     * @throws RuntimeException 当响应格式无效或处理失败时抛出
     */
    private ImageProcessResult parseExecutionResult(String jsonResponse) {
        try {
            // 1. 将JSON字符串转换为Map
            Map<String, Object> responseMap = objectMapper.readValue(
                    jsonResponse,
                    new TypeReference<Map<String, Object>>() {
                    }
            );

            // 2. 检查执行状态
            if (!Boolean.TRUE.equals(responseMap.get("success"))) {
                String errorMsg = Optional.ofNullable((String) responseMap.get("error"))
                        .orElse("Python微服务执行失败");
                throw new RuntimeException(errorMsg);
            }

            // 3. 提取Base64图像数据（兼容output_image字段）
            String base64Image = Optional.ofNullable((String) responseMap.get("output_image"))
                    .orElseThrow(() -> new RuntimeException("未返回有效的图像数据"));

            // 4. 构造元数据
            Map<String, Object> metadata = new HashMap<>();
            Optional.ofNullable(responseMap.get("execution_time"))
                    .ifPresent(time -> metadata.put("timeCost", time));
            Optional.ofNullable(responseMap.get("logs"))
                    .ifPresent(logs -> metadata.put("logs", logs));

            // 5. 构建结果对象
            ImageProcessResult result = new ImageProcessResult();
            result.setBase64Image(base64Image);
            result.setMetadata(metadata);

            return result;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON解析失败: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("处理Python响应时出错: " + e.getMessage(), e);
        }
    }

}