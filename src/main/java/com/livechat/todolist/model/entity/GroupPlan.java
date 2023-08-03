package com.livechat.todolist.model.entity;

import com.livechat.todolist.common.utils.StringToList;
import com.livechat.todolist.model.vo.AddPlanVo;
import com.livechat.todolist.model.vo.request.AddTeamPlan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;

/**
 * 团队计划表(GroupPlan)实体类
 *
 * @author makejava
 * @since 2023-04-17 18:13:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupPlan implements Serializable {
    private static final long serialVersionUID = 752153835311591697L;
    /**
     * 团队计划id
     */
    private Long id;
    /**
     * 所属团队id
     */
    private Long groupId;
    /**
     * finished=1, unfinished=0
     */
    private Integer finished;
    /**
     * 某个计划
     */
    private String tips;
    /**
     * 指定某人完成
     */
    private String openIdList;
    /**
     * 计划时间，比如选择了2023-04-15
     */
    private String planTime;

    private Long createTime;

    private Long updateTime;

    private Integer icon;
    private String remark;

    public GroupPlan(AddPlanVo a){
        this.groupId = a.getGroupId();
        this.finished = 0;
        this.planTime = a.getPlanTime();
        this.openIdList = a.getOpenIdList();
        this.tips = a.getTips();
        this.icon = a.getIcon();
        this.remark = a.getRemark();
        this.createTime = System.currentTimeMillis();
        this.updateTime = System.currentTimeMillis();
    }

}

