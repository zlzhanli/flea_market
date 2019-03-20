<%--
  Created by IntelliJ IDEA.
  User: zl
  Date: 2019/3/8
  Time: 9:23
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>我的购物车</title>
    <link rel="stylesheet" type="text/css" href="${css}/style.css">
    <link rel="stylesheet" href="${bootstrap}/css/bootstrap.css">
    <script src="${jquery}/jquery-3.0.0.js"></script>
    <script src="${bootstrap}/js/bootstrap.js"></script>

    <link rel="stylesheet" type="text/css" href="${css}/style.css">
    <link rel="stylesheet" type="text/css" href="${css}/app.css">
    <link rel="stylesheet" href="${css}/cropper.min.css">
    <link rel="stylesheet" href="${css}/ImgCropping.css">
    <link rel="stylesheet" href="../../static/bootstrap/css/bootstrap.css">

    <script type="text/javascript" src="${js}/fly.js"></script>
    <link rel="stylesheet" href="${css}/homepage.css">
    <link rel="stylesheet" href="${css}/test.css">
    <link rel="stylesheet" href="${css}/animate.min.css">
    <script>

        //检测登录
        $(function () {
            $.post("../customer_getCustomerInfo.action", function (data) {
                if (data.msg == "ok") {
                    $("#loginOrNot").html("<div style='float: left;'> <img src='../upload/" + data.photo + "' class='photo img-circle'/></div>")
                    $("#loginOrNot").append("&nbsp;&nbsp;<div style='float:left; margin-left: 10px;margin-top: 5px'> <a style='font-size: 14px'>" + data.nickName + "</a></div>");
                    $("#loginOrNot").click(
                        function () {
                            window.location.href = "../customer_infoUI.action";
                        }
                    );
                }
            }, "json");
        })
    </script>
    <style>
        *{
            font-family: 'Microsoft Yahei', '微软雅黑';
        }

    </style>

    <style>.footer[_ngcontent-c3] {
        background: #fafafa;
        border-top: 1px solid #e6e6e6;
        display: block;
        text-rendering: optimizeLegibility;
        -webkit-font-smoothing: antialiased;
    }

    .footer.theme-none[_ngcontent-c3] {
        display: none
    }

    .footer.en[_ngcontent-c3] .siteinfo[_ngcontent-c3], .footer.jp[_ngcontent-c3] .siteinfo[_ngcontent-c3] {
        height: auto;
        padding: 60px 0 38px
    }

    .footer.en[_ngcontent-c3] .siteinfo[_ngcontent-c3] > ul[_ngcontent-c3], .footer.jp[_ngcontent-c3] .siteinfo[_ngcontent-c3] > ul[_ngcontent-c3] {
        overflow: hidden
    }

    .footer.en[_ngcontent-c3] .siteinfo[_ngcontent-c3] > ul[_ngcontent-c3] li[_ngcontent-c3], .footer.jp[_ngcontent-c3] .siteinfo[_ngcontent-c3] > ul[_ngcontent-c3] li[_ngcontent-c3] {
        float: left;
        font-size: 16px;
        line-height: 1;
        margin: 0 62px 0 0
    }

    .footer.en[_ngcontent-c3] .siteinfo[_ngcontent-c3] > ul[_ngcontent-c3] li.mail[_ngcontent-c3], .footer.jp[_ngcontent-c3] .siteinfo[_ngcontent-c3] > ul[_ngcontent-c3] li.mail[_ngcontent-c3] {
        color: #424242;
        float: right;
        margin: 0
    }

    .footer.en[_ngcontent-c3] .siteinfo[_ngcontent-c3] > ul[_ngcontent-c3] li.mail[_ngcontent-c3] a[_ngcontent-c3], .footer.jp[_ngcontent-c3] .siteinfo[_ngcontent-c3] > ul[_ngcontent-c3] li.mail[_ngcontent-c3] a[_ngcontent-c3] {
        color: #84a3e3
    }

    .footer.en[_ngcontent-c3] .siteinfo[_ngcontent-c3] > ul[_ngcontent-c3] li[_ngcontent-c3] a[_ngcontent-c3], .footer.jp[_ngcontent-c3] .siteinfo[_ngcontent-c3] > ul[_ngcontent-c3] li[_ngcontent-c3] a[_ngcontent-c3] {
        color: #424242
    }

    .footer[_ngcontent-c3] .container[_ngcontent-c3] {
        width: 1220px
    }

    .footer[_ngcontent-c3] .siteinfo[_ngcontent-c3] {
        height: 108px;
        padding: 60px 0 55px;
        border-bottom: 1px solid #e6e6e6;
        position: relative
    }

    .footer[_ngcontent-c3] .siteinfo[_ngcontent-c3] .nav-footer[_ngcontent-c3] > li[_ngcontent-c3] {
        margin-right: 90px;
        line-height: 1;
        float: left
    }

    .footer[_ngcontent-c3] .siteinfo[_ngcontent-c3] .nav-footer[_ngcontent-c3] > li[_ngcontent-c3] h3[_ngcontent-c3] {
        color: #646464;
        font-weight: 700;
        font-size: 12px;
        padding: 0 0 20px
    }

    .footer[_ngcontent-c3] .siteinfo[_ngcontent-c3] .nav-footer[_ngcontent-c3] > li[_ngcontent-c3] li[_ngcontent-c3] {
        color: #c3c3c3;
        font-size: 12px;
        padding: 7px 0
    }

    .footer[_ngcontent-c3] .siteinfo[_ngcontent-c3] .nav-footer[_ngcontent-c3] > li[_ngcontent-c3] li[_ngcontent-c3] a[_ngcontent-c3] {
        color: #969696
    }

    .footer[_ngcontent-c3] .siteinfo[_ngcontent-c3] .nav-footer[_ngcontent-c3] > li[_ngcontent-c3] li[_ngcontent-c3] a[_ngcontent-c3]:hover {
        color: #5079d9
    }

    .footer[_ngcontent-c3] .siteinfo[_ngcontent-c3] .weixin[_ngcontent-c3] .qrcode[_ngcontent-c3] {
        display: none;
        width: 333px;
        height: 469px;
        margin: -255px 0 0 -187px;
        padding: 20px;
        position: fixed;
        left: 50%;
        top: 50%;
        border: 1px solid #e4e4e4;
        box-shadow: 0 2px 6px rgba(0, 0, 0, .15);
        border-radius: 1px;
        background-color: #fff;
        z-index: 1000;
        text-align: center
    }

    .footer[_ngcontent-c3] .siteinfo[_ngcontent-c3] .weixin[_ngcontent-c3] .qrcode[_ngcontent-c3] h4[_ngcontent-c3] {
        font-size: 23px;
        line-height: 1em;
        color: #666;
        padding: 21px 0 11.3px
    }

    .footer[_ngcontent-c3] .siteinfo[_ngcontent-c3] .weixin[_ngcontent-c3] .qrcode[_ngcontent-c3] h6[_ngcontent-c3] {
        font-size: 15px;
        line-height: 1.5em;
        color: #666;
        padding: 0 0 16.3px;
        margin: 0 0 20px;
        border-bottom: 1px solid #d9d9d9
    }

    .footer[_ngcontent-c3] .siteinfo[_ngcontent-c3] .weixin[_ngcontent-c3] .mask[_ngcontent-c3] {
        display: none;
        position: fixed;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
        z-index: 300;
        background: #000;
        opacity: .518;
        filter: alpha(opacity=51.8)
    }

    .footer[_ngcontent-c3] .siteinfo[_ngcontent-c3] .weixin[_ngcontent-c3] .close-btn[_ngcontent-c3] {
        width: 36px;
        height: 36px;
        background: url(//static.smartisanos.cn/index/assets/images/close-btn.c0da7e3df5e7d201be08760efaab78eb.png) no-repeat;
        position: absolute;
        top: -15px;
        right: -15px;
        cursor: pointer
    }

    .footer[_ngcontent-c3] .siteinfo[_ngcontent-c3] .weixin[_ngcontent-c3] .qrcode-bg[_ngcontent-c3] {
        height: 333px;
        width: 333px;
        background: url(//static.smartisanos.cn/index/assets/images/qrcode.fadb57837b252d0bc3418c832f45f5b8.png) no-repeat;
        background-size: contain
    }

    .footer[_ngcontent-c3] .siteinfo[_ngcontent-c3] .weixin.active[_ngcontent-c3] .qrcode[_ngcontent-c3] {
        display: block;
        -webkit-animation: thumb-box .2s ease-out;
        animation: thumb-box .2s ease-out
    }

    .footer[_ngcontent-c3] .siteinfo[_ngcontent-c3] .weixin.active[_ngcontent-c3] .mask[_ngcontent-c3] {
        display: block
    }

    .footer[_ngcontent-c3] .service[_ngcontent-c3] {
        position: absolute;
        right: 0;
        overflow: hidden;
        line-height: 34px
    }

    .footer[_ngcontent-c3] .service[_ngcontent-c3] li[_ngcontent-c3] {
        clear: both;
        width: 241px;
        font-size: 12px;
        line-height: 1;
        color: #999;
        text-align: right
    }

    .footer[_ngcontent-c3] .service[_ngcontent-c3] li.time[_ngcontent-c3] {
        margin-top: 15px;
        font-family: PingFang SC
    }

    .footer[_ngcontent-c3] .service[_ngcontent-c3] li.desc[_ngcontent-c3] {
        margin-top: 5px
    }

    .footer[_ngcontent-c3] .service[_ngcontent-c3] li.item[_ngcontent-c3] {
        margin-top: 5px;
        right: -4px;
        position: relative
    }

    .footer[_ngcontent-c3] .service[_ngcontent-c3] li.online[_ngcontent-c3] a[_ngcontent-c3] {
        float: right;
        width: 132px;
        height: 30px;
        line-height: 30px;
        background: linear-gradient(#fcfcfc, #f5f5f5);
        cursor: pointer;
        font-size: 12px;
        font-weight: 700;
        margin-top: 15px;
        text-align: center;
        color: #5079d9;
        border: 1px solid #d0d0d0;
        border-radius: 5px;
        box-sizing: border-box
    }

    .footer[_ngcontent-c3] .service[_ngcontent-c3] li.online[_ngcontent-c3] a[_ngcontent-c3]:hover {
        background: #f5f5f5
    }

    .footer[_ngcontent-c3] .service[_ngcontent-c3] .tel[_ngcontent-c3] {
        font-size: 18px;
        font-weight: 900;
        line-height: 1;
        cursor: default;
        color: #5079d9;
        position: relative
    }

    .footer[_ngcontent-c3] .copyright[_ngcontent-c3] {
        color: #434d55;
        font-size: 12px;
        padding: 30px 0 60px
    }

    .footer[_ngcontent-c3] .copyright[_ngcontent-c3] h4[_ngcontent-c3], .footer[_ngcontent-c3] .copyright[_ngcontent-c3] h5[_ngcontent-c3] {
        float: left;
        height: 15px;
        line-height: 15px;
        color: #bdbdbd
    }

    .footer[_ngcontent-c3] .copyright[_ngcontent-c3] h5[_ngcontent-c3] {
        margin-left: 3px
    }

    .footer[_ngcontent-c3] .copyright[_ngcontent-c3] h6[_ngcontent-c3] {
        clear: both;
        padding: 10px 0 0;
        height: 15px;
        font-size: 0
    }

    .footer[_ngcontent-c3] .copyright[_ngcontent-c3] h6[_ngcontent-c3] a[_ngcontent-c3] {
        color: #bdbdbd;
        font-size: 12px;
        display: inline-block;
        height: 12px;
        line-height: 1
    }

    .footer[_ngcontent-c3] .copyright[_ngcontent-c3] h6[_ngcontent-c3] span[_ngcontent-c3] {
        margin-right: 20px
    }

    .footer[_ngcontent-c3] .privacy[_ngcontent-c3] {
        float: left;
        margin: 0 0 0 12px
    }

    .footer[_ngcontent-c3] .privacy[_ngcontent-c3] li[_ngcontent-c3] {
        float: left;
        line-height: 12px;
        padding: 1px 10px 0;
        border-left: 1px solid #ccc
    }

    .footer[_ngcontent-c3] .privacy[_ngcontent-c3] li[_ngcontent-c3] a[_ngcontent-c3] {
        color: #ababab
    }

    .footer[_ngcontent-c3] .privacy[_ngcontent-c3] li[_ngcontent-c3] a[_ngcontent-c3]:hover {
        color: #5079d9
    }

    .footer[_ngcontent-c3] .language-switch[_ngcontent-c3] {
        position: absolute;
        right: -20px;
        bottom: -63px
    }

    .footer[_ngcontent-c3] .language-switch[_ngcontent-c3] li[_ngcontent-c3] {
        border-bottom: 1px solid #ebebeb
    }

    .footer[_ngcontent-c3] .language-switch[_ngcontent-c3] li[_ngcontent-c3]:first-child a[_ngcontent-c3] {
        border-radius: 8px 8px 0 0
    }

    .language-switch[_ngcontent-c3] {
        position: relative;
        z-index: 21;
        background: hsla(0, 0%, 99%, 0);
        font-size: 12px;
        line-height: 30px;
        width: 118px
    }

    .language-switch.active[_ngcontent-c3] h3[_ngcontent-c3] {
        transition: all .25s ease
    }

    .language-switch.active[_ngcontent-c3] h3[_ngcontent-c3] .smartisan-icon[_ngcontent-c3] {
        -webkit-transform: rotate(0);
        transform: rotate(0);
        transition: all .25s ease
    }

    .language-switch.active[_ngcontent-c3] h3[_ngcontent-c3] .smartisan-icon.caret-down[_ngcontent-c3]:before {
        color: #bdbdbd
    }

    .language-switch.active[_ngcontent-c3] li[_ngcontent-c3] a[_ngcontent-c3]:before {
        visibility: visible;
        content: "    "
    }

    .language-switch.active[_ngcontent-c3] ul[_ngcontent-c3] {
        background: #f5f5f5;
        box-shadow: 0 2px 10px rgba(0, 0, 0, .2), 0 0 0 1px rgba(0, 0, 0, .04);
        opacity: 1;
        visibility: visible;
        transition: opacity .25s ease
    }

    .language-switch[_ngcontent-c3] h3[_ngcontent-c3] {
        -webkit-user-select: none;
        user-select: none;
        position: relative;
        z-index: 1
    }

    .language-switch[_ngcontent-c3] h3[_ngcontent-c3] a[_ngcontent-c3] {
        color: #45474b;
        display: block;
        height: 31px;
        line-height: 33px;
        padding: 10px 20px 10px 44px;
        position: relative;
        font-weight: 400;
        font-size: 12px
    }

    .language-switch[_ngcontent-c3] h3[_ngcontent-c3] .smartisan-icon[_ngcontent-c3] {
        position: absolute;
        right: 18px;
        margin-top: -1px;
        -webkit-transform: rotate(-180deg);
        transform: rotate(-180deg);
        text-indent: 0
    }

    .language-switch[_ngcontent-c3] h3[_ngcontent-c3] .smartisan-icon.caret-down[_ngcontent-c3]:before {
        color: #bdbdbd
    }

    .language-switch[_ngcontent-c3] ul[_ngcontent-c3] {
        position: absolute;
        left: 0;
        right: 0;
        bottom: 0;
        padding-bottom: 51px;
        visibility: hidden;
        opacity: 0;
        border-radius: 8px
    }

    .language-switch[_ngcontent-c3] li[_ngcontent-c3] a[_ngcontent-c3] {
        background: #fff;
        position: relative;
        display: block;
        color: #666;
        height: 31px;
        line-height: 33px;
        padding: 10px 20px 10px 44px
    }

    .language-switch[_ngcontent-c3] li[_ngcontent-c3] a[_ngcontent-c3]:before {
        visibility: hidden
    }

    .language-switch[_ngcontent-c3] li[_ngcontent-c3] a[_ngcontent-c3]:hover {
        background-color: #fafafa
    }

    .language-switch[_ngcontent-c3] h3[_ngcontent-c3] a[_ngcontent-c3]:before, .language-switch[_ngcontent-c3] li[_ngcontent-c3] a[_ngcontent-c3]:before {
        position: absolute;
        content: " ";
        width: 16px;
        height: 15px;
        left: 19px;
        top: 18px;
        background: url(//static.smartisanos.cn/index/assets/images/flag.afd0e7c69a7b9639cbfe7dde4ec0f3b0.png) 0 0 no-repeat;
        background-size: 16px 80px
    }

    .language-switch[_ngcontent-c3] h3[_ngcontent-c3] a.flag-en[_ngcontent-c3]:before, .language-switch[_ngcontent-c3] li[_ngcontent-c3] a.flag-en[_ngcontent-c3]:before {
        background-position: 0 -30px
    }

    .language-switch[_ngcontent-c3] h3[_ngcontent-c3] a.flag-jp[_ngcontent-c3]:before, .language-switch[_ngcontent-c3] li[_ngcontent-c3] a.flag-jp[_ngcontent-c3]:before {
        background-position: 0 -61px
    }

    .footer[_ngcontent-c3] .service[_ngcontent-c3] li.online[_ngcontent-c3] a[_ngcontent-c3] .smartisan-icon.info-question[_ngcontent-c3] {
        font-size: 14px;
        margin-right: 4px;
        top: -1px;
        position: relative
    }

    @media only screen and (-o-min-device-pixel-ratio: 200/100), only screen and (-webkit-min-device-pixel-ratio: 2), only screen and (min--moz-device-pixel-ratio: 2), only screen and (min-device-pixel-ratio: 2) {
        .language-switch[_ngcontent-c3] h3[_ngcontent-c3] a[_ngcontent-c3]:before, .language-switch[_ngcontent-c3] li[_ngcontent-c3] a[_ngcontent-c3]:before {
            background-image: url(//static.smartisanos.cn/index/assets/images/flag@2x.420897ca3fac7c233cdabf69761310d4.png)
        }
    }

    @media screen and (min-width: 737px) and (max-width: 1024px) and (max-device-width: 1024px) {
        .footer[_ngcontent-c3] .container[_ngcontent-c3] {
            width: auto;
            margin: auto 34px
        }

        .footer[_ngcontent-c3] .siteinfo[_ngcontent-c3] .nav-footer[_ngcontent-c3] > li[_ngcontent-c3] {
            width: 80px
        }

        .footer[_ngcontent-c3] .privacy[_ngcontent-c3] li[_ngcontent-c3] {
            padding: 1px 3px 0
        }

        .footer[_ngcontent-c3] .privacy[_ngcontent-c3] {
            margin-left: 6px
        }
    }

    @-webkit-keyframes thumb-box {
        0% {
            -moz-transform: scale(.8);
            -webkit-transform: scale(.8);
            -o-transform: scale(.8);
            transform: scale(.8);
            opacity: 0
        }
        90% {
            -moz-transform: scale(1.01);
            -webkit-transform: scale(1.01);
            -o-transform: scale(1.01);
            transform: scale(1.01);
            opacity: 1
        }
        to {
            -moz-transform: scale(1);
            -webkit-transform: scale(1);
            -o-transform: scale(1);
            transform: scale(1);
            opacity: 1
        }
    }

    @keyframes thumb-box {
        0% {
            -moz-transform: scale(.8);
            -webkit-transform: scale(.8);
            -o-transform: scale(.8);
            transform: scale(.8);
            opacity: 0
        }
        90% {
            -moz-transform: scale(1.01);
            -webkit-transform: scale(1.01);
            -o-transform: scale(1.01);
            transform: scale(1.01);
            opacity: 1
        }
        to {
            -moz-transform: scale(1);
            -webkit-transform: scale(1);
            -o-transform: scale(1);
            transform: scale(1);
            opacity: 1
        }
    }</style>
    <script>
        // var numReg=/^([1-9][0-9]*){1,3}$/;
        $(function () {
            //计算小计 页面加载时计算
            // 数量*单价=小计
            function countTotal() {//计算小计方法
                var trArr = $("tbody").children();
                for (var i = 0; i < trArr.length; i++) {

                    trArr.eq(i).children().eq(4).children().eq(0).text((trArr.eq(i).children().eq(2).children().eq(0).text() * 1 * trArr.eq(i).children().eq(3).children().eq(1).val() * 1).toFixed(2));
                }
            }

            function countSum() {//计算总价
                var trArr = $("tbody").children();
                var sum = 0;
                for (var i = 0; i < trArr.length; i++) {
                    if (trArr.eq(i).children().eq(0).children().eq(0).prop("checked")) {
                        sum += (trArr.eq(i).children().eq(4).children().eq(0).text() * 1);
                    }

                }
                $("#sum").text(sum.toFixed(2))
            }

            countTotal();
            countSum();//计算总价


            $("#allChk").click(function () {
                var chkArr = $(".chk");
                for (var i = 0; i < chkArr.length; i++) {
                    chkArr.eq(i).prop("checked", $("#allChk").prop("checked"))
                }
                countSum();
            });

            $(".chk").click(function () {
                countSum();
            })


            $(".addNum").click(function () {
                var cartId = $(this).next().val();
                var numObj = $(this).prev();//获取前一个数量输入框的对象
                //var inNum=$(".inNum").val();


                var url = "${root}/cart_updateCart.action";
                $.post(url,
                    {"cartId": cartId, "tag": 0, "num": numObj.val()},
                    function (result) {
                        if (result.code == 200) {
                            numObj.val((numObj.val() * 1) + 1);
                            countTotal();
                            countSum();//计算总价
                        }
                    },
                    'json')
            })


            $(".subNum").click(function () {
                var cartId = $(this).next().next().next().val();
                var trObj = $(this).parent().parent();//当前行
                var numObj = $(this).next();
                //var inNum=$(this).val();
                var url = "${root}/cart_updateCart.action";

                if (numObj.val() == 1) {
                    if (confirm("是否移除购物车？")) {
                        $.post(url,
                            {"cartId": cartId, "tag": 1, "num": numObj.val()},
                            function (result) {
                                if (result.code == 200) {
                                    trObj.remove();
                                    countTotal();
                                    countSum();//计算总价

                                }
                            },
                            'json')
                    }


                } else {
                    $.post(url,
                        {"cartId": cartId, "tag": 1, "num": numObj.val()},
                        function (result) {
                            if (result.code == 200) {
                                numObj.val((numObj.val() * 1) - 1);
                                countTotal();
                                countSum();//计算总价
                            }
                        },
                        'json')
                }
            })


            $(".delete").click(function () {
                var cartId = $(this).next().val();
                var trObj = $(this).parent().parent();//当前行
                var url = "${root}/cart_deleteCart.action";
                if (confirm("是否移除购物车？")) {
                    $.post(url, {"cartId": cartId},
                        function (result) {
                            if (result.code == 200) {
                                trObj.remove();
                                countTotal();
                                countSum();//计算总价
                                Toast("删除成功", 4000);
                            }
                        },
                        'json')
                }

            })

            $(".inNum").blur(function inNum() {
                var cartId = $(this).next().next().val();
                var inNum = $(this).val();
                var numReg = /^\+?[1-9][0-9]*$/;
                if (!numReg.test(inNum)) {
                    Toast("请输入正整数", 3000);
                    return;
                }
                var url = "${root}/cart_update.action";
                $.post(url,
                    {"cartId": cartId, "tag": 0, "num": inNum, "tag": 3},
                    function (code) {
                        if (code == 200) {
                            //numObj.val((numObj.val()*1)+1);
                            countTotal();
                            countSum();//计算总价
                        }
                    },
                    'json')
            })
        })

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

    <script>

        //瞬时弹框
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

        $(function () {
            //显示模态框
            $("#sub").click(function () {
                var cart_size = "${size}";
                if(cart_size<=0){
                    Toast("购物车里空空如也，先去购物吧。",2000);
                    window.location.href="${root}/main_index";
                    return;
                }
                var chk_value = [];
                $('input[name="cartIds"]:checked').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数
                    chk_value.push($(this).val());//将选中的值添加到数组chk_value中
                });
                if (chk_value.length==0) {
                    Toast("请先选择你需要购买的商品吧。",2000);
                    return;
                }


                var html = "";
                var html1 = "";
                //显示地址信息
                $("#myModal").modal("show");
                var selectObj = $("#address");
                var url = "/address_getAddressList.action";
                $.post(url, {}, function (data) {
                    for (var i = 0; i < data.length; i++) {
                        html = html + "<option value='" + data[i].id + "'>" + data[i].addr + "</option>";
                    }
                    selectObj.append(html);
                }, 'json');
                $("#address").empty();

                //显示配送方式
                var typeObj = $("#type");
                var url1 = "/shippingMethod_getListMethod.action";
                $.post(url1,
                    {},
                    function (data) {
                        for (var i in data) {
                            html1 = html1 + "<option value='" + data[i]['shippingId'] + "'>" + data[i]['method'] + "</option>";
                        }
                        typeObj.append(html1);
                    }, 'json')
                $("#type").empty();
            })

            $("#affirm").click(function () {
                $("#shipping").val($("#type").val());
                $("#order_address").val($("#address").val());
                $("#order_form").submit();

            })


        })

    </script>

</head>
<body>
<!-- start header -->

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
<!--end header -->

<!-- start banner_x -->
<div class="banner_x center" style="margin-top: 50px">
    <a href="./index.html" target="_blank">
        <div class="logo fl"></div>
    </a>

    <div class="wdgwc fl ml40">我的购物车</div>
    <div class="wxts fl ml20">温馨提示：产品是否购买成功，以最终下单为准哦，请尽快结算</div>

    <div class="clear"></div>
</div>
<div class="xiantiao"></div>
<div class="gwcxqbj" style="height: auto; width: auto">
    <div class="gwcxd center">
        <div class="table-responsive">
            <form action="/order_createOrder.action" id="order_form" method="post">
                <table class="table text-center">
                    <thead class="text-center">
                    <tr>
                        <th style="width: 100px"><input type="checkbox" id="allChk"/> 全选</th>
                        <th class="text-center">商品名称</th>
                        <th class="text-center">单价</th>
                        <th class="text-center">数量</th>
                        <th class="text-center">小计</th>
                        <th class="text-center">单价</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%--form开始--%>


                    <input type="hidden" id="shipping" name="shipping" value="-1">
                    <input type="hidden" id="order_address" name="address" value="-1">
                    <c:forEach items="${lsCarts}" var="cart">
                        <tr>
                            <td><input type="checkbox" class="chk checkbox" name="cartIds" value="${cart.id}"/></td>
                            <td><a href="#"><img src=""/><span>${cart.goodsName}</span></a></td>
                            <td class="text-center"><span style="color: chocolate; font-weight: bold " >${cart.goodsPrice}</span></td>
                            <td>
                                <input type="button" value=" - " class="subNum "/>
                                <input style="width: 30px" disabled type="number" value="${cart.num}" class="inNum"/>
                                <input type="button" value=" + " class="addNum"/>
                                <input type="hidden" value="${cart.id}"/>
                            </td>
                            <td><span style="color: #ff5300;" class="subtotal"></span></td>
                            <td>
                                <a href="javascript:void(0)" style="color: red;" class="delete">删除</a>
                                <input type="hidden" value="${cart.id}"/>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>

                    <%--<input hidden="hidden" type="submit"  id="sub_tijiao">--%>

                </table>
            </form>
        </div>
    </div>
    <div class="jiesuandan mt20 center">
        <div class="tishi fl ml20">
            <ul>
                <li><a href="${root}/main_index">继续购物</a></li>
                <%--<li>|</li>
                <li>共<span></span>件商品，已选择<span></span>件</li>--%>
                <div class="clear"></div>
            </ul>
        </div>
        <div class="jiesuan fr">
            <div class="jiesuanjiage fl">合计（不含运费）：<span id="sum">0.00</span></div>
            <div class="jsanniu fr">
                <%--<input  class="jsan" type="submit" name="jiesuan"  value="去结算"/>--%>
                <button style="margin-top: 10px" id="sub" class="btn btn-warning">
                    去结算
                </button>
            </div>
            <div class="clear"></div>
        </div>
        <div class="clear"></div>
    </div>
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title" id="myModalLabel">添加收货地址</h3>
            </div>
            <div class="modal-body">
                请选择快递方式：
                <select name="shippingMethod" id="type" class="form-control select">
                    <option>请选择快递</option>
                </select>
                请选择收货地址：
                <select class="form-control select" name="address" id="address">
                    <option value="0">请选择收货地址</option>
                </select>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="affirm" class="btn btn-primary">确认订单</button>
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<!-- footer -->
<!--页面尾-->
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
