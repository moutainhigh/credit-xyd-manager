var prefix = "/business/product"

$(function() {
	   var startDate = new Date();
	   var endTime = new Date();
	   startDate.setDate(endTime.getDate() - 30);
	$('#startTime').val(startDate.Format("yyyy-MM-dd"));
	$('#endTime').val(endTime.Format("yyyy-MM-dd"));
		load();
});
	
	function load() {
		$('#productHeatTable')
				.bootstrapTable(
						{
							method : 'get', // 服务器数据的请求方式 get or post
							url : prefix + "/heatList", // 服务器数据的加载地址
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
									startTime : $('#startTime').val(),
									endTime : $('#endTime').val()
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
										title : '产品编号' 
									},
																	{
										field : 'productName', 
										title : '产品名称'
											
									},
																	{
										field : 'isReleaseCode', 
										title : '发布状态'
										
									},
																	{
										field : 'examineCount', 
										title : '用户产品详情查看次数' 
									},
																	{
										field : 'applyForCount', 
										title : '用户产品申请次数' 
									},
																	{
										field : 'heat', 
										title : '关注热度' 
									} ]
						});
	}
	
	function reLoad() {
		$('#productHeatTable').bootstrapTable('refresh');
	}
	
	Date.prototype.Format = function (fmt) {  
	    var o = {  
	        "M+": this.getMonth() + 1, //月份   
	        "d+": this.getDate(), //日   
	        "H+": this.getHours(), //小时   
	        "m+": this.getMinutes(), //分   
	        "s+": this.getSeconds(), //秒   
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度   
	        "S": this.getMilliseconds() //毫秒   
	    };  
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
	    for (var k in o)  
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));  
	    return fmt;  
	} 
	
