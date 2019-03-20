<%--
  Created by IntelliJ IDEA.
  User: zhanghaohao
  Date: 2019/3/11
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>详情展示页</title>
    <link rel="stylesheet" type="text/css" href="${css}/style.css">
    <script type="text/javascript" src="${jquery}/jquery-3.0.0.js"></script>

    <script type="text/javascript" src="${js}/fly.js"></script>
    <link rel="stylesheet" type="text/css" href="${bootstrap}/css/bootstrap.css">
    <script type="text/javascript" src="${bootstrap}/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${css}/homepage.css">
    <link rel="stylesheet" href="${css}/test.css">
    <link rel="stylesheet" href="${css}/animate.min.css">

    <style>
        .details{
            text-align: center;

        }
        .details img{
            width: 700px;
        }
    </style>
    <script >
        function Toast(msg,duration){
            duration=isNaN(duration)?3000:duration;
            var m = document.createElement('div');
            m.innerHTML = msg;
            m.style.cssText="width: 60%;min-width: 150px;opacity: 0.7;height: 30px;color: rgb(240, 240, 240);line-height: 30px;text-align: center;border-radius: 5px;position: fixed;top: 40%;left: 20%;z-index: 999999;background: rgb(0, 0, 0);font-size: 12px;";
            document.body.appendChild(m);
            setTimeout(function() {
                var d = 0.5;
                m.style.webkitTransition = '-webkit-transform ' + d + 's ease-in, opacity ' + d + 's ease-in';
                m.style.opacity = '0';
                setTimeout(function() { document.body.removeChild(m) }, d * 1000);
            }, duration);
        }

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
    <script type="text/javascript">
        $(function () {
            var goodsId = $("#imgBig").attr("name");
            $("#addCart").click(function () {
                var url = "${root}/cart_addCart.action";
                $.post(url,
                    {"goodsId":goodsId},
                    function (data) {
                        if(data.code==200){
                            $(".badge").html(data.count);
                            Toast("添加成功",2000);
                        }
                        if(data.code==500){
                           Toast("添加失败",2000);
                        }
                    },'json')
            })
        })
        function changeImg(obj) {
            var bigObj = document.getElementById("imgBig");
            bigObj.src = obj.src;
        }


    </script>
</head>
<body>
<header>
    <div id="header" class="header">
        <div class="main">
        <span id="header-logo" style="display: flex;justify-content: left;align-items: center;"><img
                style="height: 35px;margin-right: 10px;" src="/static/images/logo.png" alt=""><span>跳蚤市场</span></span>
            <ul style="margin-left: 92px;padding-inline-start: 0px">
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
</header>
<!--end header -->

<!-- xiangqing -->
<div style="margin-top: 50px">
    <form action="${root}/goods_findById.action">
        <div class="xiangqing">
            <div class="neirong w">
                <div class="xiaomi6 fl">${goods.goodsName}</div>
                <nav class="fr">
                    <div class="clear"></div>
                </nav>
                <div class="clear"></div>
            </div>
        </div>

        <div class="jieshao mt20 w">
            <div class="left fl" style="border: none">
                <img id="imgBig" style="height: 350px;width: 300px" name="${goods.id}" src="${upload}/${goods.goodsCover}"><br>
                <img style="height: 60px;width: 50px;" onclick="changeImg(this)" src="${upload}/${goods.goodsCover}">
                <c:forEach items="${goods.list}" var="l">
                    <img style="height: 60px;width: 50px;" onclick="changeImg(this)" src="${upload}/${l.imgName}">
                </c:forEach>

            </div>

            <div class="right fr" style="height: 500px">
                <div class="h3 ml20 mt20">${goods.goodsName}</div>
                <div class="jianjie mr40 ml20 mt10">${goods.goodsFullName}</div>
                <div class="jiage ml20 mt10">价格：${goods.goodsPrice}元</div>
                <div class="ft20 ml20 mt20">商品编号</div>
                <div class="xzbb ml20 mt10">
                    <a>
                        <span>${goods.goodsSn}</span>
                    </a>
                    <div class="clear"></div>
                </div>
                <div class="ft20 ml20 mt20">商品库存</div>
                <div class="xzbb ml20 mt10">
                    <span>${goods.goodsNum}</span>
                </div>
                <div class="xiadan ml20 mt20">
                    <input id="addCart" class="jrgwc" type="button" name="jrgwc" value="加入购物车" />

                </div>
            </div>
            <div class="clear"></div>
        </div>
    </form>
    <div class="container details">${goods.goodsDetail}</div>
