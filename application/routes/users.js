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
        res.render("dashboard",{"title":"Welcome Home"+ req.cookies.userName,"name": req.cookies.userName});
        return ;
    }

    if (req.query.error) {
        return res.redirect("/");
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
            console.log("in dashboard")
            return res.redirect("/");
        }
        var token = data.access_token;
        backEndClient.performGetUserInfoRequest('http://localhost:8080/api/users', token,
            function (error, response, body){
                res.cookie('accessToken',token,{secure: false });
                res.cookie('userName',body.display_name,{secure: false });
                res.cookie('uid',body.uid,{secure: false });
                res.redirect("/users/dashboard");

            }
        );
    });
   });

router.get('/logout', function(req,res){
    console.log('in logout');
    res.clearCookie('accessToken',{path:'/'});
    res.clearCookie('userName',{path:'/'});
    res.clearCookie('uid',{path:'/'});
    res.redirect("/");
})

router.get('/auth',function(req,res){
  client.authenticateUser(req,res);
})

module.exports = router;
