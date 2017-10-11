$(function () {
    $("#jqGrid").jqGrid({
        url: '../recruit/list',
        datatype: "json",
        colModel: [			
			{ label: 'recruitid', name: 'recruitid', index: 'recruitId', width: 50, key: true },
			{ label: '', name: 'recruittitle', index: 'recruitTitle', width: 80 }, 			
			{ label: '', name: 'recruitcontent', index: 'recruitContent', width: 80 }, 			
			{ label: '', name: 'recruittime', index: 'recruitTime', width: 80 }, 			
			{ label: '', name: 'recruitendtime', index: 'recruitEndTime', width: 80 }, 			
			{ label: '', name: 'recruitent', index: 'recruitEnt', width: 80 }, 			
			{ label: '', name: 'recruitemp', index: 'recruitEmp', width: 80 }, 			
			{ label: '', name: 'recruitstate', index: 'recruitState', width: 80 }, 			
			{ label: '', name: 'recruitflag', index: 'recruitFlag', width: 80 }, 			
			{ label: '', name: 'bz1', index: 'bz1', width: 80 }, 			
			{ label: '', name: 'bz2', index: 'bz2', width: 80 }, 			
			{ label: '', name: 'bz3', index: 'bz3', width: 80 }, 			
			{ label: '', name: 'recruitprovice', index: 'recruitProvice', width: 80 }, 			
			{ label: '', name: 'recruitcity', index: 'recruitCity', width: 80 }, 			
			{ label: '', name: 'recruitarea', index: 'recruitArea', width: 80 }			
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
		recruit: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.recruit = {};
		},
		update: function (event) {
			var recruitid = getSelectedRow();
			if(recruitid == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(recruitid)
		},
		saveOrUpdate: function (event) {
			var url = vm.recruit.recruitid == null ? "../recruit/save" : "../recruit/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.recruit),
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
			var recruitids = getSelectedRows();
			if(recruitids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../recruit/delete",
				    data: JSON.stringify(recruitids),
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
		getInfo: function(recruitid){
			$.get("../recruit/info/"+recruitid, function(r){
                vm.recruit = r.recruit;
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