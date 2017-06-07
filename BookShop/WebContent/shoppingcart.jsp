<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>购物车</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/shoppingcart.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.1.4.2-min.js"></script>
<script type="text/javascript" src="js/Calculation.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		// 全选        
		$(".allselect").click(function() {

			if ($(this).attr("checked")) {
				$(".gwc_tb2 input[name=newslist]").each(function() {
					$(this).attr("checked", true);
					//$(this).prop("checked",true);
				});
				GetCount();
			} else {
				$(".gwc_tb2 input[name=newslist]").each(function() {
					if ($(this).attr("checked")) {
						$(this).attr("checked", false);
						//$(this).prop("checked",true);
					} else {
						$(this).attr("checked", true);
						//$(this).prop("checked",true);
					}
				});
				GetCount();

			}

		});
		// 输出
		$(".gwc_tb2 input[name=newslist]").click(function() {
			GetCount();
			/* if($(this).attr("checked")){
				$(this).attr("checked", true);	
			}else{
				$(this).attr("checked", false);
			}	 */
			//alert(jQuery.fn.jquery);
			//alert($(this).val());
		});

		/* 	// 所有复选(:checkbox)框点击事件
		 $(".gwc_tb2 input[name=newslist]").click(function () {
		 if ($(this).attr("checked")) {
		 //$(this).next().css({ "background-color": "#3366cc", "color": "#ffffff" });
		 } else {
		 // $(this).next().css({ "background-color": "#ffffff", "color": "#000000" });
		 }
		 }); */
	});

	function GetCount() {
		var conts = 0;
		var aa = 0;
		$(".gwc_tb2 input[name=newslist]").each(function() {
			if ($(this).attr("checked")) {
				for (var i = 0; i < $(this).length; i++) {
					conts += parseInt($(this).val());
					aa += 1;
				}
			}
		});
		$("#shuliang").text(aa);
		$("#zong1").html((conts).toFixed(2));
		$("#jz1").css("display", "none");
		$("#jz2").css("display", "block");
	}
	
	function createOrder(){
		var index = [];	//记录图书索引
		var amount = [];//记录图书数量
		$(".gwc_tb2 input[name=newslist]").each(function() {
			if ($(this).attr("checked")) {
				//获取在shoppingCarts中的索引位置(同级元素)
				var st = $(this).next().val();
				var child = $(this).parent().parent().find("input[name=num]").val();//返回2级之后当前对象是tr
				//alert(child);
				index.push(st);	
				amount.push(child);
				//alert(st);				
			}
		});
		//alert(index);
		//alert(amount);
		//ajax调用后台的createOrder.action index作为参数传递
		$.ajax({  
		    url: "createOrder.action",  
		    type: "POST",  
		    data: {
		    	index : index,
		    	amount: amount
		    	//shoppingCarts : shoppingCarts
		    },
		    dataType: "text",  //服务器返回类型   xml/html/script/json/jsonp/text
		    traditional: true, 	//如果传递的是String或int型的为true.传递的是对象则为false 
		    success : function(o) {
				//下面对用查询结果对div进行处理
				//$("#tb<s:property value='#st.index + 1'/>").hide();
				//alert("正在与服务器后台进行数据交互");
				window.location.href = "pay.jsp";
			},
		    error:function(){
		    	//alert(XMLResponse.responseText)
		    	alert("你没有选中商品");
		    }
		});
	}
