
var prefix = "/business/homeFinEnt";
var entType = $("#entType").val();
var entTypeDesc;
if(entType == '1'){ entTypeDesc = "金融机构";}
if(entType == '2'){ entTypeDesc = "信用机构";}
$(function() {
	load(entType);
});
function load(entType) {
	$('#exampleTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/homeFinEntList", // 服务器数据的加载地址
				iconSize : 'outline',
				toolbar : '#exampleToolbar',
				striped : true, // 设置为true会有隔行变色效果
				dataType : "json", // 服务器返回的数据类型
				pagination : true, // 设置为true会在底部显示分页条
				singleSelect : false, // 设置为true将禁止多选
				// contentType : "application/x-www-form-urlencoded",
				// //发送到服务器的数据编码类型
				pageSize : 10, // 如果设置了分页，每页数据条数
				pageNumber : 1, // 如果设置了分布，首页页码
				showColumns : false, // 是否显示内容下拉框（选择显示的列）
				sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
				queryParams : function(params) {
					return {
						//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
						limit : params.limit,
						offset : params.offset,
						entType : entType,
					};
				},
				columns : [
					{
						field: 'no',
						title: '序号',
						sortable: true,
						align: "center",
						formatter: function (value, row, index) {
							return index + 1;
						}
					},
					{
						field : 'sort',
						title : '优先级'
					},
					{
						field : 'finFile',
						title : '合作' + entTypeDesc + 'LOGO',
						formatter : function (value, row, index) {
							return "<img style='width: 100px;height: 50px;' src='"+value+"' alt=''>"
						}
					},
					{
						field : 'finUrl',
						title : '跳转URL',
					},
					{
						field : 'updateDate',
						title : '最后更新时间',
					},
					{
						field : 'updateByName',
						title : '最后更新人'
					},
					{
						title : '操作',
						field : 'id',
						align : 'center',
						formatter : function(value, row, index) {
							var e = '<a class="btn btn-primary btn-sm" href="#" mce_href="#" title="编辑" onclick="editFinEnt(\''
								+ row.id
								+ '\')"><i class="fa fa-edit"></i></a> ';
							var f = '<a class="btn btn-warning btn-sm" href="#" title="移除"  mce_href="#" onclick="removeFinEnt(\''
								+ row.id
								+ '\')"><i class="fa fa-remove"></i></a> ';
							return e + f;
						}
					} ]
			});
}
function reLoad() {
	var opt = {
		query : {}
	}
	$('#exampleTable').bootstrapTable('refresh', opt);
}
function addFinEnt() {
	layer.open({
		type : 2,
		title : '添加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/addHomeFinEnt/' + entType // iframe的url
	});
}
function editFinEnt(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/editHomeFinEnt/' + id // iframe的url
	});
}
function removeFinEnt(id) {
	layer.confirm('是否确认删除', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix + "/removeHomeFinEnt",
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