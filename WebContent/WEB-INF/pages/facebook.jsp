<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>
	Facebook Share Dialogue
</title>
<!-- <meta property="og:url"                content="http://ec2-52-10-32-150.us-west-2.compute.amazonaws.com/" />
<meta property="og:type"               content="article" />
<meta property="og:title"              content="When Great Minds Don’t Think Alike" />
<meta property="og:description"        content="How much does culture influence creative thinking?" />
<meta property="og:image"              content="http://static01.nyt.com/images/2015/02/19/arts/international/19iht-btnumbers19A/19iht-btnumbers19A-facebookJumbo-v2.jpg" /> -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0-alpha1/jquery.min.js"></script>
</head>
<body>
	<div>
		<!--  <a class="fb-share-button" data-href="https://ec2-52-10-32-150.us-west-2.compute.amazonaws.com" data-layout="button"></a> -->
		<button onclick='FBShareOp()'>Share it!</button>
	</div>

<script>					
window.fbAsyncInit = function() {
  // init the FB JS SDK
  FB.init({
    appId      : '530726687101469',                            
    status     : true,                                 
    xfbml      : true                                  
  });

};

// Load the SDK asynchronously
(function(d, s, id){
   var js, fjs = d.getElementsByTagName(s)[0];
   if (d.getElementById(id)) {return;}
   js = d.createElement(s); js.id = id;
   js.src = "//connect.facebook.net/en_US/all.js";
   fjs.parentNode.insertBefore(js, fjs);
 }(document, 'script', 'facebook-jssdk'));
		</script>
		<script>
		function FBShareOp(){
			var product_name   = 	'oldwallet';
			var description	   =	'description';
			var share_image	   =	'https://tpc.googlesyndication.com/simgad/8081486627064680681';
			var share_url	   =	'http://ec2-52-10-32-150.us-west-2.compute.amazonaws.com/adminHome';	
			var redirect_uri   = 	'http://ec2-52-10-32-150.us-west-2.compute.amazonaws.com/adminHome'
		        var share_capt     =    'caption';
		    FB.ui({
		        method: 'feed',
		        name: product_name,
		        link: share_url,
		        picture: share_image,
		        caption: share_capt,
		        description: description

		    }, function(response) {
		        if(response && response.post_id){}
		        else{}
		    });
		}	
		</script>
		
		
</body>
</html>