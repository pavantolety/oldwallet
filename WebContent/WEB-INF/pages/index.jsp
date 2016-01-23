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
    <link href="css/user.css" rel="stylesheet">
    <link href="css/icheck/flat/green.css" rel="stylesheet">
    <!-- editor -->
    <link href="//netdna.bootstrapcdn.com/font-awesome/3.0.2/css/font-awesome.css" rel="stylesheet">
    <link href="css/editor/external/google-code-prettify/prettify.css" rel="stylesheet">
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
	<script>
	  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
	  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
	  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
	 
	  ga('create', 'UA-72298326-5', 'auto');
	  ga('send', 'pageview'); 
	</script>
    <div class="container body">
<div id="pink_corner" style="transform: matrix(1, 0, -0.14054, 1, 0, 0);"><img src="images/PayPal_btn4.png" alt="Smiley face" style="
    padding-left: 90%;
"></div>
<div id="blue_corner" style="transform: matrix(1, 0, -0.14054, 1, 0, 0);"></div>
            <!-- top navigation -->
            <div class="top_nav">

                <div class="nav_menu">
                    <nav class="" role="navigation">

                        <ul class="nav navbar-nav navbar-right">

                            <li class="">
                                <!-- <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                    Graph
                                    <span class=" fa fa-angle-down"></span>
                                </a>
                                <ul class="dropdown-menu dropdown-usermenu animated fadeInDown pull-right">
                                    
									 <li>
                                        <a href="metrics.html">Graphs</a>
                                    </li>
                                </ul> -->
                            </li>

                            <li role="presentation" class="dropdown">
                            </li>

                        </ul>
                    </nav>
                </div>

            </div>
            <!-- /top navigation -->

            <!-- page content -->
            <div class="right_col" role="main">

                    <div class="page-title">
                        <div class="">
                           <h3><a href="/index"><img src="images/PayPal_btn4.png"  alt="Smiley face" ></a></h3>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <div class="row">
                    	<div class="col-md-3 col-sm-3 col-xs-3">
                    </div>
                    	<div class="col-md-6 col-sm-6 col-xs-6" align="center">
                    		<h1 style="color:#fff;"><i>YOU'VE FOUND NEW MONEY</i></h1>
      						<h4 style="color:#fff;">Nice going, Enter your unique code to redeem.</h4>
                    	</div>
                    	<div class="col-md-4 col-sm-4 col-xs-4" >
                    </div>
                    </div>
                    <div class="row">
                     <div class="col-md-3 col-sm-3 col-xs-3">
                    </div>
                        <div class="col-md-6 col-sm-6 col-xs-6">
                            <div class="x_panel">
                                
                                <div class="x_content">
                                    <br />
                                    <form name="couponValidForm" id="couponValidForm"  action="" data-parsley-validate class="form-horizontal form-label-left" method="post">
										<div class="col-md-3 col-sm-3 col-xs-12">
										</div>
                                        <div class="form-group">
                                            <div class="col-md-6 col-sm-6 col-xs-12">
                                            	<label class="control-label">Coupon Code:<span class="required">*</span></label>
                                                <input type="text" id="couponCode" name="couponCode" required="required" class="form-control col-md-7 col-xs-12">
												<br/>
                                            	<div id="errorMessage"></div>                                            	
                                            												
												<input type="checkbox" name="terms" id="terms" checked> Agree to <a onclick="openTerms()"><b><u>Terms and Conditions</u></b></a>
                                            	
                                            </div>
                                        </div>
                                        <!-- <div class="form-group">
                                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="mobile">Mobile Number:<span class="required">*</span></label>
                                            <div class="col-md-6 col-sm-6 col-xs-12">
                                                <input type="tel" id="mobile" name="mobile" required="required" class="form-control col-md-7 col-xs-12">
												<input type="checkbox" name="terms" id="terms" > Agree to <a onclick="openTerms()"><b><u>Terms and Conditions</u></b></a>
                                            </div>
                                        </div> -->
                                        <div class="ln_solid"></div>
                                        <div class="form-group">
                                            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                                                <button type="button" id="redeem_button" class="btn btn-success hvr-grow">Redeem</button>
                                            </div>											
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                         <div class="col-md-2 col-sm-2 col-xs-2">
                    </div>
                    </div>    
                    </div>                 
                <!-- /page content -->
            </div>
        </div>

