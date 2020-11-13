
var prefix = "/business/wechatProduct";
$(function() {
	load();
});
function load() {
	$('#exampleTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/releaseProductList", // 服务器数据的加载地址
				iconSize : 'outline',
				toolbar : '#exampleToolbar',
				striped : true, // 设置为true会有隔行变色效果
				dataType : "json", // 服务器返回的数据类型
				pagination : true, // 设置为true会在底部显示分页条
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
						limit : params.limit,
						offset : params.offset
					};
				},
				columns : [
					{
						checkbox : true
					},
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
						field : 'productNo',
						title : '产品编号'
					},
					{
						field : 'id',
						title : 'id',
						visible : false,
					},
					{
						field : 'productName',
						title : '产品名称',
						formatter : function(value, row, index) {
							var e = '<a style="text-decoration:underline;" href=/business/product/details/' + row.id + '/1" mce_href="#" title="详情">' + value + '</a> ';
							return e;
						}
					},
					{
						field : 'insName',
						title : '所属金融机构'
					},
					{
						field : 'isReleaseDesc',
						title : '产品状态'
					}]
			});
}
function reLoad() {
	var opt = {
		query : {
			productName : $('#productName').val(),
		}
	}
	$('#exampleTable').bootstrapTable('refresh', opt);
}
function batchMoveIn() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要移入的产品");
		return;
	}
	layer.confirm("确认要移入选中的'" + rows.length + "'条产品吗?", {
		btn : [ '确定', '取消' ]
		// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['id'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"productIds" : ids
			},
			url : prefix + '/batchMoveIn',
			success : function(r) {
				if (r.code == 0) {
					parent.layer.msg(r.msg);
					parent.reLoad();
					var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
					parent.layer.close(index);
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {});
}