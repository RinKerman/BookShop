<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="css/login.css" rel="stylesheet" type="text/css" media="all" />
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />

    <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
   
    <script src="js/jquery-3.2.1.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(changeImg);
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
        $(".validatenametext").text("");
        $(".validatepasd").text("");
        if($(".text input").val()==""){
            $(".validatenametext").text("用户名不能为空");
            tarp=1;
        }else{
        	if($(".text input").val().length < 4){
        		$(".validatenametext").text("用户名长度不能小于4");
                tarp=1;
        	}
        	if($(".text input").val().length > 12){
        		$(".validatenametext").text("用户名长度不能大于12");
                tarp=1;
        	}
        }
        if($(".psd input").val()==""){
            $(".validatepasd").text("密码不能为空");
            tarp=1;
        }else{
        	if($(".psd input").val().length < 4){
        		 $(".validatepasd").text("密码长度不能小于4");
                tarp=1;
        	}
        	if($(".psd input").val().length > 12){
        		 $(".validatepasd").text("密码长度不能大于12");
                tarp=1;
        	}
        }
        if(tarp==1)
            return false;
        else
            return true;
    }
    </script>

</head>
<body>
<div style="margin-left: 10%;
    width: 80%;
    height: 800px;
    border: 1px solid white;
    background:url(back1.jpg) no-repeat 0 center;
    background-size:cover;
    ">
    <div class="middle-right">
        <div class="logining" onmouseout="validateU()">
            <div>
                <h2>密码登录</h2>
            </div>
            <form action="login" method="post">	<!-- 校验验证码 onsubmit="return check()" -->
            	<div style="margin-left:30px;color:red">
            		<s:property value="#session.errorlogin" />
            	</div>
                <div class="text" >
                    <input type="text" placeholder="昵称/手机号码" name="user.userName"/>
                	<span class="validatenametext"></span>
                </div>

                <div class="psd" >
                    <input type="password" placeholder="密码" name="user.password"/>
                    <span class="validatepasd"></span>
                </div>
                <div>
                    <input type="text" id="vcode" placeholder="验证码" value="验证码" onfocus="this.value=''"
                           onblur="if(this.value=='')this.value='验证码'" />
                    <span id="code" title="看不清，换一张" onclick="changeImg()"></span>
                    <span class="validatecode" style="margin-left: 30px;"></span>
                </div>
                <div>
                    <input type="submit" class="loginbutton" value="登录" >
                </div>
            </form>
            <div class="register">
                <a href="register.jsp">立即注册</a>
            </div>

        </div>
    </div>
</div>
</body>
</html>