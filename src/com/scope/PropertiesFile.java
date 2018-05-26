package com.scope;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class PropertiesFile {

	public static HashMap<String, String> readAttribute(HashMap<String, String> hm) {
		try {
			if (hm.size() > 0) {
				int num = 0;
//				File file = new File("props.properties");
//				FileInputStream fileInput = new FileInputStream(file);

				Properties properties = new Properties();
				properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("trasaction.properties"));
			
//				properties.load(fileInput);
//				fileInput.close();
				Enumeration enuKeys = properties.keys();
				while (enuKeys.hasMoreElements()) {
					num++;
					if (hm.size() >= num) {
						for (String key : hm.keySet()) {
//							System.out.println(key + ":" + hm.get(key));
							hm.replace(key,  properties.getProperty(key));
						}
					} else
						break;


//					String key = (String) enuKeys.nextElement();
//					String value = properties.getProperty(key);
//					System.out.println(key + ": " + value);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return hm;
	}
	public static void main(String[] args) {
//		HashMap<String, String> hm=new HashMap<>();
//		hm.put("payu.salt", "");
//		hm.put("payu.key", "");
//		for (String key : PropertiesFile.readAttribute(hm).keySet()) {
//			System.out.println(key + ":" + hm.get(key));
//			
//		}
		try {
			Properties properties = new Properties();
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("trasaction.properties"));
			Enumeration enuKeys = properties.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				String value = properties.getProperty(key);
				System.out.println(key + ": " + value);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
