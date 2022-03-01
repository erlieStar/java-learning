package com.javashitang.lock;

import org.junit.Test;

import java.util.UUID;

/**
 * @author lilimin
 * @since 2022-03-01
 */
public class RedisLockTest {

    @Test
    public void test1() {

    }

    public void workV1() {
        String lockKey = "testKey";
        String requestId = UUID.randomUUID().toString();
        if (LockUtil.lock(lockKey, requestId, 2000)) {
            try {

                // 执行业务逻辑

                LockUtil.unlock(lockKey, requestId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void workV2() {
        String lockKey = "testKey";
        String requestId = UUID.randomUUID().toString();
        try {
            if (LockUtil.lock(lockKey, requestId, 2000)) {

                // 执行业务逻辑

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            LockUtil.unlock(lockKey, requestId);
        }
    }
}
