// CurrencyConverterComponent.js
import React, { useState } from 'react';
import '../css/CurrencyConverterComponent.css';

const CurrencyConverterComponent = ({ onConvert }) => {
  const [amount, setAmount] = useState('');
  const [currencyFrom, setCurrencyFrom] = useState('USD');
  const [currencyTo, setCurrencyTo] = useState('EUR');

  const handleAmountChange = (e) => {
    const newAmount = e.target.value;
    setAmount(newAmount);

    // Automatically trigger conversion when the amount changes
    onConvert({ amount: newAmount, currencyFrom, currencyTo });
  };

  const handleCurrencyFromChange = (e) => {
    const newCurrencyFrom = e.target.value;
    setCurrencyFrom(newCurrencyFrom);

    // Trigger conversion when the currencyFrom changes
    onConvert({ amount, currencyFrom: newCurrencyFrom, currencyTo });
  };

  const handleCurrencyToChange = (e) => {
    const newCurrencyTo = e.target.value;
    setCurrencyTo(newCurrencyTo);

    // Trigger conversion when the currencyTo changes
    onConvert({ amount, currencyFrom, currencyTo: newCurrencyTo });
  };

  return (
    <div className='currency'>
    <div className="currency-inputs">
        <div className='warrap'>
        <input type="number" value={amount} onChange={handleAmountChange} placeholder="Enter amount" />
            <select className='select' value={currencyFrom} onChange={handleCurrencyFromChange}>
                <option value="USD">USD</option>
                {/* Add more currency options as needed */}
            </select>
        </div>
      <span className="conversion-icon" role="img" aria-label="convert">
        ↔️
      </span>
      <div className='warrap'>
        <input type="number" value={amount} onChange={handleAmountChange} placeholder="" />
        <select className='select' value={currencyTo} onChange={handleCurrencyToChange}>
            <option value="EUR">EUR</option>
            {/* Add more currency options as needed */}
        </select>
      </div>
    </div>
    </div>

  );
};

export default CurrencyConverterComponent;
