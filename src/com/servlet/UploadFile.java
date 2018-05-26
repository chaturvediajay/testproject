package com.servlet;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.scope.ResizeImg;
import com.scope.SessionUser;
import javax.imageio.ImageIO;

@WebServlet("/UploadFile")
public class UploadFile<E> extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SessionUser su = new SessionUser();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadFile() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(request.getParameter("cate"));
		if (request.getParameter("cate") != null) {
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (isMultipart) {
				// Create a factory for disk-based file items
				FileItemFactory factory = new DiskFileItemFactory();
				// Create a new file upload handler
				ServletFileUpload upload = new ServletFileUpload(factory);
				try {
					// Parse the request
					@SuppressWarnings("unchecked")
					List /* FileItem */<E> items = (List<E>) upload.parseRequest(request);
					Iterator<E> iterator = items.iterator();
					while (iterator.hasNext()) {
						FileItem item = (FileItem) iterator.next();
						if (!item.isFormField()) {
							String fileName = item.getName(); // --get file name
							String root = getServletContext().getRealPath("/");
							// String root =
							// System.getProperty("catalina.base");

							File path = new File(root + "/temp");
							if (!path.exists())
								path.mkdirs();
							path = new File(root + "/temp/img");
							if (!path.exists())
								path.mkdirs();

							String fName = su.generateRandomString();
							File uploadedFile = new File(path + "/" + fName + getFileExtension(item));
							// BufferedImage bimg = ImageIO.read(uploadedFile);
							/* resize image */

							BufferedInputStream is = new BufferedInputStream(item.getInputStream());
							BufferedImage image = ImageIO.read(is);

							response.setContentType("text/html");
							response.setCharacterEncoding("utf-8");
							PrintWriter out = response.getWriter();
							// create Json Object
							JSONObject json = new JSONObject();
							item.write(uploadedFile);
							json.put("url", new Gson().toJson(new com.json.UploadFile().addUrlImage(request,
									fName + getFileExtension(item), request.getParameter("cate"))));
							out.print(json);

							// ResizeImg.imgsizeConvertor(root, fName +
							// getFileExtension(item),item);
						}
					}
				} catch (FileUploadException e) {
					// e.printStackTrace();
				} catch (Exception ex) {
					Logger.getLogger(UploadFile.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
	}

	public static boolean saveImgInFolder(HttpServletRequest request, String image_name) {
		try {
			String root = request.getServletContext().getRealPath("/");
			return ResizeImg.imgsizeConvertor(root, image_name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private String getFileExtension(FileItem file) {
		String fileName = file.getName();
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return "." + fileName.substring(fileName.lastIndexOf(".") + 1);
		else
			return "";
	}

}