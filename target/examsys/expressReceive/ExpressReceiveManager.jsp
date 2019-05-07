<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<title>快递管理</title>
	<c:import url="/header.jsp"></c:import>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/expressReceive.js"></script>
</head>

 <body class="easyui-layout">
 	<div class="easyui-panel" style="height:754px;padding:10px;">
 		<div class="easyui-panel" style="padding:10px;">
 			 <%-- <shiro:hasPermission name="express:add">
		        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="$('#add').dialog('open')">添加</a>
		    </shiro:hasPermission>--%>
		    <shiro:hasPermission name="express:select">
		    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="$('#search').dialog('open')">查询</a>
		    </shiro:hasPermission> 
		    <shiro:hasPermission name="express:update">
		    	<a href="javascript:void(0)" class="easyui-linkbutton"  onclick="javascript:loadRemote()">快递接收</a>
		    </shiro:hasPermission>
		   <%--  <shiro:hasPermission name="express:delete">
		    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="javascript:removeConfirm()">删除</a>
		    </shiro:hasPermission>  --%>
		</div>
		<table id="dg" style="width:100%;height:400px" class="easyui-datagrid" data-options="url:'${pageContext.request.contextPath}/expressReceive/selectDatas.do',method:'post',rownumbers:true,pagination:true,border:true,singleSelect:true">
		</table>
	</div>
    
	<div id="add" class="easyui-dialog" title="快递信息录入" 
		data-options="modal:true,closed:true,iconCls:'icon-add',buttons:'#add-dlg-buttons'" 
		style="width:500px;height:100%;padding:10px;">
		<div style="padding:10px 0 10px 60px">
			<form id="addForm" method="post">
	            <table>
	                <tr>
	                    <td>快递员手机号:</td>
	                    <td><input class="easyui-validatebox" type="text" name="exp_iphone" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td>快递员名字:</td>
	                    <td><input class="easyui-validatebox" type="text" name="exp_name" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td>付款类型:</td>
	                    <td>
	                    	<select class="easyui-combobox" name="pay_type" style="width:170px;">
					        	<option value="1" selected="selected">寄付</option>
					        	<option value="0">到付</option>
					    	</select>
						</td>
	                </tr>
	                <tr>
	                    <td>费用:</td>
	                    <td><input class="easyui-validatebox" type="text" name="exp_cost" data-options="required:false"></input></td>
	                </tr>
	                <tr>
	                    <td>快递公司:</td>
		                <td><input class="easyui-combobox"
				            name="exp_company_id"
				            data-options="
		                    url:'${pageContext.request.contextPath}/express/expressCompanyDatas',
		                    valueField:'id',
		                    textField:'exp_company',
		                    panelHeight:'auto'
		            		"></td>
	                </tr>
	                <tr>
	                    <td>快递单号:</td>
	                    <td><input class="easyui-validatebox" type="text" name="exp_num" data-options="required:true"></input></td>
	                </tr>
	                
	                
	                <tr>
	                    <td>备注:</td>
	                    <td><input class="easyui-validatebox" type="text" name="remark" data-options="required:false"></input></td>
	                </tr>
	            </table>
	            
	         </form>
         </div>
         
         <div id="add-dlg-buttons">
	        <a id="add-save-button" href="javascript:void(0)" class="easyui-linkbutton">保存</a>
	        <a id="add-close-button" href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#add').dialog('close')">关闭</a>
	    </div>
    </div>
    
    
    <div id="edit" class="easyui-dialog" title="修改快递信息" 
    	data-options="modal:true,closed:true,iconCls:'icon-edit',buttons:'#edit-dlg-buttons'" 
		style="width:500px;height:100%;padding:10px;">
		<div style="padding:10px 0 10px 60px">
			<form id="editForm" method="post">
	           <table>
	           		
	                <tr>
	                    <td>编号:</td>
	                    <td><input class="easyui-validatebox" type="text" readonly="readonly" name="id" ></input></td>
	                </tr>
	                <tr>
	                    <td>快递员手机号:</td>
	                    <td><input class="easyui-validatebox" type="text" name="exp_iphone" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td>快递员名字:</td>
	                    <td><input class="easyui-validatebox" type="text" name="exp_name" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td>付款类型:</td>
	                    <td>
	                    	<select class="easyui-combobox" name="pay_type" style="width:170px;">
					        	<option value="1" selected="selected">寄付</option>
					        	<option value="0">到付</option>
					    	</select>
						</td>
	                </tr>
	                <tr>
	                    <td>费用:</td>
	                    <td><input class="easyui-validatebox" type="text" name="exp_cost" data-options="required:false"></input></td>
	                </tr>
	                <tr>
	                    <td>快递公司:</td>
		                <td><input class="easyui-combobox"
				            name="exp_company_id"
				            id="edit_exp_company_id"
				            data-options="
		                    url:'${pageContext.request.contextPath}/express/expressCompanyDatas',
		                    valueField:'id',
		                    textField:'exp_company',
		                    panelHeight:'auto'
		            		"></td>
	                </tr>
	                <tr>
	                    <td>快递单号:</td>
	                    <td><input class="easyui-validatebox" type="text" name="exp_num" data-options="required:true"></input></td>
	                </tr>
	                
	                
	                <tr>
	                    <td>备注:</td>
	                    <td><input class="easyui-validatebox" type="text" name="remark" data-options="required:false"></input></td>
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