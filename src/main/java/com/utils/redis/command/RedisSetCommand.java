package com.utils.redis.command;

import java.util.Set;

public interface RedisSetCommand {
	/**
     * 	添加
     * @param key
     * @param members
     * @return
     */
    boolean sadd(String key, String... members);
    /**
     * 	全部元素
     * @param key
     * @return
     */
    Set<String> smembers(String key);
    /**
     * 	指定值是否在集合中
     * @param key
     * @param val
     * @return
     */
    boolean sismember(String key, String val);
    /**
     * 	从集合中删除
     * @param key
     * @param members
     * @return
     */
    boolean srem(String key, String... members);
}
