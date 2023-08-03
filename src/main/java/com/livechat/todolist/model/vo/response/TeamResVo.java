package com.livechat.todolist.model.vo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/***
 @ClassName: TeamResVo
 @Author: zry
 @Date: 2023/4/17 18:30
 ***/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamResVo {
    @JsonProperty("group_id")
    private Long groupId;
    @JsonProperty("group_code")
    private String groupCode;
}
