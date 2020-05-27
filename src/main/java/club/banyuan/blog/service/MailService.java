package club.banyuan.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendTestMail(String receiver, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(receiver);
        simpleMailMessage.setSubject("spring mail test");
        simpleMailMessage.setText(content);
        simpleMailMessage.setFrom("linc1982@qq.com");

        mailSender.send(simpleMailMessage);
    }
}
