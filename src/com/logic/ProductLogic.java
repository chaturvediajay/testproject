package com.logic;

import com.model.HibernateUtil;
import com.model.LoginSession;
import com.model.Product;
import com.model.ProductDetail;
import com.model.trasaction_table;
import com.nonModel.SlideHome;
import com.scope.AbstractClass;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.JSONException;
import org.json.JSONObject;

public class ProductLogic {
	public ProductLogic() {
	}

	static final Logger logger = Logger.getLogger(ProductLogic.class);
	

	public static boolean uniqueSingleClass(HashMap<String, String> myMap, Object obj) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(obj.getClass());
			for (Map.Entry<String, String> entry : myMap.entrySet())
				criteria.add(org.hibernate.criterion.Restrictions.eq((String) entry.getKey(), entry.getValue()));
			obj = criteria.uniqueResult();
			System.out.println("obj " + obj);
			if (obj != null)
				return false;
			return true;
		} catch (Exception e) {
			logger.error("ProductLogic error(39)uniqueSingleClass  " + e.toString());
		}
		return false;
	}

	public static boolean product_registration(Product product) {
		boolean bol = false;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			product.setPkey(com.scope.StrongAES.stringIntGen(10, 2));
			bol = ((Integer) session.save(product)).intValue() > 0;
			transaction.commit();
			bol = true;
		} catch (Exception e) {
			logger.error("ProductLogic error(56)product_registration  " + e.toString());
			if (transaction != null)
				transaction.rollback();
			System.out.println(e.toString());
		} finally {
			if (session != null)
				session.close();
		}
		return bol;
	}

	public static Product getByPkey(HttpServletRequest request) {
		Product pro = null;
		String pkey = request.getParameter("pkey").toString();
		String scope = request.getParameter("scope").toString();
		System.out.println("pkey:- " + pkey + " scope:- " + scope + "/" + request.getSession().getAttribute("rand"));
		System.out.println(request.getSession().getAttribute("rand").toString().equals(scope) + "   " + pkey);
		if ((request.getSession().getAttribute("rand").toString().equals(scope) & pkey != null)) {
			String query = "SELECT p.title,(Select menu from menu1 where m1id=p.m1id) as m1,(Select submenu from menu2 where m2id=p.m2id) as m2,(Select submenu from menu3 where m3id=p.m3id) as m3 FROM product p where p.pkey='"
					+

					pkey + "'";

			System.out.println(query);

			Session session = HibernateUtil.getSessionFactory().openSession();
			try {
				session.beginTransaction();

				List<Object[]> list = AbstractClass.listObj(query);
				for (int i = 0; i < list.size(); i++) {
					Object[] row = (Object[]) list.get(i);
					for (int j = row.length - 1; j < row.length; j++) {
						pro = new Product();
						pro.settM3(row[j].toString());
						pro.settM2(row[(j - 1)].toString());
						pro.settM1(row[(j - 2)].toString());
						pro.setTitle(row[(j - 3)].toString());
						pro.setPkey("pkey");
					}

				}

			} catch (Exception e) {
				logger.error("ProductLogic error(101)getByPkey  " + e.toString());
			} finally {
				if (session != null) {
					session.close();
				}
			}
		}
		return pro;
	}

	public static boolean addProduct(HttpServletRequest request) {
		boolean bol = false;
		Session session = null;
		Transaction transaction = null;
		try {
			LoginSession ls = (LoginSession) request.getSession().getAttribute("loginSession");
			if (ls != null & ls.getId() > 0) {
				ProductDetail pd = new ProductDetail();
				pd.setPkey(request.getParameter("pkey"));
				pd.setSize(request.getParameter("size"));
				pd.setColor(request.getParameter("color"));
				pd.setMrp(request.getParameter("mrp"));
				pd.setSmrp(request.getParameter("smrp"));
				pd.setCount(Integer.parseInt(request.getParameter("qty")));
				pd.setDescription(request.getParameter("description"));
				pd.setUid(ls.getId());
				bol = pd.setImgUrl(request, "img");
				if (bol) {
					session = HibernateUtil.getSessionFactory().openSession();
					transaction = session.beginTransaction();
					bol = ((Integer) session.save(pd)).intValue() > 0;
					session.flush();
					session.clear();
					transaction.commit();
					request.getSession().removeAttribute("img");
				}
			}
		} catch (Exception e) {
			logger.error("ProductLogic error(133)addProduct  " + e.toString());
			if (transaction != null)
				transaction.rollback();
		} finally {
			if (session != null)
				session.close();
		}
		return bol;
	}

	public static List<SlideHome> getProductHome(String queryS, String pkey, int start, int length) {
		List<SlideHome> lsh = new java.util.ArrayList();
		String query = null;

		if (queryS.equals("all")) {
			query="SELECT (SELECT count(*) FROM product p join product_detail pd on pd.pkey=p.pkey ) as count,";
			query += " pd.url,group_concat(concat(`size`) separator ',') as size, pd.description, p.title,pd.mrp,pd.smrp,pd.pkey FROM product p join product_detail pd on   pd.pkey=p.pkey group by p.pkey";
		} else if (queryS.equals("single")) {
			query = "SELECT pd.visible,pd.color,pd.url,pd.description,pd.size, p.title,pd.mrp,pd.smrp,pd.pkey FROM product p join product_detail pd on pd.pkey=p.pkey  where CONCAT(p.pkey, pd.pdid)='"
					+ pkey + "' " + " group by p.pkey";
		} else if (queryS.equals("pkey")) {
			query = "SELECT pd.visible,pd.color,pd.url,pd.description,pd.size, p.title,pd.mrp,pd.smrp,pd.pkey FROM product p join product_detail pd on pd.pkey=p.pkey  where p.pkey='"
					+ pkey + "' " + " group by p.pkey";
		} else if ((queryS.equals("slide_top_all")))
			query = "SELECT pd.url,group_concat(concat(`size`) separator ',') as size, pd.description, p.title,pd.mrp,pd.smrp,pd.pkey FROM product p join product_detail pd on   pd.pkey=p.pkey "
					+ " where p.status=1 group by p.pkey";
		Session session = HibernateUtil.getSessionFactory().openSession();
		System.out.println("query===" + query);
		try {
			session.beginTransaction();
			AbstractClass ac=new AbstractClass();
			List<Object[]> list = null;
			if (start == 0 & length == 0)
				list = AbstractClass.listObj(query);
			else
				list = ac.listObj(query, start, length);

			for (int i = 0; i < list.size(); i++) {
				Object[] row = (Object[]) list.get(i);
				for (int j = row.length - 1; j < row.length; j++) {
					SlideHome sh = new SlideHome();
					sh.setPkey(row[j].toString());
					sh.setSmrp(row[(j - 1)].toString());
					sh.setMrp(row[(j - 2)].toString());
					sh.setTitle(row[(j - 3)].toString());
					sh.setSize(row[(j - 4)].toString());
					sh.setDescription(row[(j - 5)].toString());
					sh.setUrl(row[(j - 6)].toString());
					if (!queryS.equals("all") & !queryS.equals("slide_top_all")) {
						sh.setColor(row[(j - 7)].toString());
						sh.setVisible(Integer.parseInt(row[(j - 8)].toString()));
					}
					if(queryS.equals("all"))
						sh.setCount(Integer.parseInt(row[(j - 7)].toString()));

					lsh.add(sh);
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

	public static List<ProductDetail> singleProduct(String pkey) {
		List<ProductDetail> pd = null;
		String query = "SELECT  p.title,pd.mrp,pd.smrp,pd.pkey,pd.description,group_concat(concat(`size`) separator ',') as size FROM product p join product_detail pd on   pd.pkey=p.pkey group by p.pkey";

		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(ProductDetail.class);
			criteria.add(org.hibernate.criterion.Restrictions.eq("pkey", pkey));
			pd = criteria.list();
			session.close();
		} catch (Exception e) {
			logger.error("ProductLogic error(200)singleProduct  " + e.toString());
		}

		return pd;
	}

	public static List<ProductDetail> getColorList(String pKey, String size) {
		String str = "";
		List list = null;
		Transaction tx = null;
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			tx = session.beginTransaction();
			list = new java.util.ArrayList();
			list = session.createQuery("From ProductDetail where pkey='" + pKey + "' and size='" + size
					+ "' and status='0' and visible='1'").list();

			System.out.println("From ProductDetail where pkey='" + pKey + "' and size='" + size
					+ "' and status='0' and visible='1'");
		} catch (Exception e) {
			logger.error("ProductLogic error(219)getColorList  " + e.toString());
			if (tx != null) {
				session.getTransaction().rollback();
			}
			if (session != null) {
				session.getTransaction().commit();
				session.close();
			}
		} finally {
			if (session != null) {
				session.getTransaction().commit();
				session.close();
			}
		}
		return list;
	}

	public static String getProductCount(String pkey, String ppid, String cate, JSONObject obj) {
		int total = 0;
		int count = 0;
		boolean bol = false;

		System.out.println("product detail id :- " + ppid);
		try {
			if (((pkey.length() > 0 ? 1 : 0) & (ppid.length() > 0 ? 1 : 0)) != 0) {
				String query = "select pd.pdid,pd.smrp,pd.mrp,pd.count ,pd.description,(select COALESCE(sum(id.qty),0) from payment_details pd inner join item_details id on id.orderId=pd.orderId  where ppid="
						+

						ppid +

						" and id.pkey='" + pkey + "' and pd.visible=" + 1 + " ) as total," +

						"(pd.count>(select COALESCE(sum(id.qty),0) from payment_details pd "
						+ "inner join item_details id on id.orderId=pd.orderId " + "where id.ppid=" + ppid
						+ "  and id.pkey='" + pkey + "' and pd.visible=" + 1 + ")) as aviable "
						+ "from product_detail pd " +

						"where pd.pkey='" + pkey + "' and pd.pdid=" + ppid
						+ " and pd.count>=(select COALESCE(sum(id.qty),0) from payment_details pd "
						+ "inner join item_details id on id.orderId=pd.orderId " + "where pd.pdid=" + ppid
						+ " and id.pkey='" + pkey + "' and pd.visible=1) ";

				System.out.println(query);

				List<Object[]> list = AbstractClass.listObj(query);
				for (int i = 0; i < list.size(); i++) {
					if (i == 0)
						bol = true;
					Object[] row = (Object[]) list.get(i);
					for (int j = row.length - 1; j < row.length; j++) {
						System.out.println(row[(j - 1)].toString() + " : " + row[(j - 3)].toString());
						total = Integer.parseInt(row[(j - 1)].toString());
						count = Integer.parseInt(row[(j - 3)].toString());
						obj.put("total", total);
						obj.put("count", count);
						obj.put("ppid", ppid);

						obj.put("description", row[(j - 2)].toString());

						obj.put("mrp", row[(j - 4)].toString());
						obj.put("smrp", row[(j - 5)].toString());
					}
				}
			}

			if (!bol) {
				obj.put("total", total);
				obj.put("count", count);
			}
			obj.put("check", count > total);

			System.out.println(obj.toString());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj.toString();
	}

	public static boolean product_Detail_permission(HttpServletRequest request, String pkey,String opt) {
		//String actio = request.getParameter("submitButton");
		LoginSession ls = (LoginSession) request.getSession().getAttribute("loginSession");
		System.out.println("  action:-  " + opt+pkey);
		switch (opt) {

		case "enable":

			return updateProductDetail(pkey, 1, 1, ls);
		case "disable":
			updateProductDetail(pkey, 0, 1, ls);
			return updateProductDetail(pkey, 0, 1, ls);

		default:
			return false;
		}
	}

	public static boolean updateProductDetail(String pkey, int opt, int pdid, LoginSession ls) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {

			String hqlUpdate = "update ProductDetail pd set ";

			if (ls.getAuthorize() == 1) {
				hqlUpdate += "pd.visible = :visibl where CONCAT(pd.pkey, pd.pdid)= '" + pkey + "'";
				int updatedEntities = session.createQuery(hqlUpdate).setInteger("visibl", opt).executeUpdate();
			}

			return true;
		} catch (Exception ex) {
			System.out.println(ex.toString());
			if (tx != null) {
				session.getTransaction().rollback();
			}
			if (session != null) {
				session.getTransaction().commit();
				session.close();
			}
			System.out.println("give permission error:- " + ex.toString());
		} finally {
			if (session != null) {
				tx.commit();
				session.close();
			}
		}
		return false;
	}

	public static void main(String[] args) {
		//System.out.println(getColorList("pp3355241260", "x").size());
		
		//List<SlideHome> sm= ProductLogic.getProductHome("slide_top_all", "");
		
	//	System.out.println("List Size= "+sm.size());
	}
}