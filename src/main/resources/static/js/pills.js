$(document).ready(function(){
	$('#editItems').hide();
	$('#addItems').hide();
	$('#categories').hide();
	
	$('#getCategories').click(function(){
		$('#editItems').hide();
		$('#addItems').hide();
		$('#categories').empty();

		$.ajax({
	    	url: '/categories',
	    	dataType: 'json',
	    	success: function(data){
	    	
	    		$.each(data, function(i, d) {
	    			   var row='<br/><input type="radio" name="categories" id="'+data[i].categoryId+'" value="'+data[i].categoryId+'"/>'+data[i].categoryName; 			   	
	    			   row+='<br/>';
	    			   console.log(data[i].categoryId+""+data[i].categoryName);
	    			   $('#categories').append(row);
	    			   
	    			});	
				
	    	},
	    	error: function(data) {
	    		alert('Oops! Error');
	    	}
		});

		$('#categories').show();

});
	
	$(document).on('click', '#goBack', function(){
		console.log("clicked");
		window.location.href='/';
	});
	
	$(document).on('click','#deleteButton',function(){
		
		var id= $("input[name='categories']:checked"). val();
		console.log(id);
		$.ajax({
		    url: '/categories/'+id,
		    type: 'DELETE',
		    success: function() {
		    	$("#categories").load(location.href + " #categories");
		    	$('#getCategories').click();
		    }, 
		    error: function() {
		    }
		});
		$('input[type=radio]#'+id).remove();
	});
		$(document).on('click','#editButton',function(){
		var id= $("input[name='categories']:checked"). val();
		localStorage.setItem("id", id);
			$.ajax({
				url: '/categories/'+id,
				type: 'GET',
				dataType: 'json',
				success: function(data) { 
					console.log(id);
					$('#cname').val(data.categoryName);
				},
				error: function(data) {
					alert('Oops Error!');
				}
			});
		$('#categories').hide();
		$('#editItems').show();
			
	});
	
		$(document).on('click','#editSubmit', function(){
			var id = $("input[name='categories']:checked"). val();
			console.log(id);
			console.log('hello');
			
			$.ajax({
			    url: '/categories/'+id,
			    type: 'PUT',
			    dataType: 'json',
			    data: JSON.stringify( {
			    					categoryId : id,
			    					categoryName : $('#cname').val()
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
		
		$(document).on('click','#addCategories',function(){
			$('#categories').hide();
			$('#addItems').show();
				
		});
		
			$(document).on('click','#addSubmit', function(){
				console.log('hello');
				
				$.ajax({
				    url: '/categories/',
				    type: 'POST',
				    dataType: 'json',
				    data: JSON.stringify( {
				    					categoryName : $('#addname').val()
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
	
});