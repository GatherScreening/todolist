package com.livechat.todolist.controller;

import com.livechat.todolist.common.constants.BaseResponse;
import com.livechat.todolist.common.constants.ErrCode;
import com.livechat.todolist.common.exception.ServiceException;
import com.livechat.todolist.model.vo.AddPlanVo;
import com.livechat.todolist.model.vo.ChatGptVo;
import com.livechat.todolist.model.vo.request.DeleteVo;
import com.livechat.todolist.model.vo.request.FinishedVo;
import com.livechat.todolist.model.vo.request.GetListRequestVo;
import com.livechat.todolist.model.vo.response.TodoListResVo;
import com.livechat.todolist.service.PersonalPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/personal")
public class PersonalController {

    @Autowired
    private PersonalPlanService personalPlanService;

    @PostMapping("/add")
    public BaseResponse<ChatGptVo> addPlan(@RequestBody AddPlanVo addPlanVo){
        ErrCode code = ErrCode.SUCCESS;
        try{
            personalPlanService.addPlan(addPlanVo);
            //todo:请求chatgpt获取建议时间规划
        }catch (ServiceException e){
            code = e.getErrCode();
        }
        return new BaseResponse(code);
    }

    @PostMapping("/todo-list")
    public BaseResponse<TodoListResVo> getTodoList(@RequestBody GetListRequestVo getListRequestVo){
        ErrCode code = ErrCode.SUCCESS;
        BaseResponse<TodoListResVo> response = new BaseResponse<>();
        TodoListResVo todoListResVo = new TodoListResVo();
        try{
          todoListResVo = personalPlanService.getList(getListRequestVo);
        }catch (ServiceException e){
            code = e.getErrCode();
        }
        response.setCode(code.getCode());
        response.setMsg(code.getMsg());
        response.setData(todoListResVo);
        return response;
    }
    @PostMapping("/finish")
    public BaseResponse finished(@RequestBody FinishedVo finishedVo){
        ErrCode code = ErrCode.SUCCESS;
        try{
            personalPlanService.updateDone(finishedVo);
        }catch (ServiceException e){
            code = e.getErrCode();
        }catch (Exception e){
            code = ErrCode.SERVICE_ERROR;
        }
        return new BaseResponse(code);
    }
    @PostMapping("/delete")
    public BaseResponse delete(@RequestBody DeleteVo deleteVo){
        ErrCode code = ErrCode.SUCCESS;
        try{
            personalPlanService.delete(deleteVo);
        }catch (ServiceException e){
            code = e.getErrCode();
        }catch (Exception e){
            code = ErrCode.SERVICE_ERROR;
        }
        return new BaseResponse(code);
    }

}
