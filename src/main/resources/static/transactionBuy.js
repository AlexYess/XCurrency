
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

    fetch(`http://localhost:8080/transactions/approve/${transactionID}`,
        { method: 'GET', headers: {} })
        .catch(error => {
            console.error('Ошибка:', error);
        });
    row.color = 'green';
}

function addTransaction()
{

}
