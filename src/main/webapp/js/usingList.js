
$(function(){

    var ctx = $('#ctx').val();

    obj_using = {
        editRow : undefined,
        /**
         * 查询按钮点击后执行
         * @type {{search: Function}}
         */
        search : function(){
            $('#table-using').datagrid('load',{
                'queryParameter.d_name' : $.trim($('#dName').val()),
                'queryParameter.u_place' : $.trim($('#uPlace').val()),
            });
        },

        /**
         * 新增按钮点击后执行
         */

        add : function(){
            $('#save-using,#redo-using').show();
            //新增一行
            if(this.editRow == undefined){
                $('#table-using').datagrid('insertRow',{
                    index : 0,
                    row : {
                        date : new Date(),
                        salt : new Date().getMilliseconds() * parseInt(Math.random()*10),
                    },
                });

                //将新增的行变成可编辑状态
                $('#table-using').datagrid('beginEdit',0);
                this.editRow = 0;
            }

        },
        save : function(){
            //将新增的行变成结束编辑状态
            $('#table-using').datagrid('endEdit',this.editRow);
        },
        redo : function () {
            $('#save-using,#redo-using').hide();
            this.editRow = undefined;
            $('#table-using').datagrid('rejectChanges');
        },
        edit : function () {
            var rows = $('#table-using').datagrid('getSelections');
            if(rows.length == 1){
                if(this.editRow != undefined){
                    $('#table-using').datagrid('endEdit',this.editRow);
                }
                if(this.editRow == undefined){
                    var index = $('#table-using').datagrid('getRowIndex',rows[0]);
                    $('#save-using,#redo-using').show();
                    $('#table-using').datagrid('beginEdit',index);
                    this.editRow = index;
                    $('#table-using').datagrid('unselectRow',index);
                }
            }else{
                $.messager.alert('警告','只允许同时修改一行!','warning');
            }
        },
        remove : function(){
            var rows = $('#table-using').datagrid('getSelections');
            if(rows.length > 0){
                $.messager.confirm('确定操作','确定要删除所选吗？',function(flag){
                    if(flag){
                        var ids = [];
                        for(var i = 0; i < rows.length; i++){
                            ids.push(rows[i].id);
                        }
                        //与后台交互删除数据
                        $.ajax({
                            url : '/nav/deleteNav.action',
                            type : 'POST',
                            data : {
                                ids : ids.join(','),
                            },
                            beforeSend : function () {
                                $('#table-using').datagrid('loading');
                            },
                            success : function (data) {
                                if(data){
                                    $('#table-using').datagrid('loaded');
                                    $('#table-using').datagrid('reload');
                                    $('#table-using').datagrid('unselectAll');
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
    $('#clean-using').on('click',function(){
        $('#dName').val('');
        $('#uPlace').val('');
    });


    $('#table-using').datagrid({
        width : 500,
        title : '设备使用列表',
        url : ctx + '/using/getDeviceUsingList.action',
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
                field : 'd_name',
                title : '设备名称',
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
                field : 'u_place',
                title : '所在位置',
                sortable : true,
                align : 'center',
                width : 80,
                editor : {
                    type : 'validatebox',
                    options : {
                        required : true,
                        valueField: 'label',
                        textField: 'value',
                        panelHeight : 80,
                        data: [{
                            label: 'open',
                            value: 'open'
                        },{
                            label: 'closed',
                            value: 'closed'
                        }],
                    },
                },
            },
            {
                field : 'u_state',
                title : '使用状态',
                sortable : true,
                align : 'center',
                width : 150,
                formatter : function(value){
                    if(value == 0){
                        value = "正常"
                    }
                    if(value == 1){
                        value = "故障"
                    }
                    if(value == 2){
                        value = "维修"
                    }
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
                field : 'u_mark',
                title : '备注',
                sortable : false,
                align : 'center',
                width : 80,
                editor : {
                    type : 'validatebox',
                    options : {
                        required : true,
                    },
                 },
            },
            {
                field : 'is_using',
                title : '是否使用',
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
        toolbar : '#tb-using',
        pagination : true,
        pageSize : 10,
        pageList : [5,10,15],
        sortName : 'id',
        sortOrder : 'ASC',
        remoteSort : false,
        onDblClickRow : function(rowIndex, rowData){
            if(obj_using.editRow != undefined){
                $('#table-using').datagrid('endEdit',obj_using.editRow);
            }
            if(obj_using.editRow == undefined){
                $('#save-using,#redo-using').show();
                obj_using.editRow = rowIndex;
                $('#table-using').datagrid('beginEdit',rowIndex);
            }
        },
        onAfterEdit : function (index, rowData, change) {
            $('#save-using,#redo-using').hide();
            var inserted = $('#table-using').datagrid('getChanges','inserted');
            var updated = $('#table-using').datagrid('getChanges','updated');

            //新增用户
            if(inserted.length > 0){
                $.ajax({
                    url : '/nav/insertNav.action',
                    type : 'POST',
                    data : {
                        'queryParameter.row' : rowData,
                    },
                    beforeSend : function () {
                        $('#table-using').datagrid('loading');
                    },
                    success : function (data) {
                        if(data == 'success'){
                            $('#table-using').datagrid('loaded');
                            $('#table-using').datagrid('load');
                            $('#table-using').datagrid('unselectAll');
                            $.messager.show({
                                title : '提示',
                                msg : '新增成功!',
                            });
                            obj_using.editRow = undefined;
                        }else{
                            $('#table-using').datagrid('loaded');
                            $('#table-using').datagrid('load');
                            $('#table-using').datagrid('unselectAll');
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
                    url : '/nav/updateNav.action',
                    type : 'POST',
                    data : {
                        'queryParameter.row' : rowData,
                    },
                    beforeSend : function () {
                        $('#table-using').datagrid('loading');
                    },
                    success : function (data) {
                        if(data == 'success'){
                            $('#table-using').datagrid('loaded');
                            $('#table-using').datagrid('reload');
                            $('#table-using').datagrid('unselectAll');
                            $.messager.show({
                                title : '提示',
                                msg : '修改成功!',
                            });
                        }
                        obj_using.editRow = undefined;
                    },
                });
            }

            if(inserted.length == 0 && updated.length == 0){
                obj_using.editRow = undefined;
            }
        },

    });

    $('#table-using').datagrid("resize");
});

