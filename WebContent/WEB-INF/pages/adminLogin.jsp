<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Admin Portal | </title>

     <link href="css/bootstrap.min.css" rel="stylesheet">

    <link href="fonts/css/font-awesome.min.css" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">

    <!-- Custom styling plus plugins -->
    <link href="css/user.css" rel="stylesheet">
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


    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/jquery.form.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>   
    <script type="text/javascript" src="/js/bootstrapValidator.min.js"></script>   

    <!--[if lt IE 9]>
        <script src="../assets/js/ie8-responsive-file-warning.js"></script>
        <![endif]-->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

</head>

<body style="background:#F7F7F7;background-image: url(/images/bg.jpg)">
	<script>
	  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
	  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
	  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
	 
	  ga('create', 'UA-72298326-6', 'auto');
	  ga('send', 'pageview');
	 
	</script>
	<div class="container body">
 <div id="pink_corner" style="transform: matrix(1, 0, -0.14054, 1, 0, 0);"><img src="images/PayPal_btn4.png" alt="Smiley face" style="padding-left: 90%;"></div>
  <div id="blue_corner" style="transform: matrix(1, 0, -0.14054, 1, 0, 0);"></div>
            <!-- page content -->
            <div class="right_col" role="main">
                    <div class="page-title">
                        <div class="">
                           <h3><a href="/adminHome"><img src="images/PayPal_btn4.png"  alt="Smiley face" ></a></h3>
                        </div>
                    </div>
                    <div class="clearfix"></div>   
	<div  class="row">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<div class="col-md-3 col-sm-3 col-xs-3">
			</div>
		  	<div class="col-md-6 col-sm-6 col-xs-6" align="center">
	  			<h1 align="center" style="color:white;font-family: Copperplate, 'Copperplate Gothic Heavy', fantasy;"><i> Edvenswa Coupon Manager - PayPal </i></h1>
	  		</div>
	  	</div>
  	</div> 
        <div id="wrapper">
      
        <div class="panel panel-default panel-body">            
                <form name="signUpSubmitForm" action="/adminSubmit" modelAttribute="admin" class="formbox" method="post">					  					
						<div class="form-group margin-bottom-20" >
							<div align="center">
								<h4 style="color:#34495e">Admin Login</h4>
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
				</form> 
				</div>                    
        </div>
        </div>
       </div>
  

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
                    	regexp: /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,15}$/,
                    	message:'Password must contain atleast one special character,one number and one letter and must be minimum 8 characters and maximum 15 characters'
                    }
                  }     
                }               
               
            }
        });
	}
  
</script>


</body>

</html>