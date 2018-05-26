package com.ccavenue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Random;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.cart.OrderTrasaction;
import com.ccavenue.security.AesCryptUtil;
import com.controller.AdminController;
import com.google.gson.JsonObject;
import com.logic.CartLogic;
import com.model.LoginSession;
import com.model.ShippingModel;
import com.scope.DateConvert;
import com.scope.PropertiesFile;
import com.scope.SessionUser;

/**
 * Servlet implementation class ccavenuePayment
 */
@WebServlet("/ccavenuePayment")
public class ccavenuePayment extends HttpServlet {
	final static Logger logger = Logger.getLogger(ccavenuePayment.class);
//	private String merchantId = "130952";
//	private String accessCode = "AVYI70ED30BL87IYLB";
//	private String workingKey = "788561F55D60BFDD004E8D45300F65D9";
	
	private String merchantId = "";
	private String accessCode = "";
	private String workingKey = "";
	private String gatewayUrl="";
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ccavenuePayment() {
		 super();
		// TODO Auto-generated constructor stub
		HashMap<String, String> hm=new HashMap<>();
		hm.put("ccavenue.url", "");
		hm.put("ccavenue.merchantId", "");
		hm.put("ccavenue.accessCode", "");
		hm.put("ccavenue.workingKey", "");
		for (String key : PropertiesFile.readAttribute(hm).keySet()) {
			if(key.equals("ccavenue.url"))
				gatewayUrl=hm.get(key);
			else if(key.equals("ccavenue.merchantId"))
				merchantId=hm.get(key);
			else if(key.equals("ccavenue.accessCode"))
				accessCode=hm.get(key);
			else if(key.equals("ccavenue.workingKey"))
				workingKey=hm.get(key);	
		}		
		
		System.out.println("gatewayUrl:-  "+gatewayUrl+"   merchantId:-"+merchantId+"  accessCode:-"+accessCode+"  workingKey:-"+workingKey);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();

		// Put in the 32 Bit Working Key provided by CCAVENUES.
		String html = "";
		try {
			Enumeration enumeration = request.getParameterNames();
			String ccaRequest = "", pname = "", pvalue = "";
			while (enumeration.hasMoreElements()) {
				pname = "" + enumeration.nextElement();
				pvalue = request.getParameter(pname);
				ccaRequest = ccaRequest + pname + "=" + pvalue + "&";
			}
			AesCryptUtil aesUtil = new AesCryptUtil(workingKey);
			String encRequest = aesUtil.encrypt(ccaRequest);
			html = "<html> <body>"
					+ "<style type=\"text/css\">"
					+ ".loader {"
					+ "  margin-left: 550;"
					+ " margin-top: 200;"
					+ " border: 16px solid #f3f3f3; /* Light grey */"
					+ " border-top: 16px solid #3498db; /* Blue */"
					+ " border-radius: 50%;"
					+ " width: 120px;"
					+ " height: 120px;"
					+ " animation: spin 2s linear infinite;"
					+ " }"
					+ " @keyframes spin {"
					+ "  0% { transform: rotate(0deg); }"
					+ "  100% { transform: rotate(360deg); }"
					+ " }"
					+ " </style>"
					+ "<div class=\"container\">"
					+ "<div class=\"loader\"></div></div>";
			html += "<form id=\"nonseamless\" method=\"post\" name=\"redirect\" action=\""+gatewayUrl+"\"/>";
			html += "<input type=\"hidden\" id=\"encRequest\" name=\"encRequest\" value=\"" + encRequest + "\">";
			html += "<input type=\"hidden\" name=\"access_code\" id=\"access_code\" value=\"" + accessCode + "\">";
			html += "<script language='javascript'>document.redirect.submit();</script>";
			html += "</form>";
			html += "</body></html>";

		} catch (Exception e) {
			logger.error("ccavenuePayment error(119)doPost  " + e.toString());
		}
		System.out.println("redirect ccavenue payment  "+html);
		writer.println(html);
	}

	public static void getResponseValue(HttpServletRequest request, String msg) {
		FileWriter fw = null;
		try {
			String root = request.getServletContext().getRealPath("/");
			File path = new File(root + "/ccav");
			if (!path.exists()) {
				path.mkdirs();
			}
			File file = new File(path + "/user.txt");
			if (!file.exists())
				file.createNewFile(); // the true will append the new data

			fw = new FileWriter(file, true);
			fw.write(msg);// appends the string to the file

		} catch (Exception e) {
			// TODO Auto-generated catch block
			msg += "\n" + e.toString();
			try {
				fw.write(msg);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} // appends the string to the file
		} finally {
			try {
				fw.close();
			} catch (IOException e) {

				logger.error("ccavenuePayment error(154)getResponseValue  " + e.toString());
			}
		}
	}

	public static JsonObject getResponse(HttpServletRequest request) {
		boolean bol=true;
		JsonObject jobj = new JsonObject();
		try {
			Enumeration<String> kayParams = request.getParameterNames();
			String workingKey = "788561F55D60BFDD004E8D45300F65D9";		//32 Bit Alphanumeric Working Key should be entered here so that data can be decrypted.
			String encResp= request.getParameter("encResp");
			AesCryptUtil aesUtil=new AesCryptUtil(workingKey);
			String decResp = aesUtil.decrypt(encResp);
			StringTokenizer tokenizer = new StringTokenizer(decResp, "&");
			String pair=null, pname=null, pvalue=null;
			while (tokenizer.hasMoreTokens()) {
				pair = (String)tokenizer.nextToken();
				if(pair!=null) {
					StringTokenizer strTok=new StringTokenizer(pair, "=");
					pname=""; pvalue="";
					if(strTok.hasMoreTokens()) {
						pname=(String)strTok.nextToken();
						if(strTok.hasMoreTokens())
							pvalue=(String)strTok.nextToken();
						jobj = JsonObjectCcavenue.jobjData(pname, pvalue, jobj);
					}
				}
			}
			jobj.addProperty("payment_mode", "cavenue");
			SessionUser su=new SessionUser();
			if (su.getSession(request, "loginSession")) {
				LoginSession ls = (LoginSession) request.getSession().getAttribute("loginSession");
				jobj.addProperty("firstname", ls.getName());
				jobj.addProperty("lastname", ls.getName());
			}
			else{
				jobj.addProperty("firstname", "");
				jobj.addProperty("lastname", "");
			}
			LoginSession ls = (LoginSession) request.getSession().getAttribute("loginSession");
			String txStatus = jobj.get("order_status").getAsString();
			if (txStatus.equals("Success") | txStatus.equals("\"Success\"")) {
				OrderTrasaction.trasactionUpdate(jobj.get("order_id").toString(), jobj.get("merchant_param1").toString(),
						jobj.toString(), 1);
			} else
				OrderTrasaction.trasactionUpdate(jobj.get("order_id").toString(), jobj.get("merchant_param1").toString(),
						jobj.toString(), 2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("ccavenuePayment error(204)getResponse  " + e.toString());
			getResponseValue(request,e.toString());
		}
		return jobj;
	}


}
