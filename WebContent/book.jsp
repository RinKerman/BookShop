<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />

    <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />

    <link href="css/bookshow.css" rel="stylesheet" type="text/css"/>

    <script src="js/jquery-3.2.1.js" type="text/javascript"></script>
    <script type="text/javascript">
    function checknum() {
        var num=$(".booknum").text();
        var num1=parseInt(num);
        if(num1==0){
            $(".decreasebook").attr("disabled",true);
        }
    }
    function addbook(){
        $(".decreasebook").attr("disabled",false);
        var num=$(".booknum").text();
        var num1=parseInt(num);
        num1=num1+1;
        $(".booknum").text(num1);
    }
    function decreasebook(){
        var num=$(".booknum").text();
        var num1=parseInt(num);
        if(num > 1){
        num1=num1-1;
        $(".booknum").text(num1);        	
        }
    }
    </script>
<!--飞入购物车-->
<script src="js/jquery.fly.min.js"></script>
<s:if test="#session.user != null">
	<script>
		$(function() {
			var offset = $("#toCart").offset();
			$(".submitbook").click(
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
								addcar.unbind('click');
								this.destory(); //移除dom
							}
						});
					});
			$(".submitbook").click(function(event) {
				//alert("点击了");
				/* var bid = $(this).parent().find("input[name=bid]").val();				
				var uid = $(this).parent().find("input[name=uid]").val(); */
				var bid = ${session.book.bid};
				var uid = ${session.user.uid};
				var num = $(".booknum").text();
				/* alert("图书id：" + bid);
				alert("用户id：" + uid);
				alert("购买数量：" + num);	 */			
				
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
		$(".submitbook").click(function() {
			alert("请先登录再进行购物");
		})
	});
	</script>
</s:else>
</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
<div class="middlebook">
    <div class="titlediv">
        图书 ><s:property value="#session.booktype"/>> <s:property value="#session.book.title"/>
    </div>
    <div class="bookdetail">
        <div class="leftimg">
            <img src=${session.book.picture}>
        </div>
        <div class="detailright">
            <div class="divT">
                	书名：<s:property value="#session.book.title"/>
            </div>
            <div class="jianjie">
					简介：<s:property value="#session.book.introduction"/>
            </div>
            <div class="divT">
                	作者：<s:property value="#session.book.author"/>
            </div>
            <div class="divT">
                	出版社：<s:property value="#session.book.press"/>
            </div>
            <div class="divT">
                	价格：<s:property value="#session.book.price"/>
            </div>
            <div class="divT">
            		库存：<s:property value="#session.book.stockNumber"/>
            </div>
            <div class="submitaa">
            	<a href="javascript:return false;" style="float:right" class="submitbook hvr-shutter-in-vertical hvr-shutter-in-vertical2 ">添加到购物车</a>
                数量：<input type="button" value="-" class="decreasebook" onclick="decreasebook()" onmouseover="checknum()">
                <span class="booknum">1</span>
                <input type="button" value="+" class="addbook" onclick="addbook()">
                <!-- <input type="button" class="submitbook" value="加入购物车"> -->
            </div>
        </div>
    </div>

</div>
<!--底部-->
	<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>