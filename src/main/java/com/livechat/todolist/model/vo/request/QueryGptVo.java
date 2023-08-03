package com.livechat.todolist.model.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryGptVo {
    private String model = "gpt-3.5-turbo";
    private List<QueryGptVo> messages;
}
