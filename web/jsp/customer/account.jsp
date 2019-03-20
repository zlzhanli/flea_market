<%--
  Created by IntelliJ IDEA.
  User: 李克国
  Date: 2019/3/12
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="root" scope="application" type="java.lang.String"/>
<jsp:useBean id="css" scope="application" type="java.lang.String"/>
<html>
<head>
    <title>修改密码</title>
    <link rel="stylesheet" href="${css}/item.css">
    <link rel="stylesheet" href="${bootstrap}/css/bootstrap.css">
    <script src="${jquery}/jquery-3.0.0.js" type="text/javascript"></script>
    <script src="${bootstrap}/js/bootstrap.js" type="text/javascript"></script>
    <script>
        $(function () {
            // 加载余额
            $.ajax({
                url: "${root}/customer_info.action",
                type: "POST",
                dataType: "json",
                success: function (data) {
                    $("#balance").html(" "+data.userBalance);
                }
            });

            $(".edit").click(function () {
               var money = $(this).next().val();
               pay(money);
               // TODO 刘天佑 充值
            });
            $("#self").click(function () {
                var money = $("#money").val();
                pay(money);
            });
            //去充值
            function pay (num){
                //window.location.href="${root}/pay_pay.action?price="+num;
                $.post("${root}/pay_pay.action?price="+num,function (data) {
                    window.open(data.toString(),"_blank");
                },"text");
            }




        });


        // 弹出框
        function Toast(msg, duration) {
            duration = isNaN(duration) ? 3000 : duration;
            var m = document.createElement('div');
            m.innerHTML = msg;
            m.style.cssText = "width: 60%;min-width: 150px;opacity: 0.7;height: 30px;color: rgb(240, 240, 240);line-height: 30px;text-align: center;border-radius: 5px;position: fixed;top: 40%;left: 20%;z-index: 999999;background: rgb(0, 0, 0);font-size: 12px;";
            document.body.appendChild(m);
            setTimeout(function () {
                var d = 0.5;
                m.style.webkitTransition = '-webkit-transform ' + d + 's ease-in, opacity ' + d + 's ease-in';
                m.style.opacity = '0';
                setTimeout(function () {
                    document.body.removeChild(m)
                }, d * 1000);
            }, duration);
        }
    </script>
</head>
<body>
<div class="rtcont fr">
    <div class="grzlbt ml40">我的账户</div>
    <div class="subgrzl ml40">
        <span>账户余额：</span>
        <span>
            <span id="balance" class="text-success glyphicon-yen glyphicon-align-left">  </span>
        </span>
    </div>
    <div class="subgrzl ml40">
        <span>账户充值</span>
        <span id="loginName" class="item">
            <span class="text-success glyphicon-yen glyphicon-align-left"> 6 </span>
        </span>
        <span>
          <button  type="button" class="btn btn-primary edit" title="Popover title"
                  data-container="body" data-toggle="popover" data-placement="top"
                  data-content="顶部的 Popover 中的一些内容">充值</button>
            <input type="hidden" value="6">
        </span>
    </div>
    <div class="subgrzl ml40">
        <span>账户充值</span>
        <span class="item">
            <span class="text-success glyphicon-yen glyphicon-align-left"> 10 </span>
        </span>
        <span>
          <button  type="button" class="btn btn-primary edit" title="Popover title"
                  data-container="body" data-toggle="popover" data-placement="top"
                  data-content="顶部的 Popover 中的一些内容">充值</button>
            <input type="hidden" value="10">
        </span>
    </div>
    <div class="subgrzl ml40">
        <span>账户充值</span>
        <span class="item">
            <span class="text-success glyphicon-yen glyphicon-align-left"> 20 </span>
        </span>
        <span>
            <button  type="button" class="btn btn-primary edit" title="Popover title"
                    data-container="body" data-toggle="popover" data-placement="top"
                    data-content="顶部的 Popover 中的一些内容">充值</button>
            <input type="hidden" value="20">
        </span>
    </div>
    <div class="subgrzl ml40">
        <span>账户充值</span>
        <span class="item">
            <span class="text-success glyphicon-yen glyphicon-align-left"> 30 </span>
        </span>
        <span>
            <button  type="button" class="btn btn-primary edit" title="Popover title"
                    data-container="body" data-toggle="popover" data-placement="top"
                    data-content="顶部的 Popover 中的一些内容">充值</button>
            <input type="hidden" value="30">
        </span>
    </div>
    <div class="subgrzl ml40">
        <span>账户充值</span>
        <span class="item">
            <span class="text-success glyphicon-yen glyphicon-align-left"> 50 </span>
        </span>
        <span>
            <button  type="button" class="btn btn-primary edit" title="Popover title"
                    data-container="body" data-toggle="popover" data-placement="top"
                    data-content="顶部的 Popover 中的一些内容">充值</button>
            <input type="hidden" value="50">
        </span>
    </div>
    <div class="subgrzl ml40">
        <span>账户充值</span>
        <span class="item">
            <span class="text-success glyphicon-yen glyphicon-align-left"> 100 </span>
        </span>
        <span>
            <button  type="button" class="btn btn-primary edit" title="Popover title"
                    data-container="body" data-toggle="popover" data-placement="top"
                    data-content="顶部的 Popover 中的一些内容">充值</button>
            <input type="hidden" value="100">
        </span>
    </div>
    <div class="subgrzl ml40">
        <span>账户充值</span>
        <span class="item">
            <input id="money" type="number" value="200" class="form-control" alt="自定义金额">
        </span>
        <span>
            <button id="self"  type="button" class="btn btn-primary" title="Popover title"
                    data-container="body" data-toggle="popover" data-placement="top"
                    data-content="顶部的 Popover 中的一些内容">充值</button>
        </span>
    </div>


</div>
</body>
</html>
