package com.livechat.todolist.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.livechat.todolist.model.entity.PersonalPlan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoListVo {
    @JsonProperty("plan_id")
    private Long planId;
    @JsonProperty("open_id")
    private String openId;
    /**
     * 某个计划
     */
    private String tips;
    /**
     * 计划时间，比如选择了2023-04-15
     */
    @JsonProperty("plan_time")
    private String planTime;

    private String remark;

    private Integer icon;

    public TodoListVo(PersonalPlan personalPlan){
        this.planId = personalPlan.getId();
        this.openId = personalPlan.getOpenId();
        this.tips = personalPlan.getTips();
        this.planTime = personalPlan.getPlanTime();
        this.icon = personalPlan.getIcon();
        this.remark = personalPlan.getRemark();
    }
}
