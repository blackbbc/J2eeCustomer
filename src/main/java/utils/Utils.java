package utils;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletContext;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by sweet on 15-6-12.
 */
public class Utils {

    @Autowired
    private ServletContext servletContext;

    public Utils(){}

    public boolean saveAvatarImage(String imgData, String fileName) {
        try {
            byte[] imageDataBytes = Base64.decodeBase64(imgData);
            FileOutputStream file1 = new FileOutputStream(servletContext.getInitParameter("root")+"images/uploads/users/"+fileName);
            FileOutputStream file2 = new FileOutputStream(servletContext.getInitParameter("source")+"images/uploads/users/"+fileName);
            file1.write(imageDataBytes);
            file2.write(imageDataBytes);
            file1.close();
            file2.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void sendMail(String email, String token) {
        new mailThread(email, token).run();
    }

    public void sendForgetMail(String email, String token) {
        new forgetMailThread(email, token).run();
    }


    private boolean toSendMail(String email, String token) {
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", true); // added this line
        props.put("mail.smtp.ssl.trust", "mail.sweetll.me");
        props.put("mail.smtp.host", "mail.sweetll.me");
        props.put("mail.smtp.user", "j2ee@sweetll.me");
        props.put("mail.smtp.password", "j2ee");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", true);

        Session session = Session.getInstance(props, null);
        MimeMessage message = new MimeMessage(session);


        try {
            InternetAddress from = new InternetAddress("j2ee@sweetll.me");
            message.setSubject("j2ee", "UTF-8");
            message.setFrom(from);
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

            Multipart multipart = new MimeMultipart("alternative");

            BodyPart bodyPart = new MimeBodyPart();
            String htmlMessage = "<h1>欢迎您注册</h1>";

            String url = "http://localhost:12450/Home/UserActive.json?email=" + email + "&token=" + token;

            htmlMessage += "<p>请点击以下链接激活邮箱：<a href='"+url+"'>"+url+"</a></p>";
            bodyPart.setContent(htmlMessage, "text/html; charset=UTF-8");

            multipart.addBodyPart(bodyPart);
            message.setContent(multipart);

            Transport transport = session.getTransport("smtp");
            transport.connect("mail.sweetll.me", "j2ee", "j2ee");
            transport.sendMessage(message, message.getAllRecipients());
            return true;
        } catch (AddressException e) {
            e.printStackTrace();
            return false;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }


    class mailThread extends Thread {
        String email;
        String token;

        public mailThread(String email, String token) {
            this.email = email;
            this.token = token;
        }

        public void run() {
            while (!toSendMail(email, token)) {}
        }
    }

    private boolean toSendForgetMail(String email, String token) {
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", true); // added this line
        props.put("mail.smtp.ssl.trust", "mail.sweetll.me");
        props.put("mail.smtp.host", "mail.sweetll.me");
        props.put("mail.smtp.user", "j2ee@sweetll.me");
        props.put("mail.smtp.password", "j2ee");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", true);

        Session session = Session.getInstance(props, null);
        MimeMessage message = new MimeMessage(session);


        try {
            InternetAddress from = new InternetAddress("j2ee@sweetll.me");
            message.setSubject("j2ee", "UTF-8");
            message.setFrom(from);
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

            Multipart multipart = new MimeMultipart("alternative");

            BodyPart bodyPart = new MimeBodyPart();
            String htmlMessage = "<h1>忘记密码</h1>";

            String url = "http://localhost:12450/Home/ForgetPW.json?email=" + email + "&token=" + token;

            htmlMessage += "<p>请点击以下链接修改密码：<a href='"+url+"'>"+url+"</a></p>";
            bodyPart.setContent(htmlMessage, "text/html; charset=UTF-8");

            multipart.addBodyPart(bodyPart);
            message.setContent(multipart);

            Transport transport = session.getTransport("smtp");
            transport.connect("mail.sweetll.me", "j2ee", "j2ee");
            transport.sendMessage(message, message.getAllRecipients());
            return true;
        } catch (AddressException e) {
            e.printStackTrace();
            return false;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }


    class forgetMailThread extends Thread {
        String email;
        String token;

        public forgetMailThread(String email, String token) {
            this.email = email;
            this.token = token;
        }

        public void run() {
            while (!toSendForgetMail(email, token)) {}
        }
    }

}
