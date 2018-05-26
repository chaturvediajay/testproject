package com.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONObject;

import com.model.CartModel;
import com.model.HibernateUtil;
import com.model.LoginSession;
import com.model.Registration;
import com.scope.AbstractClass;
import com.scope.SessionUser;
import com.scope.StrongAES;

public class BuyerDetails {

	private static Transaction tx = null;
	static SessionUser su = new SessionUser();

	private int count;



	public static Registration getRegInfo(int uid) {
		Registration reg = null;
		String query = "select username,email,name from Registration where uid=" + uid + "";
		try {
			List<Object[]> list = AbstractClass.listObj(query);

			for (int i = list.size() - 1; i < list.size(); i++) {
				reg = new Registration();
				Object[] row = (Object[]) list.get(i);
				for (int j = row.length - 1; j < row.length; j++) {
					reg.setName(row[j].toString());
					reg.setEmail(row[j - 1].toString());
					reg.setUsername(row[j - 2].toString());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reg;
	}

	public static boolean setNewPassword(JSONObject obj, LoginSession ls) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Registration mi = (Registration) session.get(Registration.class, ls.getId());
			if (mi.getPswd().equals(StrongAES.run(obj.getString("oldPwd")))) {
				mi.setPswd(StrongAES.run(obj.getString("newPwd")));
				session.update(mi);
				return true;
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
			obj.remove("oldPwd");
			obj.remove("newPwd");
			obj.remove("retypeNewPwd");
		}

		return false;
	}

	


	

	

	public void setCount(int count) {
		this.count = count;
	}
}