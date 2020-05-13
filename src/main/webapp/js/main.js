// on page load
$(function(){
    // apply jQuery styles
    $( '#submitAnswerBtn' ).button().hide();
    $( '#updateAnswerBtn' ).button().hide();

    getSurvey();
});

/**
 * get and render today's survey
 */
function getSurvey() {
    $.ajax({
        url : "./getSurvey",
        success : function (respJSON, textStatus, jqXHR) {
            $('#surveyDate').val( jqXHR.getResponseHeader("surveyDate") );
            let $userName = $('#userName');
            $userName.val( jqXHR.getResponseHeader("userName") );
            if(respJSON[0].hasOwnProperty("answer")) {
                $( '#submitAnswerBtn' ).hide();
                $( '#updateAnswerBtn' ).show();
                $userName.attr("readonly", true);
            } else {
                $( '#submitAnswerBtn' ).show();
                $( '#updateAnswerBtn' ).hide();
                $userName.attr("readonly", false);
            }
            renderSurvey(respJSON);
        }
    });
}

// submit answer
$(document).on('click', '#submitAnswerBtn', function () {
    let $surveyForm = $('#surveyForm');
    $.ajax({
        url : "./postAnswer",
        method: "POST",
        data: $surveyForm.serialize(),
        headers: {
            "surveyDate": $('#surveyDate').val(),
            "userName": $('#userName').val()
        },
        statusCode: {
            200: function(respJSON, textStatus, jqXHR) {
                alert( jqXHR.getResponseHeader("Message") ); // "Answer accepted successfully"
                getSurvey();
            },
            400: function(jqXHR) {
                alert( "Error: " + jqXHR.getResponseHeader("Message") );
            }
        }
    });
});

// update answer
$(document).on('click', '#updateAnswerBtn', function () {
    let $surveyForm = $('#surveyForm');
    $.ajax({
        url : "./updAnswer",
        method: "POST",
        data: $surveyForm.serialize(),
        headers: {
            "surveyDate": $('#surveyDate').val(),
            "userName": $('#userName').val()
        },
        statusCode: {
            200: function(respJSON, textStatus, jqXHR) {
                alert( jqXHR.getResponseHeader("Message") ); // "Answer updated successfully"
                getSurvey();
            },
            400: function(jqXHR) {
                alert( "Error: " + jqXHR.getResponseHeader("Message") );
            }
        }
    });
});