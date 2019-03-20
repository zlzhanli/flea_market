<%--
  Created by IntelliJ IDEA.
  User: 李克国
  Date: 2019/3/6
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="root" scope="application"  type="java.lang.String"/>
<jsp:useBean id="info" scope="request"  type="com.flea.market.pojo.Customer"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>跳蚤商城-个人主页</title>
    <script src="${jquery}/jquery-3.0.0.js" type="text/javascript"></script>
    <script src="${bootstrap}/js/bootstrap.js" type="text/javascript"></script>
    <link rel="stylesheet" href="${bootstrap}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${css}/style.css">
    <link rel="stylesheet" type="text/css" href="${css}/app.css">
    <link rel="stylesheet" href="${css}/cropper.min.css">
    <link rel="stylesheet" href="${css}/ImgCropping.css">
    <link rel="stylesheet" href="../../static/bootstrap/css/bootstrap.css">

    <script type="text/javascript" src="${js}/fly.js"></script>
    <link rel="stylesheet" href="${css}/homepage.css">
    <link rel="stylesheet" href="${css}/test.css">
    <link rel="stylesheet" href="${css}/animate.min.css">
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
    <style>
        *{
            font-family: 'Microsoft Yahei', '微软雅黑';
        }
        .grzxbj {
            margin-top: 50px;
        }

        .selfinfo center {
            height: 500px;
        }

        #head {
            margin-top: 20px;
            margin-left: 65px;
        }

        .subddzx {
            margin-left: 50px;
        }

        .ddzx {
            padding-left: 45px;
        }
        .info_head{
            background-color: white;
            margin-left: 0px;
            margin-bottom: 10px;
            width: 100%;
            height: 10%;
            display: flex;
            justify-content: space-between;

        }
        .info_head div{
            height: 70px;
            line-height: 70px;

            text-align: center;

        }
        .info_head div h4{
            margin-top: 30px;

        }
    </style>
    <style>
        .grzxbj {
            margin-top: 50px;
        }

        .selfinfo center {
            height: 500px;
        }

        #head {
            margin-top: 20px;
            margin-left: 65px;
        }

        .subddzx {
            margin-left: 50px;
        }

        .ddzx {
            padding-left: 45px;
        }
        .info_head{
            background-color: white;
            margin-left: 0px;
            margin-bottom: 10px;
            width: 100%;
            height: 10%;
            display: flex;
            justify-content: space-between;

        }
        .info_head div{
            height: 70px;
            line-height: 70px;

            text-align: center;

        }
        .info_head div h4{
            margin-top: 30px;

        }
    </style>


    <script src="${js}/cropper.min.js"></script>
    <script>
        $(function () {
            $(".link").click(function () {
                $("#content").attr("src", $(this).attr("href"));
                return false;
            });

        });

    </script>
</head>
<body>
<div id="header" class="header">
    <div class="main">
        <span id="header-logo" style="display: flex;justify-content: left;align-items: center;"><img
                style="height: 35px;margin-right: 10px;" src="/static/images/logo.png" alt=""><span>跳蚤市场</span></span>
        <ul style="margin-left: 92px;padding-inline-start: 0px">
            <li id="navbar-homepage"><a  href="../main_index.action" target="_self">首页</a></li>
            <li id="navbar-apk"><a  href="../main_shop.action" target="_self">自营商城</a></li>
            <li id="navbar-game"><a href="../main_forum.action" target="_self">跳蚤论坛</a></li>
            <li id="navbar-market"><a href="../main_app.action" target="_self">跳蚤市场APP</a></li>
            <li id="navbar-about"><a href="../main_aboutUs.action" target="_self">关于我们</a></li>
        </ul>
        <!--购物车图标-->
        <a href="../cart_lsCarts.action"><img class="topCart" id="cart" width="30px" height="30px"
                                              src="../static/local/img/topCart.png"/></a>
        <!--购物车徽标-->
        <p class="cartNum"><a href="#"> <span class="badge" style="background-color: #888">0</span></a></p>
        <div id="loginOrNot" class="photoAndName">
            <a href="../customer_loginUI">登录</a>&nbsp;|&nbsp;<a href="../customer_registerUI">注册</a>
        </div>
    </div>
