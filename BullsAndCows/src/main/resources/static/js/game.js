const gameId = location.pathname.split("/")[2];
$("#gameId").append("Game # " + gameId);

$(document).ready(function () {
  $.ajax({
    method: "GET",
    url: "http://localhost:8080/game/" + gameId,
    contentType: "application/json",
    success: function (response) {
      successShowGame(response);
    },
  });
});

function successShowGame(response) {
  if (response.finished === true) {
    document.getElementById("notFinished").style.visibility = "hidden";
  } else {
    document.getElementById("notFinished").style.visibility = "visible";
    $(document).ready(function () {
      $("#attempt").submit(function (e) {
        e.preventDefault();
        guessNumber();
      });
    });
  }
  response.attempts.forEach((attempt) => {
    $("#game tbody").append(
      "<tr>" +
        "<td>" +
        attempt.bulls +
        "</td>" +
        "<td>" +
        attempt.cows +
        "</td>" +
        "<td>" +
        attempt.currentNumber +
        "</td>" +
        "</tr>"
    );
  });
}

function guessNumber() {
  $(".error").empty();
  var attempt = {};
  attempt["currentNumber"] = $("#currentNumber").val();
  console.log(attempt);
  $.ajax({
    method: "PUT",
    url: "http://localhost:8080/game/" + gameId,
    contentType: "application/json",
    data: JSON.stringify(attempt),
    success: function (data) {
      console.log(data.finished);
      if (data.finished === true) {
        showCongratulation();
      } else {
        showGame();
      }
    },
    error: function (error) {
      $(".error").append(error.responseJSON.message);
    },
  });
}

function showGame() {
  location.href = "/games/" + gameId;
}

function showCongratulation() {
  location.href = "/congratulation";
}
