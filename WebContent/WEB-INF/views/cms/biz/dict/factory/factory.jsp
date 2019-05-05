<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="../../../../inc/injs.jsp" />
</head>
<script type="text/javascript">
	$(function() {
		$("#dg")
				.datagrid(
						{
							url : '',
							toolbar : "#toolbar",
							onDblClickCell : doubleClickCell,
							columns : [ [ {
								title : '厂家名称',
								field : 'factoryname',
								width : 180,
								align : 'left',
								editor : {
									type : 'textbox',
									options : {
										required : true,
									/* onChange : function(value) {//输入生产厂家，自动获取拼音编码
										var rows = $('#dg').datagrid("getRows");
										rows[0][factorycode]=123;
									} */
									}
								}
							}, {
								title : '厂家编码',
								field : 'factorycode',
								width : 150,
								align : 'left',
								editor : "textbox"
							}, {
								title : '地区',
								field : 'factoryarea',
								width : 60,
								align : 'left',
								editor : "textbox"
							}, {
								title : '组织代码',
								field : 'corpcode',
								width : 150,
								align : 'left',
								editor : "textbox"
							}, {
								title : '创建日期',
								field : 'credate',
								width : 100,
								align : 'left'
							}, {
								title : '录入人',
								field : 'inputman',
								width : 160,
								align : 'left'
							}, {
								title : '录入人ID',
								field : 'inputmanid',
								width : 60,
								align : 'left'
							}, {
								title : '厂家ID',
								field : 'factoryid',
								width : 160,
								align : 'left'
							} ] ],
							singleSelect : true,
							striped : true,
							fit : true,
							border : false,
							//title:'用户列表',
							rownumbers : true,
							toolbar : "#toolbar",
							rownumbers : true,
							//编辑费用大于零则另一方赋值为空
							onBeginEdit : function(rowIndex, rowData) {
								var editors = $('#dg').datagrid('getEditors',
										rowIndex);
								var lendEditor = editors[0];
								var loadEditor = editors[1];
								//target属性就用于返回最初触发事件的DOM元素                
								lendEditor.target.textbox({
									onChange : function(newValue, oldValue) {
										loadEditor.target.textbox('setValue',
												makePy(newValue));
									}
								});
							},
						});
		//加载按钮()
		getButtons("${menuid}", $('#toolbar'), 'toolbar');
	});

	//刷新
	function refreshUser() {
		$('#dg').datagrid('reload');
	};

	//新增
	function newFactory() {
		if (endEdit()) {
			$("#dg").datagrid('appendRow', {
				inputman : "${user.realname}",
				inputmanid : "${user.userid}",
				credate : getNowFormatDate()
			});
			var rows = $("#dg").datagrid('getRows');
			$("#dg").datagrid('beginEdit', rows.length - 1);
		}
	};
	//编辑
	function editUser() {
		if (endEdit()) {
			var row = $("#dg").datagrid('getSelected');
			if (row) {
				var rowIndex = $("#dg").datagrid('getRowIndex', row);
				$("#dg").datagrid('beginEdit', rowIndex);
			}
		}
	};
	//取消
	function reject() {
		$('#dg').datagrid('rejectChanges');
	}
	//删除
	function delFactory() {
		if (endEdit()) {

			var row = $("#dg").datagrid('getSelected');
			if (row) {
				var rowIndex = $("#dg").datagrid('getRowIndex', row);
				$("#dg").datagrid('deleteRow', rowIndex);
			}
		}
	}
	//查询
	function query() {
		/* 	$('#dg').datagrid({
				url : WEB_ROOT + '/cms/biz/dict/getFactory'
			}); */
		if ($("#dg").datagrid('getChanges').length > 0) {
			$.messager.confirm('提示', '有数据为保存，是否依然查询，可能导致数据丢失', function(r) {
				if (r) {
					searh();
				}
			});
		} else {
			searh();
		}
	}

	function searh() {
		$.post(WEB_ROOT + '/cms/biz/dict/getFactory?'
				+ $('#fmQuery').serialize(), {}, function(result) {
			if (parseInt(result.total) > 0) {
				$('#dg').datagrid({
					data : result
				});
			} else {
				$.messager.show({
					title : '提示',
					msg : '未查询到相关信息！',
					showType : 'show'
				});
			}
		}, "JSON");
	}

	//保存
	function saveFactory() {
		if (endEdit()) {

			if ($("#dg").datagrid('getChanges').length) {
				var changedRows = new Object();
				changedRows["inserted"] = $("#dg").datagrid('getChanges',
						"inserted");
				changedRows["deleted"] = $("#dg").datagrid('getChanges',
						"deleted");
				changedRows["updated"] = $("#dg").datagrid('getChanges',
						"updated");
				$.post(WEB_ROOT + '/cms/biz/dict/saveFactory', {
					json : JSON.stringify(changedRows)
				}, function(result) {
					if (result.success) {
						$('#dg').datagrid('reload');
					}
					$.messager.show({
						title : '提示',
						msg : result.msg,
						showType : 'show'
					});

					$('#dg').datagrid('acceptChanges');

				}, "JSON");
			}
		}

	}
	//结束编辑
	function endEdit() {
		var rows = $("#dg").datagrid('getRows');
		for (var i = 0; i < rows.length; i++) {
			//表单验证
			if (!$('#dg').datagrid('validateRow', i)) {
				$.messager.show({
					title : '提示',
					msg : "必填项不能空!",
					showType : 'show'
				});
				$('#dg').datagrid('selectRow', i);// 关键在这里
				return false;
			}
			//结束编辑
			$("#dg").datagrid('endEdit', i);
		}
		return true;
	}
	function doubleClickCell() {
		endEdit();
		var row = $("#dg").datagrid('getSelected');
		if (row) {
			var rowIndex = $("#dg").datagrid('getRowIndex', row);
			$("#dg").datagrid('beginEdit', rowIndex);
		}
	}

	function onEndEdit(index, row) {

		var ed = $(this).datagrid('getEditor', {

		});

	}
</script>
<body>
	<!-- 数据列表 -->
	<table id="dg"></table>

	<div id="toolbar">
		<form id="fmQuery" method="post" style="border-top: 1px #DDDDDD solid">
			<div class="div-toolbar ">
				编码：<input id="factorycode" name="factorycode" class="easyui-textbox"
					style="width: 80px">&nbsp;&nbsp; 名称：<input id="factoryname"
					name="factoryname" class="easyui-textbox" style="width: 80px">&nbsp;&nbsp;
			</div>
		</form>
	</div>
</body>
</html>