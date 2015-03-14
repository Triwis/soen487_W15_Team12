$(document).ready(function() {
    // Setup some validation rules
    $("#temperature-form").validate({
        rules: {
            temperature: {
                required:true,
                number: true
            }
        },
        messages: {
            temperature: {
                required: "Enter new temperature",
                number: "Decimal numbers only!"
            }
        },
        // Override default error placement
        errorPlacement: function (error, element) {
            displayAlert('error', error.text());
        }
    });

    // Load default temperature
    showTemperature(fetchTodaysTemperature());

    // Handle click event for update btn
    $("#btn-update-temperature").click(function() {
        // Validate form inputs
        if($("#temperature-form").valid()) {
            // Perform update & display new temperature
            updateTemperature($('#temperature').val());
            showTemperature(fetchTodaysTemperature());
        }
        
        return false;
    });
});

/**
 * Fetch today's temperature
 *
 * @return string
 */
function fetchTodaysTemperature() {
    var temperature = '0.0';

    $.ajax({
        type: "GET",
        url: '/SOEN487_P2_2/api/temperature',
        dataType: "xml",
        contentType: "application/xml; charset=\"utf-8\"",
        async: false,
        success: function (result) {
            temperature = $(result).find("temperature").text();
        }
    });

    return temperature;
}

/**
 * Update today's temperature
 *
 * @param  newTemperature New temperature
 * @return void
 */
function updateTemperature(newTemperature) {
    $.ajax({
        type: "PUT",
        url: '/SOEN487_P2_2/api/temperature',
        dataType: "xml",
        data: "<temperature>" + newTemperature + "</temperature>",
        contentType: "application/xml; charset=\"utf-8\"",
        async: false,
        success: function (result) {
            displayAlert('success', 'Temperature has been updated!');
        }
    });
}

/**
 * Display temperature
 *
 * @param  temperature Temperature
 * @return void
 */
function showTemperature(temperature) {
    $('#todays-temperature').html(temperature);
}

/**
 * Display alert
 *
 * @param  alertType Alert Type (error|success)
 * @param  msg       Message
 * @return void
 */
function displayAlert(alertType, msg) {
    if(alertType == 'success') {
        $('#alerts').html('<div class="alert alert-success" role="alert">' + msg + '</div>');
    } else if(alertType == 'error') {
        $('#alerts').html('<div class="alert alert-danger" role="alert">' + msg + '</div>');
    }
}

