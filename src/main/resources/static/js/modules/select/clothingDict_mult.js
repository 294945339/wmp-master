$(function () {
    colModel=[
            { label: 'ID', name: 'id', width: 30, key: true,align: "center",hidden:true },
            { label: '名字', name: 'name', width: 60 ,align: "center" },
            { label: '类型名称', name: 'typeName', width: 60 ,align: "center" },
    ]

    $("#jqGrid").jqGrid({
        url: baseURL + 'goods/clothingDict/list',
        datatype: "json",
        colModel: colModel,
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList : [10,30,50],
        rownumbers: false,
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
        ondblClickRow:function(rowid,iRow){
            iRow=iRow-1
            $tr=$("#jqGrid").find(".jqgrow").eq(iRow).find("td")
            moveAdd($tr,colModel)

        },

    });


    $("#jqGrid2").jqGrid({
        datatype: "local",
        colModel: colModel,
        height: 385,
        autowidth:true,
        multiselect: true,
        ondblClickRow:function(rowid,iRow){
            iRow=iRow-1
            $("#jqGrid2").find(".jqgrow").eq(iRow).remove()
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
            vm.options = r.typeList;
        });
    },
    methods: {
        query: function () {
            vm.reload();
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

// 穿梭选中右移
function moveR(){
    for(var i=0;i<$("#jqGrid").find(".cbox:checked").length;i++){
        $tr=$("#jqGrid").find(".cbox:checked").eq(i).parent().parent().find("td")
        moveAdd($tr,colModel)
    }
}
// 穿梭全部右移
function moveRall(){
    for(var i=0;i<$("#jqGrid .jqgrow").length;i++){
        $tr=$("#jqGrid .jqgrow").eq(i).find("td")
        moveAdd($tr,colModel)
    }
}
// 穿梭选中左移
function moveL(){
    $("#jqGrid2").find(".cbox:checked").parent().parent().remove()
}

// 穿梭全部左移
function moveLall(){
    $("#jqGrid2 .jqgrow").remove()
}

// 穿梭调用函数
function moveAdd($tr,arr){
    obj={}
    arr.forEach(function(item,index){
        obj[item.name]=$tr.eq(index+1).html()
    })
    
    isExist=1
    for(var j=0;j<$("#jqGrid2 .jqgrow").length;j++){
        if(obj.id==$("#jqGrid2 .jqgrow").eq(j).find("td").eq(1).html()){
            isExist=0
            break
        }
    }
    
    if(isExist){
        $("#jqGrid2").jqGrid('addRowData', obj.id, obj);
    }
}
function select_qd(){
    returnAll={code:0,data:[]}

    for(var i=0;i<$("#jqGrid2 .jqgrow").length;i++){
        obj={}
        colModel.forEach(function(item,index){
            obj[item.name]=$("#jqGrid2 .jqgrow").eq(i).find("td").eq(index+1).html()
        })
        returnAll.data.push(obj);
    }

    window.parent.return_clothingDict_mult(returnAll);
}
function select_qx(){
    returnAll={code:0,data:[]}
    window.parent.return_clothingDict_mult(returnAll);
}
