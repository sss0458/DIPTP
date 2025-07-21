package org.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.example.domain.Algorithm;

/**
 * 算法Mapper接口
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@Mapper
public interface AlgorithmMapper 
{
    /**
     * 查询算法
     * 
     * @param id 算法主键
     * @return 算法
     */
    public Algorithm selectAlgorithmById(Long id);

    /**
     * 查询算法列表
     * 
     * @param algorithm 算法
     * @return 算法集合
     */
    public List<Algorithm> selectAlgorithmList(Algorithm algorithm);

    /**
     * 新增算法
     * 
     * @param algorithm 算法
     * @return 结果
     */
    public int insertAlgorithm(Algorithm algorithm);

    /**
     * 修改算法
     * 
     * @param algorithm 算法
     * @return 结果
     */
    public int updateAlgorithm(Algorithm algorithm);

    /**
     * 删除算法
     * 
     * @param id 算法主键
     * @return 结果
     */
    public int deleteAlgorithmById(Long id);

    /**
     * 批量删除算法
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAlgorithmByIds(Long[] ids);
}
