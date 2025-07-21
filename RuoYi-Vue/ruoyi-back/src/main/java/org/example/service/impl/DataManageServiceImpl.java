package org.example.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import org.example.domain.vo.DataManageExtendVo;
import org.example.domain.vo.DataManageVo;
import org.example.mapper.DataManageExtendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;
import org.example.domain.DataManageExtend;
import org.example.mapper.DataManageMapper;
import org.example.domain.DataManage;
import org.example.service.IDataManageService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 数据管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@Service
public class DataManageServiceImpl implements IDataManageService 
{
    @Autowired
    private DataManageMapper dataManageMapper;
    @Autowired
    private DataManageExtendMapper dataManageExtendMapper;


    @Override
    public DataManage selectDataManageById(Long Id) {
        return dataManageMapper.selectBasicDataManageById(Id);
    }

    /**
     * 查询数据管理
     * 
     * @param id 数据管理主键
     * @return 数据管理
     */
    @Override
    public DataManageVo selectDataManageVoById(Long id)
    {
        return dataManageMapper.selectDataManageVoById(id);
    }

    /**
     * 查询数据管理列表
     * 
     * @param dataManage 数据管理
     * @return 数据管理
     */
    @Override
    public List<DataManageVo> selectDataManageList(DataManage dataManage)
    {
        return dataManageMapper.selectDataManageVoList(dataManage);
    }

    /**
     * 新增数据管理
     * 
     * @param dataManage 数据管理
     * @return 结果
     */
    @Transactional
    @Override
    public int insertDataManage(DataManage dataManage)
    {
        int rows = dataManageMapper.insertDataManage(dataManage);
        insertDataManageExtend(dataManage);
        return rows;
    }

    /**
     * 修改数据管理
     * 
     * @param dataManage 数据管理
     * @return 结果
     */
    @Transactional
    @Override
    public int updateDataManage(DataManage dataManage)
    {
        dataManageMapper.deleteDataManageExtendByDataManageId(dataManage.getId());
        insertDataManageExtend(dataManage);
        return dataManageMapper.updateDataManage(dataManage);
    }

    /**
     * 批量删除数据管理
     * 
     * @param ids 需要删除的数据管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteDataManageByIds(Long[] ids)
    {
        dataManageMapper.deleteDataManageExtendByDataManageIds(ids);
        return dataManageMapper.deleteDataManageByIds(ids);
    }

    /**
     * 删除数据管理信息
     * 
     * @param id 数据管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteDataManageById(Long id)
    {
        dataManageMapper.deleteDataManageExtendByDataManageId(id);
        return dataManageMapper.deleteDataManageById(id);
    }

    /**
     * 新增数据管理拓展信息
     * 
     * @param dataManage 数据管理对象
     */
    public void insertDataManageExtend(DataManage dataManage)
    {
        List<DataManageExtend> dataManageExtendList = dataManage.getDataManageExtendList();
        Long id = dataManage.getId();
        if (dataManageExtendList != null)
        {
            List<DataManageExtend> list = new ArrayList<DataManageExtend>();
            for (DataManageExtend dataManageExtend : dataManageExtendList)
            {
                dataManageExtend.setDataManageId(id);
                list.add(dataManageExtend);
            }
            if (list.size() > 0)
            {
                dataManageMapper.batchDataManageExtend(list);
            }
        }
    }

