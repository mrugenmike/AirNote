var express = require('express'),
    app = express();
    router = express.Router(),
    client = require("../lib/dropboxHelper"),
    request = require('request'),
    url = require('url'),
    backEndClient = require("../lib/backEndHelper"),
    notesClient = require("../lib/notesClient.js"),
    session = require('express-session'),
    cookieParser = require('cookie-parser');

    cookieParser('mycookie');
//app.use(session({secret:'mynote' , resave:true, saveUninitialized:true }));
 /* GET users listing. */
router.get('/', function(req, res) {
  res.send('respond with a resource');
});

router.get('/dashboard', function(req, res) {

    if (client.isAuthenticated(req,res) )
    {
        res.render("dashboard",{"title":"Welcome Home"+ req.cookies.userName,"name": body.display_name});
        return ;
    }

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
        var token = data.access_token;
        backEndClient.performGetUserInfoRequest('http://localhost:8080/api/users', token,
            function (error, response, body){
                res.cookie('accessToken',token,{secure: true });
                res.cookie('userName',body.display_name,{secure: true });
                res.cookie('uid',body.uid,{secure: true });
                res.render("dashboard",{"title":"Welcome Home Chap!", "name": body.display_name});
            }
        );
    });
   });

router.get('/auth',function(req,res){
  client.authenticateUser(req,res);
})

module.exports = router;
