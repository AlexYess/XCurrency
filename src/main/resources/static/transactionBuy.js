
function getBuyers() {
    const dataDiv = document.getElementById('data');
    const curFromInput = document.getElementById("curFromID");
    const curToInput = document.getElementById("curToID");

    const curFrom = curFromInput.value.toLowerCase();
    const curTo = curToInput.value.toLowerCase();

    fetch(`/transaction/findbuyer/${curTo}/${curFrom}`)
        .then(response => response.json())
        .then(async dataList => {
            let html = '';
            for (const data of dataList) {
                const {buyerID, rate, amount, price, transactionID} = data;
                html += '<table>';
                html += '<tr><th>Username</th><th>Rate</th><th>Amount</th><th>Price</th></tr>';
                html += '<tr>'
                await fetch('/users?id=' + buyerID, {method: 'GET', headers: {},})
                    .then(res => res.json())
                    .then(res => {
                        html += '<td>' + res.username + '</td>';
                    });
                html += `<td>${rate}</td>`;
                html += `<td>${amount}</td>`;
                html += `<td>${price}</td>`;

            }

            if (html === '') {
                html = `<p>We could not find any seller :(</p>`;
            }

            dataDiv.innerHTML = html;
        })
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


    fetch('/transaction', {
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
