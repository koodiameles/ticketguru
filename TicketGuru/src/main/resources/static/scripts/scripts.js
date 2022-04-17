
var url = "https://ticketguru22.herokuapp.com/tickets?code="
var msg = ""
var pass = btoa('user:user')

async function findTicket() {
    try {
        var code = document.getElementById("code").value; 
        const response = await fetch(url + code, {
                method: 'GET',
                headers: {'Authorization': 'Basic ' + pass}
            }); 
        const json = await response.json(); 
        console.log(json); 

        if (response.status == 404) {
            msg ="Koodilla " + code + " ei löydy lippua."; 
            document.getElementById("result").innerHTML = msg; 
        } else if (response.status == 403) {
            msg ="Sinulla ei ole oikeuksia hakea lippua."; 
            document.getElementById("result").innerHTML = msg; 
        } else if (response.status == 200) {
            msg ="Koodilla " + code + " löytyi lippu.";
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
async function useTicket() {
    try {
        var code = document.getElementById("code").value;
        const response = await fetch(url + code, {
            method: 'PATCH',
            headers: {
                'Authorization': 'Basic ' + pass,
                'Content-Type': 'application/json'
            }
            }); 
        const json = await response.json(); 
        console.log(json); 
        if (response.status == 200) {
        msg = "Lippu koodilla " + code + " on nyt käytetty."
        document.getElementById("result").innerHTML = msg; 
        } else if (response.status == 404) {
        msg = "Lippua koodilla " + code + " ei ole olemassa."
        document.getElementById("result").innerHTML = msg; 
        } else if (response.status == 400) {
        msg = "Lippu koodilla " + code + " oli jo käytetty, sitä ei voi käyttää uudelleen."
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

