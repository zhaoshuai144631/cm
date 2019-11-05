<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script charset="utf-8" src="kindeditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="kindeditor/lang/zh-CN.js"></script>
    <script>
        KindEditor.ready(function(K) {//初始化编辑器
            window.editor = K.create('#editor_id',{
                width:'1000px',//编辑器的宽度
                //点击图片空间按钮是发送的请求路径
               fileManagerJson:"${pageContext.request.contextPath}/article/space",
                allowFileManager:true ,//展示图片空间
                uploadJson:"${pageContext.request.contextPath}/article/upload",
                filePostName:"wen"//文件实体参数名,接收参数固定名字  默认imgFile

            });//这个ID是文档编辑器的id textarea

        });
    </script>
</head>
<body>
<textarea id="editor_id" name="content" style="width:700px;height:300px;">
&lt;strong&gt;HTML内容&lt;/strong&gt;
</textarea>

</body>
</html>