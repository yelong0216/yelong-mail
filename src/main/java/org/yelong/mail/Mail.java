/**
 * 
 */
package org.yelong.mail;

import java.util.List;

import org.yelong.mail.exception.MailSendException;

/**
 * 邮件
 */
public interface Mail {

	/**
	 * @author PengFei
	 * @return 发件人
	 */
	Sender getSender();

	/**
	 * @return 所有收件人
	 */
	List<Recipient> getAllRecipient();

	/**
	 * @return 邮件内容
	 */
	Content getContent();

	/**
	 * 发送邮件
	 * 
	 * @throws MailSendException 邮件发送异常
	 */
	void send() throws MailSendException;

}
