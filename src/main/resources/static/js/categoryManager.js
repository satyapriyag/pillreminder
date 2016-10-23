$(document).ready(function(){
	
	var deleteButton = "<button class='delete btn btn-warning'>Delete</button>";
	var editButton = "<button class='edit btn btn-success'>Edit</button>";
	var twoButtons = "<div class='btn-group pull-right'>" + deleteButton + editButton + "</div>";
	
	
	$.ajax({
    	url: '/categories',
    	dataType: 'json',
    	success: function(data){
    	
    		$.each(data, function(i, d) {
 			   var row='<li class="list-group-item" id="'+data[i].categoryId+'" value="'+data[i].categoryName+'">';
			   row+='<div class="text_holder" >';
			   row+='<label>'+data[i].categoryName+'</label>'+twoButtons+'<div></li>'; 
			   //+data[i].pillName+''+twoButtons+' </div> </li>'; 
    			
    			   $('#viewCategories').append(row);
    			   
    			});	
			
    	},
    	error: function(data) {
    		alert('Oops! Error');
    	}
	});
	
});

$(document).on('click', "button.delete", function(){
	var list = $(this).closest("li");
	var id= list.attr('id');
	console.log(id);
	$.ajax({
	    url: '/categories/'+id,
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
	
	var list = $(this).closest("li");
	var value= list.attr('value');
	console.log(value);
	var editItemBox = "<form class='edit_input_box'><input type='text' class='itembox' value='"+value+"'>";
	editItemBox+="<div class='btn-group pull-right'>";
	editItemBox+="<button class='submit btn btn-primary'>Submit</button>";
	editItemBox+="<button type='button' class='back btn btn-default'>Back</button>";
	editItemBox+="</div></form>";
	//$(".edit_input_box").val(value);
	var deleteButton = "<button class='delete btn btn-warning'>Delete</button>";
	var editButton = "<button class='edit btn btn-success'>Edit</button>";
	var twoButtons = "<div class='btn-group pull-right'>" + deleteButton + editButton + "</div>";
	$(this).closest("div.text_holder").replaceWith(editItemBox);

});
$(document).on('submit', "form.edit_input_box", function(){
	//event.preventDefault();
	var list = $(this).closest("li");
	var id= list.attr('id');
	console.log($(".itembox").val());
	console.log(id);
	$.ajax({
	    url: '/categories/'+id,
	    type: 'PUT',
	    dataType: 'json',
	    data: JSON.stringify( {
	    					categoryId : id,
	    					categoryName : $(".itembox").val()
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

$(document).on('click', '.glyphicon', function(){

	if ($(this).hasClass("glyphicon-plus")) {
		//$(".list-group-item").hide();
		var addButton = '<input type="submit" value="Add" class="add btn btn-primary"/>';
		var backButton = '<button type="button" class="back btn btn-default">Back</button>';
		var twoButtons = "<div class='btn-group pull-right'>" + addButton + backButton + "</div>";
		var row = '<li class="list-group-item"><input class="cname" type="text" placeholder="Add category"/>'+twoButtons+'</li>';
		$('#viewCategories').append(row);
	}
});

$(document).on('click', '.back', function(){
	location.reload();
});

$(document).on('click', '.add', function(){
	var categoryName = $(".cname").val();
	if(categoryName == '')
	alert("Please enter a valid name" );
	else{
		console.log(categoryName);
		
		$.ajax({
		    url: '/categories/',
		    type: 'POST',
		    dataType: 'json',
		    data: JSON.stringify( {
		    					categoryName : categoryName
		    		}
		    		),
		    contentType: "application/json",
		    success: function() {
		    	console.log('success');
		    	location.reload();
		    }, 
		    error: function (xhr) {
		    	//location.reload();
		      }
		});
	}
});