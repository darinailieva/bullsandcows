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
