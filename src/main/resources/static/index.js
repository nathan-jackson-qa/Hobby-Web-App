'use strict'

const createBtn = document.querySelector("#create");
const titleValue = document.querySelector('#title');
const genreValue = document.querySelector('#genre');
const devValue = document.querySelector('#dev');
const platformValue = document.querySelector('#platform');
const createFeedback = document.querySelector('#createFeedback');

const readIDBtn = document.querySelector("#readID");
const readID = document.querySelector('#gameID')
const readFeedback = document.querySelector('#readDetails');

const readAllBtn = document.querySelector("#readAll");
const readAllFeedback = document.querySelector('#readAllDetails');

const updateBtn = document.querySelector("#update");
const updID = document.querySelector('#updateID')
const updTitle = document.querySelector('#updTitle');
const updGenre = document.querySelector('#updGenre');
const updDev = document.querySelector('#updDev');
const updPlat = document.querySelector('#updPlat');
const updateFeedback = document.querySelector('#updateFeedback');

const deleteBtn = document.querySelector("#delete");
const deleteID = document.querySelector('#deleteID')
const deleteFeedback = document.querySelector('#deleteFeedback');

const create = () => {
    createFeedback.innerHTML = "";
    const title = titleValue.value;
    const genre = genreValue.value;
    const dev = devValue.value;
    const platform = platformValue.value;
    let feedback = "";

    if (title != "" || genre != "Genre:" || dev > 0 || platform != "Platform:") {
        fetch(`http://localhost:8080/game/create`, {
            method: 'POST',
            body: JSON.stringify({
                "developer": { "id": dev },
                "genre": genre,
                "platform": platform,
                "title": title
            }),
            headers: {
                'Content-Type': "application/json"
            }
        }).then(response => response.json())
            .then(json => {
                feedback += `Game ID: ${json.id}, Title: ${json.title}, Genre: ${json.genre}, Platform: ${json.platform}, `;
            }).then(get => {
                fetch(`http://localhost:8080/developer/read/${dev}`)
                    .then(response => response.json())
                    .then(jsonDev => {
                        feedback += `Developer: ${jsonDev.name}`;
                        output(createFeedback, feedback);
                    }).catch(error => console.error("Error:", error))
            }).catch(err => console.error("Game Could not be created: " + err))
    } else {
        return "Game could not be created, please ensure all fields are entered";
    }
}

const read = () => {
    readFeedback.innerHTML = "";
    const readIDValue = readID.value;
    if (readIDValue > 0) {
        fetch(`http://localhost:8080/game/read/${readIDValue}`)
            .then(response => response.json())
            .then(json => {
                let readDetails = `Game ID: ${json.id}, Title: ${json.title}, Genre: ${json.genre}, Platform: ${json.platform}`;
                output(readFeedback, readDetails);
            }).catch(err => console.error("Something went wrong: " + err))
    }
}

const readAll = () => {
    readAllFeedback.innerHTML = "";
    fetch(`http://localhost:8080/game/readAll`)
        .then(response => response.json())
        .then(json => {
            for (let i = 0; i < json.length; i++) {
                let details = `Game ID: ${json[i].id}, Title: ${json[i].title}, Genre: ${json[i].genre}, Platform: ${json[i].platform}`;
                output(readAllFeedback, details);
            }
        }).catch(err => console.error("Something went wrong: " + err))
}

const update = () => {
    updateFeedback.innerHTML = "";
    const id = updID.value;
    const title = updTitle.value;
    const genre = updGenre.value;
    const dev = updDev.value;
    const platform = updPlat.value;
    let check = false;
    fetch(`http://localhost:8080/game/update/${id}`, {
        method: "PUT",
        body: JSON.stringify({
            "developer": { "id": dev },
            "title": title,
            "genre": genre,
            "platform": platform
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
    fetch(`http://localhost:8080/game/delete/${deleteValue}`, { method: "DELETE" })
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