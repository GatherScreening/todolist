package com.livechat.todolist.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.livechat.todolist.model.entity.MiniProgressUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVo {
    @JsonProperty("avatar_big")
    private String avatarBig;
    private String name;
    @JsonProperty("open_id")
    private String openId;
    public LoginVo(MiniProgressUser miniProgressUser){
        this.avatarBig = miniProgressUser.getAvatarBig();
        this.name = miniProgressUser.getName();
        this.openId = miniProgressUser.getOpenId();
    }

}
