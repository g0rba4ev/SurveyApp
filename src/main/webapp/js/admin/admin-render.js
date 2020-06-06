/**
 * render users table by template "USERS_TABLE_TPL" using Mustache-min.js
 * @param usersJSON JSON array with users like [{login: , role:}, {}, {}]
 * @returns rendered html
 */
function renderUsersTable(usersJSON) {
    return  Mustache.render(USERS_TABLE_TPL, usersJSON);
}

// users table template for Mustache
const USERS_TABLE_TPL = '<tr>' +
                            '<th>User login</th>' +
                            '<th>Role</th>' +
                        '</tr>' +
                        '{{#.}}' +
                        '<tr>\n' +
                            '<td>{{login}}</td>\n' +
                            '<td>{{role}}</td>\n' +
                        '</tr>' +
                        '{{/.}}';

