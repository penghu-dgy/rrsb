$(function () {
    $("#jqGrid").jqGrid({
        url: '../notice/list1',
        datatype: "json",
        colModel: [			
			{ label: '编号', name: 'noticeid', index: 'noticeId', width: 50, key: true },
			{ label: '标题', name: 'noticetitle', index: 'noticeTitle', width: 80 }, 			
			{ label: '内容', name: 'noticecontent', index: 'noticeContent', width: 80 }, 			
			/*{ label: '地址', name: 'noticeaddress', index: 'noticeAddress', width: 80 }, 	*/		
			{ label: '添加时间', name: 'noticetime', index: 'noticeTime', width: 80 }, 			
			/*{ label: '发布人编号', name: 'noticeemp', index: 'noticeEmp', width: 80 },*/ 			
			{ label: '发布人', name: 'noticeempname', index: 'noticeEmpName', width: 80 }, 			
			{ label: '企业编号', name: 'noticeentid', index: 'noticeEntId', width: 80 }, 			
			/*{ label: '', name: 'bz1', index: 'bz1', width: 80 }, 			
			{ label: '', name: 'bz2', index: 'bz2', width: 80 }, 			
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
		notice: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.notice = {};
		},
		update: function (event) {
			var noticeid = getSelectedRow();
			if(noticeid == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            //alert("noticeid penghu="+noticeid);
            //console.log("noticeid penghu="+noticeid);
            vm.getInfo(noticeid)
		},
		saveOrUpdate: function (event) {
			var url = vm.notice.noticeid == null ? "../notice/save" : "../notice/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.notice),
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
			var noticeids = getSelectedRows();
			if(noticeids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../notice/delete",
				    data: JSON.stringify(noticeids),
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
		getInfo: function(noticeid){
			 //console.log("noticeid="+noticeid);
			//alert("noticeid="+noticeid);
			$.get("../notice/info/"+noticeid, function(r){
				//console.log("test"+r.notice.noticeid);
                vm.notice = r.notice;
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