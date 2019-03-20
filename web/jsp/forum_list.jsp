<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script type="text/javascript" src="../static/jquery/jquery-3.0.0.js"></script>
    <link rel="stylesheet" type="text/css" href="../static/bootstrap/css/bootstrap.css">
    <script type="text/javascript" src="../static/bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/static/local/css/homepage.css">
    <link rel="stylesheet" href="/static/local/css/animate.min.css">
    <style>
        .Mysearch{
            position: relative;
            horiz-align: center;
            z-index: 0;
            margin-top: 5px;
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
        .listphoto{
            width: 64px;
            height: 64px;
            border: 2px solid #c1c1c1;
            float: left;
            margin-top: 5px;
            margin-left: 13px;
        }
        .Mysearch .title{
            margin-left: 100px;
            margin-top: 10px;
            font-size: 20px;

        }
        .author_time{
            margin-left: 26px;
            float: left;

        }
        .view_comment{
            float: right;

            margin-right: 20px;
            margin-top: 0px;
        }

        .conment{
            width:13px;
            height:13px;
        }
        .view{
            width:16px;
            height:16px;
        }


    </style>

    <%--list 页面使用的JS--%>
    <script>



        $(function(){
            //为左侧版块信息绑定内容
            $.post("../block_findById.action",{id:$("#blockId").val()},function (data) {
                $("#todayNum").html(data.todayActive);
                $("#commentNum").html(data.blockAllComment);
                $("#forumNum").html(data.blockAllForum);
                $("#blockImg").attr("src","../static/local/img/"+data.blockCover)
                $("#blockTitle").html(data.blockName);

            },"json");

            $(".pagination").delegate(".pageBtn","click",function (event) {
                var name= $(this).attr("name");
                var reg=/^\d+$/;
                if(reg.test(name)){
                    ajaxData(name);
                }else if(name=="pre"){
                    ajaxData(current-1);
                }else if(name="next"){
                    ajaxData(current+1);
                }
            });

            function ajaxData( page){
                $.post("../forum_getListByBlock.action",{
                    blockId:$("#blockId").val(),
                    page:page
                },function (data) {
                    $(".pagination").html("");
                    loadData(data);
                    var num=data.totalCount;
                    var current=data.currPage;
                    var pages=data.totalPage;
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

                    }

                },"json");
            }
            function loadData(data) {
                $("#forum_list").html("");
                if(data.list<=0){
                    $("#forum_list").html("<h4>还没有主题帖,快去创建一个吧 <a href='../forum_creatForum.action' style='text-decoration:underline ;color: blue;' >去发帖</a></h4>")

                    return;

                }
                for(var i=0;i<data.list.length;i++){

                    $("#forum_list").append(" <div name='"+data.list[i].id+"' class='Mysearch'>\n" +
                        "        <img src='../upload/"+data.list[i].customer.photo+"' class='img-circle listphoto' >\n" +
                        "        <p class='title'>"+data.list[i].forumTitle+"</p>\n" +
                        "       <div>\n" +
                        "           <p class='author_time'><span style='color:#6e6e6e;'>"+data.list[i].customer.nickName+"</span>  <span style='color:#b7b7b7;'> &nbsp;|&nbsp; 发表于:<span >"+dealTimeJson(data.list[i].forumGmtTime)+"</span></span></p>\n" +
                        "           <div class='view_comment'>\n" +
                        "               <img class='view'  src='../static/local/img/view_count.png'/>&nbsp;&nbsp;<span style='color: #b7b7b7;' >"+data.list[i].forumPageView+"</span>&nbsp;&nbsp;\n" +
                        "               <img class='conment'  src='../static/local/img/comment_count.png'/>&nbsp;&nbsp;<span style='color: #b7b7b7' >"+data.list[i].forumCommentCount+"</span>\n" +
                        "           </div>\n" +
                        "       </div>\n" +
                        "\n" +
                        "\n" +
                        "    </div>");
                }


            }

            function  dealTimeJson(jsonStr){
                var year=1900+jsonStr.year;
                var month=jsonStr.month+1;
                var day=jsonStr.date;
                var hour=jsonStr.hours;
                var minutes=jsonStr.minutes;
                return  year+"-"+month+"-"+day+" "+hour+":"+minutes;

            }
            //为列表绑定点击事件
            $("#forum_list").delegate(".Mysearch","click",function () {
               var forum_id= $(this).attr("name");
               window.open("../forum_showDetail.action?id="+forum_id,"_blank");
            });
            ajaxData(1);
        });



    </script>
    <style>
        .currentBlock {
            height: 40px;
            position: relative;
            z-index: 0;
            margin-bottom: 10px;
            margin-top: 10px;
            box-sizing: border-box;
            border: 1px solid rgba(0,0,0,.14);
            border-radius: 8px;
            background: #fff;
            box-shadow: 0 3px 8px -6px rgba(0,0,0,.1);
            padding-left: 10px;
            padding-top: 12px;
            padding-bottom: 5px;
        }
        .boxtitle{
            height: 40px;
            position: relative;
            z-index: 0;
            margin-bottom: 10px;
            margin-top: 10px;
            box-sizing: border-box;
            border: 1px solid rgba(0,0,0,.14);
            border-radius: 8px;
            background: #fff;
            box-shadow: 0 3px 8px -6px rgba(0,0,0,.1);
            padding-left: 10px;
            padding-top: 12px;
            padding-bottom: 5px;

        }
        .thisBlock{
            height: 205px;
            position: relative;
            z-index: 0;
            margin-left: -20px;
            margin-bottom: 10px;
            box-sizing: border-box;
            border: 1px solid rgba(0,0,0,.14);
            border-radius: 8px;
            background: #fff;
            box-shadow: 0 3px 8px -6px rgba(0,0,0,.1);
            padding-left: 10px;
            padding-top: 12px;
            padding-bottom: 5px;
            margin-top: 5px;
            text-align: center;
        }
        .thisBlock img{
            margin-top: 3px;
            margin-left: -20px;
        }
        .thisBlock span{
            display:block;
            margin-left: -20px;
            margin-top: 10px;
            font-size: 18px;
            font-weight: bolder;
            height: 25px;
        }

        .thisBlock .bottombar{
            box-sizing: border-box;
            border: 1px solid rgba(0,0,0,.14);
            background: #fff;

            margin-left: -10px;
            border-bottom-left-radius: 8px;
            border-bottom-right-radius: 8px;
            border-left: none;
            border-right: none;
            border-bottom: none;
            box-shadow: 0 3px 8px -6px rgba(0,0,0,.1);
            height: 59px;
            margin-top: 22px;
            padding-bottom: 1px;
        }
       .thisBlock .littleBlock{
           float: left;
           border: 1px solid rgba(0,0,0,.14);
           height: 100%;
           width:85px;
           margin-bottom: 2px;
           border-bottom: none;
           text-align: center;
           border-left: none;
           border-top: none;
        }

        .thisBlock .littleBlock.left{
              border-left: none;
            margin-left: 8px;
        ;
          }
        .thisBlock .littleBlock.right{
            border-right: none;
            margin-right: 8px;
        }
        .thisBlock .littleBlock .msg{
            margin-top: 10px;
            margin-bottom: 3px;
            color: rgba(0, 0, 0, 0.48);
        }
        .thisBlock .littleBlock .text{
            margin-top: -2px;
            color: #555555;
        }
        .regular{
            height: 730px;
            position: relative;
            z-index: 0;
            margin-top: -5px;
            margin-left: -20px;
            margin-bottom: 10px;
            box-sizing: border-box;
            border: 1px solid rgba(0,0,0,.14);
            border-radius: 8px;
            background: #fff;
            box-shadow: 0 3px 8px -6px rgba(0,0,0,.1);
            padding-left: 10px;
            padding-top: 12px;
            padding-bottom: 5px;
            font-size: 13px;
        }
        .regular .titleBar{
            height: 35px;
            text-size:15px;
            margin-left: -10px;
            margin-top: 8px;
            padding-bottom: 10px;

            border-bottom:1px solid rgba(0,0,0,.14);
        }

        .toCreateForum{
            padding: 8px;
            background-color: rgba(255, 97, 0, 0.38);
            border-radius: 5px;
            color: #fff;
            padding-left: 20px;
            padding-right:20px;
            font-weight: bolder;
            box-shadow: 0 3px 8px -6px rgba(255, 83, 0, 0.3);
            border: 1px solid rgba(255, 97, 0, 0.3);
        }
        .toCreateForum:hover{
            padding: 8px;
            background-color: rgba(255, 97, 0, 0.82);
            border-radius: 5px;
            color: #fff;
            padding-left: 20px;
            padding-right:20px;
            font-weight: bolder;
            text-decoration: none;
            box-shadow: 0 3px 8px -6px rgba(255, 83, 0, 0.3);
            border: 1px solid rgba(255, 97, 0, 0.3);
        }

    </style>


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

