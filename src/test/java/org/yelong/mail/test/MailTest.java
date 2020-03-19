/**
 * 
 */
package org.yelong.mail.test;

import java.io.UnsupportedEncodingException;

import javax.mail.internet.AddressException;

import org.yelong.mail.Mail;
import org.yelong.mail.exception.MailSendException;
import org.yelong.mail.simple.BuilderMailException;
import org.yelong.mail.simple.MailBuilder;

/**
 * @author PengFei
 * @date 2020年2月14日上午11:58:42
 * @since 1.0
 */
public class MailTest {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 * @throws BuilderMailException 
	 * @throws MailSendException 
	 * @throws InterruptedException 
	 * @throws AddressException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException, BuilderMailException, MailSendException, InterruptedException, AddressException {
		MailBuilder mailBuilder = new MailBuilder();
		mailBuilder.setCharset("UTF-8");
		mailBuilder.setSender("17601085125@163.com", "pf250216", "彭飞邮箱中心");
		//mailBuilder.addToRecipient("yangb@labbol.com");
		mailBuilder.addToRecipient("yl1430834495@163.com");
		mailBuilder.setContent("我爱你哦波波11111", "text/html;charset=UTF-8");
		mailBuilder.setSubject("彭飞提示你");
		Mail mail = mailBuilder.create();
		mail.send();
		Thread.sleep(5000);
	}

}
