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
        location.href = "./myInfo.html";
    })

    $(".goods").click(function () {
        location.href = "./goods.html";
    })

    $(".mainRoom").click(function () {
        location.href = "./mainRoom.html";
    })

    $(".basket").click(function () {
        location.href = "./basket.html";
    })
});
