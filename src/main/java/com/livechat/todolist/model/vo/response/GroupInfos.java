package com.livechat.todolist.model.vo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.livechat.todolist.model.entity.GroupMembers;
import com.livechat.todolist.model.vo.LoginVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupInfos {
    /**
     * 团队id
     */
    @JsonProperty("group_id")
    private Long groupId;
    /**
     * 团队名称
     */
    @JsonProperty("group_name")
    private String groupName;
    /**
     * 群主id
     */
    @JsonProperty("owner_info")
    private LoginVo ownerInfo;
    @JsonProperty("create_time")
    private Long createTime;
    @JsonProperty("group_icon")
    private Integer groupIcon;
    @JsonProperty("group_code")
    private String groupCode;

    private List<LoginVo> userList = new ArrayList<>();

    public GroupInfos(GroupMembers groupMembers){
        this.groupId = groupMembers.getId();
        this.createTime = groupMembers.getCreateTime();
        this.groupIcon = groupMembers.getGroupIcon();
        this.groupCode = groupMembers.getGroupCode();
        this.groupName = groupMembers.getGroupName();
    }

}
