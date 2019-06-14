
var tag = false //标志位，用于判断是回车登录还是鼠标点击登录
var index;//用于loading加载
//学生学号及姓名正则判断
/*
 * 正则验证全为数字，且长度在6-10位
       * */
function testSno () {
    var t_sno = $("input[name='sno']").val();
    var contentReg = /^\d{6,10}$/;
    if(contentReg.test(t_sno)){
        $("input[name='sno']").css('border','1px solid #cccccc');
        return true;
    }else{
        $("input[name='sno']").css('border','1px solid red');
        layer.msg("请正确填写6-10位数字")
        return false
    }
}

/*
* 正则验证全为数字，且长度在6-10位
* */
function testSname () {
    var t_sname = $("input[name='sname']").val();
    var contentReg = /^[\u4e00-\u9fa5]{1,10}$/;
    if(contentReg.test(t_sname)){
        $("input[name='sname']").css('border','1px solid #cccccc');
        return true;
    }else{
        $("input[name='sname']").css('border','1px solid red');
        layer.msg("请正确填写10以内长度的姓名");
        return false
    }
}

//学生登录
function studentLogin(){
    if(window.event.keyCode == 13 || tag  == true){

        debugger;
        tag = false; //一旦登录成功，把标志位设为false
        var sSno = $("input[name='sno']").val() ;
        var sname = $("input[name='sname']").val();
        debugger;
        if(testSno() && testSname() ){
            index = layer.load(2, {
                shade: [0.5,'#333']});
            $.ajax({
                type: 'post',
                url:"/submitStudentLogin",
                data: {
                    sSno:sSno,
                    sName:sname
                },
                success: function(data) {
                    debugger
                    var str = data.status;
                    debugger
                    switch (str){
                        case "200":
                            layer.close(index);
                            window.location.href = data.url;
                          /*  debugger
                            layer.confirm('您已登录成功，确定要进入学生主页吗？',{
                                btn:['确定','取消']
                            },function(){

                            },function(){
                                layer.msg('已取消登录',{
                                    time:1500,
                                    icon:2,
                                })
                            });*/
                            break;
                        case "404":
                            layer.close(index);
                            debugger
                            layer.alert(data.message,{
                                icon:2,
                                skin:'layer-ext-moon',
                            });
                            break;
                        case "500":
                            layer.close(index);
                            layer.alert(data.message,{
                                icon:2,
                                skin:'layer-ext-moon',
                            });
                            $("input[name='sno']").val("");
                            $("input[name='sname']").val("");
                            break;
                        case "403":
                            layer.close(index);
                            layer.alert(data.message,{
                                icon:0,
                                skin:'layer-ext-moon',
                            });
                            break;
                        default:
                            layer.close(index);
                            layer.alert(data.message,{
                                icon:2,
                                skin:'layer-ext-moon',
                            });
                            break;
                    }

                },
                error:function (data) {
                    layer.close(index);
                    debugger;
                    layer.alert("系统发生错误，请重新登录");
                }
            });
        }else{
            /*  layer.alert("请根据要求填写");*/
        }
    }

}


//教师登录
function teacherLogin(){
    if(window.event.keyCode == 13 || tag  == true){

        tag = false;
        var name = $("input[name='t_name']").val() ;
        var pass = $("input[name='t_pass']").val();

        if((name!="")&&(pass!="")){
            index = layer.load(2, {
                shade: [0.5,'#333']});
            $.ajax({
                type: 'post',
                url: "/submitTeacherLogin",
                data: {
                    tName:name,
                    tPass:MD5(pass),
                },
                success: function(data) {
                    debugger
                    var str = data.status;
                    if(str == 200){
                        layer.close(index);
                        window.location.href = data.url;
                       /* layer.confirm(data.message,{
                            btn:['确定','取消']
                        },function(){

                        },function(){
                            layer.msg('已取消登录',{
                                time:1500,
                                icon:2,
                            })
                        });*/
                    }else if(str == 403){
                        layer.close(index);
                        layer.alert(data.message,{
                            icon:0,
                            skin:'layer-ext-moon',
                        });
                        $("input[name='t_name']").val("");
                        $("input[name='t_pass']").val("");
                    }else {
                        layer.close(index);
                        layer.alert(data.message,{
                            icon:2,
                            skin:'layer-ext-moon',
                        });
                        $("input[name='t_name']").val("");
                        $("input[name='t_pass']").val("");
                    }
                },
                error:function (data) {
                    layer.close(index);
                    layer.alert('未知错误，请稍后再试,',{
                        icon:2,
                        skin:'layer-ext-moon',
                    });
                }
            });
        }else{
            layer.alert("请完善您的登录信息");
        }
    }
}

//管理员登录
function adminLogin(){
    if(window.event.keyCode == 13 || tag  == true){

        tag = false;
        var name = $("#adminname").val() ;
        var pass = $("#adminpass").val();
        if (pass!="admin"||name!="admin"){
            pass = MD5(pass);
        }
        debugger;
        if((name!="")&&(pass!="")){
            index = layer.load(2, {
                shade: [0.5,'#333']});
            $.ajax({
                type: 'post',
                url: "/submitAdminLogin",
                data: {
                    "adminname":name,
                    "adminpass":pass,
                },
                success: function(data) {
                    debugger
                    var str = data.status;
                    if(str == 200){
                        layer.close(index);
                        debugger
                        window.location.href = data.url;
                       /* layer.confirm('您已登录成功，确定要进入管理员主页吗？',{
                            btn:['确定','取消']
                        },function(){

                        },function(){
                            layer.msg('已取消登录',{
                                time:1500,
                                icon:2,
                            })
                        });*/

                    }else if (str == 403) {
                        layer.close(index);
                        layer.alert(data.message,{
                            icon:0,
                            skin:'layer-ext-moon',
                        });
                        $("input[name='adminname']").val("");
                        $("input[name='adminpass']").val("");
                    }else {
                        layer.close(index);
                        layer.alert(data.message,{
                            icon:2,
                            skin:'layer-ext-moon',
                        });
                        $("input[name='adminname']").val("");
                        $("input[name='adminpass']").val("");
                    }
                },
                error:function (data) {
                    layer.close(index);
                    layer.alert("未知错误，请稍后再试");
                }
            });
        }else{
            layer.alert("请完善您的登录信息");
        }
    }
}
$(document).ready(function () {

    //学生点击登录
    $("#btn_student").click(function(){
        debugger;
        tag = true;
        studentLogin();
    });


    //教师登录
    $("#btn_teacher").click(function(){
        tag = true;
        teacherLogin();

    });
    //管理员登录
    $("#btn_admin").click(function(){
        debugger;
        tag = true;
        adminLogin();
    });

});