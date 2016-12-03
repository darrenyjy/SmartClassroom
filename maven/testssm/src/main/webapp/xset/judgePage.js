console.log(window.location.pathname);
var judgeName = window.location.pathname;
if(judgeName.indexOf("userManage")!=-1){
    $(".system-manage").addClass("active");
    $(".privilege-manage").addClass("active");
    $(".user-manage").addClass("active");
}else if(judgeName.indexOf("roleManage")!=-1){
    $(".system-manage").addClass("active");
    $(".privilege-manage").addClass("active");
    $(".role-manage").addClass("active");
}else if(judgeName.indexOf("deviceManage")!=-1){
    $(".device-manage").addClass("active");
    $(".edit-device").addClass("active");
}