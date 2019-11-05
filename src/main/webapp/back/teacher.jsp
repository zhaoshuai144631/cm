<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <script>
        $(function () {
            $("#teacher-table").jqGrid({
                url : "${pageContext.request.contextPath}/teacher/selectAll",
                datatype : "json",
                height : 250,
                colNames : [ '编号', '名字', '性别', '头像', '状态' ],
                colModel : [
                    {name : 'id',editable:false},
                    {name : 'name' ,editable:true},
                    {name : 'sex',editable:true ,edittype:"select", editoptions:{value:"男:男;女:女"}},
                    {name : 'photo',editable:true,edittype:"file",formatter:function (value,option,rows) {//rows 一条对象信息 option 当前所有JQgRID信息
                            return "<img style='width:80px ;height:60px;' src='${pageContext.request.contextPath}/back/img/"+rows.photo+"'>";

                        }},
                    {name : 'status',editable:true,edittype:"select", editoptions:{value:"入世:入世;闭关:闭关"}}
                ],
                styleUI:'Bootstrap',
                autowidth:true,
                rowNum : 2,
                rowList : [ 3, 5, 10, 30 ],
                pager : '#teacher-page',
                sortname : 'id',
                viewrecords : true,
                multiselect : false,
                subGrid : true,//开启子表 JQGrid
                caption : "上师列表",
                editurl : "${pageContext.request.contextPath}/teacher/edit",
                subGridRowExpanded : function(subgrid_id, row_id) {
                    var subgrid_table_id, pager_id;
                    subgrid_table_id = subgrid_id + "_t";
                    pager_id = "p_" + subgrid_table_id;
                    $("#" + subgrid_id).html(//ID选择器 创建一个子表标签和一个子分页DIV标签
                        "<table id='" + subgrid_table_id
                        + "' class='scroll'></table><div id='"
                        + pager_id + "' class='scroll'></div>");
                    jQuery("#" + subgrid_table_id).jqGrid(//在做一个JQGrid
                        {
                            url : "${pageContext.request.contextPath}/user/selectAll?id=" + row_id,//根据父表id查询上师所有的信众
                            datatype : "json",
                            colNames : [ '编号', '昵称','真名','电话', '地址', '头像','性别' ,'生日'],
                            colModel : [
                                {name : "id"},
                                {name : "username"},
                                {name : "realname"},
                                {name : "phone"},
                                {name : "address"},
                                {name : "photo",edittype:"file",formatter:function (value,option,rows) {//rows 一条对象信息 option 当前所有JQgRID信息
                                        return "<img style='width:80px ;height:60px;' src='${pageContext.request.contextPath}/back/img/"+rows.photo+"'>";

                                    }},
                                {name : "sex"},
                                {name : "date"}

                            ],
                            styleUI:"Bootstrap",
                            rowNum : 2,
                            pager : pager_id,
                            sortname : 'num',
                            height : '100%'

                        });
                    jQuery("#" + subgrid_table_id).jqGrid('navGrid',
                        "#" + pager_id, {
                            edit : false,
                            add : false,
                            del : false
                        });
                },
                subGridRowColapsed : function(subgrid_id, row_id) {
                }
            }).jqGrid('navGrid', '#teacher-page', {add : true, edit : true, del : true},{
                //控制修改
                //修改之后关闭对话框
                closeAfterEdit:true,
                //设置文件上传不可选
                beforeShowFrom:function (fmt) {
                    //设置文件上传不可选
                    fmt.find("#photo").attr("disabled",true);

                }
            },{
                //控制添加文件上传
                //上传文件后关闭窗口
                closeAfterAdd:true,
                //文件上传操作

                afterSubmit:function(data) {
                    console.log(data);
                    var id = data.responseJSON.message;
                    var status = data.responseJSON.status;
                    if(status){
                        $.ajaxFileUpload({
                            url:"${pageContext.request.contextPath}/teacher/upload",//上传控制层路径
                            type:"post",//请求方式
                            fileElementId:"photo",//用哪个字段接收
                            data:{id:id},//存储在哪个上师的数据下
                            success:function (response) {
                                $("#teacher-table").trigger("reloadGrid");

                            }


                        })
                    }
                    return"123";

                }
            });
        });
    </script>

</head>
<body>
<h1>上师们</h1>
<div>
    <table id="teacher-table">

    </table>
</div>
<div id="teacher-page" style="height: 50px">

</div>
</body>

</html>