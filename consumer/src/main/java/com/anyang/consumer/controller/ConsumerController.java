package com.anyang.consumer.controller;

import com.anyang.common.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("resources")
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("getAll")
    @ResponseBody
    public ResponseEntity<?> getResources() {
//        return restTemplate.getForEntity("http://localhost:9000/provider/resources/getAll", Result.class);
        return restTemplate.getForEntity("http://PROVIDER/provider/resources/getAll", Result.class);
    }

}
