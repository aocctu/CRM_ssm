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
    if (row){
        $('#editForm').form('load', contextPath+'/express/update.do?id='+row.id);
		if(row.expressCompany){
			$("#edit_exp_company_id").combobox('select', row.expressCompany.id);
		}
        $('#edit').dialog('open');
    }else{
    	$.messager.alert('信息提示','请选择一条数据进行修改');
    }
}

   	//添加对话框中的保存按钮的监听方法
$(function(){//保存添加
	$("#add-save-button").click(
		function(){
			var datas=$("#addForm").serialize();//表单数据项
			$.messager.confirm('信息提示', '确认要保存吗?', function(r){
   	            if (r){
   	            	$.post(contextPath+"/express/addSave.do",datas,
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
   	            	$.post(contextPath+"/express/updateSave.do",datas,
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
		}

	)
});

//表格数据翻页栏设置
$(function(){

    $('#dg').datagrid({
    	columns:[[
			{field:'id',title:'编号', width:80},
			{field:'exp_iphone',title:'快递员手机号',width:160},
			{field:'exp_name',title:'快递员名字', width:160},
			{field:'pay_type',title:'付款类型', width:160,
				formatter: function(value,row,index){
    				if (row.pay_type){
    					if(row.pay_type==1){
    						return "寄付";
    					}else{
    						return "到付";
    					}
    					
    				} else {
    					return value;
    				}
    			}
			},
			{field:'exp_cost',title:'费用', width:160},
			{field:'exp_company_id',title:'快递公司', width:160,
				formatter: function(value,row,index){
					if (row.expressCompany){
						return row.expressCompany.exp_company;
					} else {
						return value;
					}
				}
			},
			{field:'exp_num',title:'快递单号', width:160},
			{field:'exp_status',title:'快递状态', width:160,
				formatter: function(value,row,index){
					if (row.exp_status){
						if(row.exp_status==1){
							return "已录入";
						}else{
							return "已接收";
						}
						
					} else {
						return value;
					}
				}
			},
			{field:'create_name',title:'录入人员', width:160},
    		{field:'create_date',title:'创建时间', width:100},
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