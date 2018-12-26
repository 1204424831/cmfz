<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>

<meta charset="utf-8">
<!-- 引入 ECharts 文件 -->

<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main1" style="width: 600px;height:400px;"></div>

<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('main1'));

    var option = {
        title: {
            text: '持明法洲用户分布图',
            subtext: '实地考察',
            left: 'center'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['男', '女']
        },
        visualMap: {
            min: 0,
            max: 4,
            left: 'left',
            top: 'middle',
            text: ['高', '低'],           // 文本，默认为数值文本
            calculable: true
        },
        toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        }, series: [
            {
                name: '男',
                type: 'map',
                mapType: 'china',
                roam: false,
                label: {
                    normal: {
                        show: false
                    },
                    emphasis: {
                        show: true
                    }
                }
            }, {
                name: '女',
                type: 'map',
                mapType: 'china',
                roam: false,
                label: {
                    normal: {
                        show: false
                    },
                    emphasis: {
                        show: true
                    }
                }
            }
        ]
    };
    myChart.setOption(option);
    $.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath}/user/queryProvinces",
        data: "sex=男",
        dataType: "JSON",
        success: function (result) {
            myChart.setOption({
                series: [
                    {data: result.data}, {}
                ]
            })
        }
    });
    $.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath}/user/queryProvinces",
        data: "sex=女",
        dataType: "JSON",
        success: function (result) {
            console.log(result);
            myChart.setOption({
                series: [
                    {}, {data: result.data}
                ]
            })
        }
    })
</script>
