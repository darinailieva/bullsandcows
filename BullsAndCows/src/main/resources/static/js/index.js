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

function showUserGames() {
  let games;
  $(document).ready(function () {
    games = $("#games").DataTable({
      searching: false,
      ordering: false,
      info: false,
      paging: false,
      ajax: {
        url: "/games",
        method: "GET",
        dataSrc: "",
      },
      columns: [
        { data: "gameId" },
        { data: "timesPlayed" },
        { data: "createdDate" },
        { data: "lastModifiedDate" },
        {
          data: "finished",
          render: function (data, type, row, meta) {
            if (data === true) {
              return "<input type='button' class='badge-gradient-success' value='View'  id='viewBtn' />";
            } else {
              return "<input type='button' class='badge-gradient-warning' value='Continue' id='viewBtn' />";
            }
          },
        },
      ],
    });
  });
}

$("#games").on("click", "#viewBtn", function (e) {
  e.stopPropagation();
  const games = $("#games").DataTable();
  let gameId = games.row($(this).closest("tr")).data().gameId;
  showGame(gameId);
});

function showGame(gameId) {
  location.href = "/games/" + gameId;
}
