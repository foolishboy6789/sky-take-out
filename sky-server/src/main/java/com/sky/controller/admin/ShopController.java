package com.sky.controller.admin;


import com.sky.result.Result;
import com.sky.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("adminShopController")
@Slf4j
@RequestMapping("/admin/shop")
public class ShopController {

    private final String Status_Key = "shop_status";
    @Autowired
    private ShopService shopService;

    @PutMapping("/{status}")
    public Result<String> startOrStop(@PathVariable Integer status) {
        log.info("更改店铺状态:{}", status);
        shopService.startOrStop(Status_Key, status);
        return Result.success();
    }

    @GetMapping("/status")
    public Result<Integer> getStatus() {
        log.info("查询店铺状态");
        Integer status = shopService.getStatus(Status_Key);
        return Result.success(status);
    }

}
