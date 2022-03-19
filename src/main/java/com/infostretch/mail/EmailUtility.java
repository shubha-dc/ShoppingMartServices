package com.infostretch.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtility {

	final static String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

	final static String from = "shubhdeepak143@gmail.com";

	static String username = "shubhdeepak@gmail.com";//
	static String password = "";

	private static Properties getConfigurationProps() {

		// Get the session object
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.store.protocol", "pop3");
		props.put("mail.transport.protocol", "smtp");

		return props;

	}

	public static void sendEmail(String subject, String emailBody, String emailAddress) {

		try {
			Session session = Session.getDefaultInstance(getConfigurationProps(), new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			// compose the message
			try {
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(from));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
				message.setSubject(subject);
				message.setText(emailBody);

				// Send message
				Transport.send(message);
				System.out.println("message sent successfully!!!!!!!!!!!!!");

			} catch (MessagingException mex) {
				mex.printStackTrace();
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
