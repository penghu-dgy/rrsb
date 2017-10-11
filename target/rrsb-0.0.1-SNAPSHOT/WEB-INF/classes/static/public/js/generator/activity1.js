$(function () {
    $("#jqGrid").jqGrid({
        url: '../activity/list1',
        datatype: "json",
        colModel: [			
			{ label: '编号', name: 'actid', index: 'actId', width: 40, key: true },
			{ label: '类型', name: 'acttype', index: 'actType', width: 50 }, 			
			{ label: '开始时间', name: 'actbegintime', index: 'actBeginTime', width: 100 }, 			
			{ label: '结束时间', name: 'actendtime', index: 'actEndTime', width: 100 }, 			
			{ label: '天数', name: 'actdays', index: 'actDays', width: 40 }, 			
			{ label: '活动原因', name: 'actreason', index: 'actReason', width: 150 }, 			
			/*{ label: '', name: 'actaddress', index: 'actAddress', width: 80 }, 	*/		
			{ label: '发布人', name: 'actpublisher', index: 'actPublisher', width: 50 }, 			
			{ label: '审核人', name: 'actchecker', index: 'actChecker', width: 50 }, 			
			{ label: '状态', name: 'actstate', index: 'actState', width: 50 }, 			
			{ label: '发布时间', name: 'actpublishtime', index: 'actPublishTime', width: 100 }, 			
			/*{ label: '', name: 'bz2', index: 'bz2', width: 80 }, 			
			{ label: '', name: 'bz3', index: 'bz3', width: 80 }	*/		
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
			console.log("actid="+actid);
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