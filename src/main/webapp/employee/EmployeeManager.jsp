<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<title>员工管理</title>
	<c:import url="/header.jsp"></c:import>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/employee.js"></script>
</head>
  <script type="text/javascript">
	// 导出
  function outf(){
	    // 返回被选中的行 然后集成的其实是 对象数组  
	    var row = $('#dg').datagrid('getSelections');  
	    var i = 0;  
	    var string = "";  
	    for(i;i<row.length;i++){  
	        string += row[i].id;  
	        if(i < row.length-1){  
	            string += ',';  
	        }else{  
	            break;  
	        }  
	    }  
	    if(row.length>0){
	    	$.messager.confirm('信息提示', '确认要导出吗?', function(r){
	            if (r){
	
	            	window.open(contextPath+"/excel/out.do?ids="+string);
	            	
	            }else{
		            $.messager.alert('信息提示','请选择一条数据进行导出');
			    }
	        });
	    }
	}

	// 上传
  function inputf(){
		$("#dlg2").dialog('open').dialog('setTitle','使用表格批量导入数据');
	}
	function downloadTemplate(){
		window.open(contextPath+'/images/SptypeExporTemplate.xls');
	}
	function uploadFile(){
		$("#uploadForm").form("submit",{
			
			success:function(result){
					$.messager.alert("系统提示","已执行移植性操作");
					$("#dlg2").dialog("close");
					$("#dg").datagrid("reload");
			}
		});
	}
  </script>
 <body class="easyui-layout">
 	<div class="easyui-panel" style="height:754px;padding:10px;">
 		<div class="easyui-panel" style="padding:10px;">
 			<shiro:hasPermission name="employee:add">
	        	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="$('#add').dialog('open')">添加</a>
	        </shiro:hasPermission>
	        
	        <shiro:hasPermission name="employee:select">
	        	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="$('#search').dialog('open')">查询</a>
	        </shiro:hasPermission>
	        
	        <shiro:hasPermission name="employee:update">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="javascript:loadRemote()">修改</a>
			</shiro:hasPermission>
			
			<shiro:hasPermission name="employee:delete">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="javascript:removeConfirm()">删除</a>
			</shiro:hasPermission>
			
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:''" onclick="javascript:outf()" style="margin-left: 30px">数据导出</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:''" onclick="javascript:inputf()">数据导入</a>
		</div>
		<table id="dg" style="width:100%;height:400px" class="easyui-datagrid" data-options="url:'${pageContext.request.contextPath}/employee/selectDatas.do',method:'post',rownumbers:true,pagination:true,border:true,singleSelect:false">
		</table>
	</div>
	
	<div id="add" class="easyui-dialog" title="添加管理员角色" 
		data-options="modal:true,closed:true,iconCls:'icon-add',buttons:'#add-dlg-buttons'" 
		style="width:500px;height:300px;padding:10px;">
		<div style="padding:10px 0 10px 60px">
			<form id="addForm" method="post">
	            <table>
	                <tr>
	                    <td>用户名:</td>
	                    <td><input class="easyui-validatebox" type="text" name="username" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td>密码:</td>
	                    <td><input class="easyui-validatebox" type="text" name="pass" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td>昵称:</td>
	                    <td><input class="easyui-validatebox" type="text" name="nickname" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td>真实姓名:</td>
	                    <td><input class="easyui-validatebox" type="text" name="realname" data-options="required:true"></input></td>
	                </tr>
	                
	                <tr>
	                    <td>职位:</td>
		                <td><input class="easyui-combobox"
				            name="jobInfoid"
				            data-options="
		                    url:'${pageContext.request.contextPath}/employee/jobDatas',
		                    valueField:'id',
		                    textField:'job',
		                    panelHeight:'auto'
		            		"></td>
	                </tr>
	                
	                <tr>
	                    <td>部门:</td>
		                <td><input class="easyui-combobox"
				            name="departmentid"
				            data-options="
		                    url:'${pageContext.request.contextPath}/employee/departmentDatas',
		                    valueField:'id',
		                    textField:'dname',
		                    panelHeight:'auto'
		            		"></td>
	                </tr>
	                
	                 <tr>
	                    <td>手机:</td>
	                    <td><input class="easyui-validatebox" type="text" name="phoneNo" data-options="required:true"></input></td>
	                </tr>
	                 <tr>
	                    <td>办公电话:</td>
	                    <td><input class="easyui-validatebox" type="text" name="officeTel" data-options="required:true"></input></td>
	                </tr>
	               
	                <tr>
	                    <td>在职状态:</td>
	                    <td>
	                    	<select class="easyui-combobox" name="workStatu" style="width:170px;">
					        	<option value="1" selected="selected">在职</option>
					        	<option value="0">离职</option>
					    	</select>
	                    </td>
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
	                    <td>用户名:</td>
	                    <td><input class="easyui-validatebox" type="text" name="username" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td>真实姓名:</td>
	                    <td><input class="easyui-validatebox" type="text" name="realname" data-options="required:true"></input></td>
	                </tr>
	                 <tr>
	                    <td>部门:</td>
		                <td><input class="easyui-combobox"
				            name="department.id"
				            data-options="
		                    url:'${pageContext.request.contextPath}/employee/departmentDatas',
		                    valueField:'id',
		                    textField:'dname',
		                    panelHeight:'auto'
		            		"></td>
	                </tr>
	            </table>
	            
	         </form>
         </div>
		<div id="search-dlg-buttons">
	        <a id="search-save-button" href="javascript:void(0)" class="easyui-linkbutton">查询</a>
	        <a id="search-close-button" href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#search').dialog('close')">关闭</a>
	    </div>
    </div>
    
    <div id="edit" class="easyui-dialog" title="修改管理员角色" 
    	data-options="modal:true,closed:true,iconCls:'icon-edit',buttons:'#edit-dlg-buttons'" 
		style="width:500px;height:300px;padding:10px;">
		<div style="padding:10px 0 10px 60px">
			<form id="editForm" method="post">
	           <table>
	           		
	                <tr>
	                    <td>编号:</td>
	                    <td><input class="easyui-validatebox" type="text" readonly="readonly" name="id" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td>用户名:</td>
	                    <td><input class="easyui-validatebox" type="text" name="username" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td>密码:</td>
	                    <td><input class="easyui-validatebox" type="text" name="pass" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td>昵称:</td>
	                    <td><input class="easyui-validatebox" type="text" name="nickname" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td>真实姓名:</td>
	                    <td><input class="easyui-validatebox" type="text" name="realname" data-options="required:true"></input></td>
	                </tr>
	                
	                <tr>
	                    <td>职位:</td>
		                <td><input class="easyui-combobox"
				            name="jobInfoid"
				            id="edit_jobInfo"
				            data-options="
		                    url:'${pageContext.request.contextPath}/employee/jobDatas',
		                    valueField:'id',
		                    textField:'job',
		                    panelHeight:'auto'
		            		"></td>
	                </tr>
	                
	                <tr>
	                    <td>部门:</td>
		                <td><input class="easyui-combobox"
				            name="departmentid"
				            id="edit_department"
				            data-options="
		                    url:'${pageContext.request.contextPath}/employee/departmentDatas',
		                    valueField:'id',
		                    textField:'dname',
		                    panelHeight:'auto'
		            		"></td>
	                </tr>
	                
	                 <tr>
	                    <td>手机:</td>
	                    <td><input class="easyui-validatebox" type="text" name="phoneNo" data-options="required:true"></input></td>
	                </tr>
	                 <tr>
	                    <td>办公电话:</td>
	                    <td><input class="easyui-validatebox" type="text" name="officeTel" data-options="required:true"></input></td>
	                </tr>
	               
	                <tr>
	                    <td>在职状态:</td>
	                    <td>
	                    	<select class="easyui-combobox" name="workStatu" style="width:170px;">
					        	<option value="1" selected="selected">在职</option>
					        	<option value="0">离职</option>
					    	</select>
	                    </td>
	                </tr>
	            </table>
	            
	         </form>
         </div>
		<div id="edit-dlg-buttons">
	        <a id="edit-save-button" href="javascript:void(0)" class="easyui-linkbutton">保存</a>
	        <a id="edit-close-button" href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#edit').dialog('close')">关闭</a>
	    </div>
    </div>
    <div id="dlg2" class="easyui-dialog" style="width:400px;height:180px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons2">
        <form id="uploadForm" action="${pageContext.request.contextPath}/excel/inser.do" method="post" enctype="multipart/form-data">
        	<table>
        		<tr>
        			<td>下载模版：</td>
        			<td><a href="javascript:void(0)" class="easyui-linkbutton"  onclick="downloadTemplate()">导入模版</a></td>
        		</tr>
        		<tr>
        			<td>上传文件：</td>
        			<td><input type="file" name="userUploadFile"></td>
        		</tr>
        	</table>
        </form>
	</div>
    
	<div id="dlg-buttons2">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="uploadFile()">上传</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg2').dialog('close')">关闭</a>
	</div>
</body>
</html>
