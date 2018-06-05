package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.json.JSONException;
import org.json.JSONObject;

import com.html.ProductPagingHome;
import com.logic.ProductLogic;
import com.model.HibernateUtil;
import com.nonModel.SlideHome;
import com.scope.AbstractClass;

@WebServlet("/paging_search")
public class paging_search extends HttpServlet {
	static final Logger logger = Logger.getLogger(paging_search.class);
	private static final long serialVersionUID = 1L;
	private String caase;
	private int start = 0;
	private int length = 16;
	private String _tag;

	private int row_count=0;
	public paging_search() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			getParameter(request);
			List<SlideHome> lsh = getProductHome();
			PrintWriter out = response.getWriter();
			JSONObject json = new JSONObject();
			json.put("name", ProductPagingHome.getProductShow(lsh, request));
			json.put("row_count", row_count);
			out.print(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private List<SlideHome> getProductHome() {
		List<SlideHome> lsh = new java.util.ArrayList();
		String query = null;
		// if (_tag.equals("all")) {
		// query = "SELECT pd.url,group_concat(concat(`size`) separator ',') as
		// size, pd.description, p.title,pd.mrp,pd.smrp,pd.pkey FROM product p
		// join product_detail pd on pd.pkey=p.pkey group by p.pkey";
		// } else if (_tag.equals("single")) {
		// query = "SELECT pd.visible,pd.color,pd.url,pd.description,pd.size,
		// p.title,pd.mrp,pd.smrp,pd.pkey FROM product p join product_detail pd
		// on pd.pkey=p.pkey where CONCAT(p.pkey, pd.pdid)='"
		// + pkey + "' " + " group by p.pkey";
		// } else if (_tag.equals("pkey")) {
		// query = "SELECT pd.visible,pd.color,pd.url,pd.description,pd.size,
		// p.title,pd.mrp,pd.smrp,pd.pkey FROM product p join product_detail pd
		// on pd.pkey=p.pkey where p.pkey='"
		// + pkey + "' " + " group by p.pkey";
		// } else if ((_tag.equals("slide_top_all")))
		// query = "SELECT pd.url,group_concat(concat(`size`) separator ',') as
		// size, pd.description, p.title,pd.mrp,pd.smrp,pd.pkey FROM product p
		// join product_detail pd on pd.pkey=p.pkey "
		// + " where p.status=1 group by p.pkey";
		if (_tag.equals("search"))
			query = "SELECT pd.url,group_concat(DISTINCT concat(`size`) separator ',') as size,"
					+ "pd.description,p.title,pd.mrp,pd.smrp,pd.pkey FROM product p,product_detail pd,"
					+ " menu1 m1,menu2 m2,menu3 m3 where pd.pkey=p.pkey"
					+ " and (m1.menu like '%"+caase.replace("-", " ")+"%' or m2.submenu like '%"+caase.replace("-", " ")+"%' or m3.submenu like '%"+caase.replace("-", " ")+"%' or p.title='%"+caase.replace("-", " ")+"%' )"
					+ " group by p.pkey ";

		Session session = HibernateUtil.getSessionFactory().openSession();
		System.out.println("query===" + query);
		try {
			session.beginTransaction();
			List<Object[]> list = null;
			AbstractClass ac=new AbstractClass();
			if (start == 0 & length == 0)
				list = ac.listObj(query);
			else
				list = ac.listObj(query, start, length);
			
			row_count=ac.getRowcount();
			System.out.println(ac.getRowcount()+":"+length);
			row_count=row_count/length;

			for (int i = 0; i < list.size(); i++) {
				Object[] row = (Object[]) list.get(i);
				for (int j = row.length - 1; j < row.length; j++) {
					SlideHome sh = new SlideHome();
					sh.setPkey(row[j].toString());
					sh.setSmrp(row[(j - 1)].toString());
					sh.setMrp(row[(j - 2)].toString());
					sh.setTitle(row[(j - 3)].toString());
					sh.setSize(row[(j - 5)].toString());
					sh.setDescription(row[(j - 4)].toString());
					sh.setUrl(row[(j - 6)].toString());
					// if (!_tag.equals("all") & !_tag.equals("slide_top_all"))
					// {
					// sh.setColor(row[(j - 7)].toString());
					// sh.setVisible(Integer.parseInt(row[(j - 8)].toString()));
					// }

					lsh.add(sh);

					System.out.println(sh);
				}
			}

		} catch (Exception e) {
			logger.error("ProductLogic error(176)getProductHome  " + e.toString());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return lsh;
	}

	public static void main(String[] args) {
		paging_search ps = new paging_search();

		ps.getProductHome();

	}

	private void getParameter(HttpServletRequest request) {
		caase = request.getParameter("_query");
		if (request.getParameter("_count") == null | request.getParameter("_count").equals("undefined"))
			start = 1;
		else
			start = Integer.parseInt(request.getParameter("_count"));

		if (request.getParameter("_query") == null | request.getParameter("_query").equals("undefined"))
			caase = "";
		else
			caase = request.getParameter("_query");

		if (request.getParameter("_tag") == null | request.getParameter("_tag").equals("undefined"))
			_tag = "";
		else
			_tag = request.getParameter("_tag");

	}

}
