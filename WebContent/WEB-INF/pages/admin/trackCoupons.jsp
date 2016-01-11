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
 <style>
 
      #map-container {
        padding: 6px;
        border-width: 1px;
        border-style: solid;
        border-color: #ccc #ccc #999 #ccc;
        -webkit-box-shadow: rgba(64, 64, 64, 0.5) 0 2px 5px;
        -moz-box-shadow: rgba(64, 64, 64, 0.5) 0 2px 5px;
        box-shadow: rgba(64, 64, 64, 0.1) 0 2px 5px;
        width: 1000px;
        margin-left :3%;
      }
      #map {
        width: 100%;
        height: 557px;
      }
      #actions {
        list-style: none;
        padding: 0;
      }
      #inline-actions {
        padding-top: 10px;
      }
      .item {
        margin-left: 20px;
      }
    </style>

    <!-- Custom styling plus plugins -->
    <link href="css/custom.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/maps/jquery-jvectormap-2.0.1.css" />
    <link href="css/icheck/flat/green.css" rel="stylesheet" />
    <link href="css/floatexamples.css" rel="stylesheet" type="text/css" />

    <script src="js/jquery.min.js"></script>
    <script src="js/nprogress.js"></script>
    <script type="text/javascript" src="js/amChart1.js"></script>
	<script type="text/javascript" src="js/amChart2.js"></script>
	<script type="text/javascript" src="js/amChart3.js"></script>
    <script>
        NProgress.start();
    </script>
    <script src="https://maps.googleapis.com/maps/api/js"></script>
    <script src="js/data.json"></script>
    <script src="js/data2.json"></script>
    <script>
      var script = '<script type="text/javascript" src="js/markerclusterer';
      if (document.location.search.indexOf('compiled') !== -1) {
        script += '_compiled';
      }
      script += '.js"><' + '/script>';
      document.write(script);
    </script>

    <script>
      var styles = [[{
        url: 'images/people35.png',
        height: 35,
        width: 35,
        anchor: [16, 0],
        textColor: '#ff00ff',
        textSize: 10
      }, {
        url: 'images/people45.png',
        height: 45,
        width: 45,
        anchor: [24, 0],
        textColor: '#ff0000',
        textSize: 11
      }, {
        url: 'images/people55.png',
        height: 55,
        width: 55,
        anchor: [32, 0],
        textColor: '#ffffff',
        textSize: 12
      }], [{
        url: 'images/conv30.png',
        height: 27,
        width: 30,
        anchor: [3, 0],
        textColor: '#ff00ff',
        textSize: 10
      }, {
        url: 'images/conv40.png',
        height: 36,
        width: 40,
        anchor: [6, 0],
        textColor: '#ff0000',
        textSize: 11
      }, {
        url: 'images/conv50.png',
        width: 50,
        height: 45,
        anchor: [8, 0],
        textSize: 12
      }], [{
        url: 'images/heart30.png',
        height: 26,
        width: 30,
        anchor: [4, 0],
        textColor: '#ff00ff',
        textSize: 10
      }, {
        url: 'images/heart40.png',
        height: 35,
        width: 40,
        anchor: [8, 0],
        textColor: '#ff0000',
        textSize: 11
      }, {
        url: 'images/heart50.png',
        width: 50,
        height: 44,
        anchor: [12, 0],
        textSize: 12
      }], [{
        url: 'images/pin.png',
        height: 48,
        width: 30,
        anchor: [-18, 0],
        textColor: '#ffffff',
        textSize: 10,
        iconAnchor: [15, 48]
      }]];

      var markerClusterer = null;
      var map = null;
      var marker1 = null ;
      var imageUrl = 'http://chart.apis.google.com/chart?cht=mm&chs=24x32&' +
          'chco=FFFFFF,008CFF,000000&ext=.png';
      var markers1 = [];
      var markers2 = [];
      var infowindow = null;
      function refreshMap() {
        if (markerClusterer) {
          markerClusterer.clearMarkers();
          
        }

        infowindow = new google.maps.InfoWindow({
      
        content: "holding..."
     
        });
      }
      var mapdata1 = [];
      var mapdata2 = [];
            $(document).ready(function(){
            	$.ajax({
        			type:'GET',
        			url:'/getTrackedCouponsMap.json',
        			success:function(data) {
        				var markerImage = new google.maps.MarkerImage(imageUrl,
        			              new google.maps.Size(24, 32));
        				   for (var i = 0; i < data.mapData1.length; ++i) {
        				          var latLng = new google.maps.LatLng(data.mapData1[i].latitude,
        				        		  data.mapData1[i].logitude)
        				          var  marker = new google.maps.Marker({
        				            position: latLng,
        				            draggable: false,
        				            icon: 'images/pin.png',
        				            title: data.mapData1[i].value+"",
        				            label:"H"
        				          });
        				          markers1.push(marker);
        				          var marker1 = markers1[i];
          				      	google.maps.event.addListener(marker1, 'mouseover', function () {
          				      	infowindow.setContent(this.title+" Coupons Hide Location");
          				      	infowindow.open(map, this);
          				      	});
          				      	google.maps.event.addListener(marker1, 'mouseout', function () {
              				      	
              				      	infowindow.close(map, this);
              				      	});
        				        }
        				        for (var i = 0; i <data.mapData2.length; ++i) {
        				            var latLng = new google.maps.LatLng(data.mapData2[i].latitude,
        				            		data.mapData2[i].logitude)

        				            var  marker = new google.maps.Marker({
        				              position: latLng,
        				              draggable: false,
        				              icon:  'images/pin2.png',
        				              title : data.mapData2[i].value+"",
        				              label :"R"
        				            });
        				            markers2.push(marker);
        				            var marker1 = markers2[i];
        				        	google.maps.event.addListener(marker1, 'mouseover', function () {
        				        	infowindow.setContent(this.title+" Coupons Retrieve Location");
        				        	infowindow.open(map, this);
        				        	});
        				        	google.maps.event.addListener(marker1, 'mouseout', function () {
                				      	
                				      	infowindow.close(map, this);
                				      	});
        				          }
        				        var markers =  markers1.concat(markers2);
        				        /* 
        				                var zoom = parseInt(document.getElementById('zoom').value, 10);
        				                var size = parseInt(document.getElementById('size').value, 10);
        				                var style = parseInt(document.getElementById('style').value, 10);
        				                zoom = zoom === -1 ? null : zoom;
        				                size = size === -1 ? null : size;
        				                style = style === -1 ? null: style; */

        				                markerClusterer = new MarkerClusterer(map, markers, {
        				                  maxZoom:10,
        				                  gridSize:-1,
        				                  styles: styles[0]
        				                }); 
        			}
                });
            });

 

      function initialize() {
        map = new google.maps.Map(document.getElementById('map'), {
          zoom: 2,
          center: new google.maps.LatLng(39.91, 116.38),
          mapTypeId: google.maps.MapTypeId.ROADMAP
        });
        

      /*   var refresh = document.getElementById('refresh');
        google.maps.event.addDomListener(refresh, 'click', refreshMap); */

       /*  var clear = document.getElementById('clear');
        google.maps.event.addDomListener(clear, 'click', clearClusters); */
       
        refreshMap();
      }

      function clearClusters(e) {
        e.preventDefault();
        e.stopPropagation();
        markerClusterer.clearMarkers();
      }

      google.maps.event.addDomListener(window, 'load', initialize);
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

                <br />
                <div class="">
					<div class="row">
					 <div id="map-container">
      					<div id="map"></div>
    				</div>
    				<!-- <input id="refresh" type="button" value="Refresh Map" class="item"/> -->
    				
						<!-- <div id="chartdiv" style="width: 100%; height: 500px;font-size	: 11px;"></div>	 -->
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
	 <script src="js/echart/echarts-all.js"></script>
    <script src="js/echart/green.js"></script>
	<script type="text/javascript" src="js/ammap.js"></script>
	<script src="js/worldLow.js"></script>
	<script src="js/dark.js"></script>

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

   
	
</body>

</html>