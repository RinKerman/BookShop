<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />

    <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />

    <link href="css/bookshow.css" rel="stylesheet" type="text/css"/>

    <script src="js/jquery-3.2.1.js" type="text/javascript"></script>
    <script type="text/javascript">
        function checknum() {
            var num=$(".booknum").text();
            var num1=parseInt(num);
            if(num1==0){
                $(".decreasebook").attr("disabled",true);
            }
        }
        function addbook(){
            $(".decreasebook").attr("disabled",false);
            var num=$(".booknum").text();
            var num1=parseInt(num);
            num1=num1+1;
            $(".booknum").text(num1);
        }
        function decreasebook(){
            var num=$(".booknum").text();
            var num1=parseInt(num);
            num1=num1-1;
            $(".booknum").text(num1);
        }
        
        //控制价格排序按钮
        function priceonclick(){
        	var flag = parseInt($.session.get('priceFlag'));
       	 	flag = (flag + 1) % 2;
       	 	if(flag){
       	 	$.session.set('priceFlag',flag);
            window.location.href="bookclassify?pageNo=${currentPage}&booktype=${booktype}& sortflag=1";       	 		
       	 	}else{
       	 	$.session.set('priceFlag',flag);
       	 	window.location.href="bookclassify?pageNo=${currentPage}&booktype=${booktype}& sortflag=3";
       	 	}
        }
        /* function priceonclick1(){
            window.location.href="bookclassify?pageNo=${currentPage}&booktype=${booktype}& sortflag=3";
        } */
        
        function Amountonclick(){	
        	 var flag = parseInt($.session.get('amountFlag'));
        	 flag = (flag + 1) % 2;
        	 //alert(flag);
        	 if(flag){
        		 $.session.set('amountFlag',flag);
        		window.location.href="bookclassify?pageNo=${currentPage}&booktype=${booktype}& sortflag=2";        		        		 
        	 }else{
        		 $.session.set('amountFlag',flag);
        		window.location.href="bookclassify?pageNo=${currentPage}&booktype=${booktype}& sortflag=4";	 
        	 }
        	 
        }
        /* function Amountonclick1(){
            window.location.href="bookclassify?pageNo=${currentPage}&booktype=${booktype}& sortflag=4";
        } */
        function Amountonclicks(){
            window.location.href="bookclassify?pageNo=${currentPage}&booktype=${booktype}& sortflag=0";
        }
        function sortarrge() {
            var max=$(".maxprice").val();
            var min=$(".minprice").val();
            window.location.href="bookclassify?pageNo=${currentPage}&booktype=${booktype}& sortflag=5 & max="+max+"&min="+min;
        }
    </script>
</head>
<body>
<!--头部-->
	<jsp:include page="head.jsp"></jsp:include>
<div class="middlebook">
	<input type="button" value="综合" title="综合排序" class="buttonsort" style="margin-left: 5%" onclick="Amountonclicks()">
    <input type="button" value="销量" title="单击销量降序，双击销量升序" class="buttonsort2" onclick="Amountonclick()">
    <input type="button" value="价格" title="单击价格降序，双击价格升序" class="buttonsort1" onclick="priceonclick()">
    <input type="text" class="minprice" style="width:50px">&nbsp;——
    <input type="text" class="maxprice" style="width:50px">
    <input type="button" value="确定" class="buttonsubmits" onclick="sortarrge()">
    <div class="titlediv">
        图书  > <s:property value="#session.booktype"/>
         
    </div>
    <div class="booklist">
    <s:iterator value="#session.booklist" var="bl">
    <%-- <div class="bookclass">
        <div class="bookimg">
        	<a href="book?bookid=${bl.bid}"><img src=${bl.picture}></a>            
        </div>
        <div class="booktitle">
            <a href="book?bookid=${bl.bid}" >
            	<s:property value="#bl.title"/>
            </a>
        </div>
        <div class="bookprice">
          	  ￥:<s:property value="#bl.price"/>
            &nbsp;&nbsp;

           	已售:<s:property value="#bl.salesAmount"/>
        </div>
    </div> --%>
    <div class="col-md-3 md-col" style="margin: 35px;">
		<div class="col-md">
			<a href="book?bookid=${bl.bid}"> <img
				src="<s:property value='#bl.picture'/>" alt="" />
			</a>
			<div class="top-content">
				<h5>
					<a href="book?bookid=${bl.bid}"><s:property value="#bl.title" /></a>
				</h5>
				
				<div class="white">
				<input type="hidden" name="bid" value="<s:property value='#bl.bid'/>"/>
				<input type="hidden" name="uid" value="<s:property value='#bl.user.uid'/>"/>
					<p class="dollar" style="float:left;">
						<span class="in-dollar" style="float:left;">¥</span><span><s:property
								value="#bl.price" /></span>
					</p>
					<p class="dollar">
						<span>销量</span><span class="in-dollar"><s:property value="#bl.salesAmount"/></span>
					</p>					
					<div class="clearfix"></div>
				</div>
	
			</div>
		</div>
	</div>
    </s:iterator>
    </div>
    <div class="bookpaging">
	[<a href="bookclassify?pageNo=1 & booktype=${booktype} & sortflag=${currentsort}">首页</a>]
	<s:if test="currentPage>1">
		[<a href="bookclassify?pageNo=${currentPage-1}&booktype=${booktype} & sortflag=${currentsort}">上一页</a>]
	</s:if>
	<s:if test="currentPage<totalPage">
		[<a href="bookclassify?pageNo=${currentPage+1}&booktype=${booktype}& sortflag=${currentsort}">下一页</a>]
	</s:if>
	</div>
	
</div>
<!--底部-->
	<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>