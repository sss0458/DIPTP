//package org.example.utils; // 包名根据路径调整为 org.example.utils
//
//import com.ruoyi.common.config.RuoYiConfig;
//import com.ruoyi.common.utils.SecurityUtils; // 假设 SecurityUtils 在这个包下
//import com.ruoyi.common.utils.StringUtils; // 假设 StringUtils 在这个包下
//import com.ruoyi.common.utils.DateUtils; // 假设 DateUtils 在这个包下
//import com.ruoyi.common.utils.file.FileUploadUtils; // 文件上传工具类
//
//import org.example.domain.DataManage;
//import org.example.domain.DataManageExtend;
//import org.example.domain.vo.DataManageExtendVo;
//import org.example.domain.vo.DataManageVo;
//import org.example.mapper.DataManageExtendMapper;
//import org.example.mapper.DataManageMapper;
//// import com.ruoyi.system.mapper.MetaDataMapper; // 如果你需要根据 metaDataNameCode 查询 meta_data_id，则需要这个 Mapper
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.stereotype.Component; // 标记为Spring组件，以便获取ApplicationContext
//import org.springframework.transaction.annotation.Transactional; // 用于事务管理
//import org.springframework.web.multipart.MultipartFile; // 用于处理文件
//
//import java.io.File;
//import java.io.IOException;
//import java.util.UUID;
//import java.util.List; // 导入 List
//
///**
// * 媒体文件及管理信息通用处理工具类
// * 注意：本工具类包含数据库操作，并尝试进行事务管理，这并非纯粹工具类的典型用法。
// * 更推荐将此类业务逻辑封装在 @Service 中。
// *
// * 该类通过实现 ApplicationContextAware 来手动获取 Spring Bean (Mapper)，
// * 从而在静态方法中执行数据库操作。
// */
//@Component // 标记为Spring组件，以便Spring容器能够管理它并注入ApplicationContext
//public class FileUtils implements ApplicationContextAware { // 类名改为 FileUtils
//
//    private static final Logger log = LoggerFactory.getLogger(FileUtils.class);
//
//    private static ApplicationContext applicationContext;
//
//    // DataManageMapper 和 DataManageExtendMapper 将通过 ApplicationContext 获取
//    private static DataManageMapper dataManageMapper;
//    private static DataManageExtendMapper dataManageExtendMapper;
//    // private static MetaDataMapper metaDataMapper; // 如果需要根据 metaDataNameCode 查询 meta_data_id，则需要这个 Mapper
//
//
//    @Override
//    public void setApplicationContext(ApplicationContext context) {
//        FileUtils.applicationContext = context;
//        // 在Spring容器初始化时获取Mapper实例
//        FileUtils.dataManageMapper = applicationContext.getBean(DataManageMapper.class);
//        FileUtils.dataManageExtendMapper = applicationContext.getBean(DataManageExtendMapper.class);
//        // 如果需要MetaDataMapper，也在这里获取
//        // FileUtils.metaDataMapper = applicationContext.getBean(MetaDataMapper.class);
//    }
//
//    /**
//     * 通用的保存媒体文件及管理信息的方法。
//     * 该方法处理文件存储、DataManage主记录和DataManageExtend扩展信息的持久化。
//     * 可以被后端其他方法直接调用。
//     *
//     * @param file 文件的 MultipartFile 对象
//     * @param dataManage 包含DataManage主要信息和extendMetadata列表的对象
//     * @return 新创建的DataManageVo对象，失败返回null
//     */
//
//    public static DataManageVo saveMediaFileAndDataManage(MultipartFile file, DataManageVo dataManage) {
//        if (file == null || file.isEmpty()) {
//            log.error("保存文件及管理信息失败：文件为空。");
//            return null;
//        }
//        if (dataManage == null) {
//            log.error("保存文件及管理信息失败：DataManage对象为空。");
//            return null;
//        }
//
//        String filePath = null;
//        try {
//            // 1. 上传文件到服务器，获取文件路径
//            filePath = FileUploadUtils.upload(RuoYiConfig.getProfile(), file);
//            if (StringUtils.isEmpty(filePath)) {
//                log.error("保存文件及管理信息失败：文件上传失败，未能生成有效的文件路径。");
//                return null;
//            }
//
//            // 2. 填充 DataManage 对象
//            dataManage.setFilePath(filePath); // 将上传后的文件路径设置到 DataManage 对象中
//            // 统一在工具类/Service层填充审计字段和默认值
//            dataManage.setCreateBy(SecurityUtils.getUsername());
//            dataManage.setCreateTime(DateUtils.getNowDate());
//            if (StringUtils.isEmpty(dataManage.getDelFlag())) {
//                dataManage.setDelFlag("0");
//            }
//            if (StringUtils.isEmpty(dataManage.getStatus())) {
//                dataManage.setStatus("0");
//            }
//            if (StringUtils.isEmpty(dataManage.getDataType())) {
//                dataManage.setDataType("FILE"); // 如果前端未指定，默认设为通用文件类型
//            }
//            if (StringUtils.isEmpty(dataManage.getSource())) {
//                dataManage.setSource("后端工具类生成/通用上传"); // 来源
//            }
//            if (StringUtils.isEmpty(dataManage.getSign())) {
//                dataManage.setSign("通用文件_" + DateUtils.dateTimeNow("yyyyMMddHHmmss") + "_" + UUID.randomUUID().toString().substring(0, 4));
//            }
//
//            // 3. 插入 DataManage 主记录
//            // 注意：这里直接使用了静态获取的 Mapper
//            int rows = dataManageMapper.insertDataManage(dataManage); // 此时 dataManage.getId() 会被回填
//
//            if (rows > 0) {
//                // 4. 处理扩展元数据 (extendMetadata)
//                if (dataManage.getExtendMetadata() != null && !dataManage.getExtendMetadata().isEmpty()) {
//                    for (DataManageExtendVo extend : dataManage.getExtendMetadata()) {
//                        extend.setDataManageId(dataManage.getId()); // 设置关联的DataManage ID
//                        // 统一在工具类/Service层填充审计字段和默认值
//                        extend.setCreateBy(SecurityUtils.getUsername());
//                        extend.setCreateTime(DateUtils.getNowDate());
//                        extend.setDelFlag("0");
//                        extend.setStatus("0");
//                    }
//                    // 批量插入扩展元数据
//                    dataManageExtendMapper.batchInsertDataManageExtendVo(dataManage.getExtendMetadata());
//                }
//                // 5. 查询并返回完整的 DataManageVo 对象
//                return dataManageMapper.selectDataManageVoById(dataManage.getId());
//            } else {
//                log.error("文件及管理信息保存失败：数据插入主表失败。");
//                return null;
//            }
//
//        } catch (IOException e) {
//            log.error("文件保存到磁盘失败: {}", e.getMessage(), e);
//            // 事务将自动回滚，因为是运行时异常
//            throw new RuntimeException("文件保存到磁盘失败", e);
//        } catch (Exception e) {
//            log.error("保存媒体文件及管理信息过程中发生未知错误: {}", e.getMessage(), e);
//            // 事务将自动回滚
//            throw new RuntimeException("保存媒体文件及管理信息失败", e); // 向上抛出运行时异常，让调用方处理
//        }
//
//    }
//}
package org.example.utils;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.utils.SecurityUtils; // 假设 SecurityUtils 可用
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.DateUtils; // 假设 DateUtils 可用

