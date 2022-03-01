package com.javashitang.lock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

public class LockUtil {

    private static final String OK = "OK";
    private static final Long LONG_ONE = 1L;
    private static final String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

    public static boolean lock(String lockKey, String requestId, long expire) {
        Jedis jedis = RedisPool.getJedis();
        SetParams setParams = new SetParams();
        setParams.nx().px(expire);
        return OK.equals(jedis.set(lockKey, requestId, setParams));
    }

    public static boolean unlock(String lockKey, String requestId) {
        Jedis jedis = RedisPool.getJedis();
        return LONG_ONE.equals(jedis.eval(script, 1, lockKey, requestId));
    }
}
