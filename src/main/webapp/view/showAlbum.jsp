<%@ page pageEncoding="utf-8" %>


<script type="text/javascript">

    $(function () {
        $("#showAlbum").form("load", "${pageContext.request.contextPath}/album/queryOne?id=" + aid);
        $("#showAlbum").form({
            onLoadSuccess: function (data) {
                $("#img").prop("src", "${pageContext.request.contextPath}" + data.coverImg);
                $("#showbrief").html(data.brief);
            }
        });
    });
</script>
<h3>专辑展示</h3>
<form id="showAlbum" method="post">
    ID: <input id="showAlbumid" disabled="true" name="id"/><br/>
    主题：<input id="showAlbumtitle" disabled="true" name="title"/><br/>
    章节数：<input id="showAlbumcount" name="count" disabled="true"/><br/>
    图片:<img id="img" src=""/><br/>
    好评：<input id="showAlbumscore" disabled="true" name="score"/><br/>
    作者：<input id="showAlbumauthor" name="author" disabled="true"/><br/>
    播音员：<input id="showAlbumbroadcast" name="broadcast" disabled="true"/><br/>
    发布时间：<input id="showAlbumputDate" name="putDate" disabled="true"/><br/>
    简介：<textarea id="showbrief" cols="25" disabled="true" rows="3"></textarea>
</form>
