 $(document).ready(function () {

     $.support.cors = true;

     var myNick = localStorage.getItem('nickname');

     $.ajax({
         url: 'http://192.168.0.149:9000/myInfo/' + myNick + '',
         type: 'GET',
         success: function (data) {
             var userObj = data;

             if (userObj.userId == myNick) {
                 $("#my_id").text(userObj.userId);
                 $("#my_point_total").text(userObj.pointTotal);
                 $("#my_left_chance").text(userObj.userLeftChance);
                 $(".totalPoint").text(userObj.pointTotal);
             }
         },
         error: function (request, status, error) {
             alert('db서버 연결 오류');
         }
     });

     $.ajax({
         url: 'http://192.168.0.149:9000/buyInfo/' + myNick + '',
         type: 'GET',
         success: function (data) {

             for (var index = 0; index < 5; index++) {

                 var buyObj = data[index];

                 var unixtime = buyObj.buyDateTime;
                 var datetime = new Date(unixtime).toISOString().substring(0, 10);
                 
                 var templete = '<tr>' +
                     '<td>' +
                     datetime +
                     '</td>' +
                     '<td>' +
                     buyObj.buyGoodsName +
                     '</td>' +
                     '<td style="text-align=right">' +
                     buyObj.buyPoint + "P" +
                     '</td>' +
                     '</tr>';
                 $('#buyList').append(templete);
             }
         },
         error: function (request, status, error) {
             alert('게시글 목록을 가져오기에 실패하였습니다.');
         }
     });

 });
