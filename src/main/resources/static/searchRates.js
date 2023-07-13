function openForm() {
    const form = document.createElement("form");
    const input = document.createElement("input");
    const button = document.createElement("button");

    form.id = "currencyForm";
    input.id = "amount";
    button.textContent = "Apply";
    button.type = "button";
    button.addEventListener("click", applyChanges);

    form.appendChild(input);
    form.appendChild(button);

    document.body.appendChild(form);
}

function applyChanges() {
    // Получение значения из поля ввода
    const amount = document.getElementById("amount").value;
    const cur1 = document.getElementById("currencyNameInput1").value;
    const cur2 = document.getElementById("currencyNameInput2").value;
    const json = sessionStorage.getItem("user");



    fetch('http://localhost:8080/transaction', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            "transactionID": generateUniqueRandomValue(),
            "sellerID": 0,
            "buyerID": json['userID'],
            "currencyCodeFrom": cur1,
            "currencyCodeTo": cur2,
            "rate": 0.0,
            "amount": amount,
            "expiryDate": formatDate(Date.now()),
            "approved": false
        })
    })
        .then(r => r.json())


    const form = document.getElementById("currencyForm");
    form.reset();
}

function generateUniqueRandomValue() {
    const currentDate = new Date();
    const randomValue = currentDate.getTime() + Math.floor(Math.random() * 1000);
    return randomValue;
}

function clickCreateButton() {
    const currency1 = document.getElementById('currencyNameInput1').value.toLowerCase();
    const currency2 = document.getElementById('currencyNameInput2').value.toLowerCase();

    fetch(`https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/${currency1}/${currency2}.json`)
        .then(resp => resp.json())
        .then(data => showCurrencies(data))
        .catch(function (error) {
            console.error(error);
        });
}

function showCurrencies(data) {
    console.log(data);
    const dataDiv = document.getElementById('data');
    const keys = Object.keys(data); // Get the keys of the JSON object
    if (keys.length > 0) {
        const currencyPair = `${document.getElementById('currencyNameInput1').value.toUpperCase()}/${keys[1].toUpperCase()}`; // Format the currency pair
        const exchangeRate = data[keys[1]]; // Get the value of the second key
        dataDiv.innerHTML = `<p style="color: green; font-size: 30px; font-weight: bolder">${currencyPair}: ${exchangeRate}</p>`;
        dataDiv.innerHTML += '<div id="buyButtonDiv" style="padding-top: 5px">\n' +
            '    <input type="button" value="Place Order" onclick="openForm()"/><br>\n' +
            '</div>'
    } else {
        dataDiv.innerHTML = "Invalid API response";
    }


}

function formatDate(date) {
    const d = new Date(date);
    const year = d.getFullYear();
    const month = String(d.getMonth() + 1).padStart(2, '0');
    const day = String(d.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
}