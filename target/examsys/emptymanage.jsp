<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">

	var url;
	/* 
	$(function(){
		$("#bName").combobox({
			onSelect:function(record){
				$("#sName").combobox("reload","productSmallType_comboList.action?s_productSmallType.bigType.id="+record.id);
				$("#sName").combobox("setValue","");
			}
		});
	}); */
	/* 表格导入 */
	function openUploadFileDialog(){
		$("#dlg2").dialog('open').dialog('setTitle','使用表格批量导入数据');
	}
	function downloadTemplate(){
		window.open('${pageContext.request.contextPath}/images/SptypeExporTemplate.xls');
	}
	function uploadFile(){
		$("#uploadForm").form("submit",{
			success:function(result){
				var result=eval('('+result+')');
				if(result.errorMsg){
					$.messager.alert("系统提示",result.errorMsg);
				}else{
					$.messager.alert("系统提示","上传成功");
					$("#dlg2").dialog("close");
					$("#dg").datagrid("reload");
				}
			}
		});
	}
	/* 表格导出 */
	/* function opendateout(){
		var r=window.confirm("您将把此表格数据导出到指定硬盘,确定导出？")
		if(r){
		window.open('excel/out');
	}} */
	
	function opendateout(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要导出的数据！");
			return;
		}
	 var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].empId);
		} 
		var ids=strIds.join(","); 
		var r=window.confirm("您将把<font color=red>"+selectedRows.length+"</font>条数据导出到指定硬盘,确定导出？")
		if(r){
			window.open("${pageContext.request.contextPath}/excel/out.do?ids="+ids);
		}
		/* $.messager.confirm("系统提示","您确认导出这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
			$.post("${pageContext.request.contextPath}/excel/out.do",{ids:ids});, function(result){
					if(result.success){
						$.messager.alert("系统提示","操作已完成！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert("系统提示","导出失败！");
					}
				},"json"); 
				var ul="excel/out.do?ids="ids;
				window.open(ul);
			} 
			
		}); */
	}
	
	
	function searchProduct(){
		$("#dg").datagrid('load',{
			"spnameselectsp":$("#s_productName").val()
		});
	}
	
	function formatProPic(val,row){
		return "<img width=60 height=60 src='${pageContext.request.contextPath}/"+val+"'>";
	}

	function formatSmallTypeId(val,row){
		return row.department.departmentName;
	}
	
	function formatSmallTypeName(val,row){
		return row.sptype.typename;
	}
	function formatstatus(val,row){
		if(val==0){
			return "离职";
		}else if(val==1){
			return "在职";
		}
		return "";
	}
	
	function formatBigTypeId(val,row){
		return row.bigType.id;
	}
	
	function formatdate(val,row){  

       var unixTimestamp = new Date(row.joinDate);  

       /*  return unixTimestamp.toLocaleString();  */
       return unixTimestamp.toLocaleDateString();
	

        } 
	
	function formatBigTypeName(val,row){
		return row.bigType.name;
	}
	
	
	function openProductAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加员工信息");
		url="${pageContext.request.contextPath}/employee/add.do";
	}
	
	function openProductModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑商品信息");
		$("#empId").val(row.empId);
		$("#empName").val(row.empName);
		$("#sex").val(row.sex);
		$("#education").val(row.education);
		$("#departmentId").combobox("setValue",row.departmentId);
		$("#idCode").val(row.idCode);
		$("#joinDate").val(row.joinDate);
		$("#status").val(row.status);
		url="${pageContext.request.contextPath}/employee/update.do?empId="+row.empId;
	}
	
	//添加提交表单
	/* function saveEmployee(){
		//有效性验证
		if(!$('#fm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		
		//ajax的post方式提交表单
		//$("#employeeAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("${pageContext.request.contextPath}/employee/add.do",$("#fm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','新增员工成功!');
				clearEmployeeAddForm();
				$("#dlg").dialog("close");
				$("#dg").datagrid("reload");
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
	} */
	function saveEmployee(){
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
				return $(this).form("validate");
			},
			success:function(result){
				var result=eval('('+result+')');
				if(result.success){
					$.messager.alert("系统提示","保存成功");
					resetValue();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
				}else{
					$.messager.alert("系统提示","已执行操作");
					/* 暂时不理想，有时间再改  */
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
				}
			}
		});
	}
	
	function resetValue(){
		$("#name").val("");
		$("#price").val("");
		$("#stock").val("");
		$("#pP").val("");
		$("#bName").combobox("setValue","");
		$("#sName").combobox("setValue","");
		$("#description").val("");
		
		$("#id").val("");
		$("#proPic").val("");
		$("#hot").val("");
		$("#hotTime").val("");
		$("#specialPrice").val("");
		$("#specialPriceTime").val("");
	}
	
	function closeProductDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
	
	function deleteProduct(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
	 var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].empId);
		} 
		var ids=strIds.join(","); 
		$.messager.confirm("系统提示","您确认要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("${pageContext.request.contextPath}/employee/delete.do",{ids:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","数据已成功删除！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert("系统提示","数据删除失败！");
					}
				},"json");
			}
		}); 
	}
	
	function setProductHot(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要设置热卖的商品！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].spid);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要设置这<font color=red>"+selectedRows.length+"</font>个商品为热卖吗？",function(r){
			if(r){
				$.post("product_sethot",{ids:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","已设置成功！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert("系统提示","设置失败！");
					}
				},"json");
			}
		});
	}
	function setProductputong(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要设置为普通的商品！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].empId);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要设置这<font color=red>"+selectedRows.length+"</font>个商品为热卖吗？",function(r){
			if(r){
				$.post("product_setputong",{ids:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","已设置成功！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert("系统提示","设置失败！");
					}
				},"json");
			}
		});
	}
	
	function setProductSpecialPrice(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要设置最新产品的商品！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].spid);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要设置这<font color=red>"+selectedRows.length+"</font>个商品为最新产品吗？",function(r){
			if(r){
				$.post("product_setnews",{ids:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","已设置成功！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert("系统提示","设置失败！");
					}
				},"json");
			}
		});
	}
