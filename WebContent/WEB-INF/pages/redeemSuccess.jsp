<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
    <link href="css/endpage.css" rel="stylesheet">
    <link href="css/icheck/flat/green.css" rel="stylesheet">
    <!-- editor -->
    <link href="//netdna.bootstrapcdn.com/font-awesome/3.0.2/css/font-awesome.css" rel="stylesheet">
    <link href="css/editor/external/google-code-prettify/prettify.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Passion+One&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
    <link href="css/editor/index.css" rel="stylesheet">
    <!-- select2 -->
    <link href="css/select/select2.min.css" rel="stylesheet">
    <!-- switchery -->
    <link rel="stylesheet" href="css/switchery/switchery.min.css" />
	<!-- Sweet Alert -->
	<link rel="stylesheet" href="css/sweet-alert.css" />
	<script src="js/sweet-alert.min.js"></script>
	
	<!-- Hover css -->
	<link rel="stylesheet" href="css/hover-min.css" />
	
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
 <div id="pink_corner" style="transform: matrix(1, 0, -0.14054, 1, 0, 0);"><a href="/index"><img src="images/PayPal_btn4.png" alt="Smiley face" style="padding-left: 90%;"></a></div>
  <div id="blue_corner" style="transform: matrix(1, 0, -0.14054, 1, 0, 0);"></div>
            <!-- page content -->
            <div class="right_col1" role="main">
                    <div class="page-title">
                        <!-- <div class="">
                           <h3><a href="/index"><img src="images/PayPal_btn4.png"  alt="Smiley face" ></a></h3>
                        </div> -->
                    </div>
                    <div class="clearfix"></div>
                    <div class="row">
                    <div class="col-md-2 col-sm-2 col-xs-2"></div>
                     <div class="col-md-8 col-sm-8 col-xs-8" align="center">
                      <h1><i>DONE AND DONE</i></h1>
       <p><h4>Getting New Money was easy. Now the hard part? Deciding where to spend it. So feel free to ask your friends by sharing the news. We're sure they'll have some ideas.</h4></p>
       </br></br>
			      
															
															<a class="fb-share-button" data-href="https://ec2-52-10-32-150.us-west-2.compute.amazonaws.com" data-layout="button"></a>																													
															&nbsp;&nbsp;
															<span><a href="https://twitter.com/share" class="twitter-share-button"{count} data-url="https://ec2-52-10-32-150.us-west-2.compute.amazonaws.com" data-text="Please use my link." data-size="small">Tweet</a></span>
															<br>
															<br>
											<a href="" ><img src="images/newmoney.jpg" class="img-responsive" alt="dsfadg"/></a>											   
											 </div>
											 
                   <br><br>
                    
                    <div class="col-md-2 col-sm-2 col-xs-2"></div>
                    </div>           
                    </div>

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
		 <script>					
			$(document).ready(function() {
			$.ajaxSetup({ cache: true });
			  $.getScript('//connect.facebook.net/en_US/sdk.js', function(){
				FB.init({
				  appId: '{530726687101469}',
				  version: 'v2.5' // or v2.0, v2.1, v2.2, v2.3
				});     
				$('#loginbutton,#feedbutton').removeAttr('disabled');
				FB.getLoginStatus(updateStatusCallback);
			  });
			}); 
		</script>
		<script>
		(function(d, s, id) {
		  var js, fjs = d.getElementsByTagName(s)[0];
		  if (d.getElementById(id)) return;
		  js = d.createElement(s); js.id = id;
		  js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.5&appId=530726687101469";
		  fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
		</script>
		<script>
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
		</script>
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

        <!-- select2 -->
       
        <!-- /editor -->
</body>

</html>