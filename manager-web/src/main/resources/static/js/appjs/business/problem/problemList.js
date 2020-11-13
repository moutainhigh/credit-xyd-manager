var prefix = "/business/financial/problem"

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
							pageSize : 10, // 如果设置了分页，每页数据条数
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
										field : 'problemCode', 
										title : '问题编号' 
									},
																	{
										field : 'problemTitle', 
										title : '问题简述'
											
									},
																	{
										field : 'problemOptions', 
										title : '可选项' 
									},
																	{
										field : 'problemRemarks', 
										title : '备注'
									},
																	{
										title : '操作',
										field : 'id',
										align : 'center',
										formatter : function(value, row, index) {
											var xiangqing = '<a href="'+prefix + '/details/' + row.id+'/1">详情</a>&nbsp;&nbsp;';
											var bianji = '<a href="'+prefix + '/details/' + row.id+'/2">编辑</a>&nbsp;&nbsp;';
											var shanchu  = '<a href="#" onclick="remove(\''+ row.id+ '\')">删除</a>';
											return xiangqing+bianji+shanchu;
										}
									} ]
						});
	}
	
	function reLoad() {
		$('#problemListTable').bootstrapTable('refresh');
	}
	
	function remove(id) {
		//根据问题编号 查询是否已经在策略中使用
		$.ajax({
			url : "/business/financial/problemStrategy/getCodeList",
			type: "post",
			data : {
				'problemCode' : id
			},
			success : function(data) {
				if (data.code == 0) {
					layer.confirm('当前问题未被匹配策略使用，可以删除，是否确认删除？', {
						btn : [ '确定', '取消' ]
					}, function() {
						$.ajax({
							url : prefix + "/remove",
							type : "post",
							data : {
								'id' : id
							},
							success : function(r) {
								if (r.code == 0) {
									layer.msg(r.msg);
									reLoad();
								} else {
									layer.msg(r.msg);
								}
							}
						});
					})
				} else {
					layer.msg("当前问题正被匹配策略使用,不可删除");
				}
			}
		});
		
	}