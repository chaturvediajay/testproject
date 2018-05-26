package com.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = { "/" })
public class PayUMoneyController {
	final static Logger logger = Logger.getLogger(PayUMoneyController.class);
	@RequestMapping(value="paypal",method=RequestMethod.GET)
	public String getpaypalpayment(HttpServletRequest request){
		String str;
		try {
			str = "";
			Enumeration enumeration = request.getParameterNames();
			String ccaRequest = "", pname = "", pvalue = "";
			while (enumeration.hasMoreElements()) {
				pname = "" + enumeration.nextElement();
				pvalue = request.getParameter(pname);
				
				str+="pname="+pname+" pvalue="+pvalue+"\n";
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("PayUMoneyController error(31)TestPagePost  " + e.toString());
		}
		return "paypal";
	}
	
	@RequestMapping(value="paypal",method=RequestMethod.POST)
	public String postpaypalpayment(HttpServletRequest request){
		
		String str;
		try {
			str = "";
			Enumeration enumeration = request.getParameterNames();
			String ccaRequest = "", pname = "", pvalue = "";
			while (enumeration.hasMoreElements()) {
				pname = "" + enumeration.nextElement();
				pvalue = request.getParameter(pname);
				
				str+="pname="+pname+" pvalue="+pvalue+"\n";
				
			}
		} catch (Exception e) {
			logger.error("PayUMoneyController error(52)postpaypalpayment  " + e.toString());
		}
		
		return "paypal";
	}
	@RequestMapping(value="cancel",method=RequestMethod.GET)
	public String getCancelpaypalpayment(HttpServletRequest request){
		String str="";
		try {
			Enumeration enumeration = request.getParameterNames();
			String ccaRequest = "", pname = "", pvalue = "";
			while (enumeration.hasMoreElements()) {
				pname = "" + enumeration.nextElement();
				pvalue = request.getParameter(pname);
				
				str+="pname="+pname+" pvalue="+pvalue+"\n";
				
			}
		} catch (Exception e) {
			logger.error("PayUMoneyController error(72)getCancelpaypalpayment  " + e.toString()+"\n"+str);
		}
		
		
		return "paypal";
	}

}
