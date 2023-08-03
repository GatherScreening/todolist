package com.livechat.todolist.model.vo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/***
 @ClassName: AddTeam
 @Author: zry
 @Date: 2023/4/17 19:20
 ***/
@Data
public class AddTeam {
    @JsonProperty("open_id")
    private String openId;
    @JsonProperty("group_code")
    private String groupCode;

}
