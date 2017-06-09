//slider script
jQuery(document).ready(function($) {
	$(".scroll").click(function(event) {
		event.preventDefault();
		$('html,body').animate({
			scrollTop : $(this.hash).offset().top
		}, 1000);
	});
});

$(function() {
	$("#slider1").responsiveSlides({
		auto : true,
		speed : 500,
		namespace : "callbacks",
		nav : true,
		prevText : "<img src='images/arrowl.png' alt=''/>", // String: Text for the "previous"button
		nextText : "<img src='images/arrowr.png' alt=''/>", // String: Text for the "next"button
		pager : true,
		pause : true,
	});
});
//Slideshow 2
//$("#slider2").responsiveSlides({
//  auto: false,
//  pager: true,
//  speed: 300,
//  maxwidth: 1112
//});
$(document).ready(function(c) {
	$('.alert-close').on('click', function(c) {
		$('.message').fadeOut('slow', function(c) {
			$('.message').remove();
		});
	});
});

$(document).ready(function(c) {
	$('.alert-close1').on('click', function(c) {
		$('.message1').fadeOut('slow', function(c) {
			$('.message1').remove();
		});
	});
});
