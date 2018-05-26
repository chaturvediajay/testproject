package com.logic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONObject;

import com.model.HibernateUtil;
import com.model.LoginSession;
import com.model.Registration;
import com.model.UpdateAccount;
import com.scope.MailHtml;
import com.scope.SaltedMD5Example;
import com.scope.SendEmail;
import com.scope.StrongAES;

public class RegLogic {
	final static Logger logger = Logger.getLogger(RegLogic.class);

	public static boolean regNewUser(Registration reg, String url) {
		Session session = null;
		Transaction transaction = null;
		boolean bol = false;
		reg.setPswd(StrongAES.run(reg.getPswd()));
		reg.setAuthorize(2);
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			int userid = (int) session.save(reg);
			MailHtml.activateAccount(reg.getName(), session, userid, url, reg.getEmail());
			bol = userid > 0 ? true : false;

			if (!transaction.wasCommitted()) {
				transaction.commit();
				bol = true;
			}
		} catch (Exception e) {
			logger.error("RegLogic error(33) regNewUser  " + e.toString());
			if (transaction != null)
				transaction.rollback();
		} finally {
			if (session != null)
				session.close();
		}
		return bol;
	}

	public static boolean loginUser(HttpServletRequest request) {
		boolean bol = false;
		try {
			String username = request.getParameter("username");
			String pswd = request.getParameter("pswd");

			if ((username.length() > 0 & username != null) & (pswd.length() > 0 & pswd != null)) {
				String query = "SELECT username,uid,name,email,authorize FROM registration " + "where pswd='"
						+ StrongAES.run(pswd) + "' and (email='" + username + "' or username='" + username + "')";

				System.out.println(query);

				List<Object[]> list = com.scope.AbstractClass.listObj(query);
				if (list != null & list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						LoginSession ls = new LoginSession();
						Object[] row = (Object[]) list.get(i);
						int num = row.length;
						for (int j = num - 1; j < row.length; j++) {
							ls.setAuthorize(Integer.parseInt(row[j].toString()));
							ls.setEmail(row[j - 1].toString());
							ls.setName(row[j - 2].toString());
							ls.setId(Integer.parseInt((row[j - 3].toString())));
							ls.setUsername(row[j - 4].toString());
							bol = true;
							request.getSession().setAttribute("loginSession", ls);
						}
					}
				}

				// int num = ((Long) session.createQuery("SELECT
				// secret,username,uid,name,email,authorize FROM registration
				// where pswd='" + pswd
				// + "' and (email='" + username + "' or username='" + username
				// + "')").uniqueResult()).intValue();
				// if (num > 0)
				// request.getSession().setAttribute("admin", rendomNumber());
				// bol = num > 0 ? true : false;
			}
		} catch (Exception e) {
			logger.error("RegLogic error(83)loginUser  " + e.toString());
		} finally {

		}
		return bol;
	}

	public static Registration getProductUserDetail(HttpServletRequest request) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		LoginSession ls = (LoginSession) request.getSession().getAttribute("loginSession");
		try {

			Transaction transaction = session.beginTransaction();
			return (Registration) session.get(Registration.class, ls.getId());
		} catch (Exception e) {
			logger.error("RegLogic error(96)getProductUserDetail  " + e.toString());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return null;
	}

	public static boolean checkLogin(HttpServletRequest request) {
		try {
			if (request.getSession().getAttribute("loginSession") != null) {
				LoginSession ls = (LoginSession) request.getSession().getAttribute("loginSession");
				System.out.println("loginSession: " + ls);
				if (ls.getId() > 0)
					return false;
			}
		} catch (Exception e) {
			logger.error("RegLogic error(114) checkLogin  " + e.toString());
		}
		return true;
	}

	public static Registration setRegAddress(JSONObject obj, LoginSession ls) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Registration mi = null;
		try {
			mi = (Registration) session.get(Registration.class, ls.getId());
			if (mi != null) {
				mi.setStreet(obj.getString("street"));
				mi.setAddress(obj.getString("address"));
				mi.setCity(obj.getString("city"));
				mi.setPincode(obj.getString("pincode"));
				mi.setState(obj.getString("state"));
				session.update(mi);
			}
		} catch (Exception ex) {
			if (tx != null) {
				session.getTransaction().rollback();
			}
			if (session != null) {
				session.getTransaction().commit();
				session.close();
			}
		} finally {
			if (session != null) {
				tx.commit();
				session.close();
			}
			obj.remove("street");
			obj.remove("address");
			obj.remove("city");
			obj.remove("pincode");
			obj.remove("state");
		}

		return mi;
	}

	public static boolean forgot_password_email(String email) {

		boolean bol = false;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Number number = 0;
		Number uid = 0;
		try {
			String SQL_QUERY = "SELECT uid from registration r where  r.email='" + email + "'";
			Query query = session.createSQLQuery(SQL_QUERY);
			List listResult = query.list();
			uid = (Number) listResult.get(0);
			String encyptedcode = SaltedMD5Example.passwordStatic(false, "");
			number = uid;
			if (number.intValue() > 0) {
				SQL_QUERY = "SELECT id from updateaccount r where  uid=" + number;
				System.out.println(SQL_QUERY);
				number = 0;
				query = session.createSQLQuery(SQL_QUERY);
				listResult = query.list();
				System.out.println(listResult.isEmpty());
				if (listResult.isEmpty()) {
					UpdateAccount ua = new UpdateAccount();
					ua.setCode(encyptedcode);
					ua.setUid(uid.intValue());
					ua.setComment("forgot password");
					session.save(ua);
					number = 1;
				} else {
					SQL_QUERY = "UPDATE updateaccount SET code='" + encyptedcode + "'," + "createDate=(SELECT NOW())"
							+ "WHERE uid=" + uid;
					query = session.createSQLQuery(SQL_QUERY);
					number = query.executeUpdate();
				}
			}
			if (number.intValue() > 0) {
				tx.commit();
				String str = "<h3>Welcome " + "</h3>"
						+ "<p>&nbsp; &nbsp; &nbsp; &nbsp; please activate your account following link.... &nbsp;</p>"
						+ "<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;"
						+ "<a href='http://" + "url" + "/activation?code=" + encyptedcode
						+ "'>Activation Account</a>(expire after 48 hours)</p>"
						+ "<p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p>" + "<p style=\"text-align: center;\">"
						+ "@chouhanrugs2018-Team</p>";
				SendEmail.send(str, "forgot password", email);
				bol = true;
			} else
				session.getTransaction().rollback();

		} catch (Exception ex) {
			if (tx != null)
				session.getTransaction().rollback();

		} finally {
			if (session != null)
				session.close();
		}
		return bol;
	}

	private static int rendomNumber() {
		Random rn = new Random();
		int range = 99999999;
		return rn.nextInt(range);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		forgot_password_email("kr.maheshngh@gmail.com");

	}

}
