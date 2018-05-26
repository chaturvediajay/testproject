package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONValue;

import com.html.ProductPagingHome;
import com.logic.ProductLogic;
import com.nonModel.SlideHome;

/**
 * Servlet implementation class paging_response
 */
@WebServlet("/paging_response")
public class paging_response extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public paging_response() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			
			String count = request.getParameter("_count").trim();
			
			System.out.println("_count: "+count);
			List<SlideHome> lsh= ProductLogic.getProductHome("all", "",Integer.parseInt(count),16);
			PrintWriter out = response.getWriter();
			JSONObject json = new JSONObject();
			json.put("name", ProductPagingHome.getProductShow(lsh,request));
			json.put("email", "codesstore@blogspot.com");
			out.print(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
