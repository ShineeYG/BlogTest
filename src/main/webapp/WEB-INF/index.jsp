<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>留言板</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">

    <script src="js/jquery-3.2.1.js" type="text/javascript"></script>
</head>
<body>


<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">博客</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="${pageContext.request.contextPath}/newblog">新建博客</a></li>
            </ul>

            <form class="navbar-form navbar-left">
                <div class="form-group">
                    <input id="title" type="text" class="form-control" placeholder="Search">
                </div>
                <button type="button" class="btn btn-default" id="btn">Submit</button>
            </form>

            <ul class="nav navbar-nav navbar-right">
                <div>${sessionScope.get("name")}</div>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${pageContext.request.contextPath}/login">切换用户</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->

    </div><!-- /.container-fluid -->
</nav>

<div class="container">
    <table class="table" id="tab">
        <thead>
        <tr>
            <th>博客标题</th>
            <th>博客描述</th>
            <th>设置</th>
        </tr>
        </thead>
    </table>
</div>


<div class="container" align="center">
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <li>
                <a href="#" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <li class="active"><a href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">5</a></li>
            <li>
                <a href="#" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
</div>

</body>
<script type="text/javascript">

    $("#btn").click(function () {
        $.ajax({
            url: "/senior",
            type: "get",
            data: {
                title: $("#title").val()
            },
            success: function (result) {
                $(".binfo").remove()
                for (var i = 0; 0 < result.length; i++) {
                    //遍历返回的数据 一次在table中添加一行
                    var info = result[i];
                    addNewTR(info.id, info.title, info.des, 0);
                }
            }
        })
    });

    function addNewTR(id, title, des, how) {

        var tdleft = $("<td></td>")
        $("<a href='###'>" + title + "</a>").appendTo(tdleft)
        var tdz = $("<td></td>").html("<p>" + des + "</p>")
        var tdright = $("<td></td>")


        $("<a href='###'>删除</a>").appendTo(tdright).click(delfunc).attr("num", id)
        var trOb = $("<tr class='binfo'></tr>").append(tdleft).append(tdz).append(tdright).attr("id", "tr" + id)

        if (how == true) {
            $("#tab").prepend(trOb)
        } else {
            $("#tab").append(trOb)
        }

    }

    function getAllBlog() {
        $.ajax({
            url: "findAllMessage",
            success: function (result) {
                console.log(result)
                for (var i = 0; i < result.length; i++) {
                    var blog = result[i];
                    addNewTR(blog.id, blog.title, blog.des, 0)
                }
            }
        })

    }
    getAllBlog()

    var delfunc = function () {

        var aid = $(this).attr("num")
        $.ajax({
            url: "/delete",
            data: {
                bid: aid
            },
            success: function (result) {

                $("#tr" + aid).remove()
            }
        })
    }


</script>

</html>
