$(document).ready(function () {

    $("#start").click(function () {
        if (localStorage.getItem("nickname") == null) {
            location.href = "./nickname.html";
        } else {
            location.href = "./mainRoom.html";
        }
    })
})
