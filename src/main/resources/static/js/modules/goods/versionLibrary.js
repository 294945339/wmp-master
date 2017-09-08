$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'goods/versionLibrary/list',
        datatype: "json",
        colModel: [
            { label: 'ID', name: 'id', width: 30, key: true,align: "center",hidden:true },
            { label: '版型编码', name: 'code', width: 60 ,align: "center" },
            { label: '原版型编号', name: 'oldCode', width: 60 ,align: "center" },
            { label: '规格表名称', name: 'specificationSheet.name', width: 60 ,align: "center" },
            { label: '类别', name: 'type', width: 60 ,align: "center" },
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
        },
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
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'code':vm.q.code},
                page:page
            }).trigger("reloadGrid");
        }
    }
});