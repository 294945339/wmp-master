$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'goods/goodsType/list',
        datatype: "json",
        colModel: [
            // { label: 'ID', name: 'id', width: 30, key: true,align: "center" },
            { label: '序号', name: 'code', width: 60 ,align: "center" },
            { label: '商品种类', name: 'crmName', width: 60 ,align: "center" },
            { label: '单位', name: 'unit', width: 60 ,align: "center" },
            { label: '类型', name: 'typeStr', width: 60 ,align: "center" },
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
        
    },
    methods: {
        query: function () {
            vm.reload();
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'crmName':vm.q.crmName},
                page:page
            }).trigger("reloadGrid");
        }
    }
});