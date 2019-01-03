 $(document).ready(function () {

     $.support.cors = true;

     var chargeNick = localStorage.getItem('nickname');

     $.ajax({
         url: 'http://192.168.0.149:9000/chargeList/' + chargeNick + '',
         type: 'GET',
         success: function (data) {
             let totalPrice = 0;

             for (var index = 0; index < data.length; index++) {

                 var chargeObj = data[index];

                 var templateStr = document.getElementById('template-product').innerHTML;
                 templateStr = templateStr.replace('{{productImage}}', chargeObj.chargeImg)
                     .replace('{{name}}', chargeObj.chargeName)
                     .replace('{{price}}', chargeObj.chargePrice)
                     .replace('{{count}}', chargeObj.chargeCount);

                 $('#charge_table').append(templateStr);

                 totalPrice += chargeObj.chargePrice;

             }

             $("#totalPrice").text(totalPrice);
             $("#totalCharge").text(totalPrice);
         },
         error: function (request, status, error) {
             alert('서버와 통신 중 오류가 발생하였습니다.');
         }
     });
 });
