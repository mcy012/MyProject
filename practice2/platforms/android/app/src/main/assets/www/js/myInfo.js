 $(document).ready(function() {

    $.support.cors = true;

    var myNick = localStorage.getItem('nickname');

        $.ajax({
            url: 'http://192.168.0.149:9000/myInfo/'+ myNick +'',
            type: 'GET',
            success: function(data) {
                var userObj = data;
                
                if(userObj.userId == myNick) {
                    $("#my_id").text(userObj.userId);
                    $("#my_point_total").text(userObj.pointTotal);
                    $("#my_left_chance").text(userObj.userLeftChance);
                    $(".totalPoint").text(userObj.pointTotal);
                }
            },
            error: function(request, status, error) {
                alert('db서버 연결 오류');
            }
        });

 });
