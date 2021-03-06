<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<title>咨询师跟单记录管理</title>
	<c:import url="/header.jsp"></c:import>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/consultRecord.js"></script>
</head>
  
 <body class="easyui-layout">
 	<div class="easyui-panel" style="height:754px;padding:10px;">
 		<div class="easyui-panel" style="padding:10px;">
 			<shiro:hasPermission name="consultRecord:add">
	        	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="$('#add').dialog('open')">添加</a>
	        </shiro:hasPermission>
	        
	        <shiro:hasPermission name="consultRecord:select">
	        	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="$('#search').dialog('open')">查询</a>
	        </shiro:hasPermission>
	        
	        <shiro:hasPermission name="consultRecord:update">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="javascript:loadRemote()">修改</a>
			</shiro:hasPermission>
			
			<shiro:hasPermission name="consultRecord:delete">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="javascript:removeConfirm()">删除</a>
			</shiro:hasPermission>
			
		</div>
		<table id="dg" style="width:100%;height:400px" class="easyui-datagrid" data-options="url:'${pageContext.request.contextPath}/consultRecord/selectDatas.do',method:'post',rownumbers:true,pagination:true,border:true,singleSelect:true">
		</table>
	</div>
	
	<div id="add" class="easyui-dialog" title="添加系统功能功" 
		data-options="modal:true,closed:true,iconCls:'icon-add',buttons:'#add-dlg-buttons'" 
		style="width:500px;height:200px;padding:10px;">
		<div style="padding:10px 0 10px 60px">
			<form id="addForm" method="post">
	            <table>
	                <tr>
	                    <td>客户:</td>
		                <td><input class="easyui-combobox"
				            name="customid"
				            data-options="
		                    url:'${pageContext.request.contextPath}/consultRecord/customDatas',
		                    valueField:'id',
		                    textField:'name',
		                    panelHeight:'auto'
		            		"></td>
	                </tr>
	                <tr>
	                    <td>咨询师:</td>
		                <td><input class="easyui-combobox"
				            name="consultManid"
				            data-options="
		                    url:'${pageContext.request.contextPath}/consultRecord/employeeDatas',
		                    valueField:'id',
		                    textField:'username',
		                    panelHeight:'auto'
		            		"></td>
	                </tr>
	               <tr>
	                    <td>咨询状态:</td>
	                    <td>
	                    	<select class="easyui-combobox" name="consultStatu" style="width:170px;">
					        	<option value="0" selected="selected">新增</option>
					        	<option value="1">紧跟</option>
					        	<option value="2">已报名</option>
					        	<option value="3">死单</option>
					        	<option value="4">报名后退费</option>
					    	</select>
	                    </td>
	                </tr>
	                
	                <tr>
	                    <td>咨询日期:</td>
	                    <td><input class="easyui-validatebox" type="date" name="consultDate" data-options="required:true"></input></td>
	                </tr>
	                
	                <tr>
	                    <td>咨询备注:</td>
	                    <td><input class="easyui-validatebox" type="text" name="result" ></input></td>
	                </tr>
	            </table>
	            
	         </form>
         </div>
         
         <div id="add-dlg-buttons">
	        <a id="add-save-button" href="javascript:void(0)" class="easyui-linkbutton">保存</a>
	        <a id="add-close-button" href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#add').dialog('close')">关闭</a>
	    </div>
    </div>
	
	<div id="search" class="easyui-dialog" title="查询条件" 
		data-options="modal:true,closed:true,iconCls:'icon-search',buttons:'#search-dlg-buttons'" 
		style="width:500px;height:200px;padding:10px;">
		
		<div style="padding:10px 0 10px 60px">
			<form id="searchForm" method="post">
	            <table>
	                 <tr>
	                    <td>客户:</td>
		                <td><input class="easyui-combobox"
				            name="customid"
				            data-options="
		                    url:'${pageContext.request.contextPath}/consultRecord/customDatas',
		                    valueField:'id',
		                    textField:'name',
		                    panelHeight:'auto'
		            		"></td>
	                </tr>
	                <tr>
	                    <td>咨询师:</td>
		                <td><input class="easyui-combobox"
				            name="consultManid"
				            data-options="
		                    url:'${pageContext.request.contextPath}/consultRecord/employeeDatas',
		                    valueField:'id',
		                    textField:'username',
		                    panelHeight:'auto'
		            		"></td>
	                </tr>
	               <tr>
	                    <td>咨询状态:</td>
	                    <td>
	                    	<select class="easyui-combobox" name="consultStatu" style="width:170px;">
					        	<option value="0" selected="selected">新增</option>
					        	<option value="1">紧跟</option>
					        	<option value="2">已报名</option>
					        	<option value="3">死单</option>
					        	<option value="4">报名后退费</option>
					    	</select>
	                    </td>
	                </tr>
	            </table>
	            
	         </form>
         </div>
		<div id="search-dlg-buttons">
	        <a id="search-save-button" href="javascript:void(0)" class="easyui-linkbutton">查询</a>
	        <a id="search-close-button" href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#search').dialog('close')">关闭</a>
	    </div>
    </div>
    
    <div id="edit" class="easyui-dialog" title="修改系统功能功" 
    	data-options="modal:true,closed:true,iconCls:'icon-edit',buttons:'#edit-dlg-buttons'" 
		style="width:500px;height:200px;padding:10px;">
		<div style="padding:10px 0 10px 60px">
			<form id="editForm" method="post">
	            <table>
	            
	           	    <tr>
	                    <td>编号:</td>
	                    <td><input class="easyui-validatebox" type="text" readonly="readonly" name="id" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td>客户:</td>
		                <td><input class="easyui-combobox"
				            name="customid"
				            id="edit_custom"
				            data-options="
		                    url:'${pageContext.request.contextPath}/consultRecord/customDatas',
		                    valueField:'id',
		                    textField:'name',
		                    panelHeight:'auto'
		            		"></td>
	                </tr>
	                <tr>
	                    <td>咨询师:</td>
		                <td><input class="easyui-combobox"
				            name="consultManid"
				            id="edit_employee"
				            data-options="
		                    url:'${pageContext.request.contextPath}/consultRecord/employeeDatas',
		                    valueField:'id',
		                    textField:'username',
		                    panelHeight:'auto'
		            		"></td>
	                </tr>
	               <tr>
	                    <td>咨询状态:</td>
	                    <td>
	                    	<select id="edit_consult" class="easyui-combobox" name="consultStatu" style="width:170px;">
					        	<option value="" ></option>
					        	<option value="0" >新增</option>
					        	<option value="1">紧跟</option>
					        	<option value="2">已报名</option>
					        	<option value="3">死单</option>
					        	<option value="4">报名后退费</option>
					    	</select>
	                    </td>
	                </tr>
	                
	                <tr>
	                    <td>咨询日期:</td>
	                    <td><input class="easyui-validatebox" type="date" name="consultDate" data-options="required:true"></input></td>
	                </tr>
	                
	                <tr>
	                    <td>咨询备注:</td>
	                    <td><input class="easyui-validatebox" type="text" name="result" ></input></td>
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
