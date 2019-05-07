<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    
    <title>系统登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<c:import url="/header.jsp"></c:import>
  </head>
  
  <body>
   <div style="width:320px;height: 600px;margin: 0 auto;margin-top: 200px;text-align: center;">
	<div class="easyui-panel" title="系统登录" style="width:300px;margin-left:auto;margin-right:auto;"> 
        <form id="loginForm" action="${pageContext.request.contextPath}/login" method="post"> 
           <table> 
                <tr> 
                    <td>用户名:</td> 
                    <td><input name="username" type="text" value="${user_name}"  class="easyui-textbox" data-options="required:true"></input></td> 
                </tr> 
                <tr> 
                    <td>密  码:</td> 
                    <td><input id="pass" name="pass" type="password"  value="${user_pass}" class="easyui-textbox" data-options="required:true"></input></td> 
                </tr>
                 <!-- <tr> 
                    <td>用户组:</td> 
                    <td>
                   		<select class="easyui-combobox" name="group" style="width:170px;">
				        	<option value="user" selected="selected">员工</option>
				        	<option value="admin" >管理员</option>
				    	</select>
                    </td> 
                </tr>  -->
                <tr> 
                    <td>验证码：</td> 
                    <td>
                    	<input class="easyui-textbox" name="code" type="text" style="width:100px;" ></input>
                   	 	<img id="code" alt="" src="${pageContext.request.contextPath}/code">
                    </td> 
                </tr> 
                <tr style="height: 60px;"> 
                    <td style="text-align: left;padding-left: 55px;" colspan="2"><input  type="submit" value="登    录"></input> ${msg}</td> 
                </tr> 
            </table> 
        </form> 
    </div> 
    </div>
	<script type="text/javascript">
		$(function(){
			var codeSrc=$("#code").attr("src");
			$("#code").click(function(){
				var t=new Date().getTime();
				this.src=codeSrc+"?t="+t;
			});
		});

	</script>

  </body>
</html>
