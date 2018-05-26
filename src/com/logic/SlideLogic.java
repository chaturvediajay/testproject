package com.logic;

import org.apache.derby.tools.sysinfo;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONException;
import org.json.JSONObject;

import com.cart.PaymentDetails;
import com.model.slide_control;

public class SlideLogic {
	final static Logger logger = Logger.getLogger(SlideLogic.class);
	private static String opt, name, value, action;

	private static boolean IsChecked;

	public static boolean trasactionUpdate(String json) {
		boolean bol = false;
		try {
			Transaction tx = null;
			Session session = null;
			PaymentDetails pd;
			try {
				if (checkNullJson(json)) {
					pd = new PaymentDetails();
					session = com.model.HibernateUtil.getSessionFactory().openSession();
					tx = session.beginTransaction();

					slide_control p = (slide_control) session.load(slide_control.class, new Integer(1));
					// String query = "";

					int check = IsChecked ? 1 : 0;
					Query query = null;
					int result = 0;
					
					System.out.println("pkey  "+value);
					
					switch (check) {
					case 1:
						query = session.createQuery("UPDATE slide_control SET name=CONCAT('" + p.getName() + "','"
								+ value + ",')" + " WHERE id=:id");
						query.setParameter("id", p.getId());

						result = query.executeUpdate();

						System.out.println("result1:  " + result);

						query = session.createQuery("update Product set status=" + check + " where pkey=:pkey");
						query.setParameter("pkey", value);

						result = query.executeUpdate();

						System.out.println("result2:  " + result);

						// bol =
						// session.createSQLQuery(query).executeUpdate() > 0
						// ? true : false;

						break;

					case 0:

						// query = "UPDATE slide_control SET name=REPLACE('" +
						// p.getName() + "','" + name + ",','')"
						// + " WHERE id=" + p.getId();
						// query+=" update product_detail set status="+check+ "
						// where pkey='"+"ba9137836639"+"'";

						query = session.createQuery("UPDATE slide_control SET name=REPLACE('" + p.getName() + "','"
								+ value + ",','')" + " WHERE id=:id");
						query.setParameter("id", p.getId());

						result = query.executeUpdate();

						System.out.println("result3:  " + result);

						query = session.createQuery("update Product set status=" + check + " where pkey=:pkey");
						query.setParameter("pkey", value);

						result = query.executeUpdate();

						System.out.println("result4:  " + result);

						// System.out.println(query);
						// bol = session.createSQLQuery(query).executeUpdate() >
						// 0 ? true : false;
						break;

					default:
						break;
					}
					// System.out.println(query);
					session.getTransaction().commit();
				}

				// if (name.contains(p.getName())) {
				// System.out.println(true);
				// String query = "UPDATE slide_control SET name=CONCAT('" +
				// p.getName() + "','" + name + ",')"
				// + " WHERE id=" + p.getId();
				// System.out.println(query);
				// }

				// String query = "UPDATE payment_details SET gateWayCode='" +
				// json + "', status=" + con
				// + " WHERE orderId=" + orderId + " and txid=" + trasactionid +
				// "";
				// System.out.println("query");
				// bol = session.createSQLQuery(query).executeUpdate() > 0 ?
				// true : false;
				// session.getTransaction().commit();
			} catch (Exception e) {

				System.out.println(" uodate " + e.toString());

				session.getTransaction().rollback();
			} finally {
				session.close();
			}
		} catch (HibernateException e) {

			logger.error("PaymentLogic error(36)trasactionUpdate  " + e.toString());
		}
		return bol;
	}

	private static boolean checkNullJson(String json) {
		JSONObject obj;
		try {
			obj = new JSONObject(json);
			if (obj.getString("opt") != null & obj.getString("name") != null & obj.getString("value") != null
					& obj.getString("IsChecked") != null) {
				opt = obj.getString("opt");
				name = obj.getString("name");
				value = obj.getString("value");
				IsChecked = obj.getBoolean("IsChecked");

				System.out.println("check is  " + IsChecked);

				return true;
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public static String getAction() {
		return action;
	}

	private static int getSlideId(String name) {
		if (name.equals("top_slide"))
			return 1;
		return 0;
	}

	public static void main(String[] args) {

		Transaction tx = null;
		Session session = null;
		session = com.model.HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();

		// slide_control p = (slide_control) session.load(slide_control.class,
		// new Integer(1));

		Query query = session.createQuery("UPDATE slide_control SET name='ju' WHERE id = :id");
		query.setParameter("id", 1);

		int result = query.executeUpdate();

		System.out.print("result:-  " + result);

		query = session.createQuery("UPDATE slide_control SET name='noo' WHERE id = :id");
		query.setParameter("id", 1);
		result = query.executeUpdate();
		System.out.print("result:-  " + result);
		session.close();

	}
}
