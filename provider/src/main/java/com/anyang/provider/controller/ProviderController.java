package com.anyang.provider.controller;

import com.anyang.common.base.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("resources")
public class ProviderController {

    @Value("${server.port}")
    private String port;

    @RequestMapping("getAll")
    @ResponseBody
    public Result<?> getResources() {
        System.out.println("getResources: providr[" + port + "]被调用");
        Map<String, Object> map = new HashMap<>();
        map.put("groupId", "com.anyang");
        map.put("artifactId", "my_springcloud_demo");
        map.put("version", "1.0-SNAPSHOT");
        return Result.wrapSuccess(map);
    }


    @RequestMapping("getAllWithSleep")
    @ResponseBody
    public Result<?> getAllWithSleep() {
        System.out.println("getAllWithSleep: providr[" + port + "]被调用");
        Map<String, Object> map = new HashMap<>();
        map.put("groupId", "com.anyang");
        map.put("artifactId", "my_springcloud_demo");
        map.put("version", "1.0-SNAPSHOT");

        try {
           Thread.sleep(7000);
        } catch (Exception e) {

        }

        return Result.wrapSuccess(map);
    }



}


