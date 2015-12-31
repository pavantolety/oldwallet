// ------------------- EVENTS ON ELEMENTS -------------------------------
$('body').bind('refresh-navigation', function(e, data) {
	setupNavigation();
});

$(document.body).on('click', '.navigation-item', function() {
	var api_url = $(this).attr("data-api-url");
	document.location.href = api_url;
});
$(document.body).on('click', '.tile-item', function() {
	var details_url = $(this).attr("data-details-url");
	document.location.href = details_url;
});
$(document.body).on('click', '.save-navigation', function() {
	var api_url = $(this).attr("data-api-url");
	$("#feed").html('');
	parseRSS(api_url);
	$('body').trigger('hide-side-nav');
});

function showFullScreen() {
	$('#fullscreenElement').addClass('open');
}


$('.close').on('click', function(event) {
	$('#fullscreenElement').removeClass('open');
});

function fixScrolling() {
	var stuff = {};
	$('#center-body').on('touchstart', stuff, function(e) {
		e.data.max = this.scrollHeight - this.offsetHeight;
		e.data.y = e.originalEvent.pageY;
	}).on('touchmove', stuff, function(e) {
		var dy = e.data.y - e.originalEvent.pageY;
		// if scrolling up and at the top, or down and at the bottom
		if ((dy < 0 && this.scrollTop < 1) || (dy > 0 && this.scrollTop >= e.data.max)) {
			e.preventDefault();
		};
	});

	$('#side').on('touchstart', stuff, function(e) {
		e.data.max = this.scrollHeight - this.offsetHeight;
		e.data.y = e.originalEvent.pageY;
	}).on('touchmove', stuff, function(e) {
		var dy = e.data.y - e.originalEvent.pageY;
		// if scrolling up and at the top, or down and at the bottom
		if ((dy < 0 && this.scrollTop < 1) || (dy > 0 && this.scrollTop >= e.data.max)) {
			e.preventDefault();
		};
	});
}

// ------------------------------ GLOBALS ------------------------------
var ALL_USERS, ALL_DEVICES, ALL_FEEDBACKS, ALL_OPTINS;

$(document.body).on('click', '.show-screen', function() {
	var api_url = $(this).attr("data-api-url");
	document.location.href = api_url;
});

// ------------------------------ SCREENS ------------------------------
function redeemCoupon() {
	resetSearch();
	document.location.href = "#/redeem";
}


function paypalSignUp() {
	document.location.href = ('https://www.paypal.com/signup/account?locale.x=en_US');
}


function log(msg) {
	console.log(msg);
}

function logs(msg) {
	log(JSON.stringify(msg));
}

var layouts = [4, 4, 4];
var index = -1;
function getSpan() {
	index++;
	if (index >= layouts.length) {
		index = 0;
	};
	return layouts[index];
}


$(document).ajaxStart(function() {
	NProgress.start();
});

$(document).ajaxStop(function() {
	NProgress.done();
});

$('body').bind('search-by-string', function(e, data) {
	var data = data || e.data;
	document.location.href = "#/talks";
	searchBy(data);
});

var userLat, userLon;
var onSuccess = function(position) {
	userLat = position.coords.latitude;
	userLon = position.coords.longitude;
	//alert('Latitude: ' + position.coords.latitude + '\n' + 'Longitude: ' + position.coords.longitude);
};

function onError(error) {
	//alert('code: ' + error.code + '\n' + 'message: ' + error.message + '\n');
}

function enableAppFeatures() {
	var token = "8a746fe2de670af90ea51e7e44157fd3";
	$.jStorage.set('REQUESTTOKEN', token);
	$.ajaxPrefilter(function(options) {
		if (options.crossDomain && jQuery.support.cors) {
			var http = (window.location.protocol === 'http:' ? 'http:' : 'https:');
			options.url = http + '//cors-anywhere.herokuapp.com/' + options.url;
			//options.url = "http://cors.corsproxy.io/url=" + options.url;
		}
	});
}

enableAppFeatures();
var showMeter = 0;
$.getJSON('http://healthbnb.net/kiranprabha/api.json', function(response) {
	showMeter = response.showMeter;
});

