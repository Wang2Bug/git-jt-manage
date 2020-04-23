package com.jt.manage.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.po.Item;
import com.jt.common.po.ItemDesc;
import com.jt.common.vo.EasyUIResult;
import com.jt.manage.mapper.ItemDescMapper;
import com.jt.manage.mapper.ItemMapper;
import com.jt.manage.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService
{
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;
	
	@Override
	public List<Item> findAll() 
	{
		List<Item> itemList = itemMapper.findAll();
		
		return itemList;
	}
	
	//
	@Override
	public EasyUIResult findItemByPage(Integer page, Integer rows) 
	{
		//获取商品记录总数
		//int total = itemMapper.findItemCount();
		int total = itemMapper.selectCount(null);
		//获取商品列表信息使用分页
		int start = (page-1) * rows;
		List<Item> itemList = itemMapper.findItemByPage(start,rows);
		
		return new EasyUIResult(total,itemList);
	}

	@Override
	public String findItemCatNameById(Long itemId) 
	{
		
		return itemMapper.findItemCatNameById(itemId);
	}
	//线程一旦获取主键后，其他线程不能获取相同主键
	@Override
	public void saveItem(Item item,String desc) 
	{
		item.setStatus(1); //1.上架状态 2.下架状态
		item.setCreated(new Date());
		item.setUpdated(item.getCreated());
		itemMapper.insert(item);
		
		//因为不是主键，不能保证数据是同一个 Item itemDB = itemMapper.select(item).get(0);
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());//获取主键
		//必须和item中的Id一致
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(item.getCreated());
		itemDesc.setUpdated(item.getCreated());
		itemDescMapper.insert(itemDesc);
		
	}

	@Override
	public void updateItem(Item item,String desc) 
	{
		item.setUpdated(new Date());
		itemMapper.updateByPrimaryKeySelective(item);
		
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDesc.setUpdated(item.getUpdated());
		itemDescMapper.updateByPrimaryKeySelective(itemDesc);
	}

	@Override
	public void updateState(Long[] ids, int status) 
	{
		//效率太低
//		for (Long itemId : ids) 
//		{
//			Item item = new Item();
//			item.setId(itemId);
//			item.setStatus(state);
//			itemMapper.updateByPrimaryKeySelective(item);
//		}
		
		itemMapper.updateStatus(ids,status);
		
	}

	@Override
	public void deleteItem(Long[] ids) 
	{
		//itemMapper.deleteByIDS(ids);
		itemMapper.deleteItem(ids);
	}

	@Override
	public ItemDesc findItemDescById(Long itemId) 
	{
		return itemDescMapper.selectByPrimaryKey(itemId);
	}

	@Override
	public Item findItemById(long itemId) 
	{
		return itemMapper.selectByPrimaryKey(itemId);
	}

}
