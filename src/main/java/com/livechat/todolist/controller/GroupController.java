package com.livechat.todolist.controller;

import com.livechat.todolist.common.constants.BaseResponse;
import com.livechat.todolist.common.constants.ErrCode;
import com.livechat.todolist.common.exception.ServiceException;
import com.livechat.todolist.model.vo.ChatGptVo;
import com.livechat.todolist.model.vo.request.*;
import com.livechat.todolist.model.vo.response.*;
import com.livechat.todolist.service.GroupPlanService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/***
 @ClassName: GroupController
 @Author: zry
 @Date: 2023/4/17 15:56
 ***/

@RequestMapping("/team")
@CrossOrigin
@RestController
public class GroupController {

    @Autowired
    private GroupPlanService groupPlanService;
    //创建团队
    @PostMapping("/create")
    public BaseResponse<TeamResVo> createTeam(
            @RequestBody CreateTeamVo createTeamVo){
        BaseResponse<TeamResVo> teamResVoBaseResponse = new BaseResponse<>();
        TeamResVo resVo = new TeamResVo();
        ErrCode errCode = ErrCode.SUCCESS;
        try{
         resVo = groupPlanService.createGroup(createTeamVo);
        }catch (ServiceException e){
            errCode = e.getErrCode();
        }
        teamResVoBaseResponse.setCode(errCode.getCode());
        teamResVoBaseResponse.setMsg(errCode.getMsg());
        teamResVoBaseResponse.setData(resVo);
        return teamResVoBaseResponse;
    }

    @PostMapping("/add-user")
    public BaseResponse addTeam(@RequestBody AddTeam addTeam){
        BaseResponse teamResVoBaseResponse = new BaseResponse<>();
        ErrCode errCode = ErrCode.SUCCESS;
        try{
            groupPlanService.addTeam(addTeam);
        }catch (ServiceException e){
            errCode = e.getErrCode();
        }
        teamResVoBaseResponse.setCode(errCode.getCode());
        teamResVoBaseResponse.setMsg(errCode.getMsg());
        return teamResVoBaseResponse;
    }
    @GetMapping("/get-users")
    public BaseResponse<TeamMemberList> getUsers(@RequestParam("group_id") Long groupId){
        BaseResponse<TeamMemberList> teamResVoBaseResponse = new BaseResponse<>();
        TeamMemberList list = new TeamMemberList();
        ErrCode errCode = ErrCode.SUCCESS;
        try{
          list=  groupPlanService.getUsers(groupId);
        }catch (ServiceException e){
            errCode = e.getErrCode();
        }
        teamResVoBaseResponse.setCode(errCode.getCode());
        teamResVoBaseResponse.setMsg(errCode.getMsg());
        teamResVoBaseResponse.setData(list);
        return teamResVoBaseResponse;
    }
    @PostMapping("/todo-list")
    public BaseResponse<TeamTodoListResVo> getTodoList(@RequestBody GetTeamTodo teamTodo){
        BaseResponse<TeamTodoListResVo> response = new BaseResponse<>();
        ErrCode code = ErrCode.SUCCESS;
        TeamTodoListResVo res = new TeamTodoListResVo();
        try{
            res = groupPlanService.getList(teamTodo);
        }catch (Exception e){
            code = ErrCode.SERVICE_ERROR;
        }
        response.setCode(code.getCode());
        response.setMsg(code.getMsg());
        response.setData(res);
        return response;
    }

    @PostMapping("/finish")
    public BaseResponse finishPlan(@RequestBody FinishTeamPlan finishTeamPlan){
        BaseResponse teamResVoBaseResponse = new BaseResponse<>();
        ErrCode errCode = ErrCode.SUCCESS;
        try{
            groupPlanService.update(finishTeamPlan);
        }catch (Exception e){
            errCode = ErrCode.SERVICE_ERROR;
        }
        teamResVoBaseResponse.setCode(errCode.getCode());
        teamResVoBaseResponse.setMsg(errCode.getMsg());
        return teamResVoBaseResponse;
    }

    @PostMapping("/delete")
    public BaseResponse deletePlan(@RequestBody DeleteVo deleteVo){
        BaseResponse teamResVoBaseResponse = new BaseResponse<>();
        ErrCode errCode = ErrCode.SUCCESS;
        try{
            groupPlanService.delete(deleteVo);
        }catch (Exception e){
            errCode = ErrCode.SERVICE_ERROR;
        }
        teamResVoBaseResponse.setCode(errCode.getCode());
        teamResVoBaseResponse.setMsg(errCode.getMsg());
        return teamResVoBaseResponse;
    }

    @GetMapping("/list")
    public BaseResponse<GroupVo> getUserTeamList(@RequestParam("open_id") String openId){
        BaseResponse<GroupVo> response = new BaseResponse<>();
        ErrCode code = ErrCode.SUCCESS;
        GroupVo groupVo = new GroupVo();
        try{
            groupVo = groupPlanService.getGroupList(openId);
        }catch (ServiceException e){
            code = e.getErrCode();
        }catch (Exception e){
            code = ErrCode.SERVICE_ERROR;
        }
        response.setData(groupVo);
        response.setCode(code.getCode());
        response.setMsg(code.getMsg());
        return response;
    }
    @PostMapping("/leave-team")
    public BaseResponse leaveTeam(@RequestBody LeaveGroupVo addTeam){
        groupPlanService.leaveGroup(addTeam);
        return new BaseResponse(ErrCode.SUCCESS);
    }

    @GetMapping("/info")
    public BaseResponse<GroupInfos> getGroupInfo(@RequestParam("group_id") Long groupId){
        BaseResponse<GroupInfos> response = new BaseResponse<>(ErrCode.SUCCESS);
        GroupInfos groupInfos = new GroupInfos();
        try{
            groupInfos = groupPlanService.getGroupInfo(groupId);
        }catch (Exception e){

        }
        response.setData(groupInfos);
        return response;
    }

}
