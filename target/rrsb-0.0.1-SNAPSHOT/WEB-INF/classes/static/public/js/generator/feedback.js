$(function () {
    $("#jqGrid").jqGrid({
        url: '../feedback/list',
        datatype: "json",
        colModel: [			
			{ label: '编号', name: 'feedbackid', index: 'feedbackId', width: 50, key: true },
			//{ label: '', name: 'empid', index: 'empId', width: 80 }, 			
			//{ label: '', name: 'fbtype', index: 'fbType', width: 80 }, 			
			{ label: '描述', name: 'fbdesc', index: 'fbDesc', width: 80 }, 			
			{ label: '内容', name: 'fbcontent', index: 'fbContent', width: 80 }, 			
			{  
                name: '操作', index: '', width: 120, fixed: true, sortable: false, resize: false,  
                //formatter:'actions',  
                formatter: function (value, grid, rows, state) { return "<button href=\"#\" class=\" btn btn-primary\" onclick=\"vm.a(" + rows.feedbackid + ")\">查看详情</button>" }  
                //formatoptions:{  
                //  keys:true,  
                //delbutton: false,//disable delete button  

                //  delOptions:{recreateForm: true, beforeShowForm:beforeDeleteCallback},  
                //editformbutton:true, editOptions:{recreateForm: true, beforeShowForm:beforeEditCallback}  
                //}  
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
		showList: true,
		title: null,
		feedback: {},
		imgArr:[],
		showimg:false,
		bb:''
	},
	methods: {
		query: function () {
			vm.reload();
		},
		a:function(feedbackid){
			
			if(feedbackid == null){
				return ;
			}
			vm.showList = false;
            vm.title = "查看";
			vm.getInfo(feedbackid)
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.feedback = {};
		},
		update: function (event) {
			var feedbackid = getSelectedRow();
			if(feedbackid == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(feedbackid)
		},
		saveOrUpdate: function (event) {
			var url = vm.feedback.feedbackid == null ? "../feedback/save" : "../feedback/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.feedback),
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
			var feedbackids = getSelectedRows();
			if(feedbackids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../feedback/delete",
				    data: JSON.stringify(feedbackids),
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
		getInfo: function(feedbackid){
			var _this=this
			$.get("../feedback/info/"+feedbackid, function(r){
                vm.feedback = r.feedback;
               // console.log(r)
                var a=_this.escape2Html(vm.feedback.imgs)
                vm.imgArr=JSON.parse(a)
			});
			
		},
		 escape2Html: function(str) {
			 var arrEntities={'lt':'<','gt':'>','nbsp':' ','amp':'&','quot':'"'};
			 return str.replace(/&(lt|gt|nbsp|amp|quot);/ig,function(all,t){return arrEntities[t];});
			},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		},
		shows:function(a){
			var _this=this
			this.bb=a
			_this.showimg=!_this.showimg
			
		},
		showw:function(){
			this.showimg=!this.showimg
			
		}
	}
});