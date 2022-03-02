package com.javashitang.lock;

import java.util.Random;

public class TestService {

    public String getData(String key) throws InterruptedException {
        String lockKey = "key";
        String lockValue = String.valueOf(System.currentTimeMillis());
        long expireTime = 1000L;
        String value = getFromRedis(key);
        if (value == null) {
            if (LockUtil.lock(lockKey, lockValue, expireTime)) {
                // 从数据库取值并放到redis中
                LockUtil.unlock(lockKey, lockValue);
            } else {
                // sleep一段时间再从缓存中拿
                Thread.sleep(100);
                getFromRedis(key);
            }
        }
        return value;
    }

    public String getFromRedis(String key) {
        return "";
    }
}
