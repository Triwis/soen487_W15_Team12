(function ($) {

$(document).ready(function () {

    var ex_url = "http://localhost:8080/Part3/exchange/tmx/business";
    var b_code = "SOEN";

    console.log("\n\nRUNNING JSON TESTS\n\n");

    // Test creating a new business
    console.log("\nCreating a new business.");
    $.ajax(ex_url,
    {
        type: "POST",
        data: {
            symbol: "SOEN",
            shares: [
                {
                    "symbol": "SOEN",
                    "shareType": "common",
                    "unitPrice": 52.00
                },
                {
                    "symbol": "SOEN",
                    "shareType": "preferred",
                    "unitPrice": 145.00
                },
                {
                    "symbol": "SOEN",
                    "shareType": "convertible",
                    "unitPrice": 200.00
                }
            ]
        },
        async: false,
        success: function (response) {
            console.log("Done creating business. Response from webserver:");
            console.log(response);
        }
    });

    // Fetch created business
    console.log("\nFetching business " + b_code);
    $.ajax(ex_url + "/" + "b_code",
    {
        type: "GET",
        dataType: "json",
        async: false,
        success: function (response) {
            console.log("Done fetching business. Response:");
            console.log(response);
        }
    });

    // Test updating the newly created business
    console.log("\nUpdating the stock price of the business...");
    $.ajax(ex_url,
    {
        type: "PUT",
        data: {
            symbol: "SOEN",
            shares: [
                {
                    "symbol": "SOEN",
                    "shareType": "common",
                    "unitPrice": 52.00
                },
                {
                    "symbol": "SOEN",
                    "shareType": "preferred",
                    "unitPrice": 145.00
                },
                {
                    "symbol": "SOEN",
                    "shareType": "convertible",
                    "unitPrice": 200.00
                }
            ]
        },
        async: false,
        success: function (response) {
            console.log("Done updating. Response:");
            console.log(response);
        }
    });

    // Fetch created business
    console.log("\nFetching updated business...");
    $.ajax(ex_url + "/" + "b_code",
    {
        type: "GET",
        dataType: "json",
        async: false,
        success: function (response) {
            console.log("Done fetching. Response");
            console.log(response);
        }
    });

    // Test deleting the business
    console.log("\nDeleting the business...");
    $.ajax(ex_url + "/" + b_code,
    {
        type: "DELETE",
        async: false,
        success: function (response) {
            console.log("Business deleted.");
        }
    });
});

})(jQuery);
