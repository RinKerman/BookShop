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
	<!--Select2实现全国省市区三级联动下拉菜单 - 素材家园-->
    <script type="text/javascript" src="select2/js/area.js"></script>
    <script type="text/javascript" src="select2/js/location.js"></script>
    <script src="select2/js/select2.js"></script>
    <script src="select2/js/select2_locale_zh-CN.js"></script>
    <link href="select2/css/common.css" rel="stylesheet"/>
    <link href="select2/css/select2.css" rel="stylesheet"/>
    

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
        function show() {
            $(".divform").css("display","block");
        }
        //ajax 
        $(document).ready(function(){
        	$('.submitaddress').click(function(){
    			$.ajax({
    				type:"POST",
    				url:"myaddress.action",
    			data:{
    					sheng:$('#loc_province').select2('data').text,
    					shi:$('#loc_city').select2('data').text,
    					qu:$('#loc_town').select2('data').text,
    					detail:$('#detail').val(),
    					email:$('#email').val()
    				}
    			})
    			alert("添加成功");
    			location.href='personcenter.jsp';
    		})
        });  
    </script>
</head>
<body>

<div class="middle-person">
<!-- 头部 -->
<jsp:include page="head.jsp"></jsp:include>
    <div class="daohanlan">
        <ul class="layui-nav layui-nav-tree" lay-filter="demo">
            <li class="layui-nav-item"><a href="">会员中心</a></li>
            <li class="layui-nav-item layui-nav-itemed">
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
                    <dd><a href="">账户余额</a></dd>
                </dl>
            </li>
        </ul>
    </div>
    <div class="person-date">
        <div class="address">
            <div class="date">
                <h4>会员中心|我的地址</h4>
            </div> 
            <s:iterator value="#session.address" status="statu" var="add">   
			    <div style="width:100%;border:1px solid #ededed;font-size: large;margin-top:2%;text-align:center">
                	<div>
                		地址：<s:property value="#add.address"/>
                	</div>
                	<div>
                		邮编：<s:property value="#add.zipCode"/>
                	</div>
            	</div> 
			</s:iterator> 
			<div>
			<input type="button" value="点击添加新地址" onclick="show()">
			</div>
			<div class="divform" style="display:none">
			<form >
                <select id="loc_province" style="width:120px;" >
                </select>
                <select id="loc_city" style="width:120px; margin-left: 10px">
                </select>
                <select id="loc_town" style="width:120px;margin-left: 10px" >
                </select>
           		<div>
           			详细地址:<input type="text" id="detail">
           		</div>
           		<div>
           			邮编:<input type="text" id="email">
           		</div>
           		<input type="button" value="添加" class="submitaddress">
           	</form>
            </div>
		</div>
    </div>
</div>

<!-- 尾部 -->
<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>