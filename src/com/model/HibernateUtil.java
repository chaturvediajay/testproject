/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import org.hibernate.cfg.AnnotationConfiguration;

import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author ajay
 */
public class HibernateUtil {

	 private static final SessionFactory sessionFactory;
	
	 static {
	 try {
	 // Create the SessionFactory from standard (hibernate.cfg.xml)
	 // config file.
	 sessionFactory = new
	 AnnotationConfiguration().configure().buildSessionFactory();
	 } catch (Throwable ex) {
	 // Log the exception.
	 System.err.println("Initial SessionFactory creation failed." + ex);
	 throw new ExceptionInInitializerError(ex);
	 }
	 }
	
	 public static SessionFactory getSessionFactory() {
	 return sessionFactory;
	 }
}

//	private static final SessionFactory sessionFactory = buildSessionFactory();

//	private static SessionFactory buildSessionFactory() {
//	    try {
//
//	        Properties dbConnectionProperties = new Properties();
//	        try {
//	            dbConnectionProperties.load(HibernateUtil.class.getClassLoader().getSystemClassLoader().getResourceAsStream("jdbc.properties"));
//	        } catch(Exception e) {
//	            e.printStackTrace();
//	            // Log
//	        }           
//
//	       AnnotationConfiguration().mergeProperties(dbConnectionProperties).configure("hibernate.cfg.xml").buildSessionFactory();          
//
//
//	        
//	private static final SessionFactory sessionFactory;
//	        static {
//	          try {
//	              // Create the SessionFactory from standard (hibernate.cfg.xml) 
//	              // config file.
//	        	  Properties dbConnectionProperties = new Properties();
//	        	  dbConnectionProperties.load(HibernateUtil.class.getClassLoader().getSystemClassLoader().getResourceAsStream("jdbc.properties"));
//	              sessionFactory = new  AnnotationConfiguration().mergeProperties(dbConnectionProperties).configure("hibernate.cfg.xml").buildSessionFactory();  
//	          } catch (Throwable ex) {
//	              // Log the exception. 
//	              System.err.println("Initial SessionFactory creation failed." + ex);
//	              throw new ExceptionInInitializerError(ex);
//	          } 
	        
	        
	        
	        
	        
	        
	        
//	    } catch (Throwable ex) {
//	        ex.printStackTrace();
//	            throw new ExceptionInInitializerError(ex);
//	    }
//	        }
//	    return sessionFactory;
//	}
//
//	public static SessionFactory getSessionFactory() {
//		return sessionFactory;
//	}
//
//}