import org.example.domain.DataManage; // 导入 DataManage 对象
import org.example.domain.DataManageExtend;
import org.example.domain.vo.DataManageExtendVo;
import org.example.domain.vo.DataManageVo;   // 导入 DataManageVo 对象
import org.example.service.IDataManageService;
import org.springframework.beans.BeanUtils; // 导入 Spring 的 BeanUtils 用于对象复制
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.example.service.IDataManageExtendService;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 文件上传工具类，封装了媒体文件上传的通用逻辑。
 * 修改：uploadFileToUserDir 方法现在返回 DataManageVo 对象。
 */
@Component
public class FileUtils {

    @Autowired
    private IDataManageService dataManageService;       // 注入 DataManage 主表的 Service

    @Autowired
    private IDataManageExtendService dataManageExtendService; // 注入 DataManageExtend 拓展表的 Service

    /**
     * 上传媒体文件到用户专属目录，装配 DataManage 对象，并将其及相关拓展信息存入数据库。
     *
     * @param userId     用户ID，用于创建用户专属文件夹
     * @return 完整装配并包含数据库ID的 DataManage 对象
     * @throws IOException            如果在文件操作过程中发生I/O错误或无法创建目录
     * @throws IllegalArgumentException 如果文件、用户ID或DataManage对象为空
     */
    @Transactional // 确保文件操作和数据库操作的原子性
    public DataManage uploadFileAndReDic(Long userId) throws IOException {
        if (userId == null) {
            throw new IllegalArgumentException("用户ID不能为空，无法创建用户专属文件夹。");
        }

        // 1. 文件上传逻辑
        String baseUploadPath = RuoYiConfig.getProfile();
        String relativeUserDirPath = "user_uploads" + File.separator + userId;
        String absoluteUserUploadPath = baseUploadPath + File.separator + relativeUserDirPath;

        File userDir = new File(absoluteUserUploadPath);
        if (!userDir.exists()) {
            boolean created = userDir.mkdirs();
            if (!created) {
                throw new IOException("无法创建用户专属文件夹：" + absoluteUserUploadPath);
            }
        }

        DataManage dataManage = new DataManage();

        // 2. 填充传入的 DataManage 对象（dataManage）的文件相关属性和审计信息
        dataManage.setFilePath(absoluteUserUploadPath);
        dataManage.setDelFlag("0");
        if (StringUtils.isEmpty(dataManage.getStatus())) {
            dataManage.setStatus("0");
        }
        if (StringUtils.isEmpty(dataManage.getSign())) {
            dataManage.setSign("用户文件_" + DateUtils.dateTimeNow("yyyyMMddHHmmss") + "_" + UUID.randomUUID().toString().substring(0, 4));
        }
        if (StringUtils.isEmpty(dataManage.getSource())) {
            dataManage.setSource("用户上传");
        }
        if (StringUtils.isEmpty(dataManage.getDataType())) {
            dataManage.setDataType("文件夹"); // 默认数据类型为文件扩展名
        }
        dataManage.setRemark(relativeUserDirPath);

        // 3. 将 DataManage 主体保存到数据库，并获取生成的 ID
        int rowsAffected = dataManageService.insertDataManage(dataManage); // 假设此方法会保存 DataManage 并回填 ID
        if (rowsAffected <= 0) {
            throw new IOException("数据管理主记录保存失败。");
        }

        // 获取数据库生成的 ID (此时 dataManage 对象已包含由数据库回填的 ID)
        Long generatedId = dataManage.getId();
        if (generatedId == null) {
            // 这通常表示您的 MyBatis Mapper 配置中缺少 ID 回填设置 (如 useGeneratedKeys="true" keyProperty="id")
            throw new IllegalStateException("保存 DataManage 后未获取到生成的ID。请检查Mybatis配置。");
        }

        return dataManage; // 返回完整装配并包含数据库ID的 DataManage 对象
    }

