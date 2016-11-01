var userId, aid,email;
$(document)
		.ready(
				function() {
					var deleteButton = "<button class='delete btn btn-warning'>Delete</button>";
					var editButton = "<button class='edit btn btn-success'>Edit</button>";
					var twoButtons = "<div class='btn-group pull-right'>"
							+ deleteButton + editButton + "</div>";
					$("#addAlarm").hide();
					function mapRecurrence(recurrence) {
						switch (recurrence) {
						case 1:
							return '9:00 HRS';
						case 2:
							return '1:00 HRS';
						case 3:
							return '7:00 HRS';
						case 4:
							return '(demo)';
						}
					}
					$
							.ajax({
								url : 'user',
								dataType : 'json',
								success : function(data) {
									userId = data.userId;
									console.log(userId);
									$
											.ajax({
												url : 'users/' + userId
														+ '/today',
												dataType : 'json',
												success : function(data) {
													$
															.each(
																	data,
																	function(i,
																			d) {
																		var row = '<li class="list-group-item" id="'
																				+ data[i].apillId
																				+ '" value="'
																				+ data[i].apillId
																				+ '">';
																		row += data[i].apillName
																				+ ' at <label>'
																				+ mapRecurrence(data[i].arecurrence)
																				+ '</label><span class="pull-right bigger blue2 glyphicon glyphicon-question-sign" data-poload="pills/'
																				+ data[i].apillId
																				+ '/alternatives"></span> </li>';
																		$(
																				'#alarmsToday')
																				.append(
																						row);
																	});
												}
											});

									$
											.ajax({
												url : 'users/' + userId
														+ '/alarms',
												dataType : 'json',
												success : function(data) {
													$
															.each(
																	data,
																	function(i,
																			d) {
																		var row = '<li class="list-group-item" id="'
																				+ data[i].aid
																				+ '" value="'
																				+ data[i].apillName
																				+ '">';
																		row += '<div class="text_holder" >';
																		row += '<label>'
																				+ data[i].apillName
																				+ '</label> from '
																				+ data[i].astartDate
																				+ ' to '
																				+ data[i].aendDate
																				+ twoButtons
																				+ '<div></li>';
																		$(
																				'#viewAlarms')
																				.append(
																						row);
																	});

												}
											});
								}
							});
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

$(document).on('mouseover', '*[data-poload]', function() {
	var e = $(this);
	$.get(e.data('poload'), function(data) {
		alternatives = "";
		$.each(data, function(i, d) {
			alternatives += data[i].pillName + ",";
		});
		if (data == "") {
			alternatives = "No alternatives found";
		}
		e.popover({
			trigger : "hover",
			title : "Alternative Pills",
			content : alternatives
		}).popover('toggle');
	});
});
$(document).on(
		'click',
		'.glyphicon',
		function() {

			if ($(this).hasClass("glyphicon-plus")) {
				$(".list-group").hide();
				$.ajax({
					url : 'categories',
					dataType : 'json',
					success : function(data) {

						$.each(data,
								function(i, d) {
									var row = '<option value="'
											+ data[i].categoryId + '">'
											+ data[i].categoryName
											+ '</option>';
									$('#selectCategory').append(row);
								});
					}
				});
				$("#addAlarm").show();
			}
		});

$(document).on('click', ".input-group", function() {
	$('#datetimepicker6').datetimepicker({
		format : "YYYY-MM-DD"
	});
	$('#datetimepicker7').datetimepicker({
		format : "YYYY-MM-DD",
		useCurrent : false
	// Important! See issue #1075
	});
	$("#datetimepicker6").on("dp.change", function(e) {
		$('#datetimepicker7').data("DateTimePicker").minDate(e.date);
	});
	$("#datetimepicker7").on("dp.change", function(e) {
		$('#datetimepicker6').data("DateTimePicker").maxDate(e.date);
	});
});

$(document).on('click', "button.delete", function() {
	var list = $(this).closest("li");
	var id = list.attr('id');
	$.ajax({
		url : 'alarms/' + id,
		type : 'DELETE'
	});
	$(this).closest("li").remove();
});
$(document).on(
		'change',
		'#selectCategory',
		function() {
			var id = $("#selectCategory option:selected").val();
			$('#selectPill').empty();
			$.ajax({
				url : 'categories/' + id + '/pills',
				dataType : 'json',
				success : function(data) {
					$.each(data, function(i, d) {
						var row = '<option value="' + data[i].pillId + '">'
								+ data[i].pillName + '</option>';
						$('#selectPill').append(row);
					});
				}
			});
		});
function formValidator(){
	var isValidated = true;
	var row = '<span class="glyphicon glyphicon-remove form-control-feedback"></span>';
	if ($("#selectCategory option:selected").val() == 0) {
		$(".formSelectCategory").addClass("has-error has-feedback");
		$(".formSelectCategory").append(row);
		isValidated = false;
	}
	if ($("#selectPill option:selected").val() == 0) {
		$(".formSelectPill").addClass("has-error has-feedback");
		$(".formSelectPill").append(row);
		isValidated = false;
	}
	if ($("input[name='endDate']").val() == "") {
		$(".formEndDate").append(row);
		$(".formEndDate").addClass("has-error has-feedback");
		isValidated = false;
	}
	if ($("input[name='startDate']").val() == "") {
		$(".formStartDate").append(row);
		$(".formStartDate").addClass("has-error has-feedback");
		isValidated = false;
	}
	if ($("#selectRecurrence option:selected").val() == 0) {
		$(".formSelectRecurrence").addClass("has-error has-feedback");
		$(".formSelectRecurrence").append(row);
		isValidated = false;
	}
	return isValidated;
}
function removeChecks(){
	$(".formSelectPill").removeClass("has-error has-feedback");
	$(".formSelectRecurrence").removeClass("has-error has-feedback");
	$(".formStartDate").removeClass("has-error has-feedback");
	$(".formEndDate").removeClass("has-error has-feedback");
	$(".formSelectCategory").removeClass("has-error has-feedback");
	$(".glyphicon-remove").remove();
}
$(document).on('click', '#addAlarmSubmit', function(ev) {
	removeChecks();
	if(formValidator()==false){
		ev.preventDefault();return;
	}
	$.ajax({
		url : 'alarms/',
		type : 'POST',
		dataType : 'json',
		data : JSON.stringify({
			apillId : $("#selectPill option:selected").val(),
			arecurrence : $("#selectRecurrence option:selected").val(),
			auserId : userId,
			astartDate : $("input[name='startDate']").val(),
			aendDate : $("input[name='endDate']").val()
		}),
		contentType : "application/json"
	});
});
$(document)
		.on(
				'click',
				"button.edit",
				function() {
					var editButton = '<input type="submit" id="editAlarmSubmit" value="Edit" class="btn btn-primary add_button"/>';
					var list = $(this).closest("li");
					var id = list.attr('id');
					aid = id;
					$(".list-group").hide();
					$.ajax({
						url : 'categories',
						dataType : 'json',
						success : function(data) {

							$.each(data, function(i, d) {
								var row = '<option value="'
										+ data[i].categoryId + '">'
										+ data[i].categoryName + '</option>';
								$('#selectCategory').append(row);
							});

						}
					});
					$.ajax({
						url : 'alarms/' + id,
						dataType : 'json',
						success : function(data) {
							$('#selectCategory').find(
									"option[value = '" + data.acategoryId
											+ "']")
									.attr("selected", "selected");
							$('#selectCategory').change();
							$('#selectPill').find(
									"option[value = '" + data.apillId + "']")
									.attr("selected", "selected");
							$('#selectRecurrence').find(
									"option[value = '" + data.arecurrence
											+ "']")
									.attr("selected", "selected");
							$('#startDate').val(data.astartDate);
							$('#endDate').val(data.aendDate);
						}
					});
					$(".modal-title").replaceWith("Edit the alarm");
					$("#addAlarmSubmit").replaceWith(editButton);
					$("#addAlarm").show();
				});
$(document).on('click', '#editAlarmSubmit', function(ev) {
	removeChecks();
	if(formValidator()==false){
		ev.preventDefault();return;
	}
	$.ajax({
		url : 'alarms/' + aid,
		type : 'PATCH',
		dataType : 'json',
		data : JSON.stringify({
			aid : aid,
			apillId : $("#selectPill option:selected").val(),
			arecurrence : $("#selectRecurrence option:selected").val(),
			auserId : userId,
			astartDate : $("input[name='startDate']").val(),
			aendDate : $("input[name='endDate']").val()
		}),
		contentType : "application/json"
	});
});