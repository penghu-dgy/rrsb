$(function () {
    $("#jqGrid").jqGrid({
        url: '../activity/list',
        datatype: "json",
        colModel: [			
        	{ label: '活动类型', name: 'actType', index: '$actType', width: 40 }, 			
			{ label: '开始时间', name: 'beginTime', index: '$beginTime', width: 80 }, 			
			{ label: '结束时间', name: 'endTime', index: '$endTime', width: 80 }, 			
			{ label: '活动天数', name: 'days', index: '$days', width: 30 }, 			
			{ label: '原由', name: 'actReason', index: '$actReason', width: 80 }, 			
			/*{ label: '地址', name: 'actaddress', index: '$actAddress', width: 80 }, 			
			*/{ label: '申请人工号', name: 'actPulisher', index: '$actPulisher', width: 40 }, 			
			{ label: '审批人工号', name: 'actChecker', index: '$actChecker', width: 40 }, 			
			{ label: '活动状态', name: 'actState', index: '$actState', width: 30 }, 			
			
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
		activity: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.activity = {};
		},
		update: function (event) {
			var actid = getSelectedRow();
			if(actid == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(actid)
		},
		saveOrUpdate: function (event) {
			var url = vm.activity.actid == null ? "../activity/save" : "../activity/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.activity),
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
			var actids = getSelectedRows();
			if(actids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../activity/delete",
				    data: JSON.stringify(actids),
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
		getInfo: function(actid){
			$.get("../activity/info/"+actid, function(r){
                vm.activity = r.activity;
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