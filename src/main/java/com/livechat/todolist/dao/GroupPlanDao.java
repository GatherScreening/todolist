package com.livechat.todolist.dao;

import com.livechat.todolist.model.entity.GroupPlan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 团队计划表(GroupPlan)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-17 18:13:27
 */
@Mapper
public interface GroupPlanDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    GroupPlan queryById(Long id);

    List<GroupPlan> queryAll(@Param("groupId") Long groupId);

    /**
     * 查询指定行数据
     *
     * @param groupPlan 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
//    List<GroupPlan> queryAllByLimit(GroupPlan groupPlan, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param groupPlan 查询条件
     * @return 总行数
     */
    long count(GroupPlan groupPlan);

    /**
     * 新增数据
     *
     * @param groupPlan 实例对象
     * @return 影响行数
     */
    int insert(GroupPlan groupPlan);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<GroupPlan> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<GroupPlan> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<GroupPlan> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<GroupPlan> entities);

    /**
     * 修改数据
     *
     * @param groupPlan 实例对象
     * @return 影响行数
     */
    int update(GroupPlan groupPlan);

    int updateFinished(@Param("id") Long id,
                       @Param("finished") Integer finished);
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

