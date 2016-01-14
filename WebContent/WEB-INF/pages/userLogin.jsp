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
                     <div class="col-md-4 col-sm-4 col-xs-4">
                    </div>
                        <div class="col-md-4 col-sm-4 col-xs-4">
                            <div class="x_panel">
                                <div class="x_title">
                                    <h2>Login with PayPal to redeem your coupon.</h2>
                                   
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">           
					                <form name="signUpSubmitForm" action="/userSubmit" modelAttribute="admin" class="formbox" method="post">					  					
											<div class="form-group margin-bottom-20" >
												<div align="center">
													<h1>Login</h1>
												</div>
											</div>
											<div class="form-group margin-bottom-20" >
												<div align="center">
													<b style="color:red;"> ${message} </b>
												</div>
											</div>
											<div class="form-group margin-bottom-20" >
												<div class="input-group">
													<span class="input-group-addon"><i class="fa fa-envelope"></i></span>
													<input type="text" class="form-control"
														placeholder="Email Address" name="emailAddress" id="emailAddress" required="required"/>
												</div>
											</div>
											
											<div class="form-group margin-bottom-20">
												<div class="input-group">
													<span class="input-group-addon"><i class="fa fa-lock"></i></span>
													<input type="password" class="form-control" placeholder="Password" name="password" id="password" required="required">
												</div>						
											</div>						
											<div class="form-group margin-bottom-20">							
													<button type="submit" class="btn btn-md btn-success pull-right"  name="Submit" >Login</button>							
											</div>				    											
									</form> </div>
                            </div>
                        </div>
                         <div class="col-md-2 col-sm-2 col-xs-2">
                    </div>
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
		<script src="js/custom.js"></script>
  

    <script type="text/javascript">
    $(document).ready(function() {       
        registerFormValidators();
    });       
    
    function registerFormValidators(){    
	    var signupForm=$('form[name="signUpSubmitForm"]');
    	signupForm.bootstrapValidator({
            message: 'Entered value is not valid',
            feedbackIcons: {
                valid: '',
                invalid: '',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                emailAddress: {
                    validators: {
                        notEmpty: {
                            message: 'Email is required and cannot be empty'
                        },
                        emailAddress: {
                            message: 'Email address is not valid'
                        }
                    }
                },                
                password: {
                    validators: {
                    	notEmpty: {
                            message: 'Password is required and cannot be empty'
                        },
                  regexp:{
                    	regexp: /^(?=.*[0-9]+.*)(?=.*[a-zA-Z]+.*)[0-9a-zA-Z]{8,15}$/,
                    	message:'Password must contain atleast one number and one letter and must be minimum 8 characters and maximum 15 characters'
                    }
                  }     
                }               
               
            }
        });
	}
  
</script>


</body>

</html>