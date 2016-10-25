$(document).on('click', '.logout', function(){
	$.ajax({
		url: '/logout',
	    type: 'POST',
	    	success: function() {
		    	window.location.href='/';
		    }
	});
});