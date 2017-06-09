/**
 * Created by DaJin on 2017/5/9.
 */
var deliverAddr;//用来保存用户选中的地址
var receive;//收件人姓名
$(document).ready(function() {	
	$("#pay").click(function(){
		//alert("确认支付");		
		if(deliverAddr == undefined || deliverAddr == ''){	//如果为空，表示用户没有选择地址，默认选择第一个
			deliverAddr = $(".add_mi.div_active").find('p[name=deliverAddress]').text();
			receive = $(".add_mi.div_active").find('p[name=name]').text();
		}
			$.ajax({
		   	url: "payment.action",  
		    type: "POST",  
		    data: {
		    	address : deliverAddr,
		    	receive : receive
		    },
	//	    dataType: "text",  //服务器返回类型   xml/html/script/json/jsonp/text
	//	    traditional: true, 	//如果传递的是String或int型的为true.传递的是对象则为false 
		    dateType : "json",
		    success : function(msg) {
		    	var backdata = JSON.parse(msg);
		    	alert(backdata.result);
		    	window.location.href = "temp.jsp";
			},
		    error:function(msg){
		    	//alert(XMLResponse.responseText)
		    	var backdata = JSON.parse(msg);
		    	alert(backdata.result);
		    	window.location.href = "temp.jsp";
		    }
		})
	
	})
    //选中div改变边框颜色
    $(".add_mi").click(function () {
        $(".add_mi").each(function () {
            if($(this).hasClass("div_active")){
                $(this).removeClass("div_active");
            }
        })
        $(this).addClass("div_active");
        //alert($(this).find('p').text());  //选中div的全部内容
        //alert($(this).find('p[name=name]').text());
        deliverAddr = $(this).find('p[name=deliverAddress]').text();
        receive = $(this).find('p[name=name]').text();
        //alert($(this).find('p[name=deliverAddress]').text());
        //alert(deliverAddress);
    })
    //获取form表单的内容
    // $('#sub_setID').click(function() {
    //         var d = {};
    //         var t = $('sub_setID').serializeArray();
    //         $.each(t, function() {
    //             d[this.name] = this.value;
    //         });
    //         alert(JSON.stringify(d));
    // });
    //进行简单的表单验证
    $("#sub_setID").click(function() {
        var input_out = $(".input_style");
        for (var i = 0; i <= input_out.length; i++) {
            if ($(input_out[i]).val() == "") {
                $(input_out[i]).css("border", "1px solid red");
                return false;
            } else {
                $(input_out[i]).css("border", "1px solid #cccccc");
            }
        }
        var region = $("#region");
        var address = $("#address");
        var addr = region.val() + address.val();
        var number_this = $("#number_this");
        var name = $("#name_");
        var phone = $("#phone");
        deliverAddr = addr;
        receive = name.val();
        //移除其他已有的选中项
        $(".add_mi").each(function () {
            if($(this).hasClass("div_active")){
                $(this).removeClass("div_active");
            }
        })
        //添加新的选中项
        $("#Caddress").append(
           "<div class='add_mi div_active'><p name='name' style='border-bottom: 1px dashed #ccc;line-height: 28px;'>"+name.val()+"</p>" +
           "<p>"+region.val()+address.val()+"</p>" +
           "<p>"+phone.val()+"</p></div>");
        //添加绑定事件
        $(".add_mi").live("click",function () {
            $(".add_mi").each(function () {
                if($(this).hasClass("div_active")){
                    $(this).removeClass("div_active");
                }
            })         
            $(this).addClass("div_active");
            //alert($(this).find('p[name=name]').text());
        })
        onclick_close();	//隐藏添加地址的页面
        //添加地址
         $.ajax({
        	 url: "addAddress.action",  
 		    type: "POST",  
 		    data: {
 		    	name:name.val(),
 		    	addr:addr,
 		   		cellphone:phone.val(),
 		   		zipCode:number_this.val()	   		
 		    	//shoppingCarts : shoppingCarts
 		    },
 		    dataType: "text",  //服务器返回类型   xml/html/script/json/jsonp/text
 		    traditional: true, 	//如果传递的是String或int型的为true.传递的是对象则为false 
 		    success : function(o) {
 				//下面对用查询结果对div进行处理
 				//$("#tb<s:property value='#st.index + 1'/>").hide();
 				//alert("正在与服务器后台进行数据交互");
 				//window.location.href = "pay.jsp";
 		    	 return false;
 			},
 		    error:function(){
 		    	//alert(XMLResponse.responseText)
 		    	alert("出错了");
 		    	return false;
 		    }
         });
        return false;
    })
});


function onclick_close() {
    var shade_content = $(".shade_content");
    var shade = $(".shade");
    // if (confirm("确认关闭么！此操作不可恢复")) {
        shade_content.hide();
        shade.hide();
    // }
}

function onclick_open() {
    $(".shade_content").show();
    $(".shade").show();
    //var input_out = $(".input_style");
    // for (var i = 0; i <= input_out.length; i++) {
    //     if ($(input_out[i]).val() != "") {
    //         $(input_out[i]).val("");
    //     }
    // }
}


