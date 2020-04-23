package com.jt.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.manage.service.ItemCatService;
import com.jt.manage.vo.EasyUITree;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController 
{
	@Autowired
	private ItemCatService itemCatService;
	
	//根据商品的分类信息
	@RequestMapping("/list")
	@ResponseBody//数据转化为JSON
	public List<EasyUITree> findItemCatByParentId(@RequestParam(value="id",defaultValue="0") Long ParentId)
	{
		//1.查询一级商品分类的信息
		//Long ParentId = id;
		
		return itemCatService.findCacheList(ParentId);
		
	}
	
	
	
}
