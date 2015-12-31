
function createMarker(oneEvent) {
	var oneMarker = {
		lat : oneEvent.latitude,
		lng : oneEvent.longitude,
		title : oneEvent.title,
		event_id : oneEvent.eventid,
		infoWindow : {
			content : '<p><strong>' + oneEvent.category + '</strong><br>' + oneEvent.date + '</p>'
		},
		animation : google.maps.Animation.DROP, //bounce animation
		icon : "https://cdn3.iconfinder.com/data/icons/iconic-1/32/map_pin_fill-32.png",
		click : function(e) {
			if (console.log)
				console.log(e);
		},
		mouseover : function(e) {
			$('body').trigger('select_event_row', this.eventid);
		},
		mouseout : function(e) {
			$('body').trigger('uselect_event_row', this.eventid);
		}
	};

	return oneMarker;
}