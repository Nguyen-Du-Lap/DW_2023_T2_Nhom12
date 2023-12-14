// CurrencyConverterComponent.js
import React, { useEffect, useState } from 'react';
import '../css/CurrencyConverterComponent.css';

const CurrencyConverterComponent = ({ data, listOptionSelect, selectedDate }) => {
  const [amount, setAmount] = useState('');
  const [result, setResult] = useState('');
  const [currencyFrom, setCurrencyFrom] = useState('EUR');
  const [currencyTo, setCurrencyTo] = useState('EUR');
  function getExchangeRatesCurrency(currency) {
    const exchangeRate = data && data.find(item => item.currency === currency);
    return exchangeRate ? exchangeRate.exchangeRate : 0;
  }
  function caculatorCurrency(amount, currencyFrom, currencyTo) {
    return amount * getExchangeRatesCurrency(currencyFrom) / getExchangeRatesCurrency(currencyTo);
  }
  const handleAmountChange = (e) => {
    const newAmount = e.target.value;
    setAmount(newAmount);
  };

  const handleCurrencyFromChange = (e) => {
    const newCurrencyFrom = e.target.value;
    setCurrencyFrom(newCurrencyFrom);
  };

  const handleCurrencyToChange = (e) => {
    const newCurrencyTo = e.target.value;
    setCurrencyTo(newCurrencyTo);

  };

  useEffect(() => {
    setResult(caculatorCurrency(amount, currencyFrom, currencyTo).toFixed(2));
  }, [amount])

  useEffect(() => {
    setAmount('')
    setResult('')
  }, [currencyFrom, currencyTo, selectedDate])

  return (
    <div className='currency'>
      <div className="currency-inputs">
          <div className='warrap'>
          <input type="number" value={amount} onChange={handleAmountChange} placeholder="Enter amount" />
              <select className='select' value={currencyFrom} onChange={handleCurrencyFromChange}>

                  { listOptionSelect && listOptionSelect.map((item, index) => {
                    return <option key={index} value={item.name_currency}>{item.name_currency}</option>
                  })}
              </select>
          </div>
        <span className="conversion-icon" role="img" aria-label="convert">
          ↔️
        </span>
        <div className='warrap'>
          <input type="number" value={result} placeholder="" />
          <select className='select' value={currencyTo} onChange={handleCurrencyToChange}>
            { listOptionSelect && listOptionSelect.map((item, index) => {
                  return  <option key={index} value={item.name_currency}>{item.name_currency}</option>
                  })}
          </select>
        </div>
      </div>
    </div>

  );
};

export default CurrencyConverterComponent;
