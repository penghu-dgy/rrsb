$(function () {
    $("#jqGrid").jqGrid({
        url: '../enterprise/list',
        datatype: "json",
        colModel: [			
			{ label: '编号', name: 'entid', index: 'entId', width: 50, key: true },
			{ label: '企业名称', name: 'entname', index: 'entName', width: 80 }, 			
			{ label: '申请时间', name: 'entcreatetime', index: 'entCreateTime', width: 80 }, 			
			/*{ label: '负责人ID', name: 'entemp', index: 'entEmp', width: 80 },*/ 			
			{ label: '负责人姓名', name: 'entmanager', index: 'entManager', width: 80 }, 			
			{ label: '电话', name: 'entphone', index: 'entPhone', width: 80 }, 			
			/*{ label: '状态', name: 'entstate', index: 'entState', width: 80 }, */			
			{ label: '省', name: 'entprovice', index: 'entProvice', width: 80 }, 			
			{ label: '市', name: 'entcity', index: 'entCity', width: 80 }, 			
			{ label: '区', name: 'entarea', index: 'entArea', width: 80 }, 			
			{ label: '详细地址', name: 'entaddress', index: 'entAddress', width: 80 }, 			
			{ label: '企业类型', name: 'bz1', index: 'bz1', width: 80 }, 			
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
		enterprise: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.enterprise = {};
		},
		update: function (event) {
			var entid = getSelectedRow();
			if(entid == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(entid)
		},
		saveOrUpdate: function (event) {
			var url = vm.enterprise.entid == null ? "../enterprise/save" : "../enterprise/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.enterprise),
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
			var entids = getSelectedRows();
			if(entids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../enterprise/delete",
				    data: JSON.stringify(entids),
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
		getInfo: function(entid){
			$.get("../enterprise/info/"+entid, function(r){
                vm.enterprise = r.enterprise;
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