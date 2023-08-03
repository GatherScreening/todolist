package com.livechat.todolist.model.vo.response;

import com.livechat.todolist.model.vo.LoginVo;
import lombok.Data;

import java.util.List;

/***
 @ClassName: TeamMemberList
 @Author: zry
 @Date: 2023/4/17 19:48
 ***/
@Data
public class TeamMemberList {
    private List<LoginVo> userList;
}
