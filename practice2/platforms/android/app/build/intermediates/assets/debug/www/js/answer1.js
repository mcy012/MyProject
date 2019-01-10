 $(document).ready(function() {

    $.support.cors = true;

    $.ajax({
        url: 'http://192.168.0.149:9000/selQuiz',
        type: 'GET',
        success: function(data) {
            
            for ( var index = 0; index < data.length; index++ ) {
                var boardObj = data[index].selectorContent;
                $('#ans'+[index+1]).append(boardObj);
            }
            $("#quiz").text(data[0].quizContent);
        },

        error: function(request, status, error) {
            alert('퀴즈 정보를 가져오는데 실패하였습니다.');
        }
    });
    
    $("#answerQue1").click(function(){
        
     if ($('input:radio[name=check]').is(':checked') == false) {
         alert("정답을 선택 하십시오.")
     } else {
        
        if($('input:radio[id=check1]').is(':checked')==true){
                 
            var pointData = {};

            pointData['pointTotal'] =  Number($("#my_point_total").text()) + 20;

            var jsonData = {pointTotal:pointData['pointTotal'], pointUpDown:20};
                
            var pointId = localStorage.getItem('nickname');

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
            
            var answer = confirm("정답입니다. \n이어서 다음 문제를 푸시겠습니까?");
            
            if(answer) {
                return;
            } else {
                location.href='./mainRoom.html'; 
            }
            
        } else {
            
            var pointData = {};

            pointData['pointTotal'] =  Number($("#my_point_total").text()) - 10;

            var jsonData = {pointTotal:pointData['pointTotal'], pointUpDown:-10};
                    
            var pointId = localStorage.getItem('nickname');

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
            
            var wrong = confirm('오답입니다 \n다른 문제에 도전하시겠습니까?');
            
            if(wrong) {
                return;
            } else {
                location.href='./mainRoom.html';
            }
        }
     }
    })
     
     
    $("#stopMain").click(function () {
        var real = confirm("나가시면 사용된 기회가 사라집니다.\n그래도 나가시겠습니까?");

        if (real) {
            location.href = "main.html";
        } else {
            return;
        }
    })

 });
