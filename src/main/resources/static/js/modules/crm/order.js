$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'crm/order/list',
        datatype: "json",
        colModel: [
            { label: 'ID', name: 'id', width: 30, key: true,align: "center",hidden:true,frozen:true },
            { label: '订单通知编号', name: 'noticeNo', width:150, align: "center",frozen:true },
            { label: '量体通知单号', name: 'originalNo',width:150, align: "center" },
            { label: '类型', name: 'type', width:60,align: "center" },
            { label: '申请时间', name: 'applyTime', width:100,align: "center",formatter: function(value, options, row){
                return value.substr(0,value.length-2)
            }},
            { label: '业务员', name: 'salesmanName',width: 80 , align: "center" },
            { label: '业务员联系号码', name: 'salesmanPhone',width: 110 , align: "center" },
            { label: '联系人', name: 'contactsName',width: 80 , align: "center" },
            { label: '联系人电话', name: 'contactsPhone',width: 110 , align: "center" },

          

            { label: '量体时间', name: 'measureTime', width: 100 ,align: "center" ,formatter: function(value, options, row){
                return value.substr(0,10)
            }},
            { label: '量体单位', name: 'company.name', width: 100 ,align: "center" },
            { label: '交付时间', name: 'deliverTime', width: 100 ,align: "center" ,formatter: function(value, options, row){
                return value.substr(0,10)
            }},
            { label: '男人数', name: 'manNum', width: 70 ,align: "center" },
            { label: '女人数', name: 'womanNum', width: 70 ,align: "center" },
            { label: '男人配置', name: 'manConfig', width: 100, align: "center" },
            { label: '女人配置', name: 'womanConfig',width: 100, align: "center" },
            { label: '量体方式', name: 'measureType',width: 100, align: "center" },
            { label: '装箱要求', name: 'packingType',width: 100, align: "center" },
            { label: '打包要求', name: 'packType',width: 100, align: "center" }
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 25,
        shrinkToFit:false,
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
            // $("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });
    $("#jqGrid").jqGrid('setFrozenColumns');
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
            noticeNo:'',
        },
        options: [],
    },
    mounted: function() {
        
    },
    methods: {
        query: function () {
            vm.reload();
        },
        update: function (id) {
            vm.tdfield=[]
            $.get(baseURL + "crm/order/list"+id, function(r){
                
                vm.specificationSheet = r.specificationSheet;
                vm.showList = false;
                vm.title = "详细";
                vm.tdfield=vm.specificationSheet.specificationSheetRelations[0].bodyValues
            },'json');
        },
        
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'noticeNo':vm.q.noticeNo},
                page:page
            }).trigger("reloadGrid");
        }
    }
});