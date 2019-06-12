
/*var type = 2;
function setType(typeId,type){


}*/



$(document).ready(function () {



    //设置 对页面大小及页号进行正则判断
    $(document).on("click", "#config_btn", function () {
        /*  var examId = $("input[name='examId']").val();*/
        var pagesize = $("input[name='pageSize']").val();
        var nowpage = $("input[name='pageNo']").val();
        if(nowpage==""){
            $("input[name='pageNo']").val("1");
        }
        if(pagesize==""){
            $("input[name='pageSize']").val("10");
        }
        $(".notfindinfo").empty();
        if(nowpage==null){
            $("input[name='pageNo']").val("1");
        }
        $.ajax({
            type: 'post',
            url: "/getExamLimitPageAfter",
            data: {
                pageSize: pagesize,
                nowPage: nowpage,
                type: 2,
            },
            success: function (data) {
                //异步刷新考试列表信息
                $("#appen_tr").empty();
                $("#appen_tr").append("<tr>" +
                    "<th class=\"span3\">考试名称</th>" +
                    "<th class=\"span3\">考试时间</th>" +
                    "<th>创建人</th>" +
                    "<th>上传试卷</th>" +
                    "<th>自动开始</th>" +
                    "<th>进行中</th>" +
                    "<th>已结束</th>" +
                    "<th>已归档</th>" +
                    "<th>已清理</th>" +
                    "<th>考试管理</th></tr>");
                var list = data.examlists;
                if(list!=null){
                    for (var i = 0; i < list.length; i++) {
                        debugger;
                        var str_isauto,str_inexam,str_isfinish,str_pageonhole,str_isdelete,exam_url;
                        if(list[i].isautostart == '1'){
                            str_isauto="<th><img src='img/gouxuan1.png'></th>";
                        }else{
                            str_isauto = "<th></th>";
                        }
                        if(list[i].isexam == '1'){
                            str_inexam="<th><img src='img/gouxuan1.png'></th>";
                        }else{
                            str_inexam = "<th></th>";
                        }
                        if(list[i].isfinished == '1'){
                            str_isfinish="<th><img src='img/gouxuan1.png'></th>";
                        }else{
                            str_isfinish = "<th></th>";
                        }
                        if(list[i].ispageonhole == '1'){
                            str_pageonhole="<th class='is_pageonhole'><img src='img/gouxuan1.png'></th>";
                        }else{
                            str_pageonhole = "<th  class='is_pageonhole'></th>";
                        }
                        if(list[i].isdelete == '1'){
                            str_isdelete="<th class='is_delete'><img src='img/gouxuan1.png'></th>";
                        }else{
                            str_isdelete = "<th class='is_delete'></th>";
                        }
                        if(list[i].exampaper_url !=null){
                            exam_url="<th><img src='img/gouxuan1.png'></th>";
                        }else{
                            exam_url = "<th></th>";
                        }
                        $("#appen_tr").append("<tr>" +
                            "<input class='examId' type='hidden' value="+ list[i].examId+">"+

                            "<th class=\"span3\">" + list[i].examname + "</th>" +
                            "<th class=\"span3\">" + list[i].exam_time + "</th>" +
                            "<th>" + list[i].create_name + "</th>" +
                            exam_url+str_isauto+str_inexam+str_isfinish+str_pageonhole+str_isdelete+
                            "<th>" +
                            "<button class='btn btn-primary pageonhole' style='padding: 1px 6px;margin-left:2px;'>归档</button>" +
                            "<button class='btn btn-primary delete' style='padding: 1px 6px;margin-left:2px;'>清理</button>" +
                            "</th></tr>");
                    }
                }else{
                    $("#appen_tr").empty();
                    $("#appen_tr").append("<tr>" +
                        "<th class=\"span3\">考试名称</th>" +
                        "<th class=\"span3\">考试时间</th>" +
                        "<th>创建人</th>" +
                        "<th>上传试卷</th>" +
                        "<th>自动开始</th>" +
                        "<th>进行中</th>" +
                        "<th>已结束</th>" +
                        "<th>已归档</th>" +
                        "<th>已清理</th>" +
                        "<th>考试管理</th></tr>");
                    $(".notfindinfo").append("<label style='font-size: 30px;color: #555555'>该范围的学生未找到</label>")
                }
            },
            error: function (data) {
                layer.alert("发生未知错误，请重试", {
                    icon: 2,
                    skin: 'layer-ext-moon',
                })
            },
        });
    })

    //首页
    $(document).on("click", "#firstPage", function () {
        //var examId = $("input[name='examId']").val();
        var pagesize = $("input[name='pageSize']").val();
        // var nowpage = $("input[name='pageNo']").val();
        var type = 1;
        $(".notfindinfo").empty();
        //设置nowpage=1
        $("input[name='pageNo']").val("1");

        if(pagesize==""){
            $("input[name='pageSize']").val("10");
        }
        $.ajax({
            type: 'post',
            url: "/getExamLimitPageAfter",
            data: {
                pageSize: pagesize,
                // nowPage: nowpage,
                type: type,
            },
            success: function (data) {
                //异步刷新考试列表信息
                $("#appen_tr").empty();
                $("#appen_tr").append("<tr>" +
                    "<th class=\"span3\">考试名称</th>" +
                    "<th class=\"span3\">考试时间</th>" +
                    "<th>创建人</th>" +
                    "<th>上传试卷</th>" +
                    "<th>自动开始</th>" +
                    "<th>进行中</th>" +
                    "<th>已结束</th>" +
                    "<th>已归档</th>" +
                    "<th>已清理</th>" +
                    "<th>考试管理</th></tr>");
                var list = data.examlists;
                if(list!=null){
                    for (var i = 0; i < list.length; i++) {
                        debugger;
                        var str_isauto,str_inexam,str_isfinish,str_pageonhole,str_isdelete,exam_url;
                        if(list[i].isautostart == '1'){
                            str_isauto="<th><img src='img/gouxuan1.png'></th>";
                        }else{
                            str_isauto = "<th></th>";
                        }
                        if(list[i].isexam == '1'){
                            str_inexam="<th><img src='img/gouxuan1.png'></th>";
                        }else{
                            str_inexam = "<th></th>";
                        }
                        if(list[i].isfinished == '1'){
                            str_isfinish="<th><img src='img/gouxuan1.png'></th>";
                        }else{
                            str_isfinish = "<th></th>";
                        }
                        if(list[i].ispageonhole == '1'){
                            str_pageonhole="<th class='is_pageonhole'><img src='img/gouxuan1.png'></th>";
                        }else{
                            str_pageonhole = "<th  class='is_pageonhole'></th>";
                        }
                        if(list[i].isdelete == '1'){
                            str_isdelete="<th class='is_delete'><img src='img/gouxuan1.png'></th>";
                        }else{
                            str_isdelete = "<th class='is_delete'></th>";
                        }
                        if(list[i].exampaper_url !=null){
                            exam_url="<th><img src='img/gouxuan1.png'></th>";
                        }else{
                            exam_url = "<th></th>";
                        }
                        $("#appen_tr").append("<tr>" +
                            "<input class='examId' type='hidden' value="+ list[i].examId+">"+

                            "<th class=\"span3\">" + list[i].examname + "</th>" +
                            "<th class=\"span3\">" + list[i].exam_time + "</th>" +
                            "<th>" + list[i].create_name + "</th>" +
                            exam_url+str_isauto+str_inexam+str_isfinish+str_pageonhole+str_isdelete+
                            "<th>" +
                            "<button class='btn btn-primary pageonhole' style='padding: 1px 6px;margin-left:2px;'>归档</button>" +
                            "<button class='btn btn-primary delete' style='padding: 1px 6px;margin-left:2px;'>清理</button>" +
                            "</th></tr>");

                    }
                }else{
                    $("#appen_tr").empty();
                    $("#appen_tr").append("<tr>" +
                        "<th class=\"span3\">考试名称</th>" +
                        "<th class=\"span3\">考试时间</th>" +
                        "<th>创建人</th>" +
                        "<th>上传试卷</th>" +
                        "<th>自动开始</th>" +
                        "<th>进行中</th>" +
                        "<th>已结束</th>" +
                        "<th>已归档</th>" +
                        "<th>已清理</th>" +
                        "<th>考试管理</th></tr>");
                    $(".notfindinfo").append("<label style='font-size: 30px;color: #555555'>该范围的学生未找到</label>")
                }
            },
            error: function (data) {
                layer.alert("发生未知错误，请重试", {
                    icon: 2,
                    skin: 'layer-ext-moon',
                })
            },
        });
    })
    //尾页
    $(document).on("click", "#lastPage", function () {
        // var examId = $("input[name='examId']").val();
        var pagesize = $("input[name='pageSize']").val();
        // var nowpage = $("input[name='pageNo']").val();
        if(pagesize==""){
            $("input[name='pageSize']").val("10");
        }
        var type = 0;
        $(".notfindinfo").empty();
        $.ajax({
            type: 'post',
            url: "/getExamLimitPageAfter",
            data: {
                pageSize: pagesize,
                // nowPage: nowpage,
                type: type,
            },
            success: function (data) {
                $("input[name='pageNo']").val(data.lastpage);
                //异步刷新考试列表信息
                $("#appen_tr").empty();
                $("#appen_tr").append("<tr>" +
                    "<th class=\"span3\">考试名称</th>" +
                    "<th class=\"span3\">考试时间</th>" +
                    "<th>创建人</th>" +
                    "<th>上传试卷</th>" +
                    "<th>自动开始</th>" +
                    "<th>进行中</th>" +
                    "<th>已结束</th>" +
                    "<th>已归档</th>" +
                    "<th>已清理</th>" +
                    "<th>考试管理</th></tr>");
                var list = data.examlists;
                if(list!=null){
                    for (var i = 0; i < list.length; i++) {
                        debugger;
                        var str_isauto,str_inexam,str_isfinish,str_pageonhole,str_isdelete,exam_url;
                        if(list[i].isautostart == '1'){
                            str_isauto="<th><img src='img/gouxuan1.png'></th>";
                        }else{
                            str_isauto = "<th></th>";
                        }
                        if(list[i].isexam == '1'){
                            str_inexam="<th><img src='img/gouxuan1.png'></th>";
                        }else{
                            str_inexam = "<th></th>";
                        }
                        if(list[i].isfinished == '1'){
                            str_isfinish="<th><img src='img/gouxuan1.png'></th>";
                        }else{
                            str_isfinish = "<th></th>";
                        }
                        if(list[i].ispageonhole == '1'){
                            str_pageonhole="<th class='is_pageonhole'><img src='img/gouxuan1.png'></th>";
                        }else{
                            str_pageonhole = "<th  class='is_pageonhole'></th>";
                        }
                        if(list[i].isdelete == '1'){
                            str_isdelete="<th class='is_delete'><img src='img/gouxuan1.png'></th>";
                        }else{
                            str_isdelete = "<th class='is_delete'></th>";
                        }
                        if(list[i].exampaper_url !=null){
                            exam_url="<th><img src='img/gouxuan1.png'></th>";
                        }else{
                            exam_url = "<th></th>";
                        }
                        $("#appen_tr").append("<tr>" +
                            "<input class='examId' type='hidden' value="+ list[i].examId+">"+

                            "<th class=\"span3\">" + list[i].examname + "</th>" +
                            "<th class=\"span3\">" + list[i].exam_time + "</th>" +
                            "<th>" + list[i].create_name + "</th>" +
                            exam_url+str_isauto+str_inexam+str_isfinish+str_pageonhole+str_isdelete+
                            "<th>" +
                            "<button class='btn btn-primary pageonhole' style='padding: 1px 6px;margin-left:2px;'>归档</button>" +
                            "<button class='btn btn-primary delete' style='padding: 1px 6px;margin-left:2px;'>清理</button>" +
                            "</th></tr>");
                    }
                }else{
                    $("#appen_tr").empty();
                    $("#appen_tr").append("<tr>" +
                        "<th class=\"span3\">考试名称</th>" +
                        "<th class=\"span3\">考试时间</th>" +
                        "<th>创建人</th>" +
                        "<th>上传试卷</th>" +
                        "<th>自动开始</th>" +
                        "<th>进行中</th>" +
                        "<th>已结束</th>" +
                        "<th>已归档</th>" +
                        "<th>已清理</th>" +
                        "<th>考试管理</th></tr>");
                    $(".notfindinfo").append("<label style='font-size: 30px;color: #555555'>该范围的学生未找到</label>")
                }
            },
            error: function (data) {
                layer.alert("发生未知错误，请重试", {
                    icon: 2,
                    skin: 'layer-ext-moon',
                })
            },
        });
    })
    //到哪一页
    $(document).on("click", "#goPage", function () {
        // var examId = $("input[name='examId']").val();
        var pagesize = $("input[name='pageSize']").val();
        var nowpage = $("input[name='pageNo']").val();
        if(nowpage==""){
            $("input[name='pageNo']").val("1");
        }
        if(pagesize==""){
            $("input[name='pageSize']").val("10");
        }
        var type = 2;
        $(".notfindinfo").empty();
        $.ajax({
            type: 'post',
            url: "/getExamLimitPageAfter",
            data: {
                pageSize: pagesize,
                nowPage: nowpage,
                type: type,
            },
            success: function (data) {
                //异步刷新考试列表信息
                $("#appen_tr").empty();
                $("#appen_tr").append("<tr>" +
                    "<th class=\"span3\">考试名称</th>" +
                    "<th class=\"span3\">考试时间</th>" +
                    "<th>创建人</th>" +
                    "<th>上传试卷</th>" +
                    "<th>自动开始</th>" +
                    "<th>进行中</th>" +
                    "<th>已结束</th>" +
                    "<th>已归档</th>" +
                    "<th>已清理</th>" +
                    "<th>考试管理</th></tr>");
                var list = data.examlists;
                if(list!=null){
                    for (var i = 0; i < list.length; i++) {
                        debugger;
                        var str_isauto,str_inexam,str_isfinish,str_pageonhole,str_isdelete,exam_url;
                        if(list[i].isautostart == '1'){
                            str_isauto="<th><img src='img/gouxuan1.png'></th>";
                        }else{
                            str_isauto = "<th></th>";
                        }
                        if(list[i].isexam == '1'){
                            str_inexam="<th><img src='img/gouxuan1.png'></th>";
                        }else{
                            str_inexam = "<th></th>";
                        }
                        if(list[i].isfinished == '1'){
                            str_isfinish="<th><img src='img/gouxuan1.png'></th>";
                        }else{
                            str_isfinish = "<th></th>";
                        }
                        if(list[i].ispageonhole == '1'){
                            str_pageonhole="<th class='is_pageonhole'><img src='img/gouxuan1.png'></th>";
                        }else{
                            str_pageonhole = "<th  class='is_pageonhole'></th>";
                        }
                        if(list[i].isdelete == '1'){
                            str_isdelete="<th class='is_delete'><img src='img/gouxuan1.png'></th>";
                        }else{
                            str_isdelete = "<th class='is_delete'></th>";
                        }
                        if(list[i].exampaper_url !=null){
                            exam_url="<th><img src='img/gouxuan1.png'></th>";
                        }else{
                            exam_url = "<th></th>";
                        }
                        $("#appen_tr").append("<tr>" +
                            "<input class='examId' type='hidden' value="+ list[i].examId+">"+

                            "<th class=\"span3\">" + list[i].examname + "</th>" +
                            "<th class=\"span3\">" + list[i].exam_time + "</th>" +
                            "<th>" + list[i].create_name + "</th>" +
                            exam_url+str_isauto+str_inexam+str_isfinish+str_pageonhole+str_isdelete+
                            "<th>" +
                            "<button class='btn btn-primary pageonhole' style='padding: 1px 6px;margin-left:2px;'>归档</button>" +
                            "<button class='btn btn-primary delete' style='padding: 1px 6px;margin-left:2px;'>清理</button>" +
                            "</th></tr>");
                    }
                }else{
                    $("#appen_tr").empty();
                    $("#appen_tr").append("<tr>" +
                        "<th class=\"span3\">考试名称</th>" +
                        "<th class=\"span3\">考试时间</th>" +
                        "<th>创建人</th>" +
                        "<th>上传试卷</th>" +
                        "<th>自动开始</th>" +
                        "<th>进行中</th>" +
                        "<th>已结束</th>" +
                        "<th>已归档</th>" +
                        "<th>已清理</th>" +
                        "<th>考试管理</th></tr>");
                    $(".notfindinfo").append("<label style='font-size: 30px;color: #555555'>该范围的学生未找到</label>")
                }
            },
            error: function (data) {
                layer.alert("发生未知错误，请重试", {
                    icon: 2,
                    skin: 'layer-ext-moon',
                })
            },
        });
    })
    //后一页
    $(document).on("click", "#nextPage", function () {
        // var examId = $("input[name='examId']").val();
        var pagesize = $("input[name='pageSize']").val();
        var nowpage = $("input[name='pageNo']").val();

        if(pagesize==""){
            $("input[name='pageSize']").val("10");
        }
        debugger
        nowpage++;
        $("input[name='pageNo']").val(nowpage);
        var type = 2;
        $(".notfindinfo").empty();
        $.ajax({
            type: 'post',
            url: "/getExamLimitPageAfter",
            data: {
                pageSize: pagesize,
                nowPage: nowpage,
                type: type,
            },
            success: function (data) {
                //异步刷新考试列表信息
                $("#appen_tr").empty();
                $("#appen_tr").append("<tr>" +
                    "<th class=\"span3\">考试名称</th>" +
                    "<th class=\"span3\">考试时间</th>" +
                    "<th>创建人</th>" +
                    "<th>上传试卷</th>" +
                    "<th>自动开始</th>" +
                    "<th>进行中</th>" +
                    "<th>已结束</th>" +
                    "<th>已归档</th>" +
                    "<th>已清理</th>" +
                    "<th>考试管理</th></tr>");
                var list = data.examlists;
                if(list!=null){
                    for (var i = 0; i < list.length; i++) {
                        debugger;
                        var str_isauto,str_inexam,str_isfinish,str_pageonhole,str_isdelete,exam_url;
                        if(list[i].isautostart == '1'){
                            str_isauto="<th><img src='img/gouxuan1.png'></th>";
                        }else{
                            str_isauto = "<th></th>";
                        }
                        if(list[i].isexam == '1'){
                            str_inexam="<th><img src='img/gouxuan1.png'></th>";
                        }else{
                            str_inexam = "<th></th>";
                        }
                        if(list[i].isfinished == '1'){
                            str_isfinish="<th><img src='img/gouxuan1.png'></th>";
                        }else{
                            str_isfinish = "<th></th>";
                        }
                        if(list[i].ispageonhole == '1'){
                            str_pageonhole="<th class='is_pageonhole'><img src='img/gouxuan1.png'></th>";
                        }else{
                            str_pageonhole = "<th  class='is_pageonhole'></th>";
                        }
                        if(list[i].isdelete == '1'){
                            str_isdelete="<th class='is_delete'><img src='img/gouxuan1.png'></th>";
                        }else{
                            str_isdelete = "<th class='is_delete'></th>";
                        }
                        if(list[i].exampaper_url !=null){
                            exam_url="<th><img src='img/gouxuan1.png'></th>";
                        }else{
                            exam_url = "<th></th>";
                        }
                        $("#appen_tr").append("<tr>" +
                            "<input class='examId' type='hidden' value="+ list[i].examId+">"+

                            "<th class=\"span3\">" + list[i].examname + "</th>" +
                            "<th class=\"span3\">" + list[i].exam_time + "</th>" +
                            "<th>" + list[i].create_name + "</th>" +
                            exam_url+str_isauto+str_inexam+str_isfinish+str_pageonhole+str_isdelete+
                            "<th>" +
                            "<button class='btn btn-primary pageonhole' style='padding: 1px 6px;margin-left:2px;'>归档</button>" +
                            "<button class='btn btn-primary delete' style='padding: 1px 6px;margin-left:2px;'>清理</button>" +
                            "</th></tr>");
                    }
                }else{
                    $("#appen_tr").empty();
                    $("#appen_tr").append("<tr>" +
                        "<th class=\"span3\">考试名称</th>" +
                        "<th class=\"span3\">考试时间</th>" +
                        "<th>创建人</th>" +
                        "<th>上传试卷</th>" +
                        "<th>自动开始</th>" +
                        "<th>进行中</th>" +
                        "<th>已结束</th>" +
                        "<th>已归档</th>" +
                        "<th>已清理</th>" +
                        "<th>考试管理</th></tr>");
                    $(".notfindinfo").append("<label style='font-size: 30px;color: #555555'>该范围的学生未找到</label>")
                }
            },
            error: function (data) {
                layer.alert("发生未知错误，请重试", {
                    icon: 2,
                    skin: 'layer-ext-moon',
                })
            },
        });
    })
    //前一页
    $(document).on("click", "#prePage", function () {
        var examId = $("input[name='examId']").val();
        var pagesize = $("input[name='pageSize']").val();
        var nowpage = $("input[name='pageNo']").val();
        if(nowpage==""){
            $("input[name='pageNo']").val("1");
        }
        if(pagesize==""){
            $("input[name='pageSize']").val("10");
        }
        debugger
        if (nowpage > 1) {
            nowpage--;
        }
        var type = 2;
        $(".notfindinfo").empty();
        $.ajax({
            type: 'post',
            url: "/getExamLimitPageAfter",
            data: {
                pageSize: pagesize,
                nowPage: nowpage,
                type: type,
            },
            success: function (data) {
                //异步刷新考试列表信息
                $("#appen_tr").empty();
                $("#appen_tr").append("<tr>" +
                    "<th class=\"span3\">考试名称</th>" +
                    "<th class=\"span3\">考试时间</th>" +
                    "<th>创建人</th>" +
                    "<th>上传试卷</th>" +
                    "<th>自动开始</th>" +
                    "<th>进行中</th>" +
                    "<th>已结束</th>" +
                    "<th>已归档</th>" +
                    "<th>已清理</th>" +
                    "<th>考试管理</th></tr>");
                var list = data.examlists;
                if(list!=null){
                    for (var i = 0; i < list.length; i++) {
                        debugger;
                        var str_isauto,str_inexam,str_isfinish,str_pageonhole,str_isdelete,exam_url;
                        if(list[i].isautostart == '1'){
                            str_isauto="<th><img src='img/gouxuan1.png'></th>";
                        }else{
                            str_isauto = "<th></th>";
                        }
                        if(list[i].isexam == '1'){
                            str_inexam="<th><img src='img/gouxuan1.png'></th>";
                        }else{
                            str_inexam = "<th></th>";
                        }
                        if(list[i].isfinished == '1'){
                            str_isfinish="<th><img src='img/gouxuan1.png'></th>";
                        }else{
                            str_isfinish = "<th></th>";
                        }
                        if(list[i].ispageonhole == '1'){
                            str_pageonhole="<th class='is_pageonhole'><img src='img/gouxuan1.png'></th>";
                        }else{
                            str_pageonhole = "<th  class='is_pageonhole'></th>";
                        }
                        if(list[i].isdelete == '1'){
                            str_isdelete="<th class='is_delete'><img src='img/gouxuan1.png'></th>";
                        }else{
                            str_isdelete = "<th class='is_delete'></th>";
                        }
                        if(list[i].exampaper_url !=null){
                            exam_url="<th><img src='img/gouxuan1.png'></th>";
                        }else{
                            exam_url = "<th></th>";
                        }
                        $("#appen_tr").append("<tr>" +
                            "<input class='examId' type='hidden' value="+ list[i].examId+">"+

                            "<th class=\"span3\">" + list[i].examname + "</th>" +
                            "<th class=\"span3\">" + list[i].exam_time + "</th>" +
                            "<th>" + list[i].create_name + "</th>" +
                            exam_url+str_isauto+str_inexam+str_isfinish+str_pageonhole+str_isdelete+
                            "<th>" +
                            "<button class='btn btn-primary pageonhole' style='padding: 1px 6px;margin-left:2px;'>归档</button>" +
                            "<button class='btn btn-primary delete' style='padding: 1px 6px;margin-left:2px;'>清理</button>" +
                            "</th></tr>");
                    }
                }else{
                    $("#appen_tr").empty();
                    $("#appen_tr").append("<tr>" +
                        "<th class=\"span3\">考试名称</th>" +
                        "<th class=\"span3\">考试时间</th>" +
                        "<th>创建人</th>" +
                        "<th>上传试卷</th>" +
                        "<th>自动开始</th>" +
                        "<th>进行中</th>" +
                        "<th>已结束</th>" +
                        "<th>已归档</th>" +
                        "<th>已清理</th>" +
                        "<th>考试管理</th></tr>");
                    $(".notfindinfo").append("<label style='font-size: 30px;color: #555555'>该范围的学生未找到</label>")
                }
            },
            error: function (data) {
                layer.alert("发生未知错误，请重试", {
                    icon: 2,
                    skin: 'layer-ext-moon',
                })
            },
        });
    })
});
