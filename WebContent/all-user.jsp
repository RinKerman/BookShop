<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Insert title here</title>

<script type="text/javascript">
	function Jump() {
		var link = document.getElementById('number').value; // 取得文本框的值
		location.href = 'UserListAction?pageNum=' + link;
		//跳转到该页面
	}
</script>

<link href="css/layui.css" rel="stylesheet" />
</head>
<s:if test="#session.user.usertype.utype != 2">
<jsp:forward page="/login" />
</s:if>
<body>
	<center>
		<fieldset class="layui-elem-field layui-field-title"
			style="margin-top: 20px;">
			<legend>默认表格</legend>
		</fieldset>
		<div class="layui-form">
			<table class="layui-table">
				<thead>
					<tr>
						<th>ID</th>
						<th>用户名</th>
						<th>余额</th>
						<th>姓名</th>
						<th>电话</th>
						<th>生日</th>
						<th>性别</th>
						<th>邮件</th>
						<th>类型</th>
						<th>删除</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator id="user" value="userList">
						<tr>
							<td><s:property value="#user.uid" /></td>
							<td><s:property value="#user.userName" /></td>
							<td><s:property value="#user.balance" /></td>
							<td><s:property value="#user.name" /></td>
							<td><s:property value="#user.callphone" /></td>
							<td><s:property value="#user.birthday" /></td>
							<td><s:property value="#user.sex" /></td>
							<td><s:property value="#user.email" /></td>
							<td><s:property value="#user.usertype.utype" /></td>
							<td><a
								href="DeleteUserAction?number=<s:property value="#user.uid" />">删除</a></td>
						</tr>
					</s:iterator>
					<tr>
						<td />
						<td />
						<td />
						<td />
						<td />
						<td />
						<td />
						<td />
						<td />
						<td />
						<a href="add-manager.jsp">新增</a>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<s:if test="pageNum>1">
			<a href="UserListAction?pageNum=${PageNum-1}">上一页</a>&nbsp;&nbsp;
	</s:if>
		<a>当前页:<s:property value="PageNum" />&nbsp;&nbsp;
		</a>
		
			<a>总页数:<s:property value="TotalPage" />&nbsp;&nbsp;
			</a>
		
		<s:if test="pageNum<TotalPage">
		<a href="UserListAction?pageNum=${PageNum+1}">下一页</a>
</s:if>
		</p>
		跳转到<input id="number" type="text" /> <input type="button" value="跳转"
			onclick="Jump()" />
	</center>
</body>
</html>