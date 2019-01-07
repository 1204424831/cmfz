<%@page pageEncoding="UTF-8" %>
<html>
<head>

    <script type="text/javascript" src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
</head>
<body>
<script type="text/javascript">
    var goEasy = new GoEasy({
        appkey: "BC-ee3c1f1bd50d4fc58d6b27a4770bd432"
    });
    goEasy.subscribe({
        channel: "140",
        onMessage: function (message) {
            alert("Channel:" + message.channel + " content:" + message.content);
            console.log(message.content);
            $("#qqq").html(message.content);
        }
    });
</script>
<span id="qqq">
</span>
</body>
</html>
