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
    $("#addBanTitle").textbox({
        required: true
    });
    $("#addBanSta").textbox({
        required: true
    });
    $("#addDescription").textbox({
        required: true
    });
    $("#addPutDate").datebox({
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

<h1>轮播图添加</h1>


<form id="addBanForm" enctype="multipart/form-data" method="post">
    轮播图主题:<input id="addBanTitle" name="title"/><br/>
    图片上传:<input type="file" name="file1"/><br/>
    轮播图是否展示（y or n）:<input id="addBanSta" name="status"/><br/>
    上传时间：<input id="addPutDate" name="putDate"/><br/>
    轮播图描述:<input id="addDescription" name="description"/>
</form>



