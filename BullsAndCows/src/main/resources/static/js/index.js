function startNewGame() {
  const fields = document.getElementById("welcome").innerText.split(" ");
  const username = fields[5];

  $.ajax({
    method: "POST",
    url: "http://localhost:8080/game",
    contentType: "application/json",
    data: username,
    success: function (data) {
      successStartGame(data);
    },
  });
}

function successStartGame(data) {
  location.href = "/games/" + data;
}

function showDashboard() {
  let users;
  $(document).ready(function () {
    users = $("#users").DataTable({
      searching: false,
      ordering: false,
      info: false,
      paging: false,
      ajax: {
        url: "/dashboard",
        method: "GET",
        dataSrc: "",
      },
      columns: [
        { data: "username" },
        { data: "numberOfFinishedGames" },
        { data: "bestTimesPlayed" },
        { data: "bestTimeInMinutes" },
      ],
    });
  });
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

function getUserGames(){
  location.href = "/my-games"
}