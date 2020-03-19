/**
 * 
 */
package org.yelong.mail;

import java.io.UnsupportedEncodingException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.lang3.StringUtils;

/**
 * 邮件发件人
 * @author PengFei
 */
public class Sender {

	//发件人邮箱
	private String senderMail;
	
	//发件人邮箱客户端授权码（不是密码）
	private String senderPwd;
	
	//显示的发件人姓名（可空）
	private String senderName;
	
	//发件人姓名编码格式
	private String charset;

	public Sender(String senderMail, String senderPwd) {
		this(senderMail, senderPwd, null, null);
	}

	public Sender(String senderMail, String senderPwd, String senderName) {
		this(senderMail, senderPwd, senderName, null);
	}

	/**
	 * @param senderEmail 发件人邮箱
	 * @param senderPwd	发件人授权码
	 * @param senderName 发件人姓名
	 * @param charset 发件人姓名编码格式
	 */
	public Sender(String senderMail, String senderPwd, String senderName, String charset) {
		this.senderMail = senderMail;
		this.senderPwd = senderPwd;
		this.senderName = senderName;
		this.charset = charset;
	}

	public String getSenderMail() {
		return senderMail;
	}

	public String getSenderPwd() {
		return senderPwd;
	}

	public String getSenderName() {
		return senderName;
	}

	public String getCharset() {
		return charset;
	}

	/**
	 * 获取发件人Address
	 * @return
	 * @throws AddressException
	 * @throws UnsupportedEncodingException
	 */
	public InternetAddress getAddress() throws AddressException, UnsupportedEncodingException {
		InternetAddress address = null;
		if(StringUtils.isNotEmpty(senderName)) {
			if(StringUtils.isEmpty(charset)) {
				address = new InternetAddress(senderMail, senderName);
			} else {
				address = new InternetAddress(senderMail, senderName, charset);
			}
		}else {
			address = new InternetAddress(senderMail);
		}
		return address;
	}

}
