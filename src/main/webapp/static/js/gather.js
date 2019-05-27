/**
 * 快递分捡管理模块用到的js代码
 */

//删除按钮的监听方法
function removeConfirm(){
	var row = $('#dg').datagrid('getSelected');
    if (row){
    	$.messager.confirm('信息提示', '确认要删除吗?', function(r){
            if (r){
            	$.get(contextPath+"/expressSort/delete.do?id="+row.id,null,
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
		
        $('#edit').dialog('open');
           
	}else{
		$.messager.alert('信息提示','请选择一条数据进行修改');
	}
}

//来料检验的监听方法，加载准备要修改的数据
function examine(){
	var row = $('#dg').datagrid('getSelected');
	if(row){
		$('#editForm2').form('load',contextPath+'/expressSort/update.do?id='+row.id);
		
        $('#edit2').dialog('open');
           
	}else{
		$.messager.alert('信息提示','请选择一条数据进行来料检验');
	}
}

// 入库
function storage(){
	// 返回被选中的行 然后集成的其实是 对象数组  
    var row = $('#dg').datagrid('getSelections');  
    var i = 0;  
    var string = "";  
    var strpos = "";
    for(i;i<row.length;i++){  
        string += row[i].id;  
        strpos += row[i].position;  
        if(i < row.length-1){  
            string += ',';  
            strpos += ',';
        }else{  
            break;  
        }  
    } 
    if(row.length>0){
    	$.messager.confirm('信息提示', '确认要这些入库吗?', function(r){
            if (r){
            	$.post(contextPath+"/expressSort/updateStorage.do",{ids:string,pos:strpos},
					function(data,state){
						if(data.status==1){
							$.messager.alert('信息提示','入库成功');
							$('#dg').datagrid('reload');//刷新
						}else{
							$.messager.alert('信息提示','入库失败,检查要入库数据是否有已入库或未检验的');
						}
					}
				);
            	 
            }
        });
        
    }else{
    	$.messager.alert('信息提示','请选择一条数据进行入库操作');
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
   	            	alert(datas);
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




//修改对话框中的保存按钮的监听方法     
$(function(){//保存修改
	$("#edit-save-button2").click(
		function(){
			var datas=$("#editForm2").serialize();//表单数据项
			$.messager.confirm('信息提示', '确认要保存吗?', function(r){
 	            if (r){
 	            	$.post(contextPath+"/expressSort/updateExamine.do",datas,
						function(data,state){
							if(data.status==1){
								$.messager.alert('信息提示','保存成功');
								$('#edit2').dialog('close');
								$('#editForm2').form('clear');//清空表单数据
								$('#dg').datagrid('reload');//刷新
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
$(function(){  
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
			{field:'model',title:'机型', width:160},
			{field:'ichiban',title:'良品', width:60},
			{field:'scrap',title:'报废', width:100},
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