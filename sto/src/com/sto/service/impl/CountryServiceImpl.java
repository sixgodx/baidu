package com.sto.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sto.entity.Country;
import com.sto.mapper.CountryMapper;
import com.sto.service.CountryService;

/**
 * 国家数据--业务层
 * @author yanshouqiang
 *
 */
@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryMapper countryMapper;
	
	public List<Country> findCountryByCondition(Map map) {

		return countryMapper.findCountryByCondition(map);
	}

}
