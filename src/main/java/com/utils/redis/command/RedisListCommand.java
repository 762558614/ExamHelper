package com.utils.redis.command;

import java.util.List;

public interface RedisListCommand {

    /**
     * 	向数组中添加
     * @param key
     * @param val
     * @return
     */
    boolean rpush(String key, String... val);
    /**
     * 	返回区间
     * @param key
     * @param start
     * @param stop
     * @return
     */
    List<String> lrange(String key, int start, int stop);
    /**
     * 	返回下标数据
     * @param key
     * @param index
     * @return
     */
    String lindex(String key, long index);
    /**
     * 	从头部弹出一个
     * @param key
     * @return
     */
    String lpop(String key);
}
