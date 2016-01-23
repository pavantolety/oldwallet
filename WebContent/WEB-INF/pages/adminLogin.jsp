<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Admin Portal | </title>

    <!-- Bootstrap core CSS -->

    <link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="/css/bootstrapValidator.min.css" rel="stylesheet" type='text/css'>
    <link href="fonts/css/font-awesome.min.css" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
     
    <!-- Custom styling plus plugins -->
    <link href="css/custom.css" rel="stylesheet">
    <link href="css/icheck/flat/green.css" rel="stylesheet">


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
	 
	  ga('create', 'UA-72298326-5', 'auto');
	  ga('send', 'pageview'); 
	</script>    
  <h3><a href="/adminHome"><img src="images/logo.png" alt="Smiley face" style="width:5%;"></a></h3>
        <div id="wrapper">
      
        <div class="panel panel-default panel-body">            
                <form name="signUpSubmitForm" action="/adminSubmit" modelAttribute="admin" class="formbox" method="post">					  					
						<div class="form-group margin-bottom-20" >
							<div align="center">
								<h1>Admin Login</h1>
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