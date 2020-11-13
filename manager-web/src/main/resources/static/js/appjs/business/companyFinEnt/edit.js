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
		url : "/business/companyFinEnt/update",
		data : $('#companyFinEntForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				layer.msg("操作成功");
				window.location.href = "/business/companyFinEnt"; 
			} else {
				layer.alert(data.msg);
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#companyFinEntForm").validate({
		rules : {
			finName : {
				required : true
			},
			sort : {
				required : true,
				number:true
			}
		},
		messages : {
			finName : {
				required : icon + "请输入机构名称"
			},
			sort : {
				required : icon + "请输入排序",
				number : icon + "排序必须为数字"
			}
		}
	})
}