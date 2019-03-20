<%--
  Created by IntelliJ IDEA.
  User: 李克国
  Date: 2019/3/6
  Time: 8:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh_CN">
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>首页</title>
    <%--stylesheet 外部样式表--%>
    <link rel="stylesheet" href="${css}/bottom.css">
    <script type="text/javascript" src="${jquery}/jquery-3.0.0.js"></script>
    <script type="text/javascript" src="${js}/fly.js"></script>
    <link rel="stylesheet" type="text/css" href="${bootstrap}/css/bootstrap.css">
    <script type="text/javascript" src="${bootstrap}/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${css}/homepage.css">
    <link rel="stylesheet" href="${css}/animate.min.css">
    <link rel="stylesheet" href="${css}/app.css">
    <link rel="stylesheet" href="${bootstrap}/css/bootstrap.min.css">
    <script type="text/javascript" src="${jquery}/jquery-3.0.0.js"></script>
    <script type="text/javascript" src="${bootstrap}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${js}/carousel.js"></script>
    <script>
        $(function () {

        })
    </script>

</head>
<body>
<div id="container">
<iframe src="../html/all_head.html" style="width: 100%; height: 100%;"></iframe>
<div class="onePage">
    <div class="onePage-bg" id="onePageBg"></div>
    <div class="container">
        <div class="row">
            <div class="title-text">
                <div class="col-md-12 headFontSize">
                    <h1 class="headHlContent">
                        跳骚市场<br>
                        欢迎大家来到我们的乐园<br>
                    </h1>
                    <p style="margin-top: 50px; line-height: 30px">在这里你可以买到所有你想要的大宝贝</p>
                    <p class="btn-app-store" style="margin-top: 50px">
                        <a class="btn btn-success btn-lg" href="${root}/jsp/customer_login.jsp">立即登录，开启跳骚市场</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="twoPage" style="border: 2px solid black">
    <div class="container" style="width: 70%">
        <div class="courser-text">
            <h1>商品推荐</h1>
            <p>下面这些商品还不错哟，快去看看吧！</p>
        </div>
        <div id="carousel-example-generic" class="carousel slide" data-interval="3000" style="height: 500px">
            <ol class="carousel-indicators" style="margin-top: 200px">
                <li data-target="#carousel-example-generic" data-slide="0" class="active"></li>
                <li data-target="#carousel-example-generic" data-slide="1" class="active"></li>
                <li data-target="#carousel-example-generic" data-slide="2" class="active"></li>
            </ol>
            <div class="carousel-inner" style="height: 500px">
                <div class="item active" style="height: 300px">
                    <img src="${img}/twoPage2.jpg">
                </div>
                <div class="item" style="height: 300px">
                    <img src="${img}/twoPage2.jpg">
                </div>
                <div class="item" style="height: 300px">
                    <img src="${img}/twoPage2.jpg">
                </div>
            </div>
            <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                <span class="qlyphicon qlyphicon-chevron-left"></span>
            </a>
            <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                <span class="qlyphicon qlyphicon-chevron-right"></span>
            </a>
        </div>

    </div>
</div>
    <iframe SCROLLING="NO" src="../html/all_bottom.html" style="width: 100%; height: 350px;"></iframe>

    <script>
        $(function () {
            //计算购物车徽标位置
            function measureNum() {
                var temleft=$(".topCart").offset().left;
                var temTop=$(".cartNum").offset().top=$(".topCart").offset().top;
                $(".cartNum").css({position: "absolute",left:temleft+15,top:temTop-7});
            }
            measureNum();
            $(window).resize(function(){
                measureNum();
            }) ;
            changeNum();
        });
        //修改购物车的数量
        function changeNum(){
            $.post("../cart_countCart.action",function(data){
                $(".badge").html(data.toString());
            },"json");
        }
    </script>
</div>
</body>
</html>
