package com.sto.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.sto.entity.Country;
import com.sto.service.CountryService;
import com.sto.service.impl.CountryServiceImpl;

/**
 * 国家数据
 * @author yanshouqiang
 *
 */
public class CountryAction {

	private static CountryService countryService = new CountryServiceImpl();

	public static List<Country> getCountry(HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();
		String data = request.getParameter("data");
		// 判断客户端传递过来的json数据
		if (data != null) {
			JSONObject obj = JSONObject.parseObject(data);
			// 判断json里的时间戳是否为空
			if (obj.get("incTimestamp") != null) {
				String time = obj.get("incTimestamp").toString();

				// 判断取出的时间是否有填，如果没有填，将null放进map中
				if (time.equals("")) {
					map.put("SYNC_TIMESTAMP", null);
				} else {

					map.put("SYNC_TIMESTAMP", time);
				}

			} else {
				// obj.get("incTimestamp")为null
				map.put("SYNC_TIMESTAMP", null);
			}

		} else {
			map.put("SYNC_TIMESTAMP", request.getParameter("timestamp"));
		}
		List<Country> countries = countryService.findCountryByCondition(map);
	
		return countries;
	}
}
