package com.livechat.todolist.common.utils;

import com.livechat.todolist.dao.MiniProgressUserDao;
import com.livechat.todolist.model.entity.MiniProgressUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserUtils {

    @Autowired
    private MiniProgressUserDao miniProgressUserDao;

    public void updateUserRecentLook(String openId, Long groupId){
        MiniProgressUser user = miniProgressUserDao.selectByGroupIdAndOpenId(openId, groupId);
        if (user == null){
            List<MiniProgressUser> users = miniProgressUserDao.queryById(openId);
            MiniProgressUser user1 = users.get(0);
            user1.setRecentLook(System.currentTimeMillis());
            user1.setGroupId(groupId);
            miniProgressUserDao.insert(user1);
        }else{
            miniProgressUserDao.updateRecentLook(user.getOpenId(), groupId, System.currentTimeMillis());
        }
    }
}
