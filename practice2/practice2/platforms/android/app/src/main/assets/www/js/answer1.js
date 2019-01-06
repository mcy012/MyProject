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
        
        if($('input:radio[id=check1]').is(':checked')==true){
            alert("정답입니다!!!");
                    
            var pointData = {};

            pointData['pointTotal'] =  Number($("#my_point_total").text()) + 50;

            var jsonData = {pointTotal:pointData['pointTotal'], pointUpDown:50};
                
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

            pointData['pointTotal'] =  Number($("#my_point_total").text()) - 30;

            var jsonData = {pointTotal:pointData['pointTotal'], pointUpDown:-30};
                    
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

 });
