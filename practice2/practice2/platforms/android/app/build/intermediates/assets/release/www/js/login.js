 $(document).ready(function() {

    $.support.cors = true;

    $('input').focus(function(){
        $(this).css("background-color", "#cccccc");
    });
    $('input').blur(function(){
        $(this).css("background-color", "#ffffff");
    });

    $('#login').click(function(){

        var inputId = $('#userId').val();
        var inputPassword = $('#userPassword').val();

        if(inputId.length<1) {
            alert("아이디를 입력하세요");
            return;
        }

        if(inputPassword.length<1) {
            alert("비밀번호를 입력하세요");
            return;
        }

        var jsonData = {userId:inputId, userPassword:inputPassword};

        $.ajax({
            url: 'http://192.168.0.149:9000/login',
            type: 'POST',
            data: JSON.stringify(jsonData),
            contentType : "application/json; charset=UTF-8",
            success:function(response){
                var userObj = response;

                if(userObj.CNT == 1){
                    location.href='./mainRoom.html';
                    localStorage.setItem('id', inputId);
                }
                else {
                    alert('아이디나 비밀번호가 틀렸습니다.');
                    return;
                }
            },
            error: function(request, status, error) {
                alert('db서버 연결 오류');
            }
        });
    });

    $('#signUp').click(function() {
        location.href = './signUp.html';
    });
 });
