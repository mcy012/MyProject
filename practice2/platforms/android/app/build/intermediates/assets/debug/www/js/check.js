 $(document).ready(function () {

     $.support.cors = true;

     $('input').focus(function () {
         $(this).css("background-color", "#cccccc");
     });
     $('input').blur(function () {
         $(this).css("background-color", "#ffffff");
     });

     $('#check').click(function () {


         if ($("#errMsg_01").css("display") != "none") {
             alert("올바른 아이디 형식을 입력해주십시오.");
             return;
         } else {
             var inputId = $('#userId').val();

             var jsonData = {
                 userId: inputId
             };

             $.ajax({
                 url: 'http://192.168.0.149:9000/userCheck',
                 type: 'POST',
                 data: JSON.stringify(jsonData),
                 contentType: "application/json; charset=UTF-8",
                 success: function (response) {
                     var userObj = response;

                     if (userObj.CNT == 1) {
                         alert("이미 사용 중인 아이디입니다.");
                     } else {
                         alert("사용 가능한 아이디입니다.");
                         checkNum = 1;
                     }
                 },
                 error: function (request, status, error) {
                     alert('아니왜안되냐고ㅡㅡ');
                 }
             });
         }

     });
 });
