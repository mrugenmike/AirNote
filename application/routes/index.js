var express = require('express');
var router = express.Router();
var client = require("../lib/dropboxHelper");


/* GET home page. */
router.get('/', function(req, res) {
  console.log("request came from: "+req.userAgent)
  if(client.isAuthenticated(req,res)){
       res.render("dashboard",{"title":"Dashboard"});
  }else{
      console.log("Not authenticated")
       res.render("login",{"flash":"Kindly Login to continue","title":"Login Page"});
  }

});

module.exports = router;
