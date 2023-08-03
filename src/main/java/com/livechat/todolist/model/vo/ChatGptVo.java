package com.livechat.todolist.model.vo;

import lombok.Data;

//根据用户的当日计划获取chatgpt提供的时间规划
@Data
public class ChatGptVo {
    private String chatGptTips = "";
}
