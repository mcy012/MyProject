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
            alert("아이디를 입력해주십시오");
            return;
        }

        if(inputPassword.length<1) {
            alert("비밀번호를 입력해주십시오");
            return;
        }

        var jsonData = {'userId':inputId, 'userPassword':inputPassword};

        $.ajax({
            url: 'http://192.168.0.149:9000/login',
            type: 'POST',  
            data: JSON.stringify(jsonData),
            contentType : "application/json; charset=UTF-8",
            success:function(response){
                var userObj = response;

                if(userObj.CNT == 1){
                    localStorage.setItem('nickname', inputId);
                    location.href='./main.html';
                }
                else {
                    alert('비밀번호/아이디를 확인해주세요.');
                    return;
                }
            },
            error: function(request, status, error) {
                alert('아니왜안되냐고ㅡㅡ');
            }
        });
    });

    $('#signUp').click(function() {
        location.href = './signUp.html';
    });
 });
