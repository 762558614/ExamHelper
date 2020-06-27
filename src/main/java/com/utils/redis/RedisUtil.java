package com.utils.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.ZParams;

public class RedisUtil extends AbstractRedisUtil {


	/**
	 *连接
	*/
	private static Jedis getConn(){
	    Jedis jedis = getPool().getResource();
	    return jedis;
	}

    public String get(String key){
    	try(Jedis jedis = getConn()){
    		return jedis.get(key);
    	}
    }
    public String set(String key, String val){
        try(Jedis jedis = getPool().getResource()){
            return jedis.set(key, val);
        }
    }

	@Override
	public boolean del(String key) {
		try(Jedis jedis = getPool().getResource()){
            return jedis.del(key)==1;
		}
	}

	@Override
	public boolean rpush(String key, String... val) {
        try(Jedis jedis = getPool().getResource()){
            return jedis.rpush(key, val)==1;
        }
	}

	@Override
	public List<String> lrange(String key, int start, int stop) {
		try(Jedis jedis = getPool().getResource()){
            return jedis.lrange(key, start, stop);
		}
	}

	@Override
	public String lindex(String key, long index) {
		try(Jedis jedis = getPool().getResource()){
            return jedis.lindex(key, index);
		}
	}

	@Override
	public String lpop(String key) {
        try(Jedis jedis = getPool().getResource()){
            return jedis.lpop(key);
        }
	}
	@Override
	public boolean sadd(String key, String... members) {
        try(Jedis jedis = getPool().getResource()){
            return jedis.sadd(key, members)==1;
        }
	}
	@Override
	public Set<String> smembers(String key) {
        try(Jedis jedis = getPool().getResource()){
            return jedis.smembers(key);
        }
	}
	@Override
	public boolean sismember(String key, String val) {
        try(Jedis jedis = getPool().getResource()){
            return jedis.sismember(key, val);
        }
	}
	@Override
	public boolean srem(String key, String... members) {
        try(Jedis jedis = getPool().getResource()){
            return jedis.srem(key, members)==1;
        }
	}
	@Override
	public boolean hset(String key, Map<String, String> hash) {
        try(Jedis jedis = getPool().getResource()){
            return jedis.hset(key, hash)==1;
        }
	}
	@Override
	public String hget(String key, String field) {
        try(Jedis jedis = getPool().getResource()){
            return jedis.hget(key, field);
        }
	}
	@Override
	public Map<String, String> hgetAll(String key) {
        try(Jedis jedis = getPool().getResource()){
            return jedis.hgetAll(key);
        }
	}
	@Override
	public boolean hdel(String key, String... fields) {
        try(Jedis jedis = getPool().getResource()){
            return jedis.hdel(key, fields)==1;
        }
	}
	@Override
	public boolean zadd(String key, double score, String member) {
        try(Jedis jedis = getPool().getResource()){
            return jedis.zadd(key, score, member)==1;
        }
	}
	@Override
	public Set<String> zrange(String key, long start, long stop) {
        try(Jedis jedis = getPool().getResource()){
            return jedis.zrange(key, start, stop);
        }
	}
	@Override
	public Set<String> zrangeByScore(String key, double min, double max) {
        try(Jedis jedis = getPool().getResource()){
            return jedis.zrangeByScore(key, min, max);
        }
	}
	@Override
	public boolean zrem(String key, String... members) {
        try(Jedis jedis = getPool().getResource()){
            return jedis.zrem(key, members)==1;
        }
	}
	@Override
	public boolean zincrby(String key, double increment, String member) {
        try(Jedis jedis = getPool().getResource()){
            return jedis.zincrby(key, increment, member)==1;
        }
	}
	@Override
	public boolean expire(String key, int seconds) {
        try(Jedis jedis = getPool().getResource()){
            return jedis.expire(key, seconds)==1;
        }
	}
	@Override
	public void zinterstore(String dstkey, String... sets) {
        try(Jedis jedis = getPool().getResource()){
            jedis.zinterstore(dstkey, sets);
        }
	}
	@Override
	public void zinterstore(String dstkey, ZParams params, String... sets) {
        try(Jedis jedis = getPool().getResource()){
            jedis.zinterstore(dstkey, params, sets);
        }
	}
	@Override
	public long zcard(String key) {
        try(Jedis jedis = getPool().getResource()){
            return jedis.zcard(key);
        }
	}
	
	@Override
	public long zremrangeByRank(String key, long start, long stop) {
        try(Jedis jedis = getPool().getResource()){
            return jedis.zremrangeByRank(key, start, stop);
        }
	}
}