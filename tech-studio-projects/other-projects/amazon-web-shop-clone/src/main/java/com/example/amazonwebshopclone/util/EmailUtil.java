package com.example.amazonwebshopclone.util;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

/**
 * Utility class for sending emails.
 */
public class EmailUtil {

    private final String host;
    private final String port;
    private final String username;
    private final String password;

    /**
     * Constructs an EmailUtil instance with SMTP server details.
     *
     * @param host     SMTP server host
     * @param port     SMTP server port
     * @param username SMTP username
     * @param password SMTP password
     */
    public EmailUtil(String host, String port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public static boolean isValidEmail(String email) {
        return false;
    }

    /**
     * Sends an email with the specified details.
     *
     * @param to      Recipient's email address
     * @param subject Email subject
     * @param body    Email body
     * @throws MessagingException if an error occurs while sending the email
     */
    public void sendEmail(String to, String subject, String body) throws MessagingException {
        // Set email properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Create a session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        // Create email message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(body);

        // Send email
        Transport.send(message);
    }
}
