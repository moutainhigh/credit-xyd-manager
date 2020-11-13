
var prefix = "/business/homeProductEdit";
var productType = $("#productType").val();
var productTypeDesc;
if(productType == '0'){ productTypeDesc = "首页热门贷款产品";}
if(productType == '1'){ productTypeDesc = "首页企业贷款产品";}
if(productType == '2'){ productTypeDesc = "首页个人贷款产品";}
$(function() {
	load(productType);
});
function load(productType) {
	$('#exampleTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/productList", // 服务器数据的加载地址
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
						productType : productType,
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
							// var pageSize=$('#exampleTable').bootstrapTable('getOptions').pageSize;
							// //获取当前是第几页
							// var pageNumber=$('#exampleTable').bootstrapTable('getOptions').pageNumber;
							// //返回序号，注意index是从0开始的，所以要加上1
							// return pageSize * (pageNumber - 1) + index + 1;
							return index + 1;
						}
					},
					{
						field : 'sort',
						title : '优先级'
					},
					{
						field : 'productNo',
						title : '产品编号'
					},
					{
						field : 'productName',
						title : '产品名称',
						formatter : function(value, row, index) {
							var e = '<a style="text-decoration:underline;" href="/business/product/details/' + row.id + '/1" mce_href="#" title="详情">' + value + '</a> ';
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
					},
					{
						field : 'updateDate',
						title : '最后更新时间'
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
							var f = '<a class="btn btn-warning btn-sm" href="#" title="移除"  mce_href="#" onclick="moveOut(\''
								+ row.id + '\',\'' + row.productName
								+ '\')"><i class="fa fa-remove"></i></a> ';
							return f;
						}
					} ]
			});
}
function reLoadList() {
	var opt = {
		query : {

		}
	}
	$('#exampleTable').bootstrapTable('refresh', opt);
}
function moveIn() {
	layer.open({
		type : 2,
		title : '移入产品',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/moveIn/' + productType // iframe的url
	});
}
function moveOut(id, productName) {
	layer.confirm('确定将[' +  productName + ']移出' + productTypeDesc +'吗？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix + "/moveOut",
			type : "post",
			data : {
				'id' : id,
				'productType' : productType
			},
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoadList();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	})
}