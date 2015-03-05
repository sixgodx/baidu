package com.sto.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sto.entity.Department;
import com.sto.mapper.DepartmentMapper;
import com.sto.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentMapper departmentMapper;
	
	public List<Department> findDepartmentByCondition(Map map) {

		return departmentMapper.findDepartmentByCondition(map);
	}

}
