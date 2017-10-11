$(function () {
    $("#jqGrid").jqGrid({
        url: '../customer/list',
        datatype: "json",
        colModel: [			
        	/*{ label: 'custid', name: 'custid', index: '$custId', width: 50, key: true },*/
			{ label: '客户姓名', name: 'custName', index: '$custName', width: 80 }, 			
			{ label: '客户类型', name: 'custType', index: '$custType', width: 80 }, 			
			{ label: '省', name: 'custProvince', index: '$custProvince', width: 80 }, 			
			{ label: '市', name: 'custCity', index: '$custCity', width: 80 }, 			
			{ label: '区', name: 'custArea', index: '$custArea', width: 80 }, 			
			{ label: '详细地址', name: 'custAddress', index: '$custAddress', width: 80 }, 			
			{ label: '手机号', name: 'custPhone', index: '$custPhone', width: 80 }, 			
		/*	{ label: '', name: 'custlng', index: '$custLng', width: 80 }, 			
			{ label: '', name: 'custlat', index: '$custLat', width: 80 }, 	*/		
			{ label: '添加时间', name: 'custTime', index: '$custtime', width: 80 }, 			
			/*{ label: '状态', name: 'custstate', index: '$custState', width: 80 }, 			*/
			{ label: '添加人', name: 'empName', index: '$empName', width: 80 }, 			
			/*{ label: '', name: 'bz1', index: '$bz1', width: 80 }, 			
			{ label: '', name: 'bz2', index: '$bz2', width: 80 }, 			
			{ label: '', name: 'bz3', index: '$bz3', width: 80 }	*/		
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
		customer: {},
		q:{key:''},
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.customer = {};
		},
		update: function (event) {
			var custid = getSelectedRow();
			if(custid == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(custid)
		},
		saveOrUpdate: function (event) {
			var url = vm.customer.custid == null ? "../customer/save" : "../customer/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.customer),
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
			var custids = getSelectedRows();
			if(custids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../customer/delete",
				    data: JSON.stringify(custids),
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
		getInfo: function(custid){
			$.get("../customer/info/"+custid, function(r){
                vm.customer = r.customer;
            });
		},
		reload: function (event) {
			var datetimeEnd=document.getElementById('datetimeEnd').value
			var datetimeStart=document.getElementById('datetimeStart').value
			if(datetimeEnd=='' || datetimeStart==''){
				vm.showList = true;
				var page = $("#jqGrid").jqGrid('getGridParam','page');
				$("#jqGrid").jqGrid('setGridParam',{ 
	                postData:{'sea': vm.q.key},
	                page:page
	            }).trigger("reloadGrid");
			}
			else{
				if(datetimeStart>datetimeEnd){
					alert('开始时间不得大于结束时间')
				}
				else{
					vm.showList = true;
					var page = $("#jqGrid").jqGrid('getGridParam','page');
					$("#jqGrid").jqGrid('setGridParam',{ 
		                postData:{'sea': vm.q.key,'startTime':datetimeStart,'endTime':datetimeEnd},
		                page:page
		            }).trigger("reloadGrid");
					
				}
				
				
			}
			
			return
		}
	}
});