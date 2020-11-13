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
		url : "/business/companyFinanceNeeds/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				layer.msg("操作成功");
			} else {
				layer.alert(data.msg)
			}

		}
	});

}

function back(){
	windows.loction.href= "/business/companyFinanceNeeds";
}

function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			acceptStatus : {
				required : true
			}/*,
			acceptRemarks : {
				required : true
			}*/
		},
		messages : {
			acceptStatus : {
				required : icon + "请选择受理状态"
			}/*,
			acceptRemarks : {
				required : icon + "请输入受理备注"
			}*/
		}
	})
}