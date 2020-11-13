var prefix = "/business/creditProduct";

$(function(){
	btnNoUse();
	/* 删除模板  */
	$('.showDetailWrap').on('click','.moveDetele',function(){
		$(this).parents('.showDetail').remove();
		btnNoUse();
	})
	 /* 上移模板 */
	 $('.showDetailWrap').on('click','.moveUp',function(){
		if($(this).parents('.showDetail').prevAll().length>0){
			/*var text = $(this).parents('.showDetail').find("iframe").contents().find("body").html();
			$(this).parents('.showDetail').insertBefore($(this).parents('.showDetail').prev());
			$(this).parents('.showDetail').find("iframe").contents().find("body").html(text);*/
			//当前点击目标的文本框内容
			var text = $(this).parents('.showDetail').find("iframe").contents().find("body").html();
			//点击的上移目标的标题内容
			var title = $(this).parents('.showDetail').find(".keyElementsKey").val();
			
			//上一个目标的文本框内容
			var text2 = $(this).parents('.showDetail').prev().find("iframe").contents().find("body").html();
			//上一个目标的标题内容
			var title2 = $(this).parents('.showDetail').prev().find(".keyElementsKey").val();
			//清空当前点击的富文本框和标题的内容，重置为上一个目标的文本框内容
			$(this).parents('.showDetail').find("iframe").contents().find("body").empty();
			$(this).parents('.showDetail').find("iframe").contents().find("body").html(text2);
			$(this).parents('.showDetail').find(".keyElementsKey").val(title2);
			//清空上一个的富文本框和标题的内容，重置为当前点击的文本框内容
			$(this).parents('.showDetail').prev().find("iframe").contents().find("body").empty();
			$(this).parents('.showDetail').prev().find("iframe").contents().find("body").html(text);
			$(this).parents('.showDetail').prev().find(".keyElementsKey").val(title);
		}
		editor.readonly(false);
		btnNoUse();
	})
	 /* 下移模板 */
	 $('.showDetailWrap').on('click','.moveDown',function(){
		 if($(this).parents('.showDetail').nextAll().length>0){
			 /*var text = $(this).parents('.showDetail').find("iframe").contents().find("body").html();
			 $(this).parents('.showDetail').insertAfter($(this).parents('.showDetail').next());
			 $(this).parents('.showDetail').find("iframe").contents().find("body").html(text);*/
			//当前点击目标的文本框内容
				var text = $(this).parents('.showDetail').find("iframe").contents().find("body").html();
				//点击的上移目标的标题内容
				var title = $(this).parents('.showDetail').find(".keyElementsKey").val();
				
				//下一个目标的文本框内容
				var text2 = $(this).parents('.showDetail').next().find("iframe").contents().find("body").html();
				//下一个目标的标题内容
				var title2 = $(this).parents('.showDetail').next().find(".keyElementsKey").val();
			 	
			 	//清空当前点击的富文本框和标题的内容，重置为上一个目标的文本框内容
				$(this).parents('.showDetail').find("iframe").contents().find("body").empty();
				$(this).parents('.showDetail').find("iframe").contents().find("body").html(text2);
				$(this).parents('.showDetail').find(".keyElementsKey").val(title2);
				//清空上一个的富文本框和标题的内容，重置为当前点击的文本框内容
				$(this).parents('.showDetail').next().find("iframe").contents().find("body").empty();
				$(this).parents('.showDetail').next().find("iframe").contents().find("body").html(text);
				$(this).parents('.showDetail').next().find(".keyElementsKey").val(title);
		}
		 btnNoUse();
	})
	/* 添加模板 */
	$('.addPlate').click(function(){
		var ctx = $("#ctx").val();
		var html='';
		 html = '<div class="showDetail prductDetailsDiv"><p><span>标题</span>'+
					'<input type="text" value="" class="keyElementsKey" placeholder="请输入标题">'+
					'<span class="productMove">'+
					'<span class="moveUp">上移</span>'+
					'<span class="moveDown">下移</span>'+
					'<span class="moveDetele">删除</span></span></p>'+
					'<textarea id="contentTag" class="detailMain" style="width:99.9%;height:300px;"></textarea>'+
					'<div style="clear:both;"></div></div>'
		$('.showDetailWrap').append(html);
		 btnNoUse();
		 kindEditor(ctx);
	});
	$("input").focus(function(){
		$(".hint-font").html("");
	});

	
	  $("#institutionId").change(function(){
		  var insId = $("#institutionId option:selected").val();
		  //根据机构获取机构logo
		  if(insId!=null&&insId!=''){
			  $("#institutionIdHint").html("");
			  $.ajax({
					url : prefix+'/getInsLogo',
					data : {finEntId:insId},
					success : function(data) {
						if(data!=null&&data.file!=null&&data.file!=''){
							/*var html = '<img th:src="'+data.file+'"/>';*/
							$("#inslogo").attr("src",data.file);
						}
					}
				});
		  }else{
			  $("#inslogo").attr("src","");
		  }
	  });
 });


