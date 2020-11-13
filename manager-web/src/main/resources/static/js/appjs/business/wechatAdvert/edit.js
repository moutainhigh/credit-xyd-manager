$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	var advertFile = $("#advertFile").val();
	if(advertFile == '' || advertFile.indexOf('data:image') != 0){
		layer.alert("请选择照片");
		return ;
	}
	$.ajax({
		cache : true,
		type : "POST",
		url : "/business/wechatAdvert/update",
		data : $('#wechatAdvertForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				layer.msg("操作成功");
				window.location.href= "/business/wechatAdvert"; 
			} else {
				layer.alert(data.msg);
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#wechatAdvertForm").validate({
		rules : {
			advertName : {
				required : true
			},
			sort : {
				required : true,
				number:true
			}
		},
		messages : {
			advertName : {
				required : icon + "请输入广告名称"
			},
			sort : {
				required : icon + "请输入排序",
				number : icon + "排序必须为数字"
			}
		}
	})
}