function sendData(data) {
    const rawResponse = fetch('/currencies', {
        method: 'POST', headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json' },
        body: JSON.stringify(data) })
        .then(res => res.json()) .then(responseData => {
            window.location.replace(`/currencies/${responseData.id}`); })
        .catch(error => {
            let answerDiv = document.getElementById('answer'); answerDiv.innerHTML = `Error: ${error}`;
        }) }
function searchButton() {
    const newItem = {};
    newItem.name = document.getElementById("currencyCodeInput").value;
    sendData(newItem); }