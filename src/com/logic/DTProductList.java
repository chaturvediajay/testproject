package com.logic;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.model.HibernateUtil;
import com.model.JqueryDataTableModel;
import com.model.LoginSession;
import com.model.ProductDetail;
import com.scope.AbstractClass;
import com.scope.SessionUser;

public class DTProductList {
	private int count = 0;
	final static Logger logger = Logger.getLogger(DTProductList.class);

	public List productList(HttpServletRequest request, int start, int length) {
		int status = 0;
		int visible = 0;
		List list = null;
		String wh_con = "";
		try {
			Enumeration<String> kayParams = request.getParameterNames();
			while (kayParams.hasMoreElements()) {
				String key = (String) kayParams.nextElement();
				String value = request.getParameter(key).toString();
				System.out.println("key " + key + " value:- " + value);
				if (key.equals("p1"))
					status = Integer.parseInt(value);
				else if (key.equals("p2"))
					visible = Integer.parseInt(value);

				if (key.equals("search[value]"))
					if (value.length() > 0)
						wh_con += "p.title LIKE '%" + value + "%' or p.pkey like '%" + value + "%'";

			}

			LoginSession ls = (LoginSession) SessionUser.getSessionObject2(request, "loginSession");
			String[] query = new String[4];
			query[3] = "";
			query[0] = "SELECT  p.pkey,p.title,p.status";
			query[1] = ",(select count(*) from product) as row_no";
			query[2] = " FROM product p";
			if (wh_con.length() > 5)
				query[3] = " where " + wh_con;

			System.out.println("dfsfsf   " + query[3].length() + " : " + query[0] + query[1] + query[2] + query[3]);
			list = AbstractClass.listObj(query[0] + query[1] + query[2] + query[3], start, length);

			System.out.println("size " + list.size());
			// count=list.size();
			outerloop: for (int i = 0; i < list.size(); i++) {
				Object[] row = (Object[]) list.get(i);
				for (int j = row.length - 1; j < row.length; j++) {
					if (i == 0) {
						count = Integer.parseInt(row[j].toString());
						System.out.println("row count query : -" + row[j - 0].toString() + " : " + row[j - 1].toString()
								+ " : " + row[j - 2].toString());
						break outerloop;
					}
				}
			}

		} catch (Exception e) {
			logger.error("DTProductList error(60)productList  " + e.toString());

		}
		return list;
	}

	public List OrderListGet(HttpServletRequest request, int start, int length) {
		int status = 0;
		int visible = 0;
		List list = null;
		try {

			JqueryDataTableModel jdtm = new JqueryDataTableModel();
			jdtm.JqueryDataTableModel(request);

			LoginSession ls = (LoginSession) SessionUser.getSessionObject2(request, "loginSession");
			String[] query = new String[5];
			query[3] = "";
			query[0] = "id.orderId,p.title" + ",sum(id.smrp) as tSMRP " + ",sum(id.mrp) as tMRP ";

			query[1] = "from item_details id " + "inner join product p on p.pid=id.pid "
					+ "inner join product_detail pd " + "on pd.pdid=id.ppid ";

			if (ls.getAuthorize() != 1)
				query[3] += " id.uid= " + ls.getId();

			/*---search in datatable textbox */
			if (jdtm.getSearch_value().length() > 0)
				query[3] += " id.orderId LIKE '%" + jdtm.getSearch_value() + "%' or p.pkey like '%"
						+ jdtm.getSearch_value() + "%'";
			
			if (query[3].length() > 5)
				query[3] = " where" + query[3];

			query[4] = " group by orderId";

			query[2] = "SELECT (SELECT count(*) " + query[1] + query[3] + ") as ccount," + query[0] + query[1]
					+ query[3] + query[4];

			System.out.println(query[2]);
			list = AbstractClass.listObj(query[2], start, length);

			System.out.println("size " + list.size());

			outerloop: for (int i = 0; i < list.size(); i++) {
				Object[] row = (Object[]) list.get(i);
				for (int j = row.length - 1; j < row.length; j++) {
					if (i == 0) {
						count = Integer.parseInt(row[j - 4].toString());
						break outerloop;
					}
				}
			}

		} catch (Exception e) {
			logger.error("DTProductList error(60)productList  " + e.toString());

		}
		return list;
	}

	public List<ProductDetail> productListWithPkey(HttpServletRequest request, int start, int length) {
		String pkey = null;
		String scope = null;
		List<ProductDetail> ld = null;
		try {
			Enumeration<String> kayParams = request.getParameterNames();
			while (kayParams.hasMoreElements()) {
				String key = (String) kayParams.nextElement();
				String value = request.getParameter(key).toString();
				if (key.equals("p1"))
					pkey = value;
				else if (key.equals("p2"))
					scope = value;
			}

			if (pkey != null) {
				Session session = HibernateUtil.getSessionFactory().openSession();
				session.beginTransaction();
				Criteria criteria = session.createCriteria(ProductDetail.class);
				criteria.add(Restrictions.eq("pkey", pkey));
				count = criteria.list().size();
				ld = criteria.list();
				session.close();

			}
		} catch (Exception e) {
			logger.error("DTProductList error(93)productListWithPkey  " + e.toString());

		}
		return ld;
	}

	public static void main(String[] args) {
		DTProductList dl = new DTProductList();
		dl.OrderListGet(null, 0, 50);
	}

	public static void testSelectUsingProperties() {
		final Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(ProductDetail.class);
		// get the count
		ScrollableResults results = criteria.scroll();
		results.last();
		int total = results.getRowNumber() + 1;
		results.close();

		// get the actual records to display
		// final Criteria criteria = getCriteria(session);
		criteria.setFirstResult(2);// first record is 2
		criteria.setMaxResults(3);
		List<Object[]> rows = criteria.list();
		for (Object[] row : rows) {
			System.out.println(row[0] + " and " + row[1]);
		}
		System.out.println("Total records is  " + total);
		session.close();
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
