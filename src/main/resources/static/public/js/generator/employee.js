$(function () {
    $("#jqGrid").jqGrid({
        url: '../employee/list',
        datatype: "json",
        colModel: [			
			/*{ label: 'empid', name: 'empid', index: 'empId', width: 50, key: true },*/
			{ label: '员工姓名', name: 'empname', index: 'empName', width: 80 }, 			
			{ label: '身份', name: 'emptype', index: 'empType', width: 80 }, 			
			/*{ label: '', name: 'enpbirthday', index: 'enpBirthday', width: 80 }, */			
			/*{ label: '', name: 'emphiretime', index: 'empHireTime', width: 80 }, 	*/		
			/*{ label: '', name: 'emptime', index: 'empTime', width: 80 }, 		*/	
			{ label: '过期时间', name: 'empexpiretime', index: 'empExpireTime', width: 80 }, 			
			/*{ label: '', name: 'empperformance', index: 'empPerformance', width: 80 }, 	*/		
			/*{ label: '', name: 'empsex', index: 'empSex', width: 80 }, 		*/	
			{ label: '手机号', name: 'empphone', index: 'empPhone', width: 80 }, 			
			/*{ label: '', name: 'emppassword', index: 'empPassword', width: 80 }, 			
			{ label: '', name: 'empmail', index: 'empMail', width: 80 }, 			
			{ label: '', name: 'empqq', index: 'empQQ', width: 80 }, 			
			{ label: '', name: 'empwechat', index: 'empWechat', width: 80 }, 	*/		
			{ label: '状态', name: 'empstate', index: 'empState', width: 80 }, 			
		/*	{ label: '', name: 'empent', index: 'empEnt', width: 80 }, 			
			{ label: '', name: 'empyzm', index: 'empYzm', width: 80 }, 			
			{ label: '', name: 'empdetail', index: 'empDetail', width: 80 }, 			
			{ label: '', name: 'empyzmexpiretime', index: 'empYzmExpireTime', width: 80 },*/ 			
		/*	{ label: '', name: 'bz1', index: 'bz1', width: 80 }, 			
			{ label: '', name: 'bz2', index: 'bz2', width: 80 }, 			
			{ label: '', name: 'bz3', index: 'bz3', width: 80 }			*/
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
		employee: {},
		q:{key:''},
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.employee = {};
		},
		update: function (event) {
			var empid = getSelectedRow();
			if(empid == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(empid)
		},
		saveOrUpdate: function (event) {
			var url = vm.employee.empid == null ? "../employee/save" : "../employee/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.employee),
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
			var empids = getSelectedRows();
			if(empids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../employee/delete",
				    data: JSON.stringify(empids),
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
		getInfo: function(empid){
			$.get("../employee/info/"+empid, function(r){
                vm.employee = r.employee;
            });
		},
		reload: function (event) {
			vm.showList = true;
			if(vm.q.key){
				var page = $("#jqGrid").jqGrid('getGridParam','page');
				$("#jqGrid").jqGrid('setGridParam',{ 
					 postData:{'sea': vm.q.key},
	                page:page
	            }).trigger("reloadGrid");
			}
			else{var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				
               page:page
           }).trigger("reloadGrid");}
			
		}
	}
});