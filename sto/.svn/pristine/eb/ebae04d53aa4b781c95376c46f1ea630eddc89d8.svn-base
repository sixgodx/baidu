package com.sto.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sto.entity.Trans;
import com.sto.mapper.TransMapper;
import com.sto.service.TransService;

/**
 * 转运中心--业务层
 * @author yanshouqiang
 *
 */
@Service
public class TransServiceImpl implements TransService {

	@Autowired
	private TransMapper transMapper;
	
	public List<Trans> findTransByCondition(Map map) {

		return transMapper.findTransByCondition(map);
	}

}
