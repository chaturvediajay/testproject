package com.html;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONException;
import org.json.JSONObject;

import com.logic.ProductLogic;
import com.nonModel.SlideHome;

public class DescriptionHtml {
	public static String getProductDescription(String pkey, JSONObject obj) {
		String htm = null;
		List<SlideHome> lsh = null;
		boolean bol = false;
		try {
			if (obj.getString("opt").equals("field_update")) {
				bol = updateMrpSMRP(obj);
				bol = true;
			}
			if (bol)
				lsh = ProductLogic.getProductHome("single", pkey,0,0);

			/*---title---*/
			for (SlideHome sh : lsh) {
				htm = "<form method=\"post\">" + "<h3 class=\"product-title\">" + sh.getTitle() + "</h3>"
						+ "<div class=\"rating\">" + "<div class=\"stars\">"
						+ "<span class=\"fa fa-star checked\"></span>" + "<span class=\"fa fa-star checked\"></span>"
						+ " <span class=\"fa fa-star checked\"></span> <span class=\"fa fa-star\"></span>"
						+ "<span class=\"fa fa-star\"></span>" + "</div>"
						+ "<span class=\"review-no\">41 reviews</span>" + "</div>" + "<p class=\"product-description\">"
						+ sh.getDescription() + "</p><h4 class=\"price\">" + "current price: <span>" + sh.getMrp()
						+ "</span>"

						+ "<input id=\"ex1\" type=\"text\" onkeypress=\"return isNumber(event)\"  value=\"0\" size=\"4\">"
						+

						"</h4>" + "<h4 class=\"price\">" + "Selling price: <span>" + sh.getSmrp() + "</span>"
						+ "<input id=\"ex1\" type=\"text\" onkeypress=\"return isNumber(event)\"  value=\"0\" size=\"4\"> "
						+ "</h4>" + "<h5 class=\"sizes\">"
						+ "sizes: <span class=\"size\" data-toggle=\"tooltip\" title=\"" + sh.getSize() + "\">"
						+ sh.getSize() + "</span>" + "</h5>" + "<h5 class=\"colors\">"
						+ "colors: <span class=\"color green not-available\""
						+ " data-toggle=\"tooltip\" title=\"avaiable\">" + sh.getColor() + "</span>" + "</h5>"
						+ "<div class=\"action\">" + "<c:if test=\"${fp.visible==1}\">"
						+ "<input type=\"submit\" name=\"submitButton\""
						+ " class=\"add-to-cart btn\" value=\"Disable\">" + "</c:if>"
						+ "<c:if test=\"${fp.visible==0}\">" + "<input type=\"submit\" name=\"submitButton\""
						+ " class=\"add-to-cart btn\" value=\"Enable\">" + "</c:if>" + "</div>"
						+ "</form><input type=\"submit\" name=\"updateButton\" class=\"btn btn-primary btn-lg\""
						+ " onclick=\"UpdateIdinHF('field_update')\" value=\"update\">";
			}

			System.out.println(lsh);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return htm;
	}
	

	

	private static boolean updateMrpSMRP(JSONObject obj) {
		try {
			Transaction tx = null;
			Session session = null;
			session = com.model.HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query query = session
					.createQuery("update ProductDetail set mrp=:mrp,smrp=:smrp where CONCAT(pkey,pdid)=:pkey");
			query.setParameter("mrp", obj.getString("mrp"));
			query.setParameter("smrp", obj.getString("smrp"));
			query.setParameter("pkey", obj.getString("pkey"));
			System.out.println("update smrp  " + query.executeUpdate());
			tx.commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

}
