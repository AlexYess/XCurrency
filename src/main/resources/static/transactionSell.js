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
