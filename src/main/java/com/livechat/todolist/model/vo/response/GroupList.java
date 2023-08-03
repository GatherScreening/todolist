package com.livechat.todolist.model.vo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.livechat.todolist.model.entity.GroupMembers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupList {
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
    @JsonProperty("open_id")
    private String openId;

    @JsonProperty("group_icon")
    private Integer groupIcon;
    @JsonProperty("group_code")
    private String groupCode;

    public GroupList(GroupMembers groupMembers){
        this.groupIcon = groupMembers.getGroupIcon();
        this.groupId = groupMembers.getId();
        this.openId = groupMembers.getOwnerId();
        this.groupName = groupMembers.getGroupName();
        this.groupCode = groupMembers.getGroupCode();
    }
}
