<%@ page pageEncoding="utf-8" %>

<title>index.html</title>
<style type="text/css">
    body {
        text-align: center;
    }

    select {
        width: 173px;
    }
</style>
<script type="text/javascript">
    $("#addAlbumtitle").textbox({
        required: true
    });
    $("#addAlbumcount").textbox({
        required: true
    });
    $("#addAlbumscore").textbox({
        required: true
    });
    $("#addAlbumauthor").textbox({
        required: true
    });
    $("#addAlbumbroadcast").textbox({
        required: true
    });
    $("#addAlbumputDate").datebox({
        required: true,
        editable: false,
        formatter: function (date) {
            var y = date.getFullYear();
            var m = date.getMonth() + 1;
            var d = date.getDate();
            return y + "-" + m + "-" + d;
        },
        parser: function (s) {
            var t = Date.parse(s);
            if (!isNaN(t)) {
                return new Date(t);
            } else {
                return new Date();
            }
        }
    });
</script>

<h1>专辑添加</h1>


<form id="addAlbumForm" enctype="multipart/form-data" method="post">
    主题：<input id="addAlbumtitle" name="title"/><br/>
    章节数：<input id="addAlbumcount" name="count"/><br/>
    图片上传:<input type="file" name="file1"/><br/>
    好评：<input id="addAlbumscore" name="score"/><br/>
    作者：<input id="addAlbumauthor" name="author"/><br/>
    播音员：<input id="addAlbumbroadcast" name="broadcast"/><br/>
    发布时间：<input id="addAlbumputDate" name="putDate"/><br/>
    简介：<textarea id="addbrief" cols="25" name="brief" rows="3"></textarea>
</form>
