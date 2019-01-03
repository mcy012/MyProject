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
 });
