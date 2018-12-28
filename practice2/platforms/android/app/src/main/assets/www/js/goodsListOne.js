 $(document).ready(function() {

    $.support.cors = true;

        $.ajax({
            url: 'http://192.168.0.149:9000/goodsList/'+goodsSeq+'',
            type: 'GET',
            success: function(data) {
                var goodsSeq = $(this).data("buy");

                $("#itemPrice"+ Number(seq+1) + "").text(goods.goodsPrice);
                $("#itemName"+ Number(seq+1) + "").text(goods.goodsName);
            },

            error: function(request, status, error) {
                alert('물품정보를 가져오는데 실패하였습니다.');
            }
        });
 });
