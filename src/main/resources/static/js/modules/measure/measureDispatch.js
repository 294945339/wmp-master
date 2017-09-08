$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'measure/measureDispatch/list',
        datatype: "json",
        colModel: [
            // { label: 'ID', name: 'id', width: 30, key: true,align: "center" },
            { label: '派遣单号', name: 'code', width: 60 ,align: "center" },
            { label: '量体单位', name: 'company.name', width: 60 ,align: "center" },
            { label: '量体方式', name: 'type', width: 60 ,align: "center" },
            { label: '量体通知单号', name: 'order.originalNo', width: 60 ,align: "center" },
            { label: '任务状态', name: 'taskStatus', width: 60 ,align: "center",formatter: function(value, options, row){
                    if(value=='Other'){
                        str='<span class="label label-primary">历史任务</span>'
                    }else if(value=='Do'){
                        str='<span class="label label-danger">开始</span>'
                    }else if(value=='Doing'){
                        str='<span class="label label-info">进行中</span>'
                    }else{
                        str='<span class="label label-success">完成</span>'
                    }
                    return str
                }
            },
            { label: '量体时间', name: 'taskTime', width: 60 ,align: "center" ,formatter: function(value, options, row){
                return value.substr(0,10)
                }
            },
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        multiselect: false,
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
    el:'#djapp',
    data:{
        showList: true,
        title: null,
        clothingDict:{
            id:null,
            name:null,
            sex:null,
            type:''
        },
        q:{
            code:'',
            companyName:'',
            taskStatus:'',
            startTime:'',
            endTime:''
        },
        taskStatus:[{value:'',text:'全部'},{value:'Do',text:'开始'},{value:'Doing',text:'进行'},{value:'Done',text:'完成'},{value:'Other',text:'历史任务'}],
        options: [],
    },
    mounted: function() {
        
    },
    methods: {
        query: function () {
            vm.reload();
        },
        reload: function () {
            vm.showList = true;
            if($("#startTime").val()!=''){
                vm.q.startTime=$("#startTime").val()+" 00:00:00.000"
            }
            if($("#endTime").val()!=''){
                vm.q.endTime=$("#endTime").val()+" 00:00:00.000"
            }
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'code':vm.q.code,'companyName':vm.q.companyName,'taskStatus':vm.q.taskStatus,'startTime':vm.q.startTime,'endTime':vm.q.endTime},
                page:page
            }).trigger("reloadGrid");
        }
    }
});