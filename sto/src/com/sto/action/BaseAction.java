package com.sto.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

public class BaseAction {

	public Map judgeData(HttpServletRequest request) {

		String data = request.getParameter("data");
		Map<String, Object> map = new HashMap<String, Object>();

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

			// 判断lev，同时间戳一样
			if (obj.get("lev") != null) {

				String lev = obj.get("lev").toString();
				if (lev.equals("")) {
					map.put("LEV", null);
				} else {

					map.put("LEV", lev);
				}
			} else {

				// lev为空
				map.put("LEV", null);
			}

		} else {
			
			//data为null,将SYNC_TIMESTAMP和LEV全部设置为null.
//			map.put("SYNC_TIMESTAMP", request.getParameter("timestamp"));
//			map.put("LEV", request.getParameter("lev"));
			
			map.put("SYNC_TIMESTAMP", null);
			map.put("LEV", null);
		}

		return map;
	}
}
