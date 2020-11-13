var prefix = "/business/product"

$(function() {
		load();
});
	
	function load() {
		$('#productListTable')
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
							pageSize : 10, // 如果设置了分页，每页数据条数
							pageNumber : 1, // 如果设置了分布，首页页码
							//search : true, // 是否显示搜索框
							showColumns : false, // 是否显示内容下拉框（选择显示的列）
							sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
							queryParams : function(params) {
								return {
									//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
									limit: params.limit,
									offset:params.offset,
						           // name:$('#searchName').val(),
						           // username:$('#searchName').val()
									productName : $('#productName').val(),
									insName : $('#insName').val(),
									isRelease : $('#isRelease').val()
								};
							},
							// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
							// queryParamsType = 'limit' ,返回参数必须包含
							// limit, offset, search, sort, order 否则, 需要包含:
							// pageSize, pageNumber, searchText, sortName,
							// sortOrder.
							// 返回false将会终止请求
							columns : [
									{
										field : 'sort', 
										title : '排序优先级',
						                align: "center",
						                width: 40,
						                formatter : function(value, row, index) {
											//var jieguo = '<input class="sort"'+index' value="'+value+'" type="text">';
						                	var jieguo = null;
						                	if(value==null){
						                		jieguo = '<input class="sort-input" type="text">';
						                	}else{
						                		jieguo = '<input class="sort-input" type="text" value="'+value+'">';
						                		
						                	}
						                	jieguo = jieguo + '<input class="sortTd" type="hidden" value="'+row.id+'">'
						                	return jieguo;
										}
									},
																	{
										field : 'productNo', 
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
										field : 'isReleaseCode', 
										title : '发布状态'/*,
										formatter: function (value, row, index) {
											if(value == '0'){
												return "未发布";
											}else if(value == '1'){
												return "已发布";
											}else if(value == '2'){
												return "已回收";
											}else{
												return '-';
											}
						                }*/
									},
																	{
										field : 'updateDate', 
										title : '最后更新时间' 
									},
																	{
										field : 'updateName', 
										title : '最后更新人' 
									}
								]
						});
	}
	
	
function reLoad() {
	$('#productListTable').bootstrapTable('refresh');
}
	
function batchSave(){
	var sortStr = [];
	$("#productListTable").find("tr").each(function(index){
		if(index!=0){
			var product = new Object();
			product.sort = $(this).find(".sort-input").val();
			product.id = $(this).find(".sortTd").val();
			sortStr.push(product);
			temp = "";
		}
	});
	$.ajax({
	   	  type: "post",
	    	 dataType:"",
	    	 data:{sortStr:JSON.stringify(sortStr)},
		     url: prefix+"/updateSort",
		     dataType : 'json',
		  	 async:false,
		     success: function(data){
		    	 if(data.code=='0'){
		    		 reLoad();
		    	 }
	         }
	});
}
	
	