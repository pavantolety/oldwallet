var userName = "MrKiranprabha";
var key = "AIzaSyCp00xYGEapr7Wdopu7Coslo5OUmABj6NQ";
var playlistId = '';
var pageToken = '';
var channelId = '';
var colors = randomColor({
	luminosity : 'dark',
	count : 55
});

function getUserUploads() {
	var videosURL = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId=" + playlistId + "&key=" + key;
	videosURL = videosURL + "&pageToken=" + pageToken;

	$.getJSON(videosURL, function(data) {
		if (data.nextPageToken) {
			pageToken = data.nextPageToken;
			//console.log("Fount token " + pageToken);
			$("#load_more").show();
		} else {
			$("#load_more").hide();
		}
		channelId = data.items[0].snippet.channelId;

		setupTiles(data.items);
		//$.each(data.items, function(i, val) {
		//console.log(JSON.stringify(data));
		//console.log(val.snippet.title);
		//console.log(val.snippet.resourceId.videoId);
		//console.log(val.snippet.channelId);
		//});

	});
}

function getPlayListForUser() {
	//Titanium.App.fireEvent("show_loading");
	var idsURL = "https://www.googleapis.com/youtube/v3/channels?part=contentDetails&";
	idsURL += "key=" + key;
	idsURL += "&forUsername=" + userName;
	$.getJSON(idsURL, function(data) {
		$.each(data.items, function(i, val) {
			//console.log(val.contentDetails.relatedPlaylists.uploads);
			playlistId = val.contentDetails.relatedPlaylists.uploads;
		});
		getUserUploads();
		//Titanium.App.fireEvent("hide_loading");
	});
}

function searchByString(channel_id, search_string) {
	alert("LOCAL HTML " + _query);
	var searchURL = "https://www.googleapis.com/youtube/v3/search?maxResults=10&part=snippet&channelId=" + channel_id + "&q=" + search_string + "&key=" + key;
	//console.log(searchURL);
	$.getJSON(searchURL, function(data) {
		//console.log(" Search Results " + data);
		$("#tiles_holder").html('');
		setupTiles(data.items);
	});
}

function setupTiles(_data) {
	if (!_data)
		return;
	var tilesArray = [];
	for (var i = 0; i < _data.length; i++) {
		oneTile = {
			name : _data[i].snippet.resourceId.videoId,
			image : _data[i].snippet.thumbnails.medium.url,
			title : _data[i].snippet.title,
			spans : getSpan(),
			bgColor : colors[i]
		};
		tilesArray.push(oneTile);
	}

	var tiletemplate = $('#ONE_TILE_DARKOVERLAY').html();
	var tilesHtml = Mustache.to_html(tiletemplate, {
		tiles : tilesArray
	});
	$("#tiles_holder").append(tilesHtml);
	//attachEventOnTile();
}

// TI BRIDGE RELATED LEGACY CODE

function resetSearch() {
	pageToken = '';
	$("#tiles_holder").html('');
	getPlayListForUser();
}

function searchBy(_query) {

	if (!_query || _query.length < 1) {
		resetSearch();
		return;
	}

	//alert(_query + "CID " + channelId);
	//searchByString(channelId, _query);
	var searchURL = "https://www.googleapis.com/youtube/v3/search?maxResults=10&part=snippet&channelId=" + channelId + "&q=" + _query + "&key=" + key;
	//console.log(searchURL);
	$.getJSON(searchURL, function(data) {
		//console.log(" Search Results " + data);
		$("#tiles_holder").html('');
		var items = [];
		for (var i = 0; i < data.items.length; i++) {
			oneItem = data.items[i];
			oneItem.snippet.resourceId = {};
			oneItem.snippet.resourceId.videoId = oneItem.id.videoId;
			console.log("SEARCH VID " + oneItem.id.videoId);
			items.push(oneItem);
		}
		setupTiles(items);
	});
}

function triggerTalks(_url) {
	var ref = window.open(_url, '_blank', 'location=no');
}

