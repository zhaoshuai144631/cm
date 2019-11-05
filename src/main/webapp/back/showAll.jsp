<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" isELIgnored="false" %>
<head>
    <script>
        $(function () {//再页面加载完成后载入函数
            $("#table").jqGrid({//添加jqGrid
                url : "${pageContext.request.contextPath}/pic/selectAll",
                datatype : "json",
                colNames : [ '编号', '名称', '封面', '描述','上传日期', '状态'],//列头自定义名
                colModel : [
                    {name : 'id',hidden:true,editable:false},//每列展示的数据 根据数据库表里的字段
                    {name : 'name',editable:true},
                    {name : 'cover',editable:true, edittype:"file",formatter:function (value,option,rows) {//rows 一条对象信息 option 当前所有JQgRID信息
                        return "<img style='width:80px ;height:60px;' src='${pageContext.request.contextPath}/back/img/"+rows.cover+"'>";

                        }},
                    {name : 'description',editable:true},
                    {name : 'date',editable:false},
                    {name : 'status',editable:true,edittype:"select",editoptions:{value:"正常:正常;冻结:冻结"}}
                ],
                height:250,
                autowidth:true,//自适应宽度
                styleUI:"Bootstrap",//结合Bootstrap
                rowNum : 3,//每页展示多少条
                rowList : [ 3, 6, 9 ],//分页选择下拉每页展示多少条
                pager : '#page',//分页ID
                sortname : 'id',
                viewrecords : true,//是否展示所有条数
                sortorder : "desc",//排序
                caption : "持明法洲图片管理列表",
                editurl : "${pageContext.request.contextPath}/pic/edit"

            }).navGrid("#page", {edit : true,add :true,del : true },{
                //控制修改
                //修改之后关闭对话框
                closeAfterEdit:true,
                beforeShowFrom:function (fmt) {
                    //设置文件上传不可选
                    fmt.find("#cover").attr("disabled",true);

                }
                },{
                //控制添加 文件上传
                closeAfterAdd:true,//添加结束后关闭窗口
                afterSubmit:function(data) {
                    //data 控制层返回的json对象·
                    //文件上传操作
                    console.log(data);
                    var status = data.responseJSON.status;
                    var id  = data.responseJSON.message;
                   if(status){//如果数据上传成功接着上传文件
                       $.ajaxFileUpload({
                           url:"${pageContext.request.contextPath}/pic/upload",//上传控制层路径
                           type:"post",//提交传输方式
                           fileElementId:"cover",//上传的图片后台用什么接收
                           data:{id:id},//要上传的图片存储到哪个ID下
                           success:function (response){
                                    //自动刷新JQGRID表单
                               $("#table").trigger("reloadGrid");
                           }


                       })
                   }

                    return"111";

                }
                }


            );
        });

    </script>
</head>
<html>
<body>
<!--panel是面板 panel-heading是面板头-->
<div class="panel panel-heading">
    <h3>所有轮播图</h3>
</div>
<table id="table">

</table>
<div id="page" style="height: 50px">
</div>
</body>
</html>