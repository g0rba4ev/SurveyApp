// on page load
$(function(){
    getSurvey();
    //if survey wasn't
    $( '#submitAnswerBtn' ).button().hide();
});

/**
 *
 */
function getSurvey() {
    $.ajax({
        url : "./getSurvey",
        success : function (respJSON, textStatus, jqXHR) {
            let surveyDate = jqXHR.getResponseHeader("surveyDate");
            renderSurvey(respJSON, surveyDate);
        }
    });
};

$(document).on('click', '#submitAnswerBtn', function () {
    let $surveyForm = $('#surveyForm');
    let $surveyHead = $('#surveyHead');
    $.ajax({
        url : "./postAnswer",
        method: "POST",
        data: $surveyForm.serialize(),
        headers: {
            "surveyDate": $surveyHead.attr("data-survey-date")
        },
        success : function (respJSON, textStatus, jqXHR) {
            $surveyHead.empty();
            $surveyForm.empty();
            $( '#submitAnswerBtn' ).hide();
            alert("Answer accepted successfully")
        }
    });
});