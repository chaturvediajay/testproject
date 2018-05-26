package com.scope;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.model.LoginSession;

public class SessionUser {
	private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	private static final int RANDOM_STRING_LENGTH = 10;

	public void setSession(HttpServletRequest request, String name, String value) {
		if (value == null) {
			value = SessionUser.generateRandomString();
		}
		HttpSession session = request.getSession(true);
		session.setAttribute(name, (Object) value);
	}

	public void setSessionArry(HttpServletRequest request, String name, ArrayList<String> arrlist) {
		// Get the session - if no session exists create one
		HttpSession session = request.getSession(true);
		// Set some attribute values to the session
		session.setAttribute(name, arrlist);

	}

	public void setSessionArry(HttpServletRequest request, String name, HashMap<?, ?> hm) {
		HttpSession session = request.getSession(true);
		session.setAttribute(name, hm);
	}

	public void setSessionObject(HttpServletRequest request, String name, Object obj) {
		// Get the session - if no session exists create one
		HttpSession session = request.getSession(true);
		// Set some attribute values to the session
		session.setAttribute(name, obj);

	}

	public static Object getSessionObject2(HttpServletRequest request, String name) {
		Object obj = null;
		HttpSession session = request.getSession();
		if (session != null) {
			if (session.getAttribute(name) != null) {
				obj = (Object) session.getAttribute(name);

			}
		}

		return obj;
	}
	
	public static List<Object> getSessionObjectList(HttpServletRequest request, String name) {
		List<Object> obj = null;
		HttpSession session = request.getSession();
		if (session != null) {
			if (session.getAttribute(name) != null) {
				obj = (List<Object>) session.getAttribute(name);

			}
		}

		return obj;
	}

	public static String[] getSessionObject(HttpServletRequest request, String name) {
		HttpSession session = request.getSession();
		// LoginSession ls;
		if (session != null) {
			if (session.getAttribute(name) != null) { // ls=(LoginSession)session.getAttribute(name);
				Object obj = (Object) session.getAttribute(name);
				// ls=(LoginSession) obj;

				// String[] arrStr=new String[]{Integer.toString(ls.getId()),
				// ls.getName(),ls.getEmail(),Integer.toString(ls.getAuthorize())};
				// String tSObjectArray[]= (String[])obj;
				String str = obj.toString();
				String[] parts = str.split(",");
				return parts;
			} else
				return null;

		} else
			return null;

	}

	public String getSessionValue(HttpServletRequest request, String name) {
		HttpSession session = request.getSession(true);
		if (session != null) {
			if (session.getAttribute(name) != null) {
				return session.getAttribute(name).toString();
			}
			return null;
		}
		return null;
	}

	public HashMap<Integer, ArrayList<String>> getSessionHashmap(HttpServletRequest request, String name) {
		HttpSession session = request.getSession(true);
		if (session != null) {
			if (session.getAttribute(name) != null) {
				return (HashMap<Integer, ArrayList<String>>) (session.getAttribute(name));
			}
			return null;
		}
		return null;
	}

	public ArrayList<String> getSessionValueArray(HttpSession session, String name) {
		if (session != null) {
			if (session.getAttribute(name) != null)
				return (ArrayList<String>) (session.getAttribute(name));
			else
				return null;

		} else
			return null;

	}

	public static String generateRandomString() {
		StringBuffer randStr = new StringBuffer();
		int i = 0;
		while (i < 10) {
			int number = SessionUser.getRandomNumber();
			char ch = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".charAt(number);
			randStr.append(ch);
			++i;
		}
		return randStr.toString();
	}

	private static int getRandomNumber() {
		int randomInt = 0;
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".length());
		if (randomInt - 1 == -1) {
			return randomInt;
		}
		return randomInt - 1;
	}

	public static void sessionDestroy(String[] arrName, HttpSession session) {
		String arr[] = Keyword.arrSession;
		boolean bol = Arrays.asList(arrName).contains("all");
		if (bol) {
			session.invalidate();
		} else {
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arrName.length; j++) {
					String str = arrName[j];
					if (arr[i].equals(str)) {
						session.removeAttribute(str);
					}
				}
			}
		}
	}

	public boolean getSessionArray(HttpServletRequest request, String name) {
		HttpSession session = request.getSession(true);
		if (session.getAttribute(name) != null) {
			String arr[] = (String[]) session.getAttribute(name);
			if (arr.length > 0)
				return true;
		}
		return false;

	}

	public static int getUserId(HttpSession session) {
		int i = 0;
		try {
			if (session.getAttribute("uid") != null)
				i = Integer.parseInt(session.getAttribute("uid").toString());
		}

		catch (Exception e) {
			return i;
		}
		return i;
	}

	public void setSessionHM(HttpServletRequest request, String name, HashMap<?, ?> hm) {
		// Get the session - if no session exists create one
		HttpSession session = request.getSession(true);
		// Set some attribute values to the session
		session.setAttribute(name, hm);

	}
	public void setSessionArrList(HttpServletRequest request, String name, ArrayList<?> hm) {
		// Get the session - if no session exists create one
		HttpSession session = request.getSession(true);
		// Set some attribute values to the session
		session.setAttribute(name, hm);

	}
	public void setSessionListObject(HttpServletRequest request, String name, Object lcm) {
		// Get the session - if no session exists create one
		HttpSession session = request.getSession(true);
		// Set some attribute values to the session
		session.setAttribute(name, lcm);

	}

	public boolean getSession(HttpServletRequest request, String name) {
		HttpSession session = request.getSession(true);
		if (session != null) {
			if (session.getAttribute(name) == null)
				return false;
			else
				return true;

		} else
			return false;

	}

	public static void main(String[] a) {
		SessionUser msr = new SessionUser();
		System.out.println(msr.generateRandomString());
		System.out.println(msr.generateRandomString());
		System.out.println(msr.generateRandomString());
		System.out.println(msr.generateRandomString());
		System.out.println(msr.generateRandomString());
		System.out.println(msr.generateRandomString());
		System.out.println(msr.generateRandomString());
	}
}