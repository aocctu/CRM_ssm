/**
 * 系统功能管理模块用到的js代码
 */

//删除按钮的监听方法
function removeConfirm(){
	var row = $('#dg').datagrid('getSelected');
    if (row){
    	$.messager.confirm('信息提示', '确认要删除吗?', function(r){
            if (r){
            	$.get(contextPath+"/jobRight/delete.do?id="+row.id,null,
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
	//$("#edit_tree").tree('reload');
	var nodes=$('#edit_tree').tree('getChecked', 'checked');  // 拿到选中的项
	for(var i=0;i<nodes.length;i++){     // 遍历选中项 
		$("#edit_tree").tree('uncheck',nodes[i].target);  // 让选中的项不选
	}
	var row = $('#dg').datagrid('getSelected');
    if (row){
        $('#editForm').form('load', contextPath+'/jobRight/update.do?id='+row.id);
		var ids=row.rightid.split(",");
		
		
		for(var i=0;i<ids.length;i++){//给之前拥有的功能项,在树中显示为选中状态
			var n=$("#edit_tree").tree('find',ids[i]);//通过编号去查找树节点
			if(n){//如果找到，则设置为勾选
				$("#edit_tree").tree('check',n.target);
			}
		}
        
        if(row.jobInfo){
			$("#edit_jobInfo").combobox('select', row.jobInfo.id);
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
			var nodes = $('#add_tree').tree('getChecked');//获取树中选中的项
            var s = '';
            for(var i=0; i<nodes.length; i++){
                if (s != '') s += ',';
                s += nodes[i].id;
            }
            datas+="&rightid="+s;//拼接数据
			$.messager.confirm('信息提示', '确认要保存吗?', function(r){
   	            if (r){
   	            	$.post(contextPath+"/jobRight/addSave.do",datas,
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
			var nodes = $('#edit_tree').tree('getChecked');//获取树中选中的项
            var s = '';
            for(var i=0; i<nodes.length; i++){
                if (s != '') s += ',';
                s += nodes[i].id;
            }
            datas+="&rightid="+s;//拼接数据
			$.messager.confirm('信息提示', '确认要保存吗?', function(r){
   	            if (r){
   	            	$.get(contextPath+"/jobRight/updateSave.do",datas,
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
    		{field:'jobInfoid',title:'职位', width:80,
    			formatter: function(value,row,index){
    				if (row.jobInfo){
    					return row.jobInfo.job;
    				} else {
    					return value;
    				}
    			}
    		},
    		{field:'rightid',title:'权限', width:180},
    		
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