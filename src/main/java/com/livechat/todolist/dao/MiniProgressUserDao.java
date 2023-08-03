package com.livechat.todolist.dao;

import com.livechat.todolist.model.entity.MiniProgressUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 用户信息表(MiniProgressUser)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-15 17:46:22
 */
@Mapper
public interface MiniProgressUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @return 实例对象
     */
    List<MiniProgressUser> queryById(@Param("openId") String openId);

    /**
     * 查询指定行数据
     *
     * @param miniProgressUser 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
//    List<MiniProgressUser> queryAllByLimit(MiniProgressUser miniProgressUser, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param miniProgressUser 查询条件
     * @return 总行数
     */
    long count(MiniProgressUser miniProgressUser);

    /**
     * 新增数据
     *
     * @param miniProgressUser 实例对象
     * @return 影响行数
     */
    int insert(MiniProgressUser miniProgressUser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<MiniProgressUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<MiniProgressUser> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<MiniProgressUser> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<MiniProgressUser> entities);

    /**
     * 修改数据
     *
     * @param miniProgressUser 实例对象
     * @return 影响行数
     */
    int update(MiniProgressUser miniProgressUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    void deleteByGroupIdAndOpenId(@Param("openId")String openId,
                                  @Param("groupId") Long groupId);

    int updateRecentLook(@Param("openId") String openId,
                         @Param("groupId") Long groupId,
                         @Param("recentLook") Long recentLook);
    MiniProgressUser selectByGroupIdAndOpenId(@Param("openId")String openId,
                                              @Param("groupId") Long groupId);
}

