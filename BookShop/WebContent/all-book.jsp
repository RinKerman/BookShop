<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript">
function Jump(){
    var link = document.getElementById('number').value; // 取得文本框的值
    location.href = 'BookListAction?pageNum=' + link;
 	//跳转到该页面
}
</script>
</head>
<body>
	<center>
	<table style="border:solid">
		<thread>
		<tr>
		<th>ID</th>
		<th>书名</th>
		<th>作者</th>
		<th>出版社</th>
		<th>入库日期</th>
		<th>价格</th>
		<th>销量</th>
		<th>库存</th>
		<th>分类</th>
		<th>备注</th>
		<th>编辑</th>
		<th>删除</th>
		</tr>
		</thread>
		<tbody>
		<s:iterator id="book" value="bookList">
		
			<tr>
				<td><s:property value="#book.bid" /></td>
				<td><s:property value="#book.title" /></td>
				<td><s:property value="#book.author" /></td>
				<td><s:property value="#book.press" /></td>
				<td><s:property value="#book.addDate" /></td>
				<td><s:property value="#book.price" /></td>
				<td><s:property value="#book.salesAmount" /></td>
				<td><s:property value="#book.stockNumber" /></td>
				<td><s:property value="#book.booktype.btype" /></td>
				<td><s:property value="#book.note" /></td>
				<td><a href="EditBookAction?bookNumber=<s:property value="#book.bid" />">修改</a></td>
				<td><a href="DeleteBookAction?number=<s:property value="#book.bid" />">删除</a></td>
			</tr>
		</s:iterator>
		</tbody>
	</table>
	<s:if test="pageNum>1">
		<a href="BookListAction?pageNum=${PageNum-1}">上一页</a>&nbsp;&nbsp;
	</s:if>
	<a>当前页:<s:property value="PageNum"/>&nbsp;&nbsp;
	<a>总页数:<s:property value="TotalPage"/>&nbsp;&nbsp;
	<a href="BookListAction?pageNum=${PageNum+1}">下一页</a>
	
	</p>跳转到<input id="number" type="text" />
	<input type="button" value="跳转" onclick="Jump()"/>
	
	
	</center>
</body>
</html>