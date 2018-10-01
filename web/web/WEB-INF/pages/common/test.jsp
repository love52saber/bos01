<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BOS主界面</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
</head>
<body>
<table class="easyui-datagrid">
    <thead>
    <tr>
        <th data-options="field:'id'">编号</th>
        <th data-options="field:'name'">姓名</th>
        <th data-options="field:'age'">年龄</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>001</td>
        <td>小明</td>
        <td>90</td>
    </tr>
    <tr>
        <td>002</td>
        <td>老王</td>
        <td>3</td>
    </tr>
    </tbody>
</table>
</body>
<script !src="">
    $(function () {

    });
</script>
</html>