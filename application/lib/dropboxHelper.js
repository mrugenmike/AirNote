var crypto = require('crypto');
var url = require('url');
var request = require("request");

//var app = require("../app")
var APP_KEY="nm2wxgh9jqaspx0";




var APP_SECRET = "off9wuy78rttsub";

var dropBoxClient = {
    generateCSRFToken: function(){
        return crypto.randomBytes(18).toString('base64')
            .replace(/\//g, '-').replace(/\+/g, '_');
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
                response_type: 'code',
                state: csrfToken,
                redirect_uri: this.generateRedirectURI(req)
            }
        }));
    },
    generateRedirectURI: function(req){
        return url.format({
            protocol: req.protocol,
            host: req.headers.host,
            pathname: '/users/dashboard'
        });
    },
    getToken:function(req,res ,callback){
        request.post('https://api.dropbox.com/1/oauth2/token', {
            form: {
                code: req.query.code,
                grant_type: 'authorization_code',
                redirect_uri: this.generateRedirectURI(req)
            },
            auth: {
                user: APP_KEY,
                pass: APP_SECRET
            }
        },callback);
    }
};



module.exports = dropBoxClient