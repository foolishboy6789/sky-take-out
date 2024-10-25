package com.sky.service;

public interface ShopService {
    void startOrStop(String key, Integer status);

    Integer getStatus(String statusKey);
}
