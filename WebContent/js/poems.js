var poemsList = [];
var poems = [{
	name : "Life is Music",
	image : "sept_2014_coverpage.jpg"
}, {
	name : "Waiting for you..",
	image : "august_2014_coverpage.jpg"
}, {
	name : "Dreams",
	image : "july_2014_coverpage.JPG"
}, {
	name : "You in me",
	image : "june_2014_coverpage.JPG"
}, {
	name : "Phases of Life",
	image : "may_2014_coverpage.JPG"
}, {
	name : "Same again..!",
	image : "april_2014_coverpage.JPG"
}, {
	name : "Keep Smiling",
	image : "march_2014_coverpage.jpg"
}, {
	name : "Searching..",
	image : "feb_2014_coverpage.jpg"
}, {
	name : "Maro Ushodayam",
	image : "jan_2014_coverpage.JPG"
}, {
	name : "Nishkramana",
	image : "dec_2013_coverpage.JPG"
}, {
	name : "Balyam",
	image : "nov_2013_coverpage.JPG"
}, {
	name : "Maa Vuuru",
	image : "oct_2013_coverpage.JPG"
}, {
	name : "Ninna",
	image : "sept_2013_coverpage.JPG"
}, {
	name : "Vennela",
	image : "august_2013_coverpage.JPG"
}, {
	name : "Ekaaki",
	image : "july_2013_coverpage.JPG"
}, {
	name : "Naakishtam",
	image : "june_2013_coverpage.JPG"
}, {
	name : "Amma",
	image : "may_2013_coverpage.JPG"
}, {
	name : "Ugadi Shubhageethi",
	image : "april_2013_coverpage.JPG"
}, {
	name : "Samdesam",
	image : "march_2013_coverpage.JPG"
}, {
	name : "Jatakudithe..",
	image : "feb_2013_coverpage.JPG"
}, {
	name : "Kadile Kshanam",
	image : "jan_2013_coverpage.JPG"
}];

for (var i = 0; i < poems.length; i++) {
	var onePoem = {
		title : poems[i].name,
		index : i,
		image_thumb : 'http://koumudi.net/mobile/thumbs/tn_' + poems[i].image.toLowerCase(),
		image_large : 'http://koumudi.net/mobile/originals/' + poems[i].image.toLowerCase(),
		spans : getSpan(),
		bgColor : colors[i]
	};
	poemsList[i] = onePoem;
}

/*
 $.get('http//cors-anywhere.herokuapp.com/http://koumudi.net/mobile/poem_names.js', function(response) {
 eval(response);
 //console.log(poems);
 for (var i = 0; i < poems.length; i++) {
 var onePoem = {
 title : poems[i].name,
 index : i,
 image_thumb : 'http://koumudi.net/mobile/thumbs/tn_' + poems[i].image.toLowerCase(),
 image_large : 'http://koumudi.net/mobile/originals/' + poems[i].image.toLowerCase(),
 spans : getSpan(),
 bgColor : colors[i]
 };
 poemsList[i] = onePoem;
 }

 });
 */