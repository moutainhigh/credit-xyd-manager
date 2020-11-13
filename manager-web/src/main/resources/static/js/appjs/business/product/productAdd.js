var prefix = "/business/product";

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

	
	  $("#finEntId").change(function(){
		  var insId = $("#finEntId option:selected").val();
		  //根据机构获取机构logo
		  if(insId!=null&&insId!=''){
			  $("#finEntIdHint").html("");
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
	  
	 /* $("#weixin").click(function(){
			$("#erweima").show();
			$(".kehujingli").hide();
		});
	  $("#zhidian").click(function(){
			$("#erweima").hide();
			$(".kehujingli").show();
	  });*/
	  $('input[type=radio][name=applicationType]').change(function() {
	        if (this.value == '1') { 
	        	$("#erweima").show();
				$(".kehujingli").hide(); 
	        } else if (this.value == '2') { 
	        	$("#erweima").hide();
				$(".kehujingli").show();
	        }else{
	        	$("#erweima").hide();
				$(".kehujingli").hide();
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
		if(getStringLength(productName)>30){
			$("#productNameHint").html("不超过30字符/15个中文字");
			$("#productNameHint").css("color","red");
			return false;
		}
	}
	//productType 产品类型
	var productType = $('input[name="productType"]:checked').val();
	if(productType==null||productType==''){
		$("#productTypeHint").html("请选择产品类型");
		$("#productTypeHint").css("color","red");
		return false;
	}
	//finEntId 所属金融机构
	var finEntId = $("#finEntId option:selected").val();
	if(finEntId==null||finEntId==''){
		$("#finEntIdHint").html("请选择金融机构");
		$("#finEntIdHint").css("color","red");
		return false;
	}
	//产品要素
	 //minAvailable 参考最低可贷额度
	var minAvailable = $("#minAvailable").val();
	if(minAvailable==null||minAvailable==''){
		$("#minAvailableHint").html("请输入大于0的整数");
		$("#minAvailableHint").css("color","red");
		return false;
	}
	//maxAvailable 参考最高可贷额度
	var maxAvailable = $("#maxAvailable").val();
	if(maxAvailable==null||maxAvailable==''){
		$("#maxAvailableHint").html("请输入参考最高可贷额度");
		$("#maxAvailableHint").css("color","red");
		return false;
	}else{
		if(eval(maxAvailable)<eval(minAvailable)){
			$("#maxAvailableHint").html("最高可贷额度不可小于参考最低可贷额度");
			$("#maxAvailableHint").css("color","red");
			return false;
		}
	}
	//minRate 参考最低贷款利率
	var minRate = $("#minRate").val();
	if(minRate==null||minRate==''){
		$("#minRateHint").html("请输入参考最低贷款利率");
		$("#minRateHint").css("color","red");
		return false;
	}
	//minRate 参考最高贷款利率
	var maxRate = $("#maxRate").val();
	if(maxRate==null||maxRate==''){
		$("#maxRateHint").html("请输入参考最高贷款利率");
		$("#maxRateHint").css("color","red");
		return false;
	}else{
		if(eval(maxRate)<eval(minRate)){
			$("#maxRateHint").html("参考最高贷款利率不可小于最低贷款利率");
			$("#maxRateHint").css("color","red");
			return false;
		}
	}
	var rateDescribing = $("#rateDescribing").val();
	if(rateDescribing!=null&&rateDescribing!=''){
		if(getStringLength(rateDescribing)>20){
			$("#rateDescribingHint").html("不超过20字符/10个中文字");
			$("#rateDescribingHint").css("color","red");
			return false;
		}
	}
	//最短贷款期限前台是否展示 1:不展示
	var minLoanTermShow = null;
	$('input[name="minLoanTermShow"]:checked').each(function(){
		minLoanTermShow = $(this).val();
	});
	//参考最高贷款利率前台是否展示 1:不展示
	var maxRateShow = null;
	$('input[name="maxRateShow"]:checked').each(function(){
		maxRateShow = $(this).val();
	});
	//minLoanTerm 最短贷款期限
	var minLoanTerm = $("#minLoanTerm").val();
	if(minLoanTerm==null||minLoanTerm==''){
		$("#minLoanTermHint").html("请输入最短贷款期限");
		$("#minLoanTermHint").css("color","red");
		return false;
	}
	//minLoanTerm 最长贷款期限
	var maxLoanTerm = $("#maxLoanTerm").val();
	if(maxLoanTerm==null||maxLoanTerm==''){
		$("#maxLoanTermHint").html("请输入最长贷款期限");
		$("#maxLoanTermHint").css("color","red");
		return false;
	}else{
		if(eval(maxLoanTermHint)<eval(minLoanTerm)){
			$("#maxRateHint").html("最长贷款期限不可小于最短贷款期限");
			$("#maxRateHint").css("color","red");
			return false;
		}
	}
	//area 适用地区
	var area = $("#area option:selected").val();
	if(area==null||area==''){
		$("#areaHint").html("请选择产品适用地区");
		$("#areaHint").css("color","red");
		return false;
	}
	//quickRelease 最快放款简述
	var quickRelease = $("#quickRelease").val().replace(/(^\s*)|(\s*$)/g, "");
	if(quickRelease==null||quickRelease==''){
		$("#quickReleaseHint").html("请输入最快放款简述");
		$("#quickReleaseHint").css("color","red");
		return false;
	}else{
		if(getStringLength(quickRelease)>10){
			$("#quickReleaseHint").html("不超过10个字符/5个中文字");
			$("#quickReleaseHint").css("color","red");
			return false;
		}
	}
	//repaymentMethod 还款方式
	//var repaymentMethod = $("#repaymentMethod option:selected").val();
	var repaymentMethodStr = [];
	$('input[name="repaymentMethod"]:checked').each(function(){
		repaymentMethodStr.push($(this).val());
		//repaymentMethod+=$(this).val()+",";
	});
	var repaymentMethod = repaymentMethodStr.join(",");
	if(repaymentMethod==null||repaymentMethod==''){
		$("#repaymentMethodHint").html("请选择还款方式");
		$("#repaymentMethodHint").css("color","red");
		return false;
	}
	//guaranteeMode 担保方式
	var guaranteeModeStr = [];
	$('input[name="guaranteeMode"]:checked').each(function(){
		guaranteeModeStr.push($(this).val());
	});
	//var guaranteeMode = $("#guaranteeMode option:selected").val();
	var guaranteeMode = guaranteeModeStr.join(",");
	if(guaranteeMode==null||guaranteeMode==''){
		$("#guaranteeModeHint").html("请选择担保方式");
		$("#guaranteeModeHint").css("color","red");
		return false;
	}
	//approvalType 产品审批类型
	var approvalType = $('input[name="approvalType"]:checked').val();
	if(approvalType==null||approvalType==''){
		$("#approvalTypeHint").html("请选择产品审批类型");
		$("#approvalTypeHint").css("color","red");
		return false;
	}
	//----------缩略图概要简介
	//firstLine第一行简介
	var firstLine = $("#firstLine").val();
	if(firstLine==null||firstLine==''){
		$("#firstLineHint").html("请输入第一行简介");
		$("#firstLineHint").css("color","red");
		return false;
	}else{
		if(getStringLength(firstLine)>32){
			$("#firstLineHint").html("不超过32字符/16个中文字");
			$("#firstLineHint").css("color","red");
			return false;
		}
	}
	//secondLine第二行简介
	var secondLine = $("#secondLine").val();
	if(secondLine!=null&&secondLine!=''&&getStringLength(secondLine)>32){
		$("#secondLineHint").html("不超过32字符/16个中文字");
		$("#secondLineHint").css("color","red");
		return false;
	}
	//thirdLine 第三行简介
	var thirdLine = $("#thirdLine").val();
	if(thirdLine!=null&&thirdLine!=''&&getStringLength(thirdLine)>32){
		$("#thirdLineHint").html("不超过32字符/16个中文字");
		$("#thirdLineHint").css("color","red");
		return false;
	}
	//----------产品亮点关键词
	//keywordsOne亮点关键词1
	var keywordsOne = $("#keywordsOne").val();
	if(keywordsOne==null||keywordsOne==''){
		$("#keywordsOneHint").html("请输入亮点关键词1");
		$("#keywordsOneHint").css("color","red");
		return false;
	}else{
		if(getStringLength(keywordsOne)>8){
			$("#keywordsOneHint").html("不超过8个字符/4个中文字");
			$("#keywordsOneHint").css("color","red");
			return false;
		}
	}
	//keywordsTwo亮点关键词2
	var keywordsTwo = $("#keywordsTwo").val();
	if(keywordsTwo!=null&&keywordsTwo!=''&&getStringLength(keywordsTwo)>8){
		$("#keywordsTwoHint").html("不超过8个字符/4个中文字");
		$("#keywordsTwoHint").css("color","red");
		return false;
	}
	//keywordsThree 亮点关键词3
	var keywordsThree = $("#keywordsThree").val();
	if(keywordsThree!=null&&keywordsThree!=''&&getStringLength(keywordsThree)>32){
		$("#keywordsThreeHint").html("不超过8个字符/4个中文字");
		$("#keywordsThreeHint").css("color","red");
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
	var prductDetailsHint = false;
	var productInfoKey = "";
	var productInfoValue = "";
	if($(".prductDetailsDiv").length==0){
		$("#productDet").html("请输入产品详情介绍");
		$("#productDet").css("color","red");
		return false;
	}
	$(".prductDetailsDiv").each(function(i){
		var productInfo = new Object();
		productInfoKey = $(this).find(".keyElementsKey").val();
		productInfoValue = $(this).find("iframe").contents().find("body").html();
		if(productInfoKey==null||productInfoKey==''){
			$(this).find(".hint-font").html("请输入标题");
			$(this).find(".hint-font").css("color","red");
			prductDetailsHint = false;
		}else{
			productInfo.productTitle = productInfoKey;
			productInfo.info = productInfoValue;
			productInfo.sort = i;
			productInfoData.push(productInfo);
			productInfoKey = "";
			productInfoValue = "";
			prductDetailsHint = true;
		}
	});
	
	if(!prductDetailsHint){
		/*$("#productDet").html("请输入产品详情介绍");
		$("#productDet").css("color","red");*/
		return false;
	}
	//------申请方案配置
	//applicationType 申请类型
	var applicationType = $('input[name="applicationType"]:checked').val();
	if(applicationType==null||applicationType==''){
		$("#applicationTypeHint").html("请选择申请类型");
		$("#applicationTypeHint").css("color","red");
		return false;
	}
	//根据申请类型 取二维码或者联系人理信息
	var qrFile = $("#advertFile").val();//二维码
	if(applicationType=='1'){
		if(qrFile == '' || qrFile.indexOf('data:image') != 0){
			$("#qrFileHint").html("请上传二维码");
			$("#qrFileHint").css("color","red");
			return false;
		}
	}
	var contactName = $("#contactName").val();//联系人姓名
	var contactInf = $("#contactInf").val();//联系人电话
	if(applicationType=='2'){
		if(contactName == '' ||contactName==null){
			$("#contactNameHint").html("请输入联系人姓名");
			$("#contactNameHint").css("color","red");
			return false;
		}
		if(contactInf == '' ||contactInf==null){
			$("#contactInfHint").html("请输入联系人电话");
			$("#contactInfHint").css("color","red");
			return false;
		}
	}
	
	layer.load();
	var id = $("#productId").val();
	//开始保存
	var params = {
			id:id,
			productNo:productNo,
            productName:productName,
            productType:productType,
            finEntId:finEntId,
            minAvailable:minAvailable,
            maxAvailable:maxAvailable,
            minRate:minRate,
            maxRate:maxRate,
            minLoanTerm:minLoanTerm,
            maxLoanTerm:maxLoanTerm,
            area:area,
            quickRelease:quickRelease,
            repaymentMethod:repaymentMethod,
            guaranteeMode:guaranteeMode,
            approvalType:approvalType,
            firstLine:firstLine,
            secondLine:secondLine,
            thirdLine:thirdLine,
            keywordsOne:keywordsOne,
            keywordsTwo:keywordsTwo,
            keywordsThree:keywordsThree,
            sector:sector,
           // infoStr:JSON.stringify(productInfoData),
            applicationType:applicationType,
            qrFile:qrFile,
            contactName:contactName,
            contactInf:contactInf,
            minLoanTermShow:minLoanTermShow,
            maxRateShow:maxRateShow,
            rateDescribing:rateDescribing
   };
	/*$("body").append("<form></form>");
    $("body").find("form").attr("action", prefix+'/addProduct');
    $("body").find("form").attr("method", "post");
    $("body").find("form").attr("style", "display:none");
    $.each(params, function (k, v) {
        $("body").find("form").append("<input type='text' name='" + k + "' value = '" + v + "'></input>");
    });
    $("body").find("form").submit();
    */
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