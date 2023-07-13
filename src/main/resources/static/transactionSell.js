function showPrice(){
    const curFromInput = document.getElementById("curFromID");
    const curToInput = document.getElementById("curToID");
    const amountInput = document.getElementById("amountInput");

    const curFrom = curFromInput.value.toLowerCase();
    const curTo = curToInput.value.toLowerCase();
    const amount = amountInput.value;


    function changeText(data) {
        const keys = Object.keys(data);
        if (keys.length > 0)
        {
            const rate = data[keys[1]];
            document.getElementById('outPrice').textContent = (rate * amount).toFixed(3);
        }
        return undefined;
    }

    fetch(`https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/${curFrom}/${curTo}.json`)
        .then(resp => resp.json())
        .then(data => changeText(data))
        .catch(function (error) {
            console.error(error);
        });

    document.getElementById('curFromID').value = curFrom;
    curToInput.value = curTo;
    amountInput.value = amount;
}

function getSellers() {
    const dataDiv = document.getElementById('data');
    const curFromInput = document.getElementById("curFromID");
    const curToInput = document.getElementById("curToID");

    const curFrom = curFromInput.value.toLowerCase();
    const curTo = curToInput.value.toLowerCase();

    fetch(`http://localhost:8080/transaction/findseller/${curFrom}/${curTo}`)
        .then(response => response.json())
        .then(dataList => {
            let html = '';
            let i = 0;
            dataList.forEach(data => {
                const { sellerID, rate, amount, price, transactionID } = data;
                const rowID = `row_${transactionID}`; // ID HTML строки

                html += `<div id="${rowID}">`; // Открываем div с ID строки
                html += `<p>Buyer ID: ${sellerID}</p>`;
                html += `<p>Rate: ${rate}</p>`;
                html += `<p>Amount: ${amount}</p>`;
                html += `<p>Price: ${price}</p>`;
                html += `<button onclick="approveTransaction(${transactionID})" data-id="${rowID}">Approve</button>`; // Передаем ID строки в параметр data-id
                html += '</div>'; // Закрываем div строки

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

    fetch(`http://localhost:8080/transactions/approve/${transactionID}`,
        { method: 'GET', headers: {} })
        .catch(error => {
            console.error('Ошибка:', error);
        });
    row.color = 'green';
}

function addTransactionS()
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
            "sellerID": userID,
            "buyerID": 0,
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