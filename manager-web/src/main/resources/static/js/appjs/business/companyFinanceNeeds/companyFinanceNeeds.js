
var prefix = "/business/companyFinanceNeeds"
$(function() {
	load();
});


function selectLoad() {
	var html = "";
	$.ajax({
		url : '/common/dict/list/com_fin_need_type',
		success : function(data) {
			//加载数据
			html += '<option value="-1">受理状态</option>';
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].value + '" ';
				//默认设置为待受理
				if( data[i].value == '1'){
					html += ' selected ';
				}
				html += '">' + data[i].name + '</option>';
			}
		
			$(".chosen-select").append(html);
			$(".chosen-select").chosen({
				maxHeight : 200
			});
			//点击事件
			//设置默认选中
//			$('#companyFinanceNeedsTable').bootstrapTable('refresh');
			/*$('.chosen-select').on('change', function(e, params) {
				console.log(params.selected);
				var opt = {
					query : {
						type : params.selected,
					}
				}
				$('#companyFinanceNeedsTable').bootstrapTable('refresh', opt);
			});*/
		}
	});
}


function load() {
	selectLoad();
	$('#companyFinanceNeedsTable')
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
								acceptStatus : $('#acceptStatus').val(),
								companyInfo : $('#companyInfo').val(),
								customerPhone : $('#customerPhone').val()
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
									field : 'needsNo', 
									title : '需求单号' 
								},
																{
//									field : 'customerDO.customerPhone', 
									field : 'customerDO', 
									title : '帐户名',
									formatter: function (value, row, index) {
										var e = '<a class="" href="/business/userManage/registerAccountDetail/'+ value.id +'" mce_href="#" title="查看账号详细" )">'
										+ value.customerPhone + '</a> ';
										return e;
					                }
										
								},
																{
									field : 'customerDO.customerName', 
									title : '姓名' 
								},
																{
									field : 'needsAmountDesc', 
									title : '融资金额' 
								},
																{
									field : 'needsTermDesc', 
									title : '融资期限' 
								},
																{
									field : 'guaranteeModeDesc', 
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
									field : 'acceptStatusDes', 
									title : '受理状态' /*,
									formatter: function (value, row, index) {
										if(value == '1'){
											return "待受理";
										}else if(value == '2'){
											return "已受理";
										}else{
											return '-';
										}
					                }*/
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
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="'+prefix + '/edit/' + row.id+'" mce_href="#" title="编辑受理" "><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-success btn-sm" href="'+prefix + '/details/' + row.id+'" title="详情"  mce_href="#" onclick="search(\''
												+ row.id
												+ '\')"><i class="fa fa-search"></i></a> ';
										return d + e;
									}
								} ]
					});
}
function reLoad() {
	$('#companyFinanceNeedsTable').bootstrapTable('refresh');
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function search(id) {
	
}
