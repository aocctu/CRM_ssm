/**
 * 申请领料模块用到的js代码
 */



//修改按钮的监听方法，加载准备要修改的数据
function loadRemote(){
	var row = $('#dg').datagrid('getSelected');
    if (row){
        $('#editForm').form('load', contextPath+'/apply/update.do?id='+row.id);
		
        $('#edit').dialog('open');
    }else{
    	$.messager.alert('信息提示','请选择一条数据申请领料');
    }
}



 
	//添加对话框中的保存按钮的监听方法
$(function(){//保存添加
	$("#edit-save-button").click(
		function(){
			var datas=$("#editForm").serialize();//表单数据项
			
			$.messager.confirm('信息提示', '确认要保存吗?', function(r){
   	            if (r){
   	            	$.post(contextPath+"/apply/updateSave.do",datas,
						function(data,state){
							if(data.status==1){
								$.messager.alert('信息提示','领料已在申请中 ...');
								$('#editForm').form('clear');//清空表单数据
								$('#dg').datagrid('reload');//刷新
								$('#edit').dialog('close');
							}else{
								$.messager.alert('信息提示','领料申请失败');
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



//表格数据翻页栏设置
$(function(){

    $('#dg').datagrid({
    	columns:[[
			{field:'id',title:'编号', width:80},
			{field:'material_code',title:'物料编码',width:160},
			{field:'model',title:'机型', width:160},
			{field:'description',title:'描述', width:160},
			{field:'ichiban',title:'良品数量', width:160},
			{field:'receive_num',title:'领取数量', width:160},
			{field:'materials_status',title:'状态', width:160},
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