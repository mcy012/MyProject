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

    $("#gift").click(function () {

        var to = Number($("#my_point_total").text());
        var pr = Number($("#TOTO").text());

        $("#leftPoint").text(to - pr);
    });

    $("#choice").click(function () {
        if (Number($("#leftPoint").text()) < 0) {
            alert("포인트가 부족합니다.");
            return;
        } else {
            var gift = confirm("선물 하시겠습니까?");

            if (gift) {
                $.ajax({ //내 포인트랑 상품 교환 재고 깎는거
                    type: 'PUT',
                    url: 'http://192.168.0.149:9000/goodsStockBuy/' + seq + '',
                    success: function (data) {
                        alert("선물상품 쿠폰이 전송되었습니다.");
                        location.href = "goods.html";
                    },

                    error: function (request, status, error) {
                        alert("error");
                    }
                });

                var data = {};

                data['pointTotal'] = Number($("#leftPoint").text());

                var jsonData = {
                    pointTotal: data['pointTotal'],
                    pointUpDown: "-" + Number($("#TOTO").text())
                };

                var pointId = localStorage.getItem('nickname');

                $.ajax({
                    type: 'PUT',
                    url: 'http://192.168.0.149:9000/pointUpdate/' + pointId + '', //포인트랑 상품이랑 교환됐을 때 내 포인트를 상품가격만큼 깎기
                    data: JSON.stringify(jsonData),
                    contentType: 'application/json',
                    success: function (response) {},
                    error: function (request, status, error) {
                        alert("에러에러");
                        return;
                    }
                })
            
                var buyData = {
                    buyUserId: pointId,
                    buyGoodsName: $("#buyName").text(),
                    buyPoint: Number($("#TOTO").text())
                };

                $.ajax({
                    type: 'POST',
                    url: 'http://192.168.0.149:9000/buyInfo',
                    data: JSON.stringify(buyData),
                    contentType: 'application/json',
                    success: function (data) {},
                    error: function (request, status, error) {
                        alert('정보에러');
                        return;
                    }
                })

            } else {
                return;
            }
        }
    });









    /*
    $("#buy").click(function () {
        location.href = "order.html";

        var nick = localStorage.getItem("nickname");
        var name = $("#goodName").text();
        var price = $("#goodPrice").text();
        var img = $("#image").attr("src");
        var count = $("#count").val();

        var chargeData = {
            chargeNick: nick,
            chargeName: name,
            chargePrice: price,
            chargeImg: img,
            chargeCount: count
        }

        $.ajax({
            type: 'POST',
            url: 'http://192.168.0.149:9000/chargeInfo',
            data: JSON.stringify(chargeData),
            contentType: 'application/json',
            success: function (data) {},
            error: function (request, status, error) {
                alert('정보에러');
                return;
            }
        })
    });

    $("#basket").click(function () {

        alert("장바구니에 추가하였습니다.");
        
        location.href = "basket.html";

        var nick = localStorage.getItem("nickname");
        var name = $("#goodName").text();
        var price = $("#goodPrice").text();
        var img = $("#image").attr("src");
        var count = $("#count").val();

        var basketData = {
            basketNick: nick,
            basketName: name,
            basketPrice: price,
            basketImg: img,
            basketCount: count
        }

        $.ajax({
            type: 'POST',
            url: 'http://192.168.0.149:9000/basketInfo',
            data: JSON.stringify(basketData),
            contentType: 'application/json',
            success: function (data) {},
            error: function (request, status, error) {
                alert('정보에러');
                return;
            }
        })
    })
 */
});
