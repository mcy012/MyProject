$(document).ready(function () {

    $.support.cors = true;

    $(".itemImage").click(function () {
        
        var goodsSeq = $(this).data("buy");
        
        location.href = "detail.html?seq=" + goodsSeq;

        $.ajax({
            url: 'http://192.168.0.149:9000/goodsList/' + goodsSeq + '',
            type: 'GET',
            success: function (data) {

                var goodsObj = data;

                $("#image").text(goodsObj.goodsImage);
                $("#goodName").text(goodsObj.goodsName);
                $("#goodPrice").text(goodsObj.goodsPrice);
            
            },

            error: function (request, status, error) {
                alert('물품정보를 가져오는데 실패하였습니다.');
            }
        });
    })

});
