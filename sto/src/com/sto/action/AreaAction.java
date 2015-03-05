package com.sto.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.sto.entity.Area;
import com.sto.service.AreaService;
import com.sto.utils.CommonMethod;
import com.sto.utils.CommonUtils;

/**
 * 大区数据
 * @author yanshouqiang
 *
 */
@Controller
@RequestMapping("/basicdata")
public class AreaAction {

	@Autowired
	private AreaService areaService;

	@RequestMapping("/getArea")
	public void getArea(HttpServletRequest request, HttpServletResponse response) {

		try {
			CommonUtils commonUtil = new CommonUtils();
			String method = request.getParameter("method");
			System.out.println(method);
			if (method.contains(" ")) {
				commonUtil.setCode(3);
				commonUtil.setMessage("参数错误");
			} else if (method == null || method.equals("")) {
				commonUtil.setCode(4);
				commonUtil.setMessage("方法名为必填项");
			} else if (!CommonMethod.GETAREA.equals(method)) {
				commonUtil.setCode(2);
				commonUtil.setMessage("方法错误");
			} else {

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

					//判断lev，同时间戳一样
					if(obj.get("lev") != null){
						
						String lev = obj.get("lev").toString();
						if(lev.equals("")){
							map.put("AREA_LEV", null);
						}else{
							
							map.put("AREA_LEV", lev);
						}
					}else{
						
						//lev为空
						map.put("AREA_LEV", null);
					}

				} else {
					map.put("SYNC_TIMESTAMP", request.getParameter("timestamp"));
				}
				List<Area> areas = areaService.findAreaByCondition(map);
				commonUtil.setData(areas);
				commonUtil.setCode(0);
			}
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().write(JSONObject.toJSONString(commonUtil));
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
