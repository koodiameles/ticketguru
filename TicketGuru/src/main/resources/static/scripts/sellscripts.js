
url = globalvariable.g_url;
pass = globalvariable.g_pass;
msg = globalvariable.g_msg;

var saleid;
var ticketamount;
var evs;
var tickettosale = new Array();
var seleventindex;
var selttypeid;
var seleventid;
var customprice = 0;
var toomanytickets = false;

$(document).ready(function () {
  $(function () {

    $("#buyticket").hide();

    $("#evdropdown").empty();
    $("#evdropdown").append(
      '<option selected="true" disabled>Valitse tapahtuma</option>'
    );
    $("#evdropdown").prop("selectedIndex", 0);

    // Populate event dropdown
    const evurl = url + "events/";
    $.ajax({
      url: evurl,
      type: "GET",
      dataType: "json",
      headers: { Authorization: "Basic " + pass },
      contentType: "application/json; charset=utf-8",
      success: function (data) {
        evs = data;

        $.each(data, function (index, event) {
          $("#evdropdown").append(
            $("<option></option>")
              .attr("value",  index)
              .attr("eventidValue",  event.eventid )
              .text(event.description + " (" + event.ticketcount + " lippua jäljellä)")
          );
        });
      },
      error: function (error) {
        console.log(error);
      },
    });
    
  });

  // Populate tickettype dropdown when event is chosen
  $("#evdropdown").change(function () {
    $("#ttdropdown").empty();
    $("#ttdropdown").append(
      '<option selected="true" disabled>Valitse lipputyyppi</option>'
    );
    $("#ttdropdown").prop("selectedIndex", 0);

    seleventindex = $("#evdropdown").val();
    selectedEventidValue = $("#evdropdown").find('option:selected').attr('eventidValue');

    $.each(evs[seleventindex].tickettypes, function (key, ttype) {
      $("#ttdropdown").append(
        $("<option></option>")
          .attr("value", ttype.tickettypeid)
          .text(ttype.name + " (" + ttype.price + " €)")
      );
    });
    $("#price").val(null);
  });

  // When tickettype is chosen
  $("#ttdropdown").change(function () {
    selttypeid = $("#ttdropdown").val();

    // Set tickettype price to price input 
    const ttypeurl = url + "tickettypes/" + selttypeid;
    $.ajax({
      url: ttypeurl,
      method: "GET",
      dataType: "json",
      headers: { Authorization: "Basic " + pass },
      contentType: "application/json; charset=utf-8",
      success: function (data) {
        $("#price").val(data.price);
        customprice = data.price;
      },
      error: function (error) {
        console.log(error);
      },
    });

    if ($("#tiamount").val() != "") {
      $("#buyticket").show();
    }

    if ($("#tiamount").val() > 10) {
      $("#buyticket").hide();
      document.getElementById("result").innerHTML = "Voit ostaa korkeintaan 10 lippua kerrallaan.";
    } else if ($("#tiamount").val() <= 10) {
      document.getElementById("result").innerHTML = "";
    }

    $("#addticket").removeAttr("disabled");
  });

  // When price changes
  $("#price").change(function () {
    customprice = $("#price").val();
  });

  // When ticketamount changes
  $("#tiamount").change(function () {
    ticketamount = $("#tiamount").val();

    if ($("#ttdropdown").val() != null) {
      $("#buyticket").show();
    }

    if ($("#tiamount").val() == "") {
      $("#buyticket").hide();
    }

    if ($("#tiamount").val() > 10) {
      $("#buyticket").hide();
      document.getElementById("result").innerHTML = "Voit ostaa korkeintaan 10 lippua kerrallaan.";
    } else if ($("#tiamount").val() <= 10) {
      document.getElementById("result").innerHTML = "";
    }
  });

  //  Clicking the "Osta liput" button
  $("#buyticket").click(function (e) {
    // Check ticket amount
    const saleurl = url + "sales";
    function ajaxCheckTicketamount() {
      return $.ajax({
        url: url + "events/" + (Number(selectedEventidValue)),
        method: "GET",
        dataType: "json",
        headers: { Authorization: "Basic " + pass },
        contentType: "application/json; charset=utf-8",
        data: null,
        success: function (data) {
          // Count how many tickets left
          var eventamount = data.ticketcount;
          var ticketsLeft = eventamount - ticketamount;
          data.ticketcount = ticketsLeft;
          if (ticketsLeft < 0) {
            document.getElementById("result").innerHTML = "Tapahtumaan on lippuja jäljellä vain " + eventamount + " ja yritit ostaa " + ticketamount + "! Myyntitapahtuma epäonnistui.";
            toomanytickets = true;
            return;
          }
          e.preventDefault();
          // Decrease the number of tickets available
          $.ajax({
            url: url + "events/" + (Number(selectedEventidValue)),
            method: "PUT",
            dataType: "json",
            headers: { Authorization: "Basic " + pass },
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            success: function (data) {},
            error: function (error) {
              console.log(error);
            },
          });
        },
        error: function (error) {
          console.log(error);
        },
      });
    }

    function ajaxSellTickets() {
      // Post new sale
      return $.ajax({
        url: saleurl,
        method: "POST",
        dataType: "json",
        headers: { Authorization: "Basic " + pass },
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({ employee: null }),
        success: function (data) {
          // Add tickets to sale (POST)
          saleid = data.saleid;
          const titosaleurl = url + "sales/" + data.saleid + "/tickets";
          e.preventDefault();
          for (var i = 0; i < ticketamount; i++) {
            $.ajax({
              url: titosaleurl,
              method: "POST",
              dataType: "json",
              headers: { Authorization: "Basic " + pass },
              contentType: "application/json; charset=utf-8",
              data: JSON.stringify({
                eventid: Number(selectedEventidValue),
                tickettypeid: selttypeid,
                price: customprice
              }),
              async: false, // Sync request to prevent table population before all tickets have been created
              success: function (titosaledata) {
                document.getElementById("result").innerHTML = "Lippujen ostaminen onnistui!";
              },
              error: function (error) {
                console.log(error);
              },
            });
          }
        },
        error: function (error) {
          console.log(error);
        },
      });
    }

    $.when(ajaxCheckTicketamount()).then(function () {
      if (!toomanytickets) {
        // Execute this when ajax for checking ticketamount is done
        $.when(ajaxSellTickets()).then(function () {
          // Reset dropdowns, ticketamount and price
          $("#evdropdown").prop("selectedIndex", 0);
          $("#ttdropdown").empty();
          $("#tiamount").val(null);
          ticketamount = 0;
          $("#price").val(null);
          customprice = 0;
          $("#buyticket").hide();

          document.getElementById("saleinfo").innerHTML =
            "Myyntitapahtuma " + saleid;

          // Add sold tickets to table
          const salegeturl = saleurl + "/" + saleid;

          $(".hidden").css("display", "block"); // Show hidden table
          $("#tbody").empty(); // Empty table body
          $.ajax({
            url: salegeturl,
            method: "GET",
            dataType: "json",
            headers: { Authorization: "Basic " + pass },
            contentType: "application/json; charset=utf-8",
            success: function (saledata) {
              var ticket;
              
              // ITERATING THROUGH OBJECTS
              $.each(saledata.tickets, function (key, value) {
                var myQR = "https://api.qrserver.com/v1/create-qr-code/?data=" + value.ticketcode;
                const qrCodeString = `<img src="${myQR} width="70" height="70">`
                // CONSTRUCTION OF ROWS HAVING
                // DATA FROM JSON OBJECT
                ticket += "<tr>";

                ticket += "<td>" + value.event.description + "</td>";

                ticket += "<td>" + value.tickettype.name + "</td>";

                ticket += "<td>" + value.ticketprice + " € </td>";

                ticket += "<td>" + value.valid + "</td>";

                ticket += "<td>" + value.ticketcode + "</td>";

                ticket += "<td>" + qrCodeString + "</td>";   

                ticket += "</tr>";
                
              });
              
              //INSERTING ROWS INTO TABLE
              $("#table").append(ticket);
            },
            error: function (error) {
              console.log(error);
            },
          });

          // Repopulate event dropdown with updated ticketamount
          $("#evdropdown").empty();
          $("#evdropdown").append(
            '<option selected="true" disabled>Valitse tapahtuma</option>'
          );
          $("#evdropdown").prop("selectedIndex", 0);
          const evurl = url + "events/";

          $.ajax({
            url: evurl,
            type: "GET",
            dataType: "json",
            headers: { Authorization: "Basic " + pass },
            contentType: "application/json; charset=utf-8",
            success: function (data) {
              evs = data;
              $.each(data, function (index, event) {
                $("#evdropdown").append(
                  $("<option></option>")
                    .attr("value",  index)
                    .attr("eventidValue",  event.eventid )
                    .text(event.description + " (" + event.ticketcount + " lippua jäljellä)")
                );
              });
            },
            error: function (error) {
              console.log(error);
            },
          });
        });
      }
      toomanytickets = false;
    });
  });
});
