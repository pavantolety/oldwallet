function submitFund(){
    	  $('#fundAllocationForm').ajaxSubmit({
   	       success : function(data)
   	        {
   	    	   if(data.result =="success"){
   	    		  if(cn!=0){
   	    	    	$("#tba"+(parseInt(cn)-1)).remove();
   	    	    	
   	    	    	cn--;
   	    	    	if(cn==0){
   	    	    		$("#totalAmount").val("");
   	    	    	}
   	    	    	}    	
   	    		 $('<div class="alert alert-success"><strong>'+data.message+'</strong></div>').appendTo("#messageD");
              	
    	         $(".alert").delay(200).addClass("in").fadeOut(3000);
    	         
    	         setTimeout(function(){ location.reload();}, 1000);
   	    	   }else{
   	    		$('<div class="alert alert-success"><strong>'+data.message+'</strong></div>').appendTo("#messageD");
              	
   	            $(".alert").delay(200).addClass("in").fadeOut(3000);
   	    	   }
   	        }
    	  });
    }
    function deleteRow(){
        if(cn<=0){
    	$("#tba"+(parseInt(cn)-1)).remove();
    	
    	cn--;
    	if(cn==0){
    		$("#totalAmount").val("");
    	}
    	}    	
    }
    var cn = 0;
    function addRow(){
    	
	$('<tr id="tba'+cn+'"><td><select class="select2_multiple form-control" name="fundAllocation['+cn+'].categoryCode" id="couponPrize'+cn+'" onchange="block(this,'+cn+')"><option value=""></option><option value="JP">Jackpot</option><option value="BB">Bumper Bonanza</option><option value="SB">Super Bonanza</option><option value="SP">Super Prize</option><option value="LO">Lottery</option></select></td><td><input type="text" class="form-control " name="fundAllocation['+cn+'].totalCouponCount" id="couponCount'+cn+'" onkeyup="return multy('+cn+',event)" placeholder="Count" > </td><td><input type="text" class="form-control " name="fundAllocation['+cn+'].couponValue" id="couponAmount'+cn+'" onkeyup="return multy('+cn+',event)" placeholder="$ Amount"> </td><td><input type="text" class="form-control" name="totalAmount'+cn+'" id="totalAmount'+cn+'" placeholder="$ Amount" disabled> </td></tr>').appendTo("#fundTable tbody");
    
    cn++;
    }
    function multy(c,event){
    	var to = parseInt($("#tCouponCount").val());
    	var ro = $("#rCouponCount").val();

    	 var charCode = (event.which) ? event.which : event.keyCode
    	    if (charCode > 31 && (charCode < 48 || charCode > 57)){
    		   $("#couponCount"+c).val("");
       		   $("#couponAmount"+c).val("");
       		   $("#totalAmount"+c).val("");
       		   return false;
    	    }
    	 
			var j = 0 ;
    		var cc = $("#couponCount"+c).val();
    		var ca = $("#couponAmount"+c).val();
    		if(cc<=ro&&cc!=0){ 
    			
    		if(ca!=0 && cc!=0){
    			var total = $("#totalAmount").val();
    			var ta = parseFloat(cc)*parseFloat(ca);
    			$("#totalAmount"+c).val(ta);
    			for(i=0;i<cn;i++){
    			  j +=  parseFloat($("#totalAmount"+i).val());
    			}
    			$("#totalAmount").val(j);
    		}else{
    			$("#totalAmount").val("");
    		}
    		 }else{
    			 alert("Please Enter number within coupon Count!..");
    			 $("#couponCount"+c).val("");
    		 }

    }
    function block(va,c){
    	if(c>=1){
    	var count = 0;
    	for(i=0;i<=cn;i++){
    	if(va[va.selectedIndex].value == $("#couponPrize"+i).val()){
    		count++;
    		if(count>1){
    			$("#couponPrize"+i).val("");
    			alert("Category is already choosen");
    		}
    	}
    	}
    	}
    }