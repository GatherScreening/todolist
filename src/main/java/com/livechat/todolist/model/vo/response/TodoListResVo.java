package com.livechat.todolist.model.vo.response;

import com.livechat.todolist.model.vo.TodoListVo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TodoListResVo {
    private List<TodoListVo> todoList= new ArrayList<>();
    private List<TodoListVo> doneList = new ArrayList<>();
    private String gptTips = "";
}
