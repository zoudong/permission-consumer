package com.zoudong.consumer.integration.feign;

import com.zoudong.permission.api.UserService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author zd
 * @description class
 * @date 2018/7/9 10:40
 */
@FeignClient("PERMISSION-SYSTEAM")
public interface UserServiceAPI extends UserService{
}