</head>
<body style="background-color: #ededed;">
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
            <a href="../main_login.action">登录</a>&nbsp;&nbsp;<a style="display: inline" href="../main_register.action">注册</a>
        </div>
    </div>

</div>
<div class="container" style="margin-top: 80px;">

    <input type="hidden" value="${blockId}" name="blockId" id="blockId"/>
    <div class="col-md-9" style="float: left">
        <div id="forum_list">
        </div>


    </div>
    <div class="col-md-3" style="float: left">
        <div class="thisBlock">
            <img id="blockImg" style="width: 72px;height: 72px" src="../static/local/img/icon_msg.png">
            <span id="blockTitle"></span>

            <div class="bottombar">
                <div class="littleBlock left">
                    <p class="msg">今日</p>
                    <p id="todayNum" class="text">0</p>

                </div>
                <div class="littleBlock ">
                    <p class="msg">主题</p>
                    <p id="forumNum" class="text">0</p>
                </div>

                <div class="littleBlock right">
                    <p class="msg">贴数</p>
                    <p id="commentNum" class="text">0</p>
                </div>
            </div>
        </div>
        <div class="regular">
            <div class="titleBar">
                <div style="margin-bottom: 10px;padding-bottom: 10px">
                <div  style="float: left;margin-bottom: 10px"><span style="font-size: 16px;margin-left: 10px;line-height: 20px"> 发帖须知</span></div>
                <div style="float: right;margin-left: 10px ;margin-right: 20px;"><a href="../forum_creatForum.action" class="toCreateForum" >去发帖</a><br></div>
                </div>
                    <p  style="margin-top: 24px;margin-bottom: 20px ;clear:both;padding-right: 8px;padding-left: 8px;text-align:left">
                        <br>
                        1、会员在社区内的言论(包括但不限于文字、图片、音频、视频，下同)不得违反国家的法律法规。根据《互联网新闻信息服务管理规定》，会员的言论不得含有下列内容：
                    <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(一)违反宪法确定的基本原则的；
                    <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(二)危害国家安全，泄露国家秘密，颠覆国家政权，破坏国家统一的；
                    <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(三)损害国家荣誉和利益的；
                    <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(四)煽动民族仇恨、民族歧视，破坏民族团结的；
                    <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(五)破坏国家宗教政策，宣扬邪教和封建迷信的；
                    <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(六)散布谣言，扰乱社会秩序，破坏社会稳定的；
                    <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(七)散布淫秽、色情、赌博、暴力、恐怖或者教唆犯罪的；
                    <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(八)侮辱或者诽谤他人，侵害他人合法权益的；
                    <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(九)煽动非法集会、结社、游行、示威、聚众扰乱社会秩序的；
                    <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(十)以非法民间组织名义活动的；
                    <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(十一)含有法律、行政法规禁止的其他内容的。
                    <br>2、会员应尊重他人的知识产权，不得剽窃他人作品，转载和引用他人作品时应符合版权许可。
                    <br>3、会员应遵守社区秩序和网络道德，不得污言秽语，不得进行刷屏、恶意顶贴、恶意灌水等影响他人阅读的行为。
                    <br>4、会员应尊重他人隐私，除非涉及公众利益或者经当事人同意，不得发表他人的姓名、住址、电话等个人资料以及其他隐私信息。
                    <br>5、会员对自己的言论承担责任。 会员在公共论坛的言论一经发表，就无法由本人修改和删除。</p>
            </div>

        </div>

    </div>

    <div style="width:100% ;align-content: center;text-align: center ;clear: both">
        <ul class="pagination">

        </ul>
    </div>

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