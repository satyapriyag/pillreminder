$(document)
		.ready(
				function() {
					$(".alert").hide();
					var deleteButton = "<button class='delete btn btn-warning'>Delete</button>";
					var editButton = "<button class='edit btn btn-success'>Edit</button>";
					var twoButtons = "<div class='btn-group pull-right'>"
							+ deleteButton + editButton + "</div>";

					$.ajax({
						url : 'categories',
						dataType : 'json',
						success : function(data) {

							$.each(data, function(i, d) {
								var row1 = '<li class="list-group-item" id="'
										+ data[i].categoryId + '" value="'
										+ data[i].categoryName + '">';
								var row2 = '<option value="'
										+ data[i].categoryId + '">'
										+ data[i].categoryName + '</option>';
								row1 += '<div class="text_holder" >';
								row1 += '<label>' + data[i].categoryName
										+ '</label>' + twoButtons
										+ '<div></li>';
								$('#viewCategories').append(row1);
								$('#selectCategory').append(row2);
							});
						}
					});
					$('.addPillPlus').hide();
				});
$(document).on('click', "button.delete", function() {
	var list = $(this).closest("li");
	var id = list.attr('id');
	var url = 'categories/';
	if ($(this).hasClass('deletePill')) {
		url = 'pills/'
	}
	console.log(id);
	$.ajax({
		url : url + id,
		type : 'DELETE'
	});
	$(this).closest("li").remove();
});
$(document)
		.on(
				'click',
				"button.edit",
				function() {
					var name='Category';
					var list = $(this).closest("li");
					var value = list.attr('value');
					var editItemBox = "<form class='edit";
					var editPill = "_input_box'><input type='text' class='itembox' value='"
							+ value + "'>";
					if ($(this).hasClass("editPill")) {
						editPill = "_pill_input_box'><input type='text' class='itembox' value='"
								+ value + "'>";
						editPill += '<select id="editCategory" name="editCategoryId"></select>';
						name = 'Pill';
					}
					editItemBox += editPill;
					editItemBox += "<div class='btn-group pull-right'>";
					editItemBox += "<button class='submit btn btn-primary'>Submit</button>";
					editItemBox += "<button type='button' class='back"+name+" btn btn-default'>Back</button>";
					editItemBox += "</div></form>";

					$(this).closest("div.text_holder").replaceWith(editItemBox);
					$('#selectCategory option').clone().appendTo(
							'#editCategory');
					var selectedValue = $("#selectCategory option:selected")
							.val();
					$('#editCategory').find(
							"option[value = '" + selectedValue + "']").attr(
							"selected", "selected");
				});
$(document).on('submit', "form.edit_input_box", function() {
	var list = $(this).closest("li");
	var id = list.attr('id');
	$.ajax({
		url : 'categories/' + id,
		type : 'PUT',
		dataType : 'json',
		data : JSON.stringify({
			categoryId : id,
			categoryName : $(".itembox").val()
		}),
		contentType : "application/json",
		success : function() {
			console.log('success');
		},
		error : function(xhr) {
			location.reload();
		}
	});
});
$(document).on('submit', "form.edit_pill_input_box", function() {
	var categoryId = $("#selectCategory option:selected").val();
	var list = $(this).closest("li");
	var id = list.attr('id');
	console.log(id + $(".itembox").val());
	$.ajax({
		url : 'pills/' + id,
		type : 'PATCH',
		dataType : 'json',
		data : JSON.stringify({
			pillId : id,
			pillName : $(".itembox").val(),
			pillCategoryId : $("#editCategory option:selected").val()
		}),
		contentType : "application/json",
		success : function() {
			$('#selectCategory').find(
					"option[value = '" + categoryId + "']").attr(
					"selected", "selected");
			$('#selectCategory').change();
			//location.reload();
			//console.log('success');
		},
		error : function(xhr) {
			location.reload();
		}
	});
});
$(document)
		.on(
				'click',
				'.glyphicon',
				function() {

					if ($(this).hasClass("glyphicon-plus")) {
						var name = 'Category';
						if ($(this).hasClass("addPillPlus")) {
							name = 'Pill';
						}
						var addButton = '<input type="submit" value="Add" class="btn btn-primary add'
								+ name + '"/>';
						var backButton = '<button type="button" class="back'+name+' btn btn-default">Back</button>';
						var twoButtons = "<div class='btn-group pull-right'>"
								+ addButton + backButton + "</div>";
						var row = '<li class="list-group-item"><input class="cname" type="text" placeholder="Add '
								+ name + '"/>' + twoButtons + '</li>';
						$('#viewPillsList').append(row);
						$('#viewCategories').append(row);
					}
				});
$(document).on('click', '.addCategory', function() {
	var categoryName = $(".cname").val();
	if (categoryName == '')
		$(".alert").show();
	else {
		$.ajax({
			url : 'categories/',
			type : 'POST',
			dataType : 'json',
			data : JSON.stringify({
				categoryName : categoryName
			}),
			contentType : "application/json",
			success : function() {
				console.log('success');
				location.reload();
			},
			error : function(xhr) {
			}
		});
	}
});
$(document).on(
		'click',
		'.addPill',
		function() {
			var pillName = $(".cname").val();
			var categoryId = $("#selectCategory option:selected").val();
			if (pillName == '')
				$(".alert").show();
			else {
				$.ajax({
					url : 'pills/',
					type : 'POST',
					dataType : 'json',
					data : JSON.stringify({
						pillName : pillName,
						pillCategoryId : categoryId
					}),
					contentType : "application/json",
					success : function() {
						console.log('success');
						//location.reload();
						$('#selectCategory').find(
								"option[value = '" + categoryId + "']").attr(
								"selected", "selected");
						$('#selectCategory').change();
					},
					error : function(xhr) {
					}
				});
			}
		});
$(document)
		.on(
				'change',
				'#selectCategory',
				function() {
					var id = $("#selectCategory option:selected").val();
					if (id == 0) {
						$('.addPillPlus').hide();
					} else {
						$('.addPillPlus').show();
					}
					var deleteButton = "<button class='delete btn btn-warning deletePill'>Delete</button>";
					var editButton = "<button class='edit btn btn-success editPill'>Edit</button>";
					var twoButtons = "<div class='btn-group pull-right '>"
							+ deleteButton + editButton + "</div>";
					$('#viewPillsList').empty();
					$.ajax({
						url : 'categories/' + id + '/pills',
						dataType : 'json',
						success : function(data) {

							$.each(data, function(i, d) {
								var row = '<li class="list-group-item" id="'
										+ data[i].pillId + '" value="'
										+ data[i].pillName + '">';
								row += '<div class="text_holder" >';
								row += '<label>' + data[i].pillName
										+ '</label>' + twoButtons
										+ '<div></li>';
								$('#viewPillsList').append(row);

							});

						}
					});
				});
$(document).on('click', '.backCategory', function() {
	location.reload();
});
$(document).on('click', '.backPill', function() {
	$(".alert").hide();
	var id = $("#selectCategory option:selected").val();
	$('#selectCategory').find(
			"option[value = '" + id + "']").attr(
			"selected", "selected");
	$('#selectCategory').change();
	
});
$(document).on('click', '.logout', function() {
	$.ajax({
		url : 'logout',
		type : 'POST',
		success : function() {
			window.location.href = './';
		}
	});
});