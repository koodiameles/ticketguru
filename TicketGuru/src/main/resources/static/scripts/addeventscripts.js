
url = globalvariable.g_url;
pass = globalvariable.g_pass;
msg = globalvariable.g_msg;

async function processForm(e){
    e.preventDefault();
    const form = document.getElementById("event-form");
    const formData = new FormData(form); // Luodaan lomakkeen tiedoista FormData-olio
    const requestBody = processFormData(formData); // Metodi, joka luo FormDatasta halutun requestbodyn
    const response = await submitForm(JSON.stringify(requestBody));

    if(response.message == null){ 
        console.log(response);
        document.getElementById("msg").innerHTML = "Tapahtuma on lisätty!";
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

        requestBody[key] = value;
    }

    return requestBody;
}

async function submitForm(request){
    try{
        const data = await fetch(`${url}events`, {
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
            return {message: "Jotain meni pieleen, yritä uudelleen."};
        }
        
    }catch(error){
        return error;
    }
}