<!-- Modal Window of terms and conditions  -->
<div class="modal fade bs-example-modal-lg" tabindex="-1" id="tandc"  role="dialog" aria-hidden="true"  >
                                    <div class="modal-dialog modal-lg" style="overflow:scroll;height:600px;">
                                        <div class="modal-content">

                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span>
                                                </button>
                                                <h4 class="modal-title" id="myModalLabel">Terms & Conditions</h4>
                                            </div>
                                            <div class="modal-body">
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
								<p>Our Service may contain links to third­party web sites or services that are not owned or controlled
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
		<script src="js/jquery.cookie.js"></script>
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
         <script type="text/javascript">
            $(function () {
                'use strict';
                var countriesArray = $.map(countries, function (value, key) {
                    return {
                        value: value,
                        data: key
                    };
                });
                // Initialize autocomplete with custom appendTo:
                $('#autocomplete-custom-append').autocomplete({
                    lookup: countriesArray,
                    appendTo: '#autocomplete-container'
                });
            });
        </script>
		 <script type="text/javascript">

		$("#redeem_button").click(function() {		
		//var e=$('#redeem_form [name=paypal_id]').val();
		var c=$("#couponCode").val();
		//var mobile = document.getElementById("mobile").value;
		
		if(c.length==0) {
			//sweetAlert("Info","Please enter a coupon code","info");
			$('#errorMessage').empty();
			$('#errorMessage').append('<b style="color:orange;">Please enter a coupon code</b>');
			return;
		}
		if($('#terms').prop('checked')){
			if(c != null && $("#couponCode").val()){			
				var coupon = {
							couponCode : c
					};
					
					$.ajax({
						type:'POST',
						url:'/validateCoupon.json',
						data:coupon,
						success:function(data) {
							
							var action = data.action;
							//alert(action);
							if(action=='valid') {
							var successUrl = '/valid';
							$('#errorMessage').empty();
							//successUrl = successUrl+c;
							$("#couponValidForm").attr("action", successUrl);
							$("#couponValidForm").submit();
							
							} else if(action=="invalid") {	
								$('#errorMessage').empty();
								$('#errorMessage').append('<b style="color:red;">Invalid Coupon Code</b>');
							}else if(data.action=='expired') {
								sweetAlert("Info",data.message,"info");
								$('#errorMessage').empty();
								$('#errorMessage').append('<b style="color:red;">'+data.message+'</b>');
							}else if(data.action=='error') {
								sweetAlert("Info",data.message,"info");
								$('#errorMessage').empty();
								$('#errorMessage').append('<b style="color:red;">'+data.message+'</b>');
							}
							
						},
						
						error:function(data) {
							console.log("Error  ::"+JSON.stringify(data));
						}
					});
					
				}else{
					$('#errorMessage').empty();
					$('#errorMessage').append('<b style="color:orange;">Please enter a valid coupon.!</b>');								
				}
			
			}
		else{
			//sweetAlert("Info","Please Agree Terms and Conditions","info");
			$('#errorMessage').empty();
			$('#errorMessage').append('<b style="color:red;">Please check Agree Terms and Conditions</b>');
		}
    
		});

	function checkArray(c){
		var bool = "false";
		for (j=0;j<coupons.length;j++){
			if(coupons[j]==c ){
				bool= "true";
			}
		}
		return bool;
	}
	
	function openTerms() {
		$("#tandc").modal();
	}
</script>
<script src="js/custom.js"></script>
        
</body>

</html>