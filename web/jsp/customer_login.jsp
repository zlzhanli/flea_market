<%--
  Created by IntelliJ IDEA.
  User: zhanghaohao
  Date: 2019/3/6
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="root" scope="application" type="java.lang.String"/>
<jsp:useBean id="css" scope="application" type="java.lang.String"/>
<jsp:useBean id="jquery" scope="application" type="java.lang.String"/>

<html lang="zh_CN">
<head>

    <meta charset="UTF-8">
    <title>会员登录</title>

    <script src="${jquery}/jquery-3.0.0.js" type="text/javascript"></script>
    <script type="text/javascript" src="../static/local/js/fly.js"></script>
    <script type="text/javascript" src="../static/local/js/carousel.js"></script>
    <link rel="stylesheet" type="text/css" href="../static/bootstrap/css/bootstrap.css">
    <script type="text/javascript" src="../static/bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${css}/homepage.css">
    <link rel="stylesheet" href="${css}/app.css">
    <link rel="stylesheet" href="${css}/animate.min.css">
    <link rel="stylesheet" href="${css}/test.css">
    <link rel="stylesheet" href="${css}/bottom.css">
    <link rel="stylesheet" type="text/css" href="${css}/login.css">
    <script>
        $(function () {
            // 跟新验证码
            $("#captcha_img").click(function () {
                var src = $("#captcha_img").attr("src") + '?time=' + Math.random();
                $("#captcha_img").attr("src", src );
            });

            // 提交登录信息
            $("#submit").click(function () {
                var name = $("#name").val();
                var password = $("#password").val();
                var captcha = $("#captcha").val();
                if (name == "") {
                    Toast("用户名不能为空",3000);
                    return;
                }
                if (password == "") {
                    Toast("密码不能为空",3000);
                    return;
                }
                if (captcha == "") {
                    Toast("验证码不能为空",3000);
                    return;
                }

                $.ajax({
                    url: "${root}/customer_login.action",
                    type: "POST",
                    data: {
                        loginName: name,
                        password: password,
                        captcha: captcha
                    },
                    dataType: "json",
                    success: function (data) {
                        if (data.code == 200) {
                            if ("${actionConfig}" == '') {
                                window.location.href = "${root}/html/index.html";
                                return;
                            }
                            window.location.href = "${actionConfig}";
                        } else {
                            Toast(data.msg,4000)
                        }
                    }
                })
                ;
            });
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

        })
    </script>
    <style>
        .back{
            background-image: url("../static/local/img/login_bg.jpg");
            background-position: -5px -5px;
        }

    </style>
</head>


<body>

<!-- login -->
<div class="top center">

        <div id="header" class="header">
            <div class="main">
            <span id="header-logo" style="display: flex;justify-content: left;align-items: center;"><img
                    style="height: 35px;margin-right: 10px;" src="/static/images/logo.png"
                    alt=""><span>跳蚤市场</span></span>
                <ul style="margin-left: 92px;padding-inline-start: 0px">
                    <li id="navbar-homepage"><a href="${root}/main_index.action" target="_self">首页</a></li>
                    <li id="navbar-apk"><a class="barActive" href="${root}/main_shop.action" target="_self">自营商城</a></li>
                    <li id="navbar-game"><a href="${root}/main_forum.action" target="_self">跳蚤论坛</a></li>
                    <li id="navbar-market"><a href="${root}/main_app.action" target="_self">跳蚤市场APP</a></li>
                    <li id="navbar-about"><a href="${root}/main_aboutUs.action" target="_self">关于我们</a></li>
                </ul>
                <!--购物车图标-->
                <a href="${root}/cart_lsCarts.action"><img class="topCart" id="cart" width="30px" height="30px"
                                                           src="../static/local/img/topCart.png"/></a>
                <!--购物车徽标-->
                <p class="cartNum"><a href="#"> <span class="badge" style="background-color: #888">0</span></a></p>
                <div id="loginOrNot" class="photoAndName">
                    <a href="${root}/customer_registerUI">注册</a>
                </div>
            </div>
        </div>
</div>

<div class="back">
<form method="post" class="form center">
    <div class="login">
        <div class="login_center">
            <div class="login_top">
                <div class="left fl">会员登录</div>
                <div class="right fr">您还不是我们的会员？<a href="${root}/customer_registerUI.action" target="_self">立即注册</a></div>
                <div class="clear"></div>
                <div class="xian center"></div>
            </div>
            <div class="login_main center">
                <div class="username">用户名:&nbsp;
                    <input id="name" style="color: black;" class="shurukuang" type="text" name="username" placeholder="请输入你的用户名"/>
                </div>
                <div class="username">密&nbsp;&nbsp;&nbsp;&nbsp;码:&nbsp;
                    <input style="color: black;" class="shurukuang" id="password" type="password" name="password" placeholder="请输入你的密码"/>
                </div>
                <div class="username">
                    <div class="left fl">验证码:&nbsp;
                        <input style="color: black;" id="captcha" class="yanzhengma" type="text" name="captcha" placeholder="请输入验证码"/>
                    </div>
                    <div class="right fl">
                        <img style="width: 120px" id="captcha_img" src="${root}/customer_captcha.action">
                    </div>
                    <div class="right fr">密码忘记了？<a href="${root}/html/findBackPwd.html"
                                                   target="_self">点击找回</a>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
            <div class="login_submit">
                <input id="submit" class="submit" type="button" name="submit" value="立即登录">
            </div>

        </div>
    </div>
</form></div>
<footer>
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
</html>
