package com.jt.mangae.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.manage.pojo.User;

public class TestObjectMapper {
	
	@Test
	public void toJSON() throws IOException {
		User user = new User();
		user.setId(100);
		user.setName("turetoo");
		user.setAge(10);
		user.setSex("男");
		
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writeValueAsString(user);
		System.out.println(result);	
		
		//将JOSN转化成对象
		User objUser = mapper.readValue(result, User.class);
		System.out.println(objUser);
		
		
	}

	@Test
	public void toListJSON() throws IOException {
		User user = new User();
		user.setId(100);
		user.setName("turetoo");
		user.setAge(10);
		user.setSex("男");
		
		User user1 = new User();
		user1.setId(200);
		user1.setName("win");
		user1.setAge(20);
		user1.setSex("nv");
		
		List<User> userList = new ArrayList<User>();
		userList.add(user);
		userList.add(user1);
		
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writeValueAsString(userList);
		System.out.println(result);		
		
		//List<User> uList = mapper.readValue(result, userList.getClass());
		//System.out.println(uList);
		
		User[] users = mapper.readValue(result, User[].class);
		List<User> arrayList = Arrays.asList(users);
		System.out.println(arrayList);
		
	}
}
