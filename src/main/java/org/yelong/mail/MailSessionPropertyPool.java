/**
 * 
 */
package org.yelong.mail;

/**
 * 邮件会话属性池
 */
public interface MailSessionPropertyPool {

	/**
	 * 传输协议
	 */
	String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";

	/**
	 * 传输host
	 */
	String MAIL_SMTP_HOST = "mail.smtp.host";

	/**
	 * 协议是否授权
	 */
	String MAIL_SMTP_AUTH = "mail.smtp.auth";

	// PS: 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),
	// 如果无法连接邮件服务器, 仔细查看控制台打印的 log, 如果有有类似 “连接失败, 要求 SSL 安全连接” 等错误,
	// 打开下面 /* ... */ 之间的注释代码, 开启 SSL 安全连接。
	/*
	 * // SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接, // 需要改为对应邮箱的 SMTP
	 * 服务器的端口, 具体可查看对应邮箱服务的帮助,
	 */

	String MAIL_SMTP_PORT = "mail.smtp.port";

	String MAIL_SMTP_SOCKET_FACTORY_CLASS = "mail.smtp.socketFactory.class";

	String MAIL_SMTP_SOCKET_FACTORY_FALLBACK = "mail.smtp.socketFactory.fallback";

	String MAIL_SMTP_SOCKET_FACTORY_PORT = "mail.smtp.socketFactory.port";

}
