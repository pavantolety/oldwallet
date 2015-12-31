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
    <link href="css/custom.css" rel="stylesheet">
    <link href="css/icheck/flat/green.css" rel="stylesheet">
    <!-- editor -->
    <link href="http://netdna.bootstrapcdn.com/font-awesome/3.0.2/css/font-awesome.css" rel="stylesheet">
    <link href="css/editor/external/google-code-prettify/prettify.css" rel="stylesheet">
    <link href="css/editor/index.css" rel="stylesheet">
    <!-- select2 -->
    <link href="css/select/select2.min.css" rel="stylesheet">
    <!-- switchery -->
    <link rel="stylesheet" href="css/switchery/switchery.min.css" />
	<!-- Sweet Alert -->
	<link rel="stylesheet" href="css/sweet-alert.css" />
	<script src="js/sweet-alert.min.js"></script>
	
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


    <div class="container body">

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
                                <!-- <a href="javascript:;" class="dropdown-toggle info-number" data-toggle="dropdown" aria-expanded="false">
                                    <i class="fa fa-envelope-o"></i>
                                    <span class="badge bg-green">6</span>
                                </a>
                                <ul id="menu1" class="dropdown-menu list-unstyled msg_list animated fadeInDown" role="menu">
                                    <li>
                                        <a>
                                            <span class="image">
                                        <img src="images/img.jpg" alt="Profile Image" />
                                    </span>
                                            <span>
                                        <span>John Smith</span>
                                            <span class="time">3 mins ago</span>
                                            </span>
                                            <span class="message">
                                        Film festivals used to be do-or-die moments for movie makers. They were where... 
                                    </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a>
                                            <span class="image">
                                        <img src="images/img.jpg" alt="Profile Image" />
                                    </span>
                                            <span>
                                        <span>John Smith</span>
                                            <span class="time">3 mins ago</span>
                                            </span>
                                            <span class="message">
                                        Film festivals used to be do-or-die moments for movie makers. They were where... 
                                    </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a>
                                            <span class="image">
                                        <img src="images/img.jpg" alt="Profile Image" />
                                    </span>
                                            <span>
                                        <span>John Smith</span>
                                            <span class="time">3 mins ago</span>
                                            </span>
                                            <span class="message">
                                        Film festivals used to be do-or-die moments for movie makers. They were where... 
                                    </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a>
                                            <span class="image">
                                        <img src="images/img.jpg" alt="Profile Image" />
                                    </span>
                                            <span>
                                        <span>John Smith</span>
                                            <span class="time">3 mins ago</span>
                                            </span>
                                            <span class="message">
                                        Film festivals used to be do-or-die moments for movie makers. They were where... 
                                    </span>
                                        </a>
                                    </li>
                                    <li>
                                        <div class="text-center">
                                            <a>
                                                <strong>See All Alerts</strong>
                                                <i class="fa fa-angle-right"></i>
                                            </a>
                                        </div>
                                    </li>
                                </ul> -->
                            </li>

                        </ul>
                    </nav>
                </div>

            </div>
            <!-- /top navigation -->

            <!-- page content -->
            <div class="right_col" role="main">

                    <div class="page-title">
                        <div class="title_left">
                            <h3><img src="images/paypal2.png" alt="Smiley face" ></h3>
                        </div>
                        <div class="title_right">
                            <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                                <!-- <div class="input-group">
                                    <input type="text" class="form-control" placeholder="Search for...">
                                    <span class="input-group-btn">
                            <button class="btn btn-default" type="button">Go!</button>
                        </span>
                                </div> -->
                            </div>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <div class="row">
                        <div class="col-md-12 col-sm-12 col-xs-12">
                            <div class="x_panel">
                                <div class="x_title">
                                    <h2>Redeem <small>your coupon.</small></h2>
                                   
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">
                                    <br />
                                    <form name="redeemForm" id="redeemForm" action="/thankYou" data-parsley-validate class="form-horizontal form-label-left" method="post">

                                        <div class="form-group">
                                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Coupon Code:<span class="required">*</span>                            </label>
                                            <div class="col-md-6 col-sm-6 col-xs-12">
                                                <input type="text" id="couponCode" name="last-name" required="required" class="form-control col-md-7 col-xs-12">
												<!-- <input type="checkbox" name="terms" id="terms" > Agree to <a href="/terms"><b><u>Terms and Conditions</u></b></a> -->
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="mobile">Mobile Number:<span class="required">*</span>                            </label>
                                            <div class="col-md-6 col-sm-6 col-xs-12">
                                                <input type="tel" id="mobile" name="mobile" required="required" class="form-control col-md-7 col-xs-12">
												<input type="checkbox" name="terms" id="terms" > Agree to <a href="/terms"><b><u>Terms and Conditions</u></b></a>
                                            </div>
                                        </div>
                                        <div class="ln_solid"></div>
                                        <div class="form-group">
                                            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                                                <button type="reset" class="btn btn-primary">Reset</button>
                                                <button type="button" id="redeem_button" class="btn btn-success">Redeem</button>
                                            </div>											
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
					 <div class="row">
                        <div class="col-md-12 col-sm-12 col-xs-12">
                        	<p align="center"><img src="../images/11.gif" alt="" style="height: auto;width: 35%;"></img>
                        	<img src="../images/11.gif" alt="" style="height: auto;width: 35%;"></img></p>
                        </div>
                     </div>
                      <div class="row">
                        <div class="col-md-12 col-sm-12 col-xs-12">
                        	<p align="center"><img src="../images/22.gif" alt="" style="height: auto;width: 35%;"></img>
                        	<img src="../images/22.gif" alt="" style="height: auto;width: 35%;"></img></p>
                        </div>
                     </div>
                <!-- /page content -->


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
	var coupons=["coupon456","coupon001","coupon123"];	
	$(function() {
		$("#redeem_button").click(function() {		
		//var e=$('#redeem_form [name=paypal_id]').val();
		var c=document.getElementById("couponCode").value;
		var mobile = document.getElementById("mobile").value;
		if($('#terms').prop('checked')){
			if(c != null){			
				if(checkArray(c) == "true"){
					for (j=0;j<coupons.length;j++){
						if(coupons[j]==c ){
							if(document.cookie != document.getElementById("couponCode").value){
							var presentVal=document.getElementById("couponCode").value;
							document.cookie = presentVal; "expires=Tue, 15 Dec 2015 00:00:10 UTC;";
							var url = '/sendSMS/';
							url = url+mobile;
							$.ajax({
								type:'GET',
								url:url,
								success:function(data) {
									
								},
								error:function(data) {
									alert(JSON.stringify(data));
								}
							});
							location.href="/thankYou";
							return false;
							}else if(document.cookie == document.getElementById("couponCode").value){
							sweetAlert("success","Coupon or Event Expired","info");
							break;
							}
						}
					}
				}else{
					swal("Error","Invalid Coupon","error");
					location.href="/";
				}
			
			}else{
				sweetAlert("success","Please enter Coupon Code","info");
			}
		}
		else{
			sweetAlert("success","Please Agree Trerms and Conditions","info");
		}
    
		});
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
</script>
<script src="js/custom.js"></script>
        
</body>

</html>