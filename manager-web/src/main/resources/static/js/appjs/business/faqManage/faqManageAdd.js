var prefix = "/business/faqManage"

function save(){
	var id = $("#id").val();
	var faqTitle = $("#faqTitle").val();
	var sort = $("#sort").val();
	var faqContent = $("iframe").contents().find("body").html();//$("#faqContent").val();
	var faqManageStr = {
			id:id,
			faqTitle:faqTitle,
			sort:sort,
			faqContent:faqContent
	};
	 $.ajax({
  	     type: "post",
  	     data:{faqManageStr:JSON.stringify(faqManageStr)},
	     url: prefix+'/save',
	  	 async:false,
	     success: function(data){
	    	 layer.msg("操作成功");
	    	 window.location.href= prefix+"/details/"+data+"/1"; 
        },
        error : function(request) {
			layer.alert("操作失败！");
		}
	 });
}

function goBack(){
	var uRef = document.referrer;
	window.location.href=uRef;
}

function edit(){
	var id = $("#id").val();
	window.location.href= prefix+"/details/"+id+"/2"; 
}