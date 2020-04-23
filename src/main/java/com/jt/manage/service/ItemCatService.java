package com.jt.manage.service;

import java.util.List;

import com.jt.manage.vo.EasyUITree;

public interface ItemCatService {

	List<EasyUITree> findItemCatList(Long parentId);

	//查询缓存
	List<EasyUITree> findCacheList(Long parentId);

}