</div>

<!-- self_info -->
<div class="grzxbj">
    <div class="selfinfo center">
        <div class="lfnav fl">
            <div class="head " style="width: 100px;height: 100px">
                <c:if test="${info.photo eq null}">
                    <img id="head" style="width: 100px;height: 100px" src="${img}/head.png" alt="您还没有头像" class="img-circle">
                </c:if>
                <c:if test="${info.photo ne null}">
                    <img id="head" style="width: 100px;height: 100px" src="${upload}/${info.photo}" alt="点击更换" class="img-circle">
                </c:if>
            </div>
            <div class="ddzx">订单中心</div>
            <div class="subddzx">
                <ul>
                    <li><a class="link" href="${root}/order_list.action">我的订单</a></li>
                    <li><a class="link"  href="${root}/customer_accountUI.action">我的账户</a></li>
                    <li><a class="link"  href="${root}/cart_goListCarts.action">我的购物车</a></li>
                </ul>
            </div>
            <div class="ddzx">个人中心</div>
            <div class="subddzx">
                <ul>
                    <li><a class="link" href="${root}/html/customer/base_info.html"
                           style="color:#ff6700;font-weight:bold;">基本信息</a></li>
                    <li><a class="link" href="${root}/html/customer/detail_info.html">详细信息</a></li>
                    <li><a class="link" href="${root}/customer_changePasswordUI.action">修改密码</a></li>
                    <li><a class="link" href="${root}/address_addressUI.action">收货地址</a></li>
                    <li><a class="link" href="${root}html/my_forum.html">我的发帖</a></li>
                </ul>
            </div>
        </div>
        <div class="fr" style="width: 80%;height: 100%">
            <div class="info_head row  text-center center-block" >
                <div class="col-lg-4 text-center">
                    <h4 class="text-primary">欢迎：${info.nickName}</h4>
                </div>
                <div class="col-lg-4 text-center">
                    <h4 class="text-success glyphicon-yen glyphicon-align-left"> 金币：${info.userBalance}</h4>
                </div>
                <div class="col-lg-4 text-center">
                    <a href="${root}/customer_exit.action"  style="color: red;font-size: 18px">退出系统</a>
                </div>
            </div>
            <iframe id="content" src="${root}/html/customer/base_info.html" scrolling="auto" frameborder="0"
                    style="width: 100%;height: 90%">
            </iframe>
        </div>

        <div class="clear"></div>
    </div>
</div>


<!--图片裁剪框 start-->
<div style="display: none" class="tailoring-container">
    <div class="black-cloth" onclick="closeTailor(this)"></div>
    <div class="tailoring-content">
        <div class="tailoring-content-one">
            <label title="上传图片" for="chooseImg" class="l-btn choose-btn">
                <input type="file" accept="image/jpg,image/jpeg,image/png" name="file" id="chooseImg" class="hidden"
                       onchange="selectImg(this)">
                选择图片
            </label>
            <div class="close-tailoring" onclick="closeTailor(this)">×</div>
        </div>
        <div class="tailoring-content-two">
            <div class="tailoring-box-parcel">
                <img id="tailoringImg">
            </div>
            <div class="preview-box-parcel">
                <p>图片预览：</p>
                <div class="square previewImg"></div>
                <div class="circular previewImg"></div>
            </div>
        </div>
        <div class="tailoring-content-three">
            <button class="l-btn cropper-reset-btn">复位</button>
            <button class="l-btn cropper-rotate-btn">旋转</button>
            <button class="l-btn cropper-scaleX-btn">换向</button>
            <button class="l-btn sureCut" id="sureCut">确定</button>
        </div>
    </div>
</div>
<!--图片裁剪框 end-->


