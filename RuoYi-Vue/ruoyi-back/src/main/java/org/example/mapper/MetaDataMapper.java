package org.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.domain.MetaData;
import org.example.domain.DataManageExtend;

/**
 * 元数据管理Mapper接口
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@Mapper
public interface MetaDataMapper
{
    /**
     * Query metadata management list
     *
     * @param metaData Metadata management object
     * @return Metadata management collection
     */
    public List<MetaData> selectMetaDataList(MetaData metaData);

    /**
     * Query metadata management by ID
     *
     * @param id Metadata management primary key
     * @return Metadata management object
     */
    public MetaData selectMetaDataById(Long id);

    /**
     * Add new metadata management
     *
     * @param metaData Metadata management object
     * @return Result
     */
    public int insertMetaData(MetaData metaData);

    /**
     * Modify metadata management
     *
     * @param metaData Metadata management object
     * @return Result
     */
    public int updateMetaData(MetaData metaData);

    // --- Hard delete methods removed below to prevent accidental data loss ---

    // /**
    //  * Delete metadata management (Physical Delete)
    //  * Use with caution, logical delete is generally preferred
    //  *
    //  * @param id Metadata management primary key
    //  * @return Result
    //  */
    // public int deleteMetaDataById(Long id);

    // /**
    //  * Batch delete metadata management (Physical Delete)
    //  * Use with caution, logical delete is generally preferred
    //  *
    //  * @param ids Collection of primary keys to be deleted
    //  * @return Result
    //  */
    // public int deleteMetaDataByIds(Long[] ids);

    /**
     * Query metadata definition by metadata name
     * @param name Metadata name
     * @return MetaData object
     */
    public MetaData selectMetaDataByName(String name);

    /**
     * Query corresponding ID by metadata name
     * @param name Metadata name, e.g., "original_video_id"
     * @return Corresponding metadata ID (Long type)
     */
    Long selectMetaDataIdByName(@Param("name") String name);

    /**
     * Batch logical delete metadata management
     * Sets del_flag to '2'
     *
     * @param ids Collection of primary keys to be logically deleted

     * @return Result
     */
    public int updateMetaDataDelFlagByIds(@Param("ids") List<Long> ids);

    /**
     * Logical delete single metadata management
     * Sets del_flag to '2'
     *
     * @param id Primary key of the data to be logically deleted

     * @return Result
     */
    public int updateMetaDataDelFlagById(@Param("id") Long id);
}