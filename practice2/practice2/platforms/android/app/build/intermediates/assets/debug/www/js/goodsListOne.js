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
            $(".goodPrice").text(goodsObj.goodsPrice);
            $("#goodTotalPrice").text(goodsObj.goodsPrice);
        },

        error: function (request, status, error) {
            alert('물품정보를 가져오는데 실패하였습니다.');
        }
    });

    $("#buy").click(function () {
        location.href = "order.html";

        var nick = localStorage.getItem("nickname");
        var name = $(".goodName").text();
        var price = $(".goodPrice").text();
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
        var name = $(".goodName").text();
        var price = $(".goodPrice").text();
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
    
    $("#back").click(function(){
        location.href = "goods.html"
    })
});
