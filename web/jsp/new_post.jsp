<%--
  Created by IntelliJ IDEA.
  User: LiuTianyou
  Date: 2019/3/14
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../static/local/kindeditor/themes/default/default.css">
    <link rel="stylesheet" type="text/css" href="../static/local/kindeditor/plugins/code/prettify.css">
    <script type="text/javascript" src="../static/local/kindeditor/kindeditor-all-min.js"></script>
    <script type="text/javascript" src="../static/local/kindeditor/lang/zh-CN.js"></script>
    <script type="text/javascript" src="../static/local/kindeditor/plugins/code/prettify.js"></script>
    <script type="text/javascript" src="../static/jquery/jquery-3.0.0.js"></script>
    <link rel="stylesheet" type="text/css" href="../static/bootstrap/css/bootstrap.css">
    <script type="text/javascript" src="../static/bootstrap/js/bootstrap.min.js"></script>
    <script>
        KindEditor.ready(function(K) {
            var editor = K.create('#editor', {
                cssPath : '../static/local/kindeditor/css/prettify.css',
                uploadJson : '../static/local/kindeditor/upload_json.jsp',
                fileManagerJson : '../static/local/kindeditor/file_manager_json.jsp',
                afterBlur: function () { this.sync(); }
            });

        });
    </script>
    <script>
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
    </script>
    <script>
        //填充版块
        $(function(){
            function loadBlock(){
                $.post("../block_listBlock.action",function (data) {
                    $("#block").html("<option value='0'>请选择发帖版块</option>");
                    for(var i=0;i<data.length;i++){
                        $("#block").append("<option  name='"+data[i].blockTopic+"' value='"+data[i].id+"'>"+data[i].blockName+"</option>");
                    }
                    loadTopic(data[0].blockTopic);
                },"json");

            }

            $("#block").change(
                function () {
                    var str=$("#block").find("option:selected").attr("name");
                    loadTopic(str);

                }

            );
            function loadTopic(str){
                $.ajax({
                    url:"../topic_listTopicByString.action",
                    data:{topicStr:str},
                    async:true,
                    dataType:"json",
                    type:"post",
                    success:function (data) {
                        $("#topic").html("<option value='0'>请选择话题</option>");
                        for(var i=0;i<data.length;i++){
                            $("#topic").append("<option value='"+data[i].id+"'>"+data[i].topicTitle+"</option>");
                        }
                    }
                } );

            }
            loadBlock();
            //为提交按钮绑定事件
            $("#create").click(function () {
                //同步编辑器的内容
                $.post("../forum_createForum.action",{
                    blockId:$("#block").val(),
                    topic:$("#topic").val(),
                    content:$("#editor").val(),
                    captcha:$("#captcha").val(),
                    title:$("#title").val()
                },function (data) {
                    if(data.code!=200){
                        //验证码错误 或发帖失败
                        $("#captcha").val("");
                        refreshCaptcha();
                        Toast(data.msg,2000);
                    }else{
                        //TODO 跳转到 论坛主页
                        window.location.href="../forum_showForumList.action?blockId="+$("#block").val();
                    }
                },"json");



            });

            //验证码刷新事件
            function refreshCaptcha() {
                var src = $("#captcha_img").attr("src") + '?time=' + Math.random();
                $("#captcha_img").attr("src", src);
            }
            //验证码点击刷新
            $("#captcha_img").click(function () {
                refreshCaptcha();
            })
        });
        //填充话题
    </script>







</head>

<style type="text/css">
    .ededed{
        background-color: #ededed;
    }
    .price {
        font-size:20px;
        font-weight: bolder;
        margin-left: 5px;
        color: #ff5300;
        height: 34px;
        line-height: 34px;
        display: block;
        float: left;
    }
    .btnGroup{
        float: right;
        width: 100px;
    }
    .pager{
        clear: both;
    }
    #msg{position:fixed;
        top:300px;
        right:2px;
        z-index:10000;
        width:1px;
        height:52px;
        line-height:52px;
        font-size:20px;
        text-align:center;
        color:#fff;
        background:#360;
        display:none;}

    .barActive{
        color: #ff5300;
        font-size: 15px;
    }

</style>
<style>
    .header{
        padding: 23px 0;
        height: 88px;
        line-height: 66px;
        border-bottom: 1px solid #e1e1e1;
        background: #fff;
        box-shadow: 0 4px 10px 0 rgba(0,0,0,.05) ;
        margin-top: -20px;
    }
    iframe{
        margin-top: 5px;
        border: none;
    }
    .topCart{
        margin-top: 16px;
        margin-left:70px ;
        margin-right: 10px;
    }
    .photoAndName{
        margin-right: 0px;
        margin-left:35px;
        margin-top: 2px;
        margin-bottom: 0px;

    }
</style>
<script type="text/javascript" src="../static/jquery/jquery-3.0.0.js"></script>
<script type="text/javascript" src="../static/local/js/fly.js"></script>
<link rel="stylesheet" type="text/css" href="../static/bootstrap/css/bootstrap.css">
<script type="text/javascript" src="../static/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/static/local/css/homepage.css">
<link rel="stylesheet" href="/static/local/css/weui.min.css">
<link rel="stylesheet" href="/static/local/css/animate.min.css">
<link rel="stylesheet" href="/static/local/css/test.css">
<script src="/static/local/js/weui.js"></script>
<style>
    ul,li{
        list-style: none;
    }
    a{
        color: #333333;

    }
    a:hover{
        color: #333333;
    }
    a:active{
        color: #333333;
    }


