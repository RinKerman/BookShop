<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="css/login.css" rel="stylesheet" type="text/css" media="all" />
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />

    <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />

    <link href="css/register.css" rel="stylesheet" type="text/css"/>

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
            //alert(input_code+"----"+code);
            if(input_code.toLowerCase()==code.toLowerCase())
            {
                //验证码正确(表单提交)
                return true;
            }
            alert("请输入正确的验证码!");
            //验证码不正确,表单不允许提交
            return false;
        }
    </script>
</head>
<body>

<div class="middle">
    <div class="middle-right">
        <div class="logining">
            <div>
                <h2>密码登录</h2>
            </div>
            <form action="login" method="post">
            <div class="text" >
                <input type="text" placeholder="昵称/手机号码" name="user.userName"/>
            </div>

            <div class="psd" >
                <input type="password" placeholder="密码" name="user.password"/>
            </div>
            <div>
                <input type="text" id="vcode" placeholder="验证码" value="验证码" onfocus="this.value=''"
                       onblur="if(this.value=='')this.value='验证码'" />
                <span id="code" title="看不清，换一张" onclick="changeImg()"></span>
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