package com.livechat.todolist.common.utils;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/***
 @ClassName: StringToList
 @Author: zry
 @Date: 2023/4/17 18:45
 ***/

public class StringToList {
    private static final String SEEDS = "ABCDEFGHJKMNPQRSTUVWXYZ23456789abcdefghijklmnopqrstuvwxyz"; // 可用字符集合
    private static final int LENGTH = 12; // 邀请码长度

    public static String generateCode() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < LENGTH; i++) {
            int index = random.nextInt(SEEDS.length()); // 随机生成一个字符的下标
            char c = SEEDS.charAt(index); // 取出这个字符
            sb.append(c); // 拼接到邀请码中
        }
        return sb.toString();
    }
    public static List<String> getList(String openIds){
        List<String> list = new ArrayList<>();
        if (StringUtils.isEmpty(openIds)){
            return list;
        }
        String []openIdArr = openIds.split(",");
        list.addAll(Arrays.asList(openIdArr));
        return list;
    }
    public static String listToString(List<String> openList){
        return String.join(",", openList);
    }
}
