document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("messageInput").addEventListener("keydown", function (event) {
        if (event.key === "Enter" && !event.shiftKey) {
            event.preventDefault(); // Prevent adding a newline
            sendRequest(); // Call the function to send the message
        }
    });
});
function sendRequest() {
    var message = document.getElementById("messageInput").value;
    var payload = JSON.stringify({ message: message });

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/untitled_war/testing");
    xhr.setRequestHeader("Content-type", "application/json");

    xhr.onload = function () {
        if (xhr.status === 200) {
            console.log("Message sent");
            document.getElementById("messageInput").value = "";
            addMessageToChat(message, "You");
            console.log(xhr.responseText);
            let data = xhr.responseText;
            const response = JSON.parse(xhr.responseText);
            if (response.reply) {
                addMessageToChat(response.reply, "Bot");
            }
        } else {
            console.log(`Error: ${xhr.status}`);
        }
    };

    xhr.onerror = function () {
        console.log("Network error occurred.");
    };
    console.log(payload);
    xhr.send(payload);
}


//Fills the Message Box with messages from the bot
function addMessageToChat(message, sender) {
    const messagesBox = document.getElementById("messagesBox");
    const newMessage = document.createElement("p");
    newMessage.textContent = sender+ ": " + message;
    messagesBox.appendChild(newMessage);
}