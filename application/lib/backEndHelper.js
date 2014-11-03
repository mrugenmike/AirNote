var querystring = require('querystring');
var http = require('http');
var request = require('request');
var host = 'localhost';
var port = 8080;
var endpoint = 'api/users';

var backEndClient = {
    performGetUserInfoRequest: function(endpoint, code, callback) {
        request.get(endpoint, {
            headers: { Authorization: "Bearer "+code },
            json: true
        }, callback);
}
}

module.exports = backEndClient