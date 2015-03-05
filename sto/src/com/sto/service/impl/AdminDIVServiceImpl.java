package com.sto.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sto.entity.AdminDIV;
import com.sto.mapper.AdminDIVMapper;
import com.sto.service.AdminDIVService;

/**
 * 行政区划--业务层
 * @author Administrator
 *
 */
@Service
public class AdminDIVServiceImpl implements AdminDIVService {

	@Autowired
	private AdminDIVMapper adminDIVMapper;

	public AdminDIV findAdminDivById(String id) {

		return adminDIVMapper.findAdminDivById(id);
	}

	public List<AdminDIV> findAll(Map map) {

		List<AdminDIV> list = adminDIVMapper.findAll(map);
		
		return list;
	}

}
