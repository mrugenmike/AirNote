var express = require('express'),
    router = express.Router(),
    client = require("../lib/dropboxHelper"),
    request = require('request'),
    url = require('url');

var APP_KEY="nm2wxgh9jqaspx0";






/* GET users listing. */
router.get('/', function(req, res) {
  res.send('respond with a resource');
});

router.get('/dashboard', function(req, res) {

    if (req.query.error) {
        return res.send('ERROR ' + req.query.error + ': ' + req.query.error_description);
    }

    // check CSRF token
    if (req.query.state !== req.cookies.csrf) {
        return res.status(401).send(
            'CSRF token mismatch, possible cross-site request forgery attempt.'
        );
    }


    client.getToken(req,res, function (error, response, body) {
        var data = JSON.parse(body);

        if (data.error) {
            return res.send('ERROR: ' + data.error);
        }

        console.log(data);
        // extract bearer token
        var token = data.access_token;
        console.log(token);
        // use the bearer token to make API calls
        request.get('https://api.dropbox.com/1/account/info', {
            headers: { Authorization: 'Bearer ' + token }
        }, function (error, response, body) {
            console.log(JSON.parse(body));
            //res.send('Logged in successfully as ' + JSON.parse(body).display_name + '.');
            res.render("dashboard",{"title":"Welcome Home Chap!", "name": JSON.parse(body).display_name});
        });
    });

   });

router.get('/auth',function(req,res){
  client.authenticateUser(req,res);
})

module.exports = router;
