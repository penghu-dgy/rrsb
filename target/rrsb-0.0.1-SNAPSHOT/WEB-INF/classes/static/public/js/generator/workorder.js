$(function () {
    $("#jqGrid").jqGrid({
        url: '../workorder/list',
        datatype: "json",
        colModel: [
			{ label: '订单号', name: 'orderId', index: 'orderId', width: 80, key: true },
			{ label: '订单类型', name: 'orderType', index: 'orderType', width: 40 }, 			
			{ label: '发布人', name: 'empName', index: 'orderBeginTime', width: 80 }, 			
			{ label: '发布时间', name: 'bz1', index: 'orderDesc', width: 80 }, 			
			{ label: '客户姓名', name: 'custName', index: 'orderCust', width: 100 }, 	
			{  
                name: 'myac', index: '', width: 80, fixed: true, sortable: false, resize: false,  
                //formatter:'actions',  
                formatter: function (value, grid, rows, state) { return "<button href=\"#\" class=\" btn btn-primary\" onclick=\"vm.a(" + rows.orderId + ")\">查看详情</button>" }  
                //formatoptions:{  
                //  keys:true,  
                //delbutton: false,//disable delete button  

                //  delOptions:{recreateForm: true, beforeShowForm:beforeDeleteCallback},  
                //editformbutton:true, editOptions:{recreateForm: true, beforeShowForm:beforeEditCallback}  
                //}  
            },  
            
			/*{
                label: '详情', name: '', index: 'operate', width: 50, align: 'center',
                formatter: function (cellvalue, options, rowObject) {
                    var detail="<input type='button'  @click='vm.a(\""+ this + "\")' value='查看详情'  class='btn btn-primary'>";
                    return detail;
                },
            },*/
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
		workorder: {},
		q:{
			key: null
		},
		
	},
	methods: {
		query: function () {
			vm.reload();
			//alert("asdasd")
		},
		a: function(i){
			vm.showList = false;
            vm.title = "查看详情";
			$.get("../workorder/info",{'orderId':i}, function(r){
                vm.workorder = r.workorder;
               
            });
			
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.workorder = {};
		},
		update: function (event) {
			
			var orderid = getSelectedRow();

			if(orderid == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(orderid)
		},
		saveOrUpdate: function (event) {
			var url = vm.workorder.orderId == null ? "../workorder/save" : "../workorder/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.workorder),
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
			var orderids = getSelectedRows();
			if(orderids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../workorder/delete",
				    data: JSON.stringify(orderids),
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
		getInfo: function(orderid){
			//alert(orderid)
			$.get("../workorder/info",{'orderId':orderid}, function(r){
                vm.workorder = r.workorder;
               
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