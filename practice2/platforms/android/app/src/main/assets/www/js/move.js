$(document).ready(function(){
    
    document.querySelector('#myInfo').onclick = function(){

        $.ajax({
            type:"GET",
            url:"./myInfo.html",
            success: function test(a){
                $("#mainRoom").html(a);
            },
            error: function error(){
                alert("error");
            }
        });
    };
        
    document.querySelector('#goods').onclick = function(){
        $.ajax({
            type:"GET",
            url:"./goods.html",
            success: function test(a){
                $("#mainRoom").html(a);
            },
            error: function error(){
                alert("error");
            }
        });
    }
 
    
    document.querySelector('#main').onclick = function(){
        $.ajax({
            type:"GET",
            url:"./mainRoom.html",
            success: function test(a){
                $("#mainRoom").html(a);
            },
            error: function error(){
                alert("error");
            }
        });
    };
});