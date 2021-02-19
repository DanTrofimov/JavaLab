function editTodo(id) {

    let todoPar = document.getElementById(id + "-text");
    let todoButton = document.getElementById(id + "-button");
    let todoButtonDelete = document.getElementById(id + "-delete-button");
    let todoButtonCancel = document.getElementById(id + "-cancel-button");
    let todoInput = document.getElementById(id + "-input");

    setTimeout(() => {
        if (todoInput.style.display == "none") {
            todoInput.style.display = "block";
            todoPar.style.display = "none";
            todoButton.innerText = "save";
            todoButtonCancel.style.display = "block";
            todoButtonDelete.style.display = "none";
        } else {
            todoInput.style.display = "none";
            todoPar.style.display = "block";
            todoButton.innerText = "change";
            todoButtonCancel.style.display = "none";
            todoButtonDelete.style.display = "block";
        }

        if (todoButton.type == "button") {
            todoButton.type = "submit"
        } else {
            todoButton.type = "button"
        }
    }, 100);
}