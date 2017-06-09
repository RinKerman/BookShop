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
    </script>
</head>
<body>
<!-- 头部 -->
<jsp:include page="head.jsp"></jsp:include>
<div class="middle-person">

    <div class="daohanlan">
        <ul class="layui-nav layui-nav-tree" lay-filter="demo">
            <li class="layui-nav-item"><a href="">会员中心</a></li>
            <li class="layui-nav-item  layui-nav-itemed">
                <a href="javascript:;">资料管理</a>
                <dl class="layui-nav-child">
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
        <div class="persondetaileddate">
            <form action="perfectdata" method="post">
                <div class="date">
                    <h4>会员中心|信息完善</h4>
                </div>
                <div class="name" >
                    <span style="font-size: 20px;font-weight:bold;">用户名:</span>
                    &nbsp;&nbsp;
					<s:textfield name="user.userName" value="%{#session.user.userName}"/>
				</div>
                <div class="email" >
                    <span style="font-size: 20px;font-weight:bold;">邮&nbsp;&nbsp;&nbsp;&nbsp;箱:</span>
                    &nbsp;&nbsp;
					<s:textfield name="user.email" value="%{#session.user.email}"/>                 
				</div>
				<div class="layui-form-item" style="margin-left:15%;margin-top:5%;">
				     <span style="font-size: 20px;font-weight:bold;">性&nbsp;&nbsp;&nbsp;&nbsp;别:</span>&nbsp;&nbsp;&nbsp;
				      <input type="radio" name="user.sex" value="男">男&nbsp;&nbsp;&nbsp;&nbsp;
				      <input type="radio" name="user.sex" value="女">女
				    <!-- <div class="layui-input-block">
				    </div> -->
				</div>
                <%-- <div class="newsex">
                    <div class="layui-form">
                        <div class="layui-form-item">
                            <label class="layui-form-label" >
                               	 <span style="font-size: 20px;font-weight:bold;">性别:</span>
                            </label>
                             <div class="layui-input-block">
                                <input type="radio" name="user.sex" value="男" />男
                                <input type="radio" name="user.sex" value="女" />女
                            </div>
                        </div>
                    </div>
                </div> --%>
                <div class="data">
                    <span style="font-size: 20px;font-weight:bold;">生&nbsp;&nbsp;&nbsp;&nbsp;日:</span>
                    &nbsp;&nbsp;

                    <div class="layui-inline">
                        <input class="layui-input" placeholder="点击输入生日"
                         onclick="layui.laydate({elem: this, festival: true})" 
                         name="user.birthday" value="${session.user.birthday}">
                    </div>
                </div>
                <div class="zhenname">
                    <span style="font-size: 20px;font-weight:bold;">真实姓名:</span>
                    <s:textfield name="user.name" value="%{#session.user.name}"/>
                </div>
                <div class="datasubmit">
                    <input type="submit" value="提交修改">
                </div>
            </form>
        </div>

    </div>
</div>

<!-- 尾部 -->
<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>