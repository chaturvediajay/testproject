package com.html;

import javax.servlet.http.HttpServletRequest;

public class ErrorHtml {

	public static String errorGet404(HttpServletRequest request) {
		String str = "<div class=\"span12\">" 
				+"<div class=\"row\">"
		        +"<div class=\"col-md-12\">"
		        +"   <div class=\"error-template\">"
		        +"       <h1 style=\"text-align: -webkit-center;\">"
		          +"          Oops!</h1>"
		          +"      <h2 style=\"text-align: -webkit-center;\">"
		         +"          Cart is empty!</h2>"
		          +"      <div class=\"error-details\" style=\"text-align: -webkit-center;\">"
		          +"         Sorry, unable to proceed next!"
		           +"     </div>"
		           +"     <div class=\"error-actions\">"
		           +"         <a href=\""+request.getContextPath()+"/\" class=\"btn btn-primary center-block\" style=\"width: 150px;\">"
		           +"        <span class=\"glyphicon glyphicon-home\"></span>Take Me Home </a>"
		           +"    </div>"
		           +" </div>"
		       +" </div>"
		   +" </div>";
		return str;
	}

}


