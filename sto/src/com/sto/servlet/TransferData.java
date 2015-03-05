package com.sto.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TransferData extends HttpServlet {

	public static final String GETADMINDIV = "getAdminDiv.action";

	public static final String GETCOUNTRY = "getCountry.action";

	public static final String GETAREA = "getArea.action";

	public static final String GETDEPARTMENT = "getDepartment.action";

	public static final String GETTRANS = "getTrans.action";

	public static final String GETSITE = "getSite.action";

	public static final String STATUS = "0";
	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	

}
