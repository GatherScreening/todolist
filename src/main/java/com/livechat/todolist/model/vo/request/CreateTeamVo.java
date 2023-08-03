package com.livechat.todolist.model.vo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 @ClassName: CreateTeamVo
 @Author: zry
 @Date: 2023/4/17 18:39
 ***/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTeamVo {
    //群主openid
    @JsonProperty("open_id")
    private String openId;
    @JsonProperty("group_name")
    private String groupName;
    @JsonProperty("group_icon")
    private Integer groupIcon;
}
