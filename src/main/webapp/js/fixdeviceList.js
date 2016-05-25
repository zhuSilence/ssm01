/**
 * Created by Tao on 2015/12/02.
 */

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

    obj_fixdevice = {
        editRow : undefined,
        /**
         * 查询按钮点击后执行
         * @type {{search: Function}}
         */
        search : function(){
            $('#table-fixdevice').datagrid('load',{
                'queryParameter.fixer' : $.trim($('#fixer').val()),
                'queryParameter.d_id' : $.trim($('#d_id').val()),
                'queryParameter.fix_time_from' : $('#fix_time_from').datebox('getValue'),
                'queryParameter.fix_time_to' :  $('#fix_time_to').datebox('getValue'),
            });
        },

        /**
         * 新增按钮点击后执行
         */

        add : function(){
            $('#save_fixdevice,#redo_fixdevice').show();
            //新增一行
            if(this.editRow == undefined){
                $('#table-fixdevice').datagrid('insertRow',{
                    index : 0,
                    row:{
                        fix_time : new Date(),
                    },
                });

                //将新增的行变成可编辑状态
                $('#table-fixdevice').datagrid('beginEdit',0);
                this.editRow = 0;
            }
        },
        save : function(){
            //将新增的行变成结束编辑状态
            $('#table-fixdevice').datagrid('endEdit',this.editRow);
        },
        redo : function () {
            $('#save_fixdevice,#redo_fixdevice').hide();
            this.editRow = undefined;
            $('#table-fixdevice').datagrid('rejectChanges');
        },
        edit : function () {
            var rows = $('#table-fixdevice').datagrid('getSelections');
            if(rows.length == 1){
                if(this.editRow != undefined){
                    $('#table-fixdevice').datagrid('endEdit',this.editRow);
                }
                if(this.editRow == undefined){
                    var index = $('#table-fixdevice').datagrid('getRowIndex',rows[0]);
                    $('#save_fixdevice,#redo_fixdevice').show();
                    $('#table-fixdevice').datagrid('beginEdit',index);
                    this.editRow = index;
                    $('#table-fixdevice').datagrid('unselectRow',index);
                }
            }else{
                $.messager.alert('警告','只允许同时修改一行!','warning');
            }
        },
        remove : function(){
            var rows = $('#table-fixdevice').datagrid('getSelections');
            if(rows.length > 0){
                $.messager.confirm('确定操作','确定要删除所选吗？',function(flag){
                    if(flag){
                        var ids = [];
                        for(var i = 0; i < rows.length; i++){
                            ids.push(rows[i].id);
                        }
                        //与后台交互删除数据
                        $.ajax({
                            url : '/fixdevice/deleteFixDevice.action',
                            type : 'POST',
                            data : {
                                ids : ids.join(','),
                            },
                            beforeSend : function () {
                                $('#table-fixdevice').datagrid('loading');
                            },
                            success : function (data) {
                                if(data){
                                    $('#table-fixdevice').datagrid('loaded');
                                    $('#table-fixdevice').datagrid('reload');
                                    $('#table-fixdevice').datagrid('unselectAll');
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
    $('#clean-buydivice').on('click',function(){
        $('#fixer').val('');
        $('#d_id').val('');
        $('#fix_time_from').datebox('setValue','');
        $('#fix_time_to').datebox('setValue','');
    });


    $('#table-fixdevice').datagrid({
        width : 650,
        title : '设备列表',
        url : '/fixdevice/getFixDeviceList.action',
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
                field : 'fixer',
                title : '修理人',
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
                field : 'd_id',
                title : '设备ID',
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
                field : 'fix_time',
                title : '修理日期',
                sortable : true,
                align : 'center',
                width : 150,
                formatter : function(value){
                     return toDateString(value);
                 },
            },
            {
                field : 'fix_mark',
                title : '修理备注',
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
                field : 'is_fixed',
                title : '是否修理',
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
        toolbar : '#tb-fixdevice',
        pagination : true,
        pageSize : 10,
        pageList : [5,10,15],
        sortName : 'id',
        sortOrder : 'DESC',
        remoteSort : false,
        onDblClickRow : function(rowIndex, rowData){
            if(obj_fixdevice.editRow != undefined){
                $('#table-fixdevice').datagrid('endEdit',obj_fixdevice.editRow);
            }
            if(obj_fixdevice.editRow == undefined){
                $('#save_fixdevice,#redo_fixdevice').show();
                obj_fixdevice.editRow = rowIndex;
                $('#table-fixdevice').datagrid('beginEdit',rowIndex);
            }
        },
        onAfterEdit : function (index, rowData, change) {
            $('#save_fixdevice,#redo_fixdevice').hide();
            var inserted = $('#table-fixdevice').datagrid('getChanges','inserted');
            var updated = $('#table-fixdevice').datagrid('getChanges','updated');

            //新增修理设备记录
            if(inserted.length > 0){
                $.ajax({
                    url : '/fixdevice/insertFixDevice.action',
                    type : 'POST',
                    data : {
                        'queryParameter.row' : rowData,
                    },
                    beforeSend : function () {
                        $('#table-fixdevice').datagrid('loading');
                    },
                    success : function (data) {
                        if(data == 'success'){
                            $('#table-fixdevice').datagrid('loaded');
                            $('#table-fixdevice').datagrid('load');
                            $('#table-fixdevice').datagrid('unselectAll');
                            $.messager.show({
                                title : '提示',
                                msg : '新增成功!',
                            });
                            obj_fixdevice.editRow = undefined;
                        }else{
                            $('#table-fixdevice').datagrid('loaded');
                            $('#table-fixdevice').datagrid('load');
                            $('#table-fixdevice').datagrid('unselectAll');
                            $.messager.alert("提示",data,"info");
                        }
                    },
                    error : function(data,value){
                    },
                });
            }

            //修改修理记录
            if(updated.length > 0){
                $.ajax({
                    url : '/fixdevice/updateFixDevice.action',
                    type : 'POST',
                    data : {
                        'queryParameter.row' : rowData,
                    },
                    beforeSend : function () {
                        $('#table-fixdevice').datagrid('loading');
                    },
                    success : function (data) {
                        if(data == 'success'){
                            $('#table-fixdevice').datagrid('loaded');
                            $('#table-fixdevice').datagrid('reload');
                            $('#table-fixdevice').datagrid('unselectAll');
                            $.messager.show({
                                title : '提示',
                                msg : '修改成功!',
                            });
                        }
                        obj_fixdevice.editRow = undefined;
                    },
                });
            }

            if(inserted.length == 0 && updated.length == 0){
                obj_fixdevice.editRow = undefined;
            }
        },

    });

    $('#table-fixdevice').datagrid("resize");



});