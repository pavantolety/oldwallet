<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
		    width: 360px;
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
                        	<div class="col-md-3 col-sm-3 scrollpoint sp-effect1">
                        	</div>
                            <div class="col-md-6 col-sm-6 scrollpoint sp-effect1">                                
								<h1  align="center" style="color:#fff;font-family: 'Squada One', cursive;"><i>YOU'VE FOUND NEW MONEY</i></h1>
      							<h5  align="center" style="color:#fff;">Nice going, Enter your unique code to redeem.</h5>
                            </div>
                            <div class="col-md-3 col-sm-3 scrollpoint sp-effect1">
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="row" align="center">
                        	<div class="col-md-4 col-sm-4 col-lg-4 scrollpoint sp-effect1">  
                        		                      		
                        	</div>
                            <div class="col-md-4 col-sm-4 col-lg-4 scrollpoint sp-effect1 image-bg" align="center">
                                <br><br><br><br><br><br><br><br><br>
                                <form role="form" id="couponValidForm" name="couponValidForm" action="" method="post">
                                    <div class="form-group">
                                        <input type="text" class="form-control-a" id="couponCode" name="couponCode" placeholder="Enter Coupon Code">
                                        <input type="hidden" id="latitude" name="latitude" >
										<input type="hidden" id="longitude" name="longitude" >
                                    </div>
                                </form>
								<br><br><br><br>
                            </div>
                            <div class="col-md-4 col-sm-4 col-lg-4 scrollpoint sp-effect1">
                            </div>
                        </div>
                        <div id="errorMessage" align="center"></div>  
                        <div align="center">${message}</div>
                        <div class="row">
                        	<div class="col-md-4 col-sm-4 col-lg-4 scrollpoint sp-effect1">  
                        		                      		
                        	</div>
                            <div class="col-md-4 col-sm-4 col-lg-4 scrollpoint sp-effect1" align="center">
                            <br>
                                <form role="form">
                                	<div class="form-group">
                                         <input type="checkbox" name="terms" id="terms" checked data-parsley-multiple="terms" data-parsley-id="3763" style="color:#AAA;"> Agree to <a onclick="openTerms()"><b><u>Terms and Conditions</u></b></a>
                                    </div>
                                    <div class="form-group">
                                         <button type="button" id="redeem_button" class="btn-a btn-success-a hvr-grow-a">ENTER</button>
                                    </div>
                                </form>
                            </div>
                            <div class="col-md-4 col-sm-4 col-lg-4 scrollpoint sp-effect1">
                            </div>
                        </div>
                        <!-- Modal Window of terms and conditions  -->
						<div class="modal fade bs-example-modal-lg" tabindex="-1" id="tandc"  role="dialog" aria-hidden="true"  >
                             <div class="modal-dialog modal-lg" style="overflow:scroll;height:600px;">
                                  <div class="modal-content">
                                       <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">�</span></button>
                                                <h4 class="modal-title" id="myModalLabel" style="color:#AAA;">Terms & Conditions</h4>
                                       </div>
                                       <div class="modal-body" style="color:#AAA;">
						                    <p>Your access to and use of the Service is conditioned on your acceptance of and compliance with
											these Terms. These Terms apply to all visitors, users and others who access or use the Service.</p>
											<p>By accessing or using the Service you agree to be bound by these Terms. If you disagree with any 
											part of the terms then you may not access the Service.</p>
											<p><b>Purchases</b></p>
											<p>If you wish to purchase any product or service made available through the Service ("Purchase"),
											you may be asked to supply certain information relevant to your Purchase including, without
											limitation.
											<p><b>Subscriptions</p></b>
											<p>Some parts of the Service are billed on a subscription basis ("Subscription(s)"). You will be billed in
											advance on a recurring ...
											The Subscriptions section is for SaaS businesses.</p>
											<p><b>Content</p></b>
											<p>Our Service allows you to post, link, store, share and otherwise make available certain information,
											text, graphics, videos, or other material ("Content").
											The Content section is for businesses that allow users to create, edit, share, make content on
											their websites or apps.</p>
											<p><b>Links To Other Web Sites</p></b>
											<p>Our Service may contain links to third�party web sites or services that are not owned or controlled
											by My Company (change this).
											My Company (change this) has no control over, and assumes no responsibility for, the content,
											privacy policies, or practices of any third party web sites or services. You further acknowledge and
											agree that My Company (change this) shall not be responsible or liable, directly or indirectly, for any
											damage or loss caused or alleged to be caused by or in connection with use of or reliance on any
											such content, goods or services available on or through any such web sites or services.</p>
											<p><b>Changes</p></b>
											<p>We reserve the right, at our sole discretion, to modify or replace these Terms at any time. If a
											revision is material we will try to provide at least 30 (change this) days' notice prior to any new terms
											taking effect. What constitutes a material change will be determined at our sole discretion.</p>
											<p><b>Contact Us</p></b>
											<p>If you have any questions about these Terms, please contact us.</p>
                                            <div class="modal-footer">
                                                <button type="button"  class="btn btn-default btn-md hvr-grow" data-dismiss="modal"><b>Close</b></button>                                               
                                            </div>
                                       </div>
                                 </div>
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

</body>

</html>