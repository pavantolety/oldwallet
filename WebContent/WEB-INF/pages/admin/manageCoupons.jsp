<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>PayPal-OldWallet | Admin Home</title>

    <!-- Bootstrap core CSS -->

    <link href="css/bootstrap.min.css" rel="stylesheet">

    <link href="fonts/css/font-awesome.min.css" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">

    <!-- Custom styling plus plugins -->
    <link href="css/custom.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/maps/jquery-jvectormap-2.0.1.css" />
    <link href="css/icheck/flat/green.css" rel="stylesheet" />
    <link href="css/floatexamples.css" rel="stylesheet" type="text/css" />
    <link href="css/TableTools.css" rel="stylesheet" type="text/css" />
   	<link href="https://cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css" /> 
   	<link href="css/datatables/tools/css/dataTables.tableTools.css" rel="stylesheet">
   		<link href="css/jquery.datetimepicker.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/nprogress.js"></script>
  	<script type="text/javascript" charset="utf-8" src="ZeroClipboard/ZeroClipboard.js"></script>
  	<script type="text/javascript" charset="utf-8" src="js/TableTools.js"></script>  
      <script src="js/bootstrap.min.js"></script>
        <script src="js/custom.js"></script>
    <!-- gauge js -->
    <script type="text/javascript" src="js/gauge/gauge.min.js"></script>
    <script type="text/javascript" src="js/gauge/gauge_demo.js"></script>
    <!-- chart js -->
    <script src="js/chartjs/chart.min.js"></script>
    <!-- bootstrap progress js -->
    <script src="js/progressbar/bootstrap-progressbar.min.js"></script>
    <script src="js/nicescroll/jquery.nicescroll.min.js"></script>
    <!-- icheck -->
    <script src="js/icheck/icheck.min.js"></script>
    <!-- daterangepicker -->
    <script type="text/javascript" src="js/moment.min2.js"></script>
    <script type="text/javascript" src="js/datepicker/jquery.datetimepicker.js"></script>

  
	
	
	<script>
        NProgress.start();
    </script>
    
    <!--[if lt IE 9]>
        <script src="../assets/js/ie8-responsive-file-warning.js"></script>
        <![endif]-->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

</head>


