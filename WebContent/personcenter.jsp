<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

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

    <script>
        layui.use('element', function(){
            var element = layui.element(); //导航的hover效果、二级菜单等功能，需要依赖element模块

            //监听导航点击
            element.on('nav(demo)', function(elem){
                //console.log(elem)
                layer.msg(elem.text());
            });
        });
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
                    <!-- <dd><a href="">账户余额</a></dd> -->
                </dl>
            </li>
        </ul>
    </div>
    <div class="person-date">
        <div class="persontitle">
            <h4>欢迎你，用户：</h4>
        </div>
        <div class="persondetaileddate">
            <div class="date">
                <h4>个人信息</h4>
            </div>
            <div class="name">
                <h4>用户名:
                	<s:property value="#session.user.userName"/>
                </h4>
            </div>
            <div class="email">
                <h4>邮箱:
                	<s:if test='%{#session.user.email==null}'>
                		<s:property value="'请在信息完善中设置'"/>  
                	</s:if>
                	<s:else>
                		<s:property value="#session.user.email" /> 
                	</s:else>
                </h4>
            </div>
            <div class="sex">
                <h4>性别:
                	<s:if test='%{#session.user.sex==null}'>
                		<s:property value="'请在信息完善中设置'"/>    
                	</s:if>
                	<s:else>
                		<s:property value="#session.user.sex" /> 
                	</s:else>
                </h4>
            </div>
            <div class="data">
                <h4>生日:
                	<s:if test='%{#session.user.birthday==null}'>
                		<s:property value="'请在信息完善中设置'"/>
                	</s:if>
                	<s:else>
                		<s:property value="#session.user.birthday" /> 
                	</s:else>
                </h4>
            </div>
            <div class="zhenname">
                <h4>真实姓名:
                	<s:if test='%{#session.user.name==null}'>
                		<s:property value="请在信息完善中设置"/>   
                	</s:if>
                	<s:else>
                		<s:property value="#session.user.name" /> 
                	</s:else>
                
                </h4>
            </div>
            <div class="zhenname">
                <h4>账户余额:              	
                		<s:property value="#session.user.balance" />                
                </h4>
            </div>
        </div>

    </div>
</div>
<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>