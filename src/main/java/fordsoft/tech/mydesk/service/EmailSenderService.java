package fordsoft.tech.mydesk.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;


/**
 * @author Bayram Ayhan Colakoglu
 */


@Component
public class EmailSenderService {
    @Autowired
    private JavaMailSender emailSender;


    /**
     * @param to
     * @param subject
     * @param text    This method is for senind simple messages as email.
     */
    public void sendSimpleMessage(String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("ehealthfrankfurt@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    /**
     * @param to
     * @param body
     * @param subject
     * @param attachment
     * @throws MessagingException if there is any exception
     *                            This class sends a message with attachment as email
     */
    public void sendMailwithAttachment(String to, String body, String subject, String attachment) throws MessagingException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom("ehealthfrankfurt@gmail.com");
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject(subject);

        FileSystemResource fileSystem = new FileSystemResource(new File(attachment));
        mimeMessageHelper.addAttachment(fileSystem.getFilename(), fileSystem);
        emailSender.send(mimeMessage);

        System.out.println("Mail with attachment sent successfully.");
    }
}

