<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <!-- 导入jquery核心类库 -->
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <!-- 导入easyui类库 -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/css/default.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
    <script
            src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
            type="text/javascript"></script>
    <script type="text/javascript">
        var reg = /^1[3|4|5|7|8][0-9]{9}$/;
        $.extend($.fn.validatebox.defaults.rules, {
            tel: {
                validator: function (value, param) {
                    return reg.test(value);
                },
                message: '手机号码不符合规范.'
            }
        });

        function doAdd() {
            //alert("增加...");
            $('#addStaffWindow').window("open");
        }

        function doView() {
            alert("查看...");
        }

        function doDelete() {
            var selectedRowBean = $("#grid").datagrid("getSelections");
            if (selectedRowBean.length == 0) {
                $.messager.alert("提示", "还未选择删除的送检人");
            } else {
                $.messager.confirm("删除确认","你确定要删除选中的取派员吗？",function (flag) {
                    if (flag) {
                        var ids = new Array();
                        for (var i = 0; i < selectedRowBean.length; i++) {
                            ids.push(selectedRowBean[i].id);
                        }
                        $.post(
                            "${pageContext.request.contextPath}/staff/delete",
                            {
                                "ids":ids
                            },
                            function (data) {
                                var code = data.code;
                                if (code==200) {
                                    $("#grid").datagrid("reload");
                                }
                                $.messager.alert("提示", data.msg);
                            }
                        );
                    }
                });
            }

        }

        function doRestore() {
            alert("将取派员还原...");
        }

        //工具栏
        var toolbar = [{
            id: 'button-view',
            text: '查询',
            iconCls: 'icon-search',
            handler: doView
        }, {
            id: 'button-add',
            text: '增加',
            iconCls: 'icon-add',
            handler: doAdd
        }, {
            id: 'button-delete',
            text: '作废',
            iconCls: 'icon-cancel',
            handler: doDelete
        }, {
            id: 'button-save',
            text: '还原',
            iconCls: 'icon-save',
            handler: doRestore
        }];
        // 定义列
        var columns = [[{
            field: 'id',
            checkbox: true,
        }, {
            field: 'name',
            title: '姓名',
            width: 120,
            align: 'center'
        }, {
            field: 'telephone',
            title: '手机号',
            width: 120,
            align: 'center'
        }, {
            field: 'haspda',
            title: '是否有PDA',
            width: 120,
            align: 'center',
            formatter: function (data, row, index) {
                if (data == "1") {
                    return "有";
                } else {
                    return "无";
                }
            }
        }, {
            field: 'deltag',
            title: '是否作废',
            width: 120,
            align: 'center',
            formatter: function (data, row, index) {
                if (data == "0") {
                    return "正常使用"
                } else {
                    return "已作废";
                }
            }
        }, {
            field: 'standard',
            title: '取派标准',
            width: 120,
            align: 'center'
        }, {
            field: 'station',
            title: '所谓单位',
            width: 200,
            align: 'center'
        }]];

        $(function () {
            // 先将body隐藏，再显示，不会出现页面刷新效果
            $("body").css({visibility: "visible"});

            // 取派员信息表格
            $('#grid').datagrid({
                iconCls: 'icon-forward',
                fit: true,
                border: false,
                rownumbers: true,
                striped: true,
                pageList: [10, 20, 30],
                pagination: true,
                toolbar: toolbar,
                url: "${pageContext.request.contextPath}/staff/list",
                idField: 'id',
                columns: columns,
                onDblClickRow: doDblClickRow,
            });

            // 添加取派员窗口
            $('#addStaffWindow').window({
                title: '添加取派员',
                width: 400,
                modal: true,
                shadow: true,
                closed: true,
                height: 400,
                resizable: false
            });
            // 添加取派员窗口
            $('#editStaffWindow').window({
                title: '添加取派员',
                width: 400,
                modal: true,
                shadow: true,
                closed: true,
                height: 400,
                resizable: false
            });

        });

        function doDblClickRow(rowIndex, rowData) {
            //点击弹出编辑弹窗
            $("#editStaffWindow").window("open");
            //将本行数据回显
            $("#editStaffForm").form("load",rowData);
        }

        function editStaff() {
            var isValidate = $("#editStaffForm").form("validate");
            if (isValidate) {
                //验证通过d
                $("#editStaffForm").form("submit", {
                    url: "${pageContext.request.contextPath}/staff/edit",
                    success: function (data){
                        var jsonData = JSON.parse(data);
                        var code = jsonData.code;
                        if (code == 200) {
                            $("#editStaffForm").form("clear");
                            $("#editStaffWindow").window("close");
                            $("#grid").datagrid("reload");
                        }
                        $.messager.alert("提示", jsonData.msg);
                    }
                });
            } else {
                //验证未通过
                $.messager.alert("提示", "表单有错误");
            }
        }

        function addStaff() {
            var isValidate = $("#addStaffForm").form("validate");
            if (isValidate) {
                //验证通过d
                $("#addStaffForm").form("submit", {
                    url: "${pageContext.request.contextPath}/staff/add",
                    success: function (data){
                        var jsonData = JSON.parse(data);
                        var code = jsonData.code;
                        if (code == 200) {
                            $("#addStaffForm").form("clear");
                            $("#addStaffWindow").window("close");
                            $("#grid").datagrid("reload");
                        }
                        $.messager.alert("提示", jsonData.msg);
                    }
                });
            } else {
                //验证未通过
                $.messager.alert("提示", "表单有错误");
            }
        }

    </script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
