<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>

    <%--引入echarts js--%>
    <script src="${pageContext.request.contextPath}/echarts/echarts.min.js"></script>
    <%-- 引入bootstrap核心样式 --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/boot/css/bootstrap.min.css">
    <%-- 引入jqgrid核心基础样式 --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/jqgrid/css/trirand/ui.jqgrid.css">
    <%-- 引入jqgrid的bootstarp的皮肤 --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/jqgrid/css/jquery-ui.css">
    <%-- 引入jquery核心js --%>
    <script src="${pageContext.request.contextPath}/statics/boot/js/jquery-3.3.1.min.js"></script>
    <%-- 引入bootstrap的hexinjs --%>
    <script src="${pageContext.request.contextPath}/statics/boot/js/bootstrap.min.js"></script>
    <%-- 引入jqgrid核心js --%>
    <script src="${pageContext.request.contextPath}/statics/jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
    <%-- 引入i18njs--%>
    <script src="${pageContext.request.contextPath}/statics/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <%-- 引入ajax上传下载文件js--%>
    <script src="${pageContext.request.contextPath}/statics/jqgrid/js/ajaxfileupload.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh-CN.js"></script>
    <script>
        $('#myTabs a').click(function (e) {
            e.preventDefault()
            $(this).tab('show')
        })
        $('#myTabs a[href="#profile"]').tab('show') // Select tab by name
        $('#myTabs a:first').tab('show') // Select first tab
        $('#myTabs a:last').tab('show') // Select last tab
        $('#myTabs li:eq(2) a').tab('show') // Select third tab (0-indexed)
        $("#dd").click(function () {
            $('#mm').modal('show');

        });
    </script>

</head>
<body>
<nav class="navbar navbar-default  navbar navbar-inverse">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">持明法洲后台管理系统<small>v1.0</small></a>
        <ul class="nav navbar-nav navbar-right">
            <li ><a class="navbar-brand " href="#" >欢迎：<span style="color: blue">${sessionScope.loginAdmin.realname}</span></a></li>
            <li > <a class="navbar-brand " href="${pageContext.request.contextPath}/code/exit" >  安全退出<span class="glyphicon glyphicon-log-out" aria-hidden="true"></span></a></li>
        </ul>
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <!--菜单-->
        <div class="col-md-2">
            <!--菜单按钮-->
            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                轮播图管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <!--列表组-->
                            <div class="list-group">
                                <a  href="javascript:$('#a1').load('${pageContext.request.contextPath}/back/showAll.jsp');" class="list-group-item">所有轮播图</a>


                            </div>

                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingTwo">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                             专辑管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                        <div class="panel-body">
                            <div class="list-group">
                                <a href="javascript:$('#a1').load('${pageContext.request.contextPath}/back/album.jsp');" class="list-group-item active">
                                    专辑管理
                                </a>



                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingFour">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseTwo">
                                文章管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
                        <div class="panel-body">
                            <div class="list-group">
                                <a href="javascript:$('#a1').load('${pageContext.request.contextPath}/back/article.jsp');" class="list-group-item active">
                                    文章操作
                                </a>


                            </div>
                        </div>
                    </div>

                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingThree">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                用户管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                        <div class="panel-body">
                            <div class="list-group">
                                <a href="javascript:$('#a1').load('${pageContext.request.contextPath}/back/user.jsp');" class="list-group-item active">
                                    用户展示
                                </a>
                                <a href="javascript:$('#a1').load('${pageContext.request.contextPath}/back/view.jsp');" class="list-group-item active">
                                    用户注册图表
                                </a>


                            </div>
                        </div>
                    </div>

                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingFive">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                                上师管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive">
                        <div class="panel-body">
                            <div class="list-group">
                                <a  href="javascript:$('#a1').load('${pageContext.request.contextPath}/back/teacher.jsp');" class="list-group-item">上师展示</a>


                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-10" id="a1">
            <div class="jumbotron" style="height: 40px" >
                <div class="container " >
                    <h3>欢迎进入持法明州后台管理系统<small>v1.0</small></h3>

                </div>
            </div>
            <div class="jumbotron" >
                <div class="container" >

                    <img  style="width: 850px;height: 400px" src="${pageContext.request.contextPath}/back/img/mv.jpg">

                </div>
            </div>
        </div>
    </div>
</div>
<%--页脚--%>
<div class="panel panel-footer text-center">
    持明法州@百知教育 2019.10.28
</div>

</body>

</html>