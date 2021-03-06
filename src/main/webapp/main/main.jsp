﻿<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>持名法州主页</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/IconExtension.css">
    <link rel="stylesheet" id="s_superplus_css_lnk" type="text/css"
          href="${pageContext.request.contextPath}/index_files/super_min_0cb4b166.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/index_files/card_min_e8bcf60d.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/index_files/ubase_83c8f0ba.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/index_files/mt_min_d0e7c6d2.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/index_files/nsguide_a8cbc2e7.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/index_files/super_ext_c02dfc40.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/index_files/jquery.js"></script>
    <script type="text/javascript" src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/datagrid-detailview.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.edatagrid.js"></script>
    <script src="../js/echarts.min.js"></script>
    <script src="../js/china.js"></script>
    <script type="text/javascript" src="../js/easyui-lang-zh_CN.js"></script>

    <script type="text/javascript">
        <!--菜单处理-->

        $(function () {

            $.get(
                "${pageContext.request.contextPath}/menu/queryAll",
                function (result) {
                    $.each(result, function (index, first) {
                        var a = "";
                        $.each(first.me, function (idex, into) {
                            console.log(into);
                            a += "<p style='text-align: center'><a id='btn' href='#' class='easyui-linkbutton' onclick=\"addTabs('" + into.title + "','" + into.iconcls + "','" + into.url + "')\" data-options=\"inconCls:'icon-search'\">" + into.title + "</a></p>"
                        });
                        $("#aa").accordion("add", {
                            iconCls: first.iconcls,
                            title: first.title,
                            content: a,
                            selected: false
                        });

                    });

                }
                , "json");

        });

        function addTabs(title, iconcls, url) {
            if ($("#tt").tabs("exists", title)) {
                //存在就加载

                $("#tt").tabs("select", title);
            } else {
                //不存在就创建
                $("#tt").tabs("add", {
                    title: title,
                    iconCls: iconcls,
                    closable: true,
                    href: "${pageContext.request.contextPath }" + url
                });
            }

        }

    </script>

</head>
<body class="easyui-layout">
<div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    <div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px">
        持名法州后台管理系统
    </div>
    <div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px">
        欢迎您:${sessionScope.name}
        &nbsp;<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;<a href="#"
                                                                                                              class="easyui-linkbutton"
                                                                                                              data-options="iconCls:'icon-01'">退出系统</a>
    </div>
</div>
<div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    <div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体">&copy;百知教育 htf@zparkhr.com.cn</div>
</div>

<div data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">
    <div id="aa" class="easyui-accordion" data-options="fit:true">

    </div>
</div>
<div data-options="region:'center'">
    <div id="tt" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">
        <div title="主页" data-options="iconCls:'icon-neighbourhood',"
             style="background-image:url(image/shouye.jpg);background-repeat: no-repeat;background-size:100% 100%;"></div>
    </div>
</div>
</body>
</html>