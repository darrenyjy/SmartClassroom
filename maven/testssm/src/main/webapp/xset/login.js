function login() {
    var username = $("#username").val();
    var password = $("#password").val();
    var data = {
        "username":username,
        "password":password
    }
    console.log(data);
    $.ajax({
        type: "POST",
        url: "/user/login",
        data: data,
        success: function(data){
            console.log(data.msg);
        }
    });
}
