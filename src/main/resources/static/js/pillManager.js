$(document).ready(function(){
	
	$('#viewPills').hide();
	$('#addPills').hide();
	$('#editPills').hide();
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
	
	
});

$(document).on('change', '#selectCategory', function(){
	var id = $("#selectCategory option:selected").val();
	$('#viewPillsList').empty();
	$.ajax({
    	url: '/categories/'+id+'/pills',
    	dataType: 'json',
    	success: function(data){
    	
    		$.each(data, function(i, d) {
    			   var row='<br/><input type="radio" name="viewPillsList" id="'+data[i].pillId+'" value="'+data[i].pillId+'"/>';
    			   row+='<label for="'+data[i].pillId+'">'+data[i].pillName+'</label>'; 			   	
    			   row+='<br/>';
    			   //console.log(data[i].pillId+""+data[i].pillName);
    			   $('#viewPillsList').append(row);
    			   
    			});	
			
    	},
    	error: function(data) {
    		alert('Oops! Error');
    	}
	});
	
	$('#viewPills').show();
});

$(document).on('click','#addPill',function(){
	$('#viewPillsList').hide();
	$('#addPills').show();
		
});

$(document).on('click','#addPillSubmit', function(){
	console.log('hello');
	
	$.ajax({
	    url: '/pills/',
	    type: 'POST',
	    dataType: 'json',
	    data: JSON.stringify( {
	    					pillName : $('#addPillName').val(),
	    					pillCategoryId : $("#selectCategory option:selected").val()
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

$(document).on('click','#editPill',function(){
	$('#viewPillsList').hide();
	$('#addPills').hide();

	
	$('#selectCategory option').clone().appendTo('#editCategory');
	var selectedValue = $("#selectCategory option:selected").val();
	$('#editCategory').find("option[value = '" + selectedValue + "']").attr("selected", "selected");

	var pillName = $("input[name='viewPillsList']:checked").next('label:first').text();
	console.log(pillName);
	$("#editPillName").val(pillName );
	
	$('#editPills').show();
	
});


$(document).on('click','#editPillSubmit', function(){
	console.log('hello');
	var id =$("input[name='viewPillsList']:checked").val();
	$.ajax({
	    url: '/pills/'+id,
	    type: 'PATCH',
	    dataType: 'json',
	    data: JSON.stringify( {
	    					pillId : id,
	    					pillName : $('#editPillName').val(),
	    					pillCategoryId : $("#editCategory option:selected").val()
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

$(document).on('click','#deletePill',function(){
	
	var id= $("input[name='viewPillsList']:checked").val();
	var category = $("#selectCategory option:selected").val();
	console.log(id);
	$.ajax({
	    url: '/pills/'+id,
	    type: 'DELETE',
	    success: function() {
	    	$("#selectCategory").change();
	    }, 
	    error: function() {
	    }
	});
	
});
