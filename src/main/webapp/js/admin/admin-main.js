// on page load
$(function () {
    // initialize jQuery tabs
    $('#tabs').tabs();
    // tabs with class="tab-link" become actually links
    $("li.tab-link a").unbind('click');
    // initialize jQuery button
    $('#logoutBtn').button();
});

/* USER MANAGEMENT */

// add new user
$(document).on('click', '#add-new-user', function () {
    let $newLogin = $('#new-login');
    let $newPassword = $('#new-pwd');
    let $newRole = $('#new-role');
    let password = $newPassword.val();
    if(password === ""){
        alert("Password field is empty");
        return;
    }
    $.ajax({
        url : "./admin/addNewUser",
        method: "POST",
        data: {
            login: $newLogin.val(),
            password: CryptoJS.SHA256(password).toString(CryptoJS.enc.Hex),
            role: $newRole.val()
        },
        statusCode: {
            200: function(respJSON, textStatus, jqXHR) {
                alert( jqXHR.getResponseHeader("Message") );
                $newLogin.val("");
                $newPassword.val("");
            },
            400: function(jqXHR) {
                alert( "Error: " + jqXHR.getResponseHeader("Message") );
            }
        }
    });
});

// change user password
$(document).on('click', '#change-user-pwd', function () {
    let $login = $('#login');
    let $newPassword = $('#pwd');
    let password = $newPassword.val();
    if(password === ""){
        alert("Password field is empty");
        return;
    }
    $.ajax({
        url : "./admin/changeUserPwd",
        method: "POST",
        data: {
            login: $login.val(),
            password: CryptoJS.SHA256(password).toString(CryptoJS.enc.Hex),
        },
        statusCode: {
            200: function(respJSON, textStatus, jqXHR) {
                alert( jqXHR.getResponseHeader("Message") );
                $login.val("");
                $newPassword.val("");
            },
            400: function(jqXHR) {
                alert( "Error: " + jqXHR.getResponseHeader("Message") );
            }
        }
    });
});
