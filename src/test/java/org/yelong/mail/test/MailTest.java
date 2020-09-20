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

public class MailTest {

	public static void main(String[] args) throws UnsupportedEncodingException, BuilderMailException, MailSendException,
			InterruptedException, AddressException {
		MailBuilder mailBuilder = new MailBuilder();
		mailBuilder.setCharset("UTF-8");
		mailBuilder.setSender("17601085125@163.com", "pf250216", "彭飞邮箱中心");
		// mailBuilder.addToRecipient("yangb@labbol.com");
		mailBuilder.addToRecipient("1392642855@qq.com");
		mailBuilder.setContent("嘿嘿", "text/html;charset=UTF-8");
		mailBuilder.setSubject("彭飞");
		Mail mail = mailBuilder.create();
		mail.send();
	}

}
