package com.utils.redis.command;

public interface RedisCommand {

	/**
	 * 	设置过期
	 * @param key
	 * @param seconds
	 * @return
	 */
	boolean expire(String key, int seconds);
}
