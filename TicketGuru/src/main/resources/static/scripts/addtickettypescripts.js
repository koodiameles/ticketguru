
url = globalvariable.g_url;
pass = globalvariable.g_pass;
msg = globalvariable.g_msg;


async function processForm(e) {
    e.preventDefault();
    const form = document.getElementById("tickettype-form");
    const formData = new FormData(form); // Create object FormData with data in form
    const requestBody = processFormData(formData); // Method to create desired requestbody out of FormData
    const response = await submitForm(JSON.stringify(requestBody));

    if (response.message == null) {
        document.getElementById("msg").innerHTML = "Lipputyyppi on lisätty!";
    } else {
        document.getElementById("msg").innerHTML = response.message;
    }
}

function processFormData(formData) {
    let requestBody = {};

    for (let [key, value] of formData) { // Loop through each key-value pair in FormData, if value is an empty string, replace it with null
        if (value === '') {
            value = null;
        }
        requestBody[key] = value;
    }
    return requestBody;
}

async function getEvents() {
    const events = await fetchEvents();
    populateEventSelection(events);
}

async function submitForm(request) {
    try {
        const data = await fetch(`${url}tickettypes`, {
            method: 'POST',
            body: request,
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Basic ' + pass
            }
        });

        if (data.status === 201) {
            return await data.json();
        } else {
            return {message: "Lipputyyppi on jo olemassa."};
        }
        
    } catch(error) {
        return error;
    }
}

async function fetchEvents(){
    try {
        const response = await fetch(`${url}events`, {
            method: 'GET',
            headers: {
                'Authorization': 'Basic ' + pass
            }
        });
        return await response.json();
    } catch(error) {
        return error;
    }
}

function populateEventSelection(events) {
    let innerHtmlStr = "";

    for (event of events) {
        innerHtmlStr += `<option value="${event.eventid}">${event.description}</option>`
    }

    document.getElementById("event").innerHTML = innerHtmlStr;
}

getEvents();