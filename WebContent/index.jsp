<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>三味书屋</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<link
	href='https://fonts.googleapis.com/css?family=Exo:100,200,300,400,500,600,700,800,900'
	rel='stylesheet' type='text/css'>
<!-- <link href="css/sliderDemo.css" rel="stylesheet" type="text/css"> -->	
<script src="js/jquery.min.js"></script>
<!-- 响应式旋转木马插件jquery Flexisel -->
<script type="text/javascript" src="js/jquery.flexisel.js"></script>
<!-- 返回顶部 -->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<!-- 轮播图 -->
<script type="text/javascript" src="js/slider.js"></script>
<!-- jQuery轻量级响应式图片轮播插件 -->
<script src="js/responsiveslides.min.js"></script>

<script type="application/x-javascript">	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); 
	 function hideURLbar(){ window.scrollTo(0,1); } 
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
	<!--头部-->
	<jsp:include page="head.jsp"></jsp:include>
	
	<div class="banner-mat">
		<div class="container">
			<div class="banner">				
				<div class="slider">
					<ul class="rslides" id="slider1">
						<li>
							<a href="javascript:redirect(1,4)"><img src="images/banner.jpg" alt=""></a>						
						</li>
						<li>
							<img src="images/banner1.jpg" alt="">							
						</li>
						<li>
							<img src="images/banner2.jpg" alt="">							
						</li>
						<li>
							<a href="javascript:redirect(1,6)"><img src="images/banner3.jpg" alt=""></a>
						</li>
					</ul>
				</div>

			</div>
		</div>
	</div>
	<!--中间-->
	<div class="container">
		<div class="content">
			<!-- 新品上架 -->
			<div class="content-top">
				<h3 class="future">新品上架</h3>
				<div class="content-top-in">
					<s:iterator value="bookListOfDate" status="st" var="book">
						<div class="col-md-3 md-col" style="margin-bottom: 40px;">
							<div class="col-md">
								<a href="book?bookid=${book.bid}"> <img
									src="<s:property value='#book.picture'/>" alt="" />
								</a>
								<div class="top-content">
									<h5>
										<a href="book?bookid=${book.bid}"><s:property value="#book.title" /></a>
									</h5>
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


					<div class="clearfix"></div>
				</div>
			</div>
			<!--热销图书 -->
			<div class="content-middle">
				<h3 class="future">热销图书</h3>
				<div class="content-top-in">
					<s:iterator value="bookListOfSaleAmount" status="st" var="book">
						<div class="col-md-3 md-col" style="margin-bottom: 40px;">
							<div class="col-md">
								<a href="book?bookid=${book.bid}"><img
									src="<s:property value='#book.picture'/>" alt="" /></a>
								<div class="top-content">
									<h5>
										<a href="book?bookid=${book.bid}"><s:property value="#book.title" /></a>
									</h5>
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



					<div class="clearfix"></div>
				</div>
			</div>

			<!--推荐图书-->
			<div class="content-bottom">
				<h3 class="future">推荐图书</h3>
				<div class="content-bottom-in">
					<ul id="flexiselDemo2">
						<s:iterator value="bookListOfRandom" status="st" var="book">
							<li>
								<div class="col-md men">
									<a href="book?bookid=${book.bid}" class="compare-in "><img
										src="<s:property value='#book.picture'/>" alt="" />
										<%--  <div class="compare in-compare">
												<span>Add to Compare</span>
												<span>Add to Wishlist</span>
											</div> --%>
									<div class="top-content bag">
										<h5>
											<a href="book?bookid=${book.bid}"><s:property value="#book.title" /></a>
										</h5>
										<div class="white">
										<input type="hidden" name="bid" value="<s:property value='#book.bid'/>"/>
										<input type="hidden" name="uid" value="<s:property value='#session.user.uid'/>"/>
										<a href="javascript:return false;"
												class="hvr-shutter-in-vertical hvr-shutter-in-vertical2">添加到购物车</a>
											<p class="dollar">
												<span class="in-dollar">¥</span><span><s:property
														value="#book.price" /></span>
											</p>
											<div class="clearfix"></div>
										</div>
									</div>
								</div>
							</li>
						</s:iterator>
					</ul>
					<script type="text/javascript">
						$(window).load(function() {
							$("#flexiselDemo2").flexisel({
								visibleItems : 4,
								animationSpeed : 1000,
								autoPlay : true,
								autoPlaySpeed : 3000,
								pauseOnHover : true,
								enableResponsiveBreakpoints : true,
								responsiveBreakpoints : {
									portrait : {
										changePoint : 480,
										visibleItems : 1
									},
									landscape : {
										changePoint : 640,
										visibleItems : 2
									},
									tablet : {
										changePoint : 768,
										visibleItems : 3
									}
								}
							});

						});
					</script>
				</div>
			</div>
		</div>
	</div>
	<!--底部-->
	<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>