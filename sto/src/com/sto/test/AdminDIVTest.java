package com.sto.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sto.entity.AdminDIV;
import com.sto.mapper.AdminDIVMapper;


public class AdminDIVTest {

ApplicationContext applicationContext;
	
	@Before
	public void setUp() throws Exception {
		//得到spring的容器
		applicationContext = new ClassPathXmlApplicationContext(new String[]{
				"spring/applicationContext-dao.xml",
				"spring/applicationContext-service.xml",
				"spring/applicationContext.xml"
		});
		
	}
	
	@Test
	public void testAdminDIV(){
		
		AdminDIVMapper adminDIVMapper = (AdminDIVMapper) applicationContext.getBean("adminDIVMapper");
		
		AdminDIV adminDIV = adminDIVMapper.findAdminDivById("1");
		
		System.out.println(adminDIV);
	}
}
