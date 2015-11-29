/**
 * Created by 朱翔 on 2015/11/28.
 */
$(function () {

    var ctx = $('#ctx').val();

    //登录界面
    $('#login').dialog({
        title : '系统登录',
        width : 300,
        height : 180,
        modal : true,
        iconCls : 'icon-login',
        buttons : '#btn',
    });

    //验证账号
    $('#manager').validatebox({
        required : true,
        missingMessage : '请输入账号',
        invalidMessage : '账号不能为空',
    });

    //验证账号密码
    $('#password').validatebox({
        required : true,
        validType : 'length[6,30]',
        missingMessage : '请输入密码',
        invalidMessage : '密码不能少于6位大于30位',
    });

    //加载时验证
    if(!$('#manager').validatebox('isValid')){
        $('#manager').focus();
    }else if(!$('#password').validatebox('isValid')){
        $('#password').focus();
    }


    //点击登录
    $('#btn a').on('click',function(){
        if(!$('#manager').validatebox('isValid')){
            $('#manager').focus();
        }else if(!$('#password').validatebox('isValid')){
            $('#password').focus();
        }else {
            $.ajax({
                url : ctx + '/login/login.action',
                type : 'POST',
                data : {
                    username : $.trim($('#manager').val()),
                    password : $('#password').val(),
                },
                beforeSend : function(){
                    $.messager.progress({
                        text : '正在登录中。。。',
                    });
                },
                success : function(data,response,status){
                    $.messager.progress('close');
                    if(data != null){
                        location.href =  ctx + '/login/home.action?username='+ data.username;
                    }else{
                        $.messager.alert('登录失败','用户名或密码错误','warning',function(){
                            $('#password').select();
                        });
                    }
                },
            });
        }
    });

});
