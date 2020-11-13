
var prefix = "/business/advert"
$(function() {
	load();
});

function selectLoad() {
	var html = "";
	$.ajax({
		url : '/common/dict/list/is_release',
		success : function(data) {
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].value + '" ';
				html += '">' + data[i].name + '</option>';
			}
		
			$(".chosen-select").append(html);
			$(".chosen-select").chosen({
				maxHeight : 200
			});
			//点击事件
			//设置默认选中
			/*$('.chosen-select').on('change', function(e, params) {
				console.log(params.selected);
				var opt = {
					query : {
						type : params.selected,
					}
				}
				$('#advertTable').bootstrapTable('refresh', opt);
			});*/
		}
	});
}

function load() {
	selectLoad();
	$('#advertTable')
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
					           // username:$('#searchName').val(),
								isRelease : $("#isRelease").val()
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
									field : 'sort', 
									title : '排序优先级' 
								},
																{
									field : 'advertName', 
									title : '广告图名称' 
								},
																{
									field : 'advertFile', 
									title : '广告图' ,
									formatter: function (value, row, index) {
										var e = '<img class="" height="100px" width="200px" src="'+ value +'" mce_href="#" alt="广告图" )" />'
										return e;
					                }
								},
																{
									field : 'advertHref', 
									title : '跳转链接' 
								},
																{
									field : 'isReleaseDesc', 
									title : '是否发布' /*,
					                formatter: function (value, row, index) {
					                	if(value == '0'){
					                		return "未发布";
					                	}else if(value == '1'){
					                		return "发布中";
					                	}else{
					                		return "-";
					                	}
					                }*/
								},
																{
									field : 'updateDate', 
									title : '最后更新时间' 
								},
																{
									field : 'updateUserDo', 
									title : '最后更新人' ,
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
										var s ;
										if(row.isRelease == '0'){
											var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="'+ prefix + '/edit/' + row.id +'" mce_href="#" title="编辑" ><i class="fa fa-edit"></i></a> ';
											var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
													+ row.id
													+ '\')"><i class="fa fa-remove"></i></a> ';
											var f = '<a class="btn btn-success btn-sm  '+s_edit_h+'" href="#" title="发布"  mce_href="#" onclick="release(\''
												+ row.id
												+ '\', \'' + row.isRelease+ '\')"><i class="fa fa-eye"></i></a> ';
											s = e + d + f;
										}else if(row.isRelease == '1'){
											var g = '<a class="btn btn-success btn-sm  '+s_edit_h+'" href="#" title="取消发布"  mce_href="#" onclick="release(\''
												+ row.id
												+ '\', \'' + row.isRelease+ '\')"><i class="fa fa-eye-slash"></i></a> ';
											s = g;
										}
										
										return s;
									}
								} ]
					});
}
function reLoad() {
	$('#advertTable').bootstrapTable('refresh');
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
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {/*title : '删除提示',*/
		btn : [ '确定', '取消' ] 
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					reLoad();
				}else{
					layer.msg(r.msg);
				}
			}
		});
	})
}

function release(id, isRelease) {
	var str = "确定要取消发布选中的广告？";
	if('0' == isRelease){
		str = '确定要发布选中的广告？';
	}
	layer.confirm( str , {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/release",
			type : "post",
			data : {
				'id' : id,
				'isRelease' : isRelease
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					reLoad();
				}else{
					layer.msg(r.msg);
				}
			}
		});
	})
}

function add(){
	window.location.href = prefix + '/add';
}
