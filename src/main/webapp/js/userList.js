//扩展dateTimeBox
$.extend($.fn.datagrid.defaults.editors, {
    datetimebox : {
        init: function(container, options){
            var input = $('<input type="text">').appendTo(container);
            options.editable = false;
            input.datetimebox(options);
            return input;
        },
        getValue: function(target){
            return $(target).datetimebox('getValue');
        },
        setValue: function(target, value){
            $(target).datetimebox('setValue', value);
        },
        resize: function(target, width){
            $(target).datetimebox('resize', width);
        },
        destroy : function (target) {
            $(target).datetimebox('destroy');
        },
    }
});


$(function(){

    var ctx = $('#ctx').val();

    obj_user = {
        editRow : undefined,
        /**
         * 查询按钮点击后执行
         * @type {{search: Function}}
         */
        search : function(){
            $('#table-user').datagrid('load',{
                'queryParameter.username' : $.trim($('#username').val()),
                'queryParameter.date_from' : $('#date_from').datebox('getValue'),
                'queryParameter.date_to' :  $('#date_to').datebox('getValue'),
            });
        },

        /**
         * 新增按钮点击后执行
         */

        add : function(){
            $('#save-user,#redo-user').show();
            //新增一行
            if(this.editRow == undefined){
                $('#table-user').datagrid('insertRow',{
                    index : 0,
                    row : {
                        date : new Date(),
                        salt : new Date().getMilliseconds() * parseInt(Math.random()*10),
                    },
                });

                //将新增的行变成可编辑状态
                $('#table-user').datagrid('beginEdit',0);
                this.editRow = 0;
            }

        },
        save : function(){
            //将新增的行变成结束编辑状态
            $('#table-user').datagrid('endEdit',this.editRow);
        },
        redo : function () {
            $('#save-user,#redo-user').hide();
            this.editRow = undefined;
            $('#table-user').datagrid('rejectChanges');
        },
        edit : function () {
            var rows = $('#table-user').datagrid('getSelections');
            if(rows.length == 1){
                if(this.editRow != undefined){
                    $('#table-user').datagrid('endEdit',this.editRow);
                }
                if(this.editRow == undefined){
                    var index = $('#table-user').datagrid('getRowIndex',rows[0]);
                    $('#save-user,#redo-user').show();
                    $('#table-user').datagrid('beginEdit',index);
                    this.editRow = index;
                    $('#table-user').datagrid('unselectRow',index);
                }
            }else{
                $.messager.alert('警告','只允许同时修改一行!','warning');
            }
        },
        remove : function(){
            var rows = $('#table-user').datagrid('getSelections');
            if(rows.length > 0){
                $.messager.confirm('确定操作','确定要删除所选吗？',function(flag){
                    if(flag){
                        var ids = [];
                        for(var i = 0; i < rows.length; i++){
                            ids.push(rows[i].id);
                        }
                        //与后台交互删除数据
                        $.ajax({
                            url : '/user/deleteUser.action',
                            type : 'POST',
                            data : {
                                ids : ids.join(','),
                            },
                            beforeSend : function () {
                                $('#table-user').datagrid('loading');
                            },
                            success : function (data) {
                                if(data){
                                    $('#table-user').datagrid('loaded');
                                    $('#table-user').datagrid('reload');
                                    $('#table-user').datagrid('unselectAll');
                                    $.messager.show({
                                        title : '提示',
                                        msg : '删除成功!',
                                    });
                                }
                            },
                        });
                    }
                });
            }else{
                $.messager.alert('提示','没有选择要删除的行!','info');
            }
        },
    };

    /**
     * 清除按钮点击后执行
     */
    $('#clean').on('click',function(){
        $('#username').val('');
        $('#date_from').datebox('setValue','');
        $('#date_to').datebox('setValue','');
    });


    $('#table-user').datagrid({
        width : 650,
        title : '用户列表',
        url : '/user/getUserList.action',
        //iconCls : 'icon-search',
        striped : true,
        fit : true,
        rownumbers : true,
        loadMsg : '努力加载中。。。',
        fitColumns : true,
        columns : [[
            {
                field : 'id',
                title : '编号',
                sortable : true,
                align : 'center',
                width : 50,
                checkbox : true,
            },
            {
                field : 'username',
                title : '账号',
                sortable : true,
                align : 'center',
                width : 100,
                editor : {
                    type : 'validatebox',
                    options : {
                        required : true,
                    },
                },
            },
            {
                field : 'password',
                title : '密码',
                sortable : true,
                align : 'center',
                width : 100,
                formatter : function(value){
                    return value;
                },
                editor : {
                    type : 'validatebox',
                    options : {
                        required : true,
                    },
                },
            },
            {
                field : 'date',
                title : '注册日期',
                sortable : true,
                align : 'center',
                width : 150,
                formatter : function(value){
                    return toDateString(value);
                },
            },
            {
                field : 'salt',
                title : '盐值',
                sortable : true,
                align : 'center',
                width : 100,
                /*editor : {
                 type : 'validatebox',
                 options : {
                 required : true,
                 },
                 },*/
            },
            {
                field : 'locked',
                title : '是否锁定',
                align : 'center',
                width : 100,
                formatter : function (value) {
                    if(value == 'true'){
                        value = '<input type="checkbox" checked> ';
                    }else{
                        value = '<input type="checkbox">';
                    }
                    return value;
                },
                editor : {
                    type : 'checkbox',
                    options : {
                        on: "true",
                        off: "false"
                    },
                },
            }
        ]],
        toolbar : '#tb',
        pagination : true,
        pageSize : 10,
        pageList : [5,10,15],
        sortName : 'id',
        sortOrder : 'DESC',
        remoteSort : false,
        onDblClickRow : function(rowIndex, rowData){
            if(obj_user.editRow != undefined){
                $('#table-user').datagrid('endEdit',obj_user.editRow);
            }
            if(obj_user.editRow == undefined){
                $('#save-user,#redo-user').show();
                obj_user.editRow = rowIndex;
                $('#table-user').datagrid('beginEdit',rowIndex);
            }
        },
        onAfterEdit : function (index, rowData, change) {
            $('#save-user,#redo-user').hide();
            var inserted = $('#table-user').datagrid('getChanges','inserted');
            var updated = $('#table-user').datagrid('getChanges','updated');

            //新增用户
            if(inserted.length > 0){
                $.ajax({
                    url : '/user/insertUser.action',
                    type : 'POST',
                    data : {
                        'queryParameter.row' : rowData,
                    },
                    beforeSend : function () {
                        $('#table-user').datagrid('loading');
                    },
                    success : function (data) {
                        if(data == 'success'){
                            $('#table-user').datagrid('loaded');
                            $('#table-user').datagrid('load');
                            $('#table-user').datagrid('unselectAll');
                            $.messager.show({
                                title : '提示',
                                msg : '新增成功!',
                            });
                            obj_user.editRow = undefined;
                        }else{
                            $('#table-user').datagrid('loaded');
                            $('#table-user').datagrid('load');
                            $('#table-user').datagrid('unselectAll');
                            $.messager.alert("提示",data,"info");
                        }
                    },
                    error : function(data,value){
                    },
                });
            }

            //修改用户
            if(updated.length > 0){
                $.ajax({
                    url : '/user/updateUser.action',
                    type : 'POST',
                    data : {
                        'queryParameter.row' : rowData,
                    },
                    beforeSend : function () {
                        $('#table-user').datagrid('loading');
                    },
                    success : function (data) {
                        if(data == 'success'){
                            $('#table-user').datagrid('loaded');
                            $('#table-user').datagrid('reload');
                            $('#table-user').datagrid('unselectAll');
                            $.messager.show({
                                title : '提示',
                                msg : '修改成功!',
                            });
                        }
                        obj_user.editRow = undefined;
                    },
                });
            }

            if(inserted.length == 0 && updated.length == 0){
                obj_user.editRow = undefined;
            }
        },

    });

    $('#table-user').datagrid("resize");
});

