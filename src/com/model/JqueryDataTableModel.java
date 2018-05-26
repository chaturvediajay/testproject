package com.model;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class JqueryDataTableModel {
	private String columns_data0;
	private String columns_name0;
	private boolean columns_searchable0;
	private boolean columns_orderable0;
	private String columns_search_value0;
	private boolean columns_search_regex0;
	private String columns_data1;
	private String columns_name1;
	private boolean columns_searchable1;
	private boolean columns_orderable1;
	private String columns_search_value1;
	private boolean columns_search_regex1;
	private String order_column;
	private String order_dir;
	private String start;
	private String length;
	private String search_value;
	private boolean search_regex;

	public void JqueryDataTableModel(HttpServletRequest request) {
		Enumeration<String> kayParams = request.getParameterNames();
		while (kayParams.hasMoreElements()) {
			String key = (String) kayParams.nextElement();
			String value = request.getParameter(key).toString();
			System.out.println("key " + key + " value:- " + value);
			if (key.equals("columns[0][data]"))
				columns_data0 = value;
			else if (key.equals("columns[0][name]"))
				columns_name0 = value;
			else if (key.equals("columns[0][searchable]"))
				columns_searchable0 = Boolean.getBoolean(value);
			else if (key.equals("columns[0][orderable]"))
				columns_orderable0 = Boolean.getBoolean(value);
			else if (key.equals("columns[0][search][value]"))
				columns_search_value0 = value;
			else if (key.equals("columns[0][search][regex]"))
				columns_search_regex0 = Boolean.getBoolean(value);
			else if (key.equals("columns[1][data]"))
				columns_data1 = value;
			else if (key.equals("columns[1][name]"))
				columns_name1 = value;
			else if (key.equals("columns[1][searchable]"))
				columns_searchable1 = Boolean.getBoolean(value);
			else if (key.equals("columns[1][orderable]"))
				columns_orderable1 = Boolean.getBoolean(value);
			else if (key.equals("columns[1][search][value]"))
				columns_search_value1 = value;
			else if (key.equals("columns[1][search][regex]"))
				columns_search_regex1 = Boolean.getBoolean(value);
			else if (key.equals("order[0][column]"))
				order_column = value;
			else if (key.equals("order[0][dir]"))
				order_dir = value;
			else if (key.equals("start"))
				start = value;
			else if (key.equals("length"))
				length = value;
			else if (key.equals("search[value]"))
				search_value = value;
			else if (key.equals("search[regex]"))
				search_regex = Boolean.getBoolean(value);

		}
	}

	public String getColumns_data() {
		return columns_data1;
	}

	public String getColumns_name() {
		return columns_name1;
	}

	public boolean isColumns_searchable() {
		return columns_searchable1;
	}

	public boolean isColumns_orderable() {
		return columns_orderable1;
	}

	public String getColumns_search_value() {
		return columns_search_value1;
	}

	public boolean isColumns_search_regex() {
		return columns_search_regex1;
	}

	public String getColumns_data1() {
		return columns_data1;
	}

	public String getColumns_name1() {
		return columns_name1;
	}

	public boolean isColumns_searchable1() {
		return columns_searchable1;
	}

	public boolean isColumns_orderable1() {
		return columns_orderable1;
	}

	public String getColumns_search_value1() {
		return columns_search_value1;
	}

	public boolean isColumns_search_regex1() {
		return columns_search_regex1;
	}

	public String getOrder_column() {
		return order_column;
	}

	public String getOrder_dir() {
		return order_dir;
	}

	public String getStart() {
		return start;
	}

	public String getLength() {
		return length;
	}

	public String getSearch_value() {
		return search_value;
	}

	public boolean isSearch_regex() {
		return search_regex;
	}

}
