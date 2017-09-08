$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'goods/specificationSheet/list',
        datatype: "json",
        colModel: [
            { label: 'ID', name: 'id', width: 30, key: true,align: "center",hidden:true },
            { label: '规格表名称', name: 'name', width: 60 ,align: "center",formatter: function(value, options, row){
                    return '<a onclick="vm.update('+row.id+')">'+value+'</a>'
                }
            },
            { label: '规格表代码', name: 'code', width: 60 ,align: "center" },
            { label: '性别', name: 'sex', width: 60 ,align: "center",formatter: function(value, options, row){
          
                    if(value=='neutral'){
                        str='<span class="label label-danger">通用</span> '
                    }else if(value=='man'){
                        str='<span class="label label-success">男</span> '
                    }else{
                        str='<span class="label label-info">女</span> '
                    }
                    return str
                }
            },
            { label: '类别', name: 'type', width: 60 ,align: "center" },
            { label: '是否使用', name: 'canUse', width: 60 ,align: "center" ,formatter: function(value, options, row){
                    if(value=='true'){
                        str='<span class="label label-danger">是</span> '
                    }else{
                        str='<span class="label label-info">否</span> '
                    }
                    return str
                }
            },
            { label: '文件编码', name: 'fileCode', width: 60 ,align: "center"}
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
        postData:{
            'searchAll':'false'
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
        specificationSheet:{
            id:null,
           
        },
        tdfield:[],
        q:{
            name:'',
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
            $.get(baseURL + "goods/specificationSheet/info/"+id, function(r){
                
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
                postData:{'name':vm.q.name},
                page:page
            }).trigger("reloadGrid");
        }
    }
});