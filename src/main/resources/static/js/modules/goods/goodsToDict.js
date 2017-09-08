$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'goods/goodsToDict/list',
        datatype: "json",
        colModel: [
            { label: 'ID', name: 'id', width: 30, key: true,align: "center" },
            { label: '商品类别', name: 'goodsType.crmName', width: 60 ,align: "center"},
            { label: '部位', name: 'clothingDicts', width: 80 ,align: "left",formatter: function(value, options, row){
                str=''
                value.forEach(function(item,index){
                    if(item.sex=='neutral'){
                        str+='<span class="label label-danger">'+item.name+'</span> '
                    }else if(item.sex=='man'){
                        str+='<span class="label label-success">'+item.name+'</span> '
                    }else{
                        str+='<span class="label label-info">'+item.name+'</span> '
                    }
                    
                })
                return str
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
        goodsToDict:{
            goodsType:{
                id:null,
                crmName:''
            },
            clothingDictIds:[],
            clothingDicts:[]
        },
        q:{
            crmName:'',
        },
        options: [],
    },
   
    methods: {
        query: function () {
            vm.reload();
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.goodsToDict = {
                goodsType:{
                    id:null,
                    crmName:''
                },
                clothingDictIds:[],
                clothingDicts:[]
            }
        },
        update: function () {
            var id = getSelectedRow();
            if(id == null){
                return ;
            }
            $.get(baseURL + "goods/goodsToDict/info/"+id, function(r){
                vm.showList = false;
                vm.title = "修改";
                vm.goodsToDict = r.goodsToDict;
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
                    url: baseURL + "goods/goodsToDict/delete",
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
        //商品类别选择
        select_type:function(){
            d_pop("/modules/select/goodsType.html",600,600);
        },
        //部位选择
        select_clothing:function(){
            d_pop("/modules/select/clothingDict_mult.html",830,600);
        },
        removeClothingDicts:function(index){
            console.log(index)
            this.goodsToDict.clothingDictIds.splice(index,1)
            this.goodsToDict.clothingDicts.splice(index,1)
        },
        saveOrUpdate: function () {
            var url = "goods/goodsToDict/save"
            if(vm.goodsToDict.goodsType.id==null){
                alert('您必须选择商品类别')
                return false;
            }
            if(vm.goodsToDict.clothingDictIds.length==0){
                alert('您必须选择部位')
                return false;
            }
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType:'application/json;charset=utf-8',
                data:JSON.stringify({goodsType:{id:vm.goodsToDict.goodsType.id},clothingDictIds:vm.goodsToDict.clothingDictIds,id:vm.goodsToDict.id}),
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
                postData:{'goodsType.crmName': vm.q.crmName},
                page:page
            }).trigger("reloadGrid");
        }
    }
});


        
function return_goodsType(obj){
    $(".d_pop_class").remove()
    if(obj.code==0){
        vm.goodsToDict.goodsType.id=obj.data[0].id
        vm.goodsToDict.goodsType.crmName=obj.data[0].crmName
    }
}
function return_clothingDict_mult(obj){
    $(".d_pop_class").remove()
    if(obj.code==0){
        obj.data.forEach(function(item,index){
            r=0;
            vm.goodsToDict.clothingDicts.forEach(function(i,j){
                if(item.id==i.id){
                    r=1;
                }
            })

            if(!r){
                arr={id:item.id,name:item.name}
                vm.goodsToDict.clothingDictIds.push(item.id)
                vm.goodsToDict.clothingDicts.push(arr)
            }
        })
    }
}
// d_pop("/modules/select/goodsType.html",500,800)