/**
 * 
 */
package org.yelong.mail.simple;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;

import org.apache.commons.lang3.StringUtils;
import org.yelong.mail.Content;
import org.yelong.mail.Mail;
import org.yelong.mail.Recipient;
import org.yelong.mail.Sender;
import org.yelong.mail.exception.AddInlineImageException;

/**
 * mail 构造器
 */
public class MailBuilder {

	private Sender sender;

	private List<Recipient> recipientList = new ArrayList<>();

	private Content mailContent = new Content();

	private String charset;

	private Properties sessionProps = new Properties();

	public MailBuilder() {

	}

	public MailBuilder(String charset) {
		this.charset = charset;
	}

	/**
	 * @param sender 发件人
	 */
	public void setSender(Sender sender) {
		this.sender = sender;
	}

	/**
	 * 设置发件人
	 * 
	 * @param senderMail 发件人邮箱
	 * @param senderPwd  发件人授权码
	 */
	public void setSender(String senderMail, String senderPwd) {
		setSender(senderMail, senderPwd, null);
	}

	/**
	 * 设置发件人
	 * 
	 * @param senderMail 发件人邮箱
	 * @param senderPwd  发件人授权码
	 * @param senderName 发件人姓名
	 */
	public void setSender(String senderMail, String senderPwd, String senderName) {
		setSender(senderMail, senderPwd, senderName, charset);
	}

	/**
	 * 设置发件人
	 * 
	 * @param senderMail 发件人邮箱
	 * @param senderPwd  发件人授权码
	 * @param senderName 发件人姓名
	 * @param charset    发件人姓名编码格式
	 */
	public void setSender(String senderMail, String senderPwd, String senderName, String charset) {
		setSender(new Sender(senderMail, senderPwd, senderName, charset));
	}

	/**
	 * 添加一个收件人
	 * 
	 * @param recipient 收件人对象
	 */
	public void addRecipient(Recipient recipient) {
		this.recipientList.add(recipient);
	}

	/**
	 * 添加一个收件人
	 * 
	 * @param recipientMail 收件人邮箱
	 * @param recipientType 收件人类型
	 * @throws AddressException
	 */
	public void addRecipient(String recipientMail, RecipientType recipientType) throws AddressException {
		addRecipient(new Recipient(recipientMail, recipientType));
	}

	/**
	 * 添加一个收件人
	 * 
	 * @param recipientMail 收件人邮箱
	 * @param recipientName 收件人姓名
	 * @param recipientType 收件人类型
	 * @throws UnsupportedEncodingException
	 */
	public void addRecipient(String recipientMail, String recipientName, RecipientType recipientType)
			throws UnsupportedEncodingException {
		if (StringUtils.isEmpty(charset)) {
			addRecipient(new Recipient(recipientMail, recipientName, recipientType));
		} else {
			addRecipient(new Recipient(recipientMail, recipientName, charset, recipientType));
		}
	}

	/**
	 * 添加一个收件人
	 * 
	 * @param recipientMail 收件人邮箱
	 * @throws AddressException
	 */
	public void addToRecipient(String recipientMail) throws AddressException {
		addRecipient(recipientMail, RecipientType.TO);
	}

	/**
	 * 添加一个收件人
	 * 
	 * @param recipientMail 收件人邮箱
	 * @param recipientName 收件人姓名
	 * @throws UnsupportedEncodingException
	 */
	public void addToRecipient(String recipientMail, String recipientName) throws UnsupportedEncodingException {
		addRecipient(recipientMail, recipientName, RecipientType.TO);
	}

	/**
	 * 添加一个抄送人
	 * 
	 * @param recipientMail 抄送人邮箱
	 * @throws AddressException
	 */
	public void addCcRecipient(String recipientMail) throws AddressException {
		addRecipient(recipientMail, RecipientType.CC);
	}

	/**
	 * 添加一个抄送人
	 * 
	 * @param recipientMail 抄送人邮箱
	 * @param recipientName 抄送人姓名
	 * @throws UnsupportedEncodingException
	 */
	public void addCcRecipient(String recipientMail, String recipientName) throws UnsupportedEncodingException {
		addRecipient(recipientMail, recipientName, RecipientType.CC);
	}

	/**
	 * 添加一个密送人
	 * 
	 * @param recipientMail 密送人邮箱
	 * @throws AddressException
	 */
	public void addBccRecipient(String recipientMail) throws AddressException {
		addRecipient(recipientMail, RecipientType.BCC);
	}

