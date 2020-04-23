package com.jt.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.jt.common.mapper.SysMapper;
import com.jt.common.po.Item;

public interface ItemMapper extends SysMapper<Item>
{
	public List<Item> findAll();
	
	//注解形式查询数据库
	@Select("select count(*) from tb_item")
	public int findItemCount();

	public List<Item> findItemByPage(@Param("start") int start, @Param("rows") Integer rows);
	
	@Select("select name from tb_item_cat where id = #{itemId}")
	public String findItemCatNameById(Long itemId);
	
	
	public void updateStatus(@Param("ids")Long[] ids,@Param("status")int status);

	public void deleteItem(@Param("ids")Long[] ids);
}
