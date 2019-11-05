<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <script>
        $(function () {
            $("#album-table").jqGrid({
                url : "${pageContext.request.contextPath}/album/selectAll",
                datatype : "json",
                height : 250,
                colNames : [ '编号', '封面', '名称', '章节数','评分','作者','播音员' ,'创建日期','简介' ],
                colModel : [
                    {name : 'id',editable:false},
                    {name : 'cover',editable:true,edittype:"file",formatter:function (value,option,rows) {//rows 一条对象信息 option 当前所有JQgRID信息
                            return "<img style='width:80px ;height:60px;' src='${pageContext.request.contextPath}/back/img/"+rows.cover+"'>";

                        }},
                    {name : 'title' ,editable:true},
                    {name : 'count' ,editable:false},
                    {name : 'score' ,editable:false},
                    {name : 'teacherId' ,editable:true ,edittype:"select",editoptions:{dataUrl:"${pageContext.request.contextPath}/teacher/teacherName"},formatter:function(value,options,rows) {
                            return rows.teacher.name;
                        }},
                    {name : 'broadcast' ,editable:true},
                    {name : 'date' ,editable:true,edittype:"date"},
                    {name : 'brief' ,editable:true}
                ],
                styleUI:'Bootstrap',
                autowidth:true,
                rowNum : 2,
                rowList : [ 3, 5, 10 ],
                pager : '#album-page',
                sortname : 'id',
                viewrecords : true,
                subGrid : true,//开启子表 JQGrid
                caption : "专辑列表",
                editurl : "${pageContext.request.contextPath}/album/edit",
                subGridRowExpanded : function(subgrid_id, id) {
                    var subgrid_table_id, pager_id;
                    subgrid_table_id = subgrid_id + "_t";
                    pager_id = "p_" + subgrid_table_id;
                    $("#" + subgrid_id).html(//ID选择器 创建一个子表标签和一个子分页DIV标签
                        "<table id='" + subgrid_table_id
                        + "' class='scroll'></table><div id='"
                        + pager_id + "' class='scroll'></div>");
                    jQuery("#" + subgrid_table_id).jqGrid(//在做一个JQGrid
                        {
                            url : "${pageContext.request.contextPath}/chapter/selectAll?albumId=" + id,//根据父表id查专辑所有的章节
                            datatype : "json",
                            colNames : [ '编号', '章节名','大小','时长', '文件名', '创建日期','音频'],
                            colModel : [
                                {name : "id" ,editable:false,hidden:true},
                                {name : "title",editable:true},//章节名
                                {name : "sizes",editable:false},//大小
                                {name : "duration",editable:false},//时长
                                {name : "name",editable:true,edittype:"file"},//文件
                                {name : "date",editable:true,edittype:"date"},//创建日期
                                {name : "operation",width:350,formatter:function (value,option,rows) {
                                        return "<audio controls>\n" +
                                            "  <source src='${pageContext.request.contextPath}/back/music/"+rows.name+"' >\n" +
                                            "</audio>";
                                    }}

                            ],
                            styleUI:"Bootstrap",
                            autowidth:true,
                            rowNum : 2,
                            pager : pager_id,
                            height : '100%',
                            editurl : "${pageContext.request.contextPath}/chapter/edit"

                        });
                    jQuery("#" + subgrid_table_id).jqGrid('navGrid',
                        "#" + pager_id, {
                            edit : false,
                            add : true,
                            del : false
                        },{},{
                        //控制添加
                            closeAfterAdd:true,
                            afterSubmit:function (response) {
                                var status=response.responseJSON.status;
                                if(status){
                                    var cid = response.responseJSON.message;
                                    //上传
                                    $.ajaxFileUpload({
                                        url:"${pageContext.request.contextPath}/chapter/upload",
                                        type:"post",
                                        fileElementId:"name",
                                        data:{id:cid,albumId:id},
                                        success:function () {
                                            $("#"+subgrid_table_id).trigger("reloadGrid");

                                        }

                                    })

                                }
                                return "1231123";
                            }
                        });
                }

            }).jqGrid('navGrid', '#album-page', {add : true, edit : true, del : true},{
                //控制修改
                //修改之后关闭对话框
                closeAfterEdit:true,
                //设置文件上传不可选
                beforeShowFrom:function (fmt) {
                    //设置文件上传不可选
                    fmt.find("#cover").attr("disabled",true);

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
                            url:"${pageContext.request.contextPath}/album/upload",//上传控制层路径
                            type:"post",//请求方式
                            fileElementId:"cover",//用哪个字段接收
                            data:{id:id},//存储在哪个上师的数据下
                            success:function (response) {
                                $("#album-table").trigger("reloadGrid");

                            }


                        })
                    }
                    return"11213";

                }
            });
        });
    </script>

</head>
<body>
<h1>专辑操作</h1>
<div>
    <table id="album-table">

    </table>
</div>
<div id="album-page" style="height: 50px">

</div>
</body>

</html>