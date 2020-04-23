package com.jt.mangae.test;

import java.util.Calendar;

import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//import com.jt.manage.factory.NewInstanceFactory;
//import com.jt.manage.factory.SpringFactory;

public class TestFactory 
{
	//@Autowired
	//private NewInstanceFactory newInstanceFactory;
	@Test
	public void testStatic()
	{
		//spring容器对象
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring/factory.xml");		
		Calendar calendar1 = (Calendar) context.getBean("calendar1");		
		System.out.println("获取时间1："+calendar1.getTime());
		Calendar calendar2 = (Calendar) context.getBean("calendar2");
		System.out.println("获取时间2："+calendar2.getTime());
		Calendar calendar3 = (Calendar) context.getBean("calendar3");
		System.out.println("获取时间3："+calendar3.getTime());
		
		//System.out.println("获取时间4："+newInstanceFactory.getInstance().getTime());
	}
}
