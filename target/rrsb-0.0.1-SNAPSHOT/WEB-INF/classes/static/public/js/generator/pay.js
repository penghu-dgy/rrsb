$(function () {
    $("#jqGrid").jqGrid({
        url: '../pay/list',
        datatype: "json",
        colModel: [			
        	{ label: '支付编号', name: 'zfbh', index: '$zfbh', width: 50, key: true },
			{ label: '金额', name: 'payMoney', index: '$payMoney', width: 80 }, 			/*
			{ label: '过期时间', name: 'payexpiretime', index: '$payExpireTime', width: 80 },*/ 			
			{ label: '支付时间', name: 'payTime', index: '$payTime', width: 80 }, 			
			{ label: '姓名', name: 'empName', index: '$empName', width: 80 }, 			
			{ label: '支付类型', name: 'zflx', index: '$zflx', width: 80 }, 			
			/*{ label: '', name: 'bz2', index: '$bz2', width: 80 }, 			
			{ label: '', name: 'bz3', index: '$bz3', width: 80 }		*/			
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
		pay: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.pay = {};
		},
		update: function (event) {
			var payid = getSelectedRow();
			if(payid == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(payid)
		},
		saveOrUpdate: function (event) {
			var url = vm.pay.payid == null ? "../pay/save" : "../pay/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.pay),
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
			var payids = getSelectedRows();
			if(payids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../pay/delete",
				    data: JSON.stringify(payids),
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
		getInfo: function(payid){
			$.get("../pay/info/"+payid, function(r){
                vm.pay = r.pay;
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