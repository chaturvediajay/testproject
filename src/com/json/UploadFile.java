package com.json;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.scope.SessionUser;

public class UploadFile {
	private SessionUser su = new SessionUser();

	public ArrayList<String> addUrlImage(HttpServletRequest request, String url, String cate) {
		HttpSession session = request.getSession();
		ArrayList<String> arrlist = null;
		if (cate.equals("multiple")) {
			if (session.getAttribute("img") != null) {
				arrlist = (su.getSessionValueArray(session, "img"));
				arrlist.add(url);

				session.setAttribute("img", arrlist);
				return arrlist;
			} else {
				arrlist = new ArrayList<>();
				arrlist.add(url);
				session.setAttribute("img", arrlist);
				return arrlist;
			}
		} else if (cate.equals("single")) {
			arrlist = new ArrayList<>();
			arrlist.add(url);
			session.setAttribute("img", arrlist);
			return arrlist;

		}
		return arrlist;

	}

}
