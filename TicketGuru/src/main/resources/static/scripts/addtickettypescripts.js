
url = globalvariable.g_url;
pass = globalvariable.g_pass;
msg = globalvariable.g_msg;


async function processForm(e){
    e.preventDefault();
    const form = document.getElementById("tickettype-form");
    const formData = new FormData(form); // Luodaan lomakkeen tiedoista FormData-olio
    const requestBody = processFormData(formData); // Metodi, joka luo FormDatasta halutun requestbodyn
    const response = await submitForm(JSON.stringify(requestBody));

    if(response.message == null){ 
        console.log(response);
        document.getElementById("msg").innerHTML = "Lipputyyppi on lisätty!";
    }else{
        document.getElementById("msg").innerHTML = response.message;
    }
}

function processFormData(formData){
    let requestBody = {};

    for(let [key, value] of formData){ // Käydään FormData avain-arvo-pari kerrallaan läpi, ja mikäli arvo on tyhjä merkkijono, vaihdetaan sen tilalle null
        if(value === ''){
            value = null;
        }
        console.log(key + ' + ' + value);
        requestBody[key] = value;
    }
    console.log('requestbody: ' + requestBody);
    return requestBody;
}

async function getEvents(){
    const events = await fetchEvents();
    populateEventSelection(events);
}

async function submitForm(request){
    try{
        const data = await fetch(`${url}tickettypes`, {
            method: 'POST',
            body: request,
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Basic ' + pass
            }
        });

        if(data.status === 201){
            return await data.json();
        }else{
            return {message: "Lipputyyppi on jo olemassa."};
        }
        
    }catch(error){
        return error;
    }
}

async function fetchEvents(){
    try{
        const response = await fetch(`${url}events`, {
            method: 'GET',
            headers: {
                'Authorization': 'Basic ' + pass
            }
        });
        return await response.json();
    }catch(error){
        return error;
    }
}

function populateEventSelection(events){
    let innerHtmlStr = "";

    for(event of events){
        innerHtmlStr += `<option value="${event.eventid}">${event.description}</option>`
    }

    document.getElementById("event").innerHTML = innerHtmlStr;
}

getEvents();