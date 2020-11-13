
var prefix = "/business/userManage"
$(function() {
	load();
});
function load() {
	$('#companyTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/customerCompanyList", // 服务器数据的加载地址
				iconSize : 'outline',
				toolbar : '#exampleToolbar',
				striped : true, // 设置为true会有隔行变色效果
				dataType : "json", // 服务器返回的数据类型
				pagination : true, // 设置为true会在底部显示分页条
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
						limit : params.limit,
						offset : params.offset,
						// name:$('#searchName').val(),
						customerId : $('#customerId').text(),
					};
				},
				columns : [
					{
						field: 'no',
						title: '序号',
						sortable: true,
						align: "center",
						formatter: function (value, row, index) {
							//获取每页显示的数量
							// var pageSize=$('#companyTable').bootstrapTable('getOptions').pageSize;
							// //获取当前是第几页
							// var pageNumber=$('#companyTable').bootstrapTable('getOptions').pageNumber;
							// //返回序号，注意index是从0开始的，所以要加上1
							// return pageSize * (pageNumber - 1) + index + 1;
							return index + 1;
						}
					},
					{
						field : 'creditCode',
						title : '企业唯一标识'
					},
					{
						field : 'cname',
						title : '企业名称'
					},
					{
						field : 'creditCode',
						title : '统一社会信用代码'
					},
					{
						field : 'isAuthenticationDesc',
						title : '当前认证状态'
					},
					{
						field : 'createDate',
						title : '创建时间'
					},
					{
						field : 'createAuthentication',
						title : '最后认证时间'
					},
					{
						title : '操作',
						field : 'id',
						align : 'center',
						formatter : function(value, row, index) {
							var e = '<a class="btn btn-primary btn-sm" href="' + prefix + '/companyDetail/' + row.id + '" mce_href="#" title="详情"><i class="fa fa-list"></i></a> ';
							return e;
						}
					} ]
			});

	$('#financeTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/companyFinanceNeedsList", // 服务器数据的加载地址
				iconSize : 'outline',
				toolbar : '#exampleToolbar',
				striped : true, // 设置为true会有隔行变色效果
				dataType : "json", // 服务器返回的数据类型
				pagination : true, // 设置为true会在底部显示分页条
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
						limit : params.limit,
						offset : params.offset,
						// name:$('#searchName').val(),
						customerId : $('#customerId').text(),
					};
				},
				columns : [
					{
						field : 'no',
						title : '序号',
						sortable: true,
						align: "center",
						formatter: function (value, row, index) {
							// //获取每页显示的数量
							// var pageSize=$('#financeTable').bootstrapTable('getOptions').pageSize;
							// //获取当前是第几页
							// var pageNumber=$('#financeTable').bootstrapTable('getOptions').pageNumber;
							// //返回序号，注意index是从0开始的，所以要加上1
							// return pageSize * (pageNumber - 1) + index + 1;
							return index + 1;
						}
					},
					{
						field : 'needsNo',
						title : '需求单号'
					},
					{
						field : 'needsAmount',
						title : '融资金额'
					},
					{
						field : 'needsTerm',
						title : '融资期限'
					},
					{
						field : 'guaranteeMode',
						title : '担保方式'
					},
					{
						field : 'pubFinIns',
						title : '指定发布金融机构'
					},
					{
						field : 'companyInfo',
						title : '企业信息'
					},
					{
						field : 'acceptStatus',
						title : '受理状态' ,
						formatter: function (value, row, index) {
							if(value == '1'){
								return "待受理";
							}else if(value == '2'){
								return "已受理";
							}else{
								return '-';
							}
						}
					},
					{
						field : 'releaseDate',
						title : '发布时间'
					},
					{
						field : 'acceptDate',
						title : '最后受理时间'
					},
					{
						field : 'userDo',
						title : '最后受理人' ,
						formatter: function (value, row, index) {
							if(value == null || value.name == null){
								return "-";
							}else {
								return value.name;
							}
						}
					},
					{
						title : '操作',
						field : 'id',
						align : 'center',
						formatter : function(value, row, index) {
							var d = '<a class="btn btn-primary btn-sm" href="/business/companyFinanceNeeds/details/' + row.id+'" title="详情"  mce_href="#" onclick="search(\''
								+ row.id
								+ '\')"><i class="fa fa-list"></i></a> ';
							return d;
						}
					} ]
			});

	$('#reportTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/creditReportList", // 服务器数据的加载地址
				iconSize : 'outline',
				toolbar : '#exampleToolbar',
				striped : true, // 设置为true会有隔行变色效果
				dataType : "json", // 服务器返回的数据类型
				pagination : true, // 设置为true会在底部显示分页条
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
						limit : params.limit,
						offset : params.offset,
						// name:$('#searchName').val(),
						reportType : "1",
						customerNo : $('#customerNo').val(),
					};
				},
				columns : [
					{
						field : 'no',
						title : '序号',
						sortable: true,
						align: "center",
						formatter: function (value, row, index) {
							// //获取每页显示的数量
							// var pageSize=$('#reportTable').bootstrapTable('getOptions').pageSize;
							// //获取当前是第几页
							// var pageNumber=$('#reportTable').bootstrapTable('getOptions').pageNumber;
							// //返回序号，注意index是从0开始的，所以要加上1
							// return pageSize * (pageNumber - 1) + index + 1;
							return index + 1;
						}
					},
					{
						field : 'reportType',
						title : '报告类型'
					},
					{
						field : 'cname',
						title : '企业名称'
					},
					{
						field : 'creditCode',
						title : '统一社会信用代码'
					},
					{
						field : 'searchDate',
						title : '查询时间'
					},
					{
						field : 'searchResult',
						title : '查询结果状态'
					},
					{
						field : 'searchResultName',
						title : '报告名称'
					} ]
			});
}