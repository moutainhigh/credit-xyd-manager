
var prefix = "/business/institutionManage";
var insType = $("#insType").val();
$(function() {
	load(insType);
});
function load(insType) {
	$('#exampleTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/institutionList", // 服务器数据的加载地址
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
						limit : params.limit,
						offset : params.offset,
						insType : insType,
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
						field: 'no',
						title: '序号',
						sortable: true,
						align: "center",
						formatter: function (value, row, index) {
							//获取每页显示的数量
							// var pageSize=$('#exampleTable').bootstrapTable('getOptions').pageSize;
							// //获取当前是第几页
							// var pageNumber=$('#exampleTable').bootstrapTable('getOptions').pageNumber;
							// //返回序号，注意index是从0开始的，所以要加上1
							// return pageSize * (pageNumber - 1) + index + 1;
							return index + 1;
						}
					},
					{
						field : 'creditCode',
						title : '统一社会信用代码'
					},
					{
						field : 'insName',
						title : '金融机构简称'
					},
					{
						field : 'file',
						title : '金融机构LOGO缩略图',
						// width : '15%',
						formatter : function (value, row, index) {
							return "<img style='width: 100px;height: 50px;' src='"+value+"' alt=''>"
						}
					},
					{
						field : 'productCount',
						title : '产品数量'
					},
					{
						field : 'remark',
						title : '备注'
					},
					{
						field : 'updateDate',
						title : '最后更新时间'
					},
					{
						field : 'updateUserName',
						title : '最后更新人'
					},
					{
						title : '操作',
						field : 'id',
						align : 'center',
						formatter : function(value, row, index) {
							var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="editIns(\''
								+ row.id
								+ '\')"><i class="fa fa-edit"></i></a> ';
							var d = '<a class="btn btn-success btn-sm ' + s_add_h + '" href="#" title="详情"  mce_href="#" onclick="infoIns(\''
								+ row.id
								+ '\')"><i class="fa fa-list"></i></a> ';
							var f = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="removeIns(\''
								+ row.id + '\',\'' + row.insName
								+ '\')"><i class="fa fa-remove"></i></a> ';
							return e + d +f;
						}
					} ]
			});
}
function reLoad() {
	var opt = {
		query : {
			creditCode : $('#creditCode').val(),
			insName : $('#insName').val(),
		}
	}
	$('#exampleTable').bootstrapTable('refresh', opt);
}
function cleanInput() {
	$("#creditCode").val("");
	$("#insName").val("");
}
function addIns() {
	layer.open({
		type : 2,
		title : '添加机构',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/addIns/' + insType // iframe的url
	});
}
function editIns(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/editIns/' + id // iframe的url
	});
}
function infoIns(id) {
	layer.open({
		type : 2,
		title : '详情',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/infoIns/' + id // iframe的url
	});
}
function removeIns(id, insName) {
	layer.confirm('确定要删除[' +  insName + ']？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix + "/removeIns",
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
}

function addD(type,description) {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add/'+type+'/'+description // iframe的url
	});
}