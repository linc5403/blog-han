package club.banyuan.blog.service;

import club.banyuan.blog.bean.MailMessage;
import club.banyuan.blog.config.RabbitMqConfig;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.http.converter.json.GsonFactoryBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private RabbitMessagingTemplate template;

    @Autowired
    private Gson gson;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void sendTestMail(String receiver, String subject, String content) {
        MailMessage message = new MailMessage(subject, content, receiver);
        template.convertAndSend(RabbitMqConfig.getTopicExchangeName(), "foo.bar.baz", gson.toJson(message));
        logger.info("send msg" + message);
    }

    public void sendMail(String receiver, String subject, String content) {
        MailMessage message = new MailMessage(subject, content, receiver);
        template.convertAndSend(RabbitMqConfig.getTopicExchangeName(), "foo.bar.baz", gson.toJson(message));
        logger.info("send msg" + message);
    }

    /*
    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String receiver, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(receiver);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        simpleMailMessage.setFrom("linc1982@qq.com");

        mailSender.send(simpleMailMessage);
    }

    public void sendTestMail(String receiver, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(receiver);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        simpleMailMessage.setFrom("linc1982@qq.com");

        mailSender.send(simpleMailMessage);
    }
    */
}
