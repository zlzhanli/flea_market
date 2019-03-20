<%--
  Created by IntelliJ IDEA.
  User: zhanghaohao
  Date: 2019/3/18
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员修改密码</title>
    <link rel="stylesheet" href="${bootstrap}/css/bootstrap.css">
    <script src="${jquery}/jquery-3.0.0.js" type="text/javascript"></script>
    <script src="${bootstrap}/js/bootstrap.js" type="text/javascript"></script>
    <script type="text/javascript">

        $(function () {
            $("#old_pwd").blur(function () {
                var oldPwd = $("#old_pwd").val();
                if(oldPwd == null || oldPwd == ""){
                    $("#span_old").text("请输入原密码");
                }
                var url = "${root}/administrator_checkPwd.action";
                $.post(url,{"password":oldPwd},
                    function (data) {
                        if(data.code==200){
                            $("#span_old").text("密码正确")
                            $("#new_pwd").prop("disabled",false);
                            $("#new_pwd_again").prop("disabled",false);
                            $("#subtijiao").prop("disabled",false);
                        }else{
                            $("#span_old").text("原密码输入错误")
                            $("#new_pwd").prop("disabled",true);
                            $("#new_pwd_again").prop("disabled",true);
                            $("#subtijiao").prop("disabled",true);
                        }
                    }
                ,'json')
            });

            $("#subtijiao").click(function () {
                var newPwd = $("#new_pwd").val();
                var newPwdAgain = $("#new_pwd_again").val();
                if(newPwd==""||newPwd==null){
                    $("#span_new").text("请输入新密码")
                }
                if(newPwdAgain==null||newPwdAgain==""){
                    $("#span_new_again").text("请再次输入新密码")
                }
                if(newPwd!=newPwdAgain){
                    $("#span_new_again").text("两次密码输入不一致")
                }
                if(newPwd.length < 6){
                    $("#span_new_again").text("更多字符")
                }
                var url = "${root}/administrator_updatePassword.action";
                $.post(url,
                    {"password":newPwd},
                    function (data) {
                        if(data.code == 200){
                            $("#new_pwd").prop("disabled",true);
                            $("#new_pwd_again").prop("disabled",true);
                            $("#subtijiao").prop("disabled",true);
                            $("#span_new_again").text("修改成功")
                        }
                    },
                    'json')
            })
            })

    </script>
</head>
<body>

<div style="padding: 100px 100px 10px;">
    <div class="bs-example bs-example-form">

        <div class="input-group input-group-lg">
            <span class="input-group-addon">原密码</span>
            <input id="old_pwd" type="text" class="form-control" placeholder="old password">
        </div>
        <br>
        <span id="span_old"></span>
        <div class="input-group input-group-lg">
            <span class="input-group-addon">新密码</span>
            <input id="new_pwd" type="password" class="form-control" placeholder="new password">
        </div>
        <br>
        <span id="span_new"></span>
        <div class="input-group input-group-lg">
            <span class="input-group-addon">再次输入新密码</span>
            <input id="new_pwd_again" type="password" class="form-control" placeholder="new password again">
        </div>
        <br>
        <span id="span_new_again"></span><br>
        <button type="button" class="btn btn-warning" id="subtijiao">点击提交</button>
    </div>
</div>
</body>
</html>
