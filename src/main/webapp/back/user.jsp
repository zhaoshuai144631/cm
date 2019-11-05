<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<script>
    $(function () {
        $("#article-table").jqGrid({
            url : "${pageContext.request.contextPath}/article/selectAll",
            datatype : "json",
            height : 250,
            colNames : [ '编号', '文章名', '内容', '创建日期', '作者',"操作" ],
            colModel : [
                {name : 'id'},
                {name : 'title' },
                {name : 'content' ,hidden:true},
                {name : 'date'},
                {name : 'author'},
                {name : 'tool',formatter:function (value,option,rows) {
                        return "<a class='btn btn-primary' onclick=\"openModel('edit','"+rows.id+"')\">修改</a>" +
                            "&nbsp;&nbsp;&nbsp;&nbsp;<a class='btn btn-danger' onclick=\"dele('"+rows.id+"')\">删除</a>";
                    }}
            ],
            styleUI:'Bootstrap',
            autowidth:true,
            rowNum : 2,
            rowList : [ 3, 5, 10, 30 ],
            pager : '#article-page',
            sortname : 'id',
            viewrecords : true,
            multiselect : false,
            caption : "文章列表",
            editurl : "${pageContext.request.contextPath}/edit",
        }).navGrid("#article-page", {edit : false,add : false,del : false,search:false});

    })
    //添加模态框事件
    function openModel(oper,id) {//传入一个参数 如果调用方法传入的是添加走添加操作 如果是修改走修改操作
        if("add"==oper){
            //执行添加
            $("#cid").val("");
            $("#title").val("");
            $("#author").val("");
            KindEditor.html("#editor_id","");
            //添加时数据不回显 清空保存数据
        }
        if("edit"==oper){

            //执行修改
            //数据回显
            var article = $("#article-table").jqGrid("getRowData",id);//根据id获得对象
            console.log(article.content);
            $("#cid").val(article.id);
            $("#title").val(article.title);
            $("#author").val(article.author);
            KindEditor.html("#editor_id",article.content);

        }
        $("#model").modal("show");



    }
    <!--初始化kindeditor-->

    KindEditor.create('#editor_id',{
        width : '600px',
        //点击图片空间按钮时发送的请求
        fileManagerJson:"${pageContext.request.contextPath}/article/space",
        //展示图片空间按钮
        allowFileManager:true,
        //上传图片所对应的方法
        uploadJson:"${pageContext.request.contextPath}/article/upload",
        //上传图片是图片的形参名称
        filePostName:"wen",
        afterBlur:function () {
            this.sync();
        }
    });
    function save() {
        var id = $("#cid").val();//根据ID判断时添加还是修改
        alert(id);
        var url="";
        if(id){
            //如果又ID执行修改方法
            url="${pageContext.request.contextPath}/article/edit";
        }else{
            //如果没有ID执行添加方法
            url="${pageContext.request.contextPath}/article/add";
        }
        //发送AJAX请求
        $.ajax({
            url: url,
            type: "post",
            data: $("#form").serialize(),
            datatype: "json",
            success: function () {
                $("#article-table").trigger("reloadGrid");
            }


        } )}
    function dele(data) {
        $.ajax({
            url:"${pageContext.request.contextPath}/article/del",
            type: "post",//请求方式
            data: {id:data},//传入参数
            datatype: "json",
            success: function () {
                $("#article-table").trigger("reloadGrid");
            }
        })
    }

</script>
<ul class="nav nav-tabs">
    <li role="presentation" class="active"><a href="#">所有文章</a></li>
    <li role="presentation"><a href="#" onclick="openModel('add','')">添加文章</a></li>
</ul>
<table id="article-table">
</table>
<div id="article-page" style="height:100px">

</div>