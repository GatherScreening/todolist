package com.livechat.todolist.model.vo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/***
 @ClassName: GetTeamTodo
 @Author: zry
 @Date: 2023/4/17 20:15
 ***/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTeamTodo {
    @JsonProperty("group_id")
    private Long groupId;
    @JsonProperty("time")
    private String planTime;
}
