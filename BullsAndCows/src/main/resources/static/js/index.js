function startNewGame() {
    var fields = document.getElementById("welcome").innerText.split(" ");
    var username = fields[5];

    $.ajax({
        method: "POST",
        url: 'http://localhost:8080/game',
        contentType: "application/json",
        data: username,
        success: function (data) {
            successStartGame(data);
        }
    });
}

function successStartGame(data) {
    location.href = '/game/' + data;
}

function dashboard() {
    location.href = "/";
}

function register() {
    location.href = "/register";
}

function login() {
    location.href = "/login";
}

function rules() {
    location.href = "/rules";
}

function getUserGames() {
    $.ajax({
        url: "/games",
        method: "GET",
        dataType: "json",
        success: function (data) {
            var tableBody = $('#games tbody');
            tableBody.empty();
                $(data).each(function (index, game){
                    tableBody.append('<tr><td>'+game.gameId + '</td><td>'+game.timesPlayed+'</td><td>'+game.createdDate+'</td><td>'+game.lastModifiedDate);
                    // if(game.isFinished() === true){
                    //     tableBody.append(+'</td><td>'+'<td><a class="badge badge-gradient-success" onclick="getGame(game.gameId)">View</a></td>');
                    // }else{
                    //     tableBody.append(+'</td><td>'+'<td><a class="badge badge-gradient-warning" onclick="getGame(game.gameId)">Continue</a></td>');
                    // }
            })
        }
    })
}
function getGame(gameId) {
    location.href = '/game/' + gameId;
}
