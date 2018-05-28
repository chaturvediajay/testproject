package com.scope;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.derby.tools.sysinfo;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.model.HibernateUtil;

public class AbstractClass {
	// private static Session session = null;
	// private static Transaction transaction = null;
	final static Logger logger = Logger.getLogger(AbstractClass.class);

	public static List<Object> getMenuObj(Object obj, Session session) {
		int i = 0;
		if (session == null) {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			i++;
		}
		List<Object> cat2 = null;
		try {
			cat2 = session.createCriteria(obj.getClass()).list();
		} catch (Exception e) {
			logger.error("abstrack error(42)  " + e.toString());
		} finally {
			if (i > 0)
				session.close();
		}
		return cat2;
	} // user = (User) session.get(User.class, user_id);

	public static Object getClassById(Object obj, int id, Session session) {
		int i = 0;
		if (session == null) {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			i++;
		}
		try {
			obj = session.get(obj.getClass(), id);
			return obj;
		} catch (Exception e) {
			logger.error("abstrack error(61)  " + e.toString());
		} finally {
			if (i > 0)
				session.close();
		}
		return null;
	}

	public static Object getClassByIdWithoutSession(int id, Object obj, Session session, int loop, String condition) {
		Transaction tx = null;
		int i = 0;
		try {
			if (session == null) {
				session = HibernateUtil.getSessionFactory().openSession();
				tx = session.beginTransaction();
				i++;
			}
			switch (condition) {
			case "classById":
				obj = session.get(obj.getClass(), id);
				break;
			case "updateClass":
				session.saveOrUpdate(obj);

				break;
			default:
				break;
			}

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			logger.error("abstrack error(52)  " + e.toString());
		} finally {
			if (session != null) {
				if (loop == 1 & i > 0) {
					if (condition.equals("updateClass")) {
						tx.commit();
						session.saveOrUpdate(obj);
					}
					session.clear();
					session.close();
				}
			}
		}
		return obj;
	}

	public static List<Object> getObjCrtialList(Object obj, HashMap<?, ?> hm, Session session, String dataType,
			int start, int length) {
		Transaction transaction = null;
		List<Object> list = Collections.emptyList();
		if (session == null) {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
		}
		try {
			Criteria criteria = session.createCriteria(obj.getClass());
			for (Map.Entry<?, ?> entry : hm.entrySet()) {
				if (dataType.equals("int"))
					criteria.add(Restrictions.eq((String) entry.getKey(), (int) entry.getValue()));
				else if (dataType.equals("string"))
					criteria.add(Restrictions.eq((String) entry.getKey(), (String) entry.getValue()));
				System.out.println(entry.getKey() + "  " + entry.getValue());
			}
			criteria.setFirstResult(start);
			criteria.setMaxResults(length);

			list = criteria.list();
		} catch (Exception e) {
			logger.error("abstrack error(58)getObjCrtialList:-  " + e.toString());
		} finally {
			if (session != null) {
				session.clear();
				session.close();
			}
		}
		return list;
	}

	public static boolean uniqueSingleClass(HashMap<String, String> myMap, Object obj, Session session) {
		Transaction transaction = null;
		int i = 0;
		if (session == null) {
			session = HibernateUtil.getSessionFactory().openSession();
			i++;
		}
		try {
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(obj.getClass());
			for (Entry<String, String> entry : myMap.entrySet())
				criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
			obj = (Object) criteria.uniqueResult();
			if (obj != null) {
				session.saveOrUpdate(obj);

				return false;
			}
			return true;
		} catch (Exception e) {
			logger.error("abstrack error(160)uniqueSingleClass:-  " + e.toString());
		} finally {
			if (i > 0) {
				session.close();
			}
		}
		return false;
	}

	public static Object uniqueObjectSingleClass(HashMap<?, ?> myMap, Object obj, Session session) {
		// Transaction transaction = null;
		if (session == null) {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
		}
		try {
			Criteria criteria = session.createCriteria(obj.getClass());
			for (Entry<?, ?> entry : myMap.entrySet())
				criteria.add(Restrictions.eq((String) entry.getKey(), entry.getValue()));
			obj = criteria.uniqueResult();
			// transaction.commit();
		} catch (Exception e) {
			logger.error("abstrack error(183)uniqueObjectSingleClass:-  " + e.toString());
		} finally {
			if (session != null)
				session.close();

		}

		return obj;
	}

