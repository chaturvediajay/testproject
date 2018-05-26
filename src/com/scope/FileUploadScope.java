package com.scope;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.util.Iterator;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.*;

public class FileUploadScope {

	public static void getLoad(HttpServletRequest request, String uploadFolder) {
		boolean isMultipart = ServletFileUpload.isMultipartContent((HttpServletRequest) request);
		if (!isMultipart) {
		} else {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			String uploadPath = request.getServletContext().getRealPath("WEB-INF/") +File.separator+ uploadFolder;
			System.out.println("upload path:- "+uploadPath);
			if (folderCreate(uploadFolder)) {
				List items = null;
				try {
					items = upload.parseRequest(request);
				} catch (FileUploadException e) {
					e.printStackTrace();
				}
				Iterator itr = items.iterator();
				while (itr.hasNext()) {
					FileItem item = (FileItem) itr.next();
					if (item.isFormField()) {
					} else {
						try {
							String itemName = item.getName();
							File savedFile = new File(uploadPath + File.separator + itemName);
							item.write(savedFile);
							System.out.println(
									"<tr><td><b>Your file has been saved at the loaction:</b></td></tr><tr><td><b>"
											+ request.getServletContext().getRealPath("/") + "uploadedFiles" + "\\"
											+ itemName + "</td></tr>");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			else
				System.out.println("can't create folder");
		}
	}

	private static boolean folderCreate(String createFolder) {
		File uploadDir = new File(createFolder);
		if (!uploadDir.exists()) {
			return uploadDir.mkdir();
		}
		return false;
	}
}
