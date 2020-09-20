package org.yelong.mail;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.yelong.mail.exception.AddInlineImageException;

/**
 * 邮件内容
 */
public class Content {

	// 主题
	private String subject;

	// 邮件编码
	private String charset;

	// 正文
	private String content;

	// 正文类型（可用HTML）
	private String contentType;

	// 如果保存eml文件则设置保存路径
	private String saveEmlPath;

	// 图片集合 key:图片引用id value:图片路径
	private Map<String, String> inlineImageMap = new HashMap<String, String>();

	// 附件集合
	private List<String> attachments = new ArrayList<String>();

	/**
	 * 添加一个内联图片
	 * <p>
	 * 如果图片标识已经存在，将会覆盖掉
	 * </p>
	 * 
	 * @param cid       图片标识 用于在文本主体中引用
	 * @param imagePath 图片路径
	 * @throws AddInlineImageException
	 */
	public void addInlineImage(String cid, String imagePath) throws AddInlineImageException {
		File file = new File(imagePath);
		if (!file.exists() || file.isDirectory()) {
			throw new AddInlineImageException("文件不存在或者这是一个文件夹！");
		}
		this.inlineImageMap.put(cid, imagePath);
	}

	/**
	 * 添加一个附件
	 * 
	 * @param attachment
	 */
	public void addAttachment(String attachment) {
		this.attachments.add(attachment);
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCharset() {
		return charset;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content, String contentType) {
		this.content = content;
		this.contentType = contentType;
	}

	public String getContentType() {
		return contentType;
	}

	public String getSaveEmlPath() {
		return saveEmlPath;
	}

	public void setSaveEmlPath(String saveEmlPath) {
		this.saveEmlPath = saveEmlPath;
	}

	/**
	 * 获取所有内联图片
	 * 
	 * @return 所有内联图片
	 */
	public Map<String, String> getAllInlineImage() {
		return inlineImageMap;
	}

	/**
	 * 获取所有附件
	 * 
	 * @return
	 */
	public List<String> getAllAttachment() {
		return attachments;
	}

	/**
	 * 是否存在内联图片
	 * 
	 * @return <tt>true</tt> 存在内联图片
	 */
	public boolean existInlineImage() {
		return !inlineImageMap.isEmpty();
	}

	/**
	 * 是否存在附件
	 * 
	 * @return <tt>true</tt> 存在附件
	 */
	public boolean existAttachment() {
		return !attachments.isEmpty();
	}

}
