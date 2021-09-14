package com.example.demo.backend2;

import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class SendEmailToClient
{

    public static void setEmail(String func,String to){


        // Put sender’s address
        String from = "ahmedjabareen7@gmail.com";
        final String username = "ahmedjabareen7@gmail.com";
        final String password = "bg5f.u2XaRHFp7U";

        String host = "smtp.gmail.com";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        session.setDebug(true);
        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);
            // Set From: header field
            message.setFrom(new InternetAddress(from));
            // Set To: header field
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            // Set Subject: header field
            int cond = 0;
            if(func.equals("resetMail"))
                cond = 1;
            if (func.equals("forthReg"))
                cond = 2;
            switch(cond) {
                case 0:
                    message.setSubject("Test mail from KIDI");
                    // Put the content of your message
                    message.setText("Hi there, this is a test mail from KIDI");
                    break;
                case 1:
                    message.setSubject("Your new Kidi Password");
                    // Put the content of your message
                    message.setText("You have requested to change your password \n your new " +
                            "password is: "+ generateRandomString() +"\n In case it was not you, please contact the administrator");
                    break;

                case 2:
                    message.setSubject("Welcome to KIDI");
                    // Put the content of your message
                    message.setText("Thank you for joining KIDI");

            }

            // Send message
            System.out.println("Sending msg");
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
public static String generateRandomString()
    {
        int len = 8;
        // ASCII range – alphanumeric (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        // each iteration of the loop randomly chooses a character from the given
        // ASCII range and appends it to the `StringBuilder` instance

        for (int i = 0; i < len; i++)
        {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        //System.out.println(sb.toString());
        return sb.toString();
    }
}
