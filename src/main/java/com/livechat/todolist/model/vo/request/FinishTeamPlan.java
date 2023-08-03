package com.livechat.todolist.model.vo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 @ClassName: FinishTeamPlan
 @Author: zry
 @Date: 2023/4/17 20:17
 ***/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinishTeamPlan {
    @JsonProperty("plan_id")
    private Long planId;

    private Boolean finished = false;

    @JsonProperty("open_id")
    private String openId;
}
