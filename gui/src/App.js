import React, { useState } from 'react';
import './App.css';
import DatePickerComponent from './components/DatePickerComponent';
import DataTableComponent from './components/DataTableComponent';
import CurrencyConverterComponent from './components/CurrencyConverterComponent';
import LineChartComponent from './components/LineChartComponent';
import DatePickerRange from './components/DatePickerRange';
import './css/SearchStyles.css';

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
  const chartData = {
    labels: ["2022-01-01", "2022-02-01", "2022-03-01", "2022-04-01", "2022-05-01", "2022-06-01","2022-01-01", "2022-02-01"],
    datasets: [
      {
        label: "Currency Exchange Rate",
        data: [110.0, 80.0, 115.0, 112.0, 90, 130],
        borderColor: "rgba(75,192,192,1)"
      }
    ]
  };
  
    // Handle the conversion logic here
    function handleConvert({ amount, currencyFrom, currencyTo }) {
      // Implement your conversion logic
      console.log(`Converting ${amount} from ${currencyFrom} to ${currencyTo}`);
    }
    const [selectedOption, setSelectedOption] = useState('EUR'); // Khởi tạo giá trị mặc định

    const handleSelectChange = (event) => {
      setSelectedOption(event.target.value);
    };
    const [selectedDateRange, setSelectedDateRange] = useState({ startDate: null, endDate: null });

    const handleDateRangeChange = ({ startDate, endDate }) => {
      setSelectedDateRange({ startDate, endDate });
      console.log(selectedDateRange.startDate?.toLocaleDateString())
      console.log(selectedDateRange.endDate?.toLocaleDateString())
    };

  return (
    <div style={{padding: '140px'}}>
      <h1 style={{textAlign: 'center'}}>Chuyển đổi tiền tệ</h1>
      <DatePickerComponent selectedDate={selectedDate} handleDateChange={handleDateChange} />
      <CurrencyConverterComponent onConvert={handleConvert} />
      <h1>Bảng tỷ giá chéo của đồng Việt Nam với một số ngoại tệ</h1>
      <DataTableComponent columns={columns} data={data} />
      <div style={{ padding: '100px' }}>
        <h1>Biểu diễn đồ thị:</h1>
      <div className='search-container'>
        <div className='select-container'>
        <label className="label-select">Chọn tiêu chí:</label>
        <select className="select-dropdown" value={selectedOption} onChange={handleSelectChange}>
          <option value="EUR">EUR</option>
          <option value="USD">USD</option>
          <option value="GBP">GBP</option>
        </select>
      </div>
      <DatePickerRange onDateRangeChange={handleDateRangeChange} />
      </div>
      
    </div>
      <LineChartComponent data={chartData} />
    </div>
  );
}

export default App;
