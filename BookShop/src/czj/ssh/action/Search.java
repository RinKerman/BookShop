package czj.ssh.action;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
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
		System.out.println("searchAction开始运行");
		System.out.println("参数：" + type + ", " + keyword + "," + minPrice + "," + maxPrice);
		if(type.equals("price")){	//搜索类型为价格	
			//对数据进行验证
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
				return SUCCESS;
			}
			//在数据库中进行查找
			List<Book> r = bookDao.queryBookByPrice(min, max);
			searchResult = new HashSet(r);
		}else{		
		//将浏览器发送过来的中文数据进行转码
		  byte[] source = null;
		  String newKeyword = null;
		try {
			source = keyword.getBytes("iso8859-1");
			newKeyword = new String (source,"UTF-8");
			/*min = minPrice.getBytes("iso8859-1");
			newMin = new String(min,"UTF-8");
			max = maxPrice.getBytes("iso8859-1");
			newMax = new String(max,"UTF-8");*/
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//对关键字进行切分
		String[] query = newKeyword.trim().split(" ");
	       System.out.println("接收到的keyword:" + newKeyword);
	       System.out.println("对keyword进行分割:");
	       for(String str : query){
	    	   System.out.println(str);
	       }
	     //在数据库中进行模糊查询	       
//	       List<Book> result = bookDao.fuzzyLookup(newKeyword.trim().replace(' ', '%'));
//	       for(Book b1: result){
//	    	   //将查询结果添加到set中
//	    	   searchResult.add(b1);
//	       }
	       //对拆分好的每个关键字进行搜索
	       for (int i = 0; i < query.length; i++) {
	    	   List<Book> r = bookDao.fuzzyLookup(query[i],type);
//	    	   for(Book b2 : r){	//并集
//	    		   searchResult.add(b2);	    		   
//	    	   }
	    	   if(i == 0){
	    		   searchResult.addAll(r);	//第一个关键字的集合添加到结果集
	    	   }else{
	    		   searchResult.retainAll(r);	//与第一个关键字的集合求交集
	    	   }	    		   
	       }
		}
//	       Iterator it= searchResult.iterator();
//	       while(it.hasNext()){
//	    	   System.out.println(it.next());
//	       }
		return SUCCESS;
	}
}
