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
    $(function () {

        $("#addArticletitle").textbox({
            required: true
        });

        $("#addpubDate").datebox({
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
        $("#addAuthor").combobox({
            editable: true,
            textField: "dharma",
            valueField: "id",
            url: "${pageContext.request.contextPath}/gurn/queryAll",
            onLoadSuccess: function (data) {
                $("#addAuthor").combobox("setValue", data[0].id);
            }
        });
    });

</script>
<h1>文章添加</h1>


<form id="addArticleForm" enctype="multipart/form-data" method="post">
    文章名称:<input id="addArticletitle" name="title"/><br/>
    图片上传:<input type="file" name="file"/><br/>
    完本日期:<input id="addpubDate" name="pubDate"/><br/>
    简介：<textarea id="addcontent" cols="25" name="content" rows="3"></textarea><br/>
    作者:<input id="addAuthor" name="g.id"><br/>
</form>
