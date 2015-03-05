package com.sto.service;

import java.util.List;
import java.util.Map;

import com.sto.entity.Site;

public interface SiteService {

	/**
	 * 根据输入的参数查询网点数据
	 * @param map
	 * @return
	 */
	public List<Site> findSiteByCondition(Map map);
}
