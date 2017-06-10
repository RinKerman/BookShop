<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!--layui的js  css 文件-->
    <link rel="stylesheet" href="css/layui.css"  media="all">
    <script src="layui.js" charset="utf-8"></script>
    <!-- header的css文件 -->
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />

    <link href="css/personcenter.css" rel="stylesheet" type="text/css">

    <script src="js/jquery-3.2.1.js"></script>

    <script>
        layui.use('element', function(){
            var element = layui.element(); //导航的hover效果、二级菜单等功能，需要依赖element模块

            //监听导航点击
            element.on('nav(demo)', function(elem){
                //console.log(elem)
                layer.msg(elem.text());
            });
        });
        layui.use('form', function(){
            var form = layui.form();

            //各种基于事件的操作，下面会有进一步介绍
        });
        layui.use('laydate', function(){
            var laydate = layui.laydate;

            var start = {
                min: laydate.now()
                ,max: '2099-06-16 23:59:59'
                ,istoday: false
                ,choose: function(datas){
                    end.min = datas; //开始日选好后，重置结束日的最小日期
                    end.start = datas //将结束日的初始值设定为开始日
                }
            };

            var end = {
                min: laydate.now()
                ,max: '2099-06-16 23:59:59'
                ,istoday: false
                ,choose: function(datas){
                    start.max = datas; //结束日选好后，重置开始日的最大日期
                }
            };

            document.getElementById('LAY_demorange_s').onclick = function(){
                start.elem = this;
                laydate(start);
            }
            document.getElementById('LAY_demorange_e').onclick = function(){
                end.elem = this
                laydate(end);
            }
        });
        function checkpwd() {
            var pwd1=$(".newpwd input").val();
            var pwd2=$(".newpwd1 input").val();
            alert(pwd2+pwd1);
            if(pwd1!=pwd2){
                $(".tishi").text("两次输入密码不相同");
                return false;
            }
            return true;
        }
        
    </script>
</head>
<body>
<!-- 头部 -->
<jsp:include page="head.jsp"></jsp:include>
<div class="middle-person">

    <div class="daohanlan">
        <ul class="layui-nav layui-nav-tree" lay-filter="demo">
            <li class="layui-nav-item"><a href="">会员中心</a></li>
            <li class="layui-nav-item layui-nav-itemed">
                <a href="javascript:;">资料管理</a>
                <dl class="layui-nav-child ">
                    <dd><a href="personcenter.jsp">个人信息</a></dd>
                    <dd><a href="perfectdata.jsp">信息完善</a></dd>
                    <dd><a href="myaddress.jsp">我的地址</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item layui-nav-itemed">
                <a href="javascript:;">订单管理</a>
                <dl class="layui-nav-child ">
                    <dd><a href="myorder?pageNo=1">我的订单</a></dd>
                    <dd><a href="">未支付订单</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item layui-nav-itemed">
                <a href="javascript:;">账号安全</a>
                <dl class="layui-nav-child">
                    <dd><a href="pwdrevise.jsp">修改密码</a></dd>
                   <!--  <dd><a href="">账户余额</a></dd> -->
                </dl>
            </li>
        </ul>
    </div>
    <div class="orderdata">
        <div class="persondetaileddate">
                <div class="date">
                    <h4>订单中心|我的订单</h4>
                </div>
            <s:iterator value="#session.myorder" var="my">
            <div class="orders">
			    <div>	
			    	订单id:<s:property value="#my.orderId"/>
			    </div>
			    <div class="bookd">
			    	<s:iterator value="#my.bookpicture" var="picture">
			    		<div style="height:50px">
			    			<img src=${picture} width="40px" height="40px">
			    		</div>
			    	</s:iterator>
			    </div>
			    <div class="bookd">
			    	<s:iterator value="#my.bookname" >
			    		<div style="height:50px">
			    		<s:property/>
			    		</div>
			    	</s:iterator>
			    </div>
			    <div class="bookd">
			    	<s:iterator value="#my.booknum" >
			    	<div style="height:50px">
			    		<s:property/>
			    	</div>
			    	</s:iterator>
			    </div>
			    <div class="bookd">
			    	<s:iterator value="#my.bookprice" >
			    	<div style="height:50px">
			    		<s:property/>
			    	</div>
			    	</s:iterator>
			    </div>
			    <div class="ordertotal">
			    	总价:<s:property value="Totalprice"/>
			    </div>
			    <div class="orderstate">
			    	<div style="float:left;">支付状态:<s:property value="orderstate"/></div>
			    	<div style="float:right;margin-right:5%;margin-bottom:5px;">
			    		<s:if test="orderstate == '未支付'">
			    		<input type="button" value="去支付" 
			    		onclick="javascript:window.location.href='payAgain.action?oid='+<s:property value='#my.orderId'/>"/>
			    	</s:if>
			    	<s:if test="orderstate == '已支付'">
			    		<input type="button" value="确认收货" 
			    		onclick="javascript:if(confirm('此操作不可逆，确认吗?')){
			    			window.location.href='receiveConfirm.action?oid='+<s:property value='#my.orderId'/>;
			    		}" />
			    		
			    	</s:if>
			    	</div>
			    	
			    </div>
            </div>
            </s:iterator>
        </div>
        <div style="margin-left:40%">
        	<ul class="pagination pagination-lg">
        	<s:if test='%{currentPage>1}'>
				<li><a href="myorder?pageNo=${currentPage-1}">&laquo;</a></li>
			</s:if>
			<s:if test='%{totalPage>0}'>
			<c:forEach var="i" begin="1" end="${totalPage}">
				<li><a href="myorder?pageNo=${i}">${i}</a><>
			</c:forEach>
			</s:if>
			<s:if test="currentPage<totalPage">
				<li><a href="myorder?pageNo=${currentPage+1}">&raquo;</a></li>>
			</s:if>
			</ul>
        </div>
</div>
	<!-- 尾部 -->
	<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>