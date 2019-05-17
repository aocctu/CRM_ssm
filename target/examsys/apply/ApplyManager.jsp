<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<title>申请领料</title>
	<c:import url="/header.jsp"></c:import>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/apply.js"></script>
</head>

 <body class="easyui-layout">
 	<div class="easyui-panel" style="height:754px;padding:10px;">
 		<div class="easyui-panel" style="padding:10px;">
		    <shiro:hasPermission name="partsWarehouse:select">
		    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="$('#search').dialog('open')">查询</a>
		    </shiro:hasPermission>
		    <shiro:hasPermission name="partsWarehouse:update">
		    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="javascript:loadRemote()">申请领料</a>
		    </shiro:hasPermission>
		</div>
		<table id="dg" style="width:100%;height:400px" class="easyui-datagrid" data-options="url:'${pageContext.request.contextPath}/apply/selectDatas.do',method:'post',rownumbers:true,pagination:true,border:true,singleSelect:true">
		</table>
	</div>
    
    
	<div id="search" class="easyui-dialog" title="查询条件" 
		data-options="modal:true,closed:true,iconCls:'icon-search',buttons:'#search-dlg-buttons'" 
		style="width:500px;height:50%;padding:10px;">
		<div style="padding:10px 0 10px 60px">
			<form id="searchForm" method="post">
	            <table>
	                
	                <tr>
	                    <td>物料编码:</td>
	                    <td><input class="easyui-textbox" type="text" name="material_code" data-options="required:false"></input></td>
	                </tr>
	                <tr>
	                    <td>机型:</td>
	                    <td><input class="easyui-textbox" type="text" name="model" data-options="required:false"></input></td>
	                </tr>
	            </table>
	            
	         </form>
         </div>
		<div id="search-dlg-buttons">
	        <a id="search-save-button" href="javascript:void(0)" class="easyui-linkbutton">查询</a>
	        <a id="search-close-button" href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#search').dialog('close').form('clear')">关闭</a>
	    </div>
    </div>
    
    
    
    <div id="edit" class="easyui-dialog" title="申请领料" 
    	data-options="modal:true,closed:true,iconCls:'icon-edit',buttons:'#edit-dlg-buttons'" 
		style="width:500px;height:90%;padding:10px;">
		<div style="padding:10px 0 10px 60px">
			<form id="editForm" method="post">
	           <table>
	                <tr>
	                    <td>编号:</td>
	                    <td><input class="easyui-textbox" type="text" readonly="readonly" name="id" ></input></td>
	                </tr>
	                <tr>
	                    <td>物料编码:</td>
	                    <td><input class="easyui-textbox" type="text" readonly="readonly" name="material_code" ></input></td>
	                </tr>
	                <tr>
	                    <td>机型:</td>
	                    <td><input class="easyui-textbox" type="text" readonly="readonly" name="model" ></input></td>
	                </tr>
	                <tr>
	                    <td>描述:</td>
	                    <td><input class="easyui-textbox" type="text" readonly="readonly" name="description" ></input></td>
	                </tr>
	                <tr>
	                    <td>库存良品:</td>
	                    <td><input class="easyui-numberbox" type="text" readonly="readonly" name="ichiban" ></input></td>
	                </tr>
	               <tr>
	                    <td>领取数量:</td>
	                    <td><input class="easyui-numberbox" type="text"  name="receive_num" data-options="required:true,min:0"></input></td>
	                </tr>
	            </table>
	            
	         </form>
         </div>
		<div id="edit-dlg-buttons">
	        <a id="edit-save-button" href="javascript:void(0)" class="easyui-linkbutton">保存</a>
	        <a id="edit-close-button" href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#edit').dialog('close')">关闭</a>
	    </div>
    </div>
    
    
</body>
</html>