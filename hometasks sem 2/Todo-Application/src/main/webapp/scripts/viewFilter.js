window.onload = () => {document.getElementById("filter-button").addEventListener('click', changeFilter)}

function changeFilter() {
    if (document.body.style.filter != "grayscale(1)") {
        document.body.style.filter = "grayscale(1)"
    } else {
        document.body.style.filter = "none"
    }
}
