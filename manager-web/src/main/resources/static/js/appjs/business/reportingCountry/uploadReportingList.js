var prefix = "/business/reporting";
$(function () {
    var startDate = new Date();
    $('#date').val(startDate.Format("yyyy-MM-dd"));
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/getReportRecord", // 服务器数据的加载地址
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                singleSelect: false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams: function (params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset: params.offset,
                        date: $('#startTime').val(),
                        userName: $('#userName').val()
                    };
                },
                columns: [
                    {
                        field: 'no',
                        title: '序号',
                        sortable: true,
                        align: "center",
                        formatter: function (value, row, index) {
                            return index + 1;
                        }
                    },
                    {
                        field: 'date',
                        title: '操作时间'
                    },
                    {
                        field: 'userName',
                        title: '账户名'

                    },
                    {
                        field: 'dayStatus',
                        title: '增量是否上报成功',
                    },
                    {
                        field: 'allStatus',
                        title: '全量是否上报成功',
                    },

                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var xiangqing = '<a href="'+prefix + '/downloadNow/' + row.date+'">下载查看文件</a>&nbsp;&nbsp;';
                            return xiangqing;
                        }
                    }]
            });
}

function reLoad() {
    var opt = {
        query: {}
    }
    $('#exampleTable').bootstrapTable('refresh', opt);
}

/***
 * 打开上传文件的窗口
 */
function openUploadModel() {
    layer.open({
        type:2,
        title: '上传文件并上报',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/openUploadModel' // iframe的url
    });
}

/**
 * 下载最新的
 */
function downloadNewModel(){
 /*   $.ajax({
        url: prefix + "/downloadNewModel",
        type: "get",
        //contentType: "multipart/form-data; charset=utf-8",
       // enctype: 'multipart/form-data',
        //contentType: false,
        success: function (r) {
            if (r.code == 0) {
                layer.msg(r.msg);
                reLoad();
            } else {
                layer.msg(r.msg);
            }
        }
    });*/
    window.location.href=prefix + "/downloadNewModel";
}
/*function downloadNow(date) {
    window.location.href=prefix + "/downloadNow?date="+date;
}*/



/**
 * 上传文件到服务器上面
 */
function uploadExcel(){
    //判断是否已经选择了文件
    var selectFile = $("#fileSelect").val();
    if (selectFile == null || selectFile == '') {
        layer.alert("请先选择文件！");
        return;
    }
    layer.confirm('是否确认上传文件并上报', {
        btn: ['是', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/uploadExcel",
            type: "post",
            //contentType: "multipart/form-data; charset=utf-8",
            enctype: 'multipart/form-data',
            //contentType: false,
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}

Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "H+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}