$(document).ready(function () {
    $.support.cors = true;

    $('#signUp').click(function () {

        var inputName = $('#userName').val();
        var inputId = $('#userId').val();
        var inputPassword = $('#userPassword1').val();
        var inputPassword2 = $('#userPassword2').val();
        var inputPhoneNumber = $('#userPhoneNumber').val();

        if (inputName.length < 1) {
            alert("이름을 입력하십시오");
            return;
        }
        if (inputId.length < 1) {
            alert("아이디를 입력하십시오");
            return;
        }
        if (inputPassword.length < 1) {
            alert("비밀번호를 입력하십시오");
            return;
        }
        if (inputPassword2.length < 1) {
            alert("비밀번호를 다시 확입하십시오");
            return;
        }
        if (inputPhoneNumber.length < 1) {
            alert("휴대전화번호를 입력하십시오");
            return;
        }

        var jsonData = {
            userName: inputName,
            userId: inputId,
            userPassword: inputPassword,
            userPhoneNumber: inputPhoneNumber
        };
        var jsonData2 = {
            pointId: inputId
        };

        if (checkNum == 1) {
            $.ajax({
                type: 'POST',
                url: 'http://192.168.0.149:9000/signUp',
                data: jsonData,
                success: function (args) {
                    alert('회원가입에 성공하셨습니다.');
                    location.href = "./login.html";
                },
                error: function (e) {
                    if (jsonData.userId == inputId) {
                        alert("이미 등록된 아이디입니다.");
                    } else {
                        alert("db 서버 연결 오류");
                    }
                }
            });
        } else {
            alert("아이디 중복을 확인하십시오.")
        }


        $.ajax({ //가입시 100시작
            type: 'POST',
            url: 'http://192.168.0.149:9000/pointSignUp',
            data: jsonData2,
            success: function (args) {},
            error: function (e) {}
        });
    });

    $(".error").hide();

    $('#userId').blur(function (event) {

        var inputId = $('#userId').val();


        if (chkValId(inputId)) {
            $("#errMsg_01").hide();
        } else {
            $("#errMsg_01").show();
            $("#errMsg_01").text("6~15자리의 (소)영문, 숫자의 조합으로 입력해주세요.");
            event.preventDefault();
        }
    });
    var chkValId = function (id) {
        var patt = new RegExp(/^.*(?=.{6,15})(?=.*[0-9])(?=.*[a-z]).*$/);

        return patt.test(id);
    }

    $('#userPassword1').blur(function (event) {

        var inputPassword = $('#userPassword1').val();


        if (chkValId(inputPassword)) {
            $("#errMsg_02").hide();
        } else {
            $("#errMsg_02").show();
            $("#errMsg_02").text("6~10자리의 (소)영문, 숫자의 조합으로 입력해주세요.");
            event.preventDefault();
        }
    });
    var chkValId = function (password) {
        var patt = new RegExp(/^.*(?=.{6,15})(?=.*[0-9])(?=.*[a-z]).*$/);

        return patt.test(password);
    }
    
    
    $('#userPassword2').blur(function (event) {

        var inputPassword = $('#userPassword1').val();
        var inputPassword2 = $('#userPassword2').val();


        if (inputPassword === inputPassword2) {
            $("#errMsg_03").hide();
        } else {
            $("#errMsg_03").show();
            $("#errMsg_03").text("비밀번호를 다시 확인해주세요.");
            event.preventDefault();
        }
    });
    
    



});
