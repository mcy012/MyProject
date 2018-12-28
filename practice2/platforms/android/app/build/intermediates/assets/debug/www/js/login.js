 $(document).ready(function () {

     $.support.cors = true;

     $('input').focus(function () {
         $(this).css("background-color", "#cccccc");
     });
     $('input').blur(function () {
         $(this).css("background-color", "#ffffff");
     });
     
     

     $('#check').click(function () {
         
         var inputId = $('#nickname').val();
         
         if (inputId.length < 1 || inputId.length > 10) {
             alert("닉네임은 1글자 이상, 10글자 이하로 해주세요.");
             return;
         }

         var jsonData = {
             userId: inputId
         };

         $.ajax({
             url: 'http://192.168.0.149:9000/login',
             type: 'POST',
             data: JSON.stringify(jsonData),
             contentType: "application/json; charset=UTF-8",
             success: function (response) {
                 var userObj = response;
                 
                 if (userObj.CNT == 1) {
                     alert("이미 사용중인 닉네임입니다.");
                     check = 2;
                     return;
                 } else {
                     alert("사용 가능한 닉네임입니다.");
                     check = 1;
                 }
             },
             error: function (request, status, error) {
                 alert('db서버 연결 오류');
             }
         });
     });
     
     
     $("#pass").click(function () {
         
         var inputId = $('#nickname').val();

         if (check == 0) {
             if(inputId.length==0){
                 alert("닉네임을 설정해 주십시오.");
             }
             
             else{
                 alert("닉네임 중복체크를 확인해주세요.")
             }
         } 
         
         else if (check == 1){

             var jsonData2 = {
                 userId: inputId
             };
             
             var jsonData3 = {
                 pointId: inputId
             };

             $.ajax({
                 type: 'POST',
                 url: 'http://192.168.0.149:9000/signUp',
                 data: jsonData2,
                 success: function (args) {
                     var real = confirm("닉네임을 설정 하시겠습니까?");
                     if(real){
                         localStorage.setItem("nickname", inputId);
                         location.href = "./mainRoom.html";
                     } else{
                         return;
                     }
                     
                 },
                 error: function (e) {
                     alert("db 서버 연결 오류");
                 }
             });

             $.ajax({ //가입시 100시작
                 type: 'POST',
                 url: 'http://192.168.0.149:9000/pointSignUp',
                 data: jsonData3,
                 success: function (args) {},
                 error: function (e) {}
             });
         }
         else if(check == 2){
             alert("중복된 닉네임입니다.")
         }
    });
});
