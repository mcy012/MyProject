 $(document).ready(function() {

    $.support.cors = true;

    var myId = localStorage.getItem('id');

    $.ajax({
        url: 'http://10.0.2.2:9000/myInfo/'+ myId +'',
        type: 'GET',
        success: function(data) {
            var userObj = data;

            if(userObj.userId == myId) {
                $('#my_id').text(userObj.userId);
                $('#my_point_total').text(userObj.pointTotal);
            }
        },

        error: function(request, status, error) {
            alert('내 정보를 가져오기에 실패하였습니다.');
        }
    });

 });
