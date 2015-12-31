Path.map("#/index").to(function() {
	$("#title_text").html("HOME");
	$("#stage").load("index.jsp");
});

Path.map("#/redeem").to(function() {
	$("#title_text").html("Redeem Coupon");
	$("#stage").load("screens/redeem.jsp");
});

Path.map("#/terms").to(function() {
	$("#title_text").html("Terms and Conditions");
	$("#stage").load("screens/terms.jsp");
});

Path.map("#/thankYou").to(function() {
	$("#title_text").html("Coupon Redemption");
	$("#stage").load("screens/thankYou.jsp");
});

Path.map("#/share").to(function() {
	$("#title_text").html("Share on Social Media");
	$("#stage").load("screens/share.jsp");
});


// APP RELATED SCREENS ---------------------

Path.root("#/index");
Path.listen();

