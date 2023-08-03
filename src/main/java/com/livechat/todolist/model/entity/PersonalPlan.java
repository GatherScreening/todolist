package com.livechat.todolist.model.entity;

import com.livechat.todolist.model.vo.AddPlanVo;
import com.livechat.todolist.model.vo.TodoListVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 个人计划(PersonalPlan)实体类
 *
 * @author makejava
 * @since 2023-04-16 11:24:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalPlan implements Serializable {
    private static final long serialVersionUID = 933672715246639239L;
    
    private Long id;
    
    private String openId;
    /**
     * finished=1, unfinished=0
     */
    private Integer finished;
    /**
     * 某个计划
     */
    private String tips;
    /**
     * 计划时间，比如选择了2023-04-15
     */
    private String planTime;
    
    private Long createTime;
    
    private Long updateTime;

    private Integer icon;

    private String remark;

    public PersonalPlan(AddPlanVo addPlanVo){
        this.openId = addPlanVo.getOpenId();
        this.finished = 0;
        this.tips = addPlanVo.getTips();
        this.planTime = addPlanVo.getPlanTime();
        this.createTime = System.currentTimeMillis();
        this.updateTime = System.currentTimeMillis();
        this.icon = addPlanVo.getIcon();
        this.remark = addPlanVo.getRemark();
    }

}

