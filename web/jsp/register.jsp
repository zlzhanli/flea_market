<%--
  Created by IntelliJ IDEA.
  User: SHASK
  Date: 2019/3/6
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh_CN">
<head>
    <title>用户注册</title>
    <link rel="stylesheet" type="text/css" href="${css}/login.css">
    <link rel="stylesheet" href="${bootstrap}/css/bootstrap.min.css">
    <script type="text/javascript" src="${jquery}/jquery-3.0.0.js"></script>
    <script type="text/javascript" src="${bootstrap}/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="../static/local/js/fly.js"></script>
    <script type="text/javascript" src="../static/local/js/carousel.js"></script>
    <link rel="stylesheet" href="${css}/homepage.css">
    <link rel="stylesheet" href="${css}/app.css">
    <link rel="stylesheet" href="${css}/animate.min.css">
    <link rel="stylesheet" href="${css}/test.css">
    <link rel="stylesheet" href="${css}/bottom.css">
    <script type="text/javascript">
        $(function () {

            $("#loginName").blur(function () {
                //alert("abc");
                $("#sp_loginName").html('');
                var loginName = $(this).val();
                var enoughRegex = new RegExp("(?=.{6,}).*", "g");
                if (false == enoughRegex.test($(this).val())) {
                    $("#sp_loginName").html('更多字符');
                    $("#sp_loginName").css("color", "red");
                    $(this).focus();
                    return;
                }

                var re = /^[\w_]{6,20}$/;
                if (false == re.test($(this).val())) {
                    $("#sp_loginName").html('格式有误，不能有特殊字符');
                    $("#sp_loginName").css("color", "red");
                    $(this).focus();
                    return;
                }

                /*定义验证表达式*/
                var reg = /^[\u4E00-\u9FA5]{2,4}$/;
                /*进行验证*/
                if (reg.test(loginName)) {
                    Toast("请不要输入汉字");
                    $(this).focus();
                }

                var url = "${root}/customer_findByLoginName.action";

                $.post(url,
                    {"loginName": loginName},
                    function (code) {
                        if (code == 400) {
                            $("#sp_loginName").html('登录名已注册，请更改您的登录名');
                            $("#sp_loginName").css("color", "red");
                            return;
                        }
                        $("#sp_loginName").html('验证通过可以注册该登录名');
                        $("#sp_loginName").css("color", "green");
                    },
                    'json')
            });

            $('#pwd').keyup(function () {
                var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
                var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
                var enoughRegex = new RegExp("(?=.{6,}).*", "g");
                if (false == enoughRegex.test($(this).val())) {
                    $('#sp_pwd').html('更多字符');
                    $("#sp_pwd").css("color", "red");
                } else if (strongRegex.test($(this).val())) {
                    $('#sp_pwd').className = 'ok';
                    $('#sp_pwd').html('强!');
                    $("#sp_pwd").css("color", "green");
                } else if (mediumRegex.test($(this).val())) {
                    $('#sp_pwd').className = 'alert';
                    $('#sp_pwd').html('中!');
                    $("#sp_pwd").css("color", "orange");
                } else {
                    $('#sp_pwd').className = 'error';
                    $('#sp_pwd').html('弱!');
                    $("#sp_pwd").css("color", "red");
                }
                return true;
            });

            $("#rePwd").keyup(function () {
                var pwd = $("#pwd").val();
                var rePwd = $(this).val();
                if (pwd == rePwd) {
                    $("#sp_rePwd").html("两次密码相同");
                    $("#sp_rePwd").css("color", "green");
                }
                else {
                    $("#sp_rePwd").html("两次密码不相同");
                    $("#sp_rePwd").css("color", "red")
                }
            });

            $("#img").click(function () {
                var src = $(this).attr("src") + '?';
                $(this).attr("src", src);
            });

            $("#submit").click(function () {
                var loginName = $("#loginName").val();
                var nickName = $("#nickName").val();
                var pwd = $("#pwd").val();
                var rePwd = $("#rePwd").val();
                var verification = $("#verification").val();

                if (loginName == "") {
                    Toast("登录名不能为空");
                    return;
                }
                if (nickName == "") {
                    Toast("昵称不能为空");
                    return;
                }
                if (pwd == "") {
                    Toast("密码不能为空");
                    return;
                }
                if (rePwd == "") {
                    Toast("确认密码不能为空");
                    return;
                }
                if (rePwd != pwd) {
                    Toast("两次密码不一致");
                    return;
                }
                if (verification == "") {
                    Toast("验证码不能为空");
                    return;
                }

                $.ajax({
                    url: "${root}/customer_save.action",
                    type: "POST",
                    data: {
                        loginName: loginName,
                        nickName: nickName,
                        password: pwd,
                        verification: verification
                    },
                    dataType: "json",
                    success: function (data) {
                        if (data.code==200){
                            Toast(data.msg);
                            window.location.href = "${root}/jsp/customer_login.jsp";
                        }else {
                            Toast(data.msg);
                        }
                    }
                });
            })

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
<div class="top center">
    <div id="header" class="header">
        <div class="main" >
            <span id="header-logo" style="display: flex;justify-content: left;align-items: center;"><img style="height: 35px;margin-right: 10px;" src="/static/images/logo.png" alt=""><span>跳蚤市场</span></span>
            <ul style="margin-left: 92px">
                <li id="navbar-homepage"  ><a  href="../control_index.action"  target="_self">首页</a></li>
                <li id="navbar-apk"><a  href="../control_shop.action" target="_self">自营商城</a></li>
                <li id="navbar-game"><a href="../control_forum.action" target="_self">跳蚤论坛</a></li>
                <li id="navbar-market"><a href="../control_app.action" target="_self">跳蚤市场APP</a></li>
                <li id="navbar-about"><a href="../control_aboutUs.action" target="_self">关于我们</a></li>
            </ul>
            <!--购物车图标-->
            <a href="../cart_lsCarts.action"><img class="topCart" id="cart" width="30px" height="30px" src="../static/local/img/topCart.png" /></a>
            <!--购物车徽标-->
            <p class="cartNum" style="width: 20px;"><a href="javascript:void(0)"> <span class="badge" style="background-color: #888">0</span></a></p>
            <div id="loginOrNot" class="photoAndName">
                <a href="../jsp/customer_login.jsp">登录</a>&nbsp;
            </div>
        </div>
    </div>
</div>

<div class="regist" style="border: 2px solid orange; width: 700px;">
    <div class="regist_center onePage-bg">
        <div class="regist_top" style="width: 700px">
            <h2 style="text-align: center">用户注册</h2>
            <%--<div class="right fr" style="border: 2px solid black"><a href="${root}/main_index" target="_self">跳骚商城</a></div>--%>
            <div class="clear"></div>
            <div class="xian center" style="width: 698px;margin-left: 0px"></div>
        </div>
        <form method="post">
            <div class="regist_main center" style="width: 700px; margin-left: 0px">
                <div class="username">
                    登&nbsp;&nbsp;录&nbsp;&nbsp;名:&nbsp;&nbsp;
                    <input class="shurukuang" type="text" name="loginName" id="loginName" placeholder="请输入你的登录名"/>
                    <span id="sp_loginName"></span>
                </div>
                <div class="username">
                    昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称:&nbsp;&nbsp;
                    <input class="shurukuang" type="text" name="nickName" id="nickName" placeholder="请输入昵称"/>
                    <%--<span>请不要输入汉字</span>--%>
                </div>

                <div class="username">
                    密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:&nbsp;&nbsp;
                    <input class="shurukuang" type="password" name="password" id="pwd" placeholder="请输入你的密码"/>

                    <span id="sp_pwd"></span>
                </div>

                <div class="username">
                    确认密码:&nbsp;&nbsp;
                    <input class="shurukuang" type="password" name="rePassword" id="rePwd" placeholder="请确认你的密码"/>

                    <span id="sp_rePwd"></span>
                </div>
                <div class="username">
                    <div class="left fl">
                        验&nbsp;&nbsp;证&nbsp;&nbsp;码:&nbsp;&nbsp;
                        <input class="yanzhengma" type="text" id="verification" name="verification"
                               placeholder="请输入验证码"/>
                    </div>
                    <div class="right fl">

                        <img id="img" src="${root}/customer_captcha.action" style="width: 100px; margin-top:4px;">
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="regist_submit" style="margin-left: -45px">
                    <input style="width: 300px" class="submit" type="button" name="submit" id="submit" value="立即注册">
                </div>
            </div>

        </form>
    </div>
</div>
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
