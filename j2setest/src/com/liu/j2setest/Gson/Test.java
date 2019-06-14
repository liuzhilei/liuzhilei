package com.liu.j2setest.Gson;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: liuzhilei
 * @Date: 2019/6/13
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        String s = "{departmentId=1, deptAttr={departmentId=1681, jobFnId=6, jobFnName=外卖, jobFnTypeId=9, jobFnTypeName=外卖, jobFnTypeValueId=66, jobFnTypeValueName=外卖, jobFnValueId=36, jobFnValueName=外卖}, skillAttr=[{jobFnId=6, jobFnName=外卖, jobFnTypeId=9, jobFnTypeName=外卖, jobFnTypeValueId=66, jobFnTypeValueName=外卖, jobFnValueId=36, jobFnValueName=外卖, skillId=[105, 106, 145, 147]}], statisticsBusinessId=317, statisticsBusinessName=外卖, subBu={317=外卖}}";

        Gson gson = new GsonBuilder().registerTypeAdapter(new TypeToken<Map<String, Object>>() {
        }.getType(), new GsonTypeAdapter()).create();

        Map<String, String> map = new HashMap<>();
        map = gson.fromJson(s, new TypeToken<Map<String, Object>>(){}.getType());

        String string1 = JSON.toJSONString(map);
        System.out.println(string1);
    }
}
