package com.anyang.consumer.remote.provider;

import com.anyang.common.base.Result;
import org.springframework.stereotype.Component;

@Component
public class ProviderFeignClientImpl implements ProviderFeignClient{

    @Override
    public Result<?> getResources() {
        return Result.wrapError("", "ProviderFeignClient.getResources() 降级了");
    }

    @Override
    public Result<?> getAllWithSleep() {
        return Result.wrapError("", "ProviderFeignClient.getAllWithSleep() 降级了");
    }
}
