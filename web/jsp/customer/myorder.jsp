<%--
  Created by IntelliJ IDEA.
  User: zl
  Date: 2019/3/11
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>跳蚤商城-个人中心</title>
    <link rel="stylesheet" href="${bootstrap}/css/bootstrap.css">
    <script src="../static/jquery/jquery-3.0.0.js"></script>
    <script src="${bootstrap}/js/bootstrap.js"></script>
</head>
<body>

<div style="margin-left: 50px;">
<div class="table-responsive">
            <table class="table">
                <div ><caption style="font-size: 20px">订单详情</caption></div>
                <div style="margin-top: 20px;">
                <thead>
                <tr>
                    <th>订单号</th>
                    <th>状态</th>
                    <th>价格</th>
                    <th>下单时间</th>
                    <%--<th>收货人</th>--%>
                    <th>订单详情</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listOrder}" var="order">
                    <tr>
                        <td>${order.orderSn}</td>
                        <td>${order.orderStatus==200?"未发货":"已发货" }</td>
                        <%--<td>${order.orderStatus==0?"<span>已收货</span>":${order.order.Status==1?"<span>未发货</span>":"<span>待收货</span>"}}</td>--%>
                        <td>${order.orderPrice}</td>
                        <td>${order.orderGmtCreate}</td>
                        <%--<td>shouhuoren</td>--%>
                        <td><a class="btn btn-primary edit" href="${root}/orderItems_show.action?orderId=${order.id}">订单详情</a></td>
                    </tr>
                </tbody>
                </c:forEach>
                </div>
            </table>
</div>
</div>
</body>
</html>
