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
    <link href="css/icheck/flat/green.css" rel="stylesheet">
    <link href="css/floatexamples.css" rel="stylesheet" />


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
                                           <li><a href="/manageCoupons"><i class="fa fa-wrench"></i>Manage Coupons</a>
                                           </li>
                                           <li><a href="/downloadData"><i class="fa fa-download"></i>Download Coupon Data</a>
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

     <script src="js/bootstrap.min.js"></script>
    <script src="js/nicescroll/jquery.nicescroll.min.js"></script>

    <!-- chart js -->
    <script src="js/chartjs/chart.min.js"></script>
    <!-- bootstrap progress js -->
    <script src="js/progressbar/bootstrap-progressbar.min.js"></script>
    <!-- icheck -->
    <script src="js/icheck/icheck.min.js"></script>
    <!-- daterangepicker -->
    <script type="text/javascript" src="js/moment.min2.js"></script>
    <script type="text/javascript" src="js/datepicker/daterangepicker.js"></script>
    <!-- sparkline -->
    <script src="js/sparkline/jquery.sparkline.min.js"></script>

    <script src="js/custom.js"></script>
    <!-- skycons -->
    <script src="js/skycons/skycons.js"></script>

    <!-- echart -->
    <script src="js/echart/echarts-all.js"></script>
    <script src="js/echart/green.js"></script>
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