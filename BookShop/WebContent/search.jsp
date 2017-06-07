<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<link
	href='https://fonts.googleapis.com/css?family=Exo:100,200,300,400,500,600,700,800,900'
	rel='stylesheet' type='text/css'>
<script src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/move-top.js"></script>
<script src="js/responsiveslides.min.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript" src="js/slider.js"></script>
<script type="text/javascript" src="js/jquery.flexisel.js"></script>
<script type="application/x-javascript">
		 
addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); }

</script>
<!--飞入购物车-->
<script src="js/jquery.fly.min.js"></script>
<s:if test="#session.user != null">
	<script>
		$(function() {
			var offset = $("#toCart").offset();
			$(".hvr-shutter-in-vertical2").click(
					function(event) {
						var addcar = $(this);
						var img = addcar.parent().parent().parent().find('img')
								.attr('src');
						var flyer = $('<img class="u-flyer" src="'+img+'" style="z-index:99;">');
						flyer.fly({
							start : {
								left : event.pageX, //开始位置（必填）#fly元素会被设置成position: fixed
								top : event.pageY
							//开始位置（必填）
							},
							end : {
								left : offset.left + 10, //结束位置（必填）
								top : offset.top + 10, //结束位置（必填）
								width : 0, //结束时宽度
								height : 0
							//结束时高度
							},
							onEnd : function() { //结束回调
								addcar.css("cursor", "default").unbind('click');
								this.destory(); //移除dom
							}
						});
					});
			$(".hvr-shutter-in-vertical2").click(function(event) {
				//alert("点击了");
				var bid = $(this).parent().find("input[name=bid]").val();
				var uid = $(this).parent().find("input[name=uid]").val();
				var num = 1;
				//alert(bid);
				//alert(uid);
				$.ajax({
				   	url: "addToShoppingCart.action",  
				    type: "POST",  
				    data: {
				    	bid : bid,
				    	uid : uid,
				    	num : num
				    },
				    dataType: "text",  //服务器返回类型   xml/html/script/json/jsonp/text
				    traditional: true, 	//如果传递的是String或int型的为true.传递的是对象则为false 
				    success : function() {
				    	
				    }
				})
			})
		});
	</script>
</s:if>
<s:else>
	<script>
	$(function() {
		$(".hvr-shutter-in-vertical2").click(function() {
			alert("请先登录再进行购物");
		})
	});
	</script>
</s:else>
</head>
<body>
	<!-- 头 -->
	<s:include value="head.jsp"></s:include>
	<!-- 主体 -->
	<div class="container">
		<div class="content">
			<div class="content-top">
				<s:if test="searchResult.size() == 0">
					<div class="check-out">
						<h4 class="title">搜索结果为空</h4>
						<p class="cart">
							<br>点击 <a href="temp.jsp">这里</a> 继续购物
						</p>
					</div>
				</s:if>
				<s:else>
					<s:iterator value="searchResult" status="st" var="book">
						<div class="col-md-3 md-col">
							<div class="col-md">
								<a href="book?bookid=${book.bid}"> <img
									src="<s:property value='#book.picture'/>" alt=""/>
								</a>
								<div class="top-content" >
									<center><h5 style="font-size:18px;font-weight:bold;">
										<a href="book?bookid=${book.bid}">
										<s:property value="#book.title" /></a>
									</h5></center>
									<p>
										<s:property value='#book.author'/>/
										<s:property value='#book.press'/>/
										<s:property value='#book.addDate'/>
									</p><br>
									<div class="white">
										<input type="hidden" name="bid" value="<s:property value='#book.bid'/>"/>
										<input type="hidden" name="uid" value="<s:property value='#session.user.uid'/>"/>
										<a href="javascript:return false;"
											class="hvr-shutter-in-vertical hvr-shutter-in-vertical2 ">添加到购物车</a>
										<p class="dollar">
											<span class="in-dollar">¥</span><span><s:property
													value="#book.price" /></span>
										</p>
										<div class="clearfix"></div>
									</div>

								</div>
							</div>
						</div>
					</s:iterator>
				</s:else>
			</div>
		</div>
	</div>
	<!-- 尾 -->
	<s:include value="foot.jsp"></s:include>
</body>
</html>