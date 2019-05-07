<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<title>快递分捡</title>
	<c:import url="/header.jsp"></c:import>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/expressSort.js"></script>
</head>


 <body class="easyui-layout">
 	<div class="easyui-panel" style="height:754px;padding:10px;">
 		<div class="easyui-panel" style="padding:10px;">
 			<shiro:hasPermission name="express:add">
		        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="$('#add').dialog('open')">添加</a>
		    </shiro:hasPermission>
		    <shiro:hasPermission name="express:select">
		    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="$('#search').dialog('open')">查询</a>
		    </shiro:hasPermission>
		    <shiro:hasPermission name="express:update">
		    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="javascript:loadRemote()">修改</a>
		    </shiro:hasPermission>
		    <shiro:hasPermission name="express:delete">
		    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="javascript:removeConfirm()">删除</a>
		    </shiro:hasPermission>
		</div>
		<table id="dg" style="width:100%;height:400px" class="easyui-datagrid" data-options="url:'${pageContext.request.contextPath}/expressSort/selectDatas.do',method:'post',rownumbers:true,pagination:true,border:true,singleSelect:true">
		</table>
	</div>
    
	<div id="add" class="easyui-dialog" title="快递分捡录入" 
		data-options="modal:true,closed:true,iconCls:'icon-add',buttons:'#add-dlg-buttons'" 
		style="width:500px;height:90%;padding:10px;">
		<div style="padding:10px 0 10px 60px">
			<form id="addForm" method="post">
	            <table>
	                <tr>
	                    <td>快递单号:</td>
	                    <td><input class="easyui-textbox" type="text" name="exp_num" data-options="required:false"></input></td>
	                </tr>
	                <tr>
	                    <td>机型:</td>
	                    <td><input class="easyui-textbox" type="text" name="model" data-options="required:false"></input></td>
	                </tr>
	                <tr>
	                    <td>物料类型:</td>
	                    <td>
	                    	<select id="material_type" class="easyui-combobox" name="material_type" style="width:170px;">
					        	<option value="1" selected="selected">整机</option>
					        	<option value="2">主机</option>
					        	<option value="3">配件</option>
					    	</select>
						</td>
	                </tr>
	                <tr>
	                    <td>数量:</td>
	                    <td><input id="quantity" class="easyui-numberbox" type="text" name="quantity" data-options="required:false" disabled></input></td>
	                </tr>
	                <tr>
	                    <td>来料故障:</td>
	                    <td><input class="easyui-textbox" type="text" name="fault" data-options="required:false"></input></td>
	                </tr>
	                <tr>
	                    <td>类型:</td>
	                    <td>
	                    	<select id="pay_type" class="easyui-combobox" name="type" style="width:170px;">
					        	<option value="1" selected="selected">保内</option>
					        	<option value="2">保外</option>
					    	</select>
						</td>
	                </tr>
	                <tr>
	                    <td>备注:</td>
	                    <td><input class="easyui-textbox" type="text" name="remark" data-options="required:false"></input></td>
	                </tr>
	                <tr>
	                    <td>颜色:</td>
	                    <td><input class="easyui-textbox" type="text" name="color" data-options="required:false"></input></td>
	                </tr>
	                <tr>
	                    <td>SN:</td>
	                    <td><input id="sn" class="easyui-textbox" type="text" name="sn" data-options="multiline:true,required:false" style="height: 100px"></input></td>
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
		style="width:500px;height:90%;padding:10px;">
		
		<div style="padding:10px 0 10px 60px">
			<form id="searchForm" method="post">
	            <table>
	               <tr>
	                    <td>快递单号:</td>
	                    <td><input class="easyui-textbox" type="text" name="exp_num" data-options="required:false"></input></td>
	                </tr>
	                <tr>
	                    <td>机型:</td>
	                    <td><input class="easyui-textbox" type="text" name="model" data-options="required:false"></input></td>
	                </tr>
	                <tr>
	                    <td>物料类型:</td>
	                    <td>
	                    	<select id="material_type" class="easyui-combobox" name="material_type" data-options="value:'',editable:false" style="width:170px;">
					        	<option value="1">整机</option>
					        	<option value="2">主机</option>
					        	<option value="3">配件</option>
					    	</select>
						</td>
	                </tr>
	                
	               
	                <tr>
	                    <td>类型:</td>
	                    <td>
	                    	<select id="pay_type" class="easyui-combobox" name="type" data-options="value:'',editable:false" style="width:170px;">
					        	<option value="1">保内</option>
					        	<option value="2">保外</option>
					    	</select>
						</td>
	                </tr>
	               
	                <tr>
	                    <td>颜色:</td>
	                    <td><input class="easyui-textbox" type="text" name="color" data-options="required:false"></input></td>
	                </tr>
	                
	            </table>
	            
	         </form>
         </div>
		<div id="search-dlg-buttons">
	        <a id="search-save-button" href="javascript:void(0)" class="easyui-linkbutton">查询</a>
	        <a id="search-close-button" href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#search').dialog('close').form('clear')">关闭</a>
	    </div>
    </div>
    
    
    <div id="edit" class="easyui-dialog" title="修改快递分捡信息" 
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
	                    <td>快递单号:</td>
	                    <td><input class="easyui-textbox" type="text" name="exp_num" data-options="required:false"></input></td>
	                </tr>
	                <tr>
	                    <td>机型:</td>
	                    <td><input class="easyui-textbox" type="text" name="model" data-options="required:false"></input></td>
	                </tr>
	                <tr>
	                    <td>物料类型:</td>
	                    <td>
	                    	<select id="material_type" class="easyui-combobox" name="material_type" style="width:170px;">
					        	<option value="1" selected="selected">整机</option>
					        	<option value="2">主机</option>
					        	<option value="3">配件</option>
					    	</select>
						</td>
	                </tr>
	                <tr>
	                    <td>数量:</td>
	                    <td><input id="quantity" class="easyui-numberbox" type="text" name="quantity" data-options="required:false"></input></td>
	                </tr>
	                <tr>
	                    <td>来料故障:</td>
	                    <td><input class="easyui-textbox" type="text" name="fault" data-options="required:false"></input></td>
	                </tr>
	                <tr>
	                    <td>类型:</td>
	                    <td>
	                    	<select id="pay_type" class="easyui-combobox" name="type" style="width:170px;">
					        	<option value="1" selected="selected">保内</option>
					        	<option value="2">保外</option>
					    	</select>
						</td>
	                </tr>
	                <tr>
	                    <td>备注:</td>
	                    <td><input class="easyui-textbox" type="text" name="remark" data-options="required:false"></input></td>
	                </tr>
	                <tr>
	                    <td>颜色:</td>
	                    <td><input class="easyui-textbox" type="text" name="color" data-options="required:false"></input></td>
	                </tr>
	                <tr>
	                    <td>SN:</td>
	                    <td><input id="sn" class="easyui-textbox" type="text" name="sn" data-options="multiline:true,required:false" style="height: 100px"></input></td>
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