$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'measure/clothingHabit/list',
        datatype: "json",
        colModel: [
            { label: 'ID', name: 'id', width: 30, key: true,align: "center" },
            { label: '商品类别', name: 'name', width: 60 ,align: "center"},
            { label: '部位', name: 'bodyValues', width: 80 ,align: "left",formatter: function(value, options, row){
                str=''
                value.forEach(function(item,index){
                    if(item.clothingDict.sex=='neutral'){
                        str+='<span class="label label-danger">'+item.clothingDict.name+'</span> '
                    }else if(item.clothingDict.sex=='man'){
                        str+='<span class="label label-success">'+item.clothingDict.name+'</span> '
                    }else{
                        str+='<span class="label label-info">'+item.clothingDict.name+'</span> '
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
        clothingHabit:{id:null,name:'',bodyValues:[] },
        q:{
            name:'',
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
            vm.clothingHabit = {id:null,name:'',bodyValues:[] }
        },
        update: function () {
            var id = getSelectedRow();
            if(id == null){
                return ;
            }
            $.get(baseURL + "measure/clothingHabit/info/"+id, function(r){
                vm.showList = false;
                vm.title = "修改";
                vm.clothingHabit = r.clothingDict;
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
                    url: baseURL + "measure/clothingHabit/delete",
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
        //部位选择
        select_clothing:function(){
            d_pop("/modules/select/clothingDict_mult.html",830,600);
        },
        // xs:function(){
        //     console.log(this.clothingHabit.bodyValues)
        // },
        removeClothingDicts:function(index){
            this.clothingHabit.bodyValues.splice(index,1)
        },
        changeClothingDict:function(index){
            this.clothingHabit.bodyValues[index].numericalValue=$(".clothingHabit").eq(index).val()
        },
        saveOrUpdate: function () {
            var url = "measure/clothingHabit/save"

            if(vm.clothingHabit.name==''){
                alert('名称不能为空')
                return false;
            }

            if(vm.clothingHabit.bodyValues.length==0){
                alert('您必须选择部位')
                return false;
            }
            // var bodyValues2=[]

            vm.clothingHabit.bodyValues.forEach(function(item,i){
                // if(item.numericalValue==''){
                //     alert('数值不能为空')
                //     // $(".clothingHabit").eq(i).focus()
                //     return false
                // }
                // bodyValues2.push({clothingDict:item.clothingDict,numericalValue:item.numericalValue})
            })
            
            // arr={id:vm.clothingHabit.id,name:vm.clothingHabit.name,bodyValues:bodyValues2}
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType:'application/json;charset=utf-8',
                data:JSON.stringify(vm.clothingHabit),
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
                postData:{'name': vm.q.name},
                page:page
            }).trigger("reloadGrid");
        }
    }
});

function return_clothingDict_mult(obj){
    $(".d_pop_class").remove()
    if(obj.code==0){
        obj.data.forEach(function(item,index){
            r=0;
            vm.clothingHabit.bodyValues.forEach(function(i,j){
                if(item.id==i.clothingDict.id){
                    r=1;
                }
            })

            if(!r){
                arr={clothingDict:{id:item.id,name:item.name},name:item.name,numericalValue:0}
                vm.clothingHabit.bodyValues.push(arr)
            }
        })
    }
}
// d_pop("/modules/select/goodsType.html",500,800)