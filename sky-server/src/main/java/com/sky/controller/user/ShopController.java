package com.sky.controller.user;


import com.sky.result.Result;
import com.sky.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("userShopController")
@RequestMapping("/user/shop")
@Slf4j
public class ShopController {

    private static final String SHOP_KEY = "shop_status";
    @Autowired
    private ShopService shopService;

    @RequestMapping("/status")
    public Result<Integer> getStatus() {
        Integer status = shopService.getStatus(SHOP_KEY);
        log.info("获取到店铺状态为：{}", status);
        return Result.success(status);
    }

}
