<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<s:form method="post" action="simulationLogin.action">
		<s:textfield label="用户名" name="user.userName"></s:textfield>
		<s:password label="密码" name="user.password"></s:password>
		<s:submit value="登录"></s:submit>
	</s:form>
</body>
</html>