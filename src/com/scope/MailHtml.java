package com.scope;
import java.util.UUID;
import com.scope.EncryptionDecryption;

import org.hibernate.Session;

import com.model.UpdateAccount;
import com.scope.SendEmail;
public class MailHtml {
	
	 public static String activateAccount(String name, Session session, int userid, String url, String email) {
	        String str = "";
	        String encyptCode = MailHtml.generateCode();
	        str = "<h3>Welcome " + name + "</h3>" + "<p>&nbsp; &nbsp; &nbsp; &nbsp; please activate your account following link.... &nbsp;</p>" + "<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;" + "<a href='http://" + url + "/activation?code=" + encyptCode + "'>Activation Account</a>(expire after 48 hours)</p>" + "<p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p>" + "<p style=\"text-align: center;\">" + "@Blagot2016-Team</p>";
	        if (createUpdateAccount(encyptCode, "account activation link", userid, session)) {
	            new com.scope.SendEmail();
	            str = SendEmail.send(str, "sucuessfull create Blagot new Account", email) ? "account create sucessfull.check your mail to activate your blagot account" : "please try later";
	        } else {
	            str = "mail send error, please try later.";
	        }
	        return str;
	    }

	 private static boolean createUpdateAccount(String code,String comment,int userid,Session session)
	    {
	    	UpdateAccount up=new UpdateAccount();
	    	up.setCode(code);
	    	up.setComment(comment);
	    	up.setUid(userid);
	    	int lon=(int)session.save(up);
	    	System.out.print("lon: "+lon);
	    	 if((lon>0))
	    		 return true;
	    	 else
	    		 
	    		 System.out.println("not sucess");
	    	 return false;
	    }
	
	public static void main(String args[])
	{
		//System.out.println(MailHtml.generateCode());
		String str="<h3>Welcome " +"ajay chaturedi"+"</h3>"+
				"<p>&nbsp; &nbsp; &nbsp; &nbsp; please activate your account following link.... &nbsp;</p>"+
				 "<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;"
				 + "<a href='"
				 +"http://localhost:8080/TestSpring"+"/activation?code="+"TGH5DvVqz/lIKCx7EFMiAc29a0Yj2IqLhuKzsTPbJ5P12ZCA439hciEOqzSAcALiQUo56lti3oaA/DNA4CGybYRdQ74mlwlh8z8mv664RC8="+"'>Activation Account</a>(expire after 48 hours)</p>"+
				"<p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p>"+
				"<p style=\"text-align: center;\">"+"@Blagot2016-Team</p>";
		 System.out.println(SendEmail.send(str, "subject testing", "jychtrvd@gmail.com"));
		
	/*	String uniqueID = UUID.randomUUID().toString().replaceAll("-", "");
		System.out.println(uniqueID);
		try {
			System.out.println(com.scope.EncryptionDecryption.encrypt(uniqueID.toCharArray()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	
	public static final String generateCode()
	{
		String uniqueID =  UUID.randomUUID().toString().replaceAll("-", "");
		
		try {
			return com.scope.EncryptionDecryption.encrypt(uniqueID.toCharArray());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String decrptCode(String code)
	{
		try {
			return EncryptionDecryption.decrypt(code.toCharArray());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static String subscribeBlagot(String name, String email,String msg) {
        String str = "";
        String message="";
        if(msg.equals(""))
        	message="Thank you for connect to www.blagot.com ....";
        else
        	message=msg;
        str = "<h3>Welcome " + name +"</h3>" + "<p>&nbsp; &nbsp; &nbsp; &nbsp; "+message+" &nbsp;</p>"
        + "<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;" 
        + "</p><p style=\"text-align: center;\">" + "@Blagot2017-Team</p>";
            new com.scope.SendEmail();
            str = SendEmail.send(str, "<br>we try to serve our best service<br> for more detail please visit our website:- www.blagot.com thank you.", email) ? "subcription successfull." : "please try later";
        return str;
    }
	
	

}
