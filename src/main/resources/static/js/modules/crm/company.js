$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'crm/company/list',
        datatype: "json",
        colModel: [
            // { label: 'ID', name: 'id', width: 30, key: true,align: "center" },
            { label: '公司名称', name: 'name', width: 60 ,align: "left" },
            { label: '公司编码', name: 'code', width: 60 ,align: "left" },
            { label: '公司地址', name: 'address', width: 60 ,align: "left" },
            { label: '公司电话', name: 'phone', width: 60 ,align: "left" },
            { label: '业务员', name: 'salesmanName', width: 60 ,align: "center" },
            { label: '业务员联系号码', name: 'salesmanPhone', width: 60 ,align: "center" },
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
            typeName:'',
            name:''
        },
        options: [],
    },
    mounted: function() {
        // $.get(baseURL + "goods/goodsCatalog/nameList", function(r){
        //     console.dir(r);
        //     vm.options = r.typeList;
        // });
    },
    methods: {
        query: function () {
            vm.reload();
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'name':vm.q.name,'salesmanName':vm.q.salesmanName},
                page:page
            }).trigger("reloadGrid");
        }
    }
});