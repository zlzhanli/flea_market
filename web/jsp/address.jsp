<%--
  Created by IntelliJ IDEA.
  User: 刘天佑
  Date: 2019/3/6
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <script type="text/javascript" src="../static/jquery/jquery-3.0.0.js">
    </script>
    <script>

        //瞬时弹框
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

        $(function () {
            var userId = 0;
            //去服务器获取Session 中的userId
            $.post("../address_getCustomId.action", function (data) {
                userId = data.toString();
                getAddressList();
            }, "json");

            function getContent(control, id, defaultValue) {
                $.post("../city_getCity.action?pId=" + id, function (data) {
                    control.html("");
                     control.append("<option value='-1'>" + defaultValue + "</option>");
                    for (var i = 0; i < data.length; i++) {
                        control.append("<option value='" + data[i].id + "'>" + data[i].cityname + "</option>")
                    }
                }, "json");
            }

            //省份设置内容
            getContent($("#province"), 1, "请选择省");
            //城市设置内容
            $("#province").on("change", function () {
                id = $(this).val();
                getContent($("#city"), id, "请选择市");
                $("#area").html("<option value='-1'>请选择区</option>")
            });

            //地区设置内容
            $("#city").on("change", function () {
                id = $(this).val();
                getContent($("#area"), id, "请选择区")
            });

            //获取详细地址
            function getAddress() {
                address = $("#province").find("option:selected").text()
                    + " " + $("#city").find("option:selected").text()
                    + " " + $("#area").find("option:selected").text()
                    + " " + $("#detail_addr").val();
                return address;

            }

            //控件绑定验证事件
            $("#tel").blur(function () {
                checkTel();
            });
            $("#name").blur(function () {
                checkName()
            });
            $("#addr").blur(function () {
                checkDetial();
            });
            //验证表单
            //验证姓名
            function checkName() {
                nameStr = $("#name").val();
                var han = /^[\u4e00-\u9fa5]{2,5}$/;
                res = han.test(nameStr);
                if (res) {
                    $("#name").popover("hide");
                } else {
                    $("#name").popover("show");
                }
                return res;
            }

            //验证电话号码
            function checkTel() {
                telStr = $("#phone").val();
                var ph = /^1[345789]\d{9}$/;
                res = ph.test(telStr);
                if (res) {
                    $("#phone").popover("hide");
                } else {
                    $("#phone").popover("show");
                }
                return res;
            }

            //验证门牌号
            function checkDetial() {
                res = $("#detail_addr").val().length < 5;

                if (res) {
                    $("#detail_addr").popover("show");
                } else {
                    $("#detail_addr").popover("hide");
                }
                return !res;
            }

            //保存数据
            function saveaddr() {
                var check = ($("#isDefault").is(":checked") == true ? 1 : 0);
                custom_id = userId;
                $.post("../address_add.action", {
                    name: $("#name").val(),
                    phone: $("#phone").val(),
                    province: $("#province").val(),
                    city: $("#city").val(),
                    area: $("#area").val(),
                    detailAddr: $("#detail_addr").val(),
                    isDefault: check,
                    customerId: custom_id,
                    addr: getAddress()
                }, function (data) {
                    if (data.code == 0 || data.code == -1) {
                        //关闭窗
                        $('#myModal').modal('hide');


                    } else {
                        $("#myModal").popover("show");
                    }

                    Toast(data.msg, 2000);
                    getAddressList();
                }, "json");
            }

            //验证地区
            function checkSelect() {
                if ($("#province").val() <= 0 || $("#city").val() <= 0 || $("#area") <= 0) {
                    $("#area").popover("show");
                    return false;
                }
                return true;
            }

            //update 方法
            function update() {
                var check = ($("#isDefault").is(":checked") == true ? 1 : 0);
                custom_id = userId;
                $.post("../address_update.action", {
                    id: $("#addrId").val(),
                    name: $("#name").val(),
                    phone: $("#phone").val(),
                    province: $("#province").val(),
                    city: $("#city").val(),
                    area: $("#area").val(),
                    detailAddr: $("#detail_addr").val(),
                    isDefault: check,
                    customerId: custom_id,
                    addr: getAddress()
                }, function (data) {
                    if (data.code == 0) {
                        //关闭窗
                        $('#myModal').modal('hide');
                        getAddressList();
                    } else {
                        $("#myModal").popover("show");
                    }

                    Toast(data.msg, 2000);
                    getAddressList();
                }, "json");
            }

            //提交添加事件
            $("#save_addr").click(function () {
                if (checkDetial() & checkName() & checkTel() & checkSelect()) {
                    if ($("#addrId").val() <= 0) {
                        saveaddr();
                    } else {
                        update();
                    }
                }
            });

            //获取用户的地址列表


            function getAddressList() {
                $.ajax({
                    url: "../address_list.action",
                    type: "post",
                    data: {customerId: userId},
                    async: false,
                    dataType: "json",
                    success: function (data) {
                        $("#list").html("");
                        if (data.length <= 0) {
                            $("#list").html("<h5>暂无收货地址，先去添加一个吧！</h5>")
                        }
                        for (var i = 0; i < data.length; i++) {
                            $("#list").append(
                                " <div class='subgrzl ml40'><span>" +
                                data[i].receiverName + "</span><span>" +
                                data[i].receiverPhone + "</span>  <span>" +
                                data[i].addr + (i == 0 && data[i].isDefault == 1 ? "&nbsp;&nbsp;&nbsp;<img style='margin-top:-4px' src='../static/local/img/default_address.png'/>" : "") + "</span>\n" +
                                "<span><a class= 'edit' name='" + data[i].id + "'href='javascript:void(0)' >编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a class= 'del' name='" + data[i].id + "' href='javascript:void(0)'>删除</a> </span></div>\n")
                        }
                    }


                });
            }

            //getAddressList();
            //删除按钮绑定时间
            $("body").delegate(".del", "click", function () {
                var addrId = $(this).attr("name");
                $.post("../address_del.action", {id: addrId}, function (data) {
                    if (data.toString() != 200) {
                        //TODO 显示一个好看的提示
                        Toast("删除失败", 2000);
                    }
                    //重新加载数据
                    getAddressList();
                }, "json")

            });
            //点击编辑后将数据显示到弹出层
            $("body").delegate(".edit", "click", function () {
                var addrId = $(this).attr("name");
                $.post("../address_getAddressById.action", {id: addrId}, function (data) {
                    //将值设置到弹出框中
                    $("#name").val(data.receiverName);
                    $("#phone").val(data.receiverPhone);
                    $("#province").val(data.province);
                    //加载一次城市信息
                    getContent($("#city"), data.province, "请选择市");
                    //切换城市信息
                    setTimeout(function () {
                        $("#city").val(data.city);
                    }, 70);
                    //加载区县信息
                    getContent($("#area"), data.city, "请选择区县");
                    //切换区县
                    setTimeout(function () {
                        $("#area").val(data.area);
                    }, 70);
                    $("#detail_addr").val(data.detial);
                    if (data.isDefault == 1) {
                        $("#isDefault").prop({checked: true});
                    } else {
                        $("#isDefault").prop({checked: false});
                    }
                    $("#addrId").val(data.id);
                    $('#myModal').modal('show')
                }, "json");

            });
            //点击添加按钮 清空 表单数据
            $("#pushModal").click(
                function () {

                    $("#dataForm")[0].reset();
                    $("#addrId").val(-1);
                }
            )

            //模态框隐藏触发事件
            $('#myModal').on('hide.bs.modal', function () {
                $("#name").popover("hide");
                $("#phone").popover("hide");
                $("#area").popover("hide");
                $("#detail_addr").popover("hide");
            })

        })
    </script>
    <link rel="stylesheet" href="../static/bootstrap/css/bootstrap.css"></link>
    <script type="text/javascript" src="../static/bootstrap/js/bootstrap.js"></script>
    <title></title>
    <style type="text/css">
        .select {
            width: 160px;
            text-align-last: center;
            display: inline;
            margin-right: 20px;
        }

        .form-control {
            margin-bottom: 10px;
        }

        .fl {
            float: left;
        }

        .fr {
            float: right;
        }

        .ml40 {
            margin-left: 40px;
        }

        .ml20 {
            margin-left: 20px;

        }

        .rtcont {
            width: 978px;
            height: 500px;
            background: #fff;
            overflow: hidden;
        }

        .rtcont .ddzxbt {
            width: 938px;
            height: 60px;
            line-height: 60px;
            font-size: 22px;
            font-weight: bold;
            color: rgb(117, 117, 117);
            padding-left: 40px;
            border-bottom: 1px solid #ccc;
        }

        .rtcont .grzlbt {
            width: 938px;
            height: 60px;
            line-height: 60px;
            font-size: 20px;
            color: rgb(117, 117, 117);
        }

        .rtcont .subgrzl {
            height: 45px;
            line-height: 45px;
            width: 900px;
            background: rgb(253, 253, 253);
            border: 1px solid #aaa;
            margin-top: 10px;
            margin-bottom: 10px;
            border-radius: 3px;
        }

        .rtcont .subgrzl span:nth-of-type(1) {
            display: inline-block;
            font-size: 15px;

            color: rgb(117, 117, 117);
            width: 110px;
            height: 45px;
            line-height: 45px;
            font-weight: bold;
            padding-left: 20px;
        }

        .rtcont .subgrzl span:nth-of-type(2) {
            display: inline-block;
            font-size: 15px;
            font-weight: bold;
            color: rgb(117, 117, 117);
            width: 110px;
            height: 45px;
            line-height: 45px;
            padding-left: 20px;
        }

        .rtcont .subgrzl span:nth-of-type(3) {
            display: inline-block;
            font-size: 15px;
            color: rgb(117, 117, 117);
            width: 570px;
            height: 45px;
            line-height: 45px;
            padding-left: 20px;
        }

        .rtcont .subgrzl span:nth-of-type(4) {
            display: inline-block;
            font-size: 15px;
            color: rgb(117, 117, 117);
            width: 100px;
            height: 45px;
            line-height: 45px;
        }

        .rtcont .subgrzl span a {
            color: teal;
        }

        .rtcont .subgrzl span a:hover {
            color: #ff6700;
        }

        .del {

        }


    </style>
