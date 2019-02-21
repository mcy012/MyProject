$(document).ready(function () {
    $.support.cors = true;

    $("#hard").hide();
    
    $.ajax({
        url: 'http://192.168.0.149:9000/getQuiz',
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

                    pointData['pointTotal'] =  Number($("#my_point_total").text()) + 70;

                    var jsonData = {pointTotal:pointData['pointTotal'], pointUpDown:70};
                    
                    var pointId = localStorage.getItem('id');

                    $.ajax({
                        type: 'PUT',
                        url: 'http://192.168.0.149:9000/pointUpdate/' + pointId + '',
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
                    
                    var pointData = {};

                    pointData['pointTotal'] =  Number($("#my_point_total").text()) - 50;

                    var jsonData = {pointTotal:pointData['pointTotal'], pointUpDown:-50};
                    
                    var pointId = localStorage.getItem('id');

                    $.ajax({
                        type: 'PUT',
                        url: 'http://192.168.0.149:9000/pointUpdate/' + pointId + '',
                        data: JSON.stringify(jsonData),
                        contentType: 'application/json',
                        success:function(response){
                        },
                        error: function(request, status, error) {
                            return;
                        }
                    })
                    location.href='./mainRoom.html';
                }
            })
        },
        error: function (request, status, error) {
            alert('퀴즈 정보를 가져오는데 실패하였습니다.');
        }
    });
    
})
