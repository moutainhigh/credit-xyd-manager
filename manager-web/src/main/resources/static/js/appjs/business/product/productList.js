var prefix = "/business/product"

$(function() {
		load();
});
	
	function selectLoad() {
		var html = "";
		$.ajax({
			url : '/common/dict/list/product_issue_state',
			success : function(data) {
				//加载数据
				html += '<option value="" selected>全部</option>';
				for (var i = 0; i < data.length; i++) {
					html += '<option value="' + data[i].value + '" ';
					//默认设置为待受理
					/* if( data[i].value == '1'){
						html += ' selected ';
					} */
					html += '">' + data[i].name + '</option>';
				}
			
				$(".chosen-select").append(html);
				$(".chosen-select").chosen({
					maxHeight : 200
				});
			}
		});
	}
	
	function load() {
		selectLoad();
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
									},
																	{
										title : '操作',
										field : 'id',
										align : 'center',
										formatter : function(value, row, index) {
											var xiangqing = '<a href="'+prefix + '/details/' + row.id+'/1">详情</a>&nbsp;&nbsp;';
											var bianji = '<a href="'+prefix + '/details/' + row.id+'/2">编辑</a>&nbsp;&nbsp;';
											var jieguo = xiangqing + bianji;
											if(row.isRelease=='0'){
												/*vaqubie = '<a href="'+prefix + '/details/' + row.id+'/4">发布</a>&nbsp;&nbsp;';*/
												vaqubie = '<a href="#" onclick="ifIssue(\''+ row.id+ '\')">发布</a>&nbsp;&nbsp;';
												del = '<a href="#" onclick="remove(\''+ row.productNo+ '\')">删除</a>';
												jieguo = jieguo + vaqubie + del; 
											}else if(row.isRelease=='1'){
												vaqubie = '<a href="#" onclick="recycleDes(\''+ row.productNo+ '\')">回收</a>';
												jieguo = jieguo + vaqubie;
											}/*else if(row.isRelease=='2'){
												//vaqubie = '<a href="#" onclick="recycleDes('+row.id+')">回收</a>';
												del = '<a href="#" onclick="remove(\''+ row.productNo+ '\')">删除</a>';
												jieguo = jieguo + del;
											}*/
											
											return jieguo;
										}
									} ]
						});
	}
	
	function reLoad() {
		$('#productListTable').bootstrapTable('refresh');
	}
	
	function ifIssue(id){
		var tempwindow=window.open('_blank');
		tempwindow.location= prefix+"/details/"+id+"/4";
	}
	
	function recycleDes(productNo) {
		layer.open({
			type : 2,
			title : '回收原因',
			maxmin : true,
			shadeClose : false, // 点击遮罩关闭层
			area : [ '400px', '300px' ],
			btn: ['确定', '取消'],
			content : prefix + '/recycleDes', // iframe的url
			success: function (layero, index) {},
			cancel: function (index) {},
			yes: function(index, layero){
				var body = layer.getChildFrame('body', index);
				console.log(body.html());
				console.log(body.find('#recycleDes').val());
				var recycleDes = body.find('#recycleDes').val();
				if(recycleDes.trim()==null||recycleDes.trim()==''){
					body.find('#hint').html("请输入回收原因！").show().delay(3000).hide(300);;
					return false;
				}
				$.ajax({
					url : prefix + '/yesRecycleDes',
					type : "post",
					data : {
						'productNo' : productNo,
						'recycleDes':recycleDes,
						'isRelease':"0"
					},
					success : function(r) {
						reLoad();
						layer.close(index);
					}
				});
			}
		});
	}
	
	function remove(productNo) {
		layer.confirm('确定要删除选中的记录？', {
			btn : [ '确定', '取消' ]
		}, function() {
			$.ajax({
				url : prefix + "/remove",
				type : "post",
				data : {
					'productNo' : productNo
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