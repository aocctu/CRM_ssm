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
		    <shiro:hasPermission name="expresReceive:select">
		    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="$('#search').dialog('open')">查询</a>
		    </shiro:hasPermission> 
		    <shiro:hasPermission name="expresReceive:update">
		    	<a href="javascript:void(0)" class="easyui-linkbutton"  onclick="javascript:loadRemote()">快递接收</a>
		    </shiro:hasPermission>
		   <%--  <shiro:hasPermission name="express:delete">
		    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="javascript:removeConfirm()">删除</a>
		    </shiro:hasPermission>  --%>
		</div>
		<table id="dg" style="width:100%;height:400px" class="easyui-datagrid" data-options="url:'${pageContext.request.contextPath}/expressReceive/selectDatas.do',method:'post',rownumbers:true,pagination:true,border:true,singleSelect:false">
		</table>
	</div>
    
    
    
    
	<div id="search" class="easyui-dialog" title="查询条件" 
		data-options="modal:true,closed:true,iconCls:'icon-search',buttons:'#search-dlg-buttons'" 
		style="width:500px;height:90%;padding:10px;">
		
		<div style="padding:10px 0 10px 60px">
			<form id="searchForm" method="post">
	            <table>
	                 <tr>
	                    <td>快递单号:</td>
	                    <td><input class="easyui-textbox" type="text" name="exp_num" ></input></td>
	                </tr>
	                
	                <tr>
	                    <td>快递员名字:</td>
	                    <td><input class="easyui-textbox" type="text" name="exp_name"></input></td>
	                </tr>
	                <tr>
	                    <td>付款类型:</td>
	                    <td>
	                    	<select class="easyui-combobox" name="pay_type" style="width:170px;" data-options="value:'',editable:false">
					        	<option value="寄付" >寄付</option>
					        	<option value="到付">到付</option>
					    	</select>
						</td>
	                </tr>
	                <tr>
	                    <td>快递公司:</td>
		               <td><input class="easyui-combobox"
				            name="expressCompany.id"
				            data-options="
		                    url:'${pageContext.request.contextPath}/express/expressCompanyDatas',
		                    valueField:'id',
		                    textField:'exp_company',
		                    panelHeight:'300'
		            		"></td>
	                </tr>
	                <tr>
	                    <td>快递状态:</td>
	                    <td>
	                    	<select class="easyui-combobox" name="exp_status" style="width:170px;" data-options="value:'',editable:false">
					        	<option value="已录入" >已录入</option>
					        	<option value="已接收">已接收</option>
					    	</select>
						</td>
	                </tr>
	               
	            </table>
	            
	         </form>
         </div>
		<div id="search-dlg-buttons">
	        <a id="search-save-button" href="javascript:void(0)" class="easyui-linkbutton">查询</a>
	        <a id="search-close-button" href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#search').dialog('close').form('clear')">关闭</a>
	    </div>
    </div>
    
    
</body>
</html>