    @Override
    @Transactional // 确保事务一致性
    public DataManageVo uploadScreenshot(MultipartFile file, Long originalDataManageId, Float screenshotTime) {
        if (file.isEmpty()) {
            return null; // 文件为空直接返回
        }

        String filePath = null;
        try {
            // 1. 上传截图文件
            filePath = FileUploadUtils.upload(RuoYiConfig.getProfile(), file);
        } catch (IOException e) {
            // 记录日志，处理文件上传异常
            System.err.println("截图文件上传失败: " + e.getMessage()); // 实际项目中应使用日志框架
            return null;
        }

        if (filePath == null) {
            return null; // 文件路径未成功生成，上传失败
        }

        // 2. 创建新的 DataManage 对象用于存储截图信息
        DataManage screenshotDataManage = new DataManage();
        screenshotDataManage.setFilePath(filePath);
        screenshotDataManage.setDataType("IMAGE"); // 截图是图片类型
        screenshotDataManage.setSource("视频截图自动生成"); // 来源标记
        screenshotDataManage.setDelFlag("0");
        screenshotDataManage.setStatus("0");

        // 3. 从原视频复制相关元数据（如果有原视频）
        DataManage originalVideo = null;
        if (originalDataManageId != null) {
            originalVideo = dataManageMapper.selectBasicDataManageById(originalDataManageId); // 使用 selectBasicDataManageById 避免不必要的扩展元数据加载
        }

        String baseSign = "";
        String baseRemark = "";
        List<DataManageExtendVo> extendsList = new ArrayList<>();

        if (originalVideo != null) {
            // 复制分类信息
            screenshotDataManage.setSceneType(originalVideo.getSceneType());
            screenshotDataManage.setTargetType(originalVideo.getTargetType());
            screenshotDataManage.setImagingType(originalVideo.getImagingType());
            screenshotDataManage.setAlgorithmId(originalVideo.getAlgorithmId()); // 如果有算法ID，也复制过来

            // 复制并修改Sign和Remark
            baseSign = originalVideo.getSign();
            baseRemark = originalVideo.getRemark();
            screenshotDataManage.setSign(baseSign + "_截图_" + DateUtils.dateTimeNow("yyyyMMddHHmmss") + "_" + UUID.randomUUID().toString().substring(0, 4));
            screenshotDataManage.setRemark(baseRemark + " (截图自视频 ID:" + originalVideo.getId() + ")");

            // 5. 添加扩展元数据：原视频ID (只有在找到原视频时才添加)
            DataManageExtendVo originalVideoIdExtend = new DataManageExtendVo();
            originalVideoIdExtend.setMetaDataNameCode("original_video_id");
            originalVideoIdExtend.setContent(String.valueOf(originalDataManageId));
            originalVideoIdExtend.setDelFlag("0");
            originalVideoIdExtend.setStatus("0");
            extendsList.add(originalVideoIdExtend);

            // 6. 添加扩展元数据：截图时间（如果提供了，并且在找到原视频时才添加）
            if (screenshotTime != null) {
                DataManageExtendVo screenshotTimeExtend = new DataManageExtendVo();
                screenshotTimeExtend.setMetaDataNameCode("screenshot_time_sec");
                screenshotTimeExtend.setContent(String.valueOf(screenshotTime));
                screenshotTimeExtend.setDelFlag("0");
                screenshotTimeExtend.setStatus("0");
                extendsList.add(screenshotTimeExtend);
            }

        } else {
            // 如果没有原视频，提供默认Sign和Remark
            screenshotDataManage.setSign("截图_" + DateUtils.dateTimeNow("yyyyMMddHHmmss") + "_" + UUID.randomUUID().toString().substring(0, 4));
            screenshotDataManage.setRemark("由用户从视频ID:" + (originalDataManageId != null ? originalDataManageId : "未知") + "进行截图。");
        }

        // 4. 插入新的截图 DataManage 记录
        int rows = dataManageMapper.insertDataManage(screenshotDataManage); // 插入后，screenshotDataManage.id 会被回填

        if (rows > 0) {
            // 7. 批量插入扩展元数据
            if (!extendsList.isEmpty()) {
                // 在这里设置 DataManageId，因为 screenshotDataManage.getId() 在 insertDataManage 之后才会有值
                for (DataManageExtendVo extend : extendsList) {
                    extend.setDataManageId(screenshotDataManage.getId());
                }
                dataManageExtendMapper.batchInsertDataManageExtendVo(extendsList);
            }
            return dataManageMapper.selectDataManageVoById(screenshotDataManage.getId());
        }
        return null; // 插入失败
    }

    /**
     * 导出查询
     * @param dataManage
     * @return
     */
    @Override
    public List<DataManageVo> selectDataManageExport(DataManage dataManage) {
        // dataManageMapper.selectDataManageList 现在返回 List<DataManageVo>
        return dataManageMapper.selectDataManageVoList(dataManage);
    }

    /**
     * 查询媒体资源数量
     * @param dataManage
     * @return
     */
    @Override
    public int selectDataManageCount(DataManage dataManage) {
        return dataManageMapper.selectDataManageCount(dataManage);
    }
}
