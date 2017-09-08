$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'goods/clothingDict/list',
        datatype: "json",
        colModel: [
            { label: 'ID', name: 'id', width: 30, key: true,align: "center" },
            { label: '名字', name: 'name', width: 60 ,align: "center" },
            { label: '代码', name: 'code', width: 60 ,align: "center" },
            { label: '类型', name: 'type', width: 60 ,align: "center" },
            { label: '类型名称', name: 'typeName', width: 60 ,align: "center" },
            { label: '性别', name: 'sex', width: 80 ,align: "center" ,formatter: function(value, options, row){
                if("man" == value){
                    return  '<span class="label label-success">男</span>';
                }else if("woman" == value){
                    return  '<span class="label label-info">女</span>';
                }else {
                    return '<span class="label label-danger">通用</span>'
                }
            }},
            { label: '优先级', name: 'priority', width: 80 ,align: "center" },
            { label: '排序号', name: 'sort', width: 80 ,align: "center" },
            { label: '能否修改', name: 'canUpdate', width: 80,align: "center" ,formatter: function(value, options, row){
                return value === true ?
                    '<span class="label label-danger">能</span>' :
                    '<span class="label label-success">不能</span>';
            }}
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
        $.get(baseURL + "goods/clothingDict/typeList", function(r){
            console.dir(r);
            vm.options = r.typeList;
        });
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.clothingDict = {sex:'neutral',priority:"0",sort:"0",canUpdate:true};
        },
        update: function () {
            var id = getSelectedRow();
            if(id == null){
                return ;
            }
            $.get(baseURL + "goods/clothingDict/info/"+id, function(r){
                vm.showList = false;
                vm.title = "修改";
                vm.clothingDict = r.clothingDict;
            });
        },
        del: function (event) {
            var ids = getSelectedRows();
            if(ids == null){
                return ;
            }

            confirm('确定要删除选中的记录？', function(){
                $.ajax({
                    type: "POST",
                    url: baseURL + "goods/clothingDict/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function(r){
                        if(r.code == 0){
                            alert('操作成功', function(){
                                vm.reload();
                            });
                        }else{
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        saveOrUpdate: function () {
            var url = vm.clothingDict.id == null ? "goods/clothingDict/save" : "goods/clothingDict/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType:'application/json;charset=utf-8',
                data:JSON.stringify(vm.clothingDict),
                success: function(r){
                    if(r.code === 0){
                        alert('操作成功', function(){
                            vm.reload();
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'id': vm.id,'name':vm.q.name,'typeName':vm.q.typeName},
                page:page
            }).trigger("reloadGrid");
        }
    }
});