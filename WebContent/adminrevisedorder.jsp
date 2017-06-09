<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>adminAllorder</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="Admin_css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="Admin_css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="Admin_css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="Admin_css/AdminLTE.min.css">
  <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
        page. However, you can choose any other skin. Make sure you
        apply the skin class to the body tag so the changes take effect.
  -->
  <link rel="stylesheet" href="Admin_css/skin-blue.min.css">

  <link rel="stylesheet" href="Admin_css/myorder.css">
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="Admin_js/html5shiv.min.js"></script>
  <script src="Admin_js/respond.min.js"></script>
  <script src="Admin_js/jquery.slimscroll.min.js"></script>
  <![endif]-->
  <!-- 提交表单 -->
	<script type="text/javascript">
	//ajax提交
	$(document).ready(function(){
        	$('#buttonsubmit').click(function(){
    			$.ajax({
    				type:"POST",
    				url:"reviceprice.action",
    			data:{
    					
    				}
    			})
    			alert("添加成功");
    			location.href='';
    		})
        });
	</script>

</head>
<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
<body class="hold-transition skin-blue slidebar-mini">
<div class="wrapper">

  <!-- Main Header -->
  <header class="main-header">

    <!-- Logo -->
    <a href="index2.html" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>A</b>LT</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>Admin</b>LTE</span>
    </a>

    <!-- Header Navbar -->
    <nav class="navbar navbar-static-top" role="navigation">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
      <!-- Navbar Right Menu -->
    </nav>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

      <!-- Sidebar user panel (optional) -->

      <!-- search form (Optional) -->
      <form action="#" method="get" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="q" class="form-control" placeholder="Search...">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>
      <!-- /.search form -->

      <!-- Sidebar Menu -->
      <ul class="sidebar-menu">
        <li class="header">HEADER</li>
        <!-- Optionally, you can add icons to the links -->

        <li class="treeview">
          <a href="#"><i class="fa fa-link"></i> <span>图书管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="#">增加图书</a></li>
            <li><a href="#">修改图书</a></li>
            <li><a href="#">查询图书</a></li>
            <li><a href="#">删除图书</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#"><i class="fa fa-link"></i> <span>人员管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="#">增加管理员</a></li>
            <li><a href="#">修改管理员信息</a></li>
            <li><a href="#">查询人员</a></li>
            <li><a href="#">删除管理员</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#"><i class="fa fa-link"></i> <span>订单管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="#">修改订单状态</a></li>
            <li><a href="#">查询订单</a></li>
            <li><a href="#">删除订单</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#"><i class="fa fa-link"></i> <span>图书分类管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="#">增加图书分类</a></li>
            <li><a href="#">修改图书分类</a></li>
            <li><a href="#">删除图书分类</a></li>
          </ul>
        </li>
      </ul>
      <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">


    <!-- Main content -->
    <section class="content">

      <!-- Your Page Content Here -->
      <div class="orderdata">
        <div class="persondetaileddate">
            <s:iterator value="#session.allorder" var="my">
            <c:if test="${param.orderid == my.orderId}"> 
            <div class="orders">
			    <div>	
			    	订单id:<s:property value="#my.orderId"/>
			    </div>
			    <div class="bookd">
			    	<s:iterator value="#my.bookpicture" var="picture">
			    		<div style="height:100px">
			    			<img src=${picture} width="100px" height="100px">
			    		</div>
			    	</s:iterator>
			    </div>
			    <div class="bookd">
			    	<s:iterator value="#my.bookname" >
			    		<div style="height:100px">
			    		<s:property/>
			    		</div>
			    	</s:iterator>
			    </div>
			    <div class="bookd">
			    	<s:iterator value="#my.booknum" var="num" status="spin1">
			    	<div style="height:100px">
			    		<input type="text" name="newprice" value="${num}" class="num${spin1.index}">本
			    	</div>
			    	</s:iterator>
			    </div>
			    <div class="bookd">
			    	<s:iterator value="#my.bookprice" status="spin1" var="price">
			    	<div style="height:100px">
                        <div style="float:left">
                            <input type="text"  name="newprice" value="${price}" class="price${spin1.index}">
			    		</div>
                        <button type="button" class="btn btn-default" 
                        onclick="
                            var prices=$('.price${spin1.index}').val();
                            var num=$('.num${spin1.index}').val();
                            var address=$('.address').val();
                            if(!isNaN(num)&&!isNaN(prices)){
                            window.location.href='reviceprice?index=${my.orderdetailid[spin1.index]}&newprice'+
                            '='+prices+'&num='+num+'&address='+address+'&oid=${my.orderId}';
                        	}
                        	else{
                        		alert('请在数量框和价格框输入数字');
                        	}
                        ">
						确定修改
			    		</button>
			    	</div>			    	
			    	</s:iterator>
			    </div>
			    <div>
			    	<div class="orderaddress">
			    		收货地址:
			    		<input type="text"  name="myaddress" value="${my.oaddress}" class="address">
			    	</div>
			    	<div class="ordertotal">
			    		总价:<s:property value="Totalprice"/>			    	
			    	</div>
			    </div>
			    <div style="margin-left:5%;margin-top:2%">
			    	<div style="float:left;">
			    		支付状态:<s:property value="orderstate"/>
			    	</div>
			    </div>
            </div>
            </c:if>
            </s:iterator>
       		
        </div>
      </div>


    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <!-- Main Footer -->
  <!--<footer class="main-footer">
    &lt;!&ndash; To the right &ndash;&gt;
    <div class="pull-right hidden-xs">
      Anything you want
    </div>
    &lt;!&ndash; Default to the left &ndash;&gt;
    <strong>Copyright &copy; 2016 <a href="#">Company</a>.</strong> All rights reserved.
  </footer>-->

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Create the tabs -->
    <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
      <li class="active"><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
      <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
    </ul>
    <!-- Tab panes -->
    <div class="tab-content">
      <!-- Home tab content -->
      <div class="tab-pane active" id="control-sidebar-home-tab">
        <h3 class="control-sidebar-heading">Recent Activity</h3>
        <ul class="control-sidebar-menu">
          <li>
            <a href="javascript:;">
              <i class="menu-icon fa fa-birthday-cake bg-red"></i>

              <div class="menu-info">
                <h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

                <p>Will be 23 on April 24th</p>
              </div>
            </a>
          </li>
        </ul>
        <!-- /.control-sidebar-menu -->

        <h3 class="control-sidebar-heading">Tasks Progress</h3>
        <ul class="control-sidebar-menu">
          <li>
            <a href="javascript:;">
              <h4 class="control-sidebar-subheading">
                Custom Template Design
                <span class="pull-right-container">
                  <span class="label label-danger pull-right">70%</span>
                </span>
              </h4>

              <div class="progress progress-xxs">
                <div class="progress-bar progress-bar-danger" style="width: 70%"></div>
              </div>
            </a>
          </li>
        </ul>
        <!-- /.control-sidebar-menu -->

      </div>
      <!-- /.tab-pane -->
      <!-- Stats tab content -->
      <div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
      <!-- /.tab-pane -->
      <!-- Settings tab content -->
      <div class="tab-pane" id="control-sidebar-settings-tab">
        <form method="post">
          <h3 class="control-sidebar-heading">General Settings</h3>

          <div class="form-group">
            <label class="control-sidebar-subheading">
              Report panel usage
              <input type="checkbox" class="pull-right" checked>
            </label>

            <p>
              Some information about this general settings option
            </p>
          </div>
          <!-- /.form-group -->
        </form>
      </div>
      <!-- /.tab-pane -->
    </div>
  </aside>
  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 2.2.3 -->
<script src="js/jquery-3.2.1.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="Admin_js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="Admin_js/app.min.js"></script>

<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. Slimscroll is required when using the
     fixed layout. -->
</body>
</html>
