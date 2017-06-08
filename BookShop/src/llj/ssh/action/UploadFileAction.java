package llj.ssh.action;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.BookDao;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.IOException;

import com.sun.jmx.snmp.Timestamp;

import czj.ssh.model.Book;


@SuppressWarnings("serial")
public class UploadFileAction extends ActionSupport {
	/** * �ϴ��ļ� */
	private File uploadFile;
	/** * �ϴ��ļ����� */
	private String uploadFileContentType;
	/**
	 * �ϴ��ļ���
	 */
	private String uploadFileName;
	/** * �ϴ�������Ϣ */
	private String uploadErrMsg;
	
	private BookDao dao;
	
	private Book book;

	public Book getBook() {
		return book;
	}


	public void setBook(Book book) {
		this.book = book;
	}


	public BookDao getDao() {
		return dao;
	}


	public void setDao(BookDao dao) {
		this.dao = dao;
	}


	public File getUploadFile() {
		return uploadFile;
	}


	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}


	public String getUploadFileContentType() {
		return uploadFileContentType;
	}


	public void setUploadFileContentType(String uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
	}


	public String getUploadFileName() {
		return uploadFileName;
	}


	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}


	public String getUploadErrMsg() {
		return uploadErrMsg;
	}


	public void setUploadErrMsg(String uploadErrMsg) {
		this.uploadErrMsg = uploadErrMsg;
	}


	public String upload() {
		//��ȡͼƬ����·��
		String realpath = ServletActionContext.getServletContext().getRealPath("/pictures/"); 
		int id = book.getBid();
		File dir = new File(realpath);
		if (!dir.exists()) {
			dir.mkdir();
		}
		
		if (uploadFileContentType.equals("image/jpeg")) {
			uploadFileContentType = ".jpg";
		} else if (uploadFileContentType.equals("image/png")) {
			uploadFileContentType = ".png";
		} 
		uploadFileName = "image" + new Timestamp().getDateTime() + uploadFileContentType;
		try {
			//����ͼƬ��·��
			FileUtils.copyFile(uploadFile, new File(dir, uploadFileName));
			System.out.println("saved in:"+dir+"\\"+uploadFileName); 
			System.out.println("the book bid = "+book.getBid()+" and book.picture = "+ "picture/"+uploadFileName);
			String url = "pictures/"+uploadFileName;
		
			dao.updatePicture(url,id);
			
			
			
			
			return SUCCESS;
		} catch (IOException e) {
			uploadErrMsg = "��ʾ���ϴ�ͼƬʧ�ܣ�����";
			return ERROR;
		}
	}

}
