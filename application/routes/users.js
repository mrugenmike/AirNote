var express = require('express');
var router = express.Router();
var client = require("../lib/dropboxHelper");

/* GET users listing. */
router.get('/', function(req, res) {
  res.send('respond with a resource');
});

router.get('/dashboard', function(req, res) {
  res.render("dashboard",{"title":"Welcome Home Chap!"});

});

router.get('/auth',function(req,res){
  client.authenticateUser(req,res);
})

module.exports = router;
