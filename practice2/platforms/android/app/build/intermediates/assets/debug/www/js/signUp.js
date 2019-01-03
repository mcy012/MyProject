$(document).ready(function() {
    $.support.cors = true;

    $('#back').click(function(){
        location.href = "login.html";
    });


    $('#signUp').click(function(){

        var inputName = $('#userName').val();
        var inputId = $('#userId').val();
        var inputPassword = $('#userPassword').val();
        //var inputPassword2 = $('#userPassword2').val();
        var inputPhoneNumber = $('#userPhoneNumber').val();
        var inputAddress = $('#userAddress').val();
/*
        if(inputPassword1 != inputPassword2) {
            alert("입력한 비밀번호가 같지 않습니다.");
            return;
        }
*/
        if(inputName.length<1) {
            alert("이름을 입력하세요");
            return;
        }
        if(inputId.length<1) {
            alert("아이디를 입력하세요");
            return;
        }
        if(inputPassword.length<1) {
            alert("비밀번호를 입력하세요");
            return;
        }
  /*      if(inputPassword2.length<1) {
            alert("비밀번호 확인을 입력하세요");
            return;
        }*/
        if(inputPhoneNumber.length<1) {
            alert("전화번호를 입력하세요");
            return;
        }
        if(inputAddress.length<1) {
            alert("주소를 입력하세요");
        }
            return;

        var jsonData = {userName:inputName, userId:inputId, userPassword:inputPassword, userPhoneNumber:inputPhoneNumber, userAddress:inputAddress };
        var jsonData2 = {pointId:inputId};

        $.ajax({
            type: 'POST',
            url: 'http://192.168.0.149:9000/signUp',
            data: jsonData,
            success:function(args){
                alert('회원가입에 성공하셨습니다.');
                location.href = "./index.html";
            },
            error:function(e){
                if(jsonData.userId == inputId) {
                    alert("이미 등록된 아이디입니다.");
                }
                else{
                    alert("db 서버 연결 오류");
                }
            }
        });

        $.ajax({                                        //가입시 100시작
            type: 'POST',
            url: 'http://192.168.0.149:9000/pointSignUp',
            data: jsonData2,
            success:function(args){
            },
            error:function(e){
            }
        });
    });
/*
    $("#alert-success").hide();
    $("#alert-danger").hide();
    $("input").keyup(function(){
        var pwd1=$("#userPassword1").val();
        var pwd2=$("#userPassword2").val();

        if(pwd1 != "" || pwd2 != ""){
            if(pwd1 == pwd2){
                $("#alert-success").show();
                $("#alert-danger").hide();
                $("#submit").removeAttr("disabled");
            }else{
                $("#alert-success").hide();
                $("#alert-danger").show();
                $("#submit").attr("disabled", "disabled");
            }
        };
    });
    */
});
