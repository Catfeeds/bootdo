$().ready(function() {

	$('.summernote').summernote({
		height : '220px',
		lang : 'zh-CN',
		callbacks: {
            onImageUpload: function(files, editor, $editable) {
                sendFile(files);
            }
        }
	});
	validateRule();
});


$.validator.setDefaults({
	submitHandler : function() {
		save(0);
	}
});
function save(status) {
	$("#status").val(status);
	$.ajax({
		cache : true,
		type : "POST",
		url : "/blog/partner/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(r) {
			if (r.code == 0) {
				parent.layer.msg(r.msg);
				parent.reLoad();
				$("#id").val(r.id);
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);
			} else {
				parent.layer.alert(r.msg)
			}
		}
	});
}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			siteName : "required",
			siteUrl : "required",
			siteDesc : "required",
			sort : "required"
		},
		messages : {
			siteName : "请填写站点名称",
			siteUrl : "请填写站点地址",
			siteDesc : "请填写站点描述",
			sort : "请填写排序"
		}
	});
}

function returnList() {
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
	parent.layer.close(index);
}

