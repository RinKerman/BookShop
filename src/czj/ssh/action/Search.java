package czj.ssh.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.opensymphony.xwork2.ActionSupport;

import czj.ssh.dao.BookDao;
import czj.ssh.model.Book;

public class Search extends ActionSupport{
	private String keyword;
	private String type;
	private String minPrice;
	private String maxPrice;
	private BookDao bookDao;
	private boolean stage=false;   //�ж���ת
	private Set<Book> searchResult = new HashSet<Book>();
	
	public Search() {
		super();
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public boolean isStage() {
		return stage;
	}

	public void setStage(boolean stage) {
		this.stage = stage;
	}

	public BookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public Set<Book> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(Set<Book> searchResult) {
		this.searchResult = searchResult;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}

	public String getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String execute(){
		System.out.println("searchAction��ʼ����");
		System.out.println("������" + type + ", " + keyword + "," + minPrice + "," + maxPrice);
		if(type.equals("price")){	//��������Ϊ�۸�	
			//�����ݽ�����֤
			int min = 0;
			int max = 0;
			try {
				min = Integer.parseInt(minPrice);
				max = Integer.parseInt(maxPrice);
			} catch (NumberFormatException e) {
				searchResult = null;
				e.printStackTrace();
			}
			if(min > max){
				searchResult = null;
				
				if(stage) return NONE;
				return SUCCESS;
			}
			//�����ݿ��н��в���
			List<Book> r = bookDao.queryBookByPrice(min, max);
			searchResult = new HashSet<>(r);
		}else{		
		//����������͹������������ݽ���ת��
		  byte[] source = null;
		  String newKeyword = null;
		try {
			source = keyword.getBytes("iso8859-1");
			newKeyword = new String (source,"UTF-8");			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�Թؼ��ֽ����з�
		String[] query = newKeyword.trim().split(" ");
	       System.out.println("���յ���keyword:" + newKeyword);
	       System.out.println("��keyword���зָ�:");
	       for(String str : query){
	    	   System.out.println(str);
	       }
	       //�Բ�ֺõ�ÿ���ؼ��ֽ�������
	       for (int i = 0; i < query.length; i++) {
	    	   List<Book> r = bookDao.fuzzyLookup(query[i],type);
	    	   if(i == 0){
	    		   System.out.println("����һ�������ӵ�����");
	    		   searchResult.addAll(r);	//��һ���ؼ��ֵļ�����ӵ������
	    	   }else{
	    		   searchResult.retainAll(r);	//���һ���ؼ��ֵļ����󽻼�
	    	   }	    		   
	       }
		}
//	       Iterator it= searchResult.iterator();
//	       while(it.hasNext()){
//	    	   System.out.println(it.next());
//	       }
		
		if(stage) return NONE;
		return SUCCESS;
	}
}
