//扩展dateTimeBox
$.extend($.fn.datagrid.defaults.editors, {
    datetimebox: {
        init: function (container, options) {
            var input = $('<input type="text">').appendTo(container);
            options.editable = false;
            input.datetimebox(options);
            return input;
        },
        getValue: function (target) {
            return $(target).datetimebox('getValue');
        },
        setValue: function (target, value) {
            $(target).datetimebox('setValue', value);
        },
        resize: function (target, width) {
            $(target).datetimebox('resize', width);
        },
        destroy: function (target) {
            $(target).datetimebox('destroy');
        },
    }
});


$(function () {

    var ctx = $('#ctx').val();

    obj_logData = {
        editRow: undefined,
        /**
         * 查询按钮点击后执行
         * @type {{search: Function}}
         */
        search: function () {
            $('#table-logData').datagrid('load', {
                'queryParameter.tester': $.trim($('#tester').val()),
            });
        },

        /**
         * 新增按钮点击后执行
         */

        add: function () {
            $('#save-logData,#redo-logData').show();
            //新增一行
            if (this.editRow == undefined) {
                $('#table-logData').datagrid('insertRow', {
                    index: 0,
                    row: {},
                });

                //将新增的行变成可编辑状态
                $('#table-logData').datagrid('beginEdit', 0);
                this.editRow = 0;
            }

        },
        save: function () {
            //将新增的行变成结束编辑状态
            $('#table-logData').datagrid('endEdit', this.editRow);
        },
        redo: function () {
            $('#save-logData,#redo-logData').hide();
            this.editRow = undefined;
            $('#table-logData').datagrid('rejectChanges');
        },
        edit: function () {
            var rows = $('#table-logData').datagrid('getSelections');
            if (rows.length == 1) {
                if (this.editRow != undefined) {
                    $('#table-logData').datagrid('endEdit', this.editRow);
                }
                if (this.editRow == undefined) {
                    var index = $('#table-logData').datagrid('getRowIndex', rows[0]);
                    $('#save-logData,#redo-logData').show();
                    $('#table-logData').datagrid('beginEdit', index);
                    this.editRow = index;
                    $('#table-logData').datagrid('unselectRow', index);
                }
            } else {
                $.messager.alert('警告', '只允许同时修改一行!', 'warning');
            }
        },
        remove: function () {
            var rows = $('#table-logData').datagrid('getSelections');
            if (rows.length > 0) {
                $.messager.confirm('确定操作', '确定要删除所选吗？', function (flag) {
                    if (flag) {
                        var ids = [];
                        for (var i = 0; i < rows.length; i++) {
                            ids.push(rows[i].logId);
                        }
                        //与后台交互删除数据
                        $.ajax({
                            url: ctx + '/logData/deleteLogData.action',
                            type: 'POST',
                            data: {
                                ids: ids.join(','),
                            },
                            beforeSend: function () {
                                $('#table-logData').datagrid('loading');
                            },
                            success: function (data) {
                                if (data) {
                                    $('#table-logData').datagrid('loaded');
                                    $('#table-logData').datagrid('reload');
                                    $('#table-logData').datagrid('unselectAll');
                                    $.messager.show({
                                        title: '提示',
                                        msg: '删除成功!',
                                    });
                                }
                            },
                        });
                    }
                });
            } else {
                $.messager.alert('提示', '没有选择要删除的行!', 'info');
            }
        },
    };

    /**
     * 清除按钮点击后执行
     */
    $('#clean').on('click', function () {
        $('#tester').val('');
    });


    $('#table-logData').datagrid({
        width: 650,
        title: '用户列表',
        url: ctx + '/logData/getLogDataList.action',
        striped: true,
        fit: true,
        rownumbers: true,
        loadMsg: '努力加载中。。。',
        fitColumns: true,
        columns: [[
            {
                field: 'log_id',
                title: '编号',
                sortable: true,
                align: 'center',
                width: 50,
                checkbox: true,
            },
            {
                field: 'level',
                title: '等级',
                sortable: true,
                align: 'center',
                width: 100,
                editor: {
                    type: 'validatebox',
                    options: {
                        required: true,
                    },
                },
            },
            {
                field: 'status',
                title: '故障状态',
                sortable: true,
                align: 'center',
                width: 100,
                formatter: function (value) {
                    return value;
                },
                editor: {
                    type: 'validatebox',
                    options: {
                        required: true,
                    },
                },
            },
            {
                field: 'tester',
                title: '测试人',
                sortable: true,
                align: 'center',
                width: 150,
                editor: {
                    type: 'validatebox',
                    options: {
                        required: true,
                    },
                },
            },
            {
                field: 'fileurl',
                title: '文件',
                sortable: true,
                align: 'center',
                width: 100,
                editor: {
                    type: 'validatebox',
                    options: {
                        required: true,
                    },
                },
                formatter: function (value) {
                    return '<a target="_blank" href="/download.action?fileurl='+ value +'">下载</a>';
                }
            },
        ]],
        toolbar: '#tb',
        pagination: true,
        pageSize: 10,
        pageList: [5, 10, 15],
        sortName: 'log_id',
        sortOrder: 'DESC',
        remoteSort: false,
        onDblClickRow: function (rowIndex, rowData) {
            if (obj_logData.editRow != undefined) {
                $('#table-logData').datagrid('endEdit', obj_logData.editRow);
            }
            if (obj_logData.editRow == undefined) {
                $('#save-logData,#redo-logData').show();
                obj_logData.editRow = rowIndex;
                $('#table-logData').datagrid('beginEdit', rowIndex);
            }
        },
        onAfterEdit: function (index, rowData, change) {
            $('#save-logData,#redo-logData').hide();
            var inserted = $('#table-logData').datagrid('getChanges', 'inserted');
            var updated = $('#table-logData').datagrid('getChanges', 'updated');

            //新增用户
            if (inserted.length > 0) {
                $.ajax({
                    url: ctx + '/logData/insertLogData.action',
                    type: 'POST',
                    data: {
                        'queryParameter.row': rowData,
                    },
                    beforeSend: function () {
                        $('#table-logData').datagrid('loading');
                    },
                    success: function (data) {
                        if (data == 'success') {
                            $('#table-logData').datagrid('loaded');
                            $('#table-logData').datagrid('load');
                            $('#table-logData').datagrid('unselectAll');
                            $.messager.show({
                                title: '提示',
                                msg: '新增成功!',
                            });
                            obj_logData.editRow = undefined;
                        } else {
                            $('#table-logData').datagrid('loaded');
                            $('#table-logData').datagrid('load');
                            $('#table-logData').datagrid('unselectAll');
                            $.messager.alert("提示", data, "info");
                        }
                    },
                    error: function (data, value) {
                    },
                });
            }

            //修改用户
            if (updated.length > 0) {
                $.ajax({
                    url: ctx + '/logData/updateLogData.action',
                    type: 'POST',
                    data: {
                        'queryParameter.row': rowData,
                    },
                    beforeSend: function () {
                        $('#table-logData').datagrid('loading');
                    },
                    success: function (data) {
                        if (data == 'success') {
                            $('#table-logData').datagrid('loaded');
                            $('#table-logData').datagrid('reload');
                            $('#table-logData').datagrid('unselectAll');
                            $.messager.show({
                                title: '提示',
                                msg: '修改成功!',
                            });
                        }
                        obj_logData.editRow = undefined;
                    },
                });
            }

            if (inserted.length == 0 && updated.length == 0) {
                obj_logData.editRow = undefined;
            }
        },

    });

    $('#table-logData').datagrid("resize");
   // uploader('file',null);





    function uploader(id, options) {
        var settings = {
            height: 30,
            swf: '/js/uploadify/uploadify.swf',
            uploader: '',
            width: 70,
            formData: {resizeImage: false},//是否进行缩放处理
            multi: false,//是否允许同时上传多文件，默认true
            simUploadLimit: 10,//多文件上传时，同时上传文件数目限制
            buttonText: '上传',
            onUploadError: function (file, errorCode, errorMsg, errorString) {
                alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
            },
            onUploadSuccess: function (file, data, response) {
                var result = $.parseJSON(data);
                if (result.success) {
                    //回调方法
                    if ($.isFunction(options.success)) {
                        options.success(file, data, response, id);
                    }
                } else {
                    alert(result.msg);
                }
            }
        };
        //合并属性
        $.extend(settings, options);
        $("#" + id).uploadify(settings);
        //单个文件上传，sortImageUrl不为空，也就是编辑模式下自动处理
        //var sortImageUrl = sortImage.val();
        $(".uploadify .uploadify-button").css("height", "19px").css("line-height", "19px");
        $(".uploadify .uploadify-button span").css("height", "19px");
    }
});