</script>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<div class="container">
		<div class="content">
			<s:if test="shoppingCarts.size() == 0">
				<div class="check-out">
					<h4 class="title">你的购物车空空如也</h4>
					<p class="cart">
						<br>点击 <a href="temp.jsp">这里</a> 继续购物
					</p>
				</div>
			</s:if>
			<s:else>
				<%-- <s:property value='shoppingCarts'/> --%>
				<!-- 显示购物车 -->
				<div class="gwc" style="margin: auto;">
					<table cellpadding="0" cellspacing="0" class="gwc_tb1">
						<tr>
							<td class="tb1_td1"><input id="Checkbox1" type="checkbox"
								class="allselect" /></td>
							<td class="tb1_td1">全选</td>
							<td class="tb1_td3">商品</td>
							<td class="tb1_td4"></td>
							<td class="tb1_td5">数量</td>
							<td class="tb1_td8">单价</td>
							<td class="tb1_td6">总价</td>
							<td class="tb1_td7">操作</td>
						</tr>
					</table>

					<!-- 显示商品详情和单个商品的总价 -->
					<%--从 queryShoppingCart action查询中传递 shoppingCarts 过来,遍历显示到界面上 --%>

					<%-- 循环开始 --%>
					<s:iterator value="shoppingCarts" status="st" var="shoppingCart">
						<!---商品加减算总数---->
						<script type="text/javascript">
							$(function() {
								//alert("<s:property value='#st.index + 1'/>");
								var t = $("#text_box<s:property value='#st.index + 1'/>");
								var p = $("#text_price<s:property value='#st.index + 1'/>");
								
								$("#add<s:property value='#st.index + 1'/>")
										.click(function() {
											t.val(parseInt(t.val()) + 1)
											setTotal();
											GetCount();
										})
								$("#min<s:property value='#st.index + 1'/>")
										.click(function() {
											if (t.val() > 1) {
												t.val(parseInt(t.val()) - 1)
												setTotal();
												GetCount();
											}
										})
								function setTotal() {
									$(
											"#total<s:property value='#st.index + 1'/>")
											.html(
													(parseFloat(t.val()) * parseFloat(p
															.val())).toFixed(2));
									$(
											"#newslist-<s:property value='#st.index + 1'/>")
											.val(
													parseFloat(t.val())
															* parseFloat(p.val()));
								}
								setTotal();

								//删除
								$("#del<s:property value='#st.index + 1'/>")
										.click(
												function() {
													if (confirm("确认删除吗?")) {
														//用ajax提交数据到服务器,服务器在后台对数据进行处理,不影响页面
														$
																.ajax({
																	type : "get",
																	url : "deleteShoppingCartItem.action",
																	data : {
																		userId : <s:property value="#shoppingCart.user.uid"/>,
																		bookId : <s:property value="#shoppingCart.book.bid"/>
																	},
																	success : function(
																			result) {
																		//下面对用查询结果对div进行处理
																		$(
																				"#tb<s:property value='#st.index + 1'/>")
																				.hide();
																		//alert("删除成功");
																	}
																});
													}

												});
							})
						</script>

						<table cellpadding="0" cellspacing="0" class="gwc_tb2"
							id="tb<s:property value='#st.index + 1'/>">
							<tr>
								<td class="tb2_td1"><input type="checkbox" value=""
									name="newslist"
									id="newslist-<s:property value='#st.index + 1'/>" />
									<input type="hidden" name="index" value="<s:property value='#st.index'/>">
									</td>

								<td class="tb2_td2"><a href="#"><img
										src="<s:property value='#shoppingCart.book.picture'/>" /></a></td>

								<td class="tb2_td3"><a href="#"><s:property
											value="#shoppingCart.book.title" /></a></td>

								<td class="tb1_td4">
									<input id="text_price<s:property value='#st.index + 1'/>" name="price"
									type="hidden" value="<s:property value='#shoppingCart.book.price'/>" />									
								</td>

								<td class="tb1_td5">
								<input id="min<s:property value='#st.index + 1'/>" name=""
									style="width: 20px; height: 18px; border: 1px solid #ccc;"
									type="button" value="-" /> 
								<input id="text_box<s:property value='#st.index + 1'/>"
									name="num" type="text"
									value="<s:property value='#shoppingCart.quantity'/>"
									readonly="readonly"
									style="width: 30px; text-align: center; border: 1px solid #ccc;"/>
								<input id="add<s:property value='#st.index + 1'/>" name=""
									style="width: 20px; height: 18px; border: 1px solid #ccc;"
									type="button" value="+" />
								</td>

								<td class="tb1_td8">
									<label><s:property value="#shoppingCart.book.price"/></label>
								</td>
								<td class="tb1_td6"><label
									id="total<s:property value='#st.index + 1'/>" class="tot"
									style="color: #ff5500; font-size: 14px; font-weight: bold;"><s:property
											value="#shoppingCart.book.price" /></label></td>

								<td class="tb1_td7"><a href="#"
									id="del<s:property value='#st.index + 1'/>">删除</a></td>
							</tr>
						</table>
					</s:iterator>
					<%-- 循环结束 --%>


					<table cellpadding="0" cellspacing="0" class="gwc_tb3">
						<tr>
							<td class="tb1_td1">&nbsp;</td>
							<td class="tb1_td1">&nbsp;</td>
							<td class="tb3_td1">&nbsp;</td>
							<td class="tb3_td2">已选商品 <label id="shuliang"
								style="color: #ff5500; font-size: 14px; font-weight: bold;">0</label>
								件
							</td>
							<td class="tb3_td3">合计(不含运费):<span>￥</span><span
								style="color: #ff5500;"><label id="zong1"
									style="color: #ff5500; font-size: 14px; font-weight: bold;"></label></span></td>
							<td class="tb3_td4"><span id="jz1">生成订单</span><a
								href="javascript:;" onclick="createOrder()"
								style="display: none;" class="jz2" id="jz2">生成订单</a></td>
						</tr>
					</table>
				</div>
			</s:else>
		</div>
	</div>

	<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>


