package com.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.json.JqueryDataTable;
import com.logic.DTProductList;
import com.model.LoginSession;
import com.model.Product;
import com.model.ProductDetail;

@Controller
@RequestMapping(value = { "/admin/" })
public class JQueryDataTable {
	final static Logger logger = Logger.getLogger(JQueryDataTable.class);
	/* ------------Datatable json----------------- */
	@RequestMapping(value = { "getProductList" }, method = { RequestMethod.GET })
	public void TestPagePost(HttpServletRequest request, HttpServletResponse response, Model model) {
			LoginSession ls = (LoginSession) request.getSession().getAttribute("loginSession");
			List<Product> list = null;
			//ProductList pl=new ProductList();
			DTProductList dt=new DTProductList();
			try {
				JqueryDataTable jt=new JqueryDataTable();
				/* request set */
				jt.getRequestData(request);
				String[] keyword = new String[4];
				keyword[0] = String.valueOf(jt.getDraw());/*--indec column--*/
				keyword[1] = jt.getOrder_column();/*--indec column--*/
				keyword[2] = jt.getOrder_dir();/*--asc/desc--*/
				keyword[3] = jt.getSearch_value();/*--keyword search--*/

				System.out.println("getProductList**********"+ls.getAuthorize());
				list = dt.productList( request, jt.getStart(), jt.getLength());
				
				if(list==null) list = Collections.EMPTY_LIST;
				System.out.println("list size:-  "+list.size());
				
				/* list convert to datatable json format*/
				jt.getResposeJson(response, dt.getCount(), new Gson().toJson(list).toString());

			} catch (Exception e) {
				logger.error("JQueryDataTable error(53)TestPagePost  " + e.toString());
			} 
	}
	
	
	
	
	
	
	@RequestMapping(value = { "getProductListWithPkey" }, method = { RequestMethod.GET })
	public void getProductDetail(HttpServletRequest request, HttpServletResponse response, Model model) {
			LoginSession ls = (LoginSession) request.getSession().getAttribute("loginSession");
			List<ProductDetail> list = null;
			//ProductList pl=new ProductList();
			DTProductList dt=new DTProductList();
			try {
				JqueryDataTable jt=new JqueryDataTable();
				/* request set */
				jt.getRequestData(request);
				String[] keyword = new String[4];
				keyword[0] = String.valueOf(jt.getDraw());/*--indec column--*/
				keyword[1] = jt.getOrder_column();/*--indec column--*/
				keyword[2] = jt.getOrder_dir();/*--asc/desc--*/
				keyword[3] = jt.getSearch_value();/*--keyword search--*/

				System.out.println("getProductList**********"+ls.getAuthorize());
				list = dt.productListWithPkey( request, jt.getStart(), jt.getLength());
				
				if(list==null) list = Collections.EMPTY_LIST;
				System.out.println("list size:-  "+list.size());
				
				/* list convert to datatable json format*/
				jt.getResposeJson(response, dt.getCount(), new Gson().toJson(list).toString());

			} catch (Exception e) {
				logger.error("JQueryDataTable error(82)getProductDetail  " + e.toString());
			} 
	}

}
