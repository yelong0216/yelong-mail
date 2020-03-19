/**
 * 
 */
package org.yelong.mail;

import java.io.UnsupportedEncodingException;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;

import org.apache.commons.lang3.StringUtils;

/**
 * 收件人
 * @author PengFei
 */
public class Recipient {

	//收件人邮箱
	private String recipientMail;

	//收件人名称
	private String recipientName;

	//收件人名称编码格式
	private String charset;
	
	//收件类型（抄送，密送，还是什么送）
	private RecipientType recipientType;

	public Recipient(String recipientMail , RecipientType recipientType) {
		this.recipientMail = recipientMail;
		this.recipientType = recipientType;
	}
	
	public Recipient(String recipientMail , String recipientName,RecipientType recipientType) {
		this(recipientMail, recipientType);
		this.recipientName = recipientName;
	}
	
	public Recipient(String recipientMail , String recipientName, String charset ,RecipientType recipientType) {
		this(recipientMail, recipientName, recipientType);
		this.charset = charset;
	}

	public String getRecipientMail() {
		return recipientMail;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public String getCharset() {
		return charset;
	}

	public RecipientType getRecipientType() {
		return recipientType;
	}
	
	/**
	 * 获取收件人address
	 * @return 收件人address
	 * @throws UnsupportedEncodingException
	 */
	public InternetAddress getInternetAddress() throws UnsupportedEncodingException {
		if(StringUtils.isEmpty(charset)) {
			return new InternetAddress(recipientMail, recipientName);
		} else {
			return new InternetAddress(recipientMail, recipientName,charset);
		}
	}
	
}
