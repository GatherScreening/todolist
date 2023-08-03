package com.livechat.todolist.model.vo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/***
 @ClassName: AddTeamPlan
 @Author: zry
 @Date: 2023/4/17 20:04
 ***/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddTeamPlan {
    /**
     * 所属团队id
     */
    @JsonProperty("group_id")
    private Long groupId;
    /**
     * 某个计划
     */
    private String tips;
    /**
     * 指定某人完成
     */
    @JsonProperty("open_id_list")
    private List<String> openIdList;
    /**
     * 计划时间，比如选择了2023-04-15
     */
    @JsonProperty("plan_time")
    private String planTime;

}
