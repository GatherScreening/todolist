package com.livechat.todolist.model.vo.response;

import com.livechat.todolist.model.vo.TeamTodoListVo;
import com.livechat.todolist.model.vo.TodoListVo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/***
 @ClassName: TeamTodoListResVo
 @Author: zry
 @Date: 2023/4/17 20:21
 ***/
@Data
public class TeamTodoListResVo {
    private List<TeamTodoListVo> todoList= new ArrayList<>();
    private List<TeamTodoListVo> doneList = new ArrayList<>();
    private String gptTips = "";
}
