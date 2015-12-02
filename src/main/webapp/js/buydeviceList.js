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

    obj_buydevice = {
        editRow : undefined,
        /**
         * 查询按钮点击后执行
         * @type {{search: Function}}
         */
        search : function(){
            $('#table-buydevice').datagrid('load',{
                'queryParameter.buyer' : $.trim($('#buyer').val()),
                'queryParameter.d_id' : $.trim($('#d_id').val()),
                'queryParameter.low_money' : $('#low_money').val(),
                'queryParameter.high_money' :  $('#high_money').val(),
                'queryParameter.b_time_from' : $('#b_time_from').datebox('getValue'),
                'queryParameter.b_time_to' :  $('#b_time_to').datebox('getValue'),
            });
        },

        /**
         * 新增按钮点击后执行
         */

        add : function(){
            $('#save_buydevice,#redo_buydevice').show();
            //新增一行
            if(this.editRow == undefined){
                $('#table-buydevice').datagrid('insertRow',{
                    index : 0,
                    row:{
                        b_time : new Date()
                    }
                });

                //将新增的行变成可编辑状态
                $('#table-buydevice').datagrid('beginEdit',0);
                this.editRow = 0;
            }
        },
        save : function(){
            //将新增的行变成结束编辑状态
            $('#table-buydevice').datagrid('endEdit',this.editRow);
        },
        redo : function () {
            $('#save_buydevice,#redo_buydevice').hide();
            this.editRow = undefined;
            $('#table-buydevice').datagrid('rejectChanges');
        },
        edit : function () {
            var rows = $('#table-buydevice').datagrid('getSelections');
            if(rows.length == 1){
                if(this.editRow != undefined){
                    $('#table-buydevice').datagrid('endEdit',this.editRow);
                }
                if(this.editRow == undefined){
                    var index = $('#table-buydevice').datagrid('getRowIndex',rows[0]);
                    $('#save_buydevice,#redo_buydevice').show();
                    $('#table-buydevice').datagrid('beginEdit',index);
                    this.editRow = index;
                    $('#table-buydevice').datagrid('unselectRow',index);
                }
            }else{
                $.messager.alert('警告','只允许同时修改一行!','warning');
            }
        },
        remove : function(){
            var rows = $('#table-buydevice').datagrid('getSelections');
            if(rows.length > 0){
                $.messager.confirm('确定操作','确定要删除所选吗？',function(flag){
                    if(flag){
                        var ids = [];
                        for(var i = 0; i < rows.length; i++){
                            ids.push(rows[i].id);
                        }
                        //与后台交互删除数据
                        $.ajax({
                            url : '/buydevice/deleteBuyDevice.action',
                            type : 'POST',
                            data : {
                                ids : ids.join(','),
                            },
                            beforeSend : function () {
                                $('#table-buydevice').datagrid('loading');
                            },
                            success : function (data) {
                                if(data){
                                    $('#table-buydevice').datagrid('loaded');
                                    $('#table-buydevice').datagrid('reload');
                                    $('#table-buydevice').datagrid('unselectAll');
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
        $('#buyer').val('');
        $('#d_id').val('');
        $('#low_money').val('');
        $('#high_money').val('');
        $('#b_time_from').datebox('setValue','');
        $('#b_time_to').datebox('setValue','');
    });


    $('#table-buydevice').datagrid({
        width : 650,
        title : '设备列表',
        url : '/buydevice/getBuyDeviceList.action',
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
                field : 'buyer',
                title : '购买人',
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
                field : 'b_money',
                title : '购买价格',
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
                field : 'b_time',
                title : '购买日期',
                sortable : true,
                align : 'center',
                width : 150,
                formatter : function(value){
                     return toDateString(value);
                 },
            },
            {
                field : 'b_mark',
                title : '备注',
                sortable : true,
                align : 'center',
                width : 100,
                editor : {
                    type : 'validatebox',
                    options : {
                        required : true,
                    },
                },
            }
        ]],
        toolbar : '#tb-buydevice',
        pagination : true,
        pageSize : 10,
        pageList : [5,10,15],
        sortName : 'id',
        sortOrder : 'DESC',
        remoteSort : false,
        onDblClickRow : function(rowIndex, rowData){
            if(obj_buydevice.editRow != undefined){
                $('#table-buydevice').datagrid('endEdit',obj_buydevice.editRow);
            }
            if(obj_buydevice.editRow == undefined){
                $('#save_buydevice,#redo_buydevice').show();
                obj_buydevice.editRow = rowIndex;
                $('#table-buydevice').datagrid('beginEdit',rowIndex);
            }
        },
        onAfterEdit : function (index, rowData, change) {
            $('#save_buydevice,#redo_buydevice').hide();
            var inserted = $('#table-buydevice').datagrid('getChanges','inserted');
            var updated = $('#table-buydevice').datagrid('getChanges','updated');

            //新增购买设备记录
            if(inserted.length > 0){
                $.ajax({
                    url : '/buydevice/insertBuyDevice.action',
                    type : 'POST',
                    data : {
                        'queryParameter.row' : rowData,
                    },
                    beforeSend : function () {
                        $('#table-buydevice').datagrid('loading');
                    },
                    success : function (data) {
                        if(data == 'success'){
                            $('#table-buydevice').datagrid('loaded');
                            $('#table-buydevice').datagrid('load');
                            $('#table-buydevice').datagrid('unselectAll');
                            $.messager.show({
                                title : '提示',
                                msg : '新增成功!',
                            });
                            obj_buydevice.editRow = undefined;
                        }else{
                            $('#table-buydevice').datagrid('loaded');
                            $('#table-buydevice').datagrid('load');
                            $('#table-buydevice').datagrid('unselectAll');
                            $.messager.alert("提示",data,"info");
                        }
                    },
                    error : function(data,value){
                    },
                });
            }

            //修改购买记录
            if(updated.length > 0){
                $.ajax({
                    url : '/buydevice/updateBuyDevice.action',
                    type : 'POST',
                    data : {
                        'queryParameter.row' : rowData,
                    },
                    beforeSend : function () {
                        $('#table-buydevice').datagrid('loading');
                    },
                    success : function (data) {
                        if(data == 'success'){
                            $('#table-buydevice').datagrid('loaded');
                            $('#table-buydevice').datagrid('reload');
                            $('#table-buydevice').datagrid('unselectAll');
                            $.messager.show({
                                title : '提示',
                                msg : '修改成功!',
                            });
                        }
                        obj_buydevice.editRow = undefined;
                    },
                });
            }

            if(inserted.length == 0 && updated.length == 0){
                obj_buydevice.editRow = undefined;
            }
        },

    });

    $('#table-buydevice').datagrid("resize");



});