function kindEditor(){
	KindEditor.options.filterMode = false;
	KindEditor.ready(function(K) {
	    window.editor = K.create('.detailMain',{
         uploadJson : '/business/editor/imgUpload',//post提交图片的地址，在后台按照往常的上传函数接受即可。
         allowFileManager : true,
         items:["undo", "redo", "|", "preview", "template", "code", 
	               "cut", "copy", "paste", "plainpaste", "wordpaste","|", "justifyleft", "justifycenter",
	               "justifyright", "justifyfull", "insertorderedlist", "insertunorderedlist", "indent", 
	               "outdent", "subscript", "superscript", "clearhtml", "quickformat", "selectall", "|", 
	               "fullscreen", "/", "formatblock", "fontname", "fontsize", "|", "forecolor", "hilitecolor", 
	               "bold", "italic", "underline", "strikethrough", "lineheight", "removeformat", "|", "image", 
	               "table", "hr", "emoticons", "baidumap", "pagebreak", 
	               "anchor", "link", "unlink", "|", "about"],
         afterUpload:function(data){
         },
         afterError : function(str) {
             alert('自定义错误信息: ' + str);
         }
     });
	});
}

/*模板按钮禁用  */
function btnNoUse(){
	$('.showDetailWrap .moveUp,.showDetailWrap .moveDown').css('background','#fff');
	$('.showDetailWrap .moveUp:first').css('background','#ccc');
	$('.showDetailWrap .moveDown:last').css('background','#ccc');
}

function goBack(){
	var uRef = document.referrer;
	window.location.href=uRef;
}

