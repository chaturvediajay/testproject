//package com.servlet;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//import java.util.Collections;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.hibernate.Query;
//import org.hibernate.Session;
//
//import com.abstrac.AbstractClass;
//import com.model.HibernateUtil;
//import com.scope.ResizeImg;
//
//@WebServlet("/BackUpFIleServlet")
//public class BackUpFIleServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	public BackUpFIleServlet() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		String error = "";
//		try {
//			File uploadedFile = new File(System.getProperty("catalina.base") + "/tmp_img/");
//			if (uploadedFile.exists()) {
//				String folderName[] = { "smaller", "fixed", "backup" };
//
//				/*--- get root path ---- */
//				String root = getServletContext().getRealPath("/");
//
//				/*--- create folder and sub-folder ---- */
//				folderName(folderName, root, "img");
//
//				/* split img */
//
//				String img_split[] = getFileNameString().split(",");
//
//				for (String spl : img_split) {
//					spl = spl.replace("upload/", "");
//
//
//					File sml = new File(root + "/img/smaller/" + spl);
//					File fix = new File(root + "/img/fixed/" + spl);
//
//					if (!sml.exists()) {
//						ResizeImg.resize(uploadedFile + "/" + spl, sml.getPath(), 100, 100);
//					}
//
//					if (!fix.exists()) {
//						ResizeImg.resize(uploadedFile + "/" + spl, fix.getPath(), 440, 600);
//					}
//
//					error += root + "/img/smaller--fixed/" + spl + "\n";
//				}
//
//			}
//			error += "all request are finish" + "\n";
//
//		} catch (Exception e) {
//			error += e.toString() + "\n";
//		} finally {
//			resp.setContentType("text/html");
//			PrintWriter out = resp.getWriter();
//			out.println("<HTML><HEAD><TITLE>Hello World!</TITLE>" + "</HEAD><BODY>" + error + "</BODY></HTML>");
//			out.close();
//		}
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//	}
//
//	private static void folderName(String subFolder[], String spath, String folder) {
//		File path = new File(spath + "/" + folder);
//		if (!path.exists()) {
//			path.mkdirs();
//		}
//		if (path.exists()) {
//			for (int i = 0; i < subFolder.length; i++) {
//				path = new File(spath + "/" + folder + "/" + subFolder[i]);
//				if (!path.exists()) {
//					path.mkdirs();
//				}
//			}
//		}
//	}
//
//	private static String getFileNameString() {
//		Session session = null;
//		String qu = "SELECT pi.url FROM product_img pi";
//
//		List<Object[]> list = AbstractClass.listObj(qu);
//		qu = "";
//
//		for (int i = 0; i < list.size(); i++) {
//			qu += list.get(i);
//		}
//		list = AbstractClass.listObj("SELECT url FROM menu2");
//		for (int i = 0; i < list.size(); i++) {
//			if (!"comingsoon.jpg".equals("" + list.get(i)))
//
//				qu += list.get(i) + ",";
//		}
//		qu += "comingsoon.jpg" + ",";
//
//		return qu;
//	}
//
//	public static void main(String[] args) {
//		System.out.println(getFileNameString());
//	}
//}