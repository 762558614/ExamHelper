package com.utils.redis.command;

public interface RedisStringCommand {
	/**
	 * 	设置值
	 * @param key
	 * @param val
	 * @return
	 */
    String set(String key, String val);
    /**
     * 	获取值
     * @param key
     * @return
     */
    String get(String key);
    /**
     * 	删除值
     * @param key
     * @return
     */
    boolean del(String key);
}
