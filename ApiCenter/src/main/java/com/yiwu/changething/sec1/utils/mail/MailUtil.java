package com.yiwu.changething.sec1.utils.mail;

import com.yiwu.changething.sec1.bean.Mail;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 邮件发送工具实现类
 *
 * @author Lim Lin
 * @created 2016-10-26 上午11:35:05
 */
@Component
public class MailUtil {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public void send(Mail mail) {
        // 发送email
        HtmlEmail email = new HtmlEmail();
        try {

            // 发送附件
            /*
             * EmailAttachment attachment = new EmailAttachment();
             * attachment.setPath(PropertiesUtil.getProperty("FILEPATH"));//附件路径
             * attachment.setDisposition(EmailAttachment.ATTACHMENT);
             * attachment.
             * setDescription(PropertiesUtil.getProperty("FILEDESCRIPTION"
             * ));//附件描述
             * attachment.setName(PropertiesUtil.getProperty("FILENAME"));//附件名
             * // 添加附件 email.attach(attachment);
             */

            // email.setDebug(true);//是否开启调试默认不开启
            email.setSSLOnConnect(true);// 开启SSL加密
            email.setStartTLSEnabled(true);// 开启TLS加密
//            email.setSslSmtpPort("465");

            // 抄送
            email.addCc(mail.getCopy());// 抄送方
            email.addBcc(mail.getSecretCopy());// 秘密抄送方

            // 这里是SMTP发送服务器的名字：163的如下："smtp.163.com"
            email.setHostName(mail.getHost());
            // 字符编码集的设置 utf-8
            email.setCharset("UTF-8");
            // 收件人的邮箱
            email.addTo(mail.getReceiver());
            // 发送人的邮箱
            email.setFrom(mail.getSender(), mail.getSenderName());
            // 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
            email.setAuthentication(mail.getUserName(), mail.getPassword());
            // 要发送的邮件主题
            email.setSubject(mail.getSubject());
            // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
            email.setHtmlMsg("<html>您好，<br/>我们收到了重新设置您的帐户密码的请求。" + "要重设密码，您必须提交此暗号以便验证该请求是合法的。<br/>暗号是："
                    + "<h1 style='color:red;display:inline-block;'>" + mail.getMessage() + "</h1><br/>谢谢！</html>");
            // setMsg() 和setHtmlMsg()同时使用的话，
            // setHtmlMsg()会覆盖掉setMsg()的内容。email.setMsg(mail.getMessage());

            // 发送
            email.send();

            // 打印日志信息
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(mail.getSender() + " 发送邮件到 " + mail.getReceiver());
                // LOGGER.debug(PropertiesUtil.getProperty("SENDER") +
                // " 秘密抄送邮件到 " + email.getBccAddresses());
                if (email.getCcAddresses() != null) {
                    LOGGER.debug(mail.getSender() + " 抄送邮件到 " + email.getCcAddresses());
                }
            }
        } catch (EmailException e) {
            e.printStackTrace();
            LOGGER.trace(mail.getSender() + " 发送邮件到 " + mail.getReceiver() + " 失败");
        }
    }
}