function productSave(type){
	var productNo = $("#productNo").val();
	//productName 产品名称
	var productName = $("#productName").val().replace(/(^\s*)|(\s*$)/g, "");
	if(productName==null||productName==''){
		$("#productNameHint").html("请输入产品名称");
		$("#productNameHint").css("color","red");
		return false;
	}else{
		if(getStringLength(productName)>20){
			$("#productNameHint").html("不超过20字符/10个中文字");
			$("#productNameHint").css("color","red");
			return false;
		}
	}
	//institutionId 所属信用机构
	var institutionId = $("#institutionId option:selected").val();
	if(institutionId==null||institutionId==''){
		$("#institutionIdHint").html("请选择信用机构");
		$("#institutionIdHint").css("color","red");
		return false;
	}
	//productInfo 产品概述
	var productInfo = $("#productInfo").val();
	if(productInfo==null||productInfo==''){
		$("#productInfoHint").html("请输入产品概述");
		$("#productInfoHint").css("color","red");
		return false;
	}
	
	//----------产品亮点关键词
	//keywordsOne亮点关键词1
	var keywordOne = $("#keywordOne").val();
	if(keywordOne==null||keywordOne==''){
		$("#keywordOneHint").html("请输入亮点关键词1");
		$("#keywordOneHint").css("color","red");
		return false;
	}else{
		if(getStringLength(keywordOne)>8){
			$("#keywordOneHint").html("不超过8个字符/4个中文字");
			$("#keywordOneHint").css("color","red");
			return false;
		}
	}
	//keywordsTwo亮点关键词2
	var keywordTwo = $("#keywordTwo").val();
	if(keywordTwo!=null&&keywordTwo!=''&&getStringLength(keywordTwo)>8){
		$("#keywordTwoHint").html("不超过8个字符/4个中文字");
		$("#keywordTwoHint").css("color","red");
		return false;
	}
	//keywordsThree 亮点关键词3
	var keywordThree = $("#keywordThree").val();
	if(keywordThree!=null&&keywordThree!=''&&getStringLength(keywordThree)>32){
		$("#keywordThreeHint").html("不超过8个字符/4个中文字");
		$("#keywordThreeHint").css("color","red");
		return false;
	}
	//sector 产品所属板块
	var sectorStr = [];
	$('input[name="sector"]:checked').each(function(){
		//sector+=$(this).val()+",";
		sectorStr.push($(this).val());
	});
	var sector = sectorStr.join(",");
	//var sector = $('input[name="sector"]:checked').val();
	if(sector==null||sector==''){
		$("#sectorHint").html("请选择产品所属板块");
		$("#sectorHint").css("color","red");
		return false;
	}
	//------------产品详情介绍
	//产品详情介绍
	var productInfoData = [];
	var prductDetailsHint = true;
	var productInfoKey = "";
	var productInfoValue = "";
	$(".prductDetailsDiv").each(function(i){
		var productInfoNew = new Object();
		productInfoKey = $(this).find(".keyElementsKey").val();
		productInfoValue = $(this).find("iframe").contents().find("body").html();
		if(productInfoKey==null||productInfoKey==''){
			$(this).find(".hint-font").html("请输入标题");
			prductDetailsHint = false;
		}else{
			productInfoNew.creditProductTitle = productInfoKey;
			productInfoNew.creditProductInfo = productInfoValue;
			productInfoNew.sort = i;
			productInfoData.push(productInfoNew);
			productInfoKey = "";
			productInfoValue = "";
		}
	});
	
	if(!prductDetailsHint){
		return false;
	}
	
	layer.load();
	var id = $("#productId").val();
	//开始保存
	var params = {
			id:id,
			productNo:productNo,
            productName:productName,
            institutionId:institutionId,
            productInfo:productInfo,
            keywordOne:keywordOne,
            keywordTwo:keywordTwo,
            keywordThree:keywordThree,
            sector:sector,
            infoStr:JSON.stringify(productInfoData)
   };
	/*$("body").append("<form></form>");
    $("body").find("form").attr("action", prefix+'/addProduct');
    $("body").find("form").attr("method", "post");
    $("body").find("form").attr("style", "display:none");
    $.each(params, function (k, v) {
        $("body").find("form").append("<input type='text' name='" + k + "' value = '" + v + "'></input>");
    });
    $("body").find("form").submit();*/
	$.ajax({
 	     type: "post",
 	     data:{productstr:JSON.stringify(params),infoStr:JSON.stringify(productInfoData)},
	     url: prefix+'/addProduct',
	  	 async:false,
	     success: function(data){
	    	 layer.msg("操作成功");
	    	 window.location.href= prefix+"/details/"+data+"/1"; 
	    	 if(type=='3'){
	    		 var tempwindow=window.open('_blank');
	    		 tempwindow.location= prefix+"/details/"+data+"/"+type;
	    	 }
       },
       error : function(request) {
			layer.alert("操作失败！");
		}
});
}



function getStringLength(str){  
	  var slength=0;  
	  for(i=0;i<str.length;i++){  
	   if ((str.charCodeAt(i)>=0) && (str.charCodeAt(i)<=255))  
	     slength=slength+1;  
	   else  
	     slength=slength+2;  
	  }   
	  return slength;  
	}; 