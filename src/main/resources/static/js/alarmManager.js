var aid;
$(document).ready(function(){
	
	var deleteButton = "<button class='delete btn btn-warning'>Delete</button>";
	var editButton = "<button class='edit btn btn-success'>Edit</button>";
	var twoButtons = "<div class='btn-group pull-right'>" + deleteButton + editButton + "</div>";
	var userId = 1;
	
    $('#datetimepicker6').datetimepicker({
    	format: "YYYY-MM-DD"
    });
    $('#datetimepicker7').datetimepicker({
    	format: "YYYY-MM-DD"
    });
	$("#addAlarm").hide();
	
	$.ajax({
    	url: '/users/'+userId+'/alarms',
    	dataType: 'json',
    	success: function(data){
    	
    		$.each(data, function(i, d) {
 			   var row='<li class="list-group-item" id="'+data[i].aid+'" value="'+data[i].apillName+'">';
			   row+='<div class="text_holder" >';
			   row+='<label>'+data[i].apillName+'</label> from '+data[i].astartDate+' to '+data[i].aendDate+twoButtons+'<div></li>'; 
			   //+data[i].pillName+''+twoButtons+' </div> </li>'; 
    			
    			   $('#viewAlarms').append(row);
    			   
    			});	
			
    	},
    	error: function(data) {
    		alert('Oops! Error');
    	}
	});
	
});

$(document).on('click', ".input-group", function(){
    $('#datetimepicker6').datetimepicker({
    });
    $('#datetimepicker7').datetimepicker({
        useCurrent: false //Important! See issue #1075
    });
    $("#datetimepicker6").on("dp.change", function (e) {
        $('#datetimepicker7').data("DateTimePicker").minDate(e.date);
    });
    $("#datetimepicker7").on("dp.change", function (e) {
        $('#datetimepicker6').data("DateTimePicker").maxDate(e.date);
    });
});

$(document).on('click', "button.delete", function(){
	var list = $(this).closest("li");
	var id= list.attr('id');
	console.log(id);
	$.ajax({
	    url: '/alarms/'+id,
	    type: 'DELETE',
	    success: function() {
	    	console.log("success");
	    	
	    }, 
	    error: function() {
	    }
	});
	$(this).closest("li").remove();
});

$(document).on('click', "button.edit", function(){
	var editButton = '<input type="submit" id="editAlarmSubmit" value="Edit" class="btn btn-primary add_button"/>';
	var list = $(this).closest("li");
	var id = list.attr('id');
	aid=id;
	$(".list-group").hide();
	console.log(id);
	$.ajax({
    	url: '/categories',
    	dataType: 'json',
    	success: function(data){
    	
    		$.each(data, function(i, d) {
    			   var row='<option value="'+data[i].categoryId+'">'+data[i].categoryName+'</option>'; 			   	
    			   //console.log(data[i].categoryId+""+data[i].categoryName);
    			   $('#selectCategory').append(row);
    			   
    			});	
			
    	},
    	error: function(data) {
    		alert('Oops! Error');
    	}
	});
	$.ajax({
    	url: '/alarms/'+id,
    	dataType: 'json',
    	success: function(data){

    			var categoryId = data.acategoryId;
    			var pillId = data.apillId;
    			var recurrence = data.arecurrence;
    			var startDate = data.astartDate;
    			var endDate = data.aendDate;
    			console.log(categoryId);
    			$('#selectCategory').find("option[value = '" + categoryId + "']").attr("selected", "selected");
    			$('#selectCategory').change();
    			$('#selectPill').find("option[value = '" + pillId + "']").attr("selected", "selected");
    			$('#selectRecurrence').find("option[value = '" + recurrence + "']").attr("selected", "selected");
    			$('#startDate').val(startDate);
    			$('#endDate').val(endDate);
    	},
    	error: function(data) {
    		alert('Oops! Error');
    	}
	});
	
	
	$(".modal-title").replaceWith("Edit the alarm");
	$("#addAlarmSubmit").replaceWith(editButton);
	$("#addAlarm").show();
});

$(document).on('click', '.glyphicon', function(){

	if ($(this).hasClass("glyphicon-plus")) {
		$(".list-group").hide();
		
		$.ajax({
	    	url: '/categories',
	    	dataType: 'json',
	    	success: function(data){
	    	
	    		$.each(data, function(i, d) {
	    			   var row='<option value="'+data[i].categoryId+'">'+data[i].categoryName+'</option>'; 			   	
	    			   //console.log(data[i].categoryId+""+data[i].categoryName);
	    			   $('#selectCategory').append(row);
	    			   
	    			});	
				
	    	},
	    	error: function(data) {
	    		alert('Oops! Error');
	    	}
		});
		
		$("#addAlarm").show();

	}
});


$(document).on('change', '#selectCategory', function(){
	var id = $("#selectCategory option:selected").val();
	$('#selectPill').empty();
	$.ajax({
    	url: '/categories/'+id+'/pills',
    	dataType: 'json',
    	success: function(data){
    	
    		$.each(data, function(i, d) {
    			var row='<option value="'+data[i].pillId+'">'+data[i].pillName+'</option>'; 			   	
 			   //console.log(data[i].categoryId+""+data[i].categoryName);
 			   $('#selectPill').append(row);
    			   
    			});	
			
    	},
    	error: function(data) {
    		alert('Oops! Error');
    	}
	});
});

$(document).on('click', '#addAlarmSubmit', function(){
//	console.log("hello");
//	console.log($("#selectRecurrence option:selected").val());
//	console.log($("#selectCategory option:selected").val());
//	console.log($("#selectPill option:selected").val());
//	console.log($("input[name='startDate']"). val());
//	console.log($("input[name='endDate']"). val());
	$.ajax({
	    url: '/alarms/',
	    type: 'POST',
	    dataType: 'json',
	    data: JSON.stringify( {
	    						 apillId: $("#selectPill option:selected").val(),
						         arecurrence : $("#selectRecurrence option:selected").val(),
						         auserId : 1,
						         astartDate :  $("input[name='startDate']"). val() ,
						         aendDate : $("input[name='endDate']"). val() 
	    		}
	    		),
	    contentType: "application/json",
	    success: function() {
	    	console.log('success');
	    }, 
	    error: function (xhr) {
	    	location.reload();
	      }
	});
});


$(document).on('click', '#editAlarmSubmit', function(){

	$.ajax({
	    url: '/alarms/'+aid,
	    type: 'PATCH',
	    dataType: 'json',
	    data: JSON.stringify( {
	    	 					 aid : aid,
						    	 apillId: $("#selectPill option:selected").val(),
						         arecurrence : $("#selectRecurrence option:selected").val(),
						         auserId : "1",
						         astartDate :  $("input[name='startDate']"). val() ,
						         aendDate : $("input[name='endDate']"). val() 
	    		}
	    		),
	    contentType: "application/json",
	    success: function() {
	    	console.log('success');
	    }, 
	    error: function (xhr) {
	    	location.reload();
	      }
	});
});
