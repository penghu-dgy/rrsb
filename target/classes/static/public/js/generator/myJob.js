$(function () {
    $("#jqGrid").jqGrid({
        url: '../job/list',
        datatype: "json",
        colModel: [			
			{ label: '职位ID', name: 'jobId', index: '$job_id', width: 50, key: true },
			{ label: '职位名称', name: 'jobTitle', index: '$job_title', width: 80 }, 					
			{ label: '发布时间', name: 'jobPubTime', index: '$job_pub_time', width: 80 }, 				
			{ label: '当前状态', name: 'jobState', index: '$job_state', width: 80 },			
			{  
                name: '操作', index: '', width: 130, fixed: true, sortable: false, resize: false,  
                //formatter:'actions',  
                formatter: function (value, grid, rows, state) { return "<button href=\"#\" class=\" btn btn-primary\" onclick=\"vm.selectApply(" + rows.jobId + ")\">查看申请记录</button>" }  
             
            }, 					
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
		showList1: true,
		title: null,
		job: {},
		showListApply:false,
		showList2:false,
		apply:[],
	},
	methods: {
		changeState:function(){
			var _this=this;
			if(_this.job.jobState != '2'){
				confirm('确认修改吗',function(){
					_this.job.jobState = '2';
					var url = vm.job.jobId == null ? "../job/save" : "../job/update";
					$.ajax({
						type: "POST",
					    url: url,
					    data: JSON.stringify(vm.job),
					    success: function(r){
					    	if(r.code === 0){
					    		alert('操作成功');
								
							}else{
								alert(r.msg);
							}
						}
					});
				});
			}else{
				alert('该信息已审核通过');
			}
		},
		goState:function(){
			var _this=this;
			if(_this.job.jobState =="1" || _this.job.jobState =="2"){
				confirm('确认修改吗',function(){
					_this.job.jobState = '3';
					var url = vm.job.jobId == null ? "../job/save" : "../job/update";
					$.ajax({
						type: "POST",
					    url: url,
					    data: JSON.stringify(vm.job),
					    success: function(r){
					    	if(r.code === 0){
					    		alert('操作成功');
								
							}else{
								alert(r.msg);
							}
						}
					});
				 });
			}else{
				alert("已审核判断为不通过！");
			}
		},
		changeDesc:function(){
			var url = vm.job.jobId == null ? "../job/save" : "../job/update";
			confirm('确认修改吗',function(){
				$.ajax({
					type: "POST",
				    url: url,
				    data: JSON.stringify(vm.job),
				    success: function(r){
				    	if(r.code === 0){
							alert('操作成功');
							
						}else{
							alert(r.msg);
						}
					}
				});
			});
			
		},
		
		selectApply:function(id){
			
			vm.showList1 = false;
			vm.showListApply = true;
			$.get("../job/getInfo",{"jobId":id}, function(r){
                vm.apply = r.apply;
                console.log(vm.apply)
            });
			

			
		},
		
		goback:function(){
			vm.reload();
		},
		query: function () {
			vm.reload();
		},
		
		update: function (event) {
			var jobId = getSelectedRow();
			if(jobId == null){
				return ;
			}
			vm.showList1 = false;
			vm.showList2 = true;
            vm.title = "审批";
            
            vm.getInfo(jobId)
		},
		saveOrUpdate: function (event) {
			var url = vm.job.jobId == null ? "../job/save" : "../job/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.job),
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
			var jobIds = getSelectedRows();
			if(jobIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../job/delete",
				    data: JSON.stringify(jobIds),
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
		getInfo: function(jobId){
			$.get("../job/info/"+jobId, function(r){
                vm.job = r.job;
            });
		},
		reload: function (event) {
			vm.showList1 = true;
			vm.showList2 = false;
			vm.showListApply = false;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});