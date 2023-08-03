package com.livechat.todolist.service;

import com.livechat.todolist.common.constants.ErrCode;
import com.livechat.todolist.common.exception.ServiceException;
import com.livechat.todolist.dao.MiniProgressUserDao;
import com.livechat.todolist.model.entity.MiniProgressUser;
import com.livechat.todolist.model.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

@Service
public class LoginService {

    @Autowired
    private MiniProgressUserDao miniProgressUserDao;
    public void addUser(LoginVo loginVo) throws ServiceException {
        if (StringUtils.isEmpty(loginVo.getOpenId())){
            throw new ServiceException(ErrCode.PARAM_ERROR);
        }
        MiniProgressUser miniProgressUser = new MiniProgressUser(loginVo);
        List<MiniProgressUser> historyUser = miniProgressUserDao.queryById(loginVo.getOpenId());
        if (CollectionUtils.isEmpty(historyUser)){
            miniProgressUserDao.insert(miniProgressUser);
            return;
        }
        if (historyUser.get(0).getAvatarBig().equals(loginVo.getAvatarBig())){
            return;
        }
        miniProgressUserDao.update(miniProgressUser);
    }

}
