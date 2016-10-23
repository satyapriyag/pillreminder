$(document).ready(function(){
	var userId= 1;
	
	function mapRecurrence(recurrence){
		switch(recurrence){
		case 1: return '9:00';
		case 2: return '1:00';
		case 3: return '7:00';
		}
	}
	$.ajax({
    	url: '/users/'+userId+'/today',
    	dataType: 'json',
    	success: function(data){
    	
    		$.each(data, function(i, d) {
 			   var dt = new Date();
			   var time = dt.getHours() + ":" + dt.getMinutes();
			   var strike ="";
 			   if(time.localeCompare(mapRecurrence(data[i].arecurrence)) ){
				   console.log(time);
				   strike="completed_item";
			   }
 			   var row='<li class="list-group-item" id="'+data[i].apillId+'" value="'+data[i].apillId+'">';
			   row+=data[i].apillName+' at <label>'+mapRecurrence(data[i].arecurrence)+'</label><span class="pull-right bigger blue2 glyphicon glyphicon-question-sign"></span> </li>'; 			   	
			   
			   $('#alarmsToday').append(row);
    			   
    			});	
			
    	},
    	error: function(data) {
    		alert('Oops! Error');
    	}
	});
	

	
});

$(document).on('click', '.glyphicon', function(){

	if ($(this).hasClass("glyphicon-question-sign")) {
		var icon = $(this);
		var pillId = $(this).parent().val();
		var alternatives="No alternatives found";
		$.ajax({
	    	url: '/pills/'+pillId+'/alternatives',
	    	dataType: 'json',
	    	success: function(data){
	    		alternatives="";
	    		$.each(data, function(i, d) {
	    			   alternatives+= data[i].pillName+",";
	    			   console.log(data[i].pillName);
	    			   icon.popover({title: "Alternative Pills", content: " "+alternatives,});
	    			});	
				
	    	},
	    	error: function(data) {
	    		alert('Oops! Error');
	    	}
		});
		console.log(alternatives);
	  }
});
