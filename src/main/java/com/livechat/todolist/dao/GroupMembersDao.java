package com.livechat.todolist.dao;

import com.livechat.todolist.model.entity.GroupMembers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
//import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 团队成员表(GroupMembers)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-17 18:37:50
 */
@Mapper
public interface GroupMembersDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    GroupMembers queryById(Long id);
    GroupMembers queryByGroupCode(@Param("groupCode") String groupCode);

    List<GroupMembers> queryAll();

    /**
     * 查询指定行数据
     *
     * @param groupMembers 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
//    List<GroupMembers> queryAllByLimit(GroupMembers groupMembers, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param groupMembers 查询条件
     * @return 总行数
     */
    long count(GroupMembers groupMembers);

    /**
     * 新增数据
     *
     * @param groupMembers 实例对象
     * @return 影响行数
     */
    Long insert(GroupMembers groupMembers);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<GroupMembers> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<GroupMembers> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<GroupMembers> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<GroupMembers> entities);

    /**
     * 修改数据
     *
     * @param groupMembers 实例对象
     * @return 影响行数
     */
    int update(GroupMembers groupMembers);

    void updateCreateTime(@Param("id") Long id,
                          @Param("create_time") Long creatTime);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