    @Transactional // 确保文件操作和数据库操作的原子性
    public DataManage uploadFileAndSaveDataManage(MultipartFile file, Long userId, DataManage dataManage) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("上传文件不能为空。");
        }
        if (userId == null) {
            throw new IllegalArgumentException("用户ID不能为空，无法创建用户专属文件夹。");
        }
        if (dataManage == null) {
            throw new IllegalArgumentException("DataManage 对象不能为空。");
        }

        // 1. 文件上传逻辑
        String baseUploadPath = RuoYiConfig.getProfile();
        String relativeUserDirPath = "user_uploads" + File.separator + userId;
        String absoluteUserUploadPath = baseUploadPath + File.separator + relativeUserDirPath;

        File userDir = new File(absoluteUserUploadPath);
        if (!userDir.exists()) {
            boolean created = userDir.mkdirs();
            if (!created) {
                throw new IOException("无法创建用户专属文件夹：" + absoluteUserUploadPath);
            }
        }

        String originalFilename = file.getOriginalFilename();
        String extension = getExtension(originalFilename); // 调用静态方法时可直接使用方法名
        String newFileName = UUID.randomUUID().toString() + "." + extension;

        File dest = new File(absoluteUserUploadPath + File.separator + newFileName);
        file.transferTo(dest);

        String fileRelativePath = relativeUserDirPath.replace(File.separator, "/") + "/" + newFileName;

        // 2. 填充传入的 DataManage 对象（dataManage）的文件相关属性和审计信息
        dataManage.setFilePath(fileRelativePath);
        dataManage.setDelFlag("0"); // 默认：未删除

        // 检查并设置默认值，如果 dataManage 中没有提供
        if (StringUtils.isEmpty(dataManage.getStatus())) {
            dataManage.setStatus("0");
        }
        if (StringUtils.isEmpty(dataManage.getSign())) {
            dataManage.setSign("用户文件_" + DateUtils.dateTimeNow("yyyyMMddHHmmss") + "_" + UUID.randomUUID().toString().substring(0, 4));
        }
        if (StringUtils.isEmpty(dataManage.getSource())) {
            dataManage.setSource("用户上传");
        }
        if (StringUtils.isEmpty(dataManage.getDataType())) {
            dataManage.setDataType(extension); // 默认数据类型为文件扩展名
        }
        dataManage.setRemark("原始文件名: " + originalFilename + ", 文件大小: " + file.getSize() + " 字节");

        // 3. 将 DataManage 主体保存到数据库，并获取生成的 ID
        int rowsAffected = dataManageService.insertDataManage(dataManage); // 假设此方法会保存 DataManage 并回填 ID
        if (rowsAffected <= 0) {
            throw new IOException("数据管理主记录保存失败。");
        }

        // 获取数据库生成的 ID (此时 dataManage 对象已包含由数据库回填的 ID)
        Long generatedId = dataManage.getId();
        if (generatedId == null) {
            // 这通常表示您的 MyBatis Mapper 配置中缺少 ID 回填设置 (如 useGeneratedKeys="true" keyProperty="id")
            throw new IllegalStateException("保存 DataManage 后未获取到生成的ID。请检查Mybatis配置。");
        }

        // 4. 处理 DataManageExtendList 并保存到数据库
        List<DataManageExtend> dataManageExtendList = dataManage.getDataManageExtendList(); // 从传入的 dataManage 获取 extend 列表
        if (dataManageExtendList != null && !dataManageExtendList.isEmpty()) {
            for (DataManageExtend extend : dataManageExtendList) {
                extend.setDataManageId(generatedId);
            }
            // 批量插入 DataManageExtend 实体
            dataManageExtendService.batchInsertDataManageExtend(dataManageExtendList);
        }

        return dataManage; // 返回完整装配并包含数据库ID的 DataManage 对象
    }

    /**
     * 获取文件名的后缀。
     *
     * @param fileName 文件名
     * @return 文件后缀，不包含点。如果无后缀或文件名为空，则返回空字符串。
     */
    public static String getExtension(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            return "";
        }
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1);
        }
        return "";
    }
}