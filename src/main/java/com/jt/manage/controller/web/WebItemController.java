package com.jt.manage.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.po.Item;
import com.jt.common.po.ItemDesc;
import com.jt.manage.service.ItemService;

@Controller
@RequestMapping("/web/item")
public class WebItemController 
{
	@Autowired
	private ItemService itemService;
	
	//"http://manage.jt.com/web/item/findItemById?itemId=xxx"
	@RequestMapping("/findItemById")
	@ResponseBody
	public Item findItemById(long itemId) 
	{
		return itemService.findItemById(itemId);
	}
	
	//"http://manage.jt.com/web/item/findItemDescById/itemId"
	@RequestMapping("/findItemDescById/{itemId}")
	@ResponseBody
	public ItemDesc findItemDescById(@PathVariable long itemId) 
	{
		return itemService.findItemDescById(itemId);
	}
}
