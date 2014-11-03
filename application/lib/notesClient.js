var express = require('express'),
    router = express.Router(),
    client = require("../lib/dropboxHelper"),
    request = require('request'),
    url = require('url'),
    backEndClient = require("../lib/backEndHelper");

var notesClient = {
    createNotesRequest: function(endpoint, code, uid, callback) {
        request.post(endpoint, {
            headers: { Authorization: code },
            json: true,
            form: {
                "title": "Vab.txt",
                "content": "This is test data",
                "userId": uid
            }
        }, callback);
    }
}

module.exports = notesClient