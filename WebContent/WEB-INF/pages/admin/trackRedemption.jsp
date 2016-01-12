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
                        <a href="/adminHome" class="site_title"> <span>Campaign Manager</span></a>
                    </div>
                    <div class="clearfix"></div>

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
                                           <li><a href="/manageCoupons"><i class="fa fa-wrench"></i>Manage Coupons</a>
                                           </li>
                                       </ul>
                            	</li>
                            	<li><a><i class="fa fa-edit"></i>Dashboard<span class="fa fa-chevron-down"></span></a>
                            		<ul class="nav child_menu" style="display: none">
                                           <li><a href="/couponStats"><i class="fa fa-dashboard"></i>Coupon Redemptions</a></li>
                                           <li><a href="/trackCoupons"><i class="fa fa-map-marker"></i>Coupon Hide & Retrieve Locations</a></li>
                                           
                                       </ul>
                            	</li>
                                <li><a href="/massPay"><i class="fa fa-paypal"></i>Mass Pay-Test</a></li>
                                
                            </ul>
                        </div>

                    </div>
                    <!-- /sidebar menu -->

                    <!-- /menu footer buttons -->
                    <div class="sidebar-footer hidden-small">
                        <a data-toggle="tooltip" data-placement="top" title="Settings">
                            <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
                        </a>
                        <a data-toggle="tooltip" data-placement="top" title="FullScreen">
                            <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
                        </a>
                        <a data-toggle="tooltip" data-placement="top" title="Lock">
                            <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
                        </a>
                        <a data-toggle="tooltip" data-placement="top" title="Logout">
                            <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
                        </a>
                    </div>
                    <!-- /menu footer buttons -->
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

                    <div class="row top_tiles" style="margin: 10px 0;">
                        <div class="col-md-3 col-sm-3 col-xs-6 tile">
                            <span>Total Coupons</span>
                            <h2>15,233</h2>
                            <span class="sparkline_one" style="height: 160px;">
                    <canvas width="200" height="60" style="display: inline-block; vertical-align: top; width: 94px; height: 30px;"></canvas>
                </span>
                        </div>
                        <div class="col-md-3 col-sm-3 col-xs-6 tile">
                            <span>Coupon Amount</span>
                            <h2>$ 4</h2>
                            <span class="sparkline_one" style="height: 160px;">
                    <canvas width="200" height="60" style="display: inline-block; vertical-align: top; width: 94px; height: 30px;"></canvas>
                </span>
                        </div>
                        <div class="col-md-3 col-sm-3 col-xs-6 tile">
                            <span>Total Amount</span>
                            <h2>$ 60,932</h2>
                            <span class="sparkline_two" style="height: 160px;">
                    <canvas width="200" height="60" style="display: inline-block; vertical-align: top; width: 94px; height: 30px;"></canvas>
                </span>
                        </div>
                        <div class="col-md-3 col-sm-3 col-xs-6 tile">
                            <span>Total Locations</span>
                            <h2>73</h2>
                            <span class="sparkline_one" style="height: 160px;">
                    <canvas width="200" height="60" style="display: inline-block; vertical-align: top; width: 94px; height: 30px;"></canvas>
                </span>
                        </div>
                    </div>
                    <br />

                    <div class="row">
                        <div class="col-md-4 col-sm-4 col-xs-12">
                            <div class="x_panel">
                                <div class="x_title">
                                    <h2>Redemption Status</h2>
                                    <ul class="nav navbar-right panel_toolbox">
                                        <li><a href="#"><i class="fa fa-chevron-up"></i></a>
                                        </li>
                                        <li class="dropdown">
                                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a href="#">Settings 1</a>
                                                </li>
                                                <li><a href="#">Settings 2</a>
                                                </li>
                                            </ul>
                                        </li>
                                        <li><a href="#"><i class="fa fa-close"></i></a>
                                        </li>
                                    </ul>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">

                                    <div id="echart_pie" style="height:350px;"></div>

                                </div>
                            </div>
                        </div>

                       <div class="col-md-4 col-sm-4 col-xs-12">
                            <div class="x_panel">
                                <div class="x_title">
                                    <h2>Mobile Devices</h2>
                                    <ul class="nav navbar-right panel_toolbox">
                                        <li><a href="#"><i class="fa fa-chevron-up"></i></a>
                                        </li>
                                        <li class="dropdown">
                                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a href="#">Settings 1</a>
                                                </li>
                                                <li><a href="#">Settings 2</a>
                                                </li>
                                            </ul>
                                        </li>
                                        <li><a href="#"><i class="fa fa-close"></i></a>
                                        </li>
                                    </ul>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">

                                    <div id="echart_pie1" style="height:350px;"></div>

                                </div>
                            </div>
                        </div>


                         <div class="col-md-4 col-sm-4 col-xs-12">
                            <div class="x_panel">
                                <div class="x_title">
                                    <h2>Event Shares</h2>
                                    <ul class="nav navbar-right panel_toolbox">
                                        <li><a href="#"><i class="fa fa-chevron-up"></i></a>
                                        </li>
                                        <li class="dropdown">
                                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a href="#">Settings 1</a>
                                                </li>
                                                <li><a href="#">Settings 2</a>
                                                </li>
                                            </ul>
                                        </li>
                                        <li><a href="#"><i class="fa fa-close"></i></a>
                                        </li>
                                    </ul>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">

                                    <div id="echart_pie2" style="height:350px;"></div>

                                </div>
                            </div>
                        </div>

                        <div class="col-md-4 col-sm-6 col-xs-12 widget_tally_box">
                            <div class="x_panel">
                                <div class="x_title">
                                    <h2>User Uptake</h2>
                                    <ul class="nav navbar-right panel_toolbox">
                                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                        </li>
                                        <li class="dropdown">
                                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a href="#">Settings 1</a>
                                                </li>
                                                <li><a href="#">Settings 2</a>
                                                </li>
                                            </ul>
                                        </li>
                                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                                        </li>
                                    </ul>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">

                                    <div id="graph_bar" style="width:100%; height:200px;"></div>

                                    <div class="col-xs-12 bg-white progress_summary">

                                        <div class="row">
                                            <div class="progress_title">
                                                <span class="left">Escudor Wireless 1.0</span>
                                                <span class="right">This sis</span>
                                                <div class="clearfix"></div>
                                            </div>

                                            <div class="col-xs-2">
                                                <span>SSD</span>
                                            </div>
                                            <div class="col-xs-8">
                                                <div class="progress progress_sm">
                                                    <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="89"></div>
                                                </div>
                                            </div>
                                            <div class="col-xs-2 more_info">
                                                <span>89%</span>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="progress_title">
                                                <span class="left">Mobile Access</span>
                                                <span class="right">Smart Phone</span>
                                                <div class="clearfix"></div>
                                            </div>

                                            <div class="col-xs-2">
                                                <span>App</span>
                                            </div>
                                            <div class="col-xs-8">
                                                <div class="progress progress_sm">
                                                    <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="79"></div>
                                                </div>
                                            </div>
                                            <div class="col-xs-2 more_info">
                                                <span>79%</span>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="progress_title">
                                                <span class="left">WAN access users</span>
                                                <span class="right">Total 69%</span>
                                                <div class="clearfix"></div>
                                            </div>

                                            <div class="col-xs-2">
                                                <span>Usr</span>
                                            </div>
                                            <div class="col-xs-8">
                                                <div class="progress progress_sm">
                                                    <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="69"></div>
                                                </div>
                                            </div>
                                            <div class="col-xs-2 more_info">
                                                <span>69%</span>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- start of weather widget -->
                        <div class="col-md-4 col-sm-6 col-xs-12">
                            <div class="x_panel">
                                <div class="x_title">
                                    <h2>Daily active users <small>Sessions</small></h2>
                                    <ul class="nav navbar-right panel_toolbox">
                                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                        </li>
                                        <li class="dropdown">
                                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a href="#">Settings 1</a>
                                                </li>
                                                <li><a href="#">Settings 2</a>
                                                </li>
                                            </ul>
                                        </li>
                                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                                        </li>
                                    </ul>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <div class="temperature"><b>Monday</b>, 07:30 AM
                                                <span>F</span>
                                                <span><b>C</b>
                                        </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <div class="weather-icon">
                                                <span>
                                            <canvas height="84" width="84" id="partly-cloudy-day"></canvas>
                                        </span>

                                            </div>
                                        </div>
                                        <div class="col-sm-8">
                                            <div class="weather-text">
                                                <h2>Texas
                                            <br><i>Partly Cloudy Day</i>
                                        </h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="weather-text pull-right">
                                            <h3 class="degrees">23</h3>
                                        </div>
                                    </div>
                                    <div class="clearfix"></div>


                                    <div class="row weather-days">
                                        <div class="col-sm-2">
                                            <div class="daily-weather">
                                                <h2 class="day">Mon</h2>
                                                <h3 class="degrees">25</h3>
                                                <span>
                                                <canvas id="clear-day" width="32" height="32">
                                                </canvas>

                                        </span>
                                                <h5>15
                                            <i>km/h</i>
                                        </h5>
                                            </div>
                                        </div>
                                        <div class="col-sm-2">
                                            <div class="daily-weather">
                                                <h2 class="day">Tue</h2>
                                                <h3 class="degrees">25</h3>
                                                <canvas height="32" width="32" id="rain"></canvas>
                                                <h5>12
                                            <i>km/h</i>
                                        </h5>
                                            </div>
                                        </div>
                                        <div class="col-sm-2">
                                            <div class="daily-weather">
                                                <h2 class="day">Wed</h2>
                                                <h3 class="degrees">27</h3>
                                                <canvas height="32" width="32" id="snow"></canvas>
                                                <h5>14
                                            <i>km/h</i>
                                        </h5>
                                            </div>
                                        </div>
                                        <div class="col-sm-2">
                                            <div class="daily-weather">
                                                <h2 class="day">Thu</h2>
                                                <h3 class="degrees">28</h3>
                                                <canvas height="32" width="32" id="sleet"></canvas>
                                                <h5>15
                                            <i>km/h</i>
                                        </h5>
                                            </div>
                                        </div>
                                        <div class="col-sm-2">
                                            <div class="daily-weather">
                                                <h2 class="day">Fri</h2>
                                                <h3 class="degrees">28</h3>
                                                <canvas height="32" width="32" id="wind"></canvas>
                                                <h5>11
                                            <i>km/h</i>
                                        </h5>
                                            </div>
                                        </div>
                                        <div class="col-sm-2">
                                            <div class="daily-weather">
                                                <h2 class="day">Sat</h2>
                                                <h3 class="degrees">26</h3>
                                                <canvas height="32" width="32" id="cloudy"></canvas>
                                                <h5>10
                                            <i>km/h</i>
                                        </h5>
                                            </div>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <!-- end of weather widget -->


                        <div class="col-md-4 col-sm-6 col-xs-12">
                            <div class="x_panel fixed_height_320">
                                <div class="x_title">
                                    <h2>Daily active users <small>Sessions</small></h2>
                                    <ul class="nav navbar-right panel_toolbox">
                                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                        </li>
                                        <li class="dropdown">
                                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a href="#">Settings 1</a>
                                                </li>
                                                <li><a href="#">Settings 2</a>
                                                </li>
                                            </ul>
                                        </li>
                                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                                        </li>
                                    </ul>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">
                                    <div class="dashboard-widget-content">
                                        <ul class="quick-list">
                                            <li><i class="fa fa-calendar-o"></i><a href="#">Settings</a>
                                            </li>
                                            <li><i class="fa fa-bars"></i><a href="#">Subscription</a>
                                            </li>
                                            <li><i class="fa fa-bar-chart"></i><a href="#">Auto Renewal</a> </li>
                                            <li><i class="fa fa-line-chart"></i><a href="#">Achievements</a>
                                            </li>
                                            <li><i class="fa fa-bar-chart"></i><a href="#">Auto Renewal</a> </li>
                                            <li><i class="fa fa-line-chart"></i><a href="#">Achievements</a>
                                            </li>
                                            <li><i class="fa fa-area-chart"></i><a href="#">Logout</a>
                                            </li>
                                        </ul>

                                        <div class="sidebar-widget">
                                            <h4>Profile Completion</h4>
                                            <canvas width="150" height="80" id="foo2" class="" style="width: 160px; height: 100px;"></canvas>
                                            <div class="goal-wrapper">
                                                <span class="gauge-value pull-left">$</span>
                                                <span id="gauge-text2" class="gauge-value pull-left">3,200</span>
                                                <span id="goal-text2" class="goal-value pull-right">$5,000</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>

                </div>

                <!-- footer content -->
                <footer>
                    <div class="">
                        <p class="pull-right"><span class="lead"></span>
                        </p>
                    </div>
                    <div class="clearfix"></div>
                </footer>
                <!-- /footer content -->

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

   <script src="js/bootstrap.min.js"></script>

    <!-- chart js -->
    <script src="js/chartjs/chart.min.js"></script>
    <!-- bootstrap progress js -->
    <script src="js/progressbar/bootstrap-progressbar.min.js"></script>
    <script src="js/nicescroll/jquery.nicescroll.min.js"></script>
    <!-- icheck -->
    <script src="js/icheck/icheck.min.js"></script>

    <script src="js/custom.js"></script>

    <!-- echart -->
    <script src="js/echart/echarts-all.js"></script>
    <script src="js/echart/green.js"></script>

    <script>
        //random data
        var d1 = [
        [0, 1], [1, 9], [2, 6], [3, 10], [4, 5], [5, 17], [6, 6], [7, 10], [8, 7], [9, 11], [10, 35], [11, 9], [12, 12], [13, 5], [14, 3], [15, 4], [16, 9]
    ];

        //flot options
        var options = {
            series: {
                curvedLines: {
                    apply: true,
                    active: true,
                    monotonicFit: true
                }
            },
            colors: ["#26B99A"],
            grid: {
                borderWidth: {
                    top: 0,
                    right: 0,
                    bottom: 1,
                    left: 1
                },
                borderColor: {
                    bottom: "#7F8790",
                    left: "#7F8790"
                }
            }
        };
        var plot = $.plot($("#placeholder3xx3"), [{
            label: "Registrations",
            data: d1,
            lines: {
                fillColor: "rgba(150, 202, 89, 0.12)"
            }, //#96CA59 rgba(150, 202, 89, 0.42)
            points: {
                fillColor: "#fff"
            }
                }], options);
    </script>
    <!-- /flot -->
    <!--  -->
    <script>
        $('document').ready(function () {
            $(".sparkline_one").sparkline([2, 4, 3, 4, 5, 4, 5, 4, 3, 4, 5, 6, 7, 5, 4, 3, 5, 6], {
                type: 'bar',
                height: '40',
                barWidth: 9,
                colorMap: {
                    '7': '#a1a1a1'
                },
                barSpacing: 2,
                barColor: '#26B99A'
            });

            $(".sparkline_two").sparkline([2, 4, 3, 4, 5, 4, 5, 4, 3, 4, 5, 6, 7, 5, 4, 3, 5, 6], {
                type: 'line',
                width: '200',
                height: '40',
                lineColor: '#26B99A',
                fillColor: 'rgba(223, 223, 223, 0.57)',
                lineWidth: 2,
                spotColor: '#26B99A',
                minSpotColor: '#26B99A'
            });

            var doughnutData = [
                {
                    value: 30,
                    color: "#455C73"
                },
                {
                    value: 30,
                    color: "#9B59B6"
                },
                {
                    value: 60,
                    color: "#BDC3C7"
                },
                {
                    value: 100,
                    color: "#26B99A"
                },
                {
                    value: 120,
                    color: "#3498DB"
                }
    ];
            var myDoughnut = new Chart(document.getElementById("canvas1").getContext("2d")).Doughnut(doughnutData);


        })
    </script>

    <!-- moris js -->
    <script src="js/moris/raphael-min.js"></script>
    <script src="js/moris/morris.js"></script>
    <script>
        $(function () {
            var day_data = [
                {
                    "period": "Jan",
                    "Hours worked": 80
                },
                {
                    "period": "Feb",
                    "Hours worked": 125
                },
                {
                    "period": "Mar",
                    "Hours worked": 176
                },
                {
                    "period": "Apr",
                    "Hours worked": 224
                },
                {
                    "period": "May",
                    "Hours worked": 265
                },
                {
                    "period": "Jun",
                    "Hours worked": 314
                }
    ];
            Morris.Bar({
                element: 'graph_bar',
                data: day_data,
                hideHover: 'always',
                xkey: 'period',
                barColors: ['#26B99A', '#34495E', '#ACADAC', '#3498DB'],
                ykeys: ['Hours worked', 'sorned'],
                labels: ['Hours worked', 'SORN'],
                xLabelAngle: 60
            });
        });
    </script>
    <!-- skycons -->
    <script>
    var myChart = echarts.init(document.getElementById('echart_pie'), theme);
    myChart.setOption({
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            //orient: 'vertical',
            //x: 'left',
            x: 'center',
            y: 'bottom',
            data: ['New', 'Redeemed', 'Expired',]
        },
        toolbox: {
            show: true,
            feature: {
                magicType: {
                    show: true,
                    type: ['pie', 'funnel'],
                    option: {
                        funnel: {
                            x: '25%',
                            width: '50%',
                            funnelAlign: 'left',
                            max: 1548
                        }
                    }
                },
                restore: {
                    show: true
                },
                saveAsImage: {
                    show: true
                }
            }
        },
        calculable: true,
        series: [
            {
                name: 'OldWallet',
                type: 'pie',
                radius: '55%',
                center: ['50%', '48%'], //left,top
                data: [
					{
					    value: 745,
					    name: 'New'
					},
                    {
                        value: 215,
                        name: 'Redeemed'
                    },
                    {
                        value: 98,
                        name: 'Expired'
                    }
            ]
        }
    ]
    });
    
    var myChart1 = echarts.init(document.getElementById('echart_pie1'), theme);
    myChart1.setOption({
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            //orient: 'vertical',
            //x: 'left',
            x: 'center',
            y: 'bottom',
            data: ['iOS', 'Android', 'Other',]
        },
        toolbox: {
            show: true,
            feature: {
                magicType: {
                    show: true,
                    type: ['pie', 'funnel'],
                    option: {
                        funnel: {
                            x: '25%',
                            width: '50%',
                            funnelAlign: 'left',
                            max: 1548
                        }
                    }
                },
                restore: {
                    show: true
                },
                saveAsImage: {
                    show: true
                }
            }
        },
        calculable: true,
        series: [
            {
                name: 'OldWallet',
                type: 'pie',
                radius: '55%',
                center: ['50%', '48%'], //left,top
                data: [
					{
					    value: 54288,
					    name: 'iOS'
					},
                    {
                        value: 38525,
                        name: 'Android'
                    },
                    {
                        value: 15058,
                        name: 'Other'
                    }
            ]
        }
    ]
    });
    
    var myChart2 = echarts.init(document.getElementById('echart_pie2'), theme);
    myChart2.setOption({
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            //orient: 'vertical',
            //x: 'left',
            x: 'center',
            y: 'bottom',
            data: ['Twitter', 'Facebook', 'Other',]
        },
        toolbox: {
            show: true,
            feature: {
                magicType: {
                    show: true,
                    type: ['pie', 'funnel'],
                    option: {
                        funnel: {
                            x: '25%',
                            width: '50%',
                            funnelAlign: 'left',
                            max: 1548
                        }
                    }
                },
                restore: {
                    show: true
                },
                saveAsImage: {
                    show: true
                }
            }
        },
        calculable: true,
        series: [
            {
                name: 'OldWallet',
                type: 'pie',
                radius: '55%',
                center: ['50%', '48%'], //left,top
                data: [
					{
					    value: 5858,
					    name: 'Twitter'
					},
                    {
                        value: 6874,
                        name: 'Facebook'
                    },
                    {
                        value: 1589,
                        name: 'Other'
                    }
            ]
        }
    ]
    });


    var dataStyle = {
        normal: {
            label: {
                show: false
            },
            labelLine: {
                show: false
            }
        }
    };
    var placeHolderStyle = {
        normal: {
            color: 'rgba(0,0,0,0)',
            label: {
                show: false
            },
            labelLine: {
                show: false
            }
        },
        emphasis: {
            color: 'rgba(0,0,0,0)'
        }
    };
    

    </script>
</body>

</html>