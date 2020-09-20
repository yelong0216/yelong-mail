/**
 * 
 */
package org.yelong.mail.simple;

/**
 * 构建邮件异常
 */
public class BuilderMailException extends Exception{

	private static final long serialVersionUID = -3149434907051931615L;

	public BuilderMailException(String message) {
		super(message);
	}
	
	public BuilderMailException(Throwable t) {
		super(t);
	}
	
	public BuilderMailException() {
		
	}
	
}
