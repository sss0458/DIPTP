package org.example.service;

import java.util.List;
import org.example.domain.Algorithm;
import org.springframework.stereotype.Service;

/**
 * 算法Service接口
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@Service
public interface IAlgorithmService 
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
     * 批量删除算法
     * 
     * @param ids 需要删除的算法主键集合
     * @return 结果
     */
    public int deleteAlgorithmByIds(Long[] ids);

    /**
     * 删除算法信息
     * 
     * @param id 算法主键
     * @return 结果
     */
    public int deleteAlgorithmById(Long id);
}
