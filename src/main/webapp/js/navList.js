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

    obj = {
        editRow : undefined,
        /**
         * 查询按钮点击后执行
         * @type {{search: Function}}
         */
        search : function(){
            $('#table-nav').datagrid('load',{
                'queryParameter.username' : $.trim($('#username').val()),
                'queryParameter.date_from' : $('#date_from').datebox('getValue'),
                'queryParameter.date_to' :  $('#date_to').datebox('getValue'),
            });
        },

        /**
         * 新增按钮点击后执行
         */

        add : function(){
            $('#save-nav,#redo-nav').show();
            //新增一行
            if(this.editRow == undefined){
                $('#table-nav').datagrid('insertRow',{
                    index : 0,
                    row : {
                        date : new Date(),
                        salt : new Date().getMilliseconds() * parseInt(Math.random()*10),
                    },
                });

                //将新增的行变成可编辑状态
                $('#table-nav').datagrid('beginEdit',0);
                this.editRow = 0;
            }

        },
        save : function(){
            //将新增的行变成结束编辑状态
            $('#table-nav').datagrid('endEdit',this.editRow);
        },
        redo : function () {
            $('#save-nav,#redo-nav').hide();
            this.editRow = undefined;
            $('#table-nav').datagrid('rejectChanges');
        },
        edit : function () {
            var rows = $('#table-nav').datagrid('getSelections');
            if(rows.length == 1){
                if(this.editRow != undefined){
                    $('#table-nav').datagrid('endEdit',this.editRow);
                }
                if(this.editRow == undefined){
                    var index = $('#table-nav').datagrid('getRowIndex',rows[0]);
                    $('#save-nav,#redo-nav').show();
                    $('#table-nav').datagrid('beginEdit',index);
                    this.editRow = index;
                    $('#table-nav').datagrid('unselectRow',index);
                }
            }else{
                $.messager.alert('警告','只允许同时修改一行!','warning');
            }
        },
        remove : function(){
            var rows = $('#table-nav').datagrid('getSelections');
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
                                $('#table-nav').datagrid('loading');
                            },
                            success : function (data) {
                                if(data){
                                    $('#table-nav').datagrid('loaded');
                                    $('#table-nav').datagrid('reload');
                                    $('#table-nav').datagrid('unselectAll');
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


    $('#table-nav').datagrid({
        width : 650,
        title : '用户列表',
        url : '/nav/getNavList.action',
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
                field : 'text',
                title : '菜单名称',
                sortable : false,
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
                field : 'state',
                title : '状态',
                sortable : true,
                align : 'center',
                width : 100,
                editor : {
                    type : 'combobox',
                    options : {
                        required : true,
                    },
                },
            },
            {
                field : 'iconCls',
                title : '图标class',
                sortable : true,
                align : 'center',
                width : 150,
            },
            {
                field : 'url',
                title : '连接地址',
                sortable : false,
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
                field : 'nid',
                title : '父节点id',
                align : 'center',
                width : 100,
                /*formatter : function (value) {
                    if(value == 'true'){
                        value = '<input type="checkbox" checked> ';
                    }else{
                        value = '<input type="checkbox">';
                    }
                    return value;
                },*/
                editor : {
                    type : 'combobox',
                    options : {

                    },
                },
            }
        ]],
        toolbar : '#tb-nav',
        pagination : true,
        pageSize : 10,
        pageList : [5,10,15],
        sortName : 'id',
        sortOrder : 'DESC',
        remoteSort : false,
        onDblClickRow : function(rowIndex, rowData){
            if(obj.editRow != undefined){
                $('#table-nav').datagrid('endEdit',obj.editRow);
            }
            if(obj.editRow == undefined){
                $('#save-nav,#redo-nav').show();
                obj.editRow = rowIndex;
                $('#table-nav').datagrid('beginEdit',rowIndex);
            }
        },
        onAfterEdit : function (index, rowData, change) {
            $('#save-nav,#redo-nav').hide();
            var inserted = $('#table-nav').datagrid('getChanges','inserted');
            var updated = $('#table-nav').datagrid('getChanges','updated');

            //新增用户
            if(inserted.length > 0){
                $.ajax({
                    url : '/user/insertUser.action',
                    type : 'POST',
                    data : {
                        'queryParameter.row' : rowData,
                    },
                    beforeSend : function () {
                        $('#table-nav').datagrid('loading');
                    },
                    success : function (data) {
                        if(data == 'success'){
                            $('#table-nav').datagrid('loaded');
                            $('#table-nav').datagrid('load');
                            $('#table-nav').datagrid('unselectAll');
                            $.messager.show({
                                title : '提示',
                                msg : '新增成功!',
                            });
                            obj.editRow = undefined;
                        }else{
                            $('#table-nav').datagrid('loaded');
                            $('#table-nav').datagrid('load');
                            $('#table-nav').datagrid('unselectAll');
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
                        $('#table-nav').datagrid('loading');
                    },
                    success : function (data) {
                        if(data == 'success'){
                            $('#table-nav').datagrid('loaded');
                            $('#table-nav').datagrid('reload');
                            $('#table-nav').datagrid('unselectAll');
                            $.messager.show({
                                title : '提示',
                                msg : '修改成功!',
                            });
                        }
                        obj.editRow = undefined;
                    },
                });
            }

            if(inserted.length == 0 && updated.length == 0){
                obj.editRow = undefined;
            }
        },

    });

    $('#table-nav').datagrid("resize");
});

