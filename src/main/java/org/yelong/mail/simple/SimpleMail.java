/**
 * 
 */
package org.yelong.mail.simple;

import java.util.List;
import java.util.Properties;

import org.yelong.mail.Content;
import org.yelong.mail.Recipient;
import org.yelong.mail.Sender;

/**
 * 简单的邮件
 */
public class SimpleMail extends AbstractMail {

	private Properties props = new Properties();

	public SimpleMail(Sender sender, List<Recipient> recipients, Content mailContent) {
		super(sender, recipients, mailContent);
	}

	@Override
	public Properties getSessionProperties() {
		return props;
	}

	public void setSessionProperties(Properties props) {
		this.props = props;
	}

	public void addSessionProperty(String key, String value) {
		this.props.put(key, value);
	}

}
