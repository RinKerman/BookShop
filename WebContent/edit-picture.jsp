<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.opensymphony.xwork2.util.ValueStack"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script Language="JavaScript" Type="text/javascript">
	function validateForm(frmUpload) {
		//检查是否提交了上传文件 否弹出提示 不需要这一功能的删除即可 
		if (document.all.uploadFile.value == "") {
			alert("没有选择上传的文件！");
			frmUpload.uploadFile.focus();
			return false;
		}
		//截取提交上传文件的扩展名
		var ext = frmUpload.uploadFile.value.match(/^(.*)(\.)(.{1,8})$/)[3];
		ext = ext.toLowerCase(); //设置允许上传文件的扩展名 
		if (ext == "jpg" || ext == "png") {
			return true;
		} else {
			alert("只允许上传.jpg或png文件，请重新选择需要上传的文件！");
			return false;
		}
	}
</script>

</head>
<s:if test="#session.user.usertype.utype != 2">
<jsp:forward page="/login" />
</s:if>
<body>
	<center>
		<h2>上传图片文件</h2>
		<hr>
		<table>
			<tr>
				<td><img src="${book.picture}" ></td>
				<td>
					<form name="frmUpload" enctype="multipart/form-data"
						action="UploadFileAction" method="post"
						onsubmit="return validateForm(this)">
						<table style="border: solid">
							<tbody>
								<tr>
									<td>ID</td>
									<td><input type="text" value="${book.bid}" name="book.bid"
										readonly></td>
								</tr>
								<tr>
									<td>书名</td>
									<td><input type="text" value="${book.title}" readonly></td>
								</tr>
								<tr>
									<td>作者</td>
									<td><input type="text" value="${book.author}" readonly></td>
								</tr>
								<tr>
									<td>出版社</td>
									<td><input type="text" value="${book.press}" readonly></td>
								</tr>
								<tr>
									<td>图片</td>
									<td><input type="file" name="uploadFile"></td>
								</tr>
								<tr>
									<td><input type="submit" value="上传"></td>
									<td><input type="button" name="Submit" value="返回"
										onclick="javascript:window.history.back(-1);"></td>
								</tr>
							</tbody>
						</table>
					</form>
				</td></table>
		
		
		
			
		
	</center>
	



















</body>
</html>