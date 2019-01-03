 $(document).ready(function () {

     $.support.cors = true;

     var basketNick = localStorage.getItem('nickname');

     $.ajax({
         url: 'http://192.168.0.149:9000/basketList/' + basketNick + '',
         type: 'GET',
         success: function (data) {
             let totalPrice = 0;

             for (var index = 0; index < data.length; index++) {

                 var basketObj = data[index];

                 var templateStr = document.getElementById('template-product').innerHTML;
                 templateStr = templateStr.replace('{{productImage}}', basketObj.basketImg)
                     .replace('{{name}}', basketObj.basketName)
                     .replace('{{price}}', basketObj.basketPrice)
                     .replace('{{count}}', basketObj.basketCount);

                 $('#basket_table').append(templateStr);

                 totalPrice += basketObj.basketPrice;
             }
             $("#totalPrice").text(totalPrice);
         },
         error: function (request, status, error) {
             alert('서버와 통신 중 오류가 발생하였습니다.');
         }
     });

     $("#deleteAll").click(function () {

         var del = confirm("모두 삭제 하겠습니까?");

         if (del) {

             $.ajax({
                 url: 'http://192.168.0.149:9000/deleteBasket/' + basketNick + '',
                 type: 'DELETE',
                 contentType: 'application/json; charset=utf-8',
                 data: JSON.stringify(basketNick),

                 success: function (data) {
                     location.reload();
                 },
                 error: function (request, status, error) {
                     alert('삭제에 실패하였습니다.');
                 }
             });
         } else{
             return;
         }
     })
 });