<body class="nav-md">
	<script>
	  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
	  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
	  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
	 
	  ga('create', 'UA-72298326-5', 'auto');
	  ga('send', 'pageview'); 
	</script>

    <div class="container body">


        <div class="main_container">

            <div class="col-md-3 left_col">
                <div class="left_col scroll-view">

                    <div class="navbar nav_title" style="border: 0;">
                        <a href="/adminHome" class="site_title"><span>Campaign Manager</span></a>
                    </div>
                    <div class="clearfix"></div>

                    <!-- menu prile quick info -->
                    <div class="profile">
                        <div class="profile_pic">
                            <img src="images/PayPal_btn4.png" alt="..." class="img-circle profile_img">
                        </div>
                        <div class="profile_info">
                            <span>OldWallet</span>
                            
                        </div>
                    </div>
                    <!-- /menu prile quick info -->
                    <br />

                   <!-- sidebar menu -->
                    <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">

                        <div class="menu_section">
                        	<h3>Admin Dashboard</h3>
                            <ul class="nav side-menu">
                            	<li><a><i class="fa fa-gamepad"></i>Coupon Management<span class="fa fa-chevron-down"></span></a>
                           			<ul class="nav child_menu" style="display: none">
                                           <li><a href="/adminHome"><i class="fa fa-upload"></i>Upload Coupons</a>
                                           </li>
                                           <li><a href="/createCoupon"><i class="fa fa-credit-card"></i>Generate Coupons</a>
                                           </li>
                                           <li><a href="/fundsManagement"><i class="fa fa-money"></i>Funds Allocation</a>
                                           </li>
                                           <li><a href="/manageCoupons"><i class="fa fa-wrench"></i>Manage Coupons</a>
                                           </li>    
                                       </ul>
                            	</li>
                            	<li><a><i class="fa fa-dashboard"></i>Dashboard<span class="fa fa-chevron-down"></span></a>
                            		<ul class="nav child_menu" style="display: none">
                                           <li><a href="/couponStats"><i class="fa fa-pie-chart"></i>Coupon Redemptions</a></li>
                                           <li><a href="/trackCoupons"><i class="fa fa-map-marker"></i>Coupon Hide & Retrieve Locations</a></li>
                                           
                                       </ul>
                            	</li>
                                <li><a href="/massPay"><i class="fa fa-paypal"></i>Mass Pay-Test</a></li>
                                
                            </ul>
                        </div>

                    </div>
                    <!-- /sidebar menu -->

                </div>
            </div>

            <!-- top navigation -->
            <div class="top_nav">

                <div class="nav_menu">
                    <nav class="" role="navigation">
                        <div class="nav toggle">
                            <a id="menu_toggle"><i class="fa fa-bars"></i></a>
                        </div>

                        <ul class="nav navbar-nav navbar-right">
                            <li class="">
                                <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                    <img src="images/emptyUser.png" alt="">Admin
                                    <span class=" fa fa-angle-down"></span>
                                </a>
                                <ul class="dropdown-menu dropdown-usermenu animated fadeInDown pull-right">                 
                                    
                                    
                                    <li><a href="/adminLogout"><i class="fa fa-sign-out pull-right"></i> Log Out</a>
                                    </li>
                                </ul>
                            </li>                            
                        </ul>
                    </nav>
                </div>

            </div>
            <!-- /top navigation -->


             <!-- page content -->
            <div class="right_col" role="main">
                <div class="">

                    <div class="page-title">
                        <div class="title_left">
                            <h3>Manage Coupons</h3>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <div class="row">
                        <div class="col-md-12 col-sm-12 col-xs-12">
                            <div class="x_panel">
                                <div class="x_content">
                                    <div class="row">

                        <div class="col-md-12 col-sm-12 col-xs-12">
                            <div class="x_panel">
                               
                                <div class="x_content">
                                    <table id="example" class="table table-striped responsive-utilities jambo_table col-md-12">
                                        <thead>
                                            <tr class="headings">
                                                <th>COUPON CODE </th>

                                                <th>COUPON VALUE</th>
                                                <!-- <th>START DATE </th>

                                                <!--<th>COUPON VALUE</th>
                                                 <th>START DATE </th>

                                                <th>END DATE</th> -->
                                                <th>STATUS</th>
                                                <th>AVAILABLE REDEMPTIONS</th>
                                                <th>COMPLETED REDEMPTIONS</th>
                                                <th>ACTION</th>
                                               
                                            </tr>
                                        </thead>

                                        <tbody>
                                        	<c:forEach var="couponList" items="${couponList}" >
                                            <tr class="even pointer">
                                                <td class=" "><c:out value="${couponList.couponCode}" /></td>

                                              <td class=" "><c:out value="${couponList.couponValue}" /></td>
                                               <%-- <td class=" "><c:out value="${couponList.validFrom}" /></td>

                                                <%--<td class=" "><c:out value="${couponList.couponValue}" /></td>
                                                <td class=" "><c:out value="${couponList.validFrom}" /></td>

                                                <td class=" "><c:out value="${couponList.validTo}" /></td> --%>
                                                 <td class=" "><c:out value="${couponList.redeemStatus}" /></td>
                                                <td class=" "><c:out value="${couponList.availableRedemptions}" /></td>
                                                
                                                <td class=" "><c:out value="${couponList.completedRedemptions}" /></td>
                                                
                                                <td class=" ">
                                                <c:if test="${couponList.redeemStatus eq 'NEW' && couponList.completedRedemptions == 0}">
                                                <button id="edit" class="btn btn-success" onclick="editCoupon('${couponList.couponCode}','${couponList.couponValue}','${couponList.validFrom}','${couponList.validTo}','${couponList.redeemStatus}','${couponList.availableRedemptions}','${couponList.completedRedemptions}')">EDIT</button>
                                                  </c:if>
                                                </td>
                                              
                                            </tr>
                                          </c:forEach>
                                        </tbody>

                                    </table>
                                </div>
                            </div>
                        </div>

                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <script type="text/javascript">
                        $(document).ready(function () {
                       
                        });
                        function editCoupon(code,v,f,t,s,a,c){
                        	$("#couponCode").val(code),
                        	$('#datetimepicker1').datetimepicker({
                        		formatTime:'H:i',
                        		formatDate:'m.d.Y',
                        		defaultTime:'10:00',
                        		format:'m-d-Y H:i:s',
                        		timepickerScrollbar:false
                        	});
                        	$('#datetimepicker2').datetimepicker({
                        		formatTime:'H:i',
                        		formatDate:'m.d.Y',
                        		defaultTime:'10:00',
                        		format:'m-d-Y H:i:s',
                        		timepickerScrollbar:false
                        	});

                        	$("#myModalLabel").html(code);
                        	$("#availableRedemptions").val(a);
                        	$("#couponValue").val(v);
                        	$("#redeemStatus").val(s);
                        	$("#datetimepicker1").val(f);
                        	$("#datetimepicker2").val(t);
                        	$('#copuonEdit').modal('show');
                        }
                         
                        function saveCouponData(){
                        	var couponData = {
                        	couponCode:$("#couponCode").val(),
                        	availableRedemptions : $("#availableRedemptions").val(),
                        	couponValue : $("#couponValue").val(),
                        	redeemStatus: $("#redeemStatus").val(),
                        	validFrom:$("#datetimepicker1").val(),
                        	validTo:$("#datetimepicker2").val()
                        	};
                        	//alert(JSON.stringify(couponData));
                        	$.ajax({
                    			type:'POST',
                    			url:'/saveCouponData.json',
                    			data:couponData,
                    			success:function(data) {
                    			if(data.status =="success"){
                    		   		$('<div class="alert alert-success"><strong>Coupon Data Saved</strong></div>').appendTo("#toastMessage");
                         	
                    	         	$(".alert").delay(200).addClass("in").fadeOut(3000);
                    			}else{
                    				 $('<div class="alert alert-success"><strong>Coupon Data Saved</strong></div>').appendTo("#toastMessage");
                                  	
                        	         $(".alert").delay(200).addClass("in").fadeOut(3000);
                    			}
                    			}
                    			});
                        }
                    </script>
                       <div class="modal fade bs-example-modal-lg"  id="copuonEdit" tabindex="-1" role="dialog" aria-hidden="true">
                                    <div class="modal-dialog modal-lg">
                                        <div class="modal-content">

                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span>
                                                </button>
                                                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                                            </div>
                                            <div class="modal-body">
                                                  <form id="demo-form2" data-parsley-validate class="form-horizontal form-label-left">

                                        <div class="form-group">
                                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Coupon Value <span class="required">*</span>
                                            </label>
                                            <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="hidden" id="couponCode" name="couponCode" >
                                                <input type="text" id="couponValue" name="couponValue" required="required" class="form-control col-md-7 col-xs-12" readonly>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Start Date <span class="required">*</span>
                                            </label>
                                            <div class="col-md-6 col-sm-6 col-xs-12">
                                            	<input type="text" value="" id="datetimepicker1"  name = "validFrom" required="required" class="form-control col-md-7 col-xs-12" readonly/>
                                            </div>             
                                        </div>
                                       <div class="form-group">
                                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">End Date <span class="required">*</span>
                                            </label>
                                            <div class="col-md-6 col-sm-6 col-xs-12">
                                            	<input type="text" value="" id="datetimepicker2" name="validTo" required="required" class="form-control col-md-7 col-xs-12" readonly />
                                            </div>            
                                        </div>
                       
                                        <div class="form-group">
                                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Status <span class="required">*</span>
                                            </label>
                                            <div class="col-md-6 col-sm-6 col-xs-12">
                                                <input id="redeemStatus" name="redeemStatus" class="date-picker form-control col-md-7 col-xs-12" required="required" type="text" readonly>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Available Redemption <span class="required">*</span>
                                            </label>
                                            <div class="col-md-6 col-sm-6 col-xs-12">
                                                <input id="availableRedemptions"  name="availableRedemptions"  class="date-picker form-control col-md-7 col-xs-12" required="required" type="text" readonly>
                                            </div>
                                        </div>
                                        <div class="ln_solid"></div>
                                         <div id="toastMessage"></div>
                                    </form>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                <button type="button" class="btn btn-primary" onclick="saveCouponData()">Save changes</button>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                 </div>
                <!-- /page content -->
        </div>

    </div>

    <div id="custom_notifications" class="custom-notifications dsp_none">
        <ul class="list-unstyled notifications clearfix" data-tabbed_notifications="notif-group">
        </ul>
        <div class="clearfix"></div>
        <div id="notif-group" class="tabbed_notifications"></div>
    </div>



    <!-- flot js -->
    <!--[if lte IE 8]><script type="text/javascript" src="js/excanvas.min.js"></script><![endif]-->
    <script type="text/javascript" src="js/flot/jquery.flot.js"></script>
    <script type="text/javascript" src="js/flot/jquery.flot.pie.js"></script>
    <script type="text/javascript" src="js/flot/jquery.flot.orderBars.js"></script>
    <script type="text/javascript" src="js/flot/jquery.flot.time.min.js"></script>
    <script type="text/javascript" src="js/flot/date.js"></script>
    <script type="text/javascript" src="js/flot/jquery.flot.spline.js"></script>
    <script type="text/javascript" src="js/flot/jquery.flot.stack.js"></script>
    <script type="text/javascript" src="js/flot/curvedLines.js"></script>
    <script type="text/javascript" src="js/flot/jquery.flot.resize.js"></script>
   

    <!-- worldmap -->
    <script type="text/javascript" src="js/maps/jquery-jvectormap-2.0.1.min.js"></script>
    <script type="text/javascript" src="js/maps/gdp-data.js"></script>
    <script type="text/javascript" src="js/maps/jquery-jvectormap-world-mill-en.js"></script>
    <script type="text/javascript" src="js/maps/jquery-jvectormap-us-aea-en.js"></script>
    <script src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
    <!-- <script src="js/datatables/js/jquery.dataTables.js"></script>-->
    <script src="js/datatables/tools/js/dataTables.tableTools.js"></script>
    <script>
            $(document).ready(function () {
                $('input.tableflat').iCheck({
                    checkboxClass: 'icheckbox_flat-green',
                    radioClass: 'iradio_flat-green'
                });
            });
            var asInitVals = new Array();
            $(document).ready(function () {
                var oTable = $('#example').dataTable({
                    "oLanguage": {
                        "sSearch": "Search Coupons:"
                    },
                    "dom": 'T<"clear">lfrtip',
                    "tableTools": {
                        "sSwfPath": "../swf/copy_csv_xls_pdf.swf"
                    },
                    "aoColumnDefs": [
                        {
                            'bSortable': false,
                            'aTargets': [0]
                        } //disables sorting for column one
            		],
                });
                $("tfoot input").keyup(function () {
                    /* Filter on the column based on the index of this element's parent <th> */
                    oTable.fnFilter(this.value, $("tfoot th").index($(this).parent()));
                });
                $("tfoot input").each(function (i) {
                    asInitVals[i] = this.value;
                });
                $("tfoot input").focus(function () {
                    if (this.className == "search_init") {
                        this.className = "";
                        this.value = "";
                    }
                });
                $("tfoot input").blur(function (i) {
                    if (this.value == "") {
                        this.className = "search_init";
                        this.value = asInitVals[$("tfoot input").index(this)];
                    }
                });
            });
        </script>
    <script>
        NProgress.done();
    </script>
    <!-- /datepicker -->
    <!-- /footer content -->
</body>

</html>
