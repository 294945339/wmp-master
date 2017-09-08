var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url:"nourl"
        }
    }
};
var ztree;

var vm = new Vue({
    el:'#rrapp',
    data:{
        showList: true,
        title: null,
        dept:{
            parentName:null,
            parentId:0,
            orderNum:0
        },
        xdept:[],
        fid:'',
    },
    mounted: function() {
       this._getList()
    },
    methods: {
        _getList:function(){
            $.get(baseURL + "sys/dept/info", function(d){
                vm.fid=d.deptId

                $.get(baseURL + "sys/dept/list", function(r){
                    var xdept=[]
                    function deptL(pid,data,i){
                        i=i+1
                        data.forEach(function(item,index){  
                            if(item.parentId==pid){
                                xdept.push(Object.assign(item,{i:i,jt:0}))
                                deptL(item.id,data,i)
                            }
                        
                        })
                    }
                    deptL(vm.fid,r,0)
                    xdept.forEach(function(item,index){
                        xdept.forEach(function(item2){
                            if(item.id==item2.parentId){
                                xdept[index].jt=1
                            }
                        })
                    })
                    vm.xdept=xdept
                })
            })
            
            
        },
        getDept: function(){
            //加载部门树
            $.get(baseURL + "sys/dept/select", function(r){
                ztree = $.fn.zTree.init($("#deptTree"), setting, r.deptList);
                var node = ztree.getNodeByParam("id", vm.dept.parentId);
                ztree.selectNode(node);

                vm.dept.parentName = node.name;
            })
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.dept = {parentName:null,parentId:0,orderNum:0};
            vm.getDept();
        },
        update: function () {
            var id = getId();
            if(id == null){
                return ;
            }

            $.get(baseURL + "sys/dept/info/"+id, function(r){
                vm.showList = false;
                vm.title = "修改";
                vm.dept = r.dept;

                vm.getDept();
            });
        },
        del: function () {
            var id = getId();
            if(id == null){
                return ;
            }

            confirm('确定要删除选中的记录？', function(){
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/dept/delete",
                    data: "id=" + id,
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
            });
        },
        saveOrUpdate: function (event) {
            var url = vm.dept.id == null ? "sys/dept/save" : "sys/dept/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.dept),
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
        deptTree: function(){
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择部门",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#deptLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    //选择上级部门
                    vm.dept.parentId = node[0].id;
                    vm.dept.parentName = node[0].name;

                    layer.close(index);
                }
            });
        },
        reload: function () {
            vm.showList = true;
            this._getList()
        }
    }
});


function getId () {
    selected=$(".deptselect:checked")
    if (selected.length == 0) {
        alert("请选择一条记录");
        return false;
    } else {
        return selected.val();
    }
}

//箭头点击
function deptshow(a){
    $pdom=$(".treegrid_"+$(a).parent().parent().find("input").val())
    if($pdom.is(":hidden")){
        $pdom.css("display","table");
        //子显示
        dgShow($pdom)
        $(a).parent().parent().find(".treegrid-expander").removeClass("glyphicon-chevron-right").addClass("glyphicon-chevron-down")
    }else{
        $pdom.css("display","none")
        //子隐藏
        dgHide($pdom)
        $(a).parent().parent().find(".treegrid-expander").removeClass("glyphicon-chevron-down").addClass("glyphicon-chevron-right")
    }
}

//递归显示
function dgShow($pdom){
    // glyphicon-chevron-down
    for(var i=0;i<$pdom.length;i++){
        if($pdom.eq(i).find(".glyphicon-chevron-down").length){
            //子显示
            deptshow($pdom.eq(i).find(".glyphicon-chevron-down"))
        }
    }
}
//递归隐藏
function dgHide($pdom){
    for(var i=0;i<$pdom.length;i++){
        $dd=$(".treegrid_"+$pdom.eq(i).find("input").val())
        $dd.css("display","none")
        dgHide($dd)
    }
}

//行点击
function deptselect(a){
    $(".treegrid-tbody tr").removeClass("treegrid-selected")
    $(a).addClass("treegrid-selected")
    $(a).find(".deptselect").prop("checked",true)
}
