package com.scope;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {
	public static boolean send(String msg, String sub, String email) {
		String str = "";
		final String pass = "Mahesh@123";
		final String user = "info@blagot.com";
		String to = email;
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", "smtp.zoho.com");
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.store.protocol", "pop3");
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.debug.auth", "true");
		props.setProperty("mail.pop3.socketFactory.fallback", "false");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, pass);
			}
		});
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom((Address) new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, (Address) new InternetAddress(to));
			message.setSubject(sub);
			message.setContent((Object) msg, "text/html");
			Transport.send((Message) message);
			return true;
		} catch (Exception e) {
			str = String.valueOf(str) + e.toString() + user + pass;
			return false;
		}
	}

	public static void main(String args[]) {
		String str = "<h3>Welcome&nbsp;ajay chaturvedi</h3><p>&nbsp; &nbsp; &nbsp; &nbsp; please activate your account following link.... &nbsp;</p><p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;<a href='http://localhost:8080/chouhanrugs/login?activation=O0xlGtF8Os5M+RxbGaKIjw=='>Activation Account</a>(expire after 48 hours)</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p style=\"text-align: center;\">@chouhanrugs-Team</p>";
		System.out.println(SendEmail.send(str, "subject testing", "kr.maheshsingh@gmail.com"));
	}
}
