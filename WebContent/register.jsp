<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新用户注册</title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />

    <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />

    <link href="css/register.css" rel="stylesheet" type="text/css"/>

    <script src="js/jquery-3.2.1.js" type="text/javascript"></script>
    <script type="text/javascript">
        window.onload=changeImg;

        var code;//声明一个变量用于存储生成的验证码

        function changeImg(){

            var arrays=new Array(
                '1','2','3','4','5','6','7','8','9','0',
                'a','b','c','d','e','f','g','h','i','j',
                'k','l','m','n','o','p','q','r','s','t',
                'u','v','w','x','y','z',
                'A','B','C','D','E','F','G','H','I','J',
                'K','L','M','N','O','P','Q','R','S','T',
                'U','V','W','X','Y','Z'
            );
            code='';//重新初始化验证码

            //随机从数组中获取四个元素组成验证码
            for(var i=0;i<4;i++){
                //随机获取一个数组的下标
                var r=parseInt(Math.random()*arrays.length);
                code+=arrays[r];
                //alert(arrays[r]);
            }
            //alert(code);
            document.getElementById('code').innerHTML=code;//将验证码写入指定区域
        }
        //效验验证码(表单被提交时触发)
        function check(){
            //获取用户输入的验证码
            var input_code=$("#vcode").val();
            //alert(input_code+"----"+code);
            if(input_code.toLowerCase()!=code.toLowerCase())
            {
                //验证码正确(表单提交)
                $(".validatecode").text("请输入正确的验证码");
                return false;
            }
            if(!validateU()){
                return false;
            }
            //验证码不正确,表单不允许提交
            return true;
        }
    function validateU() {
        var tarp=0;
        $(".validatename").text("");
        $(".validateCpwd").text("");
        $(".validatepwd").text("");
        if($(".username input").val()==""){
            $(".validatename").text("用户名不能为空");
            tarp=1;
        }else{
        	if($(".username input").val().length < 4){
        		$(".validatename").text("用户名长度不能小于4");
                tarp=1;
        	}
        	if($(".username input").val().length > 12){
        		$(".validatename").text("用户名长度不能大于12");
                tarp=1;
        	}
        }
        if($(".pwd input").val()==""){
            $(".validatepwd").text("密码不能为空");
            tarp=1;
        }else{
        	if($(".pwd input").val().length < 4){
        		 $(".validatepwd").text("密码长度不能小于4");
                tarp=1;
        	}
        	if($(".pwd input").val().length > 12){
        		 $(".validatepwd").text("密码长度不能大于12");
                tarp=1;
        	}
        }
        if($(".pwd input").val()!=$(".confirmpwd input").val()){
            $(".validateCpwd").text("两次输入的密码不一致")
            tarp=1;
        }
        if(tarp==1)
            return false;
        else
            return true;
    }
  //ajax验证用户名重复
	var user = { 
    inintEvent: function(){ 
        $("input[name='user.userName']").unbind("blur"); /*移动开输入框的时候就会响应，user.User_LogName为输入框的名字*/
        $("input[name='user.userName']").bind("blur", function(){ 
            user.checkUser($(this).val()); 
        });
    }, 
    
    checkUser:function(logname){ 
        var parameter = { 
            logname:logname/*入参,为什么用这样表示,形参:实参？*/
        }; 

        $.post("userJSON_checkname.action",parameter,function(data){ /*userJSON_checkname为action的名字*/
                /*这里的参数date就是在struct.xml里面定义的参数checkresult，也即action的一个属性值*/ 
            if(data=="ok"){ 
                $("#message").text("该用户名可用");
                $("#message").css("color","blue"); 
            }else{ 
                $("#message").text("该用户名不可用");
                $("#message").css("color","red"); 
            } 
            
        }); 
    	} 
	}; 
	/*启动页面的时候绑定动作绑定*/
	$(document).ready(function(){ 
	    user.inintEvent(); /*绑定到用户名输入框*/
	}); 	
    </script>
</head>
<body>

<div class="middle" onmouseout="validateU()">
    <form action="register" method="post">	<!-- 校验验证码 onsubmit="return check()"  -->
    <div class="bodyborder">
        <div class="regis">
            <h2>新用户注册</h2>
        </div>
        <div class="username">
            <input type="text" placeholder="请输入用户名" name="user.userName">
            <s:property value="#session.error" /> 
            <label id="message"></label>
            <span class="validatename"></span>            
        </div>
        <div class="pwd">
            <input type="password" placeholder="用户密码" name="user.password">
            <span class="validatepwd"></span>
        </div>
        <div class="confirmpwd">
            <input type="password" placeholder="确认密码">
            <span class="validateCpwd"></span>
        </div>

        <div style="margin-bottom:25px;">
            <input type="text" id="vcode" placeholder="验证码" value="验证码" onfocus="this.value=''"
                   onblur="if(this.value=='')this.value='验证码'" />
            <span id="code" title="看不清，换一张" onclick="changeImg()"></span>
        </div>
        <div>
            <span class="validatecode"></span>
        </div>
        <div style="margin-bottom:25px;">
            <input type="submit" class="registerbutton" value="注册" >
        </div>
    </div>
    </form>
</div>

</body>
</html>