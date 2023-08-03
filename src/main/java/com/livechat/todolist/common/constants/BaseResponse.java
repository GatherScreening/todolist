package com.livechat.todolist.common.constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
    private int code;
    private String msg;
    private T data;
    public BaseResponse(ErrCode errCode){
        this.code = errCode.getCode();
        this.msg = errCode.getMsg();
    }
}
