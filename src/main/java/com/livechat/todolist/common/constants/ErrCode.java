package com.livechat.todolist.common.constants;


public enum ErrCode {
    SUCCESS(200, "success"),
    PARAM_ERROR(4001, "param error"),
    SERVICE_ERROR(4000, "service error"),
    PLANID_NOT_FOUND(4002, "plan id not found"),
    GROUP_NOT_FOUND(4003, "group not found"),
    DUPLICATE_ADD_GROUP(4005, "you are in this group");
    private int code;
    private String msg;
    ErrCode(int code , String msg){
        this.code = code;
        this.msg = msg;
    }
    public int getCode(){
        return code;
    }
    public String getMsg(){
        return msg;
    }
}
