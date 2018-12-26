<%@ page pageEncoding="utf-8" %>

<meta charset="utf-8">
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    var option = {
        title: {
            text: '近三周用户注册表',
            subtext: '注册数量表'
        },
        tooltip: {},
        legend: {
            data: ['注册数']
        },
        xAxis: {
            data: ["第一周", "第二周", "第三周"]
        },
        yAxis: {},
    }

    myChart.setOption(option);
    $.ajax({
        type: "get",
        url: "${pageContext.request.contextPath}/user/querySomeUser",
        dataType: "JSON",
        success: function (result) {
            myChart.setOption({
                series: [{
                    // 根据名字对应到相应的系列
                    name: '注册数',
                    data: result.data,
                    //显示为柱状图
                    type: "bar"
                }]
            })
        }
    })
</script>