package org.example.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.mapper.TeachTaskUserMapper;
import org.example.domain.TeachTaskUser;
import org.example.service.ITeachTaskUserService;

/**
 * 教学任务与用户关联Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@Service
public class TeachTaskUserServiceImpl implements ITeachTaskUserService 
{
    @Autowired
    private TeachTaskUserMapper teachTaskUserMapper;

    /**
     * 查询教学任务与用户关联
     * 
     * @param id 教学任务与用户关联主键
     * @return 教学任务与用户关联
     */
    @Override
    public TeachTaskUser selectTeachTaskUserById(Long id)
    {
        return teachTaskUserMapper.selectTeachTaskUserById(id);
    }

    /**
     * 查询教学任务与用户关联列表
     * 
     * @param teachTaskUser 教学任务与用户关联
     * @return 教学任务与用户关联
     */
    @Override
    public List<TeachTaskUser> selectTeachTaskUserList(TeachTaskUser teachTaskUser)
    {
        return teachTaskUserMapper.selectTeachTaskUserList(teachTaskUser);
    }

    /**
     * 新增教学任务与用户关联
     * 
     * @param teachTaskUser 教学任务与用户关联
     * @return 结果
     */
    @Override
    public int insertTeachTaskUser(TeachTaskUser teachTaskUser)
    {
        return teachTaskUserMapper.insertTeachTaskUser(teachTaskUser);
    }

    /**
     * 修改教学任务与用户关联
     * 
     * @param teachTaskUser 教学任务与用户关联
     * @return 结果
     */
    @Override
    public int updateTeachTaskUser(TeachTaskUser teachTaskUser)
    {
        return teachTaskUserMapper.updateTeachTaskUser(teachTaskUser);
    }

    /**
     * 批量删除教学任务与用户关联
     * 
     * @param ids 需要删除的教学任务与用户关联主键
     * @return 结果
     */
    @Override
    public int deleteTeachTaskUserByIds(Long[] ids)
    {
        return teachTaskUserMapper.deleteTeachTaskUserByIds(ids);
    }

    /**
     * 删除教学任务与用户关联信息
     * 
     * @param id 教学任务与用户关联主键
     * @return 结果
     */
    @Override
    public int deleteTeachTaskUserById(Long id)
    {
        return teachTaskUserMapper.deleteTeachTaskUserById(id);
    }
}
