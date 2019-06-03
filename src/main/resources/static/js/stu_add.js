
/*var type = 2;
function setType(typeId,type){


}*/



$(document).ready(function () {

    //设置 对页面大小及页号进行正则判断
    $(document).on("click", "#config_btn", function () {
        var examId = $("input[name='examId']").val();
        var pagesize = $("input[name='pageSize']").val();
        var nowpage = $("input[name='pageNo']").val();
        $(".notfindinfo").empty();
        $.ajax({
            type: 'post',
            url: "/getLimitPage",
            data: {
                Id: examId,
                pageSize: pagesize,
                nowPage: nowpage,
                type: 2,
            },
            success: function (data) {
                var studenglist = data.studentlist;
                debugger;
                if (studenglist != null) {
                    $("thead").empty();
                    $("thead").append("<tr>\n" +
                        "\t\t\t\t\t<th class=\"span4\">学号</th>\n" +
                        "\t\t\t\t\t<th class=\"span4\">姓名</th>\n" +
                        "\t\t\t\t\t<th class=\"span3\">班级</th>\n" +
                        "\t\t\t\t\t<th>操作</th>\n" +
                        "\t\t\t\t</tr>");
                    for (var i = 0; i < studenglist.length; i++) {
                        var student = studenglist[i];
                        $("thead").append("<tr><input class=\"studentId\" type=\"hidden\" value='" + student.id + "'>" +
                            "<th class=\"span4\" >" + student.sSno + "</th>\n" +
                            "<th class=\"span4\" >" + student.sName + "</th>\n" +
                            "<th class=\"span3\" >" + student.sClassId + "</th>\n" +
                            "<th><button class='btn btn-primary delete' style='padding: 1px 6px;margin-left:2px;'>删除</button></th></tr>");
                    }
                } else {
                    $("thead").empty();
                    $("thead").append("<tr>\n" +
                        "\t\t\t\t\t<th class=\"span4\">学号</th>\n" +
                        "\t\t\t\t\t<th class=\"span4\">姓名</th>\n" +
                        "\t\t\t\t\t<th class=\"span3\">班级</th>\n" +
                        "\t\t\t\t\t<th>操作</th>\n" +
                        "\t\t\t\t</tr>");
                    $(".notfindinfo").append("<label style='font-size: 30px;color: #555555'>该范围的学生未找到</label>")
                }
            },
            error: function (data) {
                layer.alert("发生未知错误，请重试", {
                    icon: 2,
                    skin: 'layer-ext-moon',
                })
            },
        })
    })

    //首页
    $(document).on("click", "#firstPage", function () {
        var examId = $("input[name='examId']").val();
        var pagesize = $("input[name='pageSize']").val();
        // var nowpage = $("input[name='pageNo']").val();
        var type = 1;
        $(".notfindinfo").empty();
        $.ajax({
            type: 'post',
            url: "/getLimitPage",
            data: {
                Id: examId,
                pageSize: pagesize,
                // nowPage: nowpage,
                type: 1,
            },
            success: function (data) {
                var studenglist = data.studentlist;
                debugger;
                if (studenglist != null) {
                    $("thead").empty();
                    $("thead").append("<tr>\n" +
                        "\t\t\t\t\t<th class=\"span4\">学号</th>\n" +
                        "\t\t\t\t\t<th class=\"span4\">姓名</th>\n" +
                        "\t\t\t\t\t<th class=\"span3\">班级</th>\n" +
                        "\t\t\t\t\t<th>操作</th>\n" +
                        "\t\t\t\t</tr>");
                    for (var i = 0; i < studenglist.length; i++) {
                        var student = studenglist[i];
                        $("thead").append("<tr><input class=\"studentId\" type=\"hidden\" value='" + student.id + "'>" +
                            "<th class=\"span4\" >" + student.sSno + "</th>\n" +
                            "<th class=\"span4\" >" + student.sName + "</th>\n" +
                            "<th class=\"span3\" >" + student.sClassId + "</th>\n" +
                            "<th><button class='btn btn-primary delete' style='padding: 1px 6px;margin-left:2px;'>删除</button></th></tr>");
                    }
                } else {
                    $("thead").empty();
                    $("thead").append("<tr>\n" +
                        "\t\t\t\t\t<th class=\"span4\">学号</th>\n" +
                        "\t\t\t\t\t<th class=\"span4\">姓名</th>\n" +
                        "\t\t\t\t\t<th class=\"span3\">班级</th>\n" +
                        "\t\t\t\t\t<th>操作</th>\n" +
                        "\t\t\t\t</tr>");
                    $(".notfindinfo").append("<label style='font-size: 30px;color: #555555'>该范围的学生未找到</label>")
                }
            },
            error: function (data) {
                layer.alert("发生未知错误，请重试", {
                    icon: 2,
                    skin: 'layer-ext-moon',
                })
            },
        })
    })
    //尾页
    $(document).on("click", "#lastPage", function () {
        var examId = $("input[name='examId']").val();
        var pagesize = $("input[name='pageSize']").val();
        // var nowpage = $("input[name='pageNo']").val();
        var type = 0;
        $(".notfindinfo").empty();
        $.ajax({
            type: 'post',
            url: "/getLimitPage",
            data: {
                Id: examId,
                pageSize: pagesize,
                // nowPage: nowpage,
                type: type,
            },
            success: function (data) {
                var studenglist = data.studentlist;
                debugger;
                if (studenglist != null) {
                    $("thead").empty();
                    $("thead").append("<tr>\n" +
                        "\t\t\t\t\t<th class=\"span4\">学号</th>\n" +
                        "\t\t\t\t\t<th class=\"span4\">姓名</th>\n" +
                        "\t\t\t\t\t<th class=\"span3\">班级</th>\n" +
                        "\t\t\t\t\t<th>操作</th>\n" +
                        "\t\t\t\t</tr>");
                    for (var i = 0; i < studenglist.length; i++) {
                        var student = studenglist[i];
                        $("thead").append("<tr><input class=\"studentId\" type=\"hidden\" value='" + student.id + "'>" +
                            "<th class=\"span4\" >" + student.sSno + "</th>\n" +
                            "<th class=\"span4\" >" + student.sName + "</th>\n" +
                            "<th class=\"span3\" >" + student.sClassId + "</th>\n" +
                            "<th><button class='btn btn-primary delete' style='padding: 1px 6px;margin-left:2px;'>删除</button></th></tr>");
                    }
                } else {
                    $("thead").empty();
                    $("thead").append("<tr>\n" +
                        "\t\t\t\t\t<th class=\"span4\">学号</th>\n" +
                        "\t\t\t\t\t<th class=\"span4\">姓名</th>\n" +
                        "\t\t\t\t\t<th class=\"span3\">班级</th>\n" +
                        "\t\t\t\t\t<th>操作</th>\n" +
                        "\t\t\t\t</tr>");
                    $(".notfindinfo").append("<label style='font-size: 30px;color: #555555'>该范围的学生未找到</label>")
                }
            },
            error: function (data) {
                layer.alert("发生未知错误，请重试", {
                    icon: 2,
                    skin: 'layer-ext-moon',
                })
            },
        })
    })
    //到哪一页
    $(document).on("click", "#goPage", function () {
        var examId = $("input[name='examId']").val();
        var pagesize = $("input[name='pageSize']").val();
        var nowpage = $("input[name='pageNo']").val();
        var type = 2;
        $(".notfindinfo").empty();
        $.ajax({
            type: 'post',
            url: "/getLimitPage",
            data: {
                Id: examId,
                pageSize: pagesize,
                nowPage: nowpage,
                type: type,
            },
            success: function (data) {
                var studenglist = data.studentlist;
                debugger;
                if (studenglist != null) {
                    $("thead").empty();
                    $("thead").append("<tr>\n" +
                        "\t\t\t\t\t<th class=\"span4\">学号</th>\n" +
                        "\t\t\t\t\t<th class=\"span4\">姓名</th>\n" +
                        "\t\t\t\t\t<th class=\"span3\">班级</th>\n" +
                        "\t\t\t\t\t<th>操作</th>\n" +
                        "\t\t\t\t</tr>");
                    for (var i = 0; i < studenglist.length; i++) {
                        var student = studenglist[i];
                        $("thead").append("<tr><input class=\"studentId\" type=\"hidden\" value='" + student.id + "'>" +
                            "<th class=\"span4\" >" + student.sSno + "</th>\n" +
                            "<th class=\"span4\" >" + student.sName + "</th>\n" +
                            "<th class=\"span3\" >" + student.sClassId + "</th>\n" +
                            "<th><button class='btn btn-primary delete' style='padding: 1px 6px;margin-left:2px;'>删除</button></th></tr>");
                    }
                } else {
                    $("thead").empty();
                    $("thead").append("<tr>\n" +
                        "\t\t\t\t\t<th class=\"span4\">学号</th>\n" +
                        "\t\t\t\t\t<th class=\"span4\">姓名</th>\n" +
                        "\t\t\t\t\t<th class=\"span3\">班级</th>\n" +
                        "\t\t\t\t\t<th>操作</th>\n" +
                        "\t\t\t\t</tr>");
                    $(".notfindinfo").append("<label style='font-size: 30px;color: #555555'>该范围的学生未找到</label>")
                }
            },
            error: function (data) {
                layer.alert("发生未知错误，请重试", {
                    icon: 2,
                    skin: 'layer-ext-moon',
                })
            },
        })
    })
    //后一页
    $(document).on("click", "#nextPage", function () {
        var examId = $("input[name='examId']").val();
        var pagesize = $("input[name='pageSize']").val();
        var nowpage = $("input[name='pageNo']").val();
        debugger
        nowpage++;
        $("input[name='pageNo']").val(nowpage);
        var type = 2;
        $(".notfindinfo").empty();
        $.ajax({
            type: 'post',
            url: "/getLimitPage",
            data: {
                Id: examId,
                pageSize: pagesize,
                nowPage: nowpage,
                type: type,
            },
            success: function (data) {
                var studenglist = data.studentlist;
                debugger;
                if (studenglist != null) {
                    $("thead").empty();
                    $("thead").append("<tr>\n" +
                        "\t\t\t\t\t<th class=\"span4\">学号</th>\n" +
                        "\t\t\t\t\t<th class=\"span4\">姓名</th>\n" +
                        "\t\t\t\t\t<th class=\"span3\">班级</th>\n" +
                        "\t\t\t\t\t<th>操作</th>\n" +
                        "\t\t\t\t</tr>");
                    for (var i = 0; i < studenglist.length; i++) {
                        var student = studenglist[i];
                        $("thead").append("<tr><input class=\"studentId\" type=\"hidden\" value='" + student.id + "'>" +
                            "<th class=\"span4\" >" + student.sSno + "</th>\n" +
                            "<th class=\"span4\" >" + student.sName + "</th>\n" +
                            "<th class=\"span3\" >" + student.sClassId + "</th>\n" +
                            "<th><button class='btn btn-primary delete' style='padding: 1px 6px;margin-left:2px;'>删除</button></th></tr>");
                    }
                } else {
                    $("thead").empty();
                    $("thead").append("<tr>\n" +
                        "\t\t\t\t\t<th class=\"span4\">学号</th>\n" +
                        "\t\t\t\t\t<th class=\"span4\">姓名</th>\n" +
                        "\t\t\t\t\t<th class=\"span3\">班级</th>\n" +
                        "\t\t\t\t\t<th>操作</th>\n" +
                        "\t\t\t\t</tr>");
                    $(".notfindinfo").append("<label style='font-size: 30px;color: #555555'>该范围的学生未找到</label>")
                }
            },
            error: function (data) {
                layer.alert("发生未知错误，请重试", {
                    icon: 2,
                    skin: 'layer-ext-moon',
                })
            },
        })
    })
    //前一页
    $(document).on("click", "#prePage", function () {
        var examId = $("input[name='examId']").val();
        var pagesize = $("input[name='pageSize']").val();
        var nowpage = $("input[name='pageNo']").val();
        debugger
        if (nowpage > 1) {
            nowpage--;
        }
        $("input[name='pageNo']").val(nowpage);
        var type = 2;
        $(".notfindinfo").empty();
        $.ajax({
            type: 'post',
            url: "/getLimitPage",
            data: {
                Id: examId,
                pageSize: pagesize,
                nowPage: nowpage,
                type: type,
            },
            success: function (data) {
                var studenglist = data.studentlist;
                debugger;
                if (studenglist != null) {
                    $("thead").empty();
                    $("thead").append("<tr>\n" +
                        "\t\t\t\t\t<th class=\"span4\">学号</th>\n" +
                        "\t\t\t\t\t<th class=\"span4\">姓名</th>\n" +
                        "\t\t\t\t\t<th class=\"span3\">班级</th>\n" +
                        "\t\t\t\t\t<th>操作</th>\n" +
                        "\t\t\t\t</tr>");
                    for (var i = 0; i < studenglist.length; i++) {
                        var student = studenglist[i];
                        $("thead").append("<tr><input class=\"studentId\" type=\"hidden\" value='" + student.id + "'>" +
                            "<th class=\"span4\" >" + student.sSno + "</th>\n" +
                            "<th class=\"span4\" >" + student.sName + "</th>\n" +
                            "<th class=\"span3\" >" + student.sClassId + "</th>\n" +
                            "<th><button class='btn btn-primary delete' style='padding: 1px 6px;margin-left:2px;'>删除</button></th></tr>");
                    }
                } else {
                    $("thead").empty();
                    $("thead").append("<tr>\n" +
                        "\t\t\t\t\t<th class=\"span4\">学号</th>\n" +
                        "\t\t\t\t\t<th class=\"span4\">姓名</th>\n" +
                        "\t\t\t\t\t<th class=\"span3\">班级</th>\n" +
                        "\t\t\t\t\t<th>操作</th>\n" +
                        "\t\t\t\t</tr>");
                    $(".notfindinfo").append("<label style='font-size: 30px;color: #555555'>该范围的学生未找到</label>")
                }
            },
            error: function (data) {
                layer.alert("发生未知错误，请重试", {
                    icon: 2,
                    skin: 'layer-ext-moon',
                })
            },
        })
    })
    //删除某个学生
    $(document).on("click", ".delete", function () {
        var studentId = $(this).parent().parent().children(".studentId").val();
        var examId = $("input[name='examId']").val();
        var pagesize = $("input[name='pageSize']").val();
        var nowpage = $("input[name='pageNo']").val();
        $(".notfindinfo").empty();
        $.ajax({
            type: 'post',
            url: '/deleteOneStudent',
            data: {
                stuId: studentId,
                Id:examId,
                pageSize:pagesize,
                nowPage:nowpage,
            },
            success: function (data) {
                if (data.status == 200) {
                    layer.alert(data.message, {
                        icon: 1,
                        skin: 'layer-ext-moon',
                    })
                    var studenglist = data.studentlist;
                    debugger;
                    if (studenglist != null) {
                        $("thead").empty();
                        $("thead").append("<tr>\n" +
                            "\t\t\t\t\t<th class=\"span4\">学号</th>\n" +
                            "\t\t\t\t\t<th class=\"span4\">姓名</th>\n" +
                            "\t\t\t\t\t<th class=\"span3\">班级</th>\n" +
                            "\t\t\t\t\t<th>操作</th>\n" +
                            "\t\t\t\t</tr>");
                        for (var i = 0; i < studenglist.length; i++) {
                            var student = studenglist[i];
                            $("thead").append("<tr><input class=\"studentId\" type=\"hidden\" value='" + student.id + "'>" +
                                "<th class=\"span4\" >" + student.sSno + "</th>\n" +
                                "<th class=\"span4\" >" + student.sName + "</th>\n" +
                                "<th class=\"span3\" >" + student.sClassId + "</th>\n" +
                                "<th><button class='btn btn-primary delete' style='padding: 1px 6px;margin-left:2px;'>删除</button></th></tr>");
                        }
                    } else {
                        $("thead").empty();
                        $("thead").append("<tr>\n" +
                            "\t\t\t\t\t<th class=\"span4\">学号</th>\n" +
                            "\t\t\t\t\t<th class=\"span4\">姓名</th>\n" +
                            "\t\t\t\t\t<th class=\"span3\">班级</th>\n" +
                            "\t\t\t\t\t<th>操作</th>\n" +
                            "\t\t\t\t</tr>");
                        $(".notfindinfo").append("<label style='font-size: 30px;color: #555555'>该范围的学生未找到</label>")
                    }

                }else {
                    layer.alert(data.message, {
                        icon: 2,
                        skin: 'layer-ext-moon',
                    })
                }
            },
            error: function (data) {
                layer.alert("发生未知错误，请重试", {
                    icon: 2,
                    skin: 'layer-ext-moon',
                })
            }
        });

    })
});
