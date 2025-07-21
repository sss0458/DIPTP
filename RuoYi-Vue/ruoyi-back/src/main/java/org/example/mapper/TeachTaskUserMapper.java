package org.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.example.domain.TeachTaskUser;

/**
 * 教学任务与用户关联Mapper接口
 * 
 * @author ruoyi
 * @date 2025-07-07
 */
@Mapper
public interface TeachTaskUserMapper 
{
    /**
     * 查询教学任务与用户关联
     * 
     * @param id 教学任务与用户关联主键
     * @return 教学任务与用户关联
     */
    public TeachTaskUser selectTeachTaskUserById(Long id);

    /**
     * 查询教学任务与用户关联列表
     * 
     * @param teachTaskUser 教学任务与用户关联
     * @return 教学任务与用户关联集合
     */
    public List<TeachTaskUser> selectTeachTaskUserList(TeachTaskUser teachTaskUser);

    /**
     * 新增教学任务与用户关联
     * 
     * @param teachTaskUser 教学任务与用户关联
     * @return 结果
     */
    public int insertTeachTaskUser(TeachTaskUser teachTaskUser);

    /**
     * 修改教学任务与用户关联
     * 
     * @param teachTaskUser 教学任务与用户关联
     * @return 结果
     */
    public int updateTeachTaskUser(TeachTaskUser teachTaskUser);

    /**
     * 删除教学任务与用户关联
     * 
     * @param id 教学任务与用户关联主键
     * @return 结果
     */
    public int deleteTeachTaskUserById(Long id);

    /**
     * 批量删除教学任务与用户关联
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTeachTaskUserByIds(Long[] ids);
}
