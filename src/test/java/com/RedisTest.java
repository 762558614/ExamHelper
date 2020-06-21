package com;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import com.utils.redis.AbstractRedisUtil;
import com.utils.redis.RedisUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ZParams;

public class RedisTest {
	
	@Test
	@Ignore
	public void setAndGetInfo() {
		AbstractRedisUtil.getInstance().set("foo", "bar");
		assertEquals(AbstractRedisUtil.getInstance().get("foo"), "bar");
	}
	
	@Test
	@Ignore
	public void sadd() {
		System.out.println(AbstractRedisUtil.getInstance().sadd("fooset1", "bar"));
		System.out.println(AbstractRedisUtil.getInstance().sadd("fooset1", "bar"));
		assertEquals(AbstractRedisUtil.getInstance().get("fooset"), "bar");
	}

	@Test
	@Ignore
	public void zinterstore() {
		System.out.println(AbstractRedisUtil.getInstance().zadd("test", 2, "1"));
		System.out.println(AbstractRedisUtil.getInstance().zadd("test", 4, "2"));
		ZParams params = new ZParams();
		params.weights(2);
		AbstractRedisUtil redisUtil = AbstractRedisUtil.getInstance();
		redisUtil.zinterstore("test", params, "test");
	}
	
	public static void main(String[] args) {
	}
	
}
