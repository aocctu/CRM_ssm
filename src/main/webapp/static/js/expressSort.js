/**
 * 快递录入管理模块用到的js代码
 */

//删除按钮的监听方法
function removeConfirm(){
	var row = $('#dg').datagrid('getSelected');
    if (row){
    	$.messager.confirm('信息提示', '确认要删除吗?', function(r){
            if (r){
            	$.get(contextPath+"/express/delete.do?id="+row.id,null,
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
	if(row){
		$('#editForm').form('load',contextPath+'/expressSort/update.do?id='+row.id);
		alert(row.material_type + "===---")
		if (row.material_type == 1 || row.material_type == 2) {
			alert(123145);
			
            	$("#quantity").textbox('disable').textbox('setValue',''); //禁用并清空值 
            	$("#sn").textbox('enable');
            	$('#edit').dialog('open');
            }
            else {
            	alert(45);
            	$("#sn").textbox('disable').textbox('setValue',''); //禁用并清空值 
            	$("#quantity").textbox('enable'); //启用
            	$('#edit').dialog('open');
            	alert(666);
            	
            }
	}else{
		$.messager.alert('信息提示','请选择一条数据进行修改');
	}
}


   	//添加对话框中的保存按钮的监听方法
$(function(){
	$("#add-save-button").click(
			function(){
				var datas = $("#addForm").serialize();//表单数据项
				$.messager.confirm('信息提示','确认要保存吗？',function(r){
					if(r){
						$.post(contextPath+"/expressSort/addSave.do",datas,
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

//修改对话框中的保存按钮的监听方法     
$(function(){//保存修改
	$("#edit-save-button").click(
		function(){
			var datas=$("#editForm").serialize();//表单数据项
			$.messager.confirm('信息提示', '确认要保存吗?', function(r){
   	            if (r){
   	            	$.post(contextPath+"/expressSort/updateSave.do",datas,
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


//根据下拉框的值 禁用或启用控件
$(document).ready(function(){  
    $("#material_type").combobox({  
        onChange:function(newValue,oldValue){ 
        	if (newValue == 1 || newValue == 2) {
            	$("#quantity").textbox('disable').textbox('setValue',''); //禁用并清空值 
            	$("#sn").textbox('enable');
            }
            else {
            	$("#sn").textbox('disable').textbox('setValue',''); //禁用并清空值 
            	$("#quantity").textbox('enable'); //启用
            	
            }
        }  
    })  
})



//表格数据翻页栏设置
$(function(){

    $('#dg').datagrid({
    	columns:[[
			{field:'id',title:'编号', width:80},
			{field:'exp_num',title:'快递单号',width:160},
			{field:'model',title:'机型', width:160},
			{field:'material_type',title:'物料类型', width:100,
				formatter: function(value,row,index){
    				if (row.material_type){
    					if(row.material_type==1){
    						return "整机";
    					}else if(row.material_type==2){
    						return "主机";
    					}else{
    						return "配件";
    					}
    					
    				} else {
    					return value;
    				}
    			}
			},
			{field:'quantity',title:'数量', width:100},
			{field:'fault',title:'来料故障', width:160},
			{field:'type',title:'类型', width:100,
				formatter: function(value,row,index){
					if (row.type){
						if(row.type==1){
							return "保内";
						}else{
							return "保外";
						}
						
					} else {
						return value;
					}
				}
			},
			{field:'remark',title:'备注', width:100},
			{field:'color',title:'颜色', width:100},
    		{field:'sn',title:'SN', width:123},
    		{field:'create_name',title:'分捡人员', width:123},
    		{field:'create_date',title:'分捡时间', width:123},
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