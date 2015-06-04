package service;

import dao.UserDao;
import dao.VerifyEmailDao;
import entity.Userentity;
import entity.VerifyEmailentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

/**
 * Created by sweet on 15-6-3.
 */

@Transactional
@Service
public class RegisterService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private VerifyEmailDao verifyEmailDao;

    public Userentity getUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    public Userentity getUserByNickname(String nickname) {
        return userDao.findUserByNickname(nickname);
    }

    public VerifyEmailentity getVerifyEmailByEmail(String email) {
        return verifyEmailDao.findVerifyEmailByEmail(email);
    }

    public boolean sendmail(String email, String token) {
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
            message.setSubject("欢迎注册", "UTF-8");
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

    public boolean createUser(String email, String nickname, String password) {
        Long now = System.currentTimeMillis() / 1000L;
        Long tomorrow = now + 86400;
        String regTime = now.toString();
        String expire = tomorrow.toString();

        String token = regTime + email + password;
        token = DigestUtils.md5DigestAsHex(token.getBytes());
        if (userDao.createUser(regTime, email, nickname, password) && verifyEmailDao.createVerifyEmail(email, token, expire)) {
            sendmail(email, token);
            return true;
        } else {
            return false;
        }
    }

    public boolean activeUser(String email) {
        return userDao.activeUser(email);
    }
}
