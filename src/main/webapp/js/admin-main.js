// on page load
$(function () {
    // initialize jQuery tabs
    $('#tabs').tabs();
    // tabs with class="tab-link" become actually links
    $("li.tab-link a").unbind('click');
    // initialize jQuery button
    $('#logoutBtn').button();
});