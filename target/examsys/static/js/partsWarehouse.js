/**
 * 快递录入管理模块用到的js代码
 */

//删除按钮的监听方法
function removeConfirm(){
	var row = $('#dg').datagrid('getSelected');
    if (row){
    	$.messager.confirm('信息提示', '确认要删除吗?', function(r){
            if (r){
            	$.get(contextPath+"/partsWarehouse/delete.do?id="+row.id,null,
					function(data,state){
						if(data.status==1){
							$.messager.alert('信息提示','删除成功');
							$('#dg').datagrid('reload');//刷新
						}else{
							$.messager.alert('信息提示','删除失败');
						}
					}
				);
            	 
            }
        });
        
    }else{
    	$.messager.alert('信息提示','请选择一条数据进行删除');
    }
    
}

//修改按钮的监听方法，加载准备要修改的数据
function loadRemote(){
	var row = $('#dg').datagrid('getSelected');
    if (row){
        $('#editForm').form('load', contextPath+'/partsWarehouse/update.do?id='+row.id);
		
        $('#edit').dialog('open');
    }else{
    	$.messager.alert('信息提示','请选择一条数据进行修改');
    }
}


function inventory(){
	alert("此功能暂不开放！！");
}


   	//添加对话框中的保存按钮的监听方法
$(function(){//保存添加
	$("#add-save-button").click(
		function(){
			var datas=$("#addForm").serialize();//表单数据项
			$.messager.confirm('信息提示', '确认要保存吗?', function(r){
   	            if (r){
   	            	$.post(contextPath+"/partsWarehouse/addSave.do",datas,
						function(data,state){
							if(data.status==1){
								$.messager.alert('信息提示','保存成功');
								$('#addForm').form('clear');//清空表单数据
								$('#dg').datagrid('reload');//刷新
								$('#add').dialog('close');
							}else{
								$.messager.alert('信息提示','保存失败');
							}
						}
					);
   	            }
   	        });
		}	

	)
}); 	

	//添加对话框中的保存按钮的监听方法
$(function(){//保存添加
	$("#edit-save-button").click(
		function(){
			var datas=$("#editForm").serialize();//表单数据项
			alert(datas);
			$.messager.confirm('信息提示', '确认要保存吗?', function(r){
   	            if (r){
   	            	$.post(contextPath+"/partsWarehouse/updateSave.do",datas,
						function(data,state){
							if(data.status==1){
								$.messager.alert('信息提示','保存成功');
								$('#editForm').form('clear');//清空表单数据
								$('#dg').datagrid('reload');//刷新
								$('#edit').dialog('close');
							}else{
								$.messager.alert('信息提示','保存失败');
							}
						}
					);
   	            }
   	        });
		}	

	)
}); 	



//查询对话框中查询按钮监听方法
$(function(){//查询
	$("#search-save-button").click(
			
		function(){
			var datas=$("#searchForm").serializeArray();//表单数据项
			var serializeObj={};
            $(datas).each(function(){
                if(serializeObj[this.name]){
                    if($.isArray(serializeObj[this.name])){
                        serializeObj[this.name].push(this.value);
                    }else{  
                        serializeObj[this.name]=[serializeObj[this.name],this.value];
                    }
                }else{
                    serializeObj[this.name]=this.value;
                }
            });
			$('#dg').datagrid('reload',serializeObj);//查询刷新
			$('#search').dialog('close');
			$('#search').form('clear');//清空表单数据
		}

	)
});


// 根据下拉框的值 禁用或启用控件
$(document).ready(function(){  
    $("#pay_type").combobox({  
        onChange:function(newValue,oldValue){ 
        	if (newValue == 1) {
            	$("#exp_cost").textbox('disable').textbox('setValue',''); //禁用并清空值 
            }
            else {
            	$("#exp_cost").textbox('enable'); //启用
            }
        }  
    })  
})


//表格数据翻页栏设置
$(function(){

    $('#dg').datagrid({
    	columns:[[
			{field:'id',title:'编号', width:80},
			{field:'material_code',title:'物料编码',width:160},
			{field:'model',title:'机型', width:160},
			{field:'description',title:'描述', width:160},
			{field:'quantity',title:'数量', width:160},
			{field:'ichiban',title:'良品', width:160},
			{field:'scrap',title:'报废', width:160},
			{field:'status',title:'状态', width:160},
			{field:'create_name',title:'录入人员', width:100},
    		{field:'create_date',title:'创建时间', width:128},
    		{field:'remark',title:'备注', width:100},
    	]]
    });
    
/*  //分页处的增删改查
 *   var pager = $('#dg').datagrid('getPager');//获得表格的页对象
    pager.pagination({//给页对象设置按钮组
        buttons:[{
            iconCls:'icon-add',
            handler:function(){
            	$('#add').dialog('open');//打开添加对话框
            }
        },{
            iconCls:'icon-search',
            handler:function(){
            	$('#search').dialog('open');//打开查询对话框
            }
        },{
            iconCls:'icon-edit',
            handler:function(){
            	loadRemote();//打开修改对话框
            }
        },{
            iconCls:'icon-remove',
            handler:function(){
            	removeConfirm();//打开删除对话框
            }
        }]
    }); */
});