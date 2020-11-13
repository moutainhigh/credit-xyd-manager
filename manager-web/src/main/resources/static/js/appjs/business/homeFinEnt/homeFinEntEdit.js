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
		url : "/business/homeFinEnt/updateHomeFinEnt",
		data : $('#homeFinEntForm').serialize(),
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
	$("#homeFinEntForm").validate({
		rules : {
			finFile : {
				required : true
			},
			sort : {
				required : true
			}
		},
		messages : {
			finFile : {
				required : icon + "请上传机构LOGO"
			},
			sort : {
				required : icon + "请输入排序优先级"
			}
		}
	})
}
function closePage() {
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}