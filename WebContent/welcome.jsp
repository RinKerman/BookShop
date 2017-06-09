<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	
</script>
</head>
<body>
	<s:iterator value="shoppingCarts" status="st" var="shoppingCart">
		
		user:<s:property value="#shoppingCart.user.uid"/><br>
		userName:<s:property value="#shoppingCart.user.userName"/><br>	<!-- 由于懒加载的原因,不能显示userName -->
		book:<s:property value="#shoppingCart.book.bid"/><br>			
		book title:<s:property value="#shoppingCart.book.title"/><br>		<!-- book关闭了懒加载,能看到book title -->	
		quantity:<s:property value="#shoppingCart.quantity"/><br>		
	</s:iterator>
	
	<%-- <p>最新</p>
	
	 <s:iterator value="#session.bookListOfDate" status="st" var="book">
	 	<p>
	 	<s:property value='#book.picture'/>	
		<s:property value="#book.title"/> <s:property value="#book.price"/><br>
		</p>
	</s:iterator>
	
	<p>热销</p>
	<s:iterator value="bookListOfSaleAmount" status="st" var="book">
		<s:property value="#book.title"/> <s:property value="#book.price"/><br>
	</s:iterator>
	
	<p>推荐</p>
	<s:iterator value="bookListOfRandom" status="st" var="book">
		<s:property value="#book.title"/> <s:property value="#book.price"/><br>
	</s:iterator> --%>
	
	
 
</body>
</html>