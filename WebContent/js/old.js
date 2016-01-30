var latitude ;
var longitude;
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

    	   $('#latitude').val(location.latitude);
    	   $('#longitude').val(location.longitude);
       }
     } );
    $("#redeem_button").click(function() {		
    	$("#couponCode").removeAttr("placeholder", "");
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
    						couponCode : c,
    						latitude: $('#latitude').val(),
    						longitude : $('#longitude').val()
    						
    						
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
    							$('#errorMessage').append('<b style="color:red;">'+data.message+'</b>');
    						}else if(data.action=='expired') {
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