package application;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class JavaMail {

	/**
	 * Methode für das Versenden der Reservationsbestätigung, welche in der Klasse
	 * AutoReservieren aufgerufen wird
	 */
	public static void sendReservationsbestaetigung(String empfaenger, int reservationsnummer) {
		String to = empfaenger;

		String from = "leihautofirma@gmail.com";

		final String username = "leihautofirma@gmail.com";
		final String password = "Illenai183";

		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			message.setSubject("Reservationsbestaetigung");

			BodyPart messageBodyPart = new MimeBodyPart();

			messageBodyPart.setText("Anbei finden Sie die Reservationsbestätigung.");

			Multipart multipart = new MimeMultipart();

			multipart.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();
			String filename = reservationsnummer + "_Reservationsdokument.docx";
			DataSource source = new FileDataSource(
					"C:\\Users\\ninos\\OneDrive\\Desktop\\Leihautodocs\\Reservationsdocs\\" + filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Methode für das Versenden der Abschlussbestätigung, welche in der Klasse
	 * ReservationAbschliessen aufgerufen wird
	 */
	public static void sendAbschlussbestaetigung(String empfaenger, int reservationsnummer) {
		String to = empfaenger;

		String from = "leihautofirma@gmail.com";

		final String username = "leihautofirma@gmail.com";
		final String password = "Illenai183";

		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			message.setSubject("Abschlussbestaetigung");

			BodyPart messageBodyPart = new MimeBodyPart();

			messageBodyPart.setText("Anbei finden Sie die Abschlussbestätigung.");

			Multipart multipart = new MimeMultipart();

			multipart.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();
			String filename = reservationsnummer + "_Abschlussdokument.docx";
			DataSource source = new FileDataSource(
					"C:\\Users\\ninos\\OneDrive\\Desktop\\Leihautodocs\\Abschlussdocs\\" + filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Methode für das Versenden des Passworts, welche in der Klasse PasswordForgot
	 * aufgerufen wird
	 */
	public static void sendPasswortVergessen(String empfaenger, String passwort) {
		String to = empfaenger;

		String from = "leihautofirma@gmail.com";

		final String username = "leihautofirma@gmail.com";
		final String password = "Illenai183";

		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			message.setSubject("Passwort vergessen");

			message.setText("Ihr Passwort lautet: " + passwort);

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}