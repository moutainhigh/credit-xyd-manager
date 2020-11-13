$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/business/horse/save",
		data : $('#horseForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				layer.msg("操作成功");
				window.location.href = '/business/horse';

			} else {
				layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#horseForm").validate({
		rules : {
			horse : {
				required : true
			},
			sort : {
				required : true,
				number:true
			}
		},
		messages : {
			horse : {
				required : icon + "请输入跑马灯内容"
			},
			sort : {
				required : icon + "请输入排序",
				number : icon + "排序必须为数字"
			}
		}
	})
}