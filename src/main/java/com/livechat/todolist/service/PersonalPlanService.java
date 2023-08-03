package com.livechat.todolist.service;

import com.livechat.todolist.common.constants.ErrCode;
import com.livechat.todolist.common.exception.ServiceException;
import com.livechat.todolist.common.utils.UserUtils;
import com.livechat.todolist.dao.PersonalPlanDao;
import com.livechat.todolist.model.entity.PersonalPlan;
import com.livechat.todolist.model.vo.AddPlanVo;
import com.livechat.todolist.model.vo.TodoListVo;
import com.livechat.todolist.model.vo.request.DeleteVo;
import com.livechat.todolist.model.vo.request.FinishedVo;
import com.livechat.todolist.model.vo.request.GetListRequestVo;
import com.livechat.todolist.model.vo.response.TodoListResVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PersonalPlanService {

    @Autowired
    private PersonalPlanDao personalPlanDao;

    @Autowired
    private GroupPlanService groupPlanService;
    @Autowired
    private UserUtils utils;

    //插入计划
    public void addPlan(AddPlanVo addPlanVo) throws ServiceException {
        if (addPlanVo == null || StringUtils.isEmpty(addPlanVo.getTips())
        || StringUtils.isEmpty(addPlanVo.getPlanTime())){
            throw new ServiceException(ErrCode.PARAM_ERROR);
        }
        if (addPlanVo.getGroupId()!=null && addPlanVo.getGroupId() > 0){
            groupPlanService.addPlans(addPlanVo);
            utils.updateUserRecentLook(addPlanVo.getOpenId(), addPlanVo.getGroupId());
            if (!StringUtils.isEmpty(addPlanVo.getOpenIdList())){
                PersonalPlan plan = new PersonalPlan();
                plan.setPlanTime(addPlanVo.getPlanTime());
                plan.setIcon(addPlanVo.getIcon());
                plan.setTips(addPlanVo.getTips());
                plan.setCreateTime(System.currentTimeMillis());
                plan.setOpenId(addPlanVo.getOpenIdList());
                plan.setRemark(addPlanVo.getRemark());
                plan.setFinished(0);
                plan.setUpdateTime(System.currentTimeMillis());
                personalPlanDao.insert(plan);
            }
            return;
        }
        PersonalPlan personalPlan = new PersonalPlan(addPlanVo);
        personalPlanDao.insert(personalPlan);
    }

    //查询
    public TodoListResVo getList(GetListRequestVo getListRequestVo) throws ServiceException {
        TodoListResVo todoListResVo = new TodoListResVo();
        if (StringUtils.isEmpty(getListRequestVo.getOpenId())){
            throw new ServiceException(ErrCode.PARAM_ERROR);
        }
        //todo:请求chatgpt
        List<PersonalPlan> personalPlans = personalPlanDao.queryAll(getListRequestVo.getOpenId(),
                getListRequestVo.getPlanTime());
        if (CollectionUtils.isEmpty(personalPlans)){
            return todoListResVo;
        }
        List<TodoListVo> todoListVos = new ArrayList<>();
        List<TodoListVo> doneList = new ArrayList<>();
        for (PersonalPlan personalPlan: personalPlans){
            TodoListVo todoListVo = new TodoListVo(personalPlan);
            //未完成
            if (personalPlan.getFinished() == 0){
                todoListVos.add(todoListVo);
            }else{
                doneList.add(todoListVo);
            }
        }
        todoListResVo.setDoneList(doneList);
        todoListResVo.setTodoList(todoListVos);
        return todoListResVo;
    }

    public void updateDone(FinishedVo finishedVo) throws ServiceException {
        if (finishedVo == null
                || finishedVo.getFinished() == null
                || finishedVo.getPlanId() == null){
            throw new ServiceException(ErrCode.PARAM_ERROR);
        }
        Integer finished = finishedVo.getFinished() ? 1:0;
        if (personalPlanDao.queryById(finishedVo.getPlanId()) == null){
            throw new ServiceException(ErrCode.PLANID_NOT_FOUND);
        }
        personalPlanDao.updateFinished(finishedVo.getPlanId(), finished);
    }

    public void delete(DeleteVo deleteVo) throws ServiceException {
        if (deleteVo.getPlanId() == null){
            throw new ServiceException(ErrCode.PARAM_ERROR);
        }
        if (personalPlanDao.queryById(deleteVo.getPlanId()) == null){
            throw new ServiceException(ErrCode.PLANID_NOT_FOUND);
        }
        personalPlanDao.deleteById(deleteVo.getPlanId());
    }

}
