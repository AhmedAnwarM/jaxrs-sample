//TODO: Execute this code with and without the CORSFilter class to see the importance of the Allow-Origin header

var xhttp = new XMLHttpRequest();
xhttp.onreadystatechange = function() {
    if (this.readyState === 4 && this.status === 200) {
        console.log(xhttp.responseText)
    }
};
xhttp.open("GET", "http://localhost:8880/jaxrs-samples/students", true);
xhttp.send();