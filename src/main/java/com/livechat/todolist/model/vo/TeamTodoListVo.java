package com.livechat.todolist.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.livechat.todolist.model.entity.GroupPlan;
import com.livechat.todolist.model.vo.response.TeamTodoListResVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/***
 @ClassName: TeamTodoListVo
 @Author: zry
 @Date: 2023/4/17 20:20
 ***/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamTodoListVo {
    @JsonProperty("plan_id")
    private Long planId;
    @JsonProperty("group_id")
    private Long groupId;
    /**
     * 某个计划
     */
    private String tips;
    /**
     * 计划时间，比如选择了2023-04-15
     */
    @JsonProperty("plan_time")
    private String planTime;
    //指定用户列表
    @JsonProperty("user_list")
    private LoginVo userList;
    private Integer icon;
    private String remark;

    public TeamTodoListVo(GroupPlan groupPlan){
        this.planId = groupPlan.getId();
        this.groupId = groupPlan.getGroupId();
        this.tips = groupPlan.getTips();
        this.planTime = groupPlan.getPlanTime();
        this.icon = groupPlan.getIcon();
        this.remark = groupPlan.getRemark();
    }
}
