<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>PayPal-OldWallet</title>

    <!-- Bootstrap core CSS -->

    <link href="css/bootstrap.min.css" rel="stylesheet">

    <link href="fonts/css/font-awesome.min.css" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">

    <!-- Custom styling plus plugins -->
    <link href="css/redeem.css" rel="stylesheet">
    <link href="css/icheck/flat/green.css" rel="stylesheet">
    <!-- editor -->
    <link href="//netdna.bootstrapcdn.com/font-awesome/3.0.2/css/font-awesome.css" rel="stylesheet">
    <link href="css/editor/external/google-code-prettify/prettify.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Passion+One&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
    <link href="css/editor/index.css" rel="stylesheet">
    <!-- select2 -->
    <link href="css/select/select2.min.css" rel="stylesheet">
    
    <!-- Hover css -->
	<link rel="stylesheet" href="css/hover-min.css" />
	
    <!-- switchery -->
    <link rel="stylesheet" href="css/switchery/switchery.min.css" />
	<link rel="stylesheet" href="css/intlTelInput.css">
    <script src="js/jquery.min.js"></script>

    <!--[if lt IE 9]>
        <script src="../assets/js/ie8-responsive-file-warning.js"></script>
        <![endif]-->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

</head>

<body>
	
    <div class="container body">
 <div id="pink_corner" style="transform: matrix(1, 0, -0.14054, 1, 0, 0);"><img src="images/PayPal_btn4.png" alt="Smiley face" style="padding-left: 90%;"></div>
  <div id="blue_corner" style="transform: matrix(1, 0, -0.14054, 1, 0, 0);"></div>
            <!-- page content -->
            <div class="right_col" role="main">
                    <div class="page-title">
                        <div class="">
                           <h3><a href="/index"><img src="images/PayPal_btn4.png"  alt="Smiley face" ></a></h3>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <div class="row">
                    <div class="col-md-2 col-sm-2 col-xs-2"></div>
                     <div class="col-md-8 col-sm-8 col-xs-8" align="center">
                      <h1><i>YOU'RE IN THE NEW MONEY</i></h1>
				       <p><h4>Congratulations! Thats's a nice little chunk of change. Now, log in to deposit your New Money into your PayPal account.</h4></p>
				       </br></br>
                        <img src="images/Capture.JPG" class="img" alt="wallet" />
                        
                       
                     </div>
                     
                    <div class="col-md-2 col-sm-2 col-xs-2"></div>
                    </div>   
                     <div class="row">
                    <div class="col-md-2 col-sm-2 col-xs-2"></div>
                     <div class="col-md-8 col-sm-8 col-xs-8" align="center">
                     
                        
                       <img src="images/111.JPG" class="img1" alt="amount" />
                       <h1 style="color:#1f3087; margin-top:-115px;size:800%;"><i>$<c:out value="${coupon.couponValue}"/></i></h1>
                       </br><h1 style="color:#1f3087;"><i>New Money</i></h1>
                     </div>
                     
                    <div class="col-md-2 col-sm-2 col-xs-2"></div>
                    </div> </br>
                    <div class="col-md-2 col-sm-2 col-xs-2"></div>
                     <div class="col-md-8 col-sm-8 col-xs-8" align="center">
                     <a href="${redirectUrl}" ><img src="https://www.paypalobjects.com/webstatic/en_US/developer/docs/lipp/loginwithpaypalbutton.png"></a>
                     </div> 
                     
                    </div>                 
                <!-- /page content -->
            </div>
            <!-- /top navigation -->

        <script src="js/bootstrap.min.js"></script>

        <!-- chart js -->
        <script src="js/chartjs/chart.min.js"></script>
        <!-- bootstrap progress js -->
        <script src="js/progressbar/bootstrap-progressbar.min.js"></script>
        <script src="js/nicescroll/jquery.nicescroll.min.js"></script>
        <!-- icheck -->
        <script src="js/icheck/icheck.min.js"></script>
        <!-- tags -->
        <script src="js/tags/jquery.tagsinput.min.js"></script>
        <!-- switchery -->
        <script src="js/switchery/switchery.min.js"></script>
        <!-- daterangepicker -->
        <script type="text/javascript" src="js/moment.min2.js"></script>
        <script type="text/javascript" src="js/datepicker/daterangepicker.js"></script>
        <!-- richtext editor -->
        <script src="js/editor/bootstrap-wysiwyg.js"></script>
        <script src="js/editor/external/jquery.hotkeys.js"></script>
        <script src="js/editor/external/google-code-prettify/prettify.js"></script>
        <!-- select2 -->
        <script src="js/select/select2.full.js"></script>
        <!-- form validation -->
        <script type="text/javascript" src="js/parsley/parsley.min.js"></script>
        <!-- textarea resize -->
        <script src="js/textarea/autosize.min.js"></script>
        <script>
            autosize($('.resizable_textarea'));
        </script>
        <!-- Autocomplete -->
        <script type="text/javascript" src="js/autocomplete/countries.js"></script>
        <script src="js/autocomplete/jquery.autocomplete.js"></script>
		<script src="https://www.paypalobjects.com/js/external/api.js"></script>
       
        <script src="js/custom.js"></script>
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