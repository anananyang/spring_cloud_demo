package com.anyang.consumer.remote.provider;

import com.anyang.common.base.Result;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class ProviderFeignClientFallbackFactory implements FallbackFactory<ProviderFeignClient>{

    @Override
    public ProviderFeignClient create(Throwable throwable) {
        return new ProviderFeignClient() {
            @Override
            public Result<?> getResources() {
                return Result.wrapError("", "ProviderFeignClient.getResources() 降级了: " + throwable.getMessage());
            }

            @Override
            public Result<?> getAllWithSleep() {
                return Result.wrapError("", "ProviderFeignClient.getAllWithSleep() 降级了: " + throwable.getMessage());
            }
        };
    }
}
