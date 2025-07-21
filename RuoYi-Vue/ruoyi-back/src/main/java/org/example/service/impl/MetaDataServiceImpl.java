package org.example.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.mapper.MetaDataMapper;
import org.example.domain.MetaData;
import org.example.service.IMetaDataService;

/**
 * 元数据管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@Service
public class MetaDataServiceImpl implements IMetaDataService
{
    @Autowired
    private MetaDataMapper metaDataMapper;

    /**
     * Query metadata management list
     */
    @Override
    public List<MetaData> selectMetaDataList(MetaData metaData)
    {
        return metaDataMapper.selectMetaDataList(metaData);
    }

    /**
     * Query metadata management by ID
     */
    @Override
    public MetaData selectMetaDataById(Long id)
    {
        return metaDataMapper.selectMetaDataById(id);
    }

    /**
     * Add new metadata management
     */
    @Override
    public int insertMetaData(MetaData metaData)
    {
        if (metaData.getDelFlag() == null || metaData.getDelFlag().isEmpty()) {
            metaData.setDelFlag("0"); // Default: not deleted
        }
        if (metaData.getStatus() == null || metaData.getStatus().isEmpty()) {
            metaData.setStatus("1"); // Default: enabled
        }
        return metaDataMapper.insertMetaData(metaData);
    }

    /**
     * Modify metadata management
     */
    @Override
    public int updateMetaData(MetaData metaData)
    {
        return metaDataMapper.updateMetaData(metaData);
    }

    /**
     * Logical delete single metadata management information
     * This method replaces the previous physical delete.
     * It now explicitly passes updateBy and updateTime to the Mapper.
     */
    @Override
    public int deleteMetaDataById(Long id)
    {
        // Call the logical delete method in the Mapper with required parameters
        return metaDataMapper.updateMetaDataDelFlagById(id);
    }

    /**
     * Batch logical delete metadata management
     * This method replaces the previous batch physical delete.
     * It now explicitly passes updateBy and updateTime to the Mapper.
     */
    @Override
    public int deleteMetaDataByIds(Long[] ids)
    {
        // Convert Long[] to List<Long> to match the Mapper method signature
        return metaDataMapper.updateMetaDataDelFlagByIds(Arrays.asList(ids));
    }

    /**
     * Query metadata definition by metadata name (full object)
     */
    @Override
    public MetaData selectMetaDataByName(String name) {
        return metaDataMapper.selectMetaDataByName(name);
    }

    /**
     * Query metadata ID by metadata name
     */
    @Override
    public Long selectMetaDataIdByName(String name) {
        return metaDataMapper.selectMetaDataIdByName(name);
    }

    /**
     * Logical delete single metadata management
     * This method's functionality now mirrors `deleteMetaDataById`.
     * Consider consolidating these two methods in the Service layer if their purpose is identical.
     */
    @Override
    public int updateMetaDataDelFlagById(Long id) {
        return metaDataMapper.updateMetaDataDelFlagById(id);
    }

    /**
     * Batch logical delete metadata management
     * This method's functionality now mirrors `deleteMetaDataByIds`.
     * Consider consolidating these two methods in the Service layer if their purpose is identical.
     */
    @Override
    public int updateMetaDataDelFlagByIds(List<Long> ids) {
        return metaDataMapper.updateMetaDataDelFlagByIds(ids);
    }
}