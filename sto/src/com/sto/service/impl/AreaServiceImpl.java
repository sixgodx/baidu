package com.sto.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sto.entity.Area;
import com.sto.mapper.AreaMapper;
import com.sto.service.AreaService;

/**
 * 大区数据--业务层
 * @author yanshouqiang
 *
 */
@Service
public class AreaServiceImpl implements AreaService {

	@Autowired
	private AreaMapper areaMapper;
	
	public List<Area> findAreaByCondition(Map map) {

		return areaMapper.findAreaByCondition(map);
	}

}
