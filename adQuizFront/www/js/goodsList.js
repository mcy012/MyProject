 $(document).ready(function() {

    $.support.cors = true;

        $.ajax({
            url: 'http://192.168.0.149:9000/goodsList',
            type: 'GET',
            success: function(data) {
                var goodsSeq = $(this).data("buy");

                for(var seq = 0; seq < data.length; seq++) {
                    var goods = data[seq];
                    $(".itemPrice"+ Number(seq+1) + "").text(goods.goodsPrice);
                    $(".itemName"+ Number(seq+1) + "").text(goods.goodsName);
                    $(".type02").attr("readonly",true);
               }
            },

            error: function(request, status, error) {
                alert('물품정보를 가져오는데 실패하였습니다.');
            }
        });
     
     
    $(".itemImage").click(function () {

        var goodsSeq = $(this).data("buy");

        location.href = "detail.html?seq=" + goodsSeq;
    })
 });
