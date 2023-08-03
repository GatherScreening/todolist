package com.livechat.todolist.controller;

import com.livechat.todolist.common.constants.BaseResponse;
import com.livechat.todolist.common.constants.ErrCode;
import com.livechat.todolist.common.exception.ServiceException;
import com.livechat.todolist.model.vo.LoginVo;
import com.livechat.todolist.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    private LoginService loginService;
    @PostMapping("/login")
    public BaseResponse login(@RequestBody LoginVo loginVo){
        ErrCode code = ErrCode.SUCCESS;
        try{
            loginService.addUser(loginVo);
        }catch (ServiceException e){
            code = e.getErrCode();
        }
        return new BaseResponse(code);
    }
}
