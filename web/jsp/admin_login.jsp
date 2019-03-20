<%--
  Created by IntelliJ IDEA.
  User: zhanghaohao
  Date: 2019/3/6
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh_CN">
<head>

    <meta charset="UTF-8">
    <title>管理员登录</title>
    <link rel="stylesheet" type="text/css" href="${css}/login.css">
    <link rel="stylesheet" type="text/css" href="${css}/login.css">
    <link rel="stylesheet" href="${bootstrap}/css/bootstrap.min.css">
    <script type="text/javascript" src="${jquery}/jquery-3.0.0.js"></script>
    <script type="text/javascript" src="${bootstrap}/js/bootstrap.min.js"></script>

    <style>
        body {
            background-image: url("${img}/admin_bg.jpg");
        }
        input {
            color: black;
        }
    </style>

    <script>
        $(function () {
            $("#captcha_img").click(function () {
                var src = $("#captcha_img").attr("src") + '?time=' + Math.random();
                $("#captcha_img").attr("src", src);
            });

            $("#submit").click(function () {
                var name = $("#name").val();
                var password = $("#password").val();
                var captcha = $("#captcha").val();
                if (name == "") {
                    Toast("用户名不能为空");
                    return;
                }
                if (password == "") {
                    Toast("密码不能为空");
                    return;
                }
                if (captcha == "") {
                    Toast("验证码不能为空");
                    return;
                }

                $.ajax({
                    url: "${root}/administrator_login.action",
                    type: "POST",
                    data: {
                        name: name,
                        password: password,
                        captcha: captcha
                    },
                    dataType: "json",
                    success: function (data) {
                        if (data.code == 200) {
                            window.location.href = "${root}/html/admin/index.html";
                        } else {
                            alert(data.msg)
                        }
                    }
                })
                ;
            });

        })

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

<div style="width: auto; height: 130px; margin-top: 20px">
    <p style="font-size: 40px; font-family: 'Microsoft Yahei', '微软雅黑'; text-align: center; padding-top: 25px">跳蚤市场——后台管理系统</p>
</div>

<div class="login" style=" margin: auto; height: 500px; background-color: transparent; border: 4px grey solid">
    <div class="login_center">
        <div class="login_top">
            <div class="left fl">管理员登录</div>
            <div class="clear"></div>
            <div class="xian center"></div>
        </div>
        <div class="login_main center">
            <div class="username">用户名:&nbsp;
                <input id="name" class="shurukuang" type="text" name="username" placeholder="请输入你的用户名"/></div>
            <div class="username">密&nbsp;&nbsp;&nbsp;&nbsp;码:&nbsp;
                <input id="password" class="shurukuang" type="password" name="password" placeholder="请输入你的密码"/>
            </div>
            <div class="username">
                <div class="left fl">验证码:&nbsp;
                    <input id="captcha" class="yanzhengma" type="text" name="captcha" placeholder="请输入验证码"/></div>
                <div class="right fl">
                    <img id="captcha_img" style="width: 120px" src="${root}/administrator_captcha.action">
                </div>

                <div class="clear"></div>
            </div>
        </div>
        <div class="login_submit">
            <input style="margin-left: 100px; width: 150px" id="submit" class="btn btn-info btn-lg" type="button" name="submit" value="立即登录">
        </div>
    </div>
</div>

<footer style="margin-top: 150px; height: 130px">
    <div class="copyright">简体 | 繁体 | English | 常见问题</div>
    <div class="copyright">版权所有<img src="" alt=""></div>

</footer>

</body>
</html>
