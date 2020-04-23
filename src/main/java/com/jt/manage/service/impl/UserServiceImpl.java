package com.jt.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jt.manage.mapper.UserMapper;
import com.jt.manage.pojo.User;
import com.jt.manage.service.UserService;

@Service
public class UserServiceImpl implements UserService 
{
	@Autowired
	private UserMapper userMapper;
	
	@Override
	@Transactional(readOnly=true)
	public List<User> findAll() 
	{
		return userMapper.findAll();
	}

}
