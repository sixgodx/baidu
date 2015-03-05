package com.sto.service;

import java.util.List;
import java.util.Map;

import com.sto.entity.Department;

public interface DepartmentService {

	public List<Department> findDepartmentByCondition(Map map);
}
