package com.logic;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.model.HibernateUtil;
import com.model.Menu1;
import com.model.Menu2;
import com.model.Menu3;
import com.scope.AbstractClass;

public class MenuLogic {
	
	private List<Menu1> lmenu1;
	private List<Menu2> lmenu2;
	private List<Menu3> lmenu3;
	
	private String root;

	final static Logger logger = Logger.getLogger(MenuLogic.class);
	public static List<Menu1> getMenuObj(Object obj, int num) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Menu1> menul=null;
		System.out.println("  "+obj+"  "+num);
		try {
			Criteria criteria = session.createCriteria(Menu1.class);
			menul = criteria.list();
//			for(Menu1 m1:menul)
//				System.out.println(" Menu Name= "+m1.getMenu());
			session.getTransaction().commit();
		} catch (HibernateException e) {
			logger.error("MenuLogic error(36)getMenuObj  " + e.toString());
			session.getTransaction().rollback();
		}
		return menul;
	}
	public static void main(String[] args) {
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		
//		System.out.println(session.isConnected());
//		session.close();
		
		MenuLogic.getMenuObj((Object) new Menu1(), 0);
	}
	
	
	
	
	public static String addMenus(int m1id, int m2id, String title, String categories) {
		String str;
		str = "";
		Session session = null;
		Transaction transaction = null;
		try {
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				transaction = session.beginTransaction();
				if (categories.equals("menu1")) {
					if (MenuLogic.uniqueTitle("menu1", session, title)) {
						Menu1 cate = new Menu1();
						cate.setMenu(title);
						str = (Integer) session.save((Object) cate) > 0 ? String.valueOf(str) + "Menu add sucessfully"
								: String.valueOf(str) + "Menu:- please try after some time";
					} else {
						str = String.valueOf(str) + "Menu:- already Menu in list";
					}
				} else if (categories.equals("menu2")) {
					if (MenuLogic.uniqueTitle("menu2", session, title)) {
						Menu2 company = new Menu2();
						company.setSubmenu(title);
						company.setM1id(m1id);
						// System.out.println(title+" "+m1id);
						str = (Integer) session.save((Object) company) > 0
								? String.valueOf(str) + "SubMenu add sucessfully"
								: String.valueOf(str) + "SubMenu:- please try after some time";
					} else {
						str = String.valueOf(str) + "SubMenu:- already SubMenu in list";
					}
				} else if (categories.equals("menu3")) {
					if (MenuLogic.uniqueTitle("menu3", session, title)) {
						Menu3 model = new Menu3();
						model.setSubmenu(title);
						model.setM2id(m2id);
						str = (Integer) session.save((Object) model) > 0
								? String.valueOf(str) + "Sub-SubMenu add sucessfully"
								: String.valueOf(str) + "Sub-SubMenu:- please try after some time";
					} else {
						str = String.valueOf(str) + "Sub-SubMenu:- already Sub-SubMenu in list";
					}
				}
				if (!transaction.wasCommitted()) {
					transaction.commit();
				}
			} catch (Exception ex) {
				str = String.valueOf(str) + "categories add exception:- " + ex.toString();
				if (transaction != null) {
					transaction.rollback();
				}
				if (session != null) {
					session.close();
				}
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return str;
	}
	public static Map<String, String> getMenu2Bym1id(int id, String cateName) {
		LinkedHashMap<String, String> options;
		options = new LinkedHashMap<String, String>();
		try {
			try {
				HashMap<String, Integer> hm = new HashMap<>();
				if (cateName.equals("menu1")) {
					hm.put("m1id", id);
					List<Menu2> menu2 = (List<Menu2>) ((Object) MenuLogic.getMenuObjCrtial(new Menu2(), hm));
					for (Menu2 cat : menu2) {
						options.put("" + cat.getM2id(), cat.getSubmenu());
					}

				} else if (cateName.equals("menu2")) {
					hm.put("m2id", id);
					List<Menu3> menu3 = (List<Menu3>) ((Object) MenuLogic.getMenuObjCrtial(new Menu3(), hm));
					for (Menu3 cat : menu3) {
						options.put("" + cat.getM3id(), cat.getSubmenu());
					}
				}
				options.put("0", "Select");
			} catch (Exception ex) {
				System.out.println("getMenu2Bym1id(com.menu) :- " + ex.toString());
			}
		} finally {
		}
		return options;
	}
	public static List<Object> getMenuObjCrtial(Object obj, HashMap<?, ?> hm) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Object> list = Collections.emptyList();
		try {
			Transaction transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(obj.getClass());
			for (Map.Entry<?, ?> entry : hm.entrySet()) {
				criteria.add(Restrictions.eq((String) entry.getKey(), (int) entry.getValue()));
				System.out.println(entry.getKey() + "  " + entry.getValue());
			}
			list = criteria.list();
		} catch (HibernateException e) {
			TestConnection.errorInfoInsert("com.logic.getMenuObjCrtial ", e.toString());
		} finally {
			session.close();
		}
		return list;
	}
	
	private static boolean uniqueTitle(String categories, Session session, String title) {
		String query = "";
		try {
			if (categories.equals("menu1")) {
				query = "SELECT COUNT(*) FROM  Menu1 where menu='" + title + "'";
			} else if (categories.equals("menu2")) {
				query = "SELECT COUNT(*) FROM  Menu2 where submenu='" + title + "'";
			} else if (categories.equals("menu3")) {
				query = "SELECT COUNT(*) FROM  Menu3 where submenu='" + title + "'";
			}
			if ((Long) session.createQuery(query).uniqueResult() > 0) {
				return false;
			}
		} catch (Exception ex) {
			System.out.println("uniqure title: " + ex.toString());
		}
		return true;
	}
	public void getAllMwnu(MenuLogic m1l) {
		List<Object[]> lM1 = m1l.getHomeMenu(Menu1.class, "m1");
		List<Object[]> lM2 = m1l.getHomeMenu(Menu2.class, "m2");
		List<Object[]> lM3 = m1l.getHomeMenu(Menu3.class, "m3");

		m1l.retriveMenuList(lM1, "m1");
		m1l.retriveMenuList(lM2, "m2");
		m1l.retriveMenuList(lM3, "m3");

	}
	private List<Object[]> getHomeMenu(Object obj, String cse) {
		String query = "";
		List<Object[]> list = null;
		try {
			/* create query */
			switch (cse) {
			case "m1":
				query = "select * from menu1";
				// lmenu1 = new ArrayList<>();
				list = AbstractClass.listObj(query);
				break;
			case "m2":
				query = "select m2id,m1id,status,submenu from menu2";
				list = AbstractClass.listObj(query);
				break;
			case "m3":
				query = "select m3.m3id, m3.m2id, m3.status, m3.submenu from menu3 m3" + " inner join menu2 m2"
						+ " on m2.m2id=m3.m2id" + " inner join menu1 m1" + " on m2.m1id=m1.m1id";
				list = AbstractClass.listObj(query);
				break;
			}
		} catch (Exception e) {
			logger.error("Menu1logic error(161)getHomeMenu  " + e.toString());
		}
		return list;
	}
	private void retriveMenuList(List<Object[]> list, String cse) {
		/* convert to object */
		if (list.size() > 0) {
			switch (cse) {
			case "m1":
				lmenu1 = new ArrayList<>();
				break;

			case "m2":
				lmenu2 = new ArrayList<>();
				break;
			case "m3":
				lmenu3 = new ArrayList<>();
				break;
			}
		}

		/* menu get and convert to list */
		for (int i = 0; i < list.size(); i++) {
			Object[] row = (Object[]) list.get(i);
			for (int j = row.length - 1; j < row.length; j++) {
				/* get data */
				switch (cse) {
				case "m1":
					Menu1 menu1 = new Menu1();
					menu1.setStatus(Boolean.parseBoolean(row[j].toString()));
					menu1.setMenu(row[j - 1].toString());
					menu1.setM1id(Integer.parseInt(row[j - 2].toString()));
					lmenu1.add(menu1);
					break;
				case "m2":
					Menu2 menu2 = new Menu2();
					menu2.setSubmenu(row[j].toString());
					menu2.setStatus(Boolean.parseBoolean(row[j - 1].toString()));
					menu2.setM1id(Integer.parseInt(row[j - 2].toString()));
					menu2.setM2id(Integer.parseInt(row[j - 3].toString()));
//					root = root + "/img/fixed/" + row[j - 4].toString().replace("upload/", "");
//					File f = new File(root);
//					if (f.exists())
//						menu2.setUrl(row[j - 4].toString().replace("upload/", ""));
//					else
//						menu2.setUrl("comingsoon.jpg");

					lmenu2.add(menu2);
					root = "";
					break;
				case "m3":
					Menu3 menu3 = new Menu3();
					menu3.setSubmenu(row[j].toString());
					menu3.setStatus(Boolean.parseBoolean(row[j - 1].toString()));
					menu3.setM2id(Integer.parseInt(row[j - 2].toString()));
					menu3.setM3id(Integer.parseInt(row[j - 3].toString()));
					lmenu3.add(menu3);
					break;
				}
			}
		}
		/* if menu get null */
		switch (cse) {
		case "m1":
			if (lmenu1 == null)
				lmenu1 = Collections.emptyList();
			break;

		case "m2":
			if (lmenu2 == null)
				lmenu2 = Collections.emptyList();
			break;
		case "m3":
			if (lmenu3 == null)
				lmenu3 = Collections.emptyList();
			break;
		}

	}
	
	
	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public List<Menu1> getLmenu1() {
		return lmenu1;
	}

	public List<Menu2> getLmenu2() {
		return lmenu2;
	}

	public List<Menu3> getLmenu3() {
		return lmenu3;
	}

}
