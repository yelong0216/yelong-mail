/**
 * 
 */
package org.yelong.mail.exception;

/**
 * 邮件发送异常
 * @author PengFei
 */
public class MailSendException extends Exception{
	
	private static final long serialVersionUID = -5837421062469082430L;
	
	public MailSendException(String message) {
		super(message);
	}
	
	public MailSendException(Throwable t) {
		super(t);
	}
	
	public MailSendException() {
		
	}

}
