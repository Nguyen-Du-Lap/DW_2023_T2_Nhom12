import React, { useState } from 'react';
import './App.css';
import DatePickerComponent from './components/DatePickerComponent';
import DataTableComponent from './components/DataTableComponent';
import CurrencyConverterComponent from './components/CurrencyConverterComponent';

function App() {
  const [selectedDate, setSelectedDate] = useState(new Date());

  const handleDateChange = date => {
    setSelectedDate(date);
    // Fetch data for the selected date if needed
  };

  const columns = [
    { Header: 'STT', accessor: 'stt' },
    { Header: 'Ngoại tệ', accessor: 'currencyCode' },
    { Header: 'Tên ngoại tệ', accessor: 'currencyName' },
    { Header: 'Tỷ giá', accessor: 'exchangeRate' },
  ];

  const data = [
    { stt: 1, currencyCode: 'USD', currencyName: 'US Dollar', exchangeRate: 1.0 },
    { stt: 2, currencyCode: 'EUR', currencyName: 'Euro', exchangeRate: 0.85 },
    { stt: 3, currencyCode: 'GBP', currencyName: 'British Pound', exchangeRate: 0.75 },
    { stt: 4, currencyCode: 'JPY', currencyName: 'Japanese Yen', exchangeRate: 110.0 },
    { stt: 5, currencyCode: 'AUD', currencyName: 'Australian Dollar', exchangeRate: 1.3 },
    { stt: 6, currencyCode: 'CAD', currencyName: 'Canadian Dollar', exchangeRate: 1.25 },
    { stt: 7, currencyCode: 'CHF', currencyName: 'Swiss Franc', exchangeRate: 0.92 },
    { stt: 8, currencyCode: 'CNY', currencyName: 'Chinese Yuan', exchangeRate: 6.45 },
    { stt: 9, currencyCode: 'INR', currencyName: 'Indian Rupee', exchangeRate: 74.5 },
    { stt: 10, currencyCode: 'NZD', currencyName: 'New Zealand Dollar', exchangeRate: 1.42 },
    // Add more data as needed
  ];
  
    // Handle the conversion logic here
    function handleConvert({ amount, currencyFrom, currencyTo }) {
      // Implement your conversion logic
      console.log(`Converting ${amount} from ${currencyFrom} to ${currencyTo}`);
    }

  return (
    <div style={{padding: '100px'}}>
      <DatePickerComponent selectedDate={selectedDate} handleDateChange={handleDateChange} />
      <CurrencyConverterComponent onConvert={handleConvert} />
      <h1>Bảng tỷ giá chéo của đồng Việt Nam với một số ngoại tệ</h1>
      <DataTableComponent columns={columns} data={data} />
    </div>
  );
}

export default App;
