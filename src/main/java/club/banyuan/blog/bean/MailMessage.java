package club.banyuan.blog.bean;

import java.io.Serializable;

public class MailMessage implements Serializable {
    private String subject;
    private String content;
    private String receiver;

    public MailMessage() {
    }

    public MailMessage(String subject, String content, String receiver) {
        this.subject = subject;
        this.content = content;
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "MailMessage{" +
                "subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", receiver='" + receiver + '\'' +
                '}';
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
