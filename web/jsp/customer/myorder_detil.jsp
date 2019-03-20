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
    <script src="${jquery}/jquery-3.0.0.js" type="text/javascript"></script>
    <script src="${bootstrap}/js/bootstrap.js" type="text/javascript"></script>
    <link rel="stylesheet" href="${bootstrap}/css/bootstrap.css">
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
</head>
<body>
<div class="col-md-12 content">
    <div class="detil">
        <table class="table table-striped">

            <tr>
                <th colspan="4">订单详情</th>
            </tr>
            <tr>

                <%--<td>联系人:<span>${orderView.customer.realName}</span></td>--%>
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
                <%--<td>联系电话:<span>${orderView.customer.phone}</span></td>--%>
                <td>联系电话:<span>${orderView.receiverAddress.receiverPhone}</span></td>
                <td></td>
                <%--<td>配送方式:<span>${orderView.order.shippingMethod}</span></td>--%>
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
</html>
