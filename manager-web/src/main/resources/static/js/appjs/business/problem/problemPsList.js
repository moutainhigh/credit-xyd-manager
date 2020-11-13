var prefix = "/business/financial/problemStrategy"
	
var columnsArray = [];
$.ajax({
   	  type: "get",
    	 dataType:"",
    	 data:"",
	     url: "/business/financial/problem/allList",
	     dataType : 'json',
	  	 async:false,
	     success: function(data){
	    	 for (var i = 0; i < data.length; i++) {
	    		 columnsArray.push({
	                    "title": data[i].problemTitle,
	                    "value": data[i].problemOptions,
	                    "code":data[i].problemCode,
	                    "problemId":data[i].id
	             });
	    	 }
         }
 });
	

$(function() {
		load();
});
	
	function load() {
		$('#problemListTable')
				.bootstrapTable(
						{

							method : 'get', // 服务器数据的请求方式 get or post
							url : prefix + "/list", // 服务器数据的加载地址
						//	showRefresh : true,
						//	showToggle : true,
						//	showColumns : true,
							iconSize : 'outline',
							toolbar : '#exampleToolbar',
							striped : true, // 设置为true会有隔行变色效果
							dataType : "json", // 服务器返回的数据类型
							pagination : true, // 设置为true会在底部显示分页条
							// queryParamsType : "limit",
							// //设置为limit则会发送符合RESTFull格式的参数
							singleSelect : false, // 设置为true将禁止多选
							// contentType : "application/x-www-form-urlencoded",
							// //发送到服务器的数据编码类型
							pageSize : 5, // 如果设置了分页，每页数据条数
							pageNumber : 1, // 如果设置了分布，首页页码
							//search : true, // 是否显示搜索框
							showColumns : false, // 是否显示内容下拉框（选择显示的列）
							sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
							queryParams : function(params) {
								return {
									//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
									limit: params.limit,
									offset:params.offset
								};
							},
							// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
							// queryParamsType = 'limit' ,返回参数必须包含
							// limit, offset, search, sort, order 否则, 需要包含:
							// pageSize, pageNumber, searchText, sortName,
							// sortOrder.
							// 返回false将会终止请求
							columns :[
								{
									field : 'no', 
									title : '序号',
									sortable: true,
					                align: "center",
					                width: 40,
					                formatter: function (value, row, index) {
					                	return index + 1;
					                }
								},
																{
									field : 'productNo',
									width: 40,
									title : '产品编号' 
								},
																{
									field : 'productName', 
									title : '产品名称'
										
								},
																{
									field : 'insName', 
									title : '所属金融机构' 
								},
																{
									field : 'matchOption', 
									title : '基础匹配规则配置',
									formatter : function(value, row, index) {
										var va = "";
										for (var i = 0; i < columnsArray.length; i++) {
											va+='('+columnsArray[i].code+')'+ columnsArray[i].title+'&nbsp;';
											var values = columnsArray[i].value;//问题表数据
											var arr=new Array();
											arr=values.split(',');
											var problemId = columnsArray[i].problemId;
											for(var j=0;j<arr.length;j++){
												va+= '<input name="'+columnsArray[i].problemId+row.productId+'" class="problemOptions" type="checkbox"';
												//获取该产品原本选中的选项数据
												var matchOption = row.matchOption;//结果是以分号分隔 问题id:选项;问题id:...  eg：1:A,B;32ad5801b3694f93b336d51052b4dbd4:A
												if(matchOption!=null&&matchOption!=''){
													var options = new Array();
													optionArray = matchOption.split(';');//分隔出每个问题的历史选项
													for(var y=0;y<optionArray.length;y++){
														var hisId = optionArray[y].split(":")[0];
														var option = optionArray[y].split(":")[1];
														if(hisId==problemId&&option.indexOf(arr[j])!=-1){
															va+=  'checked ';
														}
													}
												}
												va+=  'value="'+arr[j]+'"/>'+arr[j];
													//va+= '<input name="problemOptions" class="problemOptions" type="checkbox" value="'+arr[j]+'"/>'+arr[j];
											}
											va+= '<input class="problemId" type="hidden" value="'+columnsArray[i].problemId+'">'
											if(i!=columnsArray.length-1){
												va+= '<br/>';
											}
											va+= '<br/>';
											//va+= '<input name="problemOptions" class="problemOptions" type="checkbox" value="'+columnsArray[i].value+'"/>'+columnsArray[i].value+'<br/>'
										}
										va+= '<input class="productId" type="hidden" value="'+row.productId+'">'
										return va;
									}
								},
								{
									field : 'updateDate', 
									title : '最后更新时间' 
								},
								{
									field : 'updateName', 
									title : '最后更新人' 
								},
																{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var shanchu  = '<a href="#" onclick="saveBatch(\''+ row.productId+ '\')">保存</a>';
										return shanchu;
									}
								}
								]
						
						});
	}
	
	function reLoad() {
		$('#problemListTable').bootstrapTable('refresh');
	}
	

	function saveBatch(id){
		var product_problem_strategy = [];
		if(id==null||id==''){
			var checkboxLength = $("input[type='checkbox']:checked").length;
			//获取页面所有
			$('.productId').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数  
				var productId = $(this).val();
				for(var i=0;i<columnsArray.length;i++){
					if(checkboxLength>0){
						$('input[name="'+columnsArray[i].problemId+$(this).val()+'"]:checked').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数  
							product_problem_strategy.push({
								"productId":productId,
								"problemId":columnsArray[i].problemId,
								"matchOption":$(this).val()
							});
						});
					}else{
						product_problem_strategy.push({
							"productId":productId,
							"problemId":columnsArray[i].problemId
						});
					}
				}
	        });
		}else{
			for(var i=0;i<columnsArray.length;i++){
				var checkboxLength = $('input[name="'+columnsArray[i].problemId+id+'"]:checked').length;
				if(checkboxLength>0){
					$('input[name="'+columnsArray[i].problemId+id+'"]:checked').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数  
						product_problem_strategy.push({
							"productId":id,
							"problemId":columnsArray[i].problemId,
							"matchOption":$(this).val()
						});
					});
				}else{
					product_problem_strategy.push({
						"productId":id,
						"problemId":columnsArray[i].problemId
					});
				}
			}
		}
		
		$.ajax({
		   	  type: "post",
		    	 dataType:"",
		    	 data:{listStr:JSON.stringify(product_problem_strategy)},
			     url: prefix+"/batchSave",
			     dataType : 'json',
			  	 async:false,
			     success: function(data){
			    	 if(data.code=='0'){
	   			    		layer.msg("操作成功");
	   			    		reLoad();
	   					} else {
	   						layer.alert(data.msg);
	   					}
		         }
		 });
	}