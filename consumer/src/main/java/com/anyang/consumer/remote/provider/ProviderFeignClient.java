package com.anyang.consumer.remote.provider;

import com.anyang.common.base.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// PROVIDER 是服务提供者注册到 eureka 的服务名称
@FeignClient(
        name = "PROVIDER",
//        fallback = ProviderFeignClientImpl.class    // 第一种方式
        fallbackFactory = ProviderFeignClientFallbackFactory.class
)
public interface ProviderFeignClient {

    @RequestMapping("/resources/getAll")
    Result<?> getResources();

    @RequestMapping("/resources/getAllWithSleep")
    Result<?> getAllWithSleep();
}


