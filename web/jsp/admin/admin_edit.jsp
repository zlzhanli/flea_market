<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: SHASK
  Date: 2019/3/13
  Time: 8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品维护</title>
    <link rel="stylesheet" href="${css}/pintuer.css">
    <link rel="stylesheet" href="${css}/admin.css">
    <link rel="stylesheet" type="text/css" href="${root}/static/local/kindeditor/themes/default/default.css">
    <link rel="stylesheet" type="text/css" href="${root}/static/local/kindeditor/plugins/code/prettify.css">
    <script type="text/javascript" src="${root}/static/local/kindeditor/kindeditor-all-min.js"></script>
    <script type="text/javascript" src="${root}/static/local/kindeditor/lang/zh-CN.js"></script>
    <script type="text/javascript" src="${root}/static/local/kindeditor/plugins/code/prettify.js"></script>
    <script src="${jquery}/jquery-3.0.0.js"></script>
    <script src="${js}/pintuer.js"></script>
    <script>
        KindEditor.ready(function(K) {
            var editor = K.create('#editor', {
                cssPath : '../../static/local/kindeditor/css/prettify.css',
                uploadJson : '../../static/local/kindeditor/upload_json.jsp',
                fileManagerJson : '../../static/local/kindeditor/file_manager_json.jsp',
                afterBlur: function () { this.sync(); }
            });

        });
    </script>
    <script>
        $(function () {
            initType();
            initStatus();
            initShipping();

            if ("${goods.goodsSource}" == 1) {
                $("#goodsSource").attr("placeholder","平台自营");
            } else {
                $("#goodsSource").attr("placeholder","用户发布");
            }

            // 加载商品类型
            function initType() {
                $.ajax({
                    url: "${root}/goodsType_list.action",
                    type: "POST",
                    dataType: "json",
                    cache: false,
                    success: function (data) {
                        for (var d in data) {
                            $("#type").append(createType(data, d));
                        }
                    }
                });
            }

            function createType(data, d) {
                if (data[d]['typeId'] == "${goods.goodsType}") {
                    return "<option selected value='" + data[d]['typeId'] + "'>" + data[d]['typeName'] + "</option>";
                }
                return "<option value='" + data[d]['typeId'] + "'>" + data[d]['typeName'] + "</option>";
            }

            // 加载商品状态
            function initStatus() {
                var url = "${root}/goodsStatus_list.action";
                $.post(url,
                    {},
                    function (data) {
                        // 遍历状态集合，将遍历出来的status添加到id为status的下拉框中。
                        for (var s in data) {
                            // alert(data[s]['statusName']);
                            $("#status").append(createStatus(data,s));
                        }
                    }, 'json')
            }

            function createStatus(data,s) {
                if (data[s]['id'] == "${goods.goodsStatus}") {
                    return "<option selected value='"+data[s]['id']+"'>"+data[s]['statusName']+"</option>"
                }
                // return "<option value='" + data[d]['typeId'] + "'>" + data[d]['typeName'] + "</option>";
                return "<option value='"+data[s]['id']+"'>"+data[s]['statusName']+"</option>"

            }

            // 加载商品配送信息
            function initShipping() {
                $.ajax({
                    url: "shippingMethod_getListMethod.action",
                    type: "post",
                    dataType: "json",
                    cache: false,
                    success: function(data){
                        for (var d in data) {
                            $("#shipping").append(createShipping(data,d));
                        }
                    }
                });
            }

            function createShipping(data,d) {
                if (data[d]['shippingId'] == "${goods.shippingMethod}") {
                    return "<option selected value='"+data[d]['shippingId']+"'>"+data[d]['method']+"</option>"
                }
                return "<option value='"+data[d]['shippingId']+"'>"+data[d]['method']+"</option>"
            }
        })
    </script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>修改内容</strong></div>
    <div class="body-content">
        <form method="post" class="form-x" action="goods_updateGoods.action" >
            <input type="hidden" value="${goods.id}" name="id">

            <div class="form-group">
                <div class="label">
                    <label>商品名称:</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${goods.goodsName}" name="goodsName" placeholder="${goods.goodsName}"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>商品来源:</label>
                </div>
                <div class="field">
                    <input id="goodsSource" type="text" class="input w50" value="" name="goodsSource" readonly/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>商品拥有者:</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="" name="goodsOwener" readonly placeholder="${owner}"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>商品全称:</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${goods.goodsFullName}" name="goodsFullName"
                           placeholder="${goods.goodsFullName}"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>商品封面图片:</label>
                </div>
                <div class="field">
                    <input type="hidden" name="goodsCover" value="${goods.goodsCover}">
                    <img src="${upload}/${goods.goodsCover}">
                </div>
            </div>



            <div class="form-group">
                <div class="label">
                    <label>商品状态</label>
                </div>
                <div class="field">
                    <select id="status" name="goodsStatus" class="input w50">

                    </select>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>商品类别</label>
                </div>
                <div class="field">
                    <select name="goodsType" id="type" class="input w50">

                    </select>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>商品数量:</label>
                </div>
                <div class="field">
                    <input type="number" class="input w50" value="${goods.goodsNum}" name="goodsNum" placeholder="${goods.goodsNum}"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>商品价格:</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${goods.goodsPrice}" name="goodsPrice" placeholder="${goods.goodsPrice}"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>修改原因:</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${goods.goodsModifyReason}" name="goodsModifyReason"
                           placeholder="${goods.goodsModifyReason}"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>商品关键词:</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${goods.goodsKeywords}" name="goodsKeywords"
                           placeholder="${goods.goodsKeywords}"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>配送方式</label>
                </div>
                <div class="field">
                    <select id="shipping" name="goodsStatus" class="input w50">

                    </select>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>商品详情:</label>
                </div>
                <div class="field">
                    <textarea id="editor" class="input" name="goodsDetail" style=" height:200px;"
                              placeholder="${goods.goodsDetail}"></textarea>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>商品浏览量:</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="goodsPageView" value="${goods.goodsPageView}"
                           placeholder="${goods.goodsPageView}"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
