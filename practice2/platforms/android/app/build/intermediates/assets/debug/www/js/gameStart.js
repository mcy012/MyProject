$(document).ready(function() {

    $.support.cors = true;

    var myId = localStorage.getItem("id");

    $('.start').click(function() {

        var quizStart = confirm("기회를 한 번 사용합니다. 시작하시겠습니까?");
        
        if(quizStart == true){
            var url = $(this).data("url");
        
            $.ajax({
                url: 'http://192.168.0.149:9000/myInfo/'+ myId +'',
                type: 'GET',
                success: function(data) {
                    var userObj = data;

                    if(userObj.userLeftChance < 1) {
                        alert("기회가 없습니다.");
                        return;
                    } else{
                        $.ajax({
                            type: 'PUT',
                            url: 'http://192.168.0.149:9000/chanceMinus/'+ myId +'',
                            success:function(data){
                                location.href = "./"+ url +".html"
                        },

                            error: function(request, status, error) {
                                alert("error");
                            }
                        })                        
                    }
                },

                error: function(request, status, error) {
                    alert('내 정보를 가져오기에 실패하였습니다.');
                }
            });
        }
        else if(quizStart == false){
            return;
        }
    });
    
    
});



