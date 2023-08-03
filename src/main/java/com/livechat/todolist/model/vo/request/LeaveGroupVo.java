package com.livechat.todolist.model.vo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveGroupVo {
    @JsonProperty("open_id")
    private String openId;
    @JsonProperty("group_id")
    private Long groupId;
}