</div>
<!-- footer -->
<footer class="mt20 center">
    <div>
        <div _ngcontent-c3="" class="footer">
            <div _ngcontent-c3="" class="container">
                <div _ngcontent-c3="" class="siteinfo">
                    <ul _ngcontent-c3="" class="nav-footer">
                        <li _ngcontent-c3=""><h3 _ngcontent-c3="">订单服务 </h3>
                            <ul _ngcontent-c3=""> <!---->
                                <li _ngcontent-c3="">
                                    <a href="javascript:void(0)">购买指南</a></li>
                                <li _ngcontent-c3=""><a _ngcontent-c3="" href="javascript:void(0)">支付方式</a>
                                </li>
                                <li _ngcontent-c3=""><a _ngcontent-c3="" href="javascript:void(0)">送货政策</a>
                                </li>
                            </ul>
                        </li>
                        <li _ngcontent-c3=""><h3 _ngcontent-c3="">服务支持 </h3>
                            <ul _ngcontent-c3=""> <!---->
                                <li _ngcontent-c3=""><a _ngcontent-c3="" href="javascript:void(0)">售后服务</a>
                                </li>
                                <li _ngcontent-c3=""><a _ngcontent-c3=""
                                                        href="javascript:void(0)">维修门店</a></li>
                                <li _ngcontent-c3=""><a _ngcontent-c3="" href="javascript:void(0)">零售门店</a>
                                </li>
                            </ul>
                        </li>
                        <li _ngcontent-c3=""><h3 _ngcontent-c3="">自助服务 </h3>
                            <ul _ngcontent-c3=""> <!---->
                                <li _ngcontent-c3=""><a _ngcontent-c3=""
                                                        href="javascript:void(0)">热点咨询</a></li>
                                <li _ngcontent-c3=""><a _ngcontent-c3="" href="javascript:void(0)">预约购买</a>
                                </li>
                                <li _ngcontent-c3=""><a _ngcontent-c3="" href="javascript:void(0)">订单物流</a>
                                </li>
                            </ul>
                        </li>
                        <li _ngcontent-c3=""><h3 _ngcontent-c3="">媒体中心 </h3>
                            <ul _ngcontent-c3=""> <!---->
                                <li _ngcontent-c3=""><a _ngcontent-c3="" href="javascript:void(0)">新闻动态</a>
                                </li>
                                <li _ngcontent-c3=""><a _ngcontent-c3="" href="javascript:void(0)">官方视频</a></li>
                                <li _ngcontent-c3=""><a _ngcontent-c3="" href="javascript:void(0)">图片资源</a></li>
                            </ul>
                        </li>
                        <li _ngcontent-c3=""><h3 _ngcontent-c3="">关于公司 </h3>
                            <ul _ngcontent-c3=""> <!---->
                                <li _ngcontent-c3=""><a _ngcontent-c3="" href="javascript:void(0)">公司简介</a>
                                </li>
                                <li _ngcontent-c3=""><a _ngcontent-c3="" href="javascript:void(0)">加入我们</a></li>
                                <li _ngcontent-c3=""><a _ngcontent-c3="" href="javascript:void(0)">荣誉奖项</a>
                                </li>
                            </ul>
                        </li>
                        <li _ngcontent-c3=""><h3 _ngcontent-c3="">关注我们</h3>
                            <ul _ngcontent-c3="">
                                <li _ngcontent-c3=""><a _ngcontent-c3="" href="javascript:void(0)" target="_blank">新浪微博</a>
                                </li>
                                <li _ngcontent-c3="" class="weixin"><a _ngcontent-c3="">官方微信</a>
                                    <div _ngcontent-c3="" class="mask"></div>
                                    <div _ngcontent-c3="" class="qrcode">
                                        <div _ngcontent-c3="" class="close-btn"></div>
                                        <h4 _ngcontent-c3="">锤子科技官方微信</h4> <h6 _ngcontent-c3="">
                                        打开微信，点击右上角的“+”，选择“扫一扫”功能，用摄像头对准下方二维码即可。</h6>
                                        <div _ngcontent-c3="" class="qrcode-bg"></div>
                                    </div>
                                </li>
                                <li _ngcontent-c3=""><a _ngcontent-c3="" href="javascript:void(0)"
                                                        target="_blank">联系我们</a></li>
                            </ul>
                        </li>
                    </ul>
                    <ul _ngcontent-c3="" class="service">
                        <li _ngcontent-c3="" class="tel">400 - 888 - 8888</li>
                        <li _ngcontent-c3="" class="time">周一到周日 9:00 - 18:00</li>
                        <li _ngcontent-c3="" class="desc">(仅收市话费)</li>
                        <li _ngcontent-c3="" class="online"><a _ngcontent-c3=""
                                                               href="javascript:void(0)"><span
                                _ngcontent-c3="" class="smartisan-icon info-question"></span>在线帮助</a></li>
                    </ul>
                </div>
                <div _ngcontent-c3="" class="copyright"><h4 _ngcontent-c3="">Copyright © <span _ngcontent-c3=""
                                                                                               copyright-year=""></span>2019,
                    FleaMarket Digital Co., Ltd. All Rights Reserved.</h4> <h5 _ngcontent-c3="" class="company">
                    北京跳蚤科技有限公司</h5>
                    <h6 _ngcontent-c3=""><a _ngcontent-c3="" href="javascript:void(0)" target="_blank"> <span
                            _ngcontent-c3="">京 ICP 备 14041720 号 - 1</span><span
                            _ngcontent-c3="">京 ICP 证 140622 号</span><span
                            _ngcontent-c3="">京公网安备 11010502025474</span> </a> <a _ngcontent-c3=""
                                                                                 href="javascript:void(0)"
                                                                                 target="_blank"><span _ngcontent-c3="">网络文化经营许可证京网文 (2016) 2284 - 266 号</span></a>
                    </h6></div>
            </div>
        </div>
    </div>
</footer>
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
