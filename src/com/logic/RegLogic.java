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
import com.scope.AbstractClass;
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
		reg.setPswd(SaltedMD5Example.passwordStatic(true,reg.getPswd()));
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
			pswd= SaltedMD5Example.passwordStatic(true,pswd);

			if ((username.length() > 0 & username != null) & (pswd.length() > 0 & pswd != null)) {
				String query = "SELECT username,uid,name,email,authorize FROM registration " + "where pswd='"
						+ pswd + "' and (email='" + username + "' or username='" + username + "')";

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

	public static boolean forgot_password_email(String email, String url) {

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
							+ ",status=0 WHERE uid=" + uid;
					query = session.createSQLQuery(SQL_QUERY);
					number = query.executeUpdate();
				}
			}
			if (number.intValue() > 0) {
				tx.commit();
				String str = "<h3>Welcome " + "</h3>"
						+ "<p>&nbsp; &nbsp; &nbsp; &nbsp; please activate your account following link.... &nbsp;</p>"
						+ "<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;"
						+ "<a href='http://" + url + "/forgot_password_email?ctx=change&code=" + encyptedcode
						+ "'>Change Password</a>(expire after 48 hours)</p>" + "<p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p>"
						+ "<p style=\"text-align: center;\">" + "@chouhanrugs2018-Team</p>";
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

	public static boolean change_password(HttpServletRequest request) {
		boolean bol = false;
		String ctx = request.getParameter("ctx");
		String code = request.getParameter("code");
		String password = request.getParameter("password");
		String confirmPwd = request.getParameter("confirmPwd");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			if (ctx.equals("change") & password != null & confirmPwd != null & password.equals(confirmPwd)
					& code != null) {
				tx = session.beginTransaction();
				password = SaltedMD5Example.passwordStatic(true, password);
				String sql_query = "update registration r,(SELECT * FROM updateaccount where code='" + code + "') src ,"
						+ " updateaccount ua" + " SET r.pswd = '" + password
						+ "',ua.status=1 where r.uid=src.uid and src.status=0";
				Query query = session.createSQLQuery(sql_query);
				bol = query.executeUpdate() > 0 ? true : false;
				System.out.println("num  " + sql_query);
				tx.commit();
			}

		} catch (Exception ex) {
			if (tx != null)
				session.getTransaction().rollback();

		} finally {
			if (session != null)
				session.close();
		}

		return bol;
	}
	public static int reg_rowCount(String categories,String param){
		String sql_query="";
		switch (categories) {
		case "update_account_code":
			sql_query="select count(*)  from updateaccount where code='"+param+"' and status=0";
			break;

		default:
			break;
		}
		return AbstractClass.row_Count(sql_query);
		
	}
	private static int rendomNumber() {
		Random rn = new Random();
		int range = 99999999;
		return rn.nextInt(range);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	}

}
