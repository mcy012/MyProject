$("#submitEvent").click(function(){
    if($("#answerEvent").val() == "공유"){
        alert("정답입니다.!\n상품이 지급됩니다.");
        location.href = "event.html";
    } else {
        alert("오답입니다.");
        location.href = "event.html";
    }
})