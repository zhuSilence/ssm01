/**
 * Created by Tao on 2015/11/29.
 */


$(function(){

    var ctx = $('#ctx').val();

    obj = {
        editRow : undefined,
        /**
         * 查询按钮点击后执行
         * @type {{search: Function}}
         */
        search : function(){
            $('#table').datagrid('load',{
                'queryParameter.d_name' : $.trim($('#d_name').val()),
                'queryParameter.low_price' : $('#low_price').val(),
                'queryParameter.high_price' :  $('#high_price').val(),
            });
        },

        /**
         * 新增按钮点击后执行
         */

        add : function(){
            $('#save,#redo').show();
            //新增一行
            if(this.editRow == undefined){
                $('#table').datagrid('insertRow',{
                    index : 0,
                    row : {
                        date : new Date(),
                        salt : new Date().getMilliseconds() * parseInt(Math.random()*10),
                    },
                });

                //将新增的行变成可编辑状态
                $('#table').datagrid('beginEdit',0);
                this.editRow = 0;
            }

        },
        save : function(){
            //将新增的行变成结束编辑状态
            $('#table').datagrid('endEdit',this.editRow);
        },
        redo : function () {
            $('#save,#redo').hide();
            this.editRow = undefined;
            $('#table').datagrid('rejectChanges');
        },
        edit : function () {
            var rows = $('#table').datagrid('getSelections');
            if(rows.length == 1){
                if(this.editRow != undefined){
                    $('#table').datagrid('endEdit',this.editRow);
                }
                if(this.editRow == undefined){
                    var index = $('#table').datagrid('getRowIndex',rows[0]);
                    $('#save,#redo').show();
                    $('#table').datagrid('beginEdit',index);
                    this.editRow = index;
                    $('#table').datagrid('unselectRow',index);
                }
            }else{
                $.messager.alert('警告','只允许同时修改一行!','warning');
            }
        },
        remove : function(){
            var rows = $('#table').datagrid('getSelections');
            if(rows.length > 0){
                $.messager.confirm('确定操作','确定要删除所选吗？',function(flag){
                    if(flag){
                        var ids = [];
                        for(var i = 0; i < rows.length; i++){
                            ids.push(rows[i].id);
                        }
                        //与后台交互删除数据
                        $.ajax({
                            url : '/device/deleteDevice.action',
                            type : 'POST',
                            data : {
                                ids : ids.join(','),
                            },
                            beforeSend : function () {
                                $('#table').datagrid('loading');
                            },
                            success : function (data) {
                                if(data){
                                    $('#table').datagrid('loaded');
                                    $('#table').datagrid('reload');
                                    $('#table').datagrid('unselectAll');
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
        $('#d_name').val('');
        $('#low_price').val('');
        $('#high_price').val('');
    });


    $('#table').datagrid({
        width : 650,
        title : '设备列表',
        url : '/device/getDeviceList.action',
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
                title : '名称',
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
                field : 'd_desc',
                title : '描述',
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
                field : 'd_price',
                title : '价格',
                sortable : true,
                align : 'center',
                width : 150,
                formatter : function(value){
                    return value;
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
            if(obj.editRow != undefined){
                $('#table').datagrid('endEdit',obj.editRow);
            }
            if(obj.editRow == undefined){
                $('#save,#redo').show();
                obj.editRow = rowIndex;
                $('#table').datagrid('beginEdit',rowIndex);
            }
        },
        onAfterEdit : function (index, rowData, change) {
            $('#save,#redo').hide();
            var inserted = $('#table').datagrid('getChanges','inserted');
            var updated = $('#table').datagrid('getChanges','updated');

            //新增用户
            //if(inserted.length > 0){
            //    $.ajax({
            //        url : '/device/insertDevice.action',
            //        type : 'POST',
            //        data : {
            //            'queryParameter.row' : rowData,
            //        },
            //        beforeSend : function () {
            //            $('#table').datagrid('loading');
            //        },
            //        success : function (data) {
            //            if(data == 'success'){
            //                $('#table').datagrid('loaded');
            //                $('#table').datagrid('load');
            //                $('#table').datagrid('unselectAll');
            //                $.messager.show({
            //                    title : '提示',
            //                    msg : '新增成功!',
            //                });
            //                obj.editRow = undefined;
            //            }else{
            //                $('#table').datagrid('loaded');
            //                $('#table').datagrid('load');
            //                $('#table').datagrid('unselectAll');
            //                $.messager.alert("提示",data,"info");
            //            }
            //        },
            //        error : function(data,value){
            //        },
            //    });
            //}

            //修改用户
            //if(updated.length > 0){
            //    $.ajax({
            //        url : '/device/updateDevice.action',
            //        type : 'POST',
            //        data : {
            //            'queryParameter.row' : rowData,
            //        },
            //        beforeSend : function () {
            //            $('#table').datagrid('loading');
            //        },
            //        success : function (data) {
            //            if(data == 'success'){
            //                $('#table').datagrid('loaded');
            //                $('#table').datagrid('reload');
            //                $('#table').datagrid('unselectAll');
            //                $.messager.show({
            //                    title : '提示',
            //                    msg : '修改成功!',
            //                });
            //            }
            //            obj.editRow = undefined;
            //        },
            //    });
            //}

            if(inserted.length == 0 && updated.length == 0){
                obj.editRow = undefined;
            }
        },

    });

    $('#table').datagrid("resize");



});