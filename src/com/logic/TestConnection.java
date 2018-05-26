package com.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.model.ErrorModel;
import com.model.HibernateUtil;

public class TestConnection {
	static Session session = null;
	static Transaction ts = null;

	public static String errorInfoInsert(String class_name, String description) {
		boolean bol = false;
		String str = null;
		Session session = null;
		Transaction ts = null;
		Integer eId = 0;
		try {

			session = HibernateUtil.getSessionFactory().openSession();
			ts = session.beginTransaction();
			bol = session.isConnected();
			if (bol) {
				ErrorModel info = new ErrorModel();
				info.setClass_name(class_name);
				info.setDescription(description);
				eId = (Integer) session.save(info);
				ts.commit();
				// info=(ErrorModel)session.get(ErrorModel.class,eId);
				str = "Data error submit sucessfully";
			} else
				str = "conection not establish!";

		} catch (Exception e) {
			if (ts != null)
				ts.rollback();
			str = class_name.toString();
		} finally {
			if (session.isConnected()) {
				session.clear();
				session.close();
			}

		}

		return str;
	}

	public static List<ErrorModel> viewErrorAll(int caseId, int condition) {
		List<ErrorModel> lem = null;
		Session session = null;
		Transaction ts = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			 session.beginTransaction();
			boolean bol = session.isConnected();
			System.out.println("Condition:- "+bol);
			String str = "";
			lem = session.createQuery("from ErrorModel m where status=" + condition).list();
			if (bol) {
				switch (caseId) {
				case 1:
					lem = session.createQuery("from ErrorModel m order by m.createDate desc").list();
					break;
				case 0:
					lem = session.createQuery("from ErrorModel m where status=" + condition).list();
					break;
				}

			} else
				str += "conection not establish!";
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lem;
	}

	

	public void doit() throws Exception {

		try {
			File file = new File(System.getProperty("user.dir") + File.separator + "properties" + File.separator
					+ "props.properties");
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();
			Enumeration<Object> enumeration = properties.keys();
			String str = "";
			for (int i = 0; i < properties.size(); i++) {
				String key = (String) enumeration.nextElement();
				String value = properties.getProperty(key);
				if (Integer.parseInt(key) == 2)
					str = value;
				System.out.println(key + ": " + value);
			}
			System.out.println(" page;" + str);

			System.out.println("size:--" + properties.size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String returnPage(int pId, HttpServletRequest request) throws Exception {
		String pageName = "";
		try {
			System.out.println("path2 : "+System.getProperty("user.dir"));
			System.out.println("path1 : "+request.getRealPath("/"));
			File file = new File(request.getRealPath("/")+"/"+File.separator+"properties"+File.separator+"props.properties");
			System.out.println("path:- " + file.getPath());
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();
			Enumeration<Object> enumeration = properties.keys();

			for (int i = 0; i < properties.size(); i++) {
				String key = (String) enumeration.nextElement();
				String value = properties.getProperty(key);
				int key1=Integer.parseInt(key);
				if (key1==pId){
					pageName = value;
					System.out.println(key + ": " + value);
				}
				System.out.println( "pagename : " + pageName+"  "+key.equals(pId)+"  "+key+": "+pId+"   "+(key1==pId));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pageName;
	}
	

}