<footer class="footer">

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
                FleaMarket Digital Co., Ltd. All Rights Reserved.</h4> <h5 _ngcontent-c3="" class="company">北京跳蚤科技有限公司</h5>
                <h6 _ngcontent-c3=""><a _ngcontent-c3="" href="javascript:void(0)" target="_blank"> <span
                        _ngcontent-c3="">京 ICP 备 14041720 号 - 1</span><span _ngcontent-c3="">京 ICP 证 140622 号</span><span
                        _ngcontent-c3="">京公网安备 11010502025474</span> </a> <a _ngcontent-c3=""
                                                                             href="javascript:void(0)"
                                                                             target="_blank"><span _ngcontent-c3="">网络文化经营许可证京网文 (2016) 2284 - 266 号</span></a>
                </h6></div>
        </div>
    </div>
</footer>
<script>
    //弹出框水平垂直居中
    (window.onresize = function () {
        var win_height = $(window).height();

        var win_width = $(window).width();
        if (win_width <= 768) {
            $(".tailoring-content").css({
                "top": (win_height - $(".tailoring-content").outerHeight()) / 2,
                "left": 0
            });
        } else {
            $(".tailoring-content").css({
                "top": (win_height - $(".tailoring-content").outerHeight()) / 2,
                "left": (win_width - $(".tailoring-content").outerWidth()) / 2
            });
        }
    })();


    //弹出图片裁剪框
    $("#head").on("click", function () {
        $(".tailoring-container").toggle();
    });

    //图像上传
    function selectImg(file) {
        if (!file.files || !file.files[0]) {
            return;
        }
        var reader = new FileReader();
        reader.onload = function (evt) {
            var replaceSrc = evt.target.result;
            //更换cropper的图片
            $('#tailoringImg').cropper('replace', replaceSrc, false);//默认false，适应高度，不失真
        }
        reader.readAsDataURL(file.files[0]);
    }

    //cropper图片裁剪
    $('#tailoringImg').cropper({
        aspectRatio: 1 / 1,//默认比例
        preview: '.previewImg',//预览视图
        guides: false,  //裁剪框的虚线(九宫格)
        autoCropArea: 0.5,  //0-1之间的数值，定义自动剪裁区域的大小，默认0.8
        movable: false, //是否允许移动图片
        dragCrop: true,  //是否允许移除当前的剪裁框，并通过拖动来新建一个剪裁框区域
        movable: true,  //是否允许移动剪裁框
        resizable: true,  //是否允许改变裁剪框的大小
        zoomable: false,  //是否允许缩放图片大小
        mouseWheelZoom: false,  //是否允许通过鼠标滚轮来缩放图片
        touchDragZoom: true,  //是否允许通过触摸移动来缩放图片
        rotatable: true,  //是否允许旋转图片
        crop: function (e) {
            // 输出结果数据裁剪图像。
        }
    });
    //旋转
    $(".cropper-rotate-btn").on("click", function () {
        $('#tailoringImg').cropper("rotate", 45);
    });
    //复位
    $(".cropper-reset-btn").on("click", function () {
        $('#tailoringImg').cropper("reset");
    });
    //换向
    var flagX = true;
    $(".cropper-scaleX-btn").on("click", function () {
        if (flagX) {
            $('#tailoringImg').cropper("scaleX", -1);
            flagX = false;
        } else {
            $('#tailoringImg').cropper("scaleX", 1);
            flagX = true;
        }
        flagX != flagX;
    });

    //裁剪后的处理
    $("#sureCut").on("click", function () {
        if ($("#tailoringImg").attr("src") == null) {
            return false;
        } else {
            var cas = $('#tailoringImg').cropper('getCroppedCanvas');//获取被裁剪后的canvas
            var base64url = cas.toDataURL('image/png'); //转换为base64地址形式
            $.ajax({
                url: "${root}/customer_updateHead.action",
                type: "POST",
                data: {head: base64url},
                dataType: "json",
                success: function (data) {
                    Toast(data.msg, 3000);
                }
            });
            $("#head").prop("src", base64url);//显示为图片的形式

            //关闭裁剪框
            closeTailor();
        }
    });

    //关闭裁剪框
    function closeTailor() {
        $(".tailoring-container").toggle();
    }
</script>

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
