$(function () {
    $("#jqGrid").jqGrid({
        url: '../workorder/list1',
        datatype: "json",
        colModel: [			
			{ label: '工单编号', name: 'orderid', index: 'orderId', width: 80, key: true },
			{ label: '类型', name: 'ordertype', index: 'orderType', width: 50 }, 			
			{ label: '开始时间', name: 'orderbegintime', index: 'orderBeginTime', width: 100 }, 			
			{ label: '结束时间', name: 'orderendtime', index: 'orderEndTime', width: 100 }, 			
			{ label: '描述', name: 'orderdesc', index: 'orderDesc', width: 120 }, 			
			{ label: '客户编号', name: 'ordercust', index: 'orderCust', width: 50 }, 			
			{ label: '工单状态', name: 'orderstate', index: 'orderState', width: 50 }, 			
			{ label: '发布人', name: 'orderemp', index: 'orderEmp', width: 50 }, 			
			{ label: '发布时间', name: 'bz1', index: 'bz1', width: 100 }, 			
			/*{ label: '', name: 'bz2', index: 'bz2', width: 80 }, 			
			{ label: '', name: 'bz3', index: 'bz3', width: 80 }, */			
			{ label: '企业ID', name: 'entid', index: 'entId', width: 80 }			
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
		workorder: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.workorder = {};
		},
		update: function (event) {
			var orderid = getSelectedRow();
			if(orderid == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(orderid)
		},
		saveOrUpdate: function (event) {
			vm.reload();
			/*var url = vm.workorder.orderid == null ? "../workorder/save" : "../workorder/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.workorder),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});*/
		},
		del: function (event) {
			var orderids = getSelectedRows();
			if(orderids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../workorder/delete",
				    data: JSON.stringify(orderids),
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
		getInfo: function(orderid){
			$.get("../workorder/info/"+orderid, function(r){
                vm.workorder = r.workorder;
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