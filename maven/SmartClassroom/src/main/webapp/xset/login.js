function login() {
    var username = $("#username").val();
    var password = $("#password").val();
    var data = {
        "username":username,
        "password":hex_md5(password)
    }
    $.ajax({
        type: "POST",
        url: "/login.do",
        dataType: "json",
        data: data,
        success: function(data){
            console.log(data.msg);
            if(data.msg == "0"){
                window.location.href = "index.do";
            }else{
                $('.login-tip').show();
            }
        }
    });
}
