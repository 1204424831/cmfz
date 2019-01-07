<%@page pageEncoding="UTF-8" %>
<script type="text/javascript">
    $(function () {
        var toolbar1 = [{
            iconCls: 'icon-add',
            text: "文章添加",
            handler: function () {
                $("#addArticleDialog").dialog("open");
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
        $('#showArticle').edatagrid({
            method: "GET",
            toolbar: toolbar1,
            url: '${pageContext.request.contextPath}/article/querySome',
            columns: [[
                {field: 'title', title: '文章名', width: 100},
                {field: 'content', title: '简介', width: 100},
                {field: 'pubDate', title: '添加时间', width: 100, align: 'right'},
                {field: 'g', formatter: showAuthor, title: '作者', width: 100, align: 'right'}
            ]],
            fitColumns: true,
            fit: true,
            pagination: true,
            pageList: [2, 4, 6, 8, 10],
            pageSize: 4,
            view: detailview,
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}' + rowData.insertImg + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>文章名: ' + rowData.title + '</p>' +
                    '<p>日期: ' + rowData.pubDate + '</p>' +
                    '<p>路径: ' + rowData.insertImg + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }

        });
        $("#addArticleDialog").dialog({
            iconCls: "icon-man",
            title: "添加",
            width: 400,
            height: 200,
            closed: true,
            href: "${pageContext.request.contextPath}/view/addArticle.jsp",
            model: true,
            toolbar: "#addArticleDialogTb"
        });
        $("#addArticleDialogSaveBtn").linkbutton({
            iconCls: "icon-reload",
            onClick: function () {
                $("#addArticleDialog").dialog("close");
                $("#addArticleForm").form("submit", {
                    url: "${pageContext.request.contextPath}/article/insertOne",
                    onSubmit: function () {
                        return $("#addArticleForm").form("validate");
                    },
                    success: function () {
                        $('#showArticle').edatagrid("load");
                        $("#addArticleForm").form("reset");
                        $.messager.show({
                            title: "标题",
                            msg: "添加成功！"
                        });
                    }
                });
            }
        });

    })

    function showAuthor(value, row, index) {
        return value.dharma
    }
</script>

<table id="showArticle"></table>
<div id="addArticleDialog">对话框</div>
<div id="addArticleDialogTb">
    <a id="addArticleDialogSaveBtn">点击添加</a>
</div>
