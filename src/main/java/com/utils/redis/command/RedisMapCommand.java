package com.utils.redis.command;

import java.util.Map;

public interface RedisMapCommand {

	/**
	 * 向键中添加hash
	 * 
	 * @param key
	 * @param hash
	 * @return
	 */
	boolean hset(String key, Map<String, String> hash);

	/**
	 * 获取键中对于field值
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	String hget(String key, String field);

	/**
	 * 获取键中对应hash
	 * @param key
	 * @return
	 */
	Map<String, String> hgetAll(String key);

	/**
	 * 删除键中field
	 * @param key
	 * @param fields
	 * @return
	 */
	boolean hdel(String key, String... fields);
}
