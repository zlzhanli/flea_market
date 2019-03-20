
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: SHASK
  Date: 2019/3/12
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单详情</title>
    <link rel="stylesheet" type="text/css" href="${css}/style.css">
    <script src="${jquery}/jquery-3.0.0.js" type="text/javascript"></script>
    <script src="${bootstrap}/js/bootstrap.js" type="text/javascript"></script>
    <link rel="stylesheet" href="${bootstrap}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${css}/style.css">
    <link rel="stylesheet" type="text/css" href="${css}/app.css">
    <link rel="stylesheet" href="${css}/cropper.min.css">
    <link rel="stylesheet" href="${css}/ImgCropping.css">
    <script type="text/javascript" src="${js}/fly.js"></script>
    <link rel="stylesheet" href="${css}/homepage.css">
    <link rel="stylesheet" href="${css}/test.css">
    <link rel="stylesheet" href="${css}/animate.min.css">
    <link rel="stylesheet" href="${css}/test.css">

    <%-- 详情样式 --%>
    <style>
        .content {
            margin-left: 15%;
            align-self: center;
            width: 70%;
            margin-top: 100px;
        }
        .goods {
            margin-top: 100px;
        }
        td {
            width: 300px;
        }
        th {
            width: 300px;
        }
    </style>

    <%--检测登录--%>
    <script >

        //检测登录
        $(function () {
            $.post("../customer_getCustomerInfo.action",function (data) {
                if(data.msg=="ok"){
                    $("#loginOrNot").html("<div style='float: left;'> <img src='../upload/"+data.photo+"' class='photo img-circle'/></div>")
                    $("#loginOrNot").append("&nbsp;&nbsp;<div style='float:left; margin-left: 10px;margin-top: 5px'> <a style='font-size: 14px'>" +data.nickName+"</a></div>");
                    $("#loginOrNot").click(
                        function () {
                            window.location.href="../customer_infoUI.action";
                        }

                    );
                }
            },"json");
        })
    </script>



</head>
<body style="background-color: #ededed">

<%-- 页头 --%>
<div id="header" class="header">
    <div class="main">
        <span id="header-logo" style="display: flex;justify-content: left;align-items: center;"><img
                style="height: 35px;margin-right: 10px;" src="/static/images/logo.png" alt=""><span>跳蚤市场</span></span>
        <ul style="margin-left: 55px">
            <li id="navbar-homepage"><a href="../main_index.action" target="_self">首页</a></li>
            <li id="navbar-apk"><a class="barActive" href="../main_shop.action" target="_self">自营商城</a></li>
            <li id="navbar-game"><a href="../main_forum.action" target="_self">跳蚤论坛</a></li>
            <li id="navbar-market"><a href="../main_app.action" target="_self">跳蚤市场APP</a></li>
            <li id="navbar-contact"><a href="../main_linkUs.action" target="_self">联系我们</a></li>
            <li id="navbar-about"><a href="../main_aboutUs.action" target="_self">关于我们</a></li>
        </ul>
        <!--购物车图标-->
        <a href="../cart_lsCarts.action"><img class="topCart" id="cart" width="30px" height="30px"
                                              src="../static/local/img/topCart.png"/></a>
        <!--购物车徽标-->
        <p class="cartNum"><a href="#"> <span class="badge" style="background-color: #888">0</span></a></p>
        <div id="loginOrNot" class="photoAndName">
            <a href="../main_login.action">登录</a>&nbsp;&nbsp;<a style="display: inline" href="../main_register.action">注册</a>
        </div>
    </div>
</div>

<!-- 订单详情 -->
<div style="height: 900px" class="col-md-12 content">
    <div class="detil">
        <table class="table table-striped">
            <tr>
                <th colspan="4">订单详情</th>
            </tr>
            <tr>

                <td>联系人:<span>${orderView.receiverAddress.receiverName}</span></td>
                <td></td>
                <td>订单编号:<span>${orderView.order.orderSn}</span></td>
                <td></td>
            </tr>
            <tr>
                <td>用户昵称:<span>${orderView.customer.nickName}</span></td>
                <td></td>
                <td>下单时间:<span>${orderView.order.orderGmtCreate}</span></td>
                <td></td>
            </tr>
            <tr>
                <td>联系电话:<span>${orderView.receiverAddress.receiverPhone}</span></td>
                <td></td>
                <td>配送方式:<span>${orderView.shippingMethod.method}</span></td>
                <td></td>
            </tr>
            <tr>
                <td>收货地址:<span>${orderView.receiverAddress.addr}</span></td>
                <td colspan="3"></td>
            </tr>
        </table>
    </div>
    <div class="goods">
        <table class="table table-bordered">
            <tr>
                <th>商品名称</th>
                <th>商品单价</th>
                <th>购买数量</th>
                <th>总价</th>
            </tr>
            <c:forEach items="${orderView.order.orderItems}" var="orderItems">
                <tr>
                    <td>${orderItems.goods.goodsName}</td>
                    <td>${orderItems.goods.goodsPrice}</td>
                    <td>${orderItems.num}</td>
                    <td>${orderItems.price * orderItems.num}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>




</body>
<script>
    $(function () {
        //计算购物车徽标位置
        function measureNum() {
            var temleft = $(".topCart").offset().left;
            var temTop = $(".cartNum").offset().top = $(".topCart").offset().top;
            $(".cartNum").css({position: "absolute", left: temleft + 15, top: temTop - 7});
        }

        measureNum();
        $(window).resize(function () {
            measureNum();
        });
        changeNum();
    });

    //修改购物车的数量
    function changeNum() {
        $.post("../cart_countCart.action", function (data) {
            $(".badge").html(data.toString());
        }, "json");
    }
</script>

</html>
