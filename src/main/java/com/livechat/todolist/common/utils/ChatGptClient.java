package com.livechat.todolist.common.utils;

import org.springframework.stereotype.Component;


import com.livechat.todolist.model.vo.request.QueryGptVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONArray;
import org.json.JSONObject;


@Service
@Slf4j
public class ChatGptClient {
    @Autowired
    RestTemplate restTemplate;
    @Value("${url.chatgpt.apiKey}")
    private String apiKey;

    public String listGameSiteHotQuery(String url, QueryGptVo request) {
        HttpHeaders jsonHeaders = new HttpHeaders();
        String gptTips = "";
        jsonHeaders.setContentType(MediaType.APPLICATION_JSON);
        jsonHeaders.add("Authorization", "Bearer "+ apiKey);
        try {
            HttpEntity<QueryGptVo> httpEntity = new HttpEntity<>(request, jsonHeaders);
            ResponseEntity<String> rsp = restTemplate.exchange(url, HttpMethod.POST,
                    httpEntity, String.class);
            log.info("hotQuery rsp {}", rsp);
            if (rsp.getStatusCode().is2xxSuccessful()) {
                String response = rsp.getBody();
                JSONObject jsonObject = new JSONObject(response);
                if (jsonObject.has("choices")){
                    JSONArray jsonArray = jsonObject.getJSONArray("choices");
                    JSONObject json = jsonArray.getJSONObject(0);
                    if (json.has("message")){
                        JSONObject message = json.getJSONObject("message");
                        if (message.has("content")){
                            gptTips = message.getString("content");
                        }
                    }
                }
            } else {
                log.error("hot query request time out or error");
            }
        } catch (Exception e) {
            log.error("request ");
            return "";
        }
        return gptTips;
    }
}
