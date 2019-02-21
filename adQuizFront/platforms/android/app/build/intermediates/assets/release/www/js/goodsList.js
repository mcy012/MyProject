 $(document).ready(function() {

    $.support.cors = true;

        $.ajax({
            url: 'http://192.168.0.149:9000/goodsList',
            type: 'GET',
            success: function(data) {
                for(var seq = 0; seq < data.length; seq++) {
                    var goods = data[seq];
                    $("#item"+ Number(seq+1) + "").text(goods.goodsPrice);
                    $(".type02").attr("readonly",true);
               }
            },

            error: function(request, status, error) {
                alert('물품정보를 가져오는데 실패하였습니다.');
            }
        });
 });
