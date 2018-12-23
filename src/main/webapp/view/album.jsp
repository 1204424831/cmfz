<%@page pageEncoding="UTF-8" %>
<script type="text/javascript">
    var aid;
    var toolbar = [{
        iconCls: 'icon-add',
        text: "专辑详情",
        handler: function () {
            var row = $('#album').treegrid("getSelected");
            if (row != null && row.author != null) {
                aid = row.id;
                $("#showAlbumDialog").dialog("open");
            } else {
                $.messager.show({
                    title: "标题",
                    msg: "您还未选中！"
                });
            }
        }
    }, '-', {
        text: "添加专辑",
        iconCls: 'icon-edit',
        handler: function () {
            //获取选中行
            $("#addAlbumDialog").dialog("open");

        }
    }, '-', {
        text: "添加音频",
        iconCls: 'icon-remove',
        handler: function () {
            var row = $('#album').treegrid("getSelected");
            if (row != null && row.author != null) {
                aid = row.id;
                $("#addChapterDialog").dialog("open");
            } else {
                $.messager.show({
                    title: "标题",
                    msg: "请选中对应专辑！"
                });
            }
        }
    }, '-', {
        text: "音频下载",
        iconCls: 'icon-save',
        handler: function () {
            var row = $('#album').treegrid("getSelected");
            if (row != null && row.author == null) {
                location.href = "${pageContext.request.contextPath}/chapter/download?id=" + row.id;
            } else {
                $.messager.show({
                    title: "标题",
                    msg: "请选中对应音频！"
                });
            }

        }
    }]

    function listen(value, row, index) {

        if (row.children == null) {
            var path = "${pageContext.request.contextPath}" + row.url;
            console.log(path);
            return "<audio height='20px' src='${pageContext.request.contextPath}" + row.url + "' controls=\"controls\"/>"
        }

    }

    $(function () {
        $('#album').treegrid({
            method: "get",
            url: '${pageContext.request.contextPath}/album/querySome',
            idField: 'id',
            treeField: 'title',
            pagination: true,
            pageList: [1, 3, 5, 7, 9],
            pageSize: 3,
            columns: [[
                {field: 'title', title: '名字', width: 60},
                {field: 'duration', title: '时长', width: 80},
                {field: 'size', title: '大小', width: 80},
                {field: 'url', formatter: listen, title: '音频播放', width: 80}
            ]],
            fit: true,
            fitColumns: true,
            toolbar: toolbar,
        });
        $("#showAlbumDialog").dialog({
            iconCls: "icon-man",
            title: "展示专辑",
            width: 400,
            height: 500,
            closed: true,
            cache: false,
            href: "${pageContext.request.contextPath}/view/showAlbum.jsp",
            model: true,
            toolbar: "#showAlbumDialogTb"
        });
        $("#showAlbumDialogSaveBtn").linkbutton({
            iconCls: "icon-reload",
            onClick: function () {
                $("#showAlbum").form("reset");
                $("#showAlbumDialog").dialog("close");
            }
        });
        $("#addAlbumDialog").dialog({
            iconCls: "icon-man",
            title: "添加",
            width: 400,
            height: 200,
            closed: true,
            href: "${pageContext.request.contextPath}/view/addAlbum.jsp",
            model: true,
            toolbar: "#addAlbumDialogTb"
        });
        $("#addAlbumDialogSaveBtn").linkbutton({
            iconCls: "icon-reload",
            onClick: function () {
                $("#addAlbumDialog").dialog("close");
                $("#addAlbumForm").form("submit", {
                    url: "${pageContext.request.contextPath}/album/insertOne",
                    onSubmit: function () {
                        return $("#addAlbumForm").form("validate");
                    },
                    success: function () {
                        $('#album').treegrid("load");
                        $("#addAlbumForm").form("reset");
                        $.messager.show({
                            title: "标题",
                            msg: "添加成功！"
                        });
                    }
                });
            }
        });
        $("#addChapterDialog").dialog({
            iconCls: "icon-man",
            title: "添加",
            width: 400,
            height: 200,
            closed: true,
            href: "${pageContext.request.contextPath}/view/addChapter.jsp",
            model: true,
            toolbar: "#addChapterDialogTb"
        });
        $("#addChapterDialogSaveBtn").linkbutton({
            iconCls: "icon-reload",
            onClick: function () {
                $("#addChapterDialog").dialog("close");
                $("#addChapterForm").form("submit", {
                    url: "${pageContext.request.contextPath}/chapter/insertOne",
                    onSubmit: function () {
                        return $("#addChapterForm").form("validate");
                    },
                    success: function () {
                        $('#album').treegrid("reload");
                        $("#addChapterForm").form("reset");
                        $.messager.show({
                            title: "标题",
                            msg: "添加成功！"
                        });
                    }
                });
            }
        });
    });

</script>

<table id="album"></table>
<div id="showAlbumDialog">对话框</div>
<div id="showAlbumDialogTb">
    <a id="showAlbumDialogSaveBtn">返回页面</a>
</div>

<div id="addAlbumDialog">对话框</div>
<div id="addAlbumDialogTb">
    <a id="addAlbumDialogSaveBtn">点击添加</a>
</div>

<div id="addChapterDialog">对话框</div>
<div id="addChapterDialogTb">
    <a id="addChapterDialogSaveBtn">点击添加</a>
</div>