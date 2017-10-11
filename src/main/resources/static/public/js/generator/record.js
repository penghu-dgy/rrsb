$(function () {
    $("#jqGrid").jqGrid({
        url: '../record/list',
        datatype: "json",
        colModel: [			
			{ label: 'recordid', name: 'recordid', index: '$recordId', width: 50, key: true },
			{ label: '', name: 'recordcontent', index: '$recordContent', width: 80 }, 			
			{ label: '', name: 'recordaddress', index: '$recordAddress', width: 80 }, 			
			{ label: '', name: 'recordtime', index: '$recordTime', width: 80 }, 			
			{ label: '', name: 'recordemp', index: '$recordEmp', width: 80 }, 			
			{ label: '', name: 'recordorderid', index: '$recordOrderId', width: 80 }, 			
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
		record: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.record = {};
		},
		update: function (event) {
			var recordid = getSelectedRow();
			if(recordid == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(recordid)
		},
		saveOrUpdate: function (event) {
			var url = vm.record.recordid == null ? "../record/save" : "../record/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.record),
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
			var recordids = getSelectedRows();
			if(recordids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../record/delete",
				    data: JSON.stringify(recordids),
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
		getInfo: function(recordid){
			$.get("../record/info/"+recordid, function(r){
                vm.record = r.record;
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