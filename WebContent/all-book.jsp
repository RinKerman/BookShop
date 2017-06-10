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
		location.href = 'BookListAction?pageNum=' + link;
		//跳转到该页面
	}
</script>

<link href="css/layui.css" rel="stylesheet" />
</head>
<s:if test="#session.user.usertype.utype != 1">
	<jsp:forward page="/login" />
</s:if>
<body>
	<center>
		<fieldset class="layui-elem-field layui-field-title"
			style="margin-top: 20px;">
			<legend>默认表格</legend>
		</fieldset>
		<a>搜索图书</a>
		<form method="get" action="search.action">
			<input type="hidden" name="stage" value="true"> <select
				name="type" id="mySelect">
				<option value="title">书名</option>
				<option value="author">作者</option>
				<option value="press">出版社</option>
			</select> <input type="text" name="keyword" value="搜索"
				onFocus="this.value = '';"
				onBlur="if (this.value == '') {this.value = '搜索';}"> <input
				type='text' name='minPrice' value='0' class='minPrice'
				style='display: none; left: 95px; width: 20%; border: 1px solid gray;'>
			<span class='mySpan'
				style='position: absolute; left: 170px; display: none;'>——</span> <input
				type='text' name='maxPrice' value='0' class='maxPrice'
				style='display: none; width: 20%; left: 205px; border: 1px solid gray;'>
			<input type="submit" value="搜索">
		</form>


		<div class="layui-form">
			<table class="layui-table">
				<thead>
					<tr>
						<th>ID</th>
						<th>书名</th>
						<th>作者</th>
						<th>出版社</th>
						<th>入库日期</th>
						<th>价格</th>
						<th>销量</th>
						<th>库存</th>
						<th>图片</th>
						<th>分类</th>
						<th>备注</th>
						<th>推荐</th>
						<th>编辑</th>
						<th>删除</th>
					</tr>
				</thead>
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
							<td><a
								href="EditBookAction?bookNumber=<s:property value="#book.bid" />&actionType=picture"><img
									src="${book.picture}" width="130px" height="150px"></a></td>
							<td><s:property value="#book.booktype.btype" /></td>
							<td><s:property value="#book.note" /></td>
							<td>
								<s:if test="#book.recommendFlag == 1">
									<a href="SwitchRecommendAction?number=<s:property value="#book.bid" />"> <button>取消推荐</button>
									</a>
								</s:if>
								<s:if test="#book.recommendFlag != 1">
									<a href="SwitchRecommendAction?number=<s:property value="#book.bid" />"><button>设为推荐</button>
									</a>
								</s:if>
							</td>
							<td><a
								href="EditBookAction?bookNumber=<s:property value="#book.bid" />&actionType=info">修改</a></td>
							<td><a
								href="DeleteBookAction?number=<s:property value="#book.bid" />&actionType=info">删除</a></td>
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
						<td />
						<td />
						<td />
						<td><a href="EditBookAction?bookNumber=0&actionType=info">新增</a></td>
					</tr>
				</tbody>
			</table>
		</div>
		<s:if test="pageNum>1">
			<a href="BookListAction?pageNum=${PageNum-1}">上一页</a>&nbsp;&nbsp;
	</s:if>
		<a>当前页:<s:property value="PageNum" />&nbsp;&nbsp;
		</a> <a>总页数:<s:property value="TotalPage" />&nbsp;&nbsp;
		</a>
		<s:if test="pageNum<TotalPage">
			<a href="BookListAction?pageNum=${PageNum+1}">下一页</a>
		</s:if>
		</p>
		跳转到<input id="number" type="text" /> <input type="button" value="跳转"
			onclick="Jump()" />

	</center>
	<script type="text/javascript" src="js/jquerySession.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#mySelect').change(function() {
				if ($(this).children('option:selected').val() == "price") {
					//alert("选中了类型为价格");
					$(".keyword").hide();
					$(".minPrice").show();
					$(".maxPrice").show();
					$(".mySpan").show();
					/* $(".myForm").append("<input type='text' name='min' value='0' class='minPrice' style='left:95px;width:20%;border: 1px solid gray;'>"
					+"<span class='mySpan' style='position:absolute;left:170px;'>——</span>"
					+"<input type='text' name='max' value='0' class='maxPrice' style='width:20%;left:205px;border: 1px solid gray;'>");						 */
				} else {
					$(".keyword").show();
					$(".minPrice").hide();
					$(".maxPrice").hide();
					$(".mySpan").hide();
				}
			})
		})
		function redirect(pageNo, booktype) {
			$.session.set('amountFlag', '1');
			$.session.set('priceFlag', '1');
			window.location.href = "bookclassify?pageNo=" + pageNo
					+ "&booktype=" + booktype;
		}
	</script>
</body>
</html>