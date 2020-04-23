package com.jt.mangae.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.jt.manage.pojo.User;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class TestRedis 
{
	//
	@Test
	public void testString() 
	{
		@SuppressWarnings("resource")
		Jedis jedis = new Jedis("172.16.10.96",6379);
		
		jedis.set("dd","dddddd");
		System.out.println("输出："+jedis.get("dd"));
		
		jedis.expire("dd",10);
		
		//设定数据同时设定超时时间
		jedis.setex("vv", 20, "123");
	}
	
	@Test
	public void testHash() 
	{
		User user = new User();
		user.setId(100000);
		user.setName("票");
		user.setAge(30);
		user.setSex("女");
		
		@SuppressWarnings("resource")
		Jedis jedis = new Jedis("172.16.10.96",6379);
		jedis.hset("user1", "id", user.getId()+"");
		jedis.hset("user1", "name", user.getName()+"");
		jedis.hset("user1", "age", user.getAge()+"");
		jedis.hset("user1", "sex", user.getSex()+"");
		Map<String, String> map = jedis.hgetAll("user1");
		System.out.println(map);
	}
	
	//List集合
	@Test
	public void testList() 
	{
		@SuppressWarnings("resource")
		Jedis jedis = new Jedis("172.16.10.26",6379);
		jedis.lpush("list", "1","2");
		System.out.println(jedis.rpop("list"));
		System.out.println(jedis.rpop("list"));
		System.out.println(jedis.rpop("list"));
	}
	
	public void testTx() 
	{
		@SuppressWarnings("resource")
		Jedis jedis = new Jedis("172.16.10.96",6379);
		jedis.multi();
		
	}
	
	@Test
	public void testShard() 
	{
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(1000);
		poolConfig.setMaxIdle(50);
		poolConfig.setMinIdle(10);
		
		List<JedisShardInfo> shards = new ArrayList<>();
		shards.add(new JedisShardInfo("172.16.10.26", 6379));
		shards.add(new JedisShardInfo("172.16.10.26", 6380));
		shards.add(new JedisShardInfo("172.16.10.26", 6381));
		
		@SuppressWarnings("resource")
		ShardedJedisPool pool = new ShardedJedisPool(poolConfig,shards);
		ShardedJedis shardedJedis = pool.getResource();
		
		shardedJedis.set("shards", "redis分片测试");
		System.out.println(shardedJedis.get("shards"));
		
		pool.returnResource(shardedJedis);
	}
	
	//哨兵测试
	@Test
	public void testSentinel() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(1000);
		
		//转换ip和端口的工具类
		HostAndPort port = new HostAndPort("172.16.10.26", 26379);
		System.out.println(port);
		
		Set<String> sentinels = new HashSet<>();
		sentinels.add("172.16.10.26:26379");
		sentinels.add("172.16.10.26:26380");
		sentinels.add("172.16.10.26:26381");
		
		@SuppressWarnings("resource")
		JedisSentinelPool pool = new JedisSentinelPool("mymaster", sentinels, poolConfig);
		
		Jedis jedis = pool.getResource();
		jedis.set("1111", "222222");
		System.out.println(jedis.get("1111"));
		pool.returnResource(jedis);
	}
	
	@Test
	public void testCluster() 
	{
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("172.16.10.71", 7000));
		nodes.add(new HostAndPort("172.16.10.71", 7001));
		nodes.add(new HostAndPort("172.16.10.71", 7002));
		nodes.add(new HostAndPort("172.16.10.71", 7003));
		nodes.add(new HostAndPort("172.16.10.71", 7004));
		nodes.add(new HostAndPort("172.16.10.71", 7005));
		nodes.add(new HostAndPort("172.16.10.71", 7006));
		nodes.add(new HostAndPort("172.16.10.71", 7007));
		nodes.add(new HostAndPort("172.16.10.71", 7008));
		@SuppressWarnings("resource")
		JedisCluster cluster = new JedisCluster(nodes);
		cluster.set("bug", "404");
		System.out.println(cluster.get("bug"));
	}
	
	
	
	
	
	
	
}
