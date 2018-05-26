package com.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.cart.PaymentDetails;
public class PaymentLogic {
	final static Logger logger = Logger.getLogger(PaymentLogic.class);
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

			logger.error("PaymentLogic error(36)trasactionUpdate  " + e.toString());
		}
		return bol;
	}
	
	public static double getAmt(String tockenId)
	{
		Transaction tx = null;
		Session session = null;
		double amt=0;
		try {
			
			session = com.model.HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			String query = "SELECT od.total FROM order_details od inner join payment_details pd"
					+ " on pd.orderId=od.orderId where pd.txid='"+tockenId+"'";
			amt =  (double) session.createSQLQuery(query).uniqueResult();
			System.out.println("== "+amt);
			if (amt > 0) {
				return amt;
			}
			
		} catch (Exception e) {
			System.out.println("checkEncyptData:- " + e.toString());
			e.printStackTrace();
		} finally {
		}
		return amt;
	}
	
	public static boolean updateOrderStatus(String msg,String tokenId) {
		boolean bol = false;
		try {
			Map params = getQueryMap(msg);
			Transaction tx = null;
			Session session = null;
			PaymentDetails pd;
			try {
				pd = new PaymentDetails();
				session = com.model.HibernateUtil.getSessionFactory().openSession();
				tx = session.beginTransaction();
				String query = "UPDATE payment_details SET gateWayCode='" + "{\"order_status\":\""
						+ (String) params.get("ACK") + "\",\"tracking_id\":\"" + (String) params.get("TRANSACTIONID")
						+ "\",\"status_message\":\"" + (String) params.get("PAYMENTSTATUS") + "\"}" + "', visible=" + paypalPaymentCode(msg)
						+ " WHERE txid='" +tokenId + "'";
				System.out.println("query:-  "+query);
				bol = session.createSQLQuery(query).executeUpdate() > 0 ? true : false;
				session.getTransaction().commit();
			} catch (Exception e) {

				session.getTransaction().rollback();
			} finally {
				session.close();
			}
		} catch (HibernateException e) {

			logger.error("PaymentLogic error(36)trasactionUpdate  " + e.toString());
		}
		return bol;
	}
	
	
	public static List<Object[]>  orderCancel(String trasaction_code, String tokenId, int status) {
		boolean bol = false;
		List<Object[]> list=null;
		try {
			Transaction tx = null;
			Session session = null;
			PaymentDetails pd;
			try {
				pd = new PaymentDetails();
				session = com.model.HibernateUtil.getSessionFactory().openSession();
				tx = session.beginTransaction();
				String query = " SELECT od.total,pd.orderId,od.cdate,pd.visible,ship.sName FROM payment_details pd"
						+ " inner join order_details od on pd.orderId=od.orderId" 
						+" inner join shipping ship on ship.orderId=pd.orderId"
						+ " where pd.txid='" + tokenId
						+ "' and pd.trasaction_code='" + trasaction_code + "' and pd.visible=0";
				System.out.println("query:-  " + query);
				Query hSql = session.createSQLQuery(query);
				list = hSql.list();
				if(list.size()>0) bol=true;
				if (bol) {
					 query = "update payment_details set visible=" + status + " where txid='" + tokenId
							+ "' and  trasaction_code='" + trasaction_code + "'";
					System.out.println("query:-  " + query);
					bol = session.createSQLQuery(query).executeUpdate() > 0 ? true : false;
					session.getTransaction().commit();
				}

			} catch (Exception e) {

				session.getTransaction().rollback();
			} finally {
				session.close();
			}
		} catch (HibernateException e) {

			logger.error("PaymentLogic error(36)trasactionUpdate  " + e.toString());
		}
		return list;
	}

	private static Map<String, String> getQueryMap(String query) {
		String[] params = query.split("&");
		Map<String, String> map = new HashMap<String, String>();
		for (String param : params) {
			String[] p = param.split("=");
			String name = p[0];
			if (p.length > 1) {
				String value = p[1];
				map.put(name, value);
			}
		}
		return map;
	}

	private static int paypalPaymentCode(String msg) {
		Map params = getQueryMap(msg);
		if (params.get("ACK").toString().equals("Success"))
			return 1;
		else
			return -1;
	}
	
	public static boolean updateOrderDispatch(String orderId, int value) {
		boolean bol = false;
		try {
			Transaction tx = null;
			Session session = null;
			try {
				session = com.model.HibernateUtil.getSessionFactory().openSession();
				tx = session.beginTransaction();
				String query = "UPDATE order_details SET visible='" + value + "', status='" + value
						+ "' WHERE orderId='" + orderId + "'";
				System.out.println("query= "+query);
				bol = session.createSQLQuery(query).executeUpdate() > 0 ? true : false;
				System.out.println(bol);
				session.getTransaction().commit();
			} catch (Exception e) {
				
				session.getTransaction().rollback();
			} finally {
				session.close();
			}
		} catch (HibernateException e) {

			logger.error("PaymentLogic error(184) updateOrderDispatch  " + e.toString());
		}
		return bol;
	}
	
	public static void main(String args[])
	{
		
		String orderId="eh3620429321";
		int val=0;
		System.out.println("Update value= "+updateOrderDispatch(orderId, 0));
	}
	

}
