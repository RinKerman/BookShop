<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 20px;">
		<legend>默认表格</legend>
	</fieldset>
	<div class="layui-form">
	<center>
	<form action=AddManagerAction method="post">
		<table class="layui-table">
			<tr>
				<td>用户名</td>
				<td><input type="text" name="user.userName" /></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input type="text" name="user.password" /></td>
			</tr>
			<tr>
				<td>姓名</td>
				<td><input type="text" name="user.name" /></td>
			</tr>
			<tr>
				<td>电话</td>
				<td><input type="text" name="user.callphone" /></td>
			</tr>
			<tr>
				<td>邮件</td>
				<td><input type="text" name="user.email" /></td>
			</tr>	
		</table>
		<input type="hidden" name=newType value="1">
		
		<input type="submit"><input type="reset">
		</form>
		</center>
	</div>
</body>
</html>