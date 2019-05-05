<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>权限管理系统</title>
	<c:import url="/header.jsp"></c:import>
</head>
<body class="easyui-layout layout panel-noscroll">
	<div class="easyui-layout" style="width:100%;height:768px;">
		
		<div data-options="region:'north'" style="background-color: rgb(224, 236, 255);height:100px">
			<div style="text-align: center;"><h1>欢迎使用本系统</h1></div>
			<div style="text-align: right;display: block;"><span id=localtime> </span>  
			<%=request.getSession().getAttribute("username") %>
			<a href="${pageContext.request.contextPath}/logout/out.do" style="padding: 20px">登出系统</a></div>
			
		</div>
		
		
		<div data-options="region:'south',split:true" style="height:50px;">
			
		</div>
		<!-- <div data-options="region:'east',split:true" title="East" style="width:100px;"></div> -->
		
		
		<div data-options="region:'west',split:true" title="功能导航" style="width:200px;">
			<div id="aa" class="easyui-accordion" style="width:193px;border:false">  
			    <div title="功能菜单" >  
			       <ul id="tree" class="easyui-tree" data-options="url:'ajaxTree.do',method:'get',animate:true,border:false"></ul> 
			    </div>  
			</div> 
		</div>
		
		
		<div data-options="region:'center'">
			<div id="tabs" class="easyui-tabs" style="width:100%;height:100%">
				
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$(function(){

		//实例化树菜单
		$("#tree").tree({
			lines:true,
			onClick:function(node){
				if(node.attributes){
					openTab(node.text,node.attributes.url);
				}
			}
		});
		
		// 新增Tab
		function openTab(text,url){
			if($("#tabs").tabs('exists',text)){
				$("#tabs").tabs('select',text);
			}else{
				var content="<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%' src="+url+"></iframe>";
				$("#tabs").tabs('add',{
					title:text,
					closable:true,
					content:content
				});
			}
		}

		/* openTab("系统功能管理","adminRolesSettings/select");
		$("#cl-dashboard").html(''); */
	});
	function showLocale(objD)
	{
		var str,colorhead,colorfoot;
		var yy = objD.getYear();
		if(yy<1900) yy = yy+1900;
		var MM = objD.getMonth()+1;
		if(MM<10) MM = '0' + MM;
		var dd = objD.getDate();
		if(dd<10) dd = '0' + dd;
		var hh = objD.getHours();
		if(hh<10) hh = '0' + hh;
		var mm = objD.getMinutes();
		if(mm<10) mm = '0' + mm;
		var ss = objD.getSeconds();
		if(ss<10) ss = '0' + ss;
		var ww = objD.getDay();
		if  ( ww==0 )  colorhead="<font color=\"#FF0000\">";
		if  ( ww > 0 && ww < 6 )  colorhead="<font color=\"#373737\">";
		if  ( ww==6 )  colorhead="<font color=\"#008000\">";
		if  (ww==0)  ww="星期日";
		if  (ww==1)  ww="星期一";
		if  (ww==2)  ww="星期二";
		if  (ww==3)  ww="星期三";
		if  (ww==4)  ww="星期四";
		if  (ww==5)  ww="星期五";
		if  (ww==6)  ww="星期六";
		colorfoot="</font>"
		str = colorhead + yy + "-" + MM + "-" + dd + " " + hh + ":" + mm + ":" + ss + "  " + ww + colorfoot;
		return(str);
	}
	function tick()
	{
		var today;
		today = new Date();
		document.getElementById("localtime").innerHTML = showLocale(today);
		window.setTimeout("tick()", 1000);
	}
	tick();

	</script>
</body>
</html>
