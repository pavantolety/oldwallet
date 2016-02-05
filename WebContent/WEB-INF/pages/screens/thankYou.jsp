<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<!--[if lt IE 7]><html lang="en" class="no-js ie6"><![endif]-->
<!--[if IE 7]><html lang="en" class="no-js ie7"><![endif]-->
<!--[if IE 8]><html lang="en" class="no-js ie8"><![endif]-->
<!--[if gt IE 8]><!-->
<html lang="en" class="full">
<!--<![endif]-->

<head>
    <meta charset="UTF-8">
    <title>PayPal-Oldwallet</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
 	<!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
	 <link rel="stylesheet" href="assets/css/animate.css">
    <link rel="stylesheet" href="assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="assets/css/slick.css">
    <link rel="stylesheet" href="assets/js/rs-plugin/css/settings.css">
    
    <!-- Custom CSS -->
    <link href="css/redeem.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Squada+One' rel='stylesheet' type='text/css'>
    <script src="js/jquery.min.js"></script>
    <script src="/js/jquery.form.min.js"></script>
	<script src="js/old.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	<style>
		body {
		margin-top: 0px;
		margin-bottom: 50px;
		background: none;
		}
		
		.full {
		  background: url(/images/back.jpg) no-repeat center center; 
		  -webkit-background-size: cover;
		  -moz-background-size: cover;
		  -o-background-size: cover;
		  background-size: cover;
		}
		
		.image-bg{
			background: url('../images/wallet_large.jpg');
			background-repeat: no-repeat;
		    width: 370px;
   			height: 100%;
   			position:center;
		}
	</style>
</head>

<body>
	<script>
	  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
	  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
	  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
	 
	  ga('create', 'UA-72298326-6', 'auto');
	  ga('send', 'pageview');
	 
	</script>
   <nav class="navbar navbar-fixed" role="navigation">
                 <div class="navbar-header">
                    <a class="navbar-brand" href="/index">
                        <img src="images/PayPal_btn4.png" class="animated fadeInDown" alt="">
                    </a>
                </div>
    </nav>
    <!-- Page Content -->
    <div class="container">
       <div class="row">
                    <div class="col-md-12">
                    	<div class="row">
                        	<div class="col-md-2 col-sm-2 scrollpoint sp-effect1">
                        	</div>
                            <div class="col-md-8 col-sm-8 scrollpoint sp-effect1" align="center">                                
								<h1 style="color:#fff;font-family: 'Squada One', cursive;"><i>YOU'RE IN THE NEW MONEY</i></h1>
      							<h5 style="color:#fff;">Congratulations! Thats's a nice little chunk of change. Now, log in to deposit your New Money into your PayPal account.</h5>
                            </div>
                            <div class="col-md-2 col-sm-2 scrollpoint sp-effect1">
                            </div>
                        </div>
                        <div class="row" align="center">
                        <br> <br> <br>
                        	<div class="col-md-4 col-sm-4 scrollpoint sp-effect1">
                        	</div>
                            <div class="col-md-4 col-sm-4 scrollpoint sp-effect1 image-bg" align="center">
                                <img src="images/111.JPG" class="img-responsive" align="center" alt="amount" style="margin-top:-10%;">
								   <h1 align="center" style="color:#1f3087; margin-top:-150px;font-family: 'Squada One', cursive;font-size:68px;"><i>$<c:out value="${coupon.couponValue}"></c:out></i></h1>
								   <h1 align="center" style="color:#1f3087;font-size:26px;font-family: 'Squada One', cursive;"><i>New Money</i></h1>
								    <br>
                            <br>
                            <br> <br>
                            <br>
                            <br><br>
                            <br>
                            <br>
                            
                            </div>
                            
                            <div class="col-md-3 col-sm-3 scrollpoint sp-effect1">
                            </div>
                        </div>
                        <div class="row">
                        	<br>
                        	<div class="col-md-4 col-sm-4 scrollpoint sp-effect1">  
                        		                      		
                        	</div>
                            <div class="col-md-4 col-sm-4 scrollpoint sp-effect1" align="center">
                                <a href="${redirectUrl}" ><img src="https://www.paypalobjects.com/webstatic/en_US/developer/docs/lipp/loginwithpaypalbutton.png"></a>
                            </div>
                            <div class="col-md-4 col-sm-4 scrollpoint sp-effect1">
                            </div>
                        </div>
                    </div>
                </div>
        <!-- /.row -->
    </div>
    <!-- /.container -->
    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>
    <script src="assets/js/slick.min.js"></script>
    <script src="assets/js/placeholdem.min.js"></script>
    <script src="assets/js/rs-plugin/js/jquery.themepunch.plugins.min.js"></script>
    <script src="assets/js/rs-plugin/js/jquery.themepunch.revolution.min.js"></script>
    <script src="assets/js/waypoints.min.js"></script>
    <script src="assets/js/scripts.js"></script>                
	<script>
	paypal.use( ["login"], function(login) {
		  login.render ({
			"appid": "AQkquBDf1zctJOWGKWUEtKXm6qVhueUEMvXO_-MCI4DQQ4-LWvkDLIN2fGsd",
			"authend": "sandbox",
			"scopes": "profile email address phone https://uri.paypal.com/services/paypalattributes",
			"containerid": "myContainer",
			"locale": "en-us",
			"returnurl": "https://devtools-paypal.com"
		  });
		});
	</script>
</body>

</html>