$(function () {
    $("#jqGrid").jqGrid({
        url: '../order/list',
        datatype: "json",
        colModel: [			
			{ label: 'orderid', name: 'orderid', index: '$orderId', width: 50, key: true },
			{ label: '', name: 'ordertype', index: '$orderType', width: 80 }, 			
			{ label: '', name: 'orderbegintime', index: '$orderBeginTime', width: 80 }, 			
			{ label: '', name: 'orderendtime', index: '$orderEndTime', width: 80 }, 			
			{ label: '', name: 'orderdesc', index: '$orderDesc', width: 80 }, 			
			{ label: '', name: 'ordercust', index: '$orderCust', width: 80 }, 			
			{ label: '', name: 'orderstate', index: '$orderState', width: 80 }, 			
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
		order: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.order = {};
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
			var url = vm.order.orderid == null ? "../order/save" : "../order/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.order),
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
			var orderids = getSelectedRows();
			if(orderids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../order/delete",
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
			$.get("../order/info/"+orderid, function(r){
                vm.order = r.order;
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