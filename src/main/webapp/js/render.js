/**
 * render survey by template "SURVEY_TPL" Mustache-min.js
 * @param questionsJSON JSON array with questions [{type: , question: , id: }, {}, {}]
 * @param surveyDate date of the survey (actually - survey id)
 * @returns {*}
 */
function renderSurvey(questionsJSON, surveyDate) {
    let $surveyHead = $('#surveyHead');
    let $surveyForm = $('#surveyForm');

    $surveyHead.attr("data-survey-date", surveyDate);
    $surveyHead.html("Survey date: " + surveyDate);

    $surveyForm.empty();
    $.each(questionsJSON, function (index, questionObj) {
        let html;
        if(questionObj.type === "RADIO") { // q.type - question type
            html = Mustache.render(RADIO_QUESTION_TPL, questionObj);
            $surveyForm.append(html);
            // if question already has answer - set this answer on page
            if(questionObj.hasOwnProperty("answer")){
                let inputName = "q_" + questionObj.id;
                let inputValue = questionObj.answer;
                $('input[name="' + inputName + '"][value="'+ inputValue + '"]').prop('checked', true)
            }
        } else if (questionObj.type === "TEXT") {
            html = Mustache.render(TEXT_QUESTION_TPL, questionObj);
            $surveyForm.append(html);
            if(questionObj.hasOwnProperty("answer")){
                let inputName = "q_" + questionObj.id;
                $('textarea[name="' + inputName + '"]').val(questionObj.answer);
            }
        }
    })
    // jQuery enhances standard checkbox
    $( '[type=radio]' ).checkboxradio({
        icon: false
    });
    // show submit btn
    $('#submitAnswerBtn').show();
}

// radio survey html template for Mustache
const RADIO_QUESTION_TPL =  '<div>' +
                                '<fieldset>' +
                                    '<legend>question</legend>' +
                                    '<h2 align="center">{{question}}</h2>' +
                                    '<div align="center">' +
                                        '<label for="q{{id}}_1">1</label>' +
                                        '<input id="q{{id}}_1" type="radio" name="q_{{id}}" value="1" required>' +
                                        '<label for="q{{id}}_2">2</label>' +
                                        '<input id="q{{id}}_2" type="radio" name="q_{{id}}" value="2" required>' +
                                        '<label for="q{{id}}_3">3</label>' +
                                        '<input id="q{{id}}_3" type="radio" name="q_{{id}}" value="3" required>' +
                                        '<label for="q{{id}}_4">4</label>' +
                                        '<input id="q{{id}}_4" type="radio" name="q_{{id}}" value="4" required>' +
                                        '<label for="q{{id}}_5">5</label>' +
                                        '<input id="q{{id}}_5" type="radio" name="q_{{id}}" value="5" required>' +
                                        '<label for="q{{id}}_6">6</label>' +
                                        '<input id="q{{id}}_6" type="radio" name="q_{{id}}" value="6" required>' +
                                        '<label for="q{{id}}_7">7</label>' +
                                        '<input id="q{{id}}_7" type="radio" name="q_{{id}}" value="7" required>' +
                                        '<label for="q{{id}}_8">8</label>' +
                                        '<input id="q{{id}}_8" type="radio" name="q_{{id}}" value="8" required>' +
                                        '<label for="q{{id}}_9">9</label>' +
                                        '<input id="q{{id}}_9" type="radio" name="q_{{id}}" value="9" required>' +
                                        '<label for="q{{id}}_10">10</label>' +
                                        '<input id="q{{id}}_10" type="radio" name="q_{{id}}" value="10" required>' +
                                    '</div>' +
                                '</fieldset>' +
                            '</div>';

// text survey html template for Mustache
const TEXT_QUESTION_TPL =   '<div class="question">' +
                                '<fieldset>' +
                                    '<legend>question</legend>' +
                                    '<h2 align="center">{{question}}</h2>' +
                                    '<div align="center">' +
                                        '<textarea placeholder="Enter your answer" name="q_{{id}}" required></textarea>' +
                                    '</div>' +
                                '</fieldset>' +
                            '</div>';