package com.utils.redis.command;

import java.util.Set;

import redis.clients.jedis.ZParams;

public interface RedisZSetCommand {

	boolean zadd(String key, double score, String member);

	Set<String> zrange(String key, long start, long stop);

	Set<String> zrangeByScore(String key, double min, double max);

	boolean zrem(String key, String... members);

	/**
	 * 对应键分数增加
	 * 
	 * @param key
	 * @param increment
	 * @param member
	 * @return
	 */
	boolean zincrby(String key, double increment, String member);

	/**
	 * 将多个zset合成一个zset
	 * 
	 * @param dstkey
	 * @param sets
	 */
	void zinterstore(String dstkey, String... sets);
	/**
	 * 将多个zset合成一个zset 通过params处理分值
	 * @param dstkey
	 * @param params
	 * @param sets
	 */
	void zinterstore(String dstkey, ZParams params, String... sets);

	/**
	 * 键中数据数量
	 * 
	 * @param key
	 * @return
	 */
	long zcard(String key);

	/**
	 * 删除排名区间数据
	 * 
	 * @param key
	 * @param start
	 * @param stop 正数时为移除start到stop区间数据 负数时为剩下stop个数据
	 * @return
	 */
	long zremrangeByRank(String key, long start, long stop);
}
