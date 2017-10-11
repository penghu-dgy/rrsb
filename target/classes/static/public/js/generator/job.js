$(function () {
    $("#jqGrid").jqGrid({
        url: '../job/list',
        datatype: "json",
        colModel: [			
			{ label: '职位ID', name: 'jobId', index: '$job_id', width: 50, key: true },
			{ label: '职位名称', name: 'jobTitle', index: '$job_title', width: 80 }, 			
			{ label: '需求人数', name: 'jobCount', index: '$job_count', width: 80 }, 			
			 			
						
			{ label: '市', name: 'city', index: '$city', width: 80 }, 			
			{ label: '区', name: 'area', index: '$area', width: 80 }, 			
			{ label: '发布时间', name: 'jobPubTime', index: '$job_pub_time', width: 80 }, 			
			 			
			 			
			 			
			{ label: '发布人姓名', name: 'pubName', index: '$pub_name', width: 80 }, 			
			{ label: '发布人电话', name: 'pubPhone', index: '$pub_phone', width: 80 }, 			
			{ label: '公司', name: 'company', index: '$company', width: 80 }, 			
			{ label: '当前状态', name: 'jobState', index: '$job_state', width: 80 }
					
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
		job: {}
	},
	methods: {
		changeState:function(){
			var _this=this;
			if(_this.job.jobState != '已通过'){
				confirm('确认修改吗',function(){
					
					//_this.job.jobState = '2';
					new_job=$.extend({},_this.job)

					//var new_job = _this.job;
					_this.job.jobState = '已通过';
					console.log("changeState="+new_job.jobState);
					console.log("changeState2="+JSON.stringify(new_job));
					//_this.job.jobState = '已通过';
					new_job.jobState='2'
					var url = new_job.jobId == null ? "../job/save" : "../job/update";
					$.ajax({
						type: "POST",
					    url: url,
					    data: JSON.stringify(new_job),
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
			if(_this.job.jobState =="申请中" || _this.job.jobState =="已通过"){
				confirm('确认修改吗',function(){
					//_this.job.jobState = '3';
					new_job=$.extend({},_this.job)
					_this.job.jobState = '未通过';
					console.log("state go state"+new_job.jobState);
					//_this.job.jobState = '未通过';
					new_job.jobState='3';
					var url = new_job.jobId == null ? "../job/save" : "../job/update";
					$.ajax({
						type: "POST",
					    url: url,
					    data: JSON.stringify(new_job),
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
		goback:function(){
			vm.reload();
		},
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.job = {};
		},
		update: function (event) {
			var jobId = getSelectedRow();
			if(jobId == null){
				return ;
			}
			vm.showList = false;
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
				if(r.job.jobState=="1"){
					r.job.jobState='申请中';
				}else if(r.job.jobState=="2"){
					r.job.jobState='已通过';
				}else if(r.job.jobState=="3"){
					r.job.jobState='未通过';
				}else if(r.job.jobState=="4"){
					r.job.jobState='已过期';
				}else{
					
				}
                vm.job = r.job;
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