package com.utils.redis;

import com.utils.redis.command.RedisCommand;
import com.utils.redis.command.RedisListCommand;
import com.utils.redis.command.RedisMapCommand;
import com.utils.redis.command.RedisSetCommand;
import com.utils.redis.command.RedisStringCommand;
import com.utils.redis.command.RedisZSetCommand;

public abstract class AbstractRedisUtil implements RedisStringCommand, RedisListCommand, RedisMapCommand, RedisSetCommand, RedisZSetCommand, RedisCommand {
	static AbstractRedisUtil INSTANCE;
	public static AbstractRedisUtil getInstance() {
		if(INSTANCE==null) {
			synchronized (AbstractRedisUtil.class) {
				if(INSTANCE == null) {
					INSTANCE = createInstance();
				}
			}
		}
		return INSTANCE;
	}
	
	protected static AbstractRedisUtil createInstance() {
		return new RedisUtil();
	}
    
}
