package com.livechat.todolist.service;

import com.livechat.todolist.common.constants.ErrCode;
import com.livechat.todolist.common.exception.ServiceException;
import com.livechat.todolist.common.utils.StringToList;
import com.livechat.todolist.common.utils.UserUtils;
import com.livechat.todolist.dao.GroupMembersDao;
import com.livechat.todolist.dao.GroupPlanDao;
import com.livechat.todolist.dao.MiniProgressUserDao;
import com.livechat.todolist.dao.PersonalPlanDao;
import com.livechat.todolist.model.entity.GroupMembers;
import com.livechat.todolist.model.entity.GroupPlan;
import com.livechat.todolist.model.entity.MiniProgressUser;
import com.livechat.todolist.model.vo.AddPlanVo;
import com.livechat.todolist.model.vo.LoginVo;
import com.livechat.todolist.model.vo.TeamTodoListVo;
import com.livechat.todolist.model.vo.request.*;
import com.livechat.todolist.model.vo.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/***
 @ClassName: GroupPlanService
 @Author: zry
 @Date: 2023/4/17 18:29
 ***/
@Service
public class GroupPlanService {
    @Autowired
    private GroupMembersDao groupMembersDao;

    @Autowired
    private GroupPlanDao groupPlanDao;

    @Autowired
    private MiniProgressUserDao miniProgressUserDao;

    @Autowired
    private PersonalPlanDao personalPlanDao;

    @Autowired
    private UserUtils utils;


    //返回群id
    public TeamResVo createGroup(CreateTeamVo createTeamVo) throws ServiceException {
        TeamResVo teamResVo = new TeamResVo();
        if (StringUtils.isEmpty(createTeamVo.getGroupName())
        || StringUtils.isEmpty(createTeamVo.getOpenId())){
            throw new ServiceException(ErrCode.PARAM_ERROR);
        }

        GroupMembers groupMembers = new GroupMembers(createTeamVo);
        GroupMembers members = groupMembersDao.queryByGroupCode(groupMembers.getGroupCode());
        while (members!=null){
            groupMembers.setGroupCode(StringToList.generateCode());
            members = groupMembersDao.queryByGroupCode(groupMembers.getGroupCode());
        }
        groupMembersDao.insert(groupMembers);
        List<MiniProgressUser> miniProgressUsers = miniProgressUserDao.queryById(createTeamVo.getOpenId());
        if (CollectionUtils.isEmpty(miniProgressUsers)){
            throw new ServiceException(ErrCode.PARAM_ERROR);
        }
        MiniProgressUser miniProgressUser = miniProgressUsers.get(0);
        miniProgressUser.setGroupId(groupMembers.getId());
        miniProgressUser.setRecentLook(System.currentTimeMillis());
        miniProgressUserDao.insert(miniProgressUser);
        teamResVo.setGroupId(groupMembers.getId());
        teamResVo.setGroupCode(groupMembers.getGroupCode());
        return teamResVo;
    }
    //个人添加进团队
    public void addTeam(AddTeam addTeam) throws ServiceException {
        if (StringUtils.isEmpty(addTeam.getGroupCode()) ||
        StringUtils.isEmpty(addTeam.getOpenId())){
            throw new ServiceException(ErrCode.PARAM_ERROR);
        }
        GroupMembers groupMembers = groupMembersDao.queryByGroupCode(addTeam.getGroupCode());
        if (groupMembers == null) {
            throw new ServiceException(ErrCode.GROUP_NOT_FOUND);
        }
        String list = groupMembers.getOpenIdList();
        utils.updateUserRecentLook(addTeam.getOpenId(), groupMembers.getId());
        if (StringUtils.isEmpty(list)){
            groupMembers.setOpenIdList(addTeam.getOpenId());
            groupMembersDao.update(groupMembers);
            return;
        }
        List<String> openIds = StringToList.getList(groupMembers.getOpenIdList());
        if (openIds.contains(addTeam.getOpenId())){
            throw new ServiceException(ErrCode.DUPLICATE_ADD_GROUP);
        }
        openIds.add(addTeam.getOpenId());
        groupMembers.setOpenIdList(StringToList.listToString(openIds));
        groupMembersDao.update(groupMembers);
        List<MiniProgressUser> miniProgressUser = miniProgressUserDao.queryById(addTeam.getOpenId());
        MiniProgressUser user = miniProgressUser.get(0);
        user.setRecentLook(System.currentTimeMillis());
        user.setGroupId(groupMembers.getId());
        miniProgressUserDao.insert(user);
    }
    public TeamMemberList getUsers(Long groupId) throws ServiceException {
        TeamMemberList teamMemberList = new TeamMemberList();
        List<LoginVo> userInfos = new ArrayList<>();
        if (groupId <= 0){
            throw new ServiceException(ErrCode.PARAM_ERROR);
        }
        GroupMembers groupMembers = groupMembersDao.queryById(groupId);
        if (groupMembers == null) {
            throw new ServiceException(ErrCode.GROUP_NOT_FOUND);
        }
        List<String> openIds = StringToList.getList(groupMembers.getOpenIdList());
        for (String openId: openIds){
            if (openId.equals(groupMembers.getOwnerId())){
                continue;
            }
            List<MiniProgressUser> miniProgressUsers = miniProgressUserDao.queryById(openId);
            if (CollectionUtils.isEmpty(miniProgressUsers)){
                continue;
            }
            MiniProgressUser miniProgressUser = miniProgressUsers.get(0);
            if (miniProgressUser != null){
                userInfos.add(new LoginVo(miniProgressUser));
            }
        }
        teamMemberList.setUserList(userInfos);
        return teamMemberList;
    }

