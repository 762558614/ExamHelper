package com.utils.redis;

import com.utils.redis.command.RedisCommand;
import com.utils.redis.command.RedisListCommand;
import com.utils.redis.command.RedisMapCommand;
import com.utils.redis.command.RedisSetCommand;
import com.utils.redis.command.RedisStringCommand;
import com.utils.redis.command.RedisZSetCommand;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public abstract class AbstractRedisUtil implements RedisStringCommand, RedisListCommand, RedisMapCommand, RedisSetCommand, RedisZSetCommand, RedisCommand {
	static AbstractRedisUtil INSTANCE;
		
	private static JedisPool pool;

	/**
	 *连接池
	*/
	protected static JedisPool getPool(){
	    if(pool==null){
	        synchronized (AbstractRedisUtil.class){
	            if(pool==null){
	                JedisPoolConfig config = new JedisPoolConfig();
	                config.setMaxIdle(8);
	                config.setMaxTotal(18);
	                pool = new JedisPool(config, "39.100.200.64", 6379, 2000, "123456");
	            }
	        }
	    }
	    return pool;
	}
	
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
    
	@Override
	public void submit() {	}
	
}
