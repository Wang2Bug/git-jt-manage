package com.jt.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.po.Item;
import com.jt.common.po.ItemDesc;
import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.manage.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController 
{
	//查询
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/query")
	@ResponseBody
	public EasyUIResult findItemByPage(Integer page,Integer rows) 
	{
		return itemService.findItemByPage(page,rows);
	}
	//分类查询
	@RequestMapping(value="/cat/queryItemName",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findItemCatNameById(Long itemId) 		
	{
		return itemService.findItemCatNameById(itemId);
	}
	
	//根据商品id查询商品详情
	@RequestMapping("/query/item/desc/{itemId}")
	@ResponseBody
	public SysResult findItemDescById(@PathVariable Long itemId) 
	{
		try 
		{
			ItemDesc itemDesc = itemService.findItemDescById(itemId);
			return SysResult.oK(itemDesc);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return SysResult.build(201, "查询失败");
	}
	
	//新增商品
	@RequestMapping("/save")
	@ResponseBody
	public SysResult saveItem(Item item,String desc) 
	{
		try 
		{
			itemService.saveItem(item,desc);
			return SysResult.oK();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return SysResult.build(201, "新增商品失败");
	}
	
	//编辑商品信息
	@RequestMapping("/update")
	@ResponseBody
	public SysResult updateItem(Item item,String desc) 
	{
		try 
		{
			itemService.updateItem(item,desc);
			return SysResult.oK();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return SysResult.build(201, "修改失败");
	}
	
	//商品下架
	@RequestMapping("/instock")
	@ResponseBody
	public SysResult instock(Long[] ids) 
	{
		try 
		{
			int status = 2;
			itemService.updateState(ids,status);
			return SysResult.oK();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return SysResult.build(201, "用户下架失败");
	}
	
	//商品上架
	@RequestMapping("/reshelf")
	@ResponseBody
	public SysResult reshelf(Long[] ids) 
	{
		try 
		{
			int status = 1;
			itemService.updateState(ids, status);
			return SysResult.oK();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return SysResult.build(201, "用户上架失败");
	}
	
	//删除商品
	@RequestMapping("/delete")
	@ResponseBody
	public SysResult deleteItem(Long[] ids) 
	{
		try 
		{
			itemService.deleteItem(ids);
			return SysResult.oK();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return SysResult.build(201,"商品删除失败");
	}
}
		