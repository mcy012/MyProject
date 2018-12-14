$(document).ready(function() {

    $.support.cors = true;

    var myId = localStorage.getItem("id");

    $('.start').click(function() {

        var url = $(this).data("url");
        
        $.ajax({
            url: 'http://10.0.2.2:9000/myInfo/'+ myId +'',
            type: 'GET',
            success: function(data) {
                var userObj = data;

                if(userObj.userLeftChance < 1) {
                    alert("기회가 없습니다.");
                    return;
                } else{
                    $.ajax({
                        type: 'PUT',
                        url: 'http://10.0.2.2:9000/chanceMinus/'+ myId +'',
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
    });
    
    
});



