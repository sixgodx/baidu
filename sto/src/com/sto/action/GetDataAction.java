package com.sto.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.sto.entity.AdminDIV;
import com.sto.entity.Area;
import com.sto.entity.Country;
import com.sto.entity.Site;
import com.sto.entity.Trans;
import com.sto.service.AdminDIVService;
import com.sto.service.AreaService;
import com.sto.service.CountryService;
import com.sto.service.SiteService;
import com.sto.service.TransService;
import com.sto.utils.CommonMethod;
import com.sto.utils.CommonUtils;
import com.sto.utils.MD5Utils;

@Controller
@RequestMapping("/")
public class GetDataAction extends BaseAction{

	@Autowired
	private CountryService countryService;
	@Autowired
	private AdminDIVService adminDIVService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private SiteService siteService;
	@Autowired
	private TransService transService;
	
	@RequestMapping("/get")
	public void getCountry(HttpServletRequest request,
			HttpServletResponse response) {

		CommonUtils commonUtil = new CommonUtils();
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			String method = request.getParameter("method");
			String appKey = request.getParameter("app_key");
			String timestamp = request.getParameter("timestamp");
			String data = request.getParameter("data");
			String clientSign = request.getParameter("sign");
			
			//根据app_key判断客户端传递过来的数据有无权限
			if(!"stoapp01".equals(appKey)){
				
				commonUtil.setCode(403);
				commonUtil.setMessage("您没有访问权限");
				
			}else{
				
				//判断传递过来的时间戳是否在当前时间(北京时间)的前后20分钟
				//将客户端传递过来的时间转成毫秒值
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = format.parse(timestamp);
				long clientTime = date.getTime();
				System.out.println(clientTime);
				//获取当前时间的毫秒值
				String currentTime = format.format(new Date());
				Date currentDate = format.parse(currentTime);
				long serverTime = currentDate.getTime();
				
				System.out.println(serverTime);
				
				if(!(clientTime>=(serverTime-1200000)&&clientTime<=(serverTime+1200000))){
					
					//时间戳不在规定时间范围内
					commonUtil.setCode(25);
					commonUtil.setMessage("时间戳不在规定范围内");
					
				}else{
					
					//时间戳在规定时间内,判断秘钥,签名是否正确
					String serverSign = MD5Utils.md5("21232f297a57a5a743894a0e4a801fc3"
							+ "app_key" + "stoapp01" + "data" + data + "method" + method
							+ "timestamp" + timestamp + "21232f297a57a5a743894a0e4a801fc3").toUpperCase();
					if(!clientSign.equals(serverSign)){
						
						commonUtil.setCode(31);
						commonUtil.setMessage("签名错误");
						
					}else{
						
						//签名正确

						if (method == null) {
							commonUtil.setCode(3);
							commonUtil.setMessage("参数错误");
						} else if (method == null || method.equals("")) {
							commonUtil.setCode(4);
							commonUtil.setMessage("方法名为必填项");
						} else if (CommonMethod.GETADMINDIV.equals(method)) {
							
							map = super.judgeData(request);
							List<AdminDIV> admindivs = adminDIVService.findAll(map);
							commonUtil.setData(admindivs);
							commonUtil.setCode(0);
							
						} else if (CommonMethod.GETAREA.equals(method)) {
							
							map = super.judgeData(request);
							List<Area> areas = areaService.findAreaByCondition(map);
							commonUtil.setData(areas);
							commonUtil.setCode(0);
						
							
						} else if (CommonMethod.GETCOUNTRY.equals(method)) {
							
							Map<String, Object> map1 = new HashMap<String, Object>();
//							String data = request.getParameter("data");
							// 判断客户端传递过来的json数据
							if (data != null) {
								JSONObject obj = JSONObject.parseObject(data);
								// 判断json里的时间戳是否为空
								if (obj.get("incTimestamp") != null) {
									String time = obj.get("incTimestamp").toString();

									// 判断取出的时间是否有填，如果没有填，将null放进map中
									if (time.equals("")) {
										map1.put("SYNC_TIMESTAMP", null);
									} else {

										map1.put("SYNC_TIMESTAMP", time);
									}

								} else {
									// obj.get("incTimestamp")为null
									map1.put("SYNC_TIMESTAMP", null);
								}

							} else {
								//如果data为null,将SYNC_TIMESTAMP设置为null.
								
//								map1.put("SYNC_TIMESTAMP", request.getParameter("timestamp"));
								map1.put("SYNC_TIMESTAMP", null);
							}
							List<Country> countries = countryService.findCountryByCondition(map1);
							commonUtil.setData(countries);
							commonUtil.setCode(0);
							
						} else if (CommonMethod.GETDEPARTMENT.equals(method)) {
							
							
							
						} else if (CommonMethod.GETSITE.equals(method)) {
							
							map = super.judgeData(request);
							List<Site> sites = siteService.findSiteByCondition(map);
							commonUtil.setData(sites);
							commonUtil.setCode(0);
						
							
						} else if (CommonMethod.GETTRANS.equals(method)) {

							map = super.judgeData(request);
							List<Trans> trans = transService.findTransByCondition(map);
							commonUtil.setData(trans);
							commonUtil.setCode(0);
						
							
						} else {
							
							commonUtil.setCode(2);
							commonUtil.setMessage("方法错误");
						}
						
					}
					
				}
				
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
