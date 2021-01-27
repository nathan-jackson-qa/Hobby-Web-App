'use strict'

const createBtn = document.querySelector("#submit");
const titleValue = document.querySelector('#title');
const genreValue = document.querySelector('#genre');
const devValue = document.querySelector('#dev');
const platformValue = document.querySelector('#platform');
const createFeedback = document.querySelector('#feedback');


const create = () => {
    const title = titleValue.value;
    const genre = genreValue.value;
    const dev = devValue.value;
    const platform = platformValue.value;
    let devJSON = get(dev)
    if(title != "" || genre != "Genre:" || dev > 0 || platform != "Platform:") {
            fetch(`http://localhost:8080/game/create`, {
            method: 'POST',
            body: JSON.stringify({
                "developer": devJSON,
                "genre": genre,
                "platform": platform,
                "title": title
            }),
            headers: {
                'Content-Type': "application/json"
            }
            }).then(response => response.json())
        .catch(err => console.error("Game could not be created", error)).then(output => console.log(output))
    }
    else {
        return "Game could not be created, please ensure all fields are entered";
    }
}

const get = (id) => {
    fetch(`http://localhost:8080/game/read/${id}`)
    .then(response => response.json())
    .then(json => console.log(json))
    .catch(error => console.error("Error:", error))
}

const output = () => {
    let p = document.createElement("p");
    let feedback = document.createTextNode(create());
    p.appendChild(feedback);
    createFeedback.appendChild(p);
}

createBtn.addEventListener('click', output);