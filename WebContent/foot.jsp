<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
<div class="footer">
	<p class="footer-class">
		Copyright &copy; 2017.网上书店.14级软件工程2班 <a
			href="temp.jsp" target="_blank" title="首页">首页</a>
	</p>

	<script type="text/javascript">
		$(document).ready(function() {

			/* var defaults = {
				containerID: 'toTop', // fading element id
				containerHoverID: 'toTopHover', // fading element hover id
				scrollSpeed: 1200,
				easingType: 'linear' 
			}; */

			$().UItoTop({
				easingType : 'easeOutQuart'
			});

		});
	</script>
	<a href="#" id="toTop" style="display: block;"> <span
		id="toTopHover" style="opacity: 1;"> </span></a>
	<s:if test="#session.user != null">
	<a href="queryShoppingCart.action?userId=${sessionScope.user.uid}" id="toCart" style="display: block;"> <span
	 style="opacity: 1;"> </span></a>
	 </s:if>
	 <s:else>
	 <a href="#" onclick="javascript:alert('请先登录再进行购物')" id="toCart" style="display: block;"> <span
	 style="opacity: 1;"> </span></a>
	 </s:else>

</div>
</body>
</html>