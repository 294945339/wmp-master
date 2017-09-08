$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'goods/goodsCatalog/list',
        datatype: "json",
        colModel: [
            // { label: 'ID', name: 'id', width: 30, key: true,align: "center" },
            { label: '商品代码', name: 'code', width: 60 ,align: "center" },
            { label: '商品名称', name: 'name', width: 60 ,align: "center" },
            { label: '商品单位', name: 'unit', width: 60 ,align: "center" },
            { label: '大类', name: 'type', width: 60 ,align: "center" },
            { label: '大类名称', name: 'typeName', width: 60 ,align: "center" },
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
                postData:{'code':vm.q.code,'name':vm.q.name},
                page:page
            }).trigger("reloadGrid");
        }
    }
});