</style>
<style>
    .cartNum{
        right: 400px;
        top: 12px;
        position:absolute;
    }
    .photo{
        height: 40px;
        border: 2px solid #c1c1c1;
        width:40px;
    }

</style>


<%--检测登录--%>
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
<script>
    $(function () {
        $.post("../block_listBlock.action",function (data) {
            $("#blockList").html("");
            for(var  i=0;i<data.length;i++){

                $("#blockList").append("<a href='../forum_showForumList.action?blockId="+data[i].id+"'><div class='col-md-4' style='padding: 3px'>\n" +
                    "    <div class='Mysearch'>\n" +
                    "        <img src='../static/local/img/"+data[i].blockCover+"'>\n" +
                    "        <h3>"+data[i].blockName+"</h3>\n" +
                    "        <p style='font-size: 15px'>今日：<span style='color: red'>"+data[i].todayActive+"</span> &nbsp;&nbsp;主题：<span>"+data[i].blockAllForum+"</span>&nbsp;&nbsp;贴数：<span>"+data[i].blockAllComment+"</span></p>\n" +
                    "    </div></div></a>")
            }
        },"json");



    })
</script>

<style>
    .select{
        width:150px;
        margin-top: 15px;
        float: left;
        margin-bottom: 15px;
        margin-right: 20px;
    }
    #title{
        margin-bottom: 15px;

    }
    #editor{
        width: 70%;
        margin-bottom: 20px;
        height: 300px;

    }
</style>
<style>
    .Mysearch{
        position: relative;
        z-index: 0;
        margin-top: 10px;
        width: 98%;
        height: 100px;
        margin-left: 6px;
        margin-right: 6px;
        box-sizing: border-box;
        border: 1px solid rgba(0,0,0,.14);
        border-radius: 8px;
        background: #fff;
        box-shadow: 0 3px 8px -6px rgba(0,0,0,.1);
        padding-left: 10px;
        padding-top: 12px;
        cursor: pointer;
    }

    .Mysearch img{
        float: left;
        padding:  10px;
        margin-left: -5px;
        width: 68px;
        height: 68px;

    }
    .Mysearch h3{
        margin-top: 8px;
        margin-left: 70px;
    }
    .Mysearch p{
        margin-left: 70px;
        font-size: 15px;
        color: #6e6e6e;
    }
</style>
<script type="text/javascript">

    $(function () {
        //检测登录
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
        measureNum();
    });

</script>

<body style="background-color: #ededed">



<div id="header" class="header">
    <div class="main" >
        <span id="header-logo" style="display: flex;justify-content: left;align-items: center;"><img style="height: 35px;margin-right: 10px;" src="/static/images/logo.png" alt=""><span>跳蚤论坛</span></span>
        <ul style="margin-left: 92px;padding-inline-start: 0px">
            <li id="navbar-homepage"  ><a  href="../control_index.action"  target="_self">首页</a></li>
            <li id="navbar-apk"><a  href="../control_shop.action" target="_self">自营商城</a></li>
            <li id="navbar-game"><a class="barActive" href="../control_forum.action" target="_self">跳蚤论坛</a></li>
            <li id="navbar-market"><a href="../control_app.action" target="_self">跳蚤市场APP</a></li>
            <li id="navbar-about"><a href="../control_aboutUs.action" target="_self">关于我们</a></li>
        </ul>
        <!--购物车图标-->
        <!--<a href="../cart_lsCarts.action"><img class="topCart" id="cart" width="30px" height="30px" src="../static/local/img/topCart.png" /></a>-->
        <a href="../cart_lsCarts.action"><img class="topCart" id="cart" width="30px" height="30px" src="../static/local/img/topCart.png" /></a>
        <!--购物车徽标-->
        <p class="cartNum" style="width: 20px"><a href="#"> <span class="badge" style="background-color: #888">0</span></a></p>
        <div id="loginOrNot" class="photoAndName">
            <a href="../jsp/customer_login.jsp">登录</a>&nbsp;|&nbsp;<a href="../jsp/register.jsp">注册</a>
        </div>
    </div>

</div>
<div class="container" style="margin-top: 80px;min-height: 500px">
    <select id="block" class="form-control select">
        <option value="0">请选择发帖版块</option>

    </select>
    <select id="topic" class="form-control select " >
        <option value="0">请选择话题</option>
    </select>

    <input style="width: 70%; clear: both "  type="text"  id="title" class="form-control" placeholder="请输入标题"/>

    <textarea id="editor" style="margin-bottom: 50px" ></textarea>

    <input type="text" style="margin-top: 20px; float:left;width: 120px" id="captcha"  placeholder="请输入验证码" class="form-control">
    <img style="width: 98px; margin-left: 15px; border:1px #666 solid;margin-top: 20px"  id="captcha_img" src="/customer_captcha.action" `>
    <button style="margin-top: 20px;margin-left: 506px" type="button"  id ="create" class="btn btn-info">发布</button>
</div>
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
</body>
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
</html>
