package com.livechat.todolist.model.vo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DeleteVo {
    @JsonProperty("plan_id")
    private Long planId;
}
