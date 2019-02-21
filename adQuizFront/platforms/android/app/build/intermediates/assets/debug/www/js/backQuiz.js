document.addEventListener("backbutton", onBackKeyDown, false);  
function onBackKeyDown(e) { 
   e.preventDefault(); 
   var out = confirm("나가시면 사용된 기회가 사라집니다.\n그래도 나가시겠습니까?"); 
    
    if(out) {
        location.href = "mainRoom.html";
    } else {
        return;
    }
}