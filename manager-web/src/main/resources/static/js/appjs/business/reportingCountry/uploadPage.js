var prefix = "/business/reporting";
$(function () {
    var startDate = new Date();
    $('#startTime').val(startDate.Format("yyyy-MM-dd"));
    load();
});



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
        title: '上传文件并上报',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/openUploadModel' // iframe的url
    });
}



/**
 * 上传文件到服务器上面
 */
function save(){
    debugger
    console.log("执行提交上传excel 保存")
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
                    closePage();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}


function closePage() {
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}