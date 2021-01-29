'use strict'

const createBtn = document.querySelector("#create");
const devName = document.querySelector('#name');
const createFeedback = document.querySelector('#createFeedback');

const readIDBtn = document.querySelector("#readID");
const readID = document.querySelector('#devID')
const readFeedback = document.querySelector('#readDetails');

const readAllBtn = document.querySelector("#readAll");
const readAllFeedback = document.querySelector('#readAllDetails');

const updateBtn = document.querySelector("#update");
const updID = document.querySelector('#updateID')
const updName = document.querySelector('#updName');
const updateFeedback = document.querySelector('#updateFeedback');

const deleteBtn = document.querySelector("#delete");
const deleteID = document.querySelector('#deleteID')
const deleteFeedback = document.querySelector('#deleteFeedback');

const create = () => {
    createFeedback.innerHTML = "";
    const nameValue = devName.value;
    let feedback = "";

    if (nameValue != "") {
        fetch(`http://localhost:8080/developer/create`, {
            method: 'POST',
            body: JSON.stringify({
                "name": nameValue
            }),
            headers: {
                'Content-Type': "application/json"
            }
        }).then(response => response.json())
            .then(json => {
                feedback += `Developer ID: ${json.id}, Name: ${json.name}`;
                output(createFeedback, feedback);
            }).catch(err => console.error("Developer could not be created: " + err))
    } else {
        return "Developer could not be created, please enter a valid name";
    }
}

const read = () => {
    readFeedback.innerHTML = "";
    const readIDValue = readID.value;
    if (readIDValue > 0) {
        fetch(`http://localhost:8080/developer/read/${readIDValue}`)
            .then(response => response.json())
            .then(json => {
                let readDetails = `Developer ID: ${json.id}, Name: ${json.name} `;
                for(let i = 0; i<json.games.length;i++) {
                    readDetails += `Game ${i+1}: ${json.games[i].title}`;
                }
                output(readFeedback, readDetails);
            }).catch(err => console.error("Something went wrong: " + err))
    }
}

const readAll = () => {
    readAllFeedback.innerHTML = "";
    fetch(`http://localhost:8080/developer/readAll`)
        .then(response => response.json())
        .then(json => {
            for (let i = 0; i < json.length; i++) {
                let details = `Developer ID: ${json[i].id}, Name: ${json[i].name} `;
                for(let j = 0; j<json[i].games.length;j++) {
                    details += `Game ${j+1}: ${json[i].games[j].title}`;
                }
                output(readAllFeedback, details);
            }
        }).catch(err => console.error("Something went wrong: " + err))
}

const update = () => {
    updateFeedback.innerHTML = "";
    const id = updID.value;
    const devName = updName.value
    let check = false;
    fetch(`http://localhost:8080/developer/update/${id}`, {
        method: "PUT",
        body: JSON.stringify({
            "name": devName
        }),
        headers: {
            "Content-type": "application/json; charset=UTF-8",
        }
    }).then(response => {
        if (response.status === 202) {
            check = true;
            response.json();
        }
    }).then(json => {
        let details = null;
        if (check == true) {
            details = `Update Successful`;
        }
        else {
            details = `Update Failed`;
        }
        output(updateFeedback, details);
    }).catch(err => console.error(err))
}


const deleteGame = () => {
    deleteFeedback.innerHTML = "";
    const deleteValue = deleteID.value;
    let p = document.createElement("p");
    let feedback = undefined;
    fetch(`http://localhost:8080/developer/delete/${deleteValue}`, { method: "DELETE" })
        .then(response => {
            if (response.status != 204) {
                feedback = "Item could not be deleted";
            }
            else {
                feedback = "Item Deleted!";
            }
            output(deleteFeedback, feedback);
        }).catch(err => console.error("Something went wrong: " + err))
}

const output = (element, text) => {
    let p = document.createElement("p");
    let details = document.createTextNode(text);
    p.appendChild(details);
    element.appendChild(p);
}

createBtn.addEventListener('click', create);
readIDBtn.addEventListener('click', read);
readAllBtn.addEventListener('click', readAll);
updateBtn.addEventListener('click', update);
deleteBtn.addEventListener('click', deleteGame);