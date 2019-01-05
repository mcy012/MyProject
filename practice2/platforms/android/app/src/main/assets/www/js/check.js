 $(document).ready(function() {

    $.support.cors = true;

    $('input').focus(function(){
        $(this).css("background-color", "#cccccc");
    });
    $('input').blur(function(){
        $(this).css("background-color", "#ffffff");
    });

    $('#check').click(function(){

        var inputId = $('#userId').val();

        if(inputId.length<1) {
            alert("아이디를 입력하세요");
            return;
        }

        var jsonData = {userId:inputId};

        $.ajax({
            url: 'http://192.168.0.149:9000/login',
            type: 'POST',
            data: JSON.stringify(jsonData),
            contentType : "application/json; charset=UTF-8",
            success:function(response){
                var userObj = response;

                if(userObj.CNT == 1){
                    alert("중복된 아이디 입니다.");
                }
                else {
                    alert("되는 아이디");
                    checkNum = 1;
                }
            },
            error: function(request, status, error) {
                alert('아니왜안되냐고ㅡㅡ');
            }
        });
    });
 });
