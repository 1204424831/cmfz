<%@page pageEncoding="UTF-8" %>
<script type="text/javascript">
    $(function () {

        $('#dg').edatagrid({
            method: "GET",
            toolbar: "#ShowAllBannerDialog",
            updateUrl: "${pageContext.request.contextPath}/banner/updateOne",
            url: '${pageContext.request.contextPath}/banner/querySome',
            columns: [[
                {field: 'title', title: '名称', width: 100},
                {
                    field: 'status', title: '状态', width: 100, editor: {
                        type: "text",
                        options: {required: true}
                    }
                },
                {field: 'putDate', title: '时间', width: 100, align: 'right'},
                {field: 'imgPath', title: '路径', width: 100, align: 'right'}
            ]],
            fitColumns: true,
            fit: true,
            pagination: true,
            pageList: [1, 3, 5, 7, 9],
            pageSize: 3,
            view: detailview,
            detailFormatter: function (rowIndex, rowData) {
                console.log(rowData.imgPath)
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}' + rowData.imgPath + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>描述: ' + rowData.description + '</p>' +
                    '<p>日期: ' + rowData.putDate + '</p>' +
                    '<p>主题: ' + rowData.title + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }

        });
        $("#addBan").linkbutton({
            iconCls: 'icon-add',
            onClick: function () {
                $("#addBanDialog").dialog("open");
            }
        });
        $("#updateBan").linkbutton({
            iconCls: 'icon-edit',
            onClick: function () {
                var row = $("#dg").edatagrid("getSelected");
                if (row != null) {
                    var index = $("#dg").edatagrid("getRowIndex", row);
                    $("#dg").edatagrid("editRow", index);
                } else {
                    $.messager.show({
                        title: "标题",
                        msg: "您还未选中行！"
                    });
                }
            }
        });
        $("#deleteBan").linkbutton({
            iconCls: 'icon-remove',
            onClick: function () {
                var row = $("#dg").edatagrid("getSelected");
                $.get("${pageContext.request.contextPath}/banner/deleteOne", "id=" + row.id, function (result) {
                    if (result == "ok") {
                        $("#dg").edatagrid("load");
                        $.messager.show({
                            title: "标题",
                            msg: "删除成功！"
                        });
                    }
                });
            }
        });
        $("#saveBan").linkbutton({
            iconCls: 'icon-save',
            onClick: function () {
                $("#dg").edatagrid("saveRow");
                $.messager.show({
                    title: "标题",
                    msg: "修改成功！"
                });
            }
        });
        $("#addBanDialog").dialog({
            iconCls: "icon-man",
            title: "添加",
            width: 400,
            height: 200,
            closed: true,
            href: "${pageContext.request.contextPath}/view/addBanner.jsp",
            model: true,
            toolbar: "#addBanDialogTb"
        });
        $("#addBanDialogSaveBtn").linkbutton({
            iconCls: "icon-reload",
            onClick: function () {
                $("#addBanDialog").dialog("close");
                $("#addBanForm").form("submit", {
                    url: "${pageContext.request.contextPath}/banner/insertOne",
                    onSubmit: function () {
                        return $("#addBanForm").form("validate");
                    },
                    success: function () {
                        $("#dg").edatagrid("load");
                        $("#addBanForm").form("reset");
                        $.messager.show({
                            title: "标题",
                            msg: "添加成功！"
                        });
                    }
                });
            }
        });
    })

</script>

<table id="dg"></table>
<!--表格工具栏的 添加按钮 -->
<div id="ShowAllBannerDialog">
    <!-- 添加 添加按钮 -->
    <a id="addBan">添加</a>
    <a id="updateBan">修改</a>
    <a id="deleteBan">删除</a>
    <a id="saveBan">保存</a>
</div>

<!-- 添加用户对话框 -->
<div id="addBanDialog">对话框</div>
<!-- 添加用户对话框-工具栏 -->
<div id="addBanDialogTb">
    <a id="addBanDialogSaveBtn">点击添加</a>
</div>