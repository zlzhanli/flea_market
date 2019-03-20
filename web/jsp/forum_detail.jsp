<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script type="text/javascript" src="../static/jquery/jquery-3.0.0.js"></script>
    <link rel="stylesheet" type="text/css" href="../static/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="../static/local/kindeditor/themes/simple/simple.css">
    <link rel="stylesheet" type="text/css" href="../static/local/kindeditor/plugins/code/prettify.css">
    <script type="text/javascript" src="../static/local/kindeditor/kindeditor-all-min.js"></script>
    <script type="text/javascript" src="../static/local/kindeditor/lang/zh-CN.js"></script>
    <script type="text/javascript" src="../static/local/kindeditor/plugins/code/prettify.js"></script>
    <script type="text/javascript" src="../static/bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/static/local/css/homepage.css">
    <link rel="stylesheet" href="/static/local/css/animate.min.css">

    <%--头部使用的CSS--%>
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

    <%--尾部使用的CSS--%>
    <link rel="stylesheet" href="/static/local/css/test.css">

    <script>
        KindEditor.ready(function(K) {
            var editor = K.create('#editor', {
                cssPath : '../static/local/kindeditor/css/prettify.css',
                uploadJson : '../static/local/kindeditor/upload_json.jsp',
                fileManagerJson : '../static/local/kindeditor/file_manager_json.jsp',
                afterBlur: function () { this.sync(); }
            });

            var modalEditor = K.create('#modalEditor', {
                cssPath : '../static/local/kindeditor/css/prettify.css',
                uploadJson : '../static/local/kindeditor/upload_json.jsp',
                fileManagerJson : '../static/local/kindeditor/file_manager_json.jsp',
                afterBlur: function () { this.sync(); }
            });

        });
    </script>


    <script type="text/javascript" src="../static/bootstrap/js/bootstrap.min.js"></script>
    <style>
        p {
        }

        .time {
            margin-top: 5px;
            height: 38px;
            margin-left: 6px;
            margin-right: 6px;
            box-sizing: border-box;
            border: 1px solid rgba(0, 0, 0, .14);
            background: #fff;
            box-shadow: 0 3px 8px -6px rgba(0, 0, 0, .1);
            padding-left: 10px;
            padding-top: 8px;
            cursor: pointer;
            justify-content: space-between;
            display: flex;
        }

        .replyandts {
            margin-top: -1px;
            height: 38px;
            margin-left: 6px;
            margin-right: 6px;
            box-sizing: border-box;
            border: 1px solid rgba(0, 0, 0, .14);
            background: #fff;
            box-shadow: 0 3px 8px -6px rgba(0, 0, 0, .1);
            padding-left: 10px;
            padding-top: 8px;
            cursor: pointer;

        }

        .title {
            margin-top: -11px;
            height: 44px;
            margin-left: 6px;
            margin-right: 6px;
            box-sizing: border-box;
            border: 1px solid rgba(0, 0, 0, .14);
            background: #fff;
            box-shadow: 0 3px 8px -6px rgba(0, 0, 0, .1);
            padding-left: 10px;
            padding-top: 12px;
            cursor: pointer;
        }


        body {
            background-color: #ededed;
        }

        .left {
            padding-left: 10px;
            padding-top: 12px;
            margin-top: 5px;
            box-sizing: border-box;
            border: 1px solid rgba(0, 0, 0, .14);
            background: #fff;
            box-shadow: 0 3px 8px -6px rgba(0, 0, 0, .1);


        }

        .forumphoto {
            margin-top: 4px;
            width: 150px;
            margin-left: 10px;
            height: 150px;
            border: 5px solid #ffffff;
        }

        #louzhou, .list_item {
            min-height: 279px;
        }

        .right {
            margin-left: -11px;
        }

        #content {
            margin-left: 6px;
            min-height: 200px;
            margin-right: 6px;
            box-sizing: border-box;
            border: 1px solid rgba(0, 0, 0, .14);
            background: #fff;
            box-shadow: 0 3px 8px -6px rgba(0, 0, 0, .1);
            padding-left: 10px;
            padding-top: 10px;
            cursor: pointer;
        }

        .content {
            margin-top: -11px;
            min-height: 200px;
            margin-left: 6px;
            margin-right: 6px;
            box-sizing: border-box;
            border: 1px solid rgba(0, 0, 0, .14);
            background: #fff;
            box-shadow: 0 3px 8px -6px rgba(0, 0, 0, .1);
            padding-left: 10px;
            padding-top: 10px;
            cursor: pointer;
        }

        .nickname {
            margin-top: 5px;
            width: 100%;
            text-align: center;
            font-size: 15px;
        }

        .reply {
            display: block;
            width: 24px;
            height: 24px;
            background-repeat: no-repeat;
            background-size: 24px 24px;
            background-image: url("../static/local/img/reply.png");

        }

        .reply:hover {
            display: block;
            width: 24px;
            height: 24px;
            background-repeat: no-repeat;
            background-size: 24px 24px;
            background-image: url("../static/local/img/reply_activity.png");
        }

        .reachTextReply{
            min-height: 350px;
            margin-left: 3px;
            margin-right: 6px;
            padding-right: 10px;
            width: 97%;
            margin-top: 5px;
            box-sizing: border-box;
            border: 1px solid rgba(0, 0, 0, .14);
            background: #fff;
            box-shadow: 0 3px 8px -6px rgba(0, 0, 0, .1);
            padding-left: 10px;
            padding-top: 10px;
            cursor: pointer;
            margin-bottom: 10px;

        }
        #content img {
            max-width: 800px;
        }

        .content img {
            max-width: 800px;
        }
        .quote {
            overflow: hidden;
            margin: 10px 0;
            color: #666;
            margin: 10px 0;
            padding: 10px 10px 10px 65px;
            zoom: 1;
            background: #F9F9F9 url(http://bbs-static.smartisan.com/static/image/common//icon_quote_s.gif) no-repeat 20px 6px;
        }
        .quote blockquote {
            display: inline-block;
            margin: 0;
            padding: 0 65px 5px 0;
            background: url(http://bbs-static.smartisan.com/static/image/common//icon_quote_e.gif) no-repeat 100% 100%;
            line-height: 1.6;
            zoom: 1;
            margin: 0;
            padding-right: 89px;
        }
        blockquote {
            display: block;
            margin-block-start: 1em;
            margin-block-end: 1em;
            margin-inline-start: 40px;
            margin-inline-end: 40px;
        }

        .loubiao{
            margin-right:15px;
        }

    </style>

    <script>
        //回到页面顶端
        function smoothscroll(){
            var currentScroll = document.documentElement.scrollTop || document.body.scrollTop;
            if (currentScroll > 0) {
                window.requestAnimationFrame(smoothscroll);
                window.scrollTo (0,currentScroll - (currentScroll/5));
            }
        }
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

        function dealTimeJson(jsonStr) {
            var year = 1900 + jsonStr.year;
            var month = jsonStr.month + 1;
            var day = jsonStr.date;
            var hour = jsonStr.hours;
            var minutes = jsonStr.minutes;
            return year + "-" + month + "-" + day + " " + hour + ":" + minutes;
        }


        function  showParent (parent,parentId){
            if(parentId!=0){
               return ("<div class='quote'>\n" +
                    "        <blockquote><font size='2'><a\n>" +
                    "<font color='#999999'>"+parent.customer.nickName+"&nbsp;&nbsp;发表于"+dealTimeJson(parent.time)+"<br>\n" +
                    "        </font></a></font>" +contentAbstract(parent.content)+
                    "        </blockquote>\n" +
                    "    </div><hr>")
            }
            else{
                return "";
            }
        }

        function contentAbstract( content){
            return content;

        }
        $(function () {
            // 绑定分页事件
            $(".pagination").delegate(".pageBtn","click",function (event) {
                var name= $(this).attr("name");
                var reg=/^\d+$/;
                if(reg.test(name)){
                    getComment(name);
                }else if(name=="pre"){
                    getComment(current-1);
                }else if(name="next"){
                    getComment(current+1);
                }
                smoothscroll();
            });


            //为用户回复用户的小黄标设置事件
            $("#list").delegate(".reply","click",function () {
               var content=$(this).parent().prev().html();
               var nickName=$(this).parent().parent().prev().children().next().html()
               var id=$(this).attr("name");
                console.log(content);

              var title="回复 => "+nickName+":"+ contentAbstract(content);
               $("#myModalLabel").html(title);
              $("#select_comment_id").val(id);
              $("#replyModal").modal("show");


            });

            //为模态框提交按钮绑定时间

            $("#submitReplyToCustomer").click(function () {
                var id=$("#select_comment_id").val();
                var content=$("#modalEditor").val();
                var forumId=$("#forum_id").val();
                 // alert(id);
                 // alert(content);
                 // alert(forumId);
                creatComment(id,content);
                $("#modalEditor").html("");
                $("#close").click();

            });

            //发送请求获取主题帖内容
            function getlouZhu() {
                //TODO 通过id 获取 帖子内容
                $.post("../forum_findForumById.action", {
                    id: $("#forum_id").val()
                }, function (data) {
                    loadFirstLevel(data)
                }, "json");
            }

            //发送请求获取回复内容
            var num;
            var current;
            var pages;

            function getComment(page) {
                //TODO :获取评论
                $.post("../comment_listComment.action",{
                    forumId:$("#forum_id").val(),
                    page:page
                },function (data) {
                    $(".pagination").html("");
                    loadComment(data);
                     num=data.totalCount;
                     current=data.currPage;
                     pages=data.totalPage;
                    if(num>0){
                        //显示上一页
                        if(current>1){
                            //TODO 显示上一页
                            $(".pagination").append("<li><a name='pre' class='pageBtn' href='javascript:void(0)' onclick='ajaxData(current-1)'>&laquo;</a></li>");
                        }else{
                            $(".pagination").append("<li   class='disabled '><a href='javascript:void(0)'>&laquo;</a></li>");

                        }
                        for(var i=1;i<=pages;i++){
                            if(i==current){
                                $(".pagination").append("<li name='"+i+"' class='active'><a  href='javascript:void(0)'>"+i+"</a></li>");
                            }else{
                                $(".pagination").append("<li><a name='"+i+"' href='javascript:void(0)' class='pageBtn'>"+(i)+"</a></li>");
                            }
                        }
                        //显示下一页
                        if(current>=1&&current<pages){
                            //TODO 显示下一页
                            $(".pagination").append("<li><a name ='next' href='javascript:void(0)' class='pageBtn'>&raquo;</a></li>");
                        }else{
                            $(".pagination").append("<li class='disabled'><a href='javascript:void(0)'>&raquo;</a></li>");
                        }

                    }else{
                        $(".pagination").html("<h4>还没有评论快来抢沙发吧</h4>");
                    }



                },"json");
            }
            getlouZhu();
            getComment(1);
            //为快速回复绑定事件
            $("#firstLeve").delegate("#swiftReply", "click", function () {
                creatComment(null, $("#reply_text").val());
                $("#reply_text").val("");
            });
            //为页面底端的回复绑定时间
            $("#reachRelpay").click(function () {
                creatComment(null, $("#editor").val());
                KindEditor.html("#editor","");
            })

        });
        function loadFirstLevel(data) {
            $("#firstLeve").html("");
            $("#firstLeve").append(" <div id='louzhou'>\n" +
                "            <div class='col-md-2 left' >\n" +
                "                <img class='forumphoto' src='../upload/" + data.customer.photo + "'/>\n" +
                "                <p class='nickname'>" + data.customer.nickName + "</p>\n" +
                "            </div>\n" +
                "\n" +
                "            <div class='col-md-10 right'>\n" +
                "                <div><p class='time '><span>发表于: <sapan>" + dealTimeJson(data.forumGmtTime) + "</sapan></span> <span class='loubiao'>楼主</span></p></div>\n" +
                "              <div><h4 class='title'>" + data.forumTitle + "</h4></div>\n" +
                "                <div id='content' >\n" +
                "                    <span  id='neirong'></span>" + data.forumContent +
                "                <div style='margin-top: 80px ;margin-bottom: 40px'>\n" +
                "                    <input style='width: 85%; margin-left: 20px; float: left' type='text' class='form-control' id='reply_text' placeholder='快速回复'/>\n" +
                "                    <button style='margin-left: 20px' type='button' id='swiftReply' class='btn btn-info'>回复</button>\n" +
                "                </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div> <div style='clear:both'></div>")
        }
        function loadComment(data) {
            $("#list").html("");
            $("#count").val(data.totalCount);
            for(var i=0;i<data.list.length;i++){
                var index=(data.currPage-1)*10+i+2;
                var loubiao="";
                switch (index) {
                    case 2:
                        loubiao="沙发";
                        break;
                    case 3:
                        loubiao="板凳";
                        break;
                    case 4:
                        loubiao="地板";
                        break;
                    default:
                        loubiao=index+"楼";
                }

                console.log(index);
                loadOneComment(data.list[i],loubiao);

            }
        }
        function loadOneComment(comment,loubiao){

            $("#list").append("<div class='list_item'>\n" +
                "            <div class='col-md-2 left'>\n" +
                "                <img class='forumphoto' src='../upload/"+comment.customer.photo+"'/>\n" +
                "                <p class='nickname'>"+comment.customer.nickName+"</p>\n" +
                "            </div>\n" +
                "            <div class='col-md-10 right'>\n" +
                "                <div><p class='time'><span>发表于: <span>"+dealTimeJson(comment.time)+"</span></span>      <span class='loubiao'>"+loubiao+"</span></p></div>\n" +
                "                <div class='content'>" +showParent(comment.parent,comment.parentId)+
                comment.content+"</div>\n" +
                "                <div class='replyandts'><a class='reply' name='"+comment.id+"'></a></div>\n" +
                "            </div>\n" +
                "        </div>");
        }

        function creatComment(parent_id, str) {
            $.post("../comment_creatComment.action", {
                content: str,
                forumId: $("#forum_id").val(),
                parentId: parent_id
            }, function (data) {
                if(data.toString()==6666){
                    window.location.href="../main_login.action";
                    return;
                }
                if (data.code != 200) {
                    Toast(data.msg, 2000)
                } else {
                    //清空快速回复的内容
                    //TODO: 跳转到用户之前浏览的页面
                    var index=$("#count").val()*1+2;
                    var loubiao="";
                    switch (index) {
                        case 2:
                            loubiao="沙发";
                            break;
                        case 3:
                            loubiao="板凳";
                            break;
                        case 4:
                            loubiao="地板";
                            break;
                        default:
                            loubiao=index+"楼";
                    }
                    $("#count").val(index-1);
                    loadOneComment(data.target,loubiao);
                }
            }, "json");

        }


    </script>

</head>
<body>
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
        <p class="cartNum" style="width:20px;"><a href="#"> <span class="badge" style="background-color: #888">0</span></a></p>
        <div id="loginOrNot" class="photoAndName">
            <a href="../main_login.action">登录</a>&nbsp;&nbsp;<a style="display: inline" href="../main_register.action">注册</a>
        </div>
    </div>
</div>
<input value="${id}" type="hidden" id="forum_id">
<input value="0" type="hidden" id="select_comment_id">
<input value="0" type="hidden" id="count">
<div class="container" style="margin-top: 80px">
    <div id="firstLeve">


    </div>
    <div id="list">
    </div>
    <%--<div class="quote">--%>
        <%--<blockquote><font size="2"><a--%>
                <%--href="http://bbs.smartisan.com/forum.php?mod=redirect&amp;goto=findpost&amp;pid=6861896&amp;ptid=1023667"--%>
                <%--target="_blank"><font color="#999999">在你爱的牵挂里 发表于 2018-10-12 15:46<br>--%>
        <%--</font></a></font>Pro2s相机卡顿严重--%>
        <%--</blockquote>--%>
    <%--</div>--%>

    <div style="align-content:center;width: 100%;text-align: center">
        <ul class="pagination" style="margin-bottom: 5px">
        </ul>


    </div>
    <div class=" reachTextReply">

        <textarea id="editor" style="width: 100%;margin-left: 6px;height: 300px"></textarea>
        <button type="button" id="reachRelpay"  style="margin: 20px;margin-bottom: 20px;margin-left: 950px " class="btn btn-info">提交回复</button>

    </div>




    <!-- 模态框（Modal） -->
    <div class="modal fade" id="replyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title" id="myModalLabel">  </h4>
                </div>
                <div class="modal-body">
                    <textarea  style="width: 100% ;height: 350px"  id="modalEditor"></textarea>

                </div>
                <div class="modal-footer">
                    <button type="button" id="close" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" id="submitReplyToCustomer" class="btn btn-primary">提交更改</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->
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