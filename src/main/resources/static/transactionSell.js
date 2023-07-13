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

function getSellers()
{
    const dataDiv = document.getElementById('data');
    const curFromInput = document.getElementById("curFromID");
    const curToInput = document.getElementById("curToID");

    const curFrom = curFromInput.value.toLowerCase();
    const curTo = curToInput.value.toLowerCase();

    fetch(`http://localhost:8080/transaction/findseller/${curFrom}/${curTo}`,
        {method: 'GET', headers: {}})
        .then(response => response.json())
        .then(dataList => {
            let html = '';
            dataList.forEach(data => {
                const { sellerID, rate, amount, price } = data;
                html += `<p>Seller ID: ${sellerID}</p>`;
                html += `<p>Rate: ${rate}</p>`;
                html += `<p>Amount: ${amount}</p>`;
                html += `<p>Price: ${price}</p>`;
                html += '<hr>';
            });
            dataDiv.innerHTML = html;
        })
        .catch(error => {
            console.error('Ошибка:', error);
        });
}