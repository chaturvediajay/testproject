package com.cart;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;




public class OrderTrasaction {
	public static boolean saveOrder(PaymentDetails pd) {
		boolean bol = false;
		Session session = com.model.HibernateUtil.getSessionFactory().openSession();
		Transaction ts = null;
		Integer itemID = 0;
		try {
			ts = session.beginTransaction();
			bol = (Integer) session.save(pd) > 0 ? true : false;
			ts.commit();

		} catch (Exception e) {
			System.out.println("saveOrder:- " + e.toString());
			if (ts != null)
				ts.rollback();
		} finally {
			session.close();
		}
		return bol;
	}
	
	public static boolean trasactionUpdate(String orderId, String trasactionid, String json, int con) {
		boolean bol = false;
		try {
			Transaction tx = null;
			Session session = null;
			PaymentDetails pd;
			try {
				pd = new PaymentDetails();
				session = com.model.HibernateUtil.getSessionFactory().openSession();
				tx = session.beginTransaction();
				String query = "UPDATE payment_details SET gateWayCode='" + json + "', status=" + con
						+ " WHERE orderId=" + orderId + " and txid=" + trasactionid + "";
				System.out.println("query");
				bol = session.createSQLQuery(query).executeUpdate() > 0 ? true : false;
				session.getTransaction().commit();
			} catch (Exception e) {
				session.getTransaction().rollback();
			} finally {
				session.close();
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			// System.out.println(e.toString());
		}
		return bol;
	}

}
