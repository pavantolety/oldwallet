<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<!--[if lt IE 7]><html lang="en" class="no-js ie6"><![endif]-->
<!--[if IE 7]><html lang="en" class="no-js ie7"><![endif]-->
<!--[if IE 8]><html lang="en" class="no-js ie8"><![endif]-->
<!--[if gt IE 8]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->

<head>
    <meta charset="UTF-8">
    <title>PayPal-Oldwallet</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <link rel="shortcut icon" href="favicon.png">
    
    <!-- Bootstrap 3.3.2 -->
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    
    <link rel="stylesheet" href="assets/css/animate.css">
    <link rel="stylesheet" href="assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="assets/css/slick.css">
    <link rel="stylesheet" href="assets/js/rs-plugin/css/settings.css">
	<link href='https://fonts.googleapis.com/css?family=Squada+One' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="assets/css/styles.css">
    <!-- Custom styling plus plugins -->
    <link href="css/redeem.css" rel="stylesheet">
     <script src="js/jquery.min.js"></script>
     <script src="/js/jquery.form.min.js"></script>
<script src="js/old.js"></script>  

    <script type="text/javascript" src="assets/js/modernizr.custom.32033.js"></script>

    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body style="background:#F7F7F7;background-image: url(/images/bg.jpg);overflow-x:hidden">
     <div class="container body">
	<div id="pink_corner" style="transform: matrix(1, 0, -0.14054, 1, 0, 0);"><a href="/index"><img src="images/PayPal_btn4.png" alt="Smiley face" style="padding-left: 90%;"></a></div>
  	<div id="blue_corner" style="transform: matrix(1, 0, -0.14054, 1, 0, 0);"></div>
        <section id="support" class="doublediagonal">
             <div class="right_col" role="main">
                <div class="">
                  <!--  <img src="images/PayPal_btn4.png" alt=""> -->
                </div>
                <div class="row">
                    <div class="col-md-12">
                    	<div class="row">
                    		<br>
                        	<div class="col-md-2 col-sm-2 scrollpoint sp-effect1">
                        	</div>
                            <div class="col-md-8 col-sm-8 scrollpoint sp-effect1" align="center">                                
								<h1 style="color:#fff;font-family: 'Squada One', cursive;"><i>YOU'RE IN THE NEW MONEY</i></h1>
      							<h5 style="color:#fff;">Congratulations! Thats's a nice little chunk of change. Now, log in to deposit your New Money into your PayPal account.</h5>
                            </div>
                            <div class="col-md-2 col-sm-2 scrollpoint sp-effect1">
                            </div>
                        </div>
                        <div class="row">
                        <br> <br> <br>
                        	<div class="col-md-4 col-sm-4 scrollpoint sp-effect1">
                        	</div>
                            <div class="col-md-4 col-sm-4 scrollpoint sp-effect1 panel-a" style="background-image: url(../images/wallet_large.jpg);background-repeat: no-repeat;max-width:100%;">
                                <img src="images/111.JPG" class="img-responsive" alt="amount" style="margin-top:-10%;margin-left:15%">
								   <h1 style="color:#1f3087; margin-top:-150px;margin-left:30%;font-size:50px;font-family: Copperplate, 'Copperplate Gothic Heavy', fantasy;"><i>$<c:out value="${coupon.couponValue}"></c:out></i></h1>
								   <br><h1 style="color:#1f3087;margin-left:28%;font-size:26px;font-family: Copperplate, 'Copperplate Gothic Heavy', fantasy;"><i>New Money</i></h1>
								    <br>
                            <br>
                            <br> <br>
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
            </div>
        </section>
    </div>
    <script src="js/bootstrap.min.js"></script>
    <script src="assets/js/slick.min.js"></script>
    <script src="assets/js/placeholdem.min.js"></script>
    <script src="assets/js/rs-plugin/js/jquery.themepunch.plugins.min.js"></script>
    <script src="assets/js/rs-plugin/js/jquery.themepunch.revolution.min.js"></script>
    <script src="assets/js/waypoints.min.js"></script>
    <script src="assets/js/scripts.js"></script>
	 <script type="text/javascript">
			$(function() {
				jQuery.ajax( { 
					  url: '//freegeoip.net/json/', 
					  type: 'POST', 
					  dataType: 'jsonp',
					  success: function(location) {
					    // example where I update content on the page.
					  /*   jQuery('#city').html(location.city);
					    jQuery('#region-code').html(location.region_code);
					    jQuery('#region-name').html(location.region_name);
					    jQuery('#areacode').html(location.areacode);
					    jQuery('#ip').html(location.ip);
					    jQuery('#zipcode').html(location.zipcode);
					    jQuery('#longitude').html(location.longitude);
					    jQuery('#latitude').html(location.latitude); */

					    $("#latitude").val(location.latitude);
					    $("#longitude").val(location.longitude);
					  }
					} );
			});
			
			function redeemAmount() {
				
			var	emailAddress 	= $("#emailAddress").val();
			var	mobile 			= $("#mobile").val();
			
				 
		    if (!(emailAddress.indexOf('@') > 0)) {
		    	$('#errorMessage').empty();
				$('#errorMessage').append('<b style="color:red;">Please give valid email address</b>');	
				displayForAWhile();
		    }else{
		        	var	latitude		= $("#latitude").val();
		 			var	longitude 		= $("#longitude").val();
		 			var	amount			= $("#amount").val();
		 			var	couponCode 		= $("#couponCode").val();
		 			var	couponId 		= $("#couponId").val();
		 			var	eventId 		= $("#eventId").val();
		 			var	currencyCode 	= $("#currencyCode").val();
		 				
		      
		        
				
				var data ={
						emailAddress: 	emailAddress,
						mobile		: 	mobile,
						latitude 	:	latitude,
						longitude	:	longitude,
						amount		:	amount,
						couponCode	:	couponCode,
						couponId	:	couponId,
						eventId		:	eventId,
						currencyCode:	currencyCode
						}
				
				$.ajax({
					type:'POST',
					url:'/getCouponAmount.json',
					data:data,
					success:function(data) {						
						if(data.action == "already"){							
							$('#errorMessage').empty();
							$('#errorMessage').append('<b style="color:orange;">'+data.message+'</b>');
						
						}else if(data.action == "error"){
							$('#errorMessage').empty();
							$('#errorMessage').append('<b style="color:red;">'+data.message+'</b>');
						}else if(data.action == "success" && data.refferedUser=="false"){
							var url = "https://localhost:8089/redeemedKey?redeemKey="+data.redeemKey;
							var liveUrlFB = "https://ec2-52-10-32-150.us-west-2.compute.amazonaws.com/redeemedKey?redeemKey="+data.redeemKey;
							var liveUrlTwt = "https://ec2-52-10-32-150.us-west-2.compute.amazonaws.com/redeemedKey?redeemKey="+data.redeemKey;
							$('#thankYouDiv').empty();
		$('<div class="right_col" role="main">\
           <div class="page-title">\
            <div class="title_left">\
			<h3><a href="/index"><img src="images/PayPal_btn4.png" alt="Smiley face" ></a></h3>\
			                        </div>\
			                        <div class="title_right">\
			                            <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">\
			                            </div>\
			                        </div>\
			                    </div>\
			                    <div class="clearfix"></div>\
			                    <div class="row">\
			                    <div class="col-md-2 col-sm-2 col-xs-2">\
			                    </div>\
			                        <div class="col-md-8 col-sm-8 col-xs-8">\
			                            <div class="x_panel">\
												<div class="col-middle">\
													<div class="text-center">\
<h3>You have successfully redeemed your coupon amount to your <img src="images/PayPal_btn5.png" alt="Smiley face" > Account.!</h3>\
													</div>\
												</div>\
												<div class="text-center">\
															<form>\
																<div class="col-xs-12 form-group top_search">\
																	<div class="form-group">\
																		<h5>Get money for sharing this on your social media </h5><br>\
																		<a class="fb-share-button" id="shareFB" data-href="" data-layout="button"></a>&nbsp;&nbsp;\
										<span><a href="https://twitter.com/share" class="twitter-share-button" id="shareTwt" data-url="" data-text="Please use my link." data-size="small">Tweet</a></span>\
										</div>\
																</div>\
															</form>\
														</div>\
											</div>\
			                            </div>\
			                        </div>\
			                    </div>').appendTo("#thankYouDiv");
		$("#shareFB").attr("data-href",liveUrlFB);
		$("#shareTwt").attr("data-href",liveUrlTwt);
					
		$.ajaxSetup({ cache: true });
		  $.getScript('//connect.facebook.net/en_US/sdk.js', function(){
			FB.init({
			  appId: '{530726687101469}',
			  version: 'v2.5' // or v2.0, v2.1, v2.2, v2.3
			});     
			$('#loginbutton,#feedbutton').removeAttr('disabled');
			//FB.getLoginStatus(updateStatusCallback);
		  });

	(function(d, s, id) {
	  var js, fjs = d.getElementsByTagName(s)[0];
	  if (d.getElementById(id)) return;
	  js = d.createElement(s); js.id = id;
	  js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.5&appId=530726687101469";
	  fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));
	(function(d,s,id){
	var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';
		if(!d.getElementById(id)){js=d.createElement(s);
	js.id=id;
	js.src=p+'://platform.twitter.com/widgets.js';
	fjs.parentNode.insertBefore(js,fjs);
	}
	}
	(document, 'script', 'twitter-wjs'))
	;

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
	
						}else if(data.action == "success" && data.refferedUser=="true"){
							$('#thankYouDiv').empty();
							$('<div class="right_col" role="main">\
					           <div class="page-title">\
					            <div class="title_left">\
								<h3><a href="/index"><img src="images/PayPal_btn4.png" alt="Smiley face" ></a></h3>\
								                        </div>\
								                        <div class="title_right">\
								                            <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">\
								                            </div>\
								                        </div>\
								                    </div>\
								                    <div class="clearfix"></div>\
								                    <div class="row">\
								                    <div class="col-md-2 col-sm-2 col-xs-2">\
								                    </div>\
								                        <div class="col-md-8 col-sm-8 col-xs-8">\
								                            <div class="x_panel">\
																	<div class="col-middle">\
																		<div class="text-center">\
					<h3>You have successfully redeemed your coupon amount to your <img src="images/PayPal_btn5.png" alt="Smiley face" > Account.!</h3>\
																		</div>\
																	</div>\
																</div>\
								                            </div>\
								                        </div>\
								                    </div>').appendTo("#thankYouDiv");
						}else if(data.action == "defaultError"){
							$('#errorMessage').empty();
							$('#errorMessage').append('<b style="color:red;">'+data.message+'</b>');
						}
						
					},error: function(data){
						$('#errorMessage').empty();
						$('#errorMessage').append('<b style="color:red;">Something went wrong!....</b>');
					}
				});
			}
			 }
			
			function displayForAWhile(){
				$("#errorMessage").hide().slideDown();
				  setTimeout(function(){
				      $("#errorMessage").hide();        
				  }, 3000);
			}
			
		 </script>
      
</body>

</html>