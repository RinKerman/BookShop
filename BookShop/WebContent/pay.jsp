<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>支付</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link rel="stylesheet" type="text/css" href="css/css1.css">
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<link
	href='https://fonts.googleapis.com/css?family=Exo:100,200,300,400,500,600,700,800,900'
	rel='stylesheet' type='text/css'>
<script type="text/javascript" src="js/jquery.1.4.2-min.js"></script>
<script type="text/javascript" src="js/js.js"></script>

<script type="text/javascript">
	var arr_pay = new Array();
	$(document).ready(function() {
		var tot_pay = 0;
		for (var i = 0; i < arr_pay.length; i++) {
			//var index = '#sp' + i;
			$("#sp" + i).text(arr_pay[i]);
			tot_pay += parseFloat(arr_pay[i]);
		}
		$("#tot").text(tot_pay.toFixed(2));
		
		//alert(tot.toFixed(2));
		//$("#sp0").text(arr[0]);
		//alert(arr[0]);
	})
</script>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<div class="container">
		<div class="content">
			<!-- 新品上架 -->
			<div class="content-top">
				<div class="content-top-tab">
					<div class="div1"></div>
					<div class="div2"></div>
					<div>&nbsp;&nbsp;商品信息</div>					
				</div>
				<div class="content-top-tab">
					<!-- 商品信息 -->
					<table border="1px" width="90%" align="center" rules="rows" cellspacing="10px">
						<tr class="title" bgcolor="#fb5e33" style="color:white">
							<th style="text-align:center;">商品信息</th>
							<th>商品数量</th>
							<th>商品单价</th>
							<th>总金额</th>
						</tr>
						<s:iterator value="#session.order.orderdetails" status="st"
							var="item">
							<script type="text/javascript">
								var quantity = <s:property value="#item.quantity"/>;
								var price = <s:property value="#item.book.price"/>;
								var sum = (parseFloat(quantity) * parseFloat(price))
										.toFixed(2);
								arr_pay[arr_pay.length] = sum;
								/* window.onload=function(){
									$("td[name=sum]").text(getSum());
								} */
							</script>
							<tr>
								<td><img alt="" width="100" height="100"
									src="<s:property value='#item.book.picture'/>"> <s:property
										value="#item.book.title" /></td>
								<td><s:property value="#item.quantity" /></td>
								<td><s:property value="#item.book.price" /></td>
								<td name="sum"><span id="sp<s:property value='#st.index'/>"></span></td>
							</tr>
						</s:iterator>
						<tr>
							
							<td name="total" colspan="4" style="text-align:right">总价:<span id="tot" style="display:inline-block; 
width:80px;text-align:left; "></span></td>
						</tr>
					</table>
					<div class="clearfix"></div>
				</div>
			</div>
			<s:if test="#session.order.orderstate.osId == 1">			<!-- 状态是否为未支付 -->
				<!--配送地址  -->
				<div class="content-middle">
					<div class="content-top-tab" style="padding-bottom:1em">
						<div class="div1"></div>
						<div class="div2"></div>
						<div>&nbsp;&nbsp;配送地址</div>					
					</div>
					<div class="content-top-tab" style="padding-top:1em">
						<!-- 配送地址 -->
						<div class="Caddress" id="Caddress">
							<div class="open_new">
								<button class="open_btn" onclick="javascript:onclick_open();">使用新地址</button>
							</div>
							<s:iterator value="#session.order.user.addresses" status="st"
								var="addr">
								<s:if test="#st.index == 0">
									<div class="add_mi div_active">									
										<p name="name"
											style="border-bottom: 1px dashed #ccc; line-height: 28px;">
											<s:property value="#session.order.user.name" />
										</p>
										<p name="deliverAddress">
											<s:property value="address" />
										</p>
										<p>
											<s:property value="#session.order.user.cellphone" />
										</p>
									</div>
								</s:if>
								<s:else>
									<div class="add_mi">									
										<p name="name"
											style="border-bottom: 1px dashed #ccc; line-height: 28px;">
											<s:property value="#session.order.user.name" />
										</p>
										<p name="deliverAddress">
											<s:property value="address" />
										</p>
										<p>
											<s:property value="#session.order.user.cellphone" />
										</p>
									</div>
								</s:else>
							</s:iterator>
						</div>					
						<div class="clearfix"></div>
					</div>
				</div>
				<div class="content-bottom">
					<div class="content-bottom-in">
						<!-- 确认按钮 -->
						<div class="confirm">
							<input class="open_btn" id="pay" type="button" value="确认支付" />
							<!-- onclick="javascript:onclick_pay();" -->
						</div>
					</div>
				</div>
			</s:if>
		</div>
	</div>
	<jsp:include page="foot.jsp"></jsp:include>
				<!--shade-->
					<div class="shade" style="display: none"></div>

					<!--shade_content-->
					<div class="shade_content" style="display: none">
						<div class="col-xs-12 shade_colse">
							<button onclick="javascript:onclick_close()">x</button>
						</div>
						<div class="nav shade_content_div">
							<div class="col-xs-12 shade_title">新增收货地址</div>
							<div class=" col-xs-12 shade_from">
								<!--action获取填写的数据之后传送到后台进行保存-->
								<form method="post" name="form1" action="">
									<div class="col-xs-12">
										<span class="span_style">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;区</span>
										<input class="input_style" id="region" type=""
											placeholder="&nbsp;&nbsp;请输入您的所在地区" value="">
									</div>
									<div class="col-xs-12">
										<span class="span_style">详细地址</span> <input
											class="input_style" id="address" type=""
											placeholder="&nbsp;&nbsp;请输入您的详细地址" value="">
									</div>
									<div class="col-xs-12">
										<span class="span_style">邮政编号</span> <input
											class="input_style" id="number_this" type=""
											placeholder="&nbsp;&nbsp;请输入您的邮政编号" value="">
									</div>
									<div class="col-xs-12">
										<span class="span_style">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</span>
										<input class="input_style" id="name_" type=""
											placeholder="&nbsp;&nbsp;请输入您的姓名" value="">
									</div>
									<div class="col-xs-12">
										<span class="span_style">手机号码</span> <input
											class="input_style" id="phone" type=""
											placeholder="&nbsp;&nbsp;请输入您的手机号码" value="">
									</div>
									<div class="col-xs-12">
										<input class="btn_remove"
											onclick="javascript:onclick_close();" type="button"
											value="取消"> <input class="sub_set" id="sub_setID"
											type="submit" value="提交">
									</div>
								</form>
							</div>
						</div>
					</div>
</body>
</html>