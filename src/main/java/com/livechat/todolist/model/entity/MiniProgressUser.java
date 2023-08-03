package com.livechat.todolist.model.entity;

import com.livechat.todolist.model.vo.LoginVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * 用户信息表(MiniProgressUser)实体类
 *
 * @author makejava
 * @since 2023-04-15 17:46:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MiniProgressUser implements Serializable {
    private static final long serialVersionUID = 117043366096733942L;
    
    private Long id;
    /**
     * 小程序openid
     */
    private String openId;
    
    private String avatarBig;
    
    private String name;

    private Long groupId;

    private Long recentLook;

    public MiniProgressUser(LoginVo loginVo){
        this.openId = loginVo.getOpenId();
        this.avatarBig = loginVo.getAvatarBig();
        this.name = loginVo.getName();
    }

}

