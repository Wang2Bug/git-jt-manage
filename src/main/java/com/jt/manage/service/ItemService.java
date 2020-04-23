package com.jt.manage.service;

import java.util.List;

import com.jt.common.po.Item;
import com.jt.common.po.ItemDesc;
import com.jt.common.vo.EasyUIResult;

public interface ItemService 
{
	public List<Item> findAll();

	public EasyUIResult findItemByPage(Integer page, Integer rows);

	public String findItemCatNameById(Long itemId);

	public void saveItem(Item item, String desc);

	public void updateItem(Item item, String desc);

	public void updateState(Long[] ids, int status);

	public void deleteItem(Long[] ids);

	public ItemDesc findItemDescById(Long itemId);

	public Item findItemById(long itemId);
}
