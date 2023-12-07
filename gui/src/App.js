import React, { useEffect, useState } from 'react';
import './App.css';
import DatePickerComponent from './components/DatePickerComponent';
import DataTableComponent from './components/DataTableComponent';
import CurrencyConverterComponent from './components/CurrencyConverterComponent';
import LineChartComponent from './components/LineChartComponent';
import DatePickerRange from './components/DatePickerRange';
import './css/SearchStyles.css';
import fetchDataFromApi from './fechAPI/API';
import { formatDate, formatDateChart } from './utils/util';

function App() {
  const [selectedDate, setSelectedDate] = useState(new Date());

  const handleDateChange = date => {
    setSelectedDate(date);
  };

  const columns = [
    { Header: 'STT', accessor: 'STT' },
    { Header: 'Ngoại tệ', accessor: 'currency' },
    { Header: 'Tên ngoại tệ', accessor: 'name' },
    { Header: 'Tỷ giá', accessor: 'exchangeRate' },
  ];
  const [data, setData] = useState(null);
  // gọi api lấy ra bảng giá trị 
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(`http://localhost:8080/api/exchangeRateMart?date=${formatDate(selectedDate)}`);
        const result = await response.json();
        result.forEach((item, index) => {
          item.STT = index + 1;
        });
        setData(result);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();

  }, [selectedDate]); // Thêm một mảng trống để useEffect chỉ chạy một lần sau khi component mount

  const [listOptionSelect, setListOptionSelect] = useState(null);
  // gọi api lấy ra currency
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(`http://localhost:8080/api/currency-dim`);
        const result = await response.json();
        setListOptionSelect(result);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();
  }, []);
  // gọi api lấy ra name currency
  const [nameCurrency, setNameCurrency] = useState(null);
  // gọi api lấy ra currency
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(`http://localhost:8080/api/name-dim`);
        const result = await response.json();
        setNameCurrency(result);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();
  }, []);

  
    const [selectedOption, setSelectedOption] = useState('EUR'); // Khởi tạo giá trị mặc định
    const handleSelectChange = (event) => {
      setSelectedOption(event.target.value);
    };
    const [selectedDateRange, setSelectedDateRange] = useState({ startDate: null, endDate: null });

    const handleDateRangeChange = ({ startDate, endDate }) => {
      setSelectedDateRange({ startDate, endDate });
    };

    const [chartData, setChartData] = useState({
      labels: [],
      datasets: [
        {
          label: "Currency Exchange Rate",
          data: [],
          borderColor: "rgba(75,192,192,1)"
        }
      ]
    });
    const handleSearch = () => {

      const fetchData = async () => {
        const requestData = {
          dateStart: formatDate(selectedDateRange.startDate),
          dateEnd: formatDate(selectedDateRange.endDate),
          nameCurrency: selectedOption
        };
  
        try {
          const data = await fetchDataFromApi(requestData);
          setChartData({
            labels: data?.map(item => formatDateChart(item.date)),
            datasets: [
              {
                label: "Currency Exchange Rate",
                data: data?.map(item => item.exchangeRate),
                borderColor: "rgba(75,192,192,1)"
              }
            ]
          })
        } catch (error) {
          // Xử lý lỗi nếu có
        }
      };
  
      (selectedDateRange.startDate && selectedDateRange.endDate) && fetchData();
    }
  
  return (
    <div style={{padding: '140px'}}>
      <h1 style={{textAlign: 'center'}}>Chuyển đổi tiền tệ</h1>
      <DatePickerComponent selectedDate={selectedDate} handleDateChange={handleDateChange} />
      <CurrencyConverterComponent data={data} listOptionSelect={listOptionSelect} selectedDate={selectedDate}/>
      <h1>Bảng tỷ giá chéo của đồng Việt Nam với một số ngoại tệ</h1>
       {
        data && <DataTableComponent columns={columns} data={data} />
       }
      <div style={{ padding: '100px' }}>
        <h1>Biểu diễn đồ thị:</h1>
      <div className='search-container'>
        <div className='select-container'>
          <label className="label-select">Chọn tiêu chí:</label>
          <select className="select-dropdown" value={selectedOption} onChange={handleSelectChange}>
            { nameCurrency && nameCurrency.map((item, index) => {
                    return <option key={index} value={item.name}>{item.name}</option>
                  })}
          </select>
        </div>
        <DatePickerRange onDateRangeChange={handleDateRangeChange} />
        <button style={{ cursor: 'pointer',padding: '0 24px',marginLeft: '24px', backgroundColor: '#4bc0c0', border: 'none', color: '#fff'}} 
         className='search-button' onClick={handleSearch}>
          Tìm kiếm
        </button>
      </div>
    
    </div>
      <LineChartComponent data={chartData} />
    </div>
  );
}

export default App;
