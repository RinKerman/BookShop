<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>	
</head>
<body>
<div class="header">
		<div class="header-top">
			<div class="container">	
			<div class="header-top-in">			
				<div class="logo">
					<a href="temp.jsp">
						<img src="images/logo.png" alt=" " >
					</a>	<!--  -->
				</div>
				<div class="header-in">
					<ul class="icon1 sub-icon1">
							<s:if test="#session.user != null">
							<li  ><a href="personcenter.jsp">个人中心</a></li>
							</s:if>
							<s:else>
							<li  ><a href="#" onclick="javascript:alert('请先登录再进行购物')">个人中心</a></li>
							</s:else>
							<s:if test="#session.user != null">
							<li ><a href="queryShoppingCart.action?userId=${sessionScope.user.uid}" > 购物车 </a></li>
							</s:if>
							<s:else>
							<li ><a href="#" onclick="javascript:alert('请先登录再进行购物')" > 购物车 </a></li>
							</s:else>
							<li > 							
							<s:if test="#session.user != null">
								<a href="checkout.action" >退出登录	</a>		
							</s:if>
							<s:else>
								<a href="login.jsp" >登录	</a>	
							</s:else>
								 </li>	
							
						</ul>
				</div>
				<div class="clearfix"> </div>
			</div>
			</div>
		</div>
		<div class="header-bottom">
		<div class="container">
			<div class="h_menu4">
				<!-- <a class="toggleMenu" href="#">Menu</a> -->
				<ul class="nav">
					<li class="active"><a href="temp.jsp"><i> </i>首页</a></li>
					<li ><a href="javascript:redirect(1,1)" >哲学宗教</a> </li>
                    <li><a href="javascript:redirect(1,2)" >组织纪律</a></li>
                    <li><a href="javascript:redirect(1,3)">军事</a></li>
                    <li><a href="javascript:redirect(1,4)" >经济</a></li>
                    <li><a href="javascript:redirect(1,5)" >文化教育 </a></li>
                    <li><a href="javascript:redirect(1,6)" >文学 </a></li>
                    <li><a href="javascript:redirect(1,7)" >艺术 </a></li>
				</ul>
				<script type="text/javascript" src="js/nav.js"></script>
			</div>
		</div>
		</div>
		<div class="header-bottom-in">
			<div class="container">
			<div class="header-bottom-on">
			<s:if test="#session.user != null">
				<p class="wel"><a href="#">你已经登录,现在开始选购商品吧</a></p>			
			</s:if>
			<s:else>
				<p class="wel"><a href="login.jsp">欢迎光临,可以点我进行登录或者注册一个新的账户</a></p>
			</s:else>
			<div class="header-can">
					<div class="search">
						<form method="get" action="search.action" class="myForm" style="margin:0px;display:inline;">
					        <select name="type" id="mySelect" style="position: absolute;left: 0px;padding: 5px 15px;border: none;"> 
								<option value="title">书名</option> 
								<option value="author">作者</option> 
								<option value="press">出版社</option> 
								<option value="price">价格</option> 
							</select>     
							<input type="text" name="keyword" class="keyword" value="搜索(用空格分隔关键字)" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = '搜索(用空格分隔关键字)';}" >
							<input type='text' name='minPrice' value='0' class='minPrice' style='display:none;left:95px;width:20%;border:1px solid gray;'>
							<span class='mySpan' style='position:absolute;left:170px;display:none;'>——</span>
							<input type='text' name='maxPrice' value='0' class='maxPrice' style='display:none;width:20%;left:205px;border:1px solid gray;'>
							<input type="submit" value="">
						</form>
					</div>

					<div class="clearfix"> </div>
			</div>
			<div class="clearfix"></div>
		</div>
		</div>
		</div>
	</div>
	<script type="text/javascript" src="js/jquerySession.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){ 
			$('#mySelect').change(function(){ 
				if($(this).children('option:selected').val() == "price"){
					//alert("选中了类型为价格");
					$(".keyword").hide();
					$(".minPrice").show();
					$(".maxPrice").show();
					$(".mySpan").show();
					/* $(".myForm").append("<input type='text' name='min' value='0' class='minPrice' style='left:95px;width:20%;border: 1px solid gray;'>"
					+"<span class='mySpan' style='position:absolute;left:170px;'>——</span>"
					+"<input type='text' name='max' value='0' class='maxPrice' style='width:20%;left:205px;border: 1px solid gray;'>");						 */
				}else{ 
					$(".keyword").show();
					$(".minPrice").hide();
					$(".maxPrice").hide();
					$(".mySpan").hide();
				} 
			}) 
		}) 
		function redirect(pageNo,booktype){
			 $.session.set('amountFlag', '1');
			$.session.set('priceFlag', '1');
			window.location.href = "bookclassify?pageNo="+pageNo+"&booktype="+booktype;
		}
	</script>
</body>
</html>