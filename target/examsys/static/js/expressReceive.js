/**
 * 快递接收管理模块用到的js代码
 */

//删除按钮的监听方法
function removeConfirm(){
	var row = $('#dg').datagrid('getSelected');
    if (row){
    	$.messager.confirm('信息提示', '确认要删除吗?', function(r){
            if (r){
            	$.get(contextPath+"/expressReceive/delete.do?id="+row.id,null,
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
	// 返回被选中的行 然后集成的其实是 对象数组  
    var row = $('#dg').datagrid('getSelections');  
    var i = 0;  
    var string = "";  
    var status = "";
    for(i;i<row.length;i++){  
        string += row[i].id;  
        status += row[i].exp_status;  
        if(i < row.length-1){  
            string += ',';  
            status += ',';
        }else{  
            break;  
        }  
    }
    if(row.length>0){
    	$.messager.confirm('信息提示', '确认要这些入库吗?', function(r){
            if (r){
            	$.post(contextPath+"/expressReceive/updateStatus.do",{ids:string,exp_status:status},
					function(data,state){
						if(data.status==1){
							$.messager.alert('信息提示','接收成功');
							$('#dg').datagrid('reload');//刷新
						}else{
							$.messager.alert('信息提示','接收失败,检查快递状态是否已接收');
						}
					}
				);
            	 
            }
        });
        
    }else{
    	$.messager.alert('信息提示','请选择未录入的快递数据进行接收操作');
    }
}

   	//添加对话框中的保存按钮的监听方法
$(function(){//保存添加
	$("#add-save-button").click(
		function(){
			var datas=$("#addForm").serialize();//表单数据项
			$.messager.confirm('信息提示', '确认要保存吗?', function(r){
   	            if (r){
   	            	$.post(contextPath+"/expressReceive/addSave.do",datas,
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
   	            	$.post(contextPath+"/expressReceive/updateSave.do",datas,
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

function ann(){
	var row = $('#dg').datagrid('getSelected');
    if (row){
    	$.messager.confirm('信息提示', '确认要接收此快递吗?', function(r){
            if (r){
            	$.get(contextPath+"/expressReceive/updateStatus.do?id="+row.id,null,
					function(data,state){
						if(data.status==1){
							$.messager.alert('信息提示','接收成功');
							$('#dg').datagrid('reload');//刷新
						}else{
							$.messager.alert('信息提示','接收失败');
						}
					}
				);
            	 
            }
        });
        
    }else{
    	$.messager.alert('信息提示','请选择一条快递数据进行接收');
    }
    
}

//表格数据翻页栏设置
$(function(){

    $('#dg').datagrid({
    	columns:[[
    		{field:'ck',checkbox:'true' },
    		/*{title: "快递接收",field: 'file', width:80,
			    formatter:function(value,row,index){
			        return "<a href='javascript:void(0);' class='easyui-linkbutton'  onclick='ann();'>快递接收</a>";
				}
    		},*/
			{field:'id',title:'编号', width:80},
			{field:'exp_num',title:'快递单号', width:140},
			{field:'exp_iphone',title:'快递员手机号',width:140},
			{field:'exp_name',title:'快递员名字', width:140},
			{field:'pay_type',title:'付款类型', width:140},
			{field:'exp_cost',title:'费用', width:140},
			{field:'exp_company_id',title:'快递公司', width:140,
				formatter: function(value,row,index){
					if (row.expressCompany){
						return row.expressCompany.exp_company;
					} else {
						return value;
					}
				}
			},
			{field:'exp_status',title:'快递状态', width:140},
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