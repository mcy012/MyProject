$(document).ready(function () {

    $.support.cors = true;

    var seq = $(location).attr('search').slice($(location).attr('search').indexOf('=') + 1);

    $.ajax({
        url: 'http://192.168.0.149:9000/goodsList/' + seq + '',
        type: 'GET',
        success: function (data) {

            var goodsObj = data;

            $("#image").attr("src", goodsObj.goodsImage);
            $(".goodName").text(goodsObj.goodsName);
            $("#goodPrice").text(goodsObj.goodsPrice);


            var allPrice = $("#goodPrice").text() * $("#count").val();

            $(".totalPrice").text(allPrice);

            $(".upDown").click(function () {
                var allPrice = $("#goodPrice").text() * $("#count").val();

                $(".totalPrice").text(allPrice);

                $("#change").load("gogogo.html");
            })
        },

        error: function (request, status, error) {
            alert('물품정보를 가져오는데 실패하였습니다.');
        }
    });
});