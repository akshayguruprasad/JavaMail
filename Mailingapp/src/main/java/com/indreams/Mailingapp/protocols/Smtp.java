package com.indreams.Mailingapp.protocols;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.indreams.Mailingapp.protocols.interfaces.Mailing;

public class Smtp implements Mailing {

	public void sendMail() {
		String senderMail = "studentportal.manager@gmail.com";
		String senderPassword = "ABC12345six";
		String reciverMail = "akshayguruprasad@gmail.com";
		String protocol = "smtp.gmail.com";
		String port = "465";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", protocol);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		Authenticator auth = new Authenticator() {
		};

		Session session = Session.getDefaultInstance(props, auth);// try without auth
		session.setDebug(true);

		MimeMessage messagePacket = new MimeMessage(session);
		try {
			messagePacket.addRecipient(RecipientType.TO, new InternetAddress(reciverMail));
			messagePacket.setFrom(new InternetAddress(senderMail));
			messagePacket.setText("PFA");
			messagePacket.setSubject("Find the attachement");
			MimeMultipart mp = new MimeMultipart();

			InputStream is = new FileInputStream(new File("/home/bridgeit/Desktop/google.jpg"));
			MimeBodyPart bp = new MimeBodyPart(is);
			
			mp.addBodyPart(bp);

			messagePacket.setContent(mp);

			Transport transport = session.getTransport("smtp");

			transport.connect(senderMail, senderPassword);

			transport.sendMessage(messagePacket, messagePacket.getAllRecipients());

		} catch (Exception e) {
			System.out.println(e.getMessage());

			e.printStackTrace();

		}

	}

}
