document.querySelector('#logout').onclick = function() {
    var logout = confirm("로그아웃하시겠습니까?");
        
    if(logout){
        alert("로그아웃되었습니다.");
        localStorage.clear();
        location.href = "login.html";    
    } else{
         return;
    }
};
