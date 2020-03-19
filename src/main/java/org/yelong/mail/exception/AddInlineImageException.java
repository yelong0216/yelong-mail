/**
 * 
 */
package org.yelong.mail.exception;

/**
 * 添加内联图片异常
 * @author PengFei
 */
public class AddInlineImageException extends Exception{
	
	private static final long serialVersionUID = -5837421062469082430L;
	
	public AddInlineImageException(String message) {
		super(message);
	}
	
	public AddInlineImageException(Throwable t) {
		super(t);
	}
	
	public AddInlineImageException() {
		
	}

}