</script>
</head>
<body style="margin:1px;">
	<table id="dg" title="员工管理" class="easyui-datagrid"
	 fitColumns="true" pagination="true" rownumbers="true"
	 url="${pageContext.request.contextPath}/employee/employeelist.do" fit="true" toolbar="#tb">
	 <thead>
	 	<tr>
	 		<th field="cb" checkbox="true" align="center"></th>
	 		<th field="empId" width="50" align="center">员工编号</th>
	 		<!-- <th field="path" width="100" align="center" formatter="formatProPic">商品图片</th> -->
	 		<th field="empName" width="100" align="center">员工姓名</th> 
	 		<th field="sex" width="50" align="center">性别</th>
	 		<th field="education" width="50" align="center">学历</th>
	 		<th field="department" width="100" align="center" formatter="formatSmallTypeId">所属部门</th> 
	 		<th field="idCode" width="50" align="center">身份证号</th>
	 		<!-- <th field="birthday" width="50" align="center" formatter="formatdate">生日</th> -->
	 		<th field="joinDate" width="50" align="center" formatter="formatdate">入职时间</th>
	 		<th field="status" width="50" align="center" formatter="formatstatus">状态</th>
	 		<!-- <th field="sptype.typeid" width="100" align="center" formatter="formatSmallTypeId" hidden="true">所属商品类id</th>
	 		<th field="sptype.typename" width="100" align="center" formatter="formatSmallTypeName">所属商品分类</th> 
	 		<th field="spdesc" width="100" align="center">状态</th> -->
	 	</tr>
	 </thead>
	</table>
	
	<div id="tb">
		<div>
			<a href="javascript:openProductAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:openProductModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<shiro:hasPermission name="order:dele">
			<a href="javascript:deleteProduct()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
			</shiro:hasPermission>
			<a href="javascript:opendateout()" class="easyui-linkbutton" iconCls="" plain="true">导出数据</a>
			<a href="javascript:openUploadFileDialog()" class="easyui-linkbutton" iconCls="" plain="true">使用表格批量导入数据</a>
			<!-- <a href="javascript:setProductHot()" class="easyui-linkbutton" plain="true">设置为热卖</a>
			<a href="javascript:setProductSpecialPrice()" class="easyui-linkbutton"  plain="true">设置为最新产品</a>
			<a href="javascript:setProductputong()" class="easyui-linkbutton" plain="true">设置为普通商品</a> -->
		</div>
		<div>
			&nbsp;员工编号：&nbsp;<input type="text" id="s_productName" size="20" onkeydown="if(event.keyCode==13) searchProduct()"/>
			<a href="javascript:searchProduct()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width: 600px;height:450px;padding: 10px 20px"
	  closed="true" buttons="#dlg-buttons">
	 	<form id="fm" method="post" enctype="multipart/form-data">
	 		<table cellspacing="8px">
	 			<tr>
	 				<td>员工编号：</td>
	 				<td colspan="4"><input type="text" id="empId" name="empId" class="easyui-validatebox" required="true"/></td>
	 			</tr>
	 			<tr>
	 				<td>姓名：</td>
	 				<td colspan="4"><input type="text" id="empName" name="empName" class="easyui-validatebox" required="true"/></td>
	 			</tr>
	 			<tr>
	 				<td>性别：</td>
	 				<td colspan="4"><input type="text" id="sex" name="sex" class="easyui-validatebox" required="true"/></td>
	 			</tr>
	 			<tr>
	 				<td>学历：</td>
	 				<td colspan="4"><input type="text" id="education" name="education" class="easyui-validatebox" required="true"/></td>
	 			</tr>
	 			<tr>
	 				<td>所属部门：</td>
	 				<td colspan="4">
	 					<input class="easyui-combobox" id="sName" name="departmentId" data-options="panelHeight:'auto',editable:false,valueField:'departmentId',textField:'departmentName',url:'${pageContext.request.contextPath}/departments/listforemp.do'"/>
	 				</td>
	 			</tr> 
	 			<tr>
	 				<td>身份证号：</td>
	 				<td colspan="4"><input type="text" id="idCode" name="idCode" class="easyui-validatebox" required="true"/></td>
	 			</tr>
	 			<tr>
	 				<td>入职时间：</td>
	 				<td colspan="4"><input type="text" id="joinDate" name="joinDate" class="easyui-datebox" value="12/5/2017" required="true"/></td>
	 			</tr>
	 			<!-- <tr>
	            <td>入职日期:</td>
	            <td><input class="easyui-datebox" name="joinDate"     
        			value="5/5/2016" style="width:150px"> </td>
	      		  </tr> -->
	 			
	 			<tr>
	 				<td>状态(1在职0离职)：</td>
	 				<td colspan="4"><input type="text" id="status" name="status" class="easyui-validatebox" required="true"/></td>
	 			</tr>
	 		</table>
	 	</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:saveEmployee()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeProductDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
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