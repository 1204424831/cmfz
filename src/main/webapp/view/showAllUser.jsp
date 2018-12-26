<%@page pageEncoding="UTF-8" %>
<script type="text/javascript">
    $(function () {
        var toolbar1 = [{
            iconCls: 'icon-add',
            text: "状态修改",
            handler: function () {
                var row = $("#showUser").edatagrid("getSelected");
                if (row != null) {
                    var index = $("#showUser").edatagrid("getRowIndex", row);
                    $("#showUser").edatagrid("editRow", index);
                } else {
                    $.messager.show({
                        title: "标题",
                        msg: "您还未选中行！"
                    });
                }
            }
        }, '-', {
            text: "数据导出",
            iconCls: 'icon-save',
            handler: function () {
                $.get("${pageContext.request.contextPath}/user/out", function (result) {
                    $.messager.show({
                        title: "标题",
                        msg: "导出成功！"
                    });
                });

            }
        }, '-', {
            text: "数据导入",
            iconCls: 'icon-save',
            handler: function () {

                $.get("${pageContext.request.contextPath}/user/init", function (result) {
                    $.messager.show({
                        title: "标题",
                        msg: "导入成功！"
                    });
                });
            }
        }, '-', {
            text: "保存",
            iconCls: 'icon-edit',
            handler: function () {
                $("#showUser").edatagrid("saveRow");
                $.messager.show({
                    title: "标题",
                    msg: "修改成功！"
                });
            }
        }]
        $('#showUser').edatagrid({
            method: "GET",
            toolbar: toolbar1,
            updateUrl: "${pageContext.request.contextPath}/user/updateOne",
            url: '${pageContext.request.contextPath}/user/querySome',
            columns: [[
                {field: 'name', title: '用户名', width: 100},
                {field: 'dharma', title: '法名', width: 100},
                {field: 'sex', title: '性别', width: 100},
                {field: 'phone', title: '手机号', width: 100},
                {field: 'province', title: '省份', width: 100},
                {field: 'password', title: '密码', width: 100},
                {field: 'city', title: '城市', width: 100},
                {
                    field: 'status', title: '状态', width: 100, editor: {
                        type: "text",
                        options: {required: true}
                    }
                },
                {field: 'regDate', title: '注册时间', width: 100, align: 'right'},
                {field: 'headPic', title: '路径', width: 100, align: 'right'}
            ]],
            fitColumns: true,
            fit: true,
            pagination: true,
            pageList: [2, 4, 6, 8, 10],
            pageSize: 4,
            view: detailview,
            detailFormatter: function (rowIndex, rowData) {
                console.log(rowData.headPic)
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}' + rowData.headPic + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>签名: ' + rowData.sign + '</p>' +
                    '<p>日期: ' + rowData.regDate + '</p>' +
                    '<p>法号: ' + rowData.dharma + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }

        });

    })

</script>

<table id="showUser"></table>
