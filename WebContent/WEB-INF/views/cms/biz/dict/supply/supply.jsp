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
							url : WEB_ROOT + '/cms/biz/dict/getSupply',
							toolbar : "#toolbar",
							onDblClickCell : doubleClickCell,
							columns : [ [ {
								title : '供应商名称',
								field : 'supplyname',
								width : 180,
								align : 'left',
								editor : {
									type : 'textbox',
									options : {
										required : true,
									}
								}
							}, {
								title : '供应商编码',
								field : 'supplyopcode',
								width : 150,
								align : 'left',
								editor : "textbox"
							}, {
								title : '旧编码',
								field : 'supplyno',
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
								title : '税号',
								field : 'taxnumber',
								width : 100,
								align : 'left',
								editor : "textbox"
							}, {
								title : '法人',
								field : 'legalperson',
								width : 100,
								align : 'left',
								editor : "textbox"
							}, {
								title : '证照管控类型',
								field : 'GSPCATEGORYID',
								width : 100,
								align : 'left',
								hidden : true
							}, {
								title : '注册地址',
								field : 'registadd',
								width : 100,
								align : 'left',
								editor : "textbox"
							}, {
								title : '仓库地址',
								field : 'address',
								width : 100,
								align : 'left',
								editor : "textbox"
							}, {
								title : '状态',
								field : 'usestatus',
								width : 100,
								align : 'left',
								editor : {
									type : 'combobox',
									options : {
										valueField : 'usestatus',
										textField : 'text',
										data : [ {
											usestatus : '0',
											text : '临时',
											'selected' : 'true'
										}, {
											usestatus : '1',
											text : '正式'
										} ],
										panelHeight : 'auto',
										disabled : true
									}
								},
								formatter : function(value, row) {
									return row.text;
								}
							}, {
								title : '备注',
								field : 'memo',
								width : 100,
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
								width : 100,
								align : 'left'
							}, {
								title : '录入人ID',
								field : 'inputmanid',
								width : 60,
								align : 'left'
							}, {
								title : '供应商ID',
								field : 'supplyid',
								width : 60,
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
	function newSupply() {
		if (endEdit()) {
			$("#dg").datagrid('appendRow', {
				inputman : "${user.realname}",
				inputmanid : "${user.userid}",
				usestatus : "0",
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
	function delSupply() {
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
		var url = WEB_ROOT + '/cms/biz/dict/getSupply?'
				+ $('#fmQuery').serialize();
		$.post(url, {}, function(result) {
			if (parseInt(result.total) > 0) {

				$('#dg').datagrid('reload', url);
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
	function saveSupply() {
		if (endEdit()) {
			if ($("#dg").datagrid('getChanges').length) {
				var changedRows = new Object();
				changedRows["inserted"] = $("#dg").datagrid('getChanges',
						"inserted");
				changedRows["deleted"] = $("#dg").datagrid('getChanges',
						"deleted");
				changedRows["updated"] = $("#dg").datagrid('getChanges',
						"updated");
				$.post(WEB_ROOT + '/cms/biz/dict/saveSupply', {
					json : JSON.stringify(changedRows)
				}, function(result) {
					if (result.success) {
						//$('#dg').datagrid('refreshRow');

						/*  var row = $('#dg').datagrid('getSelected');
											    var index = $('#dg').datagrid('getRowIndex', row);
											    row.rec = '<font color="red">是</font>';
											    $('#dg').datagrid('refreshRow', index); */
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

	var editIndex = undefined;
	function endEditing() {
		if (editIndex == undefined) {
			return true
		}
		if ($('#dg').datagrid('validateRow', editIndex)) {
			var ed = $('#dg').datagrid('getEditor', {
				index : editIndex,
				field : 'productid'
			});
			var productname = $(ed.target).combobox('getText');
			$('#dg').datagrid('getRows')[editIndex]['productname'] = productname;
			$('#dg').datagrid('endEdit', editIndex);
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}
	function onClickRow(index) {
		if (editIndex != index) {
			if (endEditing()) {
				$('#dg').datagrid('selectRow', index).datagrid('beginEdit',
						index);
				editIndex = index;
			} else {
				$('#dg').datagrid('selectRow', editIndex);
			}
		}
	}
	function append() {
		if (endEditing()) {
			$('#dg').datagrid('appendRow', {
				status : 'P'
			});
			editIndex = $('#dg').datagrid('getRows').length - 1;
			$('#dg').datagrid('selectRow', editIndex).datagrid('beginEdit',
					editIndex);
		}
	}
	function removeit() {
		if (editIndex == undefined) {
			return
		}
		$('#dg').datagrid('cancelEdit', editIndex).datagrid('deleteRow',
				editIndex);
		editIndex = undefined;
	}
	function accept() {
		if (endEditing()) {
			$('#dg').datagrid('acceptChanges');
		}
	}
	function reject() {
		$('#dg').datagrid('rejectChanges');
		editIndex = undefined;
	}
	function getChanges() {
		var rows = $('#dg').datagrid('getChanges');
		alert(rows.length + ' rows are changed!');
	}
</script>
<body>
	<!-- 数据列表 -->
	<table id="dg"></table>

	<div id="toolbar">
		<form id="fmQuery" method="post" style="border-top: 1px #DDDDDD solid">
			<div class="">
				编码：<input id="supplyopcode" name="supplyopcode"
					class="easyui-textbox" style="width: 80px">&nbsp;&nbsp; 名称：<input
					id="supplyname" name="supplyname" class="easyui-textbox"
					style="width: 80px">&nbsp;&nbsp;
			</div>
		</form>
	</div>
</body>
</html>