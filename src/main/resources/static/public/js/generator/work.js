$(function () {
    $("#jqGrid").jqGrid({
        url: '../work/list',
        datatype: "json",
        colModel: [			
			{ label: 'workid', name: 'workid', index: '$workId', width: 50, key: true },
			{ label: '', name: 'workcontent', index: '$workContent', width: 80 }, 			
			{ label: '', name: 'worktime', index: '$workTime', width: 80 }, 			
			{ label: '', name: 'workemp', index: '$workEmp', width: 80 }, 			
			{ label: '', name: 'workcust', index: '$workCust', width: 80 }, 			
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
		work: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.work = {};
		},
		update: function (event) {
			var workid = getSelectedRow();
			if(workid == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(workid)
		},
		saveOrUpdate: function (event) {
			var url = vm.work.workid == null ? "../work/save" : "../work/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.work),
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
			var workids = getSelectedRows();
			if(workids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../work/delete",
				    data: JSON.stringify(workids),
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
		getInfo: function(workid){
			$.get("../work/info/"+workid, function(r){
                vm.work = r.work;
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