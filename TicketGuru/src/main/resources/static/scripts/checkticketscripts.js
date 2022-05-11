
url = globalvariable.g_url;
pass = globalvariable.g_pass;
msg = globalvariable.g_msg;

// Get ticket with ticketcode
async function findTicket() {
    let fturl = url + "tickets?code="
    try {
        var code = document.getElementById("code").value; 
        const response = await fetch(fturl + code, {
                method: 'GET',
                headers: {'Authorization': 'Basic ' + pass}
            }); 

        if (response.status == 404) {
            msg ="Koodilla " + " <br /> " + code + " <br /> " +" ei löydy lippua."; 
            document.getElementById("result").innerHTML = msg; 
        } else if (response.status == 403) {
            msg ="Sinulla ei ole oikeuksia hakea lippua."; 
            document.getElementById("result").innerHTML = msg; 
        } else if (response.status == 200) {
            msg ="Koodilla " + " <br /> " + code + " <br /> " + " löytyi lippu.";
            document.getElementById("result").innerHTML = msg; 
        } 
        else {
            msg ="Jokin men vikaan";
            document.getElementById("result").innerHTML = msg; 
        }
    } catch (error) {
        console.log(error); 
        msg = "Lippua ei voitu hakea";
        document.getElementById("result").innerHTML = msg; 
    }
}

// Use ticket with ticketcode
async function useTicket() {
    var uturl = url + "tickets?code="
    try {
        var code = document.getElementById("code").value;
        const response = await fetch(uturl + code, {
            method: 'PATCH',
            headers: {
                'Authorization': 'Basic ' + pass,
                'Content-Type': 'application/json'
            }
            }); 
        const json = await response.json(); 
        console.log(json); 
        if (response.status == 200) {
        msg = "Lippu koodilla " + " <br /> " +  code +  " <br /> " + " on nyt käytetty."
        document.getElementById("result").innerHTML = msg; 
        } else if (response.status == 404) {
        msg = "Lippua koodilla " + " <br /> " +  code + " ei ole olemassa."
        document.getElementById("result").innerHTML = msg; 
        } else if (response.status == 400) {
        msg = "Lippu koodilla " + " <br /> " +  code + " <br /> " + "on jo käytetty, sitä ei voi käyttää uudelleen."
        document.getElementById("result").innerHTML = msg; 
        } else {
        msg = "Jokin meni vikaan"
        document.getElementById("result").innerHTML = msg; 
        }
    } catch (error) {
    console.log(error); 
    msg = "Lippua ei voitu merkitä käytetyksi."; 
    document.getElementById("result").innerHTML = msg; 
    }
}

// Get and list all tickets
async function findAllTickets() {
        
    let faturl = url + "tickets";

    $(".hidden").css('display', 'block'); // Show hidden elements
    $("#liput").empty(); // Empty table body
    try {
        $.ajax({
            headers: {
                'Authorization': 'Basic ' + pass,
                'Content-Type': 'application/json'
            },
            dataType: "json",
            url: faturl,
            success: function(data) {
                var ticket = '';

                // ITERATING THROUGH OBJECTS
                $.each(data, function (key, value) {
                    var myQR = "https://api.qrserver.com/v1/create-qr-code/?data=" + value.ticketcode;
                    const qrCodeString = `<img src="${myQR}" width="70" height="70">`
                    //CONSTRUCTION OF ROWS HAVING
                    // DATA FROM JSON OBJECT
                    ticket += '<tr>';
                    ticket += '<td>' + 
                        value.ticketcode + '</td>';

                    ticket += '<td>' + 
                        value.event.description + '</td>';

                    ticket += '<td>' + 
                        value.ticketprice + ' € </td>';

                    ticket += '<td>' + 
                        value.valid + '</td>';

                    ticket += "<td>" + qrCodeString + "</td>";   

                    ticket += '</tr>';
                });
                //INSERTING ROWS INTO TABLE 
                $('#table').append(ticket);
            }
        });
    } catch (error) {
    console.log(error); 
    }
}