    public void addPlans(AddPlanVo addTeamPlan){
        GroupPlan groupPlan = new GroupPlan(addTeamPlan);
        groupPlanDao.insert(groupPlan);
    }

    public TeamTodoListResVo getList(GetTeamTodo getTeamTodo){
        TeamTodoListResVo teamTodoListResVo = new TeamTodoListResVo();
        List<GroupPlan> groupPlans = groupPlanDao.queryAll(getTeamTodo.getGroupId());
        if (CollectionUtils.isEmpty(groupPlans)){
            return teamTodoListResVo;
        }
        List<TeamTodoListVo> todoList = new ArrayList<>();
        List<TeamTodoListVo> doneList = new ArrayList<>();
        for (GroupPlan groupPlan: groupPlans){
            LoginVo userInfos = new LoginVo();
            if (!StringUtils.isEmpty(groupPlan.getOpenIdList())){
                //查group members
                List<MiniProgressUser> miniProgressUsers = miniProgressUserDao.queryById(groupPlan.getOpenIdList());
                MiniProgressUser miniProgressUser = miniProgressUsers.get(0);
                if (miniProgressUser != null){
                    userInfos = new LoginVo(miniProgressUser);
                }
            }
            TeamTodoListVo teamTodoListVo = new TeamTodoListVo(groupPlan);
            teamTodoListVo.setUserList(userInfos);
            if (groupPlan.getFinished() == 0){
                todoList.add(teamTodoListVo);
            }else {
                doneList.add(teamTodoListVo);
            }
        }
        teamTodoListResVo.setTodoList(todoList);
        teamTodoListResVo.setDoneList(doneList);
        teamTodoListResVo.setGptTips("");
        return teamTodoListResVo;
    }

    public void update(FinishTeamPlan finishTeamPlan){
        groupPlanDao.updateFinished(finishTeamPlan.getPlanId(), finishTeamPlan.getFinished()?1:0);
        GroupPlan groupPlan = groupPlanDao.queryById(finishTeamPlan.getPlanId());
        if (groupPlan == null){
            return;
        }
        //更新个人计划
        if (!StringUtils.isEmpty(groupPlan.getOpenIdList())){
            personalPlanDao.updateUserFinished(groupPlan.getTips(),
                    groupPlan.getPlanTime(), groupPlan.getOpenIdList(), groupPlan.getFinished());
        }
        utils.updateUserRecentLook(finishTeamPlan.getOpenId(), groupPlan.getGroupId());
    }
    public void delete(DeleteVo deleteVo){
        GroupPlan groupPlan = groupPlanDao.queryById(deleteVo.getPlanId());
        //删除个人计划
        if (!StringUtils.isEmpty(groupPlan.getOpenIdList())){
            personalPlanDao.delete(groupPlan.getTips(), groupPlan.getPlanTime(),groupPlan.getOpenIdList());
        }
        //删除组计划
        groupPlanDao.deleteById(deleteVo.getPlanId());
    }

    /***
     * 获取用户加入的群组信息
     * @param openId
     * @return
     */
    public GroupVo getGroupList(String openId) throws ServiceException {
        if (StringUtils.isEmpty(openId)){
            throw new ServiceException(ErrCode.PARAM_ERROR);
        }
        GroupVo groupVo = new GroupVo();
        List<GroupList> groupLists = new ArrayList<>();
        List<MiniProgressUser> miniProgressUsers = miniProgressUserDao.queryById(openId);
        for (MiniProgressUser user: miniProgressUsers){
            if (user.getGroupId() == null){
                continue;
            }
            GroupMembers groupMembers = groupMembersDao.queryById(user.getGroupId());
            if (groupMembers == null){
                continue;
            }
            GroupList groupList = new GroupList(groupMembers);
            groupLists.add(groupList);
        }
        groupVo.setTeamList(groupLists);
        return groupVo;
    }

    public void leaveGroup(LeaveGroupVo addTeam){
        GroupMembers groupMembers = groupMembersDao.queryById(addTeam.getGroupId());
        String openIdList = groupMembers.getOpenIdList();
        List<String> list = StringToList.getList(openIdList);
        //群主退群，则随机指派人当群主
        if (groupMembers.getOwnerId().equals(addTeam.getOpenId())){
            //该群为空则删除群
            if (CollectionUtils.isEmpty(list)){

            }
            else {
                //否则指派新群主
                groupMembers.setOwnerId(list.get(0));
                list.remove(0);
            }
        }
        list.remove(addTeam.getOpenId());
        groupMembers.setOpenIdList(StringToList.listToString(list));
        groupMembersDao.update(groupMembers);
        miniProgressUserDao.deleteByGroupIdAndOpenId(addTeam.getOpenId(), addTeam.getGroupId());
    }

    public GroupInfos getGroupInfo(Long groupId) throws ServiceException {
        GroupMembers groupMembers = groupMembersDao.queryById(groupId);
        GroupInfos groupInfos = new GroupInfos(groupMembers);
        TeamMemberList users = getUsers(groupId);
        groupInfos.setUserList(users.getUserList());
        List<MiniProgressUser> users1 = miniProgressUserDao.queryById(groupMembers.getOwnerId());
        LoginVo owner = new LoginVo(users1.get(0));
        groupInfos.setOwnerInfo(owner);
        return groupInfos;
    }

}
