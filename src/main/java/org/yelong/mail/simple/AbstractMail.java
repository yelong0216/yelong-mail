/**
 * 
 */
package org.yelong.mail.simple;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang3.StringUtils;
import org.yelong.mail.Content;
import org.yelong.mail.Mail;
import org.yelong.mail.Recipient;
import org.yelong.mail.Sender;
import org.yelong.mail.exception.MailSendException;

/**
 * 抽象的邮件的实现
 * @author PengFei
 */
public abstract class AbstractMail implements Mail{

	private Sender sender;

	private List<Recipient> recipients;

	private Content mailContent;

	/**
	 * @param sender 发件人
	 * @param recipients 收件人（集合）
	 * @param mailContent 邮件内容
	 */
	public AbstractMail(Sender sender, List<Recipient> recipients, Content mailContent) {
		this.sender = sender;
		this.recipients = Collections.unmodifiableList(recipients);
		this.mailContent = mailContent;
	}

	@Override
	public Sender getSender() {
		return this.sender;
	}

	@Override
	public List<Recipient> getAllRecipient() {
		return this.recipients;
	}

	@Override
	public Content getContent() {
		return this.mailContent;
	}

	@Override
	public void send() throws MailSendException {
		try {
			MimeMessage message = createMimeMessage(sender, recipients, mailContent);
			//是否保存到本地
			if(StringUtils.isNotEmpty(mailContent.getSaveEmlPath())) {
				OutputStream out = new FileOutputStream(mailContent.getSaveEmlPath());
				message.writeTo(out);
			}
			Transport.send(message);
		} catch (Exception e) {
			throw new MailSendException(e);
		} 
	}

	/**
	 * 创建一个邮件消息
	 * @param sender 发件人
	 * @param recipient 收件人
	 * @param mailMessage 邮件信息
	 * @return
	 * @throws MessagingException 
	 * @throws UnsupportedEncodingException 
	 * @throws AddressException 
	 * @throws MailSendExection
	 */
	protected MimeMessage createMimeMessage(Sender sender , List<Recipient> recipients , Content mailContent) throws AddressException, UnsupportedEncodingException, MessagingException{
		Session session = Session.getInstance(this.getSessionProperties(),new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender.getSenderMail(), sender.getSenderPwd());
			}
		});
		session.setDebug(true);
		MimeMessage message = new MimeMessage(session);
		//设置发件人
		message.setFrom(sender.getAddress());
		//收件人
		for (Recipient recipient : recipients) {
			message.addRecipient(recipient.getRecipientType(), recipient.getInternetAddress());
		}
		//设置主题
		message.setSubject(mailContent.getSubject(),mailContent.getCharset());
		//设置主体
		message.setContent(createContentMultipart(mailContent));
		return message;
	}

	/**
	 * 创建Multipart
	 * @param mailContent 邮件内容
	 * @return
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	protected Multipart createContentMultipart(Content mailContent) throws MessagingException, UnsupportedEncodingException {
		if( StringUtils.isEmpty(mailContent.getContent())&& !mailContent.existAttachment()) {
			throw new MessagingException("邮件内容和附件都没有，它们之间至少存在一项！");
		}
		Multipart content = new MimeMultipart();

		if( StringUtils.isNotEmpty(mailContent.getContent()) ) {
			//文本和图片
			BodyPart textAndImageBody = new MimeBodyPart();
			//结合文本和图片
			Multipart textAndImageMultipart = new MimeMultipart();
			//文本
			BodyPart text = new MimeBodyPart();
			text.setContent(mailContent.getContent(), mailContent.getContentType());
			textAndImageMultipart.addBodyPart(text);
			if(mailContent.existInlineImage()) {
				//图片
				Map<String,String> inlineImage = mailContent.getAllInlineImage();
				for (String cid : inlineImage.keySet()) {
					MimeBodyPart image = new MimeBodyPart();
					DataHandler dh = new DataHandler(new FileDataSource(inlineImage.get(cid)));
					image.setDataHandler(dh);
					image.setContentID(cid);
					textAndImageMultipart.addBodyPart(image);
				}
			}
			textAndImageBody.setContent(textAndImageMultipart);
			content.addBodyPart(textAndImageBody);
		}

		if(mailContent.existAttachment()) {
			//添加附件节点
			for (String string : mailContent.getAllAttachment()) {
				MimeBodyPart document = new MimeBodyPart();
				DataHandler dhdoc = new DataHandler( new FileDataSource(string));
				document.setDataHandler(dhdoc);
				document.setFileName(MimeUtility.encodeText(dhdoc.getName()));
				content.addBodyPart(document);
			}
		}

		return content;
	}

	/**
	 * @return session的配置参数
	 */
	protected abstract Properties getSessionProperties();

}
