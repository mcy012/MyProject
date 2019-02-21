 $(document).ready(function() {

    $.support.cors = true;

    var myId = localStorage.getItem('id');

    $.ajax({
        url: 'http://192.168.0.149:9000/myInfo/'+ myId +'',
        type: 'GET',
        success: function(data) {
            var userObj = data;

            if(userObj.userId == myId) {
                $('#my_id').text(userObj.userId);
                $('#my_point_total').text(userObj.pointTotal);
            }
        },

        error: function(request, status, error) {
            alert('물품정보를 가져오는데 실패하였습니다.');
        }
    });

 });
