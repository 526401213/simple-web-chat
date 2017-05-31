/**
 * Created by KevinBlandy on 2017/5/24 13:23
 */

Date.prototype.now = function(){
	var year = this.getFullYear();
	var month = this.getMonth() + 1;	
	if(month < 10){
		month = "0" + month;
	}
	var dayOfMonth = this.getDate();
	if(dayOfMonth < 10){
		dayOfMonth = "0" + dayOfMonth;
	}
	var hours = this.getHours();		
	if(hours < 10){
		hours = "0" + hours;
	}
	var minutes = this.getMinutes();	
	if(minutes < 10){
		minutes = "0" + minutes;
	}
	var seconds = this.getSeconds();
	if(seconds < 10){
		seconds = "0" + seconds;
	}
	return year + "年" + month + "月" + dayOfMonth + "日 " + hours + ":" + minutes + ":" + seconds;
}

$.extend($.fn.validatebox.defaults.rules, {
    minWithMaxLength : {
        validator : function(value, param) {
            return value.length >= param[0] && value.length <= param[1];
        },
        message : '该字段长度只能是{0}-{1}位'
    },
    english : {
		validator : function(value) {
			return /^[A-Za-z]+$/i.test(value);
		},
		message : '请输入英文'
	},
	noSpaces:{
		validator : function(value) {
			return value.indexOf(" ") == -1;
		},
		message : '不能有空格'
	}
});

CT = {

    getRootPath : function (){
        var pathName = window.document.location.pathname;
        var projectName = pathName.substring(0,pathName.substr(1).indexOf('/') + 1);
        return projectName;
    },

    /**
     * @param options
     */
    ajax : function(options) {
    	if(!options.async){
    		$.messager.progress({text : '执行中....'});
    	}
        $.ajax({
            url : options.url,
            type : options.method || 'POST',
            dateType : 'JSON',
            data : options.data,
            success : function(response) {
                $.messager.progress('close');
                if (response.success) {
                    // OK
                    options.success && options.success(response);
                } else {
                    options.error && options.error(response);
                    CT.showAlert(response.message);
                }
            },
            error : CT.onError
        })
    },
    /**
     * 表单校验
     * @param form
     * @returns {*|{js}}
     */
    validateForm : function(form) {
        if (!form.form('validate')) {
            this.showMessage("参数校验失败,请检查");
            return false;
        }
        return true;
    },

    /**
     * 异常
     */
    onError : function(response) {
        $.messager.progress('close');
        CT.showAlert("网络异常");
    },

    /**
     * 弹窗
     */
    showAlert : function(message, type, title) {
        type = type || "warning";
        title = title || "提示";
        $.messager.alert(title, message, type);
    },
    /**
     * 右下角提示
     */
    showMessage : function(message) {
        $.messager.show({
            title : '提示',
            msg : message
        });
    },
    /**
     * 回调
     */
    showConfirm : function(message,callback){
        $.messager.confirm("警告",message,function(r){
            if(r){
                callback && callback();
            }
        });
    },
}
