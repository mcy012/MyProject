$(document).ready(function () {
    /*
    $("#myInfo").click(function(){

        $.ajax({
            type:"GET",
            url:"./myInfo.html",
            success: function test(a){
                $("#mainRoom").html(a);
            },
            error: function error(){
                alert("error");
            }
        })
    })
        
    $("#goods").click(function(){
        $.ajax({
            type:"GET",
            url:"./goods.html",
            success: function test(a){
                $("#mainRoom").html(a);
            },
            error: function error(){
                alert("error");
            }
        })
    })
 
    $("#main").click(function(){
        location.href="./mainRoom.html";
    })
    
    $("#basket").click(function(){
        $.ajax({
            type:"GET",
            url:"./basket.html",
            success: function test(a){
                $("#mainRoom").html(a);
            },
            error: function error(){
                alert("error");
            }
        })
    })
    */
    $(".myInfo").click(function () {
        location.href = "myInfo.html";
    })

    $(".goods").click(function () {
        location.href = "goods.html";
    })

    $(".quizRoom").click(function () {
        location.href = "mainRoom.html";
    })

    $(".event").click(function () {
        location.href = "event.html";
    })

    $("#goMain").click(function () {
        location.href = "main.html";
    })
    $(".logout").click(function () {
        var logout = confirm("로그아웃하시겠습니까?");

        if (logout) {
            alert("로그아웃되었습니다.");
            localStorage.clear();
            location.href = "login.html";
        } else {
            return;
        }
    });

    $("#close").click(function () {

        var myId = localStorage.getItem("nickname")
        
        $.ajax({
            type: 'PUT',
            url: 'http://192.168.0.149:9000/chancePlus/' + myId + '',
            success: function (data) {
                alert("남은 기회가 증가하였습니다.");
            },

            error: function (request, status, error) {
                alert("error");
            }
        })
    })

});
