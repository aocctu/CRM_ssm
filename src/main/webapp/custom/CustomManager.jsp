<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<title>客户基础信息</title>
	<c:import url="/header.jsp"></c:import>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/custom.js"></script>
</head>
 <script type="text/javascript">
	$(function(){
		$.get("${pageContext.request.contextPath}/ttuu.do",null,function(data,state){
			$("#loadImg").attr("src","${pageContext.request.contextPath}/DisplayChart?filename="+data);
		});
		
	});
</script> 
 <body class="easyui-layout">
 	<div class="easyui-panel" style="height:754px;padding:10px;">
 		<div class="easyui-panel" style="padding:10px;">
 			<shiro:hasPermission name="custom:add">
	        	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="$('#add').dialog('open')">添加</a>
	        </shiro:hasPermission>
	        
	        <shiro:hasPermission name="custom:select">
	        	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="$('#search').dialog('open')">查询</a>
	        </shiro:hasPermission>
	        
	        <shiro:hasPermission name="custom:update">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="javascript:loadRemote()">修改</a>
			</shiro:hasPermission>
			
			<shiro:hasPermission name="custom:delete">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="javascript:removeConfirm()">删除</a>
			</shiro:hasPermission>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="" onclick="$('#addJf').dialog('open')" style="margin-left: 30px">客户状态报表图</a>
		</div>
		<table id="dg" style="width:100%;height:400px" class="easyui-datagrid" data-options="url:'${pageContext.request.contextPath}/custom/selectDatas.do',method:'post',rownumbers:true,pagination:true,border:true,singleSelect:true">
		</table>
	</div>
	
	<div id="add" class="easyui-dialog" title="添加系统功能功" 
		data-options="modal:true,closed:true,iconCls:'icon-add',buttons:'#add-dlg-buttons'" 
		style="width:500px;height:200px;padding:10px;">
		<div style="padding:10px 0 10px 60px">
			<form id="addForm" method="post">
	            <table>
	                <tr>
	                    <td>客户姓名:</td>
	                    <td><input class="easyui-validatebox" type="text" name="name" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td>教育水平:</td>
	                    <td><input class="easyui-validatebox" type="text" name="education" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td>手机:</td>
	                    <td><input class="easyui-validatebox" type="text" name="phoneNo" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td>QQ:</td>
	                    <td><input class="easyui-validatebox" type="text" name="QQ" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td>邮箱:</td>
	                    <td><input class="easyui-validatebox" type="text" name="email" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td>客户状态:</td>
	                    <td>
	                    	<select class="easyui-combobox" name="customStatu" style="width:170px;">
					        	<option value="0" selected="selected">新增未上门</option>
					        	<option value="1">新增已上门</option>
					        	<option value="2">销售跟进中</option>
					        	<option value="3">咨询跟进中</option>
					        	<option value="4">死单</option>
					        	<option value="5">已报名</option>
					    	</select>
	                    </td>
	                </tr>
	                <tr>
	                    <td>邀请人姓名:</td>
	                    <td><input class="easyui-validatebox" type="text" name="inviteName" data-options="required:true"></input></td>
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
	                    <td>编号:</td>
	                    <td><input class="easyui-validatebox" type="text" name="id" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td>客户姓名:</td>
	                    <td><input class="easyui-validatebox" type="text" name="name" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td>客户状态:</td>
	                    <td>
	                    	<select class="easyui-combobox" name="customStatu" style="width:170px;">
					        	<option value="" ></option>
					        	<option value="0" >新增未上门</option>
					        	<option value="1">新增已上门</option>
					        	<option value="2">销售跟进中</option>
					        	<option value="3">咨询跟进中</option>
					        	<option value="4">死单</option>
					        	<option value="5">已报名</option>
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
	                    <td>客户姓名:</td>
	                    <td><input class="easyui-validatebox" type="text" name="name" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td>教育水平:</td>
	                    <td><input class="easyui-validatebox" type="text" name="education" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td>手机:</td>
	                    <td><input class="easyui-validatebox" type="text" name="phoneNo" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td>QQ:</td>
	                    <td><input class="easyui-validatebox" type="text" name="QQ" ></input></td>
	                </tr>
	                <tr>
	                    <td>邮箱:</td>
	                    <td><input class="easyui-validatebox" type="text" name="email" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td>客户状态:</td>
	                    <td>
	                    	<select id="edit_statu" class="easyui-combobox" name="customStatu" style="width:170px;">
					        	<option value="0" selected="selected">新增未上门</option>
					        	<option value="1">新增已上门</option>
					        	<option value="2">销售跟进中</option>
					        	<option value="3">咨询跟进中</option>
					        	<option value="4">死单</option>
					        	<option value="5">已报名</option>
					    	</select>
	                    </td>
	                </tr>
	                <tr>
	                    <td>邀请人姓名:</td>
	                    <td><input class="easyui-validatebox" type="text" name="inviteName" data-options="required:true"></input></td>
	                </tr>
	               
	            </table>
	            
	         </form>
         </div>
		<div id="edit-dlg-buttons">
	        <a id="edit-save-button" href="javascript:void(0)" class="easyui-linkbutton">保存</a>
	        <a id="edit-close-button" href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#edit').dialog('close')">关闭</a>
	    </div>
    </div>
    
    <div id="addJf" class="easyui-dialog" title="图" 
		data-options="modal:true,closed:true" 
		style="width:800px;height:580px;padding:10px;">
		<div style="padding:10px 0 10px 60px">
			<form id="addForm" method="post">
	            
	            <img id="loadImg" src="" width="700" height="500" border="0"/>
	            
	         </form>
         </div>
    </div>
    
</body>
</html>
