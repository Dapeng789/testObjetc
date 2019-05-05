<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>供应链管理一体化系统</title>
	<jsp:include page="../inc/injs.jsp"/>
	<!-- panel收起后显示标题 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/myself.js"></script> 
	<!-- 密码 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/home/home.password.js"></script> 
	<!-- 双击tab关闭 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/home/home.doubleClick.js"></script> 
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/home/home.css">
	
	<script type="text/javascript">
		$(function(){
			/* 总体布局 */
			$('body').layout({
				fit : true
			});
			/* 自定义滚动条样式 */
		   /*  $('#west').mCustomScrollbar({
			  theme:"inset-3"
			});    */
			
			/* 右上角菜单及其事件 */
			/*1*/
		    var ddlMenu1=$('#mb1').menubutton({    
			    iconCls: 'icon-help',    
			    menu: '#mm1'  
			});
			$(ddlMenu1.menubutton('options').menu).menu({
	            onClick: function (item) {  menuHandler(item);  }
			})
			/*2*/
			var ddlMenu2=$('#mb2').menubutton({    
			    iconCls: 'icon-tip',    
			    menu: '#mm2'  
			});    
			$(ddlMenu2.menubutton('options').menu).menu({
	            onClick: function (item) { menuHandler(item); }
			})
			
			/*修改密码框*/
			$("#changePsw").dialog({
				title: '修改密码',    
			    width: 300,    
			    height: 250,    
			    closed: true,    
			    cache: false,     
			    modal: true   ,
				buttons:[{
					text:'保存',
					handler:function(){
						$('#myform').form("submit",{
							url: "${pageContext.request.contextPath}/cms/home/modifypassword",
							onSubmit: function(){
								if(!checkPsw()){
									$("#info").html("建议密码采用子母和数字组合，并且长度大于6！");
									return false;
								}
								var isValid = $(this).form('validate');
								if (!isValid){
									$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
								}
								return isValid;	// 返回false终止表单提交
							},
							success: function(result){
								var obj=eval('(' + result + ')')
								if (!obj.success) {//失败
									$("#info").html(obj.msg);
									//$.messager.show({title:"提示", msg : obj.msg });
								} else {//成功
									$.messager.show({title:"提示", msg : obj.msg });
									$("#changePsw").dialog("close");//关闭dialog
								}
							}
						});
						return false; //阻止form的默认提交动作
					}
				},{
					text:'关闭',
					handler:function(){
						$("#changePsw").dialog("close");//关闭dialog
					}
				}]
			});
			/* 登录人密码 如果是123初始密码,提示需要改密码 */
			$.post('${pageContext.request.contextPath}/cms/home/validpassword', 
					' ', 
					function(result){
						if(result.success){
							$("#info").html("请修改初始密码!");
							//弹出修改密码框
							$("#changePsw").dialog("open");
						}
					}, 'json');
			/* 给tab绑定双击关闭事件*/
			$('#tt').tabs('bindDblclick', function(index, title){
				if(index>0){$('#tt').tabs('close',index);}
		    });
			
			/* 左边默认刷新 */
			refreshLeftTree("${m1[0].id }","${m1[0].text }",0,"${m1[0].iconCls }");
		});
		
		/* 创建Tab */
		function addTab(text,url ,iconCls){    
		    if ($('#tt').tabs('exists', text)){    
		        $('#tt').tabs('select', text);    
		    } else {    
		        var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';    
		        $('#tt').tabs('add',{    
		            title:text,    
		            iconCls : iconCls,
		            content:content,    
		            closable:true 
		        });    
		    }    
		}   
		
		/* 处理右上角各个菜单事件 */
		function menuHandler(item){
			if (item.text=='退出系统'){
				
			}else if(item.text=='修改密码'){
				//初始化表单
				$("#myform").form("reset");
				$('#weak').css({backgroundColor:''});
		        $('#middle').css({backgroundColor:''});
		        $('#strength').css({backgroundColor:''});
		        $('#weak').html('');
		        $('#middle').html('');
		        $('#strength').html('');
		      	
				$("#changePsw").dialog("open");
			}
		}
		
		/* 刷新左边导航 */
		function refreshLeftTree(id,title,index,iconCls){
	        $('#west_accordion').accordion();
	        var selectPanel=$("#west_accordion").accordion('getPanel',2);
	        if(selectPanel != null ){
	        	$("#west_accordion").accordion('remove',2);
	        }
	        $('#west_accordion').accordion('add', {  
                title : title,  
                iconCls : iconCls,  
                selected : true, 
                border : false,
                fit : true,
                collapsible : false,
                content : '<div style="padding:0px"><ul name="'+title+'"></ul></div>',  
            });   
	        $('#west_accordion').accordion({  
	            onSelect : function(title, index) {  
	                $("ul[name='" + title + "']").tree({  
	                    url : '${pageContext.request.contextPath}/cms/home/tree23',  
	                    queryParams : {  
	                        text : title , //父菜单名称
	                        id : id
	                    },  
	                    animate : false,  //关闭动画
	                    border : false,
	                    fit : true,
	                    lines : true,//显示虚线效果    
	                    onClick: function(node){// 在用户点击一个子节点即二级菜单时触发addTab()方法,用于添加tabs  
	                    	var bl=$(this).tree('isLeaf',node.target);
	                    	if (bl) {
	                            //alert("this is leaf");
	                            //将本页面id传至本页面   
	                    		addTab(node.text,node.url+"?menuid="+node.id,node.iconCls);  
	                        }
	                    	 
	                    },
	                    //EasyUI Tree 默认选中跟节点
	                    onLoadSuccess: function (node, data) {//数据加载完成事件
	                        var rootNode = data[0].children[0];
	                        if (rootNode) {
	                            //alert(rootNode.id);
	                        }
	                        var rootNode = $(this).tree("getRoot"); //获取根节点
	                        $(this).tree("select",rootNode.target);//根节点 被选中,选中的同时也是执行了点击的事件
	                        $(this).tree("collapseAll");
	                    }
	                });  
	            }  
	        });  
			
		}
	</script>
  </head>
  <body>
 	  	<!-- 头部信息 --> 
		<div data-options="region:'north',border:false" style="height:40px;line-height:36px;background:#01A09C;padding:0px;color:#FFF">
			<div style="float: left;padding-left: 10;font-size: 20"><span id="title">SPD信息管理系统</span></div>  
			
			<div style="float: left;padding-left: 100;">
				<!-- 二级菜单 -->
				<c:forEach items="${m1 }" var="L1" varStatus="status">
					<a href="javascript:void(0)" class="easyui-menubutton" 
						data-options="menu:'#nav${status.index}',iconCls:'${L1.iconCls }'"
						onclick="refreshLeftTree('${L1.id }','${L1.text }','${status.index}','${L1.iconCls }')" 
						style="color:#fff;" 
						>${L1.text }</a>
						<div id="nav${status.index}" class="menu-content" style="background:#fff;padding:10px;text-align:left;font-size:14;">
							<div>
								<!-- 三级菜单 -->
								<c:forEach items="${L1.children}" var="L2" >
									<dl style="">
										<dt style=""><a href="javascript:void(0)">${L2.text}</a></dt>
										<c:forEach items="${L2.children}" var="L3" >
											<dd style="">
												<%-- <a href="javascript:void(0)"  onclick="addTab('${L3.text}','${pageContext.request.contextPath}/${L3.url}?menuid=${L3.id}' ,'${L3.iconCls}')">${L3.text}</a> --%>
												<a href="javascript:void(0)" class='easyui-linkbutton' iconCls='${L3.iconCls}' plain='true' onclick="addTab('${L3.text}','${pageContext.request.contextPath}/${L3.url}?menuid=${L3.id}' ,'${L3.iconCls}')">${L3.text}</a>
											</dd>
										</c:forEach> 
									</dl>
								</c:forEach> 
								<!-- 三级菜单end -->
							</div> 
						</div>
				</c:forEach>
				<!-- 二级菜单end -->
			</div>
			
			
			
			<div style="float: right;c">
				<a id="mb1" href="javascript:void(0)"style="color:#fff;">个人中心</a>   
				<div id="mm1" > 
					<div iconCls="icon-man">个人信息</div> 
					<div iconCls="icon-edit">修改密码</div> 
				</div> 
				<a id="mb2" href="javascript:void(0)"style="color:#fff;">系统操作</a>   
				<div id="mm2" > 
					<div iconCls="icon_door_out">
						<a href="${pageContext.request.contextPath}/cms/logout" style="text-decoration:none">退出系统</a>
					</div> 
				</div>  
			</div>
		</div>
		
		<!-- ***左边菜单导航*** -->
		<div id="west" data-options="region:'west',border:true,split:false,collapsible:true,title:'导航菜单',iconCls:'icon_text_list_bullets'" style="width:180px;">
	    	<div id="west_accordion"  class="easyui-accordion" data-options="border:false,fit:true">  <!-- 手风琴 -->
		    	<div  data-options="iconCls:'icon-search',collapsed:false,collapsible:false,border:false" 
					  style="padding:10px 5px 10px 20px;background-color:#FFF ;color:#000;">  
			        <!-- 个人照片 -->
			        <div style="float:left;border:0px solid #000;">
			        	<img alt="" src="${pageContext.request.contextPath}/img/user.png" style="height:40px"> 
			        </div style="float: right;border:0px solid #000;">
			        	<span style="color:#fff">${user.realname }</span>
			        
			        <!-- 系统按钮 -->
			        <a href="javascript:void(0)" class="easyui-menubutton" data-options="menu:'#menubutton',iconCls:'icon_cog'" style="float: right;border:0px solid #000;"></a> 
			        <div id="menubutton"> 
						<div iconCls="icon-edit">修改密码</div> 
						<div iconCls="icon_door_out">
							<a href="${pageContext.request.contextPath}/cms/logout" style="text-decoration:none">退出系统</a>
						</div> 
					</div>
		    	</div>
		    	
		    	
		    	<div>
		    	
		    	</div>
	    		<!-- <div class="easyui-tree" id="west_tree" data-options="fit:true,border:false">
	    			
				</div> -->
		    	
	    	</div>  
		</div>  
		
		
		<!-- ***右边索引查询*** -->
		<%--  <div id="east" data-options="region:'east',split:true,collapsed:true,title:'个人中心'" style="width:200px;padding:10px;">
			<div class="easyui-panel"  data-options="fit:true,border:false">
		        <ul class="easyui-tree" 
		        	data-options="url:'${pageContext.request.contextPath}/js/jquery-easyui-1.6.10/tree_data2.json',method:'get',animate:true,lines:true">
		        </ul> 
		    </div> 
		</div>  --%>  
	
		<!-- 中间内容 -->
		<div id="Center" data-options="region:'center',border:false">
			<!-- ***TabPage*** -->
		    <div id="tt" class="easyui-tabs" style="width:700px;height:250px;"  data-options="fit:true,border:true,narrow:true,plain:true">
		        <div title="首页" style="padding:10px" data-options="iconCls:'icon_house'" >
		           <iframe scrolling="auto" frameborder="0"  src="cms/desktop" style="width:100%;height:100%;"></iframe>
		        </div>
		    </div> 
		</div>
		
		<!-- ***页尾*** -->
		<div id="south" data-options="region:'south',border:false" style="height:20px;background:#01A09C;padding:0px;color:#FFF">
			<span style=" float:left;display:block;">当前用户:${user.realname }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;所属科室:供应室</span>
			<span style=" float:right;display:block;">版本v1.0</span>
		</div>   
		
		
		<div id="changePsw">
		<form id="myform">
			<table id="table-psw" > 
				<tr>
					<td>旧密码：</td>
					<td><input type="password"  id="oldpassword" name="oldpassword"  /> </td>
					<td></td>
				</tr>
				<tr> <td></td> <td> </td>  <td> </td> </tr>
				<tr> <td></td> <td> </td>  <td> </td> </tr>
				<tr> <td></td> <td> </td>  <td> </td> </tr>
				<tr>
					<td>新密码：</td>
					<td><input type="password" id="newpassword" name="newpassword"  onKeyUp="javascript:AuthPasswd();"/></td> 
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td><div id="strengt-span"> <div id="weak" ></div> <div id="middle"></div> <div id="strength"></div> </div></td> 
					<td></td>
				</tr>
				<tr>
					<td>密码确认：</td>
					<td colspan=2><input type="password"  id="newpassword2" name="newpassword2" /></td>
				</tr>
			</table> 
		</form>
		<div id="info" style="color:#F00"></div>
	</div>
  </body>
</html>
