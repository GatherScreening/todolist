package com.livechat.todolist.dao;

import com.livechat.todolist.model.entity.PersonalPlan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 个人计划(PersonalPlan)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-16 11:24:29
 */
@Mapper
public interface PersonalPlanDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PersonalPlan queryById(Long id);

    List<PersonalPlan> queryAll(@Param("openId") String openId,
                                @Param("planTime") String planTime);

    /**
     * 查询指定行数据
     *
     * @param personalPlan 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
//    List<PersonalPlan> queryAllByLimit(PersonalPlan personalPlan, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param personalPlan 查询条件
     * @return 总行数
     */
    long count(PersonalPlan personalPlan);

    /**
     * 新增数据
     *
     * @param personalPlan 实例对象
     * @return 影响行数
     */
    int insert(PersonalPlan personalPlan);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<PersonalPlan> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<PersonalPlan> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<PersonalPlan> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<PersonalPlan> entities);

    /**
     * 修改数据
     *
     * @param personalPlan 实例对象
     * @return 影响行数
     */
    int update(PersonalPlan personalPlan);

    int updateFinished(@Param("planId") Long planId,
                       @Param("finished") Integer finished);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    int updateUserFinished(@Param("tips")String tips,
                           @Param("planTime")String planTime,
                           @Param("openId") String openId,
                           @Param("finished")Integer finished);

    int delete(@Param("tips")String tips,
               @Param("planTime")String planTime,
               @Param("openId") String openId);

}

