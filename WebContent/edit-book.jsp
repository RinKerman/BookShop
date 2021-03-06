<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.opensymphony.xwork2.util.ValueStack" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<s:if test="#session.user.usertype.utId != 1">
	<jsp:forward page="login.jsp"/>
</s:if>
<body>
	<center>
		<form action=UpdateBookAction method="post">
			<table border="1" width="70%" cellpadding="15" cellspacing="0" align="center" style="border-color:#e2e2e2;">

				<tbody>
					<tr>
						<td>ID</td>
						<td><input type="text" value="${book.bid}" name="book.bid"
							readonly></td>
					</tr>
					<tr>
						<td>书名</td>
						<td><input type="text" value="${book.title}"
							name="book.title"></td>
					</tr>
					<tr>
						<td>作者</td>
						<td><input type="text" value="${book.author}"
							name="book.author"></td>
					</tr>
					<tr>
						<td>出版社</td>
						<td><input type="text" value="${book.press}"
							name="book.press"></td>
					</tr>
					<tr>
						<td>添加日期</td>
						<td><input type="text" value="${book.addDate}"
							name="book.addDate" readonly></td>
					</tr>
					<tr>
						<td>价格</td>
						<td><input type="text" value="${book.price}"
							name="book.price"></td>
					</tr>
					<tr>
						<td>销量</td>
						<td><input type="text" value="${book.salesAmount}"
							name="book.salesAmount" readonly></td>
					</tr>
					<tr>
						<td>库存</td>
						<td><input type="text" value="${book.stockNumber}"
							name="book.stockNumber"></td>
					</tr>
					<tr>
						<td>分类</td>
						<td>当前:${book.booktype.btype}<select name="newType">
								<s:iterator id="type" value="bookTypeList">
									<option value="${type.tid}"><s:property
											value="#type.btype" /></option>
								</s:iterator>
						</select></td>
					</tr>
					<tr>
						<td>备注</td>
						<td><input type="text" value="${book.note}" name="book.note"/></td>
					</tr>
				</tbody>
			</table>
			<input type="hidden" value="${book.picture}" name="book.picture"/>
			<input type="submit" /> <input type="button" value="返回" onclick="javascript:window.history.back(-1);">
		</form>
	</center>
	</body>
</html>