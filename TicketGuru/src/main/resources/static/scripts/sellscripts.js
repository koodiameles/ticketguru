  var url = 'https://ticketguru22.herokuapp.com/';
//   var url = 'http://localhost:8080/';

  var pass = btoa('admin:admin'); 
  var saleid;
  var ticketamount = 1;
  var evs;
  var tickettosale = new Array();
  var selevent;
  var selttype;
  var customprice;


   $(document).ready(function() {
      

      $(function() {
          let evdropdown = $('#evdropdown');
          
          evdropdown.empty();

          evdropdown.append('<option selected="true" disabled>Valitse tapahtuma</option>');
          evdropdown.prop('selectedIndex', 0);

          const evurl = url + 'events/';

          //Populate event dropdown
          $.ajax({
              url: evurl,
              type: 'GET',
              dataType: 'json',
              headers: {'Authorization': 'Basic ' + pass},
              contentType: 'application/json; charset=utf-8',
              success: function (data) {
                  evs = data;
                  $.each(data, function (key, event) {
                      evdropdown.append($('<option></option>').attr('value', (event.eventid -1)).text(event.description));
                  })
              },
              error: function (error) {
                  console.log(error);
              }
          });

      });

      //Tickettype dropdown
      $('#evdropdown').change(function() {
          let ttdropdown = $('#ttdropdown');

          ttdropdown.empty();

          ttdropdown.append('<option selected="true" disabled>Valitse lipputyyppi</option>');
          ttdropdown.prop('selectedIndex', 0);

          selevent = $('#evdropdown').val();
          console.log('selected event: ' + selevent);

          console.log(evs[selevent]);
          $.each(evs[selevent].tickettypes, function (key, ttype) {
              ttdropdown.append($('<option></option>').attr('value', ttype.tickettypeid).text(ttype.name + ' (' + ttype.price + ' €)'));
          })
          
      });
      
      $('#ttdropdown').change(function() {
          selttype = $('#ttdropdown').val();
          console.log('selected tickettype: ' + selttype);

          $('#addticket').removeAttr('disabled');
      });

      $('#tiamount').change(function() {
          ticketamount = $('#tiamount').val();
          console.log('ticketamount: ' + ticketamount);
      });
      
      $("#buyticket").click(function(e) {
          const saleurl = url + 'sales';

          function ajax1() {
              // Post new sale
              return $.ajax({
                  url: saleurl,
                  method: 'POST',
                  dataType: 'json',
                  headers: {'Authorization': 'Basic ' + pass},
                  contentType: 'application/json; charset=utf-8',
                  data: JSON.stringify({employee : null}),
                  success: function (data) {
                      // Add tickets to sale (POST)
                      saleid = data.saleid;
                      const titosaleurl = url + 'sales/' + data.saleid + '/tickets'
                      e.preventDefault();
                      for (i = 0; i < ticketamount; i++) {
                          $.ajax({
                              url: titosaleurl,
                              method: 'POST',
                              dataType: 'json',
                              headers: {'Authorization': 'Basic ' + pass},
                              contentType: 'application/json; charset=utf-8',
                              data: JSON.stringify({eventid: Number(selevent) +1,
                                  tickettypeid: selttype}),
                              success: function (titosaledata) {
                                  document.getElementById("result").innerHTML = "Lippujen ostaminen onnistui!"; 
                              },
                              error: function (error) {
                                  console.log(error);
                              }
                          });
                      }
                  },
                  error: function (error) {
                      console.log(error);
                  }
              });
          }

          // Execute this when ajax is done
          $.when(ajax1()).done(function(a1) {

              // Reset dropdowns and ticketamount HOW TO RESET TO 'Valitse tapahtuma:' etc, NOT EMPTY?
              $('#evdropdown').val(null);
              $('#ttdropdown').val(null);
              $('#tiamount').val(null);

              document.getElementById("saleinfo").innerHTML = "Myyntitapahtuma " + saleid; 
              
              // Add sold tickets to table 
              const salegeturl = saleurl + '/' + saleid
              console.log('salegeturl: ' + salegeturl);
          
              $(".hidden").css('display', 'block'); // Show hidden table
              $.ajax({
                  url: salegeturl,
                  method: 'GET',
                  dataType: 'json',
                  headers: {'Authorization': 'Basic ' + pass},
                  contentType: 'application/json; charset=utf-8',
                  success: function (saledata) {
                      var ticket;
                      // ITERATING THROUGH OBJECTS
                      $.each(saledata.tickets, function (key, value) {

                          // CONSTRUCTION OF ROWS HAVING
                          // DATA FROM JSON OBJECT
                          ticket += '<tr>';
                              
                          ticket += '<td>' + 
                              value.event.description + '</td>';
                                  
                          ticket += '<td>' +
                              value.tickettype.name + '</td>';
                                      
                          ticket += '<td>' + 
                              value.ticketprice + '</td>';
                                          
                          ticket += '<td>' + 
                              value.valid + '</td>';
                                              
                          ticket += '<td>' + 
                              value.ticketcode + '</td>';

                          ticket += '</tr>';
                          });
                      //INSERTING ROWS INTO TABLE 
                      $('#table').append(ticket);
                  },
                  error: function (error) {
                      console.log(error);
                  }
              });
          });            
        
      });
  
  });