	/**
	 * 添加一个密送人
	 * 
	 * @param recipientMail 密送人邮箱
	 * @param recipientName 密送人姓名
	 * @throws UnsupportedEncodingException
	 */
	public void addBccRecipient(String recipientMail, String recipientName) throws UnsupportedEncodingException {
		addRecipient(recipientMail, recipientName, RecipientType.BCC);
	}

	/**
	 * 设置编码格式
	 * 
	 * @param charset
	 */
	public void setCharset(String charset) {
		this.charset = charset;
	}

	/**
	 * 添加一个内联图片
	 * <p>
	 * 如果图片标识已经存在，将会覆盖掉
	 * </p>
	 * 
	 * @param cid       图片标识 用于在文本主体中引用
	 * @param imagePath 图片路径
	 * @throws AddInlineImageException
	 */
	public void addInlineImage(String cid, String imagePath) throws AddInlineImageException {
		mailContent.addInlineImage(cid, imagePath);
	}

	/**
	 * 添加一个附件
	 * 
	 * @param attachment
	 */
	public void addAttachment(String attachment) {
		mailContent.addAttachment(attachment);
	}

	/**
	 * @param subject 邮件主题
	 */
	public void setSubject(String subject) {
		mailContent.setSubject(subject);
	}

	/**
	 * @param content     邮件主体内容
	 * @param contentType 内容格式 “（text/html;charset=UTF-8）”支持html样式
	 */
	public void setContent(String content, String contentType) {
		mailContent.setContent(content, contentType);
	}

	/**
	 * 设置保存邮件路径 只有设置此属性才会保存邮件的文件
	 * 
	 * @param saveEmlPath
	 */
	public void setSaveEmlPath(String saveEmlPath) {
		mailContent.setSaveEmlPath(saveEmlPath);
	}

	/**
	 * @param properties mail 会话属性
	 */
	public void setSessionProps(Properties properties) {
		this.sessionProps = properties;
	}

	/**
	 * 添加一个mail 会话属性
	 * 
	 * @param key
	 * @param value
	 */
	public void putSessionProperty(String key, String value) {
		this.sessionProps.put(key, value);
	}

	/**
	 * 创建邮件
	 * 
	 * @return 邮件
	 * @throws BuilderMailException 构建邮件异常
	 */
	public Mail create() throws BuilderMailException {
		if (null == sender) {
			throw new BuilderMailException("构建邮件时不存在发件人！");
		}
		if (this.recipientList.isEmpty()) {
			throw new BuilderMailException("构建邮件时不存在收件人！");
		}
		// 邮件内容和邮件的附件均不存在表示内容为空
		if (StringUtils.isEmpty(mailContent.getContent()) && !mailContent.existAttachment()) {
			throw new BuilderMailException("构建邮件时不存在邮件的内容！");
		}
		SimpleMail mail = new SimpleMail(sender, recipientList, mailContent);
		mail.setSessionProperties(getMailSessionProperties());
		return mail;
	}

	/**
	 * 获取mail 会话属性
	 * 
	 * @return
	 */
	private Properties getMailSessionProperties() {
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.host", getSmtpHost(sender.getSenderMail()));
		props.setProperty("mail.smtp.auth", "true");
		// PS: 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),
		// 如果无法连接邮件服务器, 仔细查看控制台打印的 log, 如果有有类似 “连接失败, 要求 SSL 安全连接” 等错误,
		// 打开下面 /* ... */ 之间的注释代码, 开启 SSL 安全连接。
		/*
		 * // SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接, // 需要改为对应邮箱的 SMTP
		 * 服务器的端口, 具体可查看对应邮箱服务的帮助, // QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看) final String
		 * smtpPort = "465"; props.setProperty("mail.smtp.port", smtpPort);
		 * props.setProperty("mail.smtp.socketFactory.class",
		 * "javax.net.ssl.SSLSocketFactory");
		 * props.setProperty("mail.smtp.socketFactory.fallback", "false");
		 * props.setProperty("mail.smtp.socketFactory.port", smtpPort);
		 */
		props.putAll(sessionProps);
		return props;
	}

	/**
	 * 根据发件人邮件获取邮箱host
	 * 
	 * @param senderMail
	 * @return
	 */
	private String getSmtpHost(String senderMail) {
		String suffix = senderMail.substring(senderMail.lastIndexOf("@") + 1);
		if (suffix.equals("qq.com")) {
			return "smtp.qq.com";
		} else if (suffix.equals("163.com")) {
			return "smtp.163.com";
		}
		return null;
	}

}
