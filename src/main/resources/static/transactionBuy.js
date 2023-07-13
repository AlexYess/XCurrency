
function getBuyers() {
    const dataDiv = document.getElementById('data');
    const curFromInput = document.getElementById("curFromID");
    const curToInput = document.getElementById("curToID");

    const curFrom = curFromInput.value.toLowerCase();
    const curTo = curToInput.value.toLowerCase();

    fetch(`http://localhost:8080/transaction/findbuyer/${curTo}/${curFrom}`)
        .then(response => response.json())
        .then(dataList => {
            let html = '';
            let i = 0;
            dataList.forEach(data => {
                const { buyerID, rate, amount, price, transactionID } = data;
                const rowID = `row_${transactionID}`;

                html += `<div id="${rowID}">`;
                html += `<p>Seller ID: ${buyerID}</p>`;
                html += `<p>Rate: ${rate}</p>`;
                html += `<p>Amount: ${amount}</p>`;
                html += `<p>Price: ${price}</p>`;
                html += `<button onclick="approveTransaction(${transactionID})" data-id="${rowID}">Approve</button>`; // Передаем ID строки в параметр data-id
                html += '</div>';

                i++;
            });

            if (html === '') {
                html = `<p>We could not find any seller :(</p>`;
            }

            dataDiv.innerHTML = html;
        })
        .catch(error => {
            console.error('Ошибка:', error);
        });
}

function approveTransaction(transactionID) {
    const row = document.getElementById(transactionID);

    fetch(`http://localhost:8080/transaction/approve/${transactionID}`,
        { method: 'GET', headers: {} })
        .catch(error => {
            console.error('Ошибка:', error);
        });
}

function addTransactionB()
{
    const amount = document.getElementById("amountInput").value;
    const cur1 = document.getElementById("curFromID").value;
    const cur2 = document.getElementById("curToID").value;

    const json = sessionStorage.getItem("user");
    const jsonObject = JSON.parse(json);
    const userID = jsonObject['userID'];



    fetch('http://localhost:8080/transaction', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            "transactionID": generateUniqueRandomValue(),
            "sellerID": 0,
            "buyerID": userID,
            "currencyCodeFrom": cur1,
            "currencyCodeTo": cur2,
            "rate": 0.0,
            "amount": amount,
            "expiryDate": formatDate(Date.now()),
            "approved": false
        })
    })
        .then(response => response.json())

    alert('Transaction Added')
}

function generateUniqueRandomValue() {
    const currentDate = new Date();
    const randomValue = currentDate.getTime() + Math.floor(Math.random() * 1000);
    return randomValue;
}

function formatDate(date) {
    const d = new Date(date);
    const year = d.getFullYear();
    const month = String(d.getMonth() + 1).padStart(2, '0');
    const day = String(d.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
}