	public static int saveClass(Object obj, Session session) {
		session = com.model.HibernateUtil.getSessionFactory().openSession();
		Transaction ts = null;
		int i = 0;
		if (session == null) {
			session = HibernateUtil.getSessionFactory().openSession();
			ts = session.beginTransaction();
			i++;
		}
		Integer itemID = 0;
		try {
			itemID = (Integer) session.save(obj);
			ts.commit();
		} catch (Exception e) {
			if (ts != null)
				ts.rollback();
			logger.error("abstrack error(210)saveClass  " + e.toString());
		} finally {
			if (i > 0) {
				session.close();
			}
		}

		return itemID;
	}

	public static List<Object[]> listObj(String sqlQuery) {
		Session session = null;
		if (session == null) {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
		}
		List<Object[]> list = null;
		try {
			Query hSql = session.createSQLQuery(sqlQuery);
			list = hSql.list();
			if (list == null)
				list = Collections.emptyList();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("abstrack obj error(235)listObj  " + e.toString());
		} finally {
			if (session != null) {
				session.clear();
				session.close();
			}

		}
		return list;
	}

	public static List<Object[]> listObj(String sqlQuery, Session session, boolean bol) {
		if (session == null) {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
		}
		List<Object[]> list = null;
		try {
			Query hSql = session.createSQLQuery(sqlQuery);
			list = hSql.list();
			session.flush();
			session.clear();
			if (list == null)
				list = Collections.emptyList();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("abstrack obj error(235)listObj  " + e.toString());
		} finally {
			if (session.isOpen() & bol) {
				session.clear();
				session.close();
			}

		}
		return list;
	}

	public static List<Object[]> listObj(String sqlQuery, boolean bol) {
		Session session = com.model.HibernateUtil.getSessionFactory().openSession();
		Transaction ts = null;
		List<Object[]> list = null;
		try {
			if (session == null) {
				session = HibernateUtil.getSessionFactory().openSession();
				ts = session.beginTransaction();
			}
			Query hSql = session.createSQLQuery(sqlQuery);
			list = hSql.list();
			if (list == null)
				list = Collections.emptyList();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("abstrack error(262)listObj  " + e.toString());
		} finally {
			if (bol)
				if (session != null & bol) {
					session.clear();
					session.close();
				}

		}
		return list;
	}

	public static List<Object[]> listObj(String sqlQuery, int start, int length) {
		Session session = null;
		Transaction ts = null;
		List<Object[]> list = Collections.emptyList();

		try {
			if (session == null) {
				session = HibernateUtil.getSessionFactory().openSession();
				ts = session.beginTransaction();
			}
			Query hSql = null;
			hSql = session.createSQLQuery(sqlQuery);
			hSql.setFirstResult(start);
			hSql.setMaxResults(length);
			list = hSql.list();
			if (list == null)
				list = Collections.emptyList();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("abstrack error(292)listObj  " + e.toString());
		} finally {
			if (session != null) {
				session.clear();
				session.close();
			}

		}
		return list;
	}

	public static String checkImageUrl(HttpServletRequest request, String url) {
		String[] aStr = url.split(",");
		System.out.println("array " + aStr);
		boolean check = false;
		String rurl = "";
		for (int i = 0; i < aStr.length; i++) {
			check = new File(request.getContextPath(), "/" + "").exists();
			if (check) {
				rurl = request.getContextPath() + "/" + aStr[i];
				System.out.println("url" + rurl);
				i = aStr.length;
			}
		}
		return rurl;
	}

	public static int row_Count(String str) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Query query = session.createSQLQuery(str);
			System.out.println(query);
			List listResult = query.list();
			Number nu = (Number) listResult.get(0);
			return nu.intValue();
		} catch (Exception ex) {
			System.out.println(ex.toString());

		} finally {
			session.clear();
		}
		return 0;
	}

}