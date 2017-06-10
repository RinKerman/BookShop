<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书分类</title>
<link rel="stylesheet" type="text/css" href="css/css1.css">
<style type="text/css">
    td {text-align:center;}
    a{font-size:20px;}
</style>
<script type="text/javascript" src="js/jquery.1.4.2-min.js"></script>
<script>
	function onclick_close() {
	    var shade_content = $(".shade_content");
	    var shade = $(".shade");
	    // if (confirm("确认关闭么！此操作不可恢复")) {
	        shade_content.hide();
	        shade.hide();
	    // }
	}
	function onclick_open() {
	    $(".shade_content").show();
	    $(".shade").show();
	}	
	$(document).ready(function() {
		$(".change").click(function(){
			var tid = $(this).parent().parent().find(".tid").text();
			var btype = $(this).parent().parent().find(".btype").text();
			$("#tid").val(tid);
			$("#type").val(btype);	
		})
		$(".add").click(function(){			
			$("#tid").val('');
			$("#type").val('');	
		})
		$(".delete").click(function(){			
			if(confirm("此操作不可逆，确定吗？")){
				var tid = $(this).parent().parent().find(".tid").text();
				window.location.href="DeleteBookTypeAction?tid="+tid;
			}
		})
	})
</script>
</head>
<body>
	<center><font color="gray"><h3>所有图书类型如下</h3></font></center>
	<table  border="1" width="70%" cellpadding="15" cellspacing="0" align="center" style="border-color:#e2e2e2;">
		<tr>
			<th>编号</th>
			<th>图书类型</th>
			<th>操作</th>
		</tr>
		<s:iterator value="bookType" status="st" var="type">
			<tr>
				<td class="tid"><s:property value="#type.tid" /></td>
				<td class="btype"><s:property value="#type.btype" /></td>
				<td><a href="javascript:onclick_open();" class="change" id="change<s:property value="#st.index"/>">修改</a> | 
				<a href="javascript:false;" class="delete">删除</a></td>				
			</tr>			
		</s:iterator>	
			<tr>
				<td></td>
				<td></td>
				<td><a href="javascript:onclick_open();" class="add">增加</a></td>
			</tr>
	</table>
	<!--shade-->
	<div class="shade" style="display: none"></div>

	<!--shade_content-->
	<div class="shade_content" style="height:300px;display: none;">
		<div class="col-xs-12 shade_colse">
			<button onclick="javascript:onclick_close()">x</button>
		</div>
		<div class="nav shade_content_div">
			<div class="col-xs-12 shade_title">维护图书类型</div>
			<div class=" col-xs-12 shade_from">
				<!--action获取填写的数据之后传送到后台进行保存-->
				<form method="post" name="form1" action="UpdateBookTypeAction">
					<input type="hidden" id="tid" name="tid" class="" value=""/>
					<div class="col-xs-12">
						<span class="span_style">图书类型</span>
						<input class="input_style" id="type" type="text"
							 name="type" value="">	<!-- placeholder="&nbsp;&nbsp;请输入图书类型" -->
					</div>
					<div class="col-xs-12">
						<input class="btn_remove"
							onclick="javascript:onclick_close();" type="button"
							value="取消"> 
						<input class="sub_set" id="sub_setID"
							type="submit" value="提交">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>