<style type="text/css" media="screen">
	label {
		font-family: lato;
		letter-spacing: 2px;
		text-align: left;
		color: white;
	}
</style>
<form id="redeem_form">
	<div class="container" style="margin-top: 20px;">
		<div class="row">
			<div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
				<div class="panel panel-info animated fadeInRight" style="background: rgba(255,255,255,0.1);border:none;border-radius: 0px;">

					<div class="panel-body" >
						<!--
						<div class="form-group text-left">
													<input type="hidden" name="eventid" id="feedback_title" class="form-control input-md" placeholder="Enter Tile" required>
												</div>
												<div class="form-group text-left">
													<input type="hidden" name="token" id="token" class="form-control input-md" placeholder="enter tile" required>
												</div>
												<div class="form-group text-left">
													<input type="hidden" class="form-control input-md" name="latitude" id="latitude" value="" />
												</div>
												<div class="form-group text-left">
													<input type="hidden" class="form-control input-md" name="longitude" id="longitude" value="" />
												</div>-->
						
						<!--<div class="form-group text-left">
							<label for="paypal_id">PayPal ID:*</label>
							<input class="form-control input-md"  type="text" name="paypal_id" required/>
						</div>-->

						<div class="form-group text-left">
							<label for="coupon_code">Coupon Code:*</label>
							<input class="form-control input-md"  type="text" name="coupon_code" required/>
							<input type="checkbox" name="terms" id="terms" value="1"> Agree to <a href="#/terms"><b><u>Terms and Conditions</u></b></a>
						</div>
						<!--
						<div class="form-group text-left">
						<label for="feedback_date">DateTime</label>
						<div class='input-group date' id='datetimepicker1'>
						<input type='text' name='datetime' class="form-control" />
						<span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span> </span>
						</div>
						</div>-->

						<!-- div class="form-group text-left">
						<label for="feedback_description">Please choose a file:</label>
						<input type="file" name="files" id="file"/>
						</div -->
						<br />
						<button id="redeem_button" value="" class="btn btn-primary btn-block">
							Redeem Coupon
						</button>
					</div>
				</div>
			</div>
		</div>

	</div>
</form>

<script type="text/javascript" charset="utf-8">
	//var emails=["new@gmail.com","old@gmail.com"];
	var coupons=["coupon456","coupon001","coupon123"];
	/*(function() {
		$("#homebutton").show();
		var token = $.jStorage.get('REQUESTTOKEN');
		$('#redeem_form [name=token]').val(token);

		//$('#event_form [name=latitude]').val(userLat);
		//$('#event_form [name=longitude]').val(userLon);

		var data = $.jStorage.get('CATEGORY');
		if (data.length > 0) {
			for ( i = 0; i < data.length; i++) {
				$('#selectbasic').append($('<option></option>').val(data[i].category).html(data[i].category));
			}
		} else {
			$('#selectbasic').append($('<option></option>').val('fever').html('fever'));
			$('#selectbasic').append($('<option></option>').val('headache').html('headache'));
		}

		var SUBMIT_URL = base + '/redeem', SUBMIT_METHOD = 'POST';
		var result = $.jStorage.get('data');
		if (result != null) {
			//$('#redeem_form [name=eventid]').val(result.eventid);
			//$('#redeem_form [name=title]').val(result.title);
			//$('#redeem_form [name=description]').val(result.description);
			//$('#redeem_form [name=date]').val(result.date);
			$('#redeem_form [name=paypal_id]').val(result.paypal_id);
			$('#redeem_form [name=coupon_code]').val(result.coupon_code);
			SUBMIT_URL = base + '/update/' + result.eventid + ',' + token;
			SUBMIT_METHOD = 'PUT';
		}

		$('#redeem_form').validate({
			rules : {
				title : {
					minlength : 3,
					maxlength : 200,
					required : true
				},
				category : {
					required : true
				},
				date : {
					required : true
				},
				time : {
					required : true
				},
				description : {
					minlength : 3,
					maxlength : 500,
					required : true
				}
			},
			highlight : function(element) {
				$(element).closest('.form-group').addClass('has-error');
			},
			unhighlight : function(element) {
				$(element).closest('.form-group').removeClass('has-error');
			},
			errorElement : 'span',
			errorClass : 'help-block',
			errorPlacement : function(error, element) {
				if (element.parent('.input-group').length) {
					error.insertAfter(element.parent());
				} else {
					error.insertAfter(element);
				}
			},
			submitHandler : function(form) {
				var options = {
					url : SUBMIT_URL,
					method : SUBMIT_METHOD,
					contentType : "application/json",
					success : function(response) {
						if (response.ack == 'success') {
							window.location.href = "#/events";
						} else {
							console.log("errorr");
						}
					}
				};
				$(form).ajaxSubmit(options);
			}
		});
	})(); */
	
	$(function() {
		$("#homebutton").hide();
		$("#redeem_button").click(function() {
		
		//var e=$('#redeem_form [name=paypal_id]').val();
		var c=$('#redeem_form [name=coupon_code]').val();
		//var code = document.getElementById("couponCode").value;
		//document.cookie = c; "expires=Tue, 15 Dec 2015 00:00:10 UTC;";
		 if($('#terms').prop('checked')){
		if(c != null){
			for (j=0;j<coupons.length;j++){
				if(coupons[j]==c ){
					if(document.cookie != $('#redeem_form [name=coupon_code]').val()){
					var presentVal=$('#redeem_form [name=coupon_code]').val();
					document.cookie = presentVal; "expires=Tue, 15 Dec 2015 00:00:10 UTC;";
					window.location.href="#/thankYou";
					}else if(document.cookie == $('#redeem_form [name=coupon_code]').val()){
						alert("Coupon Expired or Invalid");
						break;
					}
				}
			}	
		}
		/*for (i=0;i<coupons.length;i++){
				if(coupons[i]==c){
					if(document.cookie != $('#redeem_form [name=coupon_code]').val()){
					var presentVal=$('#redeem_form [name=coupon_code]').val();
					document.cookie = presentVal; "expires=Tue, 15 Dec 2015 00:00:10 UTC;";
					window.location.href="#/thankYouTemp";
					}else if(document.cookie == $('#redeem_form [name=coupon_code]').val()){
						alert("Coupon Expired or Invalid");
						break;
					}
					
				}
			}	*/
		}
		else{
			alert("Please Agree Trerms and Conditions");
		}
    
});
		});
</script>
