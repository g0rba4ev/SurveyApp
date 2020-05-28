// on page load
$(function(){
    $('#login_form').submit(function(e) {
        e.preventDefault();
        let login = $('#login').val();
        let pwd = $('#pwd').val();
        let pwdEncrypted = CryptoJS.SHA256(pwd).toString(CryptoJS.enc.Hex);

        let form = $('<form></form>');

        form.attr("method", "post");
        form.attr("action", ""); // to current page

        let loginField = $('<input></input>');
        loginField.attr("type", "hidden");
        loginField.attr("name", "login");
        loginField.attr("value", login);
        form.append(loginField);

        let pwdField = $('<input></input>');
        pwdField.attr("type", "hidden");
        pwdField.attr("name", "password");
        pwdField.attr("value", pwdEncrypted);
        form.append(pwdField);

        // The form needs to be a part of the document in
        // order for us to be able to submit it.
        $(document.body).append(form);
        form.submit();
    });
});