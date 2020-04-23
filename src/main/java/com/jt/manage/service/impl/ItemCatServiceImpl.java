package com.jt.manage.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.po.ItemCat;
import com.jt.common.service.RedisService;

import com.jt.manage.mapper.ItemCatMapper;
import com.jt.manage.service.ItemCatService;
import com.jt.manage.vo.EasyUITree;

//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.ShardedJedisPool;

@Service
public class ItemCatServiceImpl implements ItemCatService 
{
	@Autowired
	private ItemCatMapper itemCatMapper;
	
	@Autowired
	//private Jedis jedis;
	//private ShardedJedisPool shardedJedisPool;
	private RedisService redisService; 
	
	private static ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public List<EasyUITree> findItemCatList(Long parentId) 
	{
		ItemCat itemCatTemp = new ItemCat();
		itemCatTemp.setParentId(parentId);
		List<ItemCat> itemCatList = itemCatMapper.select(itemCatTemp);
		
		//准备返回值的数据
		List<EasyUITree> treeList = new ArrayList<>();
		
		for(ItemCat itemCat : itemCatList) 
		{
			EasyUITree easyUITree = new EasyUITree();
			easyUITree.setId(itemCat.getId());		
			easyUITree.setText(itemCat.getName());//商品分类名称
			//如果为父级则closed 如果不是父级open
			String state = itemCat.getIsParent() ? "closed" : "open";
			easyUITree.setState(state);
			treeList.add(easyUITree);
		}
		return treeList;
	}

	@Override
	public List<EasyUITree> findCacheList(Long parentId) {
		
		String key = "ITEM_CAT_"+parentId;
		
		String resultJSON = redisService.get(key);
		List<EasyUITree> treeList = new ArrayList<>();
		
		try 
		{
			if(StringUtils.isEmpty(resultJSON))
			{
				//获取分类对象
				treeList = findItemCatList(parentId);
				//对象转化JSON
				String jsonData = objectMapper.writeValueAsString(treeList);
				//存入redis
				//redisService.set(key, jsonData);
				//redisService.expire(key, 40);
				redisService.set(key, jsonData);
				System.out.println("用户第一次查询，存入redis的数据："+jsonData+"JSON格式");
			}	
			else 
			{
				//treeList = objectMapper.readValue(resultJSON, treeList.getClass());
				//JSON转数组对象
				EasyUITree[] easyUITrees = objectMapper.readValue(resultJSON, EasyUITree[].class);
				//数组转List集合
				treeList = Arrays.asList(easyUITrees);
				System.out.println("用户查询缓存，从redis中查询的数据："+treeList+"对象toString");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			//	
		}
		
		
		return treeList;
	}
}
