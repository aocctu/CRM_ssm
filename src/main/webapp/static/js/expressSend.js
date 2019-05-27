/**
 * 快递寄送管理模块用到的js代码
 */

//删除按钮的监听方法

   	//添加对话框中的保存按钮的监听方法
$(function(){
	$("#add-save-button").click(
			function(){
				var datas = $("#addForm").serialize();//表单数据项
				$.messager.confirm('信息提示','确认要保存吗？',function(r){
					if(r){
						$.post(contextPath+"/expressSend/addSave.do",datas,
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
			{field:'id',title:'编号', width:60},
			{field:'exp_num',title:'快递单号',width:160},
			{field:'model',title:'机型', width:160},
    		{field:'sn',title:'SN', width:123},
    		{field:'exp_status',title:'状态', width:123},
    		{field:'create_name',title:'创建人员', width:80},
    		{field:'create_date',title:'创建时间', width:128},
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