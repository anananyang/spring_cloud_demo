package com.anyang.consumer.controller;

import com.anyang.common.base.Result;
import com.anyang.consumer.remote.provider.ProviderFeignClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RefreshScope
@Controller
@RequestMapping("resources")
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ProviderFeignClient providerFeignClient;
    @Value("${my.name}")
    private String myName;


    @RequestMapping("getMyName")
    @ResponseBody
    public Result<?> getMyName() {
        Map<String, String> map = new HashMap<>();
        map.put("myName", myName);
        return Result.wrapSuccess(map);
    }

    @RequestMapping("getAll")
    @ResponseBody
    public ResponseEntity<?> getResources() {
//        return restTemplate.getForEntity("http://localhost:9000/provider/resources/getAll", Result.class);
        return restTemplate.getForEntity("http://PROVIDER/resources/getAll", Result.class);
    }


    @RequestMapping("getAllByFeign")
    @ResponseBody
    public Result<?> getAllByFeign() {
        return providerFeignClient.getResources();
    }


    @RequestMapping("getAllByFeignAndHystrix")
    @ResponseBody
    public Result<?> getAllByFeignAndHystrix() {
        return providerFeignClient.getAllWithSleep();
    }

    @HystrixCommand(fallbackMethod = "fallback")
    @RequestMapping("getAllByFeignWithHystrix")
    @ResponseBody
    public Result<?> getAllByFeignWithHystrix() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }

        return providerFeignClient.getResources();
    }

    /**
     * ??????????????????????????????
     *
     * @return
     */
    @HystrixCommand(
            fallbackMethod = "fallbackWithException",
            commandProperties = {
                    @HystrixProperty(name = "execution.timeout.enabled", value = "true"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
            }
    )
    @RequestMapping("getAllByFeignWithCustomHystrixTimeout")
    @ResponseBody
    public Result<?> getAllByFeignWithCustomHystrixTimeout() {
        if (5 / 0 == 0) {

        }
        return providerFeignClient.getResources();
    }

    /**
     * ????????????
     *
     * @return
     */
    @HystrixCommand(
            fallbackMethod = "fallbackWithException",
            ignoreExceptions = {Throwable.class}
    )
    @RequestMapping("getAllByFeignWIgnoreException")
    @ResponseBody
    public Result<?> getAllByFeignWIgnoreException() {
        if (5 / 0 == 0) {

        }
        return providerFeignClient.getResources();
    }

    /**
     * ????????????
     *
     * @return
     */
    @HystrixCommand(
            fallbackMethod = "fallback",
            threadPoolKey = "resources",   // ????????????key
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "2"),   // ????????????????????????????????????
                    @HystrixProperty(name = "maxQueueSize", value = "2")  // ?????????????????????????????????2?????????
            }
    )
    @RequestMapping("getAllByFeignWithLimiter")
    @ResponseBody
    public Result<?> getAllByFeignWithLimiter() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }

        return providerFeignClient.getResources();
    }


    /**
     * ???????????????????????????
     *
     * @return
     */
    public Result<?> fallback() {
        return Result.wrapError("", "????????????");
    }

    /**
     * ???????????????????????????
     *
     * @return
     */
    public Result<?> fallbackWithException(Throwable throwable) {
        return Result.wrapError("??????", throwable.getMessage());
    }

}
