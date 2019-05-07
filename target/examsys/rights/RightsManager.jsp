<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<title>权限管理</title>
	<c:import url="/header.jsp"></c:import>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/rights.js"></script>
</head>
  
 <body class="easyui-layout">
 	<div class="easyui-panel" style="height:754px;padding:10px;">
 		<div class="easyui-panel" style="padding:10px;">
	        <shiro:hasPermission name="rights:add">
	        	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="$('#add').dialog('open')">添加</a>
	        </shiro:hasPermission>
	        
	        <shiro:hasPermission name="rights:select">
	        	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="$('#search').dialog('open')">查询</a>
	        </shiro:hasPermission>
	        
	       <shiro:hasPermission name="rights:update">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="javascript:loadRemote()">修改</a>
			</shiro:hasPermission>
			
		
			<shiro:hasPermission name="rights:delete">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="javascript:removeConfirm()">删除</a>
			</shiro:hasPermission>
		</div>
		<table id="dg" style="width:100%;height:400px" class="easyui-datagrid" data-options="url:'${pageContext.request.contextPath}/rights/selectDatas.do',method:'post',rownumbers:true,pagination:true,border:true,singleSelect:true">
		</table>
	</div>
	
	<div id="add" class="easyui-dialog" title="添加" 
		data-options="modal:true,closed:true,iconCls:'icon-add',buttons:'#add-dlg-buttons'" 
		style="width:500px;height:95%;padding:10px;">
		<div style="padding:10px 0 10px 60px">
			<form id="addForm" method="post">
	            <table>
	                <tr>
	                    <td>权限名称:</td>
	                    <td><input class="easyui-validatebox" type="text" name="rightName" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td>权限级别:</td>
	                    <td>
	                    	<select class="easyui-combobox" name="rightType" style="width:170px;">
					        	<option value="1" selected="selected">一级</option>
					        	<option value="2">二级</option>
					    	</select>
	                    </td>
	                </tr>
	               
	                <tr>
	                    <td>选项卡url值:</td>
	                    <td><input class="easyui-validatebox" type="text" name="url" ></input></td>
	                </tr>
	                <tr>
	                    <td>父级菜单:</td>
		                <td><input class="easyui-combobox"
				            name="pid"
				            data-options="
		                    url:'${pageContext.request.contextPath}/rights/rightsDatas',
		                    valueField:'rid',
		                    textField:'rightName',
		                    panelHeight:'300'
		            		"></td>
	                </tr>
	                <tr>
	                    <td>权限项代码:</td>
	                    <td><input class="easyui-validatebox" type="text" name="rightCode" ></input></td>
	                </tr>
	            </table>
	            
	         </form>
         </div>
         
         <div id="add-dlg-buttons">
	        <a id="add-save-button" href="javascript:void(0)" class="easyui-linkbutton">保存</a>
	        <a id="add-close-button" href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#add').dialog('close').form('clear')">关闭</a>
	    </div>
    </div>
	
	<div id="search" class="easyui-dialog" title="查询条件" 
		data-options="modal:true,closed:true,iconCls:'icon-search',buttons:'#search-dlg-buttons'" 
		style="width:500px;height:200px;padding:10px;">
		
		<div style="padding:10px 0 10px 60px">
			<form id="searchForm" method="post">
	            <table>
	                <tr>
	                    <td>权限名称:</td>
	                    <td><input class="easyui-validatebox" type="text" name="rightName"></input></td>
	                </tr>
	                <tr>
	                    <td>url代码:</td>
	                    <td><input class="easyui-validatebox" type="text" name="url"></input></td>
	                </tr>
	               
	            </table>
	            
	         </form>
         </div>
		<div id="search-dlg-buttons">
	        <a id="search-save-button" href="javascript:void(0)" class="easyui-linkbutton">查询</a>
	        <a id="search-close-button" href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#search').dialog('close').form('clear')">关闭</a>
	    </div>
    </div>
    
    <div id="edit" class="easyui-dialog" title="修改" 
    	data-options="modal:true,closed:true,iconCls:'icon-edit',buttons:'#edit-dlg-buttons'" 
		style="width:500px;height:95%;padding:10px;">
		<div style="padding:10px 0 10px 60px">
			<form id="editForm" method="post">
	            <table>
	            	<tr>
	                    <td>编号:</td>
	                    <td><input class="easyui-validatebox" type="text" readonly="readonly" name="rid" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td>权限名称:</td>
	                    <td><input class="easyui-validatebox" type="text" name="rightName" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td>权限级别:</td>
	                    <td>
	                    	<select id="eidt_type" class="easyui-combobox" name="rightType" style="width:170px;">
					        	<option value="1" selected="selected">一级</option>
					        	<option value="2">二级</option>
					    	</select>
	                    </td>
	                </tr>
	               
	                <tr>
	                    <td>选项卡url值:</td>
	                    <td><input class="easyui-validatebox" type="text" name="url" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td>父级菜单:</td>
		                <td><input class="easyui-combobox"
				            name="pid"
				            id="edit_pid"
				            data-options="
		                    url:'${pageContext.request.contextPath}/rights/rightsDatas',
		                    valueField:'rid',
		                    textField:'rightName',
		                    panelHeight:'300'
		            		"></td>
	                </tr>
	                 <tr>
	                    <td>权限项代码:</td>
	                    <td><input class="easyui-validatebox" type="text" name="rightCode"></input></td>
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
