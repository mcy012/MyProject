$(document).ready(function () {
    $.support.cors = true;

    $.ajax({
        url: 'http://10.0.2.2:9000/getQuiz',
        type: 'GET',
        success: function (data) {
            $("#question2").text(data.quizContent);
            
            $("#submit2").click(function(){
                var inputAnswer = $("#answer2").val();
        
                if(inputAnswer.length < 1){
                    alert("정답을 입력하세요");
                    return;
                }
        
                if(inputAnswer == data.answerContent){
                    alert('정답입니다!!!');
                    
                    var pointData = {};

                    pointData['pointTotal'] =  Number($("#my_point_total").text()) + 7;

                    var jsonData = {pointTotal:pointData['pointTotal'], pointUpDown:7};
                    
                    var pointId = localStorage.getItem('id');

                    $.ajax({
                        type: 'PUT',
                        url: 'http://10.0.2.2:9000/pointUpdate/' + pointId + '',
                        data: JSON.stringify(jsonData),
                        contentType: 'application/json',
                        success:function(response){
                        },
                        error: function(request, status, error) {
                            return;
                        }
                    })
                    location.href='./mainRoom.html';
                } else {
                    alert('틀렸습니다.');
                    return;
                }
            })
        },
        error: function (request, status, error) {
            alert('내 정보를 가져오기에 실패하였습니다.');
        }
    });
    
})
