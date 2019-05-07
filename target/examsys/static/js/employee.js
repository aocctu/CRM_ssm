/**
 * 部门的js代码
 */

//删除按钮的监听方法
function removeConfirm(){
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
    	$.messager.confirm('信息提示', '确认要删除吗?', function(r){
            if (r){
            	$.post(contextPath+"/employee/delete.do",{ids:string},
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

function outfirm(){
	var row = $('#dg').datagrid('getSelected');
    if (row){
    	$.messager.confirm('信息提示', '确认要删除吗?')}
	alert("sssss")
    	$.messager.confirm('信息提示', '确认要导出吗?', function(r){
            if (r){
            	window.open(contextPath+"/excel/out.do");
            	 
            }
        });
        
   
    
}


//修改按钮的监听方法，加载准备要修改的数据
function loadRemote(){
	var row = $('#dg').datagrid('getSelected');
    if (row){
        $('#editForm').form('load', contextPath+'/employee/update.do?id='+row.id);
        
        if(row.jobInfo){
        	$("#edit_jobInfo").combobox('select', row.jobInfo.id);
        }
        if(row.department){
        	$("#edit_department").combobox('select', row.department.id);
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
   	            	$.post(contextPath+"/employee/addSave.do",datas,
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
   	            	$.get(contextPath+"/employee/updateSave.do",datas,
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
			alert(datas);
			var serializeObj={};
            $(datas).each(function(){
                if(serializeObj[this.name]){
                    if($.isArray(serializeObj[this.name])){
                        serializeObj[this.name].push(this.value);
                        alert(serializeObj[this.name].push(this.value) + "111");
                    }else{  
                        serializeObj[this.name]=[serializeObj[this.name],this.value];
                        alert(serializeObj[this.name] + "2222");
                    }
                }else{
                    serializeObj[this.name]=this.value;
                    alert(serializeObj[this.name] + "----33333");
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
    		{field:"ck",checkbox:"true" },
			{field:'id',title:'编号', width:80},
			{field:'username',title:'用户名', width:150},
			{field:'pass',title:'密码', width:160},
			{field:'nickname',title:'昵称', width:100},
			{field:'realname',title:'真实姓名', width:100},
			{field:'jobInfoid',title:'职位', width:100,
    			formatter: function(value,row,index){
    				if (row.jobInfo){
    					return row.jobInfo.job;
    				} else {
    					return value;
    				}
    			}
    		},
			{field:'departmentid',title:'部门', width:80,
    			formatter: function(value,row,index){
    				if (row.department){
    					return row.department.dname;
    				} else {
    					return value;
    				}
    			}
    		},
    		{field:'phoneNo',title:'手机', width:100},
    		{field:'officeTel',title:'办公电话', width:100},
    		{field:'workStatu',title:'在职状态', width:80,
    			formatter: function(value,row,index){
    				if (row.workStatu){
    					if(row.workStatu==1){
    						return "在职";
    					}else{
    						return "离职";
    					}
    					
    				} else {
    					return value;
    				}
    			}
    		},
    	]]
    });
    
    var pager = $('#dg').datagrid('getPager');//获得表格的页对象
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
    }); 
});