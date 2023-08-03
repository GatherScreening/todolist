package com.livechat.todolist.model.vo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetListRequestVo {
    @JsonProperty("open_id")
    private String openId;
    @JsonProperty("time")
    private String planTime;
}
