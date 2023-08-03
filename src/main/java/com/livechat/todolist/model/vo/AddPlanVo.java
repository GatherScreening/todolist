package com.livechat.todolist.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddPlanVo {
    @JsonProperty("open_id")
    private String openId;
    /**
     * 某个计划
     */
    private String tips;
    /**
     * 计划时间，比如选择了2023-04-15
     */
    @JsonProperty("plan_time")
    private String planTime;
    @Nullable
    private String remark = "";
    @JsonProperty("open_id_list")
    @Nullable
    private String openIdList = "";
    @JsonProperty("group_id")
    @Nullable
    private Long groupId = 0L;

    private Integer icon;

}