</head>
<body>
<div class="rtcont fr">
    <div class="grzlbt ml40">收货地址 <img id="pushModal" src="../static/local/img/add_address.png" data-toggle="modal"
                                       data-target="#myModal"/></div>
    <div id="list">
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
                <form id="dataForm" method="get">


                    <input type="hidden" id="addrId" value="-1">

                    <input placeholder="收货人姓名" type="text" class="form-control" id="name" name="name"
                           data-container="body" data-placement="right"
                           data-content="请输入2-5个汉字"
                    />
                    <input placeholder="电话号码" type="text" class="form-control" id="phone" name="phone"
                           data-container="body" data-placement="right"
                           data-content="请填写正确的电话号码"
                    />
                    <select id="province" name="province" class="form-control select">
                        <option value="-1">请选择省</option>
                    </select>
                    <select id="city" name="city" class="form-control select">
                        <option value="-1">请选择市</option>
                    </select>
                    <select id="area" name="area" class="form-control select" data-container="body"
                            data-placement="right"
                            data-content="请选择地址信息">
                        <option value="-1">请选择区</option>
                    </select>


                    <input placeholder="街道门牌号" type="text" class="form-control" id="detail_addr" name="detail_addr"
                           data-container="body" data-placement="right"
                           data-content="请至少输入5个字符"
                    />
                    <div class="checkbox">
                        <label><input type="checkbox" id="isDefault" name="isDefault" value="">设为默认地址</label>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="save_addr" class="btn btn-primary">保存地址</button>
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

</body>
</html>