<div region="center" border="false">
    <table id="grid"></table>
</div>
<div class="easyui-window" title="对收派员进行添加或者修改" id="addStaffWindow" collapsible="false" minimizable="false"
     maximizable="false" style="top:20px;left:200px">
    <div region="north" style="height:31px;overflow:hidden;" split="false" border="false">
        <div class="datagrid-toolbar">
            <a id="save" icon="icon-save" href="javascript:addStaff()" class="easyui-linkbutton" plain="true">保存</a>
        </div>d
    </div>

    <div region="center" style="overflow:auto;padding:5px;" border="false" >
        <form id="addStaffForm" method="post">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">收派员信息</td>
                </tr>
<%--                <!-- TODO 这里完善收派员添加 table -->
                <tr>
                    <td>取派员编号</td>
                    <td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>
                </tr>--%>

                <tr>
                    <td>姓名</td>
                    <td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>手机</td>
                    <td><input type="text" name="telephone" class="easyui-validatebox" data-options="validType:'tel'"
                               required="true"/></td>
                </tr>
                <tr>
                    <td>单位</td>
                    <td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="checkbox" name="haspda" value="1"/>
                        是否有PDA
                    </td>
                </tr>
                <tr>
                    <td>取派标准</td>
                    <td>
                        <input type="text" name="standard" class="easyui-validatebox" required="true"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<div class="easyui-window" title="对收派员进行改" id="editStaffWindow" collapsible="false" minimizable="false"
     maximizable="false" style="top:20px;left:200px">
    <div region="north" style="height:31px;overflow:hidden;" split="false" border="false">
        <div class="datagrid-toolbar">
            <a id="edit" icon="icon-save" href="javascript:editStaff()" class="easyui-linkbutton" plain="true">修改</a>
        </div>d
    </div>

    <div region="center" style="overflow:auto;padding:5px;" border="false" >
        <form id="editStaffForm" method="post">
            <input type="hidden" class="hidden" name="id" >
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">收派员信息</td>
                </tr>
                <%--                <!-- TODO 这里完善收派员添加 table -->
                                <tr>
                                    <td>取派员编号</td>
                                    <td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>
                                </tr>--%>
                <tr>
                    <td>姓名</td>
                    <td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>手机</td>
                    <td><input type="text" name="telephone" class="easyui-validatebox" data-options="validType:'tel'"
                               required="true"/></td>
                </tr>
                <tr>
                    <td>单位</td>
                    <td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="checkbox" name="haspda" value="1"/>
                        是否有PDA
                    </td>
                </tr>
                <tr>
                    <td>取派标准</td>
                    <td>
                        <input type="text" name="standard" class="easyui-validatebox" required="true"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>	