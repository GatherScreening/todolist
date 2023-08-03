package com.livechat.todolist.common.exception;

import com.livechat.todolist.common.constants.ErrCode;
import lombok.Data;

@Data
public class ServiceException extends Exception{
    private ErrCode errCode;
    public ServiceException(ErrCode errCode){
        this.errCode = errCode;
    }
}
