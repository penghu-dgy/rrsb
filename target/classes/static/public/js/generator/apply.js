$(function () {
    $("#jqGrid").jqGrid({
        url: '../apply/list',
        datatype: "json",
        colModel: [			
			/*{ label: 'appId', name: 'appId', index: 'app_id', width: 50, key: true },
			{ label: 'job_id', name: 'job_id', index: 'job_id', width: 80 }, 			
			*/{ label: '职位名称', name: 'job_name', index: 'job_name', width: 80 }, 			
			/*{ label: '', name: 'emp_id', index: 'emp_id', width: 80 }, 			
			*/{ label: '申请人', name: 'emp_name', index: 'emp_name', width: 80 }, 			
			{ label: '电话', name: 'emp_phone', index: 'emp_phone', width: 80 }, 			
			{ label: '申请时间', name: 'emp_time', index: 'emp_time', width: 80 }, 			
			{ label: '公司名', name: 'company', index: 'company', width: 80 }, 			
			{ label: '所招人数', name: 'job_count', index: 'job_count', width: 80 }, 			
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
		apply: {},
		q:{key:''},
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.apply = {};
		},
		update: function (event) {
			var appId = getSelectedRow();
			if(appId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(appId)
		},
		saveOrUpdate: function (event) {
			var url = vm.apply.appId == null ? "../apply/save" : "../apply/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.apply),
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
			var appIds = getSelectedRows();
			if(appIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../apply/delete",
				    data: JSON.stringify(appIds),
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
		getInfo: function(appId){
			$.get("../apply/info/"+appId, function(r){
                vm.apply = r.apply;
            });
		},
		reload: function (event) {
			
				vm.showList = true;
				var page = $("#jqGrid").jqGrid('getGridParam','page');
				$("#jqGrid").jqGrid('setGridParam',{ 
	                postData:{'company': vm.q.key},
	                page:page
	            }).trigger("reloadGrid");
			
				
				
			
				
			
			
			return
		}
		
		
	}
});