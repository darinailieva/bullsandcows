$(document).ready(function () {
    $('#registration').submit(function (e) {
        e.preventDefault();
       register();
    });
});

function register() {
    var user = {}
    user["username"] = $("#username").val();
    user["password"] = $("#password").val();

    $.ajax({
        method: "POST",
        url: "/register",
        contentType: "application/json",
        data: JSON.stringify(user),
        success: function () {
            successRegister();
        },
        error: function (error){
        $('.error').empty();
        $('.error').append(error.responseJSON.message);
        }
    });
}

function successRegister() {
    location.href = "/login";
}