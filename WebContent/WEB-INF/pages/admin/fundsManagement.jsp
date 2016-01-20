<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
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
    <link href="css/icheck/flat/green.css" rel="stylesheet">
    <!-- ion_range -->
    <link rel="stylesheet" href="css/normalize.css" />
    <link rel="stylesheet" href="css/ion.rangeSlider.css" />
    <link rel="stylesheet" href="css/ion.rangeSlider.skinFlat.css" />

    <!-- colorpicker -->
    <link href="css/colorpicker/bootstrap-colorpicker.min.css" rel="stylesheet">
    
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

<body class="nav-md">

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
                            <h3>Funds Allocation</h3>
                        </div>
                    </div>
                    <div class="clearfix"></div>
		             <div class="row">
		             	 <!-- form input knob -->
		                        <div class="col-md-12 col-xs-12">
                            <div class="x_panel">
                                <div class="x_title">
                                    <h2>Manage Coupon Values</h2>
                                    <div class="clearfix"></div>
                                </div>
								 <div class="x_content">
                                    <div class="col-md-2">
                                        <p>Coupon Count</p>
                                        <input class="knob" data-width="100" data-height="120" data-min="-100" data-displayPrevious=true data-fgColor="#26B99A" value="93">
                                    </div>
									<div class="col-md-2">
                                        <p>Coupon Value</p>
                                        <input class="knob" data-width="100" data-height="120" data-min="-100" data-max="10000"data-displayPrevious=true data-fgColor="#26B99A" value="10000">
                                    </div>
								</div>
                                <div class="x_content">
                                    <br />
										
                                    <form class="form-horizontal form-label-left input_mask">
										
										
                                        <div class="col-md-3 col-sm-3 col-xs-12 form-group has-feedback">
                                            <label>Name of Prize:</label><input type="text" class="form-control " id="inputSuccess2" placeholder="First Name" value="Jackpot">                                            
                                        </div>

                                         <div class="col-md-3 col-sm-3 col-xs-12 form-group has-feedback">
                                            <label>Quantity:</label><input type="text" class="form-control " id="inputSuccess2" placeholder="First Name" value="4">                                            
                                        </div>
										
										 <div class="col-md-3 col-sm-3 col-xs-12 form-group has-feedback">
                                            <label>Amount per Coupon:</label><input type="text" class="form-control " id="inputSuccess2" placeholder="First Name" value="$500">                                            
                                        </div>
										
										 <div class="col-md-3 col-sm-3 col-xs-12 form-group has-feedback">
                                            <label>Total Value:</label><input type="text" class="form-control " id="inputSuccess2" placeholder="First Name" value="$2000">                                            
                                        </div>
										
										 <div class="col-md-3 col-sm-3 col-xs-12 form-group has-feedback">
                                            <input type="text" class="form-control " id="inputSuccess2" placeholder="First Name" value="Bumper Bonanza">                                            
                                        </div>
										
										 <div class="col-md-3 col-sm-3 col-xs-12 form-group has-feedback">
                                            <input type="text" class="form-control " id="inputSuccess2" placeholder="First Name" value="4">                                            
                                        </div>
										
										 <div class="col-md-3 col-sm-3 col-xs-12 form-group has-feedback">
                                            <input type="text" class="form-control " id="inputSuccess2" placeholder="First Name" value="$250">                                            
                                        </div>
										
										 <div class="col-md-3 col-sm-3 col-xs-12 form-group has-feedback">
                                            <input type="text" class="form-control " id="inputSuccess2" placeholder="First Name" value="$1000">                                            
                                        </div>
										
										 <div class="col-md-3 col-sm-3 col-xs-12 form-group has-feedback">
                                            <input type="text" class="form-control " id="inputSuccess2" placeholder="First Name" value="Super Bonanza">                                            
                                        </div>
										
										 <div class="col-md-3 col-sm-3 col-xs-12 form-group has-feedback">
                                            <input type="text" class="form-control " id="inputSuccess2" placeholder="First Name" value="5">                                            
                                        </div>
										
										 <div class="col-md-3 col-sm-3 col-xs-12 form-group has-feedback">
                                            <input type="text" class="form-control " id="inputSuccess2" placeholder="First Name" value="$200">                                            
                                        </div>
										
										 <div class="col-md-3 col-sm-3 col-xs-12 form-group has-feedback">
                                            <input type="text" class="form-control " id="inputSuccess2" placeholder="First Name" value="$1000">                                            
                                        </div>
										
										 <div class="col-md-3 col-sm-3 col-xs-12 form-group has-feedback">
                                            <input type="text" class="form-control " id="inputSuccess2" placeholder="First Name" value="Super Prize">                                            
                                        </div>
										 <div class="col-md-3 col-sm-3 col-xs-12 form-group has-feedback">
                                            <input type="text" class="form-control " id="inputSuccess2" placeholder="First Name" value="40">                                            
                                        </div>
										 <div class="col-md-3 col-sm-3 col-xs-12 form-group has-feedback">
                                            <input type="text" class="form-control " id="inputSuccess2" placeholder="First Name" value="$100">                                            
                                        </div>
										 <div class="col-md-3 col-sm-3 col-xs-12 form-group has-feedback">
                                            <input type="text" class="form-control " id="inputSuccess2" placeholder="First Name" value="$4000">                                            
                                        </div>
										 <div class="col-md-3 col-sm-3 col-xs-12 form-group has-feedback">
                                            <input type="text" class="form-control " id="inputSuccess2" placeholder="First Name" value="Lottery">                                            
                                        </div>
										 <div class="col-md-3 col-sm-3 col-xs-12 form-group has-feedback">
                                            <input type="text" class="form-control " id="inputSuccess2" placeholder="First Name" value="40">                                            
                                        </div>
										 <div class="col-md-3 col-sm-3 col-xs-12 form-group has-feedback">
                                            <input type="text" class="form-control " id="inputSuccess2" placeholder="First Name" value="$50">                                            
                                        </div>
										 <div class="col-md-3 col-sm-3 col-xs-12 form-group has-feedback">
                                            <input type="text" class="form-control " id="inputSuccess2" placeholder="First Name" value="$2000">                                            
                                        </div>
										<div class="col-md-3 col-sm-3 col-xs-12 form-group has-feedback">
										</div>
										<div class="col-md-3 col-sm-3 col-xs-12 form-group has-feedback">
										</div>
										<div class="col-md-3 col-sm-3 col-xs-12 form-group has-feedback">
										</div>
                                        <div class="col-md-3 col-sm-3 col-xs-12 form-group has-feedback">
                                            Grand Total:<input type="text" class="form-control" id="inputSuccess2" placeholder="First Name" value="$10000" readonly>  
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
                                                <button type="submit" class="btn btn-danger">Delete Row</button>
                                                <button type="submit" class="btn btn-primary">Add Row</button>
												<button type="submit" class="btn btn-success">Submit</button>
                                            </div>
                                        </div>

                                    </form>
                                </div>
                            </div>
                    </div>
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
    <script src="js/custom.js"></script>
    <!-- daterangepicker -->
    <script type="text/javascript" src="js/moment.min2.js"></script>
    <script type="text/javascript" src="js/datepicker/daterangepicker.js"></script>
    <!-- input mask -->
    <script src="js/input_mask/jquery.inputmask.js"></script>
    <!-- knob -->
    <script src="js/knob/jquery.knob.min.js"></script>
    <!-- knob -->
    <script>
        $(function ($) {

            $(".knob").knob({
                change: function (value) {
                    //console.log("change : " + value);
                },
                release: function (value) {
                    //console.log(this.$.attr('value'));
                    console.log("release : " + value);
                },
                cancel: function () {
                    console.log("cancel : ", this);
                },
                /*format : function (value) {
                 return value + '%';
                 },*/
                draw: function () {

                    // "tron" case
                    if (this.$.data('skin') == 'tron') {

                        this.cursorExt = 0.3;

                        var a = this.arc(this.cv) // Arc
                            ,
                            pa // Previous arc
                            , r = 1;

                        this.g.lineWidth = this.lineWidth;

                        if (this.o.displayPrevious) {
                            pa = this.arc(this.v);
                            this.g.beginPath();
                            this.g.strokeStyle = this.pColor;
                            this.g.arc(this.xy, this.xy, this.radius - this.lineWidth, pa.s, pa.e, pa.d);
                            this.g.stroke();
                        }

                        this.g.beginPath();
                        this.g.strokeStyle = r ? this.o.fgColor : this.fgColor;
                        this.g.arc(this.xy, this.xy, this.radius - this.lineWidth, a.s, a.e, a.d);
                        this.g.stroke();

                        this.g.lineWidth = 2;
                        this.g.beginPath();
                        this.g.strokeStyle = this.o.fgColor;
                        this.g.arc(this.xy, this.xy, this.radius - this.lineWidth + 1 + this.lineWidth * 2 / 3, 0, 2 * Math.PI, false);
                        this.g.stroke();

                        return false;
                    }
                }
            });

            // Example of infinite knob, iPod click wheel
            var v, up = 0,
                down = 0,
                i = 0,
                $idir = $("div.idir"),
                $ival = $("div.ival"),
                incr = function () {
                    i++;
                    $idir.show().html("+").fadeOut();
                    $ival.html(i);
                },
                decr = function () {
                    i--;
                    $idir.show().html("-").fadeOut();
                    $ival.html(i);
                };
            $("input.infinite").knob({
                min: 0,
                max: 20,
                stopper: false,
                change: function () {
                    if (v > this.cv) {
                        if (up) {
                            decr();
                            up = 0;
                        } else {
                            up = 1;
                            down = 0;
                        }
                    } else {
                        if (v < this.cv) {
                            if (down) {
                                incr();
                                down = 0;
                            } else {
                                down = 1;
                                up = 0;
                            }
                        }
                    }
                    v = this.cv;
                }
            });
        });
    </script>
    <script>
    $('.masterSelect').on('change', function() {
        $('.hiddenOptions select').removeClass("active");
        $('.hiddenOptions select').eq($(this).val()).addClass("active");

        showValue();
    });

    $('.hiddenOptions select').on('change', function() {
        showValue();
    });

    $('.masterSelect').trigger('change'); //set initial value on load

    function showValue()
    {
        console.log($('.hiddenOptions select.active').val());
    }
    </script>
	</body>
</html>