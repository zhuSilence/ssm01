
$(function(){

    var ctx = $('#ctx').val();

    obj_nav = {
        editRow : undefined,
        /**
         * 查询按钮点击后执行
         * @type {{search: Function}}
         */
        search : function(){
            $('#table-nav').datagrid('load',{
                'queryParameter.text' : $.trim($('#text').val()),
                'queryParameter.state' : $.trim($('#state').combobox('getValue')),
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
                            url :ctx + '/nav/deleteNav.action',
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
    $('#clean-nav').on('click',function(){
        $('#text').val('');
        $('#state').combobox('setValue','');
    });


    $('#table-nav').datagrid({
        width : 500,
        title : '菜单列表',
        url : ctx + '/nav/getNavList.action',
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
                title : '导航名称',
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
                title : '导航状态',
                sortable : true,
                align : 'center',
                width : 80,
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
                editor : {
                    type : 'validatebox',
                    options : {
                        required : true,
                    },
                },
            },
            {
                field : 'url',
                title : '连接地址',
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
                field : 'nid',
                title : '父节点id',
                align : 'center',
                width : 100,
                editor : {
                    type : 'combobox',
                    options : {
                        valueField: 'id',
                        textField: 'id',
                        panelHeight : 80,
                        //url : ctx + '/nav/getNavList.action',
                    },
                },
            }
        ]],
        toolbar : '#tb-nav',
        pagination : true,
        pageSize : 10,
        pageList : [5,10,15],
        sortName : 'id',
        sortOrder : 'ASC',
        remoteSort : false,
        onDblClickRow : function(rowIndex, rowData){
            if(obj_nav.editRow != undefined){
                $('#table-nav').datagrid('endEdit',obj_nav.editRow);
            }
            if(obj_nav.editRow == undefined){
                $('#save-nav,#redo-nav').show();
                obj_nav.editRow = rowIndex;
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
                    url : ctx +'/nav/insertNav.action',
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
                            obj_nav.editRow = undefined;
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
                    url : ctx +'/nav/updateNav.action',
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
                        obj_nav.editRow = undefined;
                    },
                });
            }

            if(inserted.length == 0 && updated.length == 0){
                obj_nav.editRow = undefined;
            }
        },

    });

    $('#table-nav').datagrid("resize");
});

