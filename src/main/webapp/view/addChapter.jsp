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
    $("#addChaptertitle").textbox({
        required: true
    });
    $("#addChapterDuration").textbox({
        required: true
    });
    $("#addChapterAlbumId").val(aid);
    $("#addChapteruploadDate").datebox({
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

<h1>章节添加</h1>


<form id="addChapterForm" enctype="multipart/form-data" method="post">
    主题：<input id="addChaptertitle" name="title"/><br/>
    文件上传:<input type="file" name="file1"/><br/>
    时长：<input id="addChapterDuration" name="duration"/><br/>
    发布时间：<input id="addChapteruploadDate" name="uploadDate"/><br/>
    所属专辑Id: <input id="addChapterAlbumId" name="albumId"/><br/>
</form>
