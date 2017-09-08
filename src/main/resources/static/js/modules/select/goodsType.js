$(function () {

    colModel=[
        { label: 'ID', name: 'id', width: 60 ,align: "center" ,hidden:true},
        { label: '商品种类', name: 'crmName',align: "center" },
        { label: '单位', name: 'unit',align: "center" },
        { label: '类型', name: 'typeStr',align: "center" },
    ]

    $("#jqGrid").jqGrid({
        url: baseURL + 'goods/goodsType/list',
        datatype: "json",
        colModel: colModel,
        viewrecords: true,
        height: 385,
        width:450,
        rowNum: 10,
        rowList : [10,30,50],
        rownumbers: false,
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
       
        ondblClickRow:function(rowid,iRow){
            select_qd()
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

function select_qd(){
    returnAll={code:0,data:[]}

    obj={}
    colModel.forEach(function(item,index){
        obj[item.name]=$("#jqGrid .success").find("td").eq(index).html()
    })
    returnAll.data.push(obj);
    if(returnAll.data.length==0){
        select_qx()
    }else{
        window.parent.return_goodsType(returnAll);
    }
}

function select_qx(){
    returnAll={code:1,data:[]}
    window.parent.return_goodsType(returnAll);
}
