package com.scope;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

public class ReadFile {

	public static HashMap<String, String> getState(HttpServletRequest request) {
		InputStream inputStream = null;
		BufferedReader bufferedReader = null;
		Set<String> keySet=null;
		HashMap<String, String> mapState=null;
		try {
			inputStream = request.getServletContext().getResourceAsStream("/WEB-INF/json/my.json");
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

			StringBuilder sb = new StringBuilder();
			String line;
			line = bufferedReader.readLine();
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = bufferedReader.readLine();
			}
			String everything = sb.toString();

			// System.out.println("everything "+everything);

			org.json.simple.JSONObject object = (org.json.simple.JSONObject) org.json.simple.JSONValue
					.parse(everything);
			keySet = object.keySet();
			
			 mapState = new HashMap<String, String>();
			
			
			for (String key : keySet) {
				Object value = object.get(key);
				mapState.put(key, value.toString());
				System.out.println(key + " **:** " + value.toString() + " **:** " + value + "---- " + value.getClass().getSimpleName());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (inputStream != null)
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			if (bufferedReader != null)
				try {
					bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return mapState;
	}
	
	public static HashMap<String, String> getCityPin(HttpServletRequest request,String fileName,String id,String state,String cas) {
		InputStream inputStream = null;
		BufferedReader bufferedReader = null;
		Set<String> keySet=null;
		HashMap<String, String> mapState=null;
		try {
			inputStream = request.getServletContext().getResourceAsStream("/WEB-INF/json/"+fileName);
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

			StringBuilder sb = new StringBuilder();
			String line;
			line = bufferedReader.readLine();
			while (line != null) {
				System.out.println("Line***="+line);
				sb.append(line);
				sb.append(System.lineSeparator());
				line = bufferedReader.readLine();
			}
			String everything = sb.toString();
			if(cas.equals("getCity"))
					{
				 mapState = new HashMap<String, String>();
				 JSONObject jObject  = new JSONObject(everything);
				    JSONObject  menu = jObject.getJSONObject(id);
				    Map<String,String> map = new HashMap<String,String>();
				    Iterator iter = menu.keys();
				    while(iter.hasNext()){
				        String key = (String)iter.next();
				        String value = menu.getString(key);
				        System.out.println(key+" :======== "+value);
				        mapState.put(key,value);
							
			}
			}
			else if(cas.equals("getPin"))
				{
				mapState = new HashMap<String, String>();
				JSONObject jObject  = new JSONObject(everything);
				JSONObject  menu = jObject.getJSONObject(state);
				
				Map<String,String> map = new HashMap<String,String>();
				Iterator iter = menu.keys();
				while(iter.hasNext()){
				   String key = (String)iter.next();
				   String value = menu.getString(key);
				   System.out.println(key+"2 :======== "+value);
				   if(id.equals(key)){
					   String[] str=value.split(",");
					   for(int i=0;i<str.length;i++){
						   mapState.put(str[i],str[i]);
						   System.out.println(" Pin==="+str[i]);
					   }
					  
				   }
					}
			    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (inputStream != null)
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			if (bufferedReader != null)
				try {
					bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return mapState;
	}

}
