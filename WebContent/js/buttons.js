

//js获取项目根路径，如： http://localhost:8083/uimcardprj
function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}

/*
 * menuid	是本页面在sys_menu中的id
 * obj		是需要添加按钮的div的jquery对象, 如$("#toolbar")
 * divid 	是工具栏的div  id 
 */
function getButtons(menuid, obj,divid){
	$.post(getRootPath()+'/cms/sysmenu/getButtons', { menuid:menuid,divid:divid }, 
			function(result) {
				if(result.success){ 
					//生成按钮
					var content = 
								    "<table cellpadding='1' cellspacing='1'>  " +
								       "<tr>	" ;
										for ( i in result.buttons) {
											if(i==0){content +="<td><div class='btn-separator'></div></td>";}
											content += '<td><a href="javascript:void(0);" class="easyui-linkbutton" ' +
													"iconCls='"+result.buttons[i].iconcls+"' plain='true' " +
													"onclick='"+result.buttons[i].functionname+"'>"+result.buttons[i].text+"</a></td>" +
													"<td><div class='btn-separator'></div></td>" ;
										}
						content +=      "</tr> " +
							        "</table>" ;
					obj.prepend(content);
					//重新渲染组件
					$.parser.parse(obj);
				}else{
					//没有按钮
				}
			}, "JSON");
}



