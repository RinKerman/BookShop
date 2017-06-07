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
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />

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
            <li class="layui-nav-item layui-nav-itemed" >
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
                    <dd><a href="">账户余额</a></dd>
                </dl>
            </li>
        </ul>
    </div>
    <div class="person-date">
        <div class="persondetaileddate">
            <form onsubmit="return checkpwd()" action="revisepwd">
                <div class="date">
                    <h4>账号安全|修改密码</h4>
                </div>
                <div class="pwdrevise">
                    <div class="oldpwd">
                        原密码：<input type="password" name="oldpassword"/>
                    </div>
                    <s:property value="#session.errorpwd"/>
                    <div class="newpwd">
                        新密码：<input type="password" name="newpassword"/>
                    </div>
                    <div class="newpwd1">
                        重新输入新密码：<input type="password" >
                    </div>
                    <span class="tishi"></span>
                </div>
                <div class="submitpwd">
                    <input type="submit" value="点击修改">
                </div>
            </form>
        </div>

    </div>
</div>

<!-- 尾部 -->
<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>