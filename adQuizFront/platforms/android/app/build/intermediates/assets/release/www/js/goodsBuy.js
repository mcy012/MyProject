$(document).ready(function() {

    $.support.cors = true;

    $(".secondary").click(function() {

        var goodsSeq = $(this).data("buy");

        if(Number($("#my_point_total").text()) >= Number($("#item"+ goodsSeq +"").text()) )
        {
            var buyQue = confirm("교환하시겠습니까?");

            if(buyQue == true) {

                $.ajax({                                                                            //내 포인트랑 상품 교환 재고 깎는거
                    type: 'PUT',
                    url: 'http://192.168.0.149:9000/goodsStockBuy/'+ goodsSeq +'',
                    success:function(data){
                        alert("교환이 완료되었습니다.");
                        location.href = "./mainRoom.html";
                    },

                    error: function(request, status, error) {
                        alert("error");
                    }
                })

                var data = {};

                data['pointTotal'] =  Number($("#my_point_total").text()) - Number($("#item"+ goodsSeq +"").text());

                var jsonData = {pointTotal:data['pointTotal'], pointUpDown:"-" + Number($("#item"+ goodsSeq +"").text())};

                var pointId = localStorage.getItem('id');

                $.ajax({
                    type: 'PUT',
                    url: 'http://192.168.0.149:9000/pointUpdate/' + pointId + '',                       //포인트랑 상품이랑 교환됐을 때 내 포인트를 상품가격만큼 깎기
                    data: JSON.stringify(jsonData),
                    contentType: 'application/json',
                    success:function(response){
                    },
                    error: function(request, status, error) {
                        alert("에러에러");
                        return;
                    }
                })

                var buyData = {buyUserId: pointId, goodsSeq:goodsSeq};

                $.ajax({
                    type: 'POST',
                    url: 'http://192.168.0.149:9000/buyInfo',
                    data: JSON.stringify(buyData),
                    contentType: 'application/json',
                    success:function(data) {
                    },
                    error:function(request, status, error) {
                        alert('정보에러');
                        return;
                    }
                })
            }
            else{
                return;
            }
        }
        else{
            alert("포인트가 부족합니다.");
        }



    })
})

