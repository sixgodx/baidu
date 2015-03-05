package com.sto.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sto.entity.Site;
import com.sto.mapper.SiteMapper;
import com.sto.service.SiteService;

/**
 * 网点数据--业务层
 * @author yanshouqiang
 *
 */
@Service
public class SiteServiceImpl implements SiteService {

	@Autowired
	private SiteMapper siteMapper;
	
	public List<Site> findSiteByCondition(Map map) {

		return siteMapper.findSiteByCondition(map);
	}

}
