package com.utils.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Pipeline;
import redis.clients.jedis.ZParams;

public class RedisPipelineConnector extends AbstractRedisUtil {

	ThreadLocal<Pipeline> pipelineThreadLoacl = new ThreadLocal<Pipeline>();
	
	private Pipeline getPipeline() {
		if(pipelineThreadLoacl.get()==null) {
			pipelineThreadLoacl.set(getPool().getResource().pipelined());
		}
		return pipelineThreadLoacl.get();
	}
	
	@Override
	public void submit() {
		Pipeline pipeline = pipelineThreadLoacl.get();
		if(pipeline!=null) {
			pipeline.exec();
			pipelineThreadLoacl.remove();
		}
	}
	
	@Override
	public String set(String key, String val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String get(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean del(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean rpush(String key, String... val) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> lrange(String key, int start, int stop) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String lindex(String key, long index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String lpop(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hset(String key, Map<String, String> hash) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String hget(String key, String field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> hgetAll(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hdel(String key, String... fields) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sadd(String key, String... members) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<String> smembers(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean sismember(String key, String val) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean srem(String key, String... members) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zadd(String key, double score, String member) {
		Pipeline pipeline = getPipeline();
		pipeline.zadd(key, score, member);
		return false;
	}

	@Override
	public Set<String> zrange(String key, long start, long stop) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> zrangeByScore(String key, double min, double max) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean zrem(String key, String... members) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zincrby(String key, double increment, String member) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void zinterstore(String dstkey, String... sets) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void zinterstore(String dstkey, ZParams params, String... sets) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long zcard(String key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long zremrangeByRank(String key, long start, long stop) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean expire(String key, int seconds) {
		// TODO Auto-generated method stub
		return false;
	}

}
