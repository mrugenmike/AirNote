var crypto = require('crypto');
var url = require('url')

//var app = require("../app")
var APP_KEY="nm2wxgh9jqaspx0";




var APP_SECRET = '<YOUR APP SECRET>';

var dropBoxClient = {
    generateCSRFToken: function(){
        return crypto.randomBytes(18).toString('base64')
            .replace(/\//g, '-').replace(/\+/g, '_');
    },
    generateRedirectURI: function(req){
        return url.format({
            protocol: req.protocol,
            host: req.headers.host,
            pathname:'http://localhost:3000/'
        });
    },
    isAuthenticated: function(req,res){
  return false;
    },
    authenticateUser: function(req,res){
        var csrfToken = this.generateCSRFToken();
        res.cookie('csrf', csrfToken);
        res.redirect(url.format({
            protocol: 'https',
            hostname: 'www.dropbox.com',
            pathname: '1/oauth2/authorize',
            query: {
                client_id: APP_KEY,
                response_type: 'token',
                state: csrfToken,
                redirect_uri: "http://localhost:3000/users/dashboard"
            }
        }));
    }
};



module.exports = dropBoxClient