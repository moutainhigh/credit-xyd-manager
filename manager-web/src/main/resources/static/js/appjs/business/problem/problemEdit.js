function updateEdit(){
		var id = $("#id").val();
		var problemCode = $("#problemCode").val().replace(/(^\s*)|(\s*$)/g, "");
		if(problemCode==null||problemCode==''){
			layer.alert("请输入问题编号");
			return;
		}
		var problemTitle = $("#problemTitle").val().replace(/(^\s*)|(\s*$)/g, "");
		if(problemTitle==null||problemTitle==''){
			layer.alert("请输入问题简述");
			return;
		}
		var problemOptions = "";
		$('input[name="problemOptions"]:checked').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数  
			if(problemOptions!=""){
				problemOptions = problemOptions+","+$(this).val();
			}else{
				problemOptions = $(this).val();
			}
        });
		if(problemOptions==null||problemOptions==''){
			layer.alert("请选择可选项");
			return;
		}
		var problemRemarks = $("#problemRemarks").val().replace(/(^\s*)|(\s*$)/g, "");
		if(problemRemarks==null||problemRemarks==''){
			layer.alert("请输入备注");
			return;
		}
		var optionsRetain = $("#optionsRetain").val();//编辑之前的可选项
		//检索编号是否已存在
		var biaoshi = "notexist";
		var problemCodeFormer = $("#problemCodeFormer").val();
		if(problemCodeFormer!=problemCode&&problemCode!=null&&problemCode!=''){
			$.ajax({
	 		   	  type: "get",
	 		    	 dataType:"",
	 		    	 data:{
	 		    		problemCode:problemCode
	 		    		},
	 			     url: "/business/financial/problem/getProblemCode",
	 			     dataType : 'json',
	 			  	 async:false,
	 			     success: function(exist){
	 			    	  if(exist.code!='0'){
	 			    		   layer.alert("编号已存在");
		   						biaoshi = "exist";
		   					} 
	 		         }
	  		 });
		}
		if(biaoshi=='notexist'&&id!=null&&id!=''){
			//根据问题编号+可选项检查取消的可选项在智能匹配策略中是否存在
			$.ajax({
			   	  type: "post",
			    	 dataType:"",
			    	 data:{id:id,optionsRetain:optionsRetain},
				     url: "/business/financial/problemStrategy/optionsList",
				     dataType : 'json',
				  	 async:false,
				     success: function(data){
				    	 if(data.code=='0'){
				    		 $.ajax({
				   		   	  type: "post",
				   		    	 dataType:"",
				   		    	 data:{
				   		    		 id:id,
				   		    		 problemCode:problemCode,
				   		    		 problemTitle:problemTitle,
				   		    		 problemOptions:problemOptions,
				   		    		 problemRemarks:problemRemarks
				   		    		},
				   			     url: "/business/financial/problem/update",
				   			     dataType : 'json',
				   			  	 async:false,
				   			     success: function(value){
				   			    	  if(value.code=='0'){
					   			    		layer.msg("操作成功");
					   						window.location.href= "/business/financial/problem/details/"+id+"/1"; 
					   					} else {
					   						layer.alert(data.msg);
					   					}
				   		         }
				    		 });
				    	 }else{
				    		 layer.msg("无法去除正在被使用的选项");
				    	 }
			         }
			});
		}
		if(biaoshi=='notexist'&&(id==null||id=='')){//新增
			$.ajax({
	   		   	  type: "post",
	   		    	 dataType:"",
	   		    	 data:{
	   		    		 id:id,
	   		    		 problemCode:problemCode,
	   		    		 problemTitle:problemTitle,
	   		    		 problemOptions:problemOptions,
	   		    		 problemRemarks:problemRemarks
	   		    		},
	   			     url: "/business/financial/problem/save",
	   			     dataType : 'json',
	   			  	 async:false,
	   			     success: function(value){
	   			    	  if(value.code=='0'){
		   			    		layer.msg("操作成功");
		   						window.location.href= "/business/financial/problem"; 
		   					} else {
		   						layer.alert(data.msg);
		   					}
	   		         }
	    		 });
		}
}