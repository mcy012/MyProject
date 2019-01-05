$(document).ready(function () {
    $.support.cors = true;

    $('#signUp').click(function () {

        var inputName = $('#userName').val();
        var inputId = $('#userId').val();
        var inputPassword = $('#userPassword1').val();
        var inputPassword2 = $('#userPassword2').val();
        var inputPhoneNumber = $('#userPhoneNumber').val();

        if (inputPassword != inputPassword2) {
            alert("입력한 비밀번호가 같지 않습니다.");
            return;
        }

        if (inputName.length < 1) {
            alert("이름을 입력하세요");
            return;
        }
        if (inputId.length < 1) {
            alert("아이디를 입력하세요");
            return;
        }
        if (inputPassword.length < 1) {
            alert("비밀번호를 입력하세요");
            return;
        }
        if (inputPassword2.length < 1) {
            alert("비밀번호 확인을 입력하세요");
            return;
        }
        if (inputPhoneNumber.length < 1) {
            alert("전화번호를 입력하세요");
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
            alert("아이디 중복 확인을 해주세요.")
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
            $("#errMsg_01").text("영문, 숫자 혼합하여 6~20자리 이내");
            event.preventDefault();
        }
    });

    $("#userId").focus(function (event) {
        $("#errMsg_01").hide();
    })

    var chkValId = function (id) {
        var patt = new RegExp(/^[A-Za-z0-9+]*$/);

        return patt.test(id);
    }



});
