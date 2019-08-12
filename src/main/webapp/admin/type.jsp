<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css" />
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
    <script type="text/javascript">
        $(function(){
            //使用datagrid绑定数据展示
            $('#dg').datagrid({
                url:'/type/getType',
                fitColumns: true,
                pagination: true,
                pageList: [5, 10, 15, 20],
                toolbar:"#ToolBar",
                //singleSelect:true,  //只能设置单选
                pageSize:5,
                columns: [[
                    {field:'ck',checkbox:true},  //复选框列
                    { field: 'id', title: '编号', width: 50, align: "center" },
                    { field: 'name', title: '房型', width: 50, align: "center" },
                    { field: 'function', title: '操作', width: 50, align: "center",
                        formatter: function(value,row,index){
                            return  "<a href='javascript:DeleteById("+row.id+")'> 删除</a>"
                        }
                    }
                ]]

            });
        });

        //打开添加窗口
        function Add() {
            // alert("打开窗口");
            $("#AddDialog").dialog("open").dialog('setTitle',"添加区域");
        }

        //关闭添加窗口
        function CloseDialog(obj){
            $("#"+obj).dialog("close");
        }

        //修改数据
        function ModifyBySelect() {
            //判断有没有选择修改的记录
            //获取所有的选中杭返回数据，如果没有则返回空
            var selectRows=$("#dg").datagrid('getSelections');//获得选中的数组
            if (selectRows.length!=1) {
                $.messager.alert('提示框','您没有选中行，或者选中了多行！请检查！');
                return;
            }
            var selectRow=selectRows[0];
            $("#updateDistrict").dialog("open").dialog('setTitle',"修改区域");
            $("#upDistrictForm").form('load',selectRow);
        }
    //保存提交按钮
     function SaveDialog() {
         //跳转到后台实现保存
         $('#saveDialogForm').form('submit',{
             url:"/admin/addType", //提交的服务器地址
             success:function(data){//成功的回调函数
                 //data接收服务器返回的json字符串（不是json对象）
                 var obj=$.parseJSON(data);
                 if (obj.result>0) {
                     $("#AddDialog").dialog("close");  //关闭
                     $.messager.alert('提示框','恭喜添加成功!');
                     refresh();
                 }else{
                     $.messager.alert('提示框','系统正在维护!');
                 }
             }
         });
     }

     //修改提交按钮
       function SaveDialog2() {
           $('#upDistrictForm').form('submit',{
               url:"/type/updateType",//提交服务器地址
               success:function (data) {//成功的回调函数
                   //data接收服务器返回的json字符串（不是json对象）
                   var obj=$.parseJSON(data);
                   if (obj.result>0) {
                       $("#updateDistrict").dialog("close");  //关闭
                       $.messager.alert('提示框','恭喜修改成功!');
                       refresh();
                   }else{
                       $.messager.alert('提示框','系统正在维护!');
                   }
               }
           })
       }
       //点击批量删除跳出模态框
    function DeleteBatch() {
        //判断有没有选择修改的记录
        //获取所有的选中杭返回数据，如果没有则返回空
        var selectRows=$("#dg").datagrid('getSelections');//获得选中的数组
        if (selectRows.length==0) {
            $.messager.alert('提示框','您没有选中行请选中后进行操作！');
            return;
        }
        var delNum="";
        for (var i = 0; i <selectRows.length ; i++) {
            delNum=delNum+selectRows[i].id+","
        }
        //截取字符串数组的范围
        delNum=delNum.substring(0,delNum.length-1);
        //发送异步请求
        $.post("/type/delBatch",
            {"ids":delNum},
            function (data) {
            if (data.result>0) {
                $.messager.alert('提示框','恭喜你成功删除'+data.result+'行!');
                refresh();
            }
            else
            {
                $.messager.alert('提示框','系统正在维护!');
            }
        },"json");
    }


    function DeleteById(id){
        $.messager.confirm('提示框','确认删除吗?',function(r){
            if (r){
                $.post("/type/deleteType",
                    {"id":id},
                    function (data) {
                        if (data.result>0) {
                            $.messager.alert('提示框','恭喜删除成功!');
                            refresh();
                        }else{
                            $.messager.alert('提示框','系统正在维护!');
                        }
                },"json")
            }
        });
    }
    function refresh() {
            window.location.reload();//强制刷新
    }
    </script>
</head>
<body>
<table id="dg"></table>
<!--定义工具栏-->
<div id="ToolBar">
    <div style="height: 40px;">
        <a href="javascript:Add()" class="easyui-linkbutton"
           iconCls="icon-add" plain="true">添加</a>
        <a
                href="javascript:ModifyBySelect()" class="easyui-linkbutton"
                iconCls="icon-edit" plain="true">修改</a>
        <a
                href="javascript:DeleteBatch()" class="easyui-linkbutton"
                iconCls="icon-remove" plain="true">删除</a>
    </div>
</div>
<!--添加窗口-->
<div id="AddDialog" class="easyui-dialog" buttons="#AddDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form id="saveDialogForm" method="post">
        <table>
            <tr>
                <td>区域名称:</td>
                <td><input type="text" name="name"  /></td>
            </tr>
        </table>
    </form>
</div>

<!--给添加对话框添加按钮-->
<div id="AddDialogButtons">
    <a href="javascript:SaveDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a>
    <a href="javascript:CloseDialog('AddDialog')"
       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>


<!--修改对话框-->
<div id="updateDistrict" class="easyui-dialog" buttons="#upDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form id="upDistrictForm" method="post">
        <table>
            <tr>
                <input type="hidden" name="id">
                <td>区域名称:</td>
                <td><input type="text" name="name" id="author" /></td>
            </tr>
        </table>
    </form>
</div>
<!--给修改对话框添加修改按钮-->
<div id="upDialogButtons">
    <a href="javascript:SaveDialog2()" class="easyui-linkbutton"
       iconCls="icon-ok">修改</a>
    <a href="javascript:CloseDialog('updateDistrict')"
       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>

</body>
</html>
