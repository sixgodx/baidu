package com.sto.mapper;

import java.util.List;
import java.util.Map;

import com.sto.entity.AdminDIV;

public interface AdminDIVMapper {

	public AdminDIV findAdminDivById(String id);
	
	public List<AdminDIV> findAll(Map map);
}
