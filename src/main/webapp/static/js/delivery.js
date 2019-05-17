/**
 * 配件发料管理模块用到的js代码
 */



// 配件发料
function loadRemote(){
	// 返回被选中的行 然后集成的其实是 对象数组  
    var row = $('#dg').datagrid('getSelections');  
    var i = 0;  
    var string = "";  
    var status = "";
    for(i;i<row.length;i++){  
        string += row[i].id;  
        status += row[i].materials_status;  
        if(i < row.length-1){  
            string += ',';  
            status += ',';
        }else{  
            break;  
        }  
    } 
    if(row.length>0){
    	$.messager.confirm('信息提示', '确认将这些单时行发料吗?', function(r){
            if (r){
            	$.post(contextPath+"/delivery/updateMaterials.do",{ids:string,status:status},
					function(data,state){
						if(data.status==1){
							$.messager.alert('信息提示','发料成功');
							$('#dg').datagrid('reload');//刷新
						}else{
							$.messager.alert('信息提示','发料失败,检查要发料单中是否存在已发料');
						}
					}
				);
            	 
            }
        });
        
    }else{
    	$.messager.alert('信息提示','请选择一条数据进行发料操作');
    }
}



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
    		{field:'ck',checkbox:'true' },
			{field:'id',title:'编号', width:80},
			{field:'batch_num',title:'批次号',width:160},
			{field:'material_code',title:'物料编码',width:100},
			{field:'model',title:'机型', width:100},
			{field:'description',title:'描述', width:100},
			{field:'ichiban',title:'库存良品', width:100},
			{field:'receive_num',title:'领料数量', width:100},
			{field:'materials_status',title:'物料状态', width:100},
			{field:'apply_name',title:'需领料人', width:100},
    		{field:'apply_date',title:'领料单创建时间', width:128},
    		{field:'delivery_name',title:'发料人', width:100},
    		{field:'delivery_date',title:'发料时间', width:128},
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