<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<title>分检入库</title>
	<c:import url="/header.jsp"></c:import>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/expressSort.js"></script>
</head>
<script type="text/javascript">
// 导出


</script>

 <body class="easyui-layout">
 	<div class="easyui-panel" style="height:754px;padding:10px;">
 		<div class="easyui-panel" style="padding:10px;">
 			<shiro:hasPermission name="expressSort:add">
		        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="$('#add').dialog('open')">分拣录入</a>
		    </shiro:hasPermission>
		    <shiro:hasPermission name="expressSort:examine">
		    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="javascript:examine()">来料检验</a>
		    </shiro:hasPermission>
		    <shiro:hasPermission name="expressSort:storage">
		    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="javascript:storage()">入库</a>
		    </shiro:hasPermission>
		    <shiro:hasPermission name="expressSort:select">
		    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="$('#search').dialog('open')">查询</a>
		    </shiro:hasPermission>
		    <shiro:hasPermission name="expressSort:update">
		    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="javascript:loadRemote()">修改</a>
		    </shiro:hasPermission>
		    <shiro:hasPermission name="expressSort:delete">
		    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="javascript:removeConfirm()">删除</a>
		    </shiro:hasPermission>
		</div>
		<table id="dg" style="width:100%;height:400px" class="easyui-datagrid" data-options="url:'${pageContext.request.contextPath}/expressSort/selectDatas.do',method:'post',rownumbers:true,pagination:true,border:true,singleSelect:false">
		</table>
	</div>
    
	<div id="add" class="easyui-dialog" title="快递分拣录入" 
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
	                
	                 <tr>
	                    <td>维修类型:</td>
	                    <td>
	                    	<select  class="easyui-combobox" name="repair_type" data-options="value:'',panelHeight:'150',editable:false,required:false" style="width:170px;">
					        	<option value="1">待一级翻新</option>
					        	<option value="2">待二级翻新</option>
					        	<option value="3">待三级翻新</option>
					        	<option value="4">待维修</option>
					        	<option value="5">待报废</option>
					    	</select>
						</td>
	                </tr>
	                
	                <tr>
	                    <td>位置:</td>
	                    <td>
	                    	<select  class="easyui-combobox" name="position" data-options="value:'',panelHeight:'150',editable:false,required:false" style="width:170px;" >
					        	<option value="分拣录入">分拣录入</option>
					        	<option value="来料检验">来料检验</option>
					        	<option value="入库">入库</option>
					        	<option value="已寄送">已寄送</option>
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
    
    
    <div id="edit" class="easyui-dialog" title="修改快递分拣信息" 
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
					    	<!-- <input class="easyui-combobox" id="material_type" name="material_type" style="width: 170px" 
data-options="valueField: 'value',textField: 'label',editable:false,
data: [{label: '整机',value: '1'},{ label: '主机',value: '2'},{ label: '配件',value: '3'}]
,panelHeight:'auto',
onChange:function(newValue,oldValue){


}" /> -->
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
	                    <td><input id="sn" class="easyui-textbox" type="text" name="sn" data-options="required:false" ></input></td>
	                </tr>
	            </table>
	            
	         </form>
         </div>
		<div id="edit-dlg-buttons">
	        <a id="edit-save-button" href="javascript:void(0)" class="easyui-linkbutton">保存</a>
	        <a id="edit-close-button" href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#edit').dialog('close')">关闭</a>
	    </div>
    </div>
    
    
    <div id="edit2" class="easyui-dialog" title="来料检验" 
    	data-options="modal:true,closed:true,iconCls:'icon-edit',buttons:'#edit-dlg-buttons2'" 
		style="width:500px;height:90%;padding:10px;">
		<div style="padding:10px 0 10px 60px">
			<form id="editForm2" method="post">
	           <table>
	                <tr>
	                    <td>编号:</td>
	                    <td><input class="easyui-textbox" type="text" readonly="readonly" name="id" ></input></td>
	                </tr>
	                <tr>
	                    <td>快递单号:</td>
	                    <td><input class="easyui-textbox" type="text" readonly="readonly" name="exp_num" data-options="required:false"></input></td>
	                </tr>
	                <tr>
	                    <td>机型:</td>
	                    <td><input class="easyui-textbox" type="text" readonly="readonly" name="model" data-options="required:false"></input></td>
	                </tr>
	                <tr>
	                    <td>物料类型:</td>
	                    <td>
	                    	<select id="material_type" class="easyui-combobox" readonly="readonly" name="material_type" style="width:170px;">
					        	<option value="1" selected="selected">整机</option>
					        	<option value="2">主机</option>
					        	<option value="3">配件</option>
					    	</select>
						</td>
	                </tr>
	                <tr>
	                    <td>数量:</td>
	                    <td><input id="quantity" class="easyui-numberbox" type="text" readonly="readonly" name="quantity" data-options="required:false"></input></td>
	                </tr>
	                <tr>
	                    <td>来料故障:</td>
	                    <td><input class="easyui-textbox" type="text" name="fault" readonly="readonly" data-options="required:false"></input></td>
	                </tr>
	                <tr>
	                    <td>类型:</td>
	                    <td>
	                    	<select id="pay_type" class="easyui-combobox" name="type" readonly="readonly" style="width:170px;">
					        	<option value="1" selected="selected">保内</option>
					        	<option value="2">保外</option>
					    	</select>
						</td>
	                </tr>
	                <tr>
	                    <td>备注:</td>
	                    <td><input class="easyui-textbox" type="text" name="remark" readonly="readonly" data-options="required:false"></input></td>
	                </tr>
	                <tr>
	                    <td>颜色:</td>
	                    <td><input class="easyui-textbox" type="text" name="color" readonly="readonly" data-options="required:false"></input></td>
	                </tr>
	                <tr>
	                    <td>SN:</td>
	                    <td><input id="sn" class="easyui-textbox" type="text" readonly="readonly" name="sn" data-options="required:false" ></input></td>
	                </tr>
	                
	                 <tr>
	                    <td>确认故障:</td>
	                   <td><input class="easyui-textbox" type="text" name="confirm_fault" data-options="required:true" ></input></td>
	                </tr>
	                 <tr>
	                    <td>维修类型:</td>
	                    <td>
	                    	<select  class="easyui-combobox" name="repair_type" data-options="value:'',panelHeight:'150',editable:false,required:true" style="width:170px;">
					        	<option value="待一级翻新">待一级翻新</option>
					        	<option value="待二级翻新">待二级翻新</option>
					        	<option value="待三级翻新">待三级翻新</option>
					        	<option value="待维修">待维修</option>
					        	<option value="待报废">待报废</option>
					    	</select>
						</td>
	                </tr>
	            </table>
	            
	         </form>
         </div>
		<div id="edit-dlg-buttons2">
	        <a id="edit-save-button2" href="javascript:void(0)" class="easyui-linkbutton">保存</a>
	        <a id="edit-close-button2" href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#edit2').dialog('close').form('clear')">关闭</a>
	    </div>
    </div>
    
    
</body>
</html>