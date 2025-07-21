package org.example.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.mapper.AlgorithmMapper;
import org.example.domain.Algorithm;
import org.example.service.IAlgorithmService;

/**
 * 算法Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@Service
public class AlgorithmServiceImpl implements IAlgorithmService 
{
    @Autowired
    private AlgorithmMapper algorithmMapper;

    /**
     * 查询算法
     * 
     * @param id 算法主键
     * @return 算法
     */
    @Override
    public Algorithm selectAlgorithmById(Long id)
    {
        return algorithmMapper.selectAlgorithmById(id);
    }

    /**
     * 查询算法列表
     * 
     * @param algorithm 算法
     * @return 算法
     */
    @Override
    public List<Algorithm> selectAlgorithmList(Algorithm algorithm)
    {
        return algorithmMapper.selectAlgorithmList(algorithm);
    }

    /**
     * 新增算法
     * 
     * @param algorithm 算法
     * @return 结果
     */
    @Override
    public int insertAlgorithm(Algorithm algorithm)
    {
        return algorithmMapper.insertAlgorithm(algorithm);
    }

    /**
     * 修改算法
     * 
     * @param algorithm 算法
     * @return 结果
     */
    @Override
    public int updateAlgorithm(Algorithm algorithm)
    {
        return algorithmMapper.updateAlgorithm(algorithm);
    }

    /**
     * 批量删除算法
     * 
     * @param ids 需要删除的算法主键
     * @return 结果
     */
    @Override
    public int deleteAlgorithmByIds(Long[] ids)
    {
        return algorithmMapper.deleteAlgorithmByIds(ids);
    }

    /**
     * 删除算法信息
     * 
     * @param id 算法主键
     * @return 结果
     */
    @Override
    public int deleteAlgorithmById(Long id)
    {
        return algorithmMapper.deleteAlgorithmById(id);
    }
}
