package com.json;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class JqueryDataTable {
	private int start = 0;
	private int length = 0;
	private int draw = 0;
    private String order_column="";
    private String order_dir="";
    private String search_value="";
    private String pkey="";
	final static Logger logger = Logger.getLogger(JqueryDataTable.class);
	
	
	public void getRequestData(HttpServletRequest request)	{
		try {
			Enumeration<String> kayParams = request.getParameterNames();
			while (kayParams.hasMoreElements()) {
				String key = (String) kayParams.nextElement();
				String value = request.getParameter(key).toString();
				if (key.equals("start"))
					start = Integer.parseInt(value);
				else if (key.equals("length"))
					length = Integer.parseInt(value);
				else if (key.equals("draw"))
					draw = Integer.parseInt(value);
				else if (key.equals("order[0][column]"))
					order_column = value;
				else if (key.equals("order[0][dir]"))
					order_dir = value;
				else if (key.equals("search[value]"))
					search_value = value;
				else if (key.equals("pkey"))
					pkey = value;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("request not fount : " + e.toString());
		}
	}
	
	
	public void getResposeJson(HttpServletResponse response,int result_count,String json) {
		try {
			JsonObject jsonResponse = new JsonObject();
			jsonResponse.addProperty("draw", draw);
			jsonResponse.addProperty("recordsTotal", result_count);
			jsonResponse.addProperty("recordsFiltered", result_count);
			jsonResponse.addProperty("data", json);
			logger.error("gson data String format : " + json);
			response.setContentType("application/Json");
			response.getWriter().print(jsonResponse.toString());
		} catch (NumberFormatException e) {
			logger.error("number format exception(jsondata convert datatable) : " + e.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("IOException(jsondata convert datatable) : " + e.toString());
		}
	}


	public int getStart() {
		return start;
	}


	public void setStart(int start) {
		this.start = start;
	}


	public int getLength() {
		return length;
	}


	public void setLength(int length) {
		this.length = length;
	}


	public int getDraw() {
		return draw;
	}


	public void setDraw(int draw) {
		this.draw = draw;
	}


	public String getOrder_column() {
		return order_column;
	}


	public void setOrder_column(String order_column) {
		this.order_column = order_column;
	}


	public String getOrder_dir() {
		return order_dir;
	}


	public void setOrder_dir(String order_dir) {
		this.order_dir = order_dir;
	}


	public String getSearch_value() {
		return search_value;
	}


	public void setSearch_value(String search_value) {
		this.search_value = search_value;
	}


	public String getPkey() {
		return pkey;
	}


	public void setPkey(String pkey) {
		this.pkey = pkey;
	}
	
	
	
	
	

}
