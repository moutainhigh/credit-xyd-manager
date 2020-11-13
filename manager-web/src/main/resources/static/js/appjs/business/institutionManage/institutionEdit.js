$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/business/institutionManage/updateIns",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			creditCode : {
				required : true
			},
			insName : {
				required : true
			},
			file : {
				required : true
			}
		},
		messages : {
			creditCode : {
				required : icon + "请输入统一社会信用代码"
			},
			insName : {
				required : icon + "请输入机构简称"
			},
			file : {
				required : icon + "请输入机构LOGO"
			}
		}
	})
}