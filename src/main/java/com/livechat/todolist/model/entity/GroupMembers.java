package com.livechat.todolist.model.entity;

import com.livechat.todolist.common.utils.StringToList;
import com.livechat.todolist.model.vo.request.CreateTeamVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 团队成员表(GroupMembers)实体类
 *
 * @author makejava
 * @since 2023-04-17 18:37:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupMembers implements Serializable {
    private static final long serialVersionUID = -24365686830031938L;
    /**
     * 团队id
     */
    private Long id;
    /**
     * 团队名称
     */
    private String groupName;
    /**
     * 群成员id
     */
    private String openIdList;
    /**
     * 群主id
     */
    private String ownerId;

    private Long createTime;

    private Integer groupIcon;

    private String groupCode;

    public GroupMembers(CreateTeamVo createTeamVo){
        this.groupName = createTeamVo.getGroupName();
        this.openIdList = "";
        this.ownerId = createTeamVo.getOpenId();
        this.createTime = System.currentTimeMillis();
        this.groupIcon = createTeamVo.getGroupIcon();
        this.groupCode = StringToList.generateCode();
    }
}

