$(function () {
    $("#jqGrid").jqGrid({
        url: '../operation/list',
        datatype: "json",
        colModel: [			
			{ label: 'operid', name: 'operid', index: '$operId', width: 50, key: true },
			{ label: '', name: 'operdesc', index: '$operDesc', width: 80 }, 			
			{ label: '', name: 'opertime', index: '$operTime', width: 80 }, 			
			{ label: '', name: 'operemp', index: '$operEmp', width: 80 }, 			
			{ label: '', name: 'operorder', index: '$operOrder', width: 80 }, 			
			{ label: '', name: 'bz1', index: '$bz1', width: 80 }, 			
			{ label: '', name: 'bz2', index: '$bz2', width: 80 }, 			
			{ label: '', name: 'bz3', index: '$bz3', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		operation: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.operation = {};
		},
		update: function (event) {
			var operid = getSelectedRow();
			if(operid == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(operid)
		},
		saveOrUpdate: function (event) {
			var url = vm.operation.operid == null ? "../operation/save" : "../operation/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.operation),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var operids = getSelectedRows();
			if(operids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../operation/delete",
				    data: JSON.stringify(operids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(operid){
			$.get("../operation/info/"+operid, function(r){
                vm.operation = r.operation;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});