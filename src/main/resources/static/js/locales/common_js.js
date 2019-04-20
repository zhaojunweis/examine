$(document).ready(function () {
    $("#admin_logout").click(function () {
        layer.alert('是否要退出登录',{
            btn:['确定','取消']
        },function () {
            window.location.href = '/logout';
        },function () {

        });
    });
    $("#btn_change").click(function () {
        debugger;
        var oldpass = $("input[name='oldpass']").val();
        var newpass1 = $("input[name='newpass1']").val();
        var newpass2 = $("input[name='newpass2']").val();
        var name = $("#brandname").text();
        debugger
        if((oldpass=="")||(newpass1=="")||(newpass2=="")){
            layer.alert('请完善信息',{
                icon:2,
                skin:'layer-ext-moon',
            });
        }else{
            debugger;
            if(newpass1 == newpass2){
                $.ajax({
                    type:"post",
                    url:"/checkPasswordByname",
                    data:{
                        tName:name,
                    },
                    success:function(data){
                        if(data.status == 200){
                            debugger;
                            if(data.content!=oldpass){

                                layer.alert('原密码不正确,请重新输入',{
                                    icon:2,
                                    skin:'layer-ext-moon',
                                });
                                $("input[name='oldpass']").val("");
                            }else{
                                if(newpass1 == oldpass){
                                    layer.alert('新口令与旧口令一致,请修改',{
                                        icon:2,
                                        skin:'layer-ext-moon',
                                    });
                                    $("input[name='newpass1']").val("");
                                    $("input[name='newpass2']").val("");
                                }else {
                                    $.ajax({
                                        type:"post",
                                        url:"/editPassword",
                                        data:{
                                            tName:name,
                                            tPass:newpass1,
                                        },
                                        success:function(data){
                                            if(data.status == 200){
                                                debugger
                                                layer.msg('恭喜您，修改口令成功',{
                                                    time:1000,
                                                });
                                                $("input[name='oldpass']").val("");
                                                $("input[name='newpass1']").val("");
                                                $("input[name='newpass2']").val("");
                                            }else{
                                                layer.alert('服务器错误,修改失败',{
                                                    icon:2,
                                                    skin:'layer-ext-moon',
                                                });
                                                $("input[name='oldpass']").val("");
                                                $("input[name='newpass1']").val("");
                                                $("input[name='newpass2']").val("");
                                            }
                                        },
                                        error:function (data) {
                                            layer.alert('请求错误,请稍后再试',{
                                                icon:2,
                                                skin:'layer-ext-moon',
                                            });
                                        }

                                    });
                                }

                            }
                        }else{
                            layer.alert('数据库密码为空错误',{
                                icon:2,
                                skin:'layer-ext-moon',
                            });
                        }
                    },
                    error:function (data) {
                        debugger
                        layer.alert('查询密码是否正确失败',{
                            icon:2,
                            skin:'layer-ext-moon',
                        });
                    }

                });
            }else{
                layer.alert('两次密码不一致',{
                    icon:2,
                    skin:'layer-ext-moon',
                });
                $("input[name='newpass1']").val("");
                $("input[name='newpass2']").val("");
            }
        }

    });
});