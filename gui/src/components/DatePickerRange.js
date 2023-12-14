// DatePickerRange.js
import React, { useState } from 'react';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import '../css/DatePickerRange.css';

const DatePickerRange = ({ onDateRangeChange }) => {
  const [startDate, setStartDate] = useState(null);
  const [endDate, setEndDate] = useState(null);

  const handleStartDateChange = (date) => {
    setStartDate(date);
    onDateRangeChange && onDateRangeChange({ startDate: date, endDate });
  };

  const handleEndDateChange = (date) => {
    setEndDate(date);
    onDateRangeChange && onDateRangeChange({ startDate, endDate: date });
  };
  // Lấy ngày hiện tại
  const currentDate = new Date();

  return (
    <div style={{display: 'flex', justifySelf: 'start'}}>
      <div style={{marginLeft: '24px'}}>
        <label>Start Date: </label>
        <DatePicker
          selected={startDate}
          onChange={handleStartDateChange}
          maxDate={currentDate} // Chỉ cho phép chọn ngày trở về trước
        />
      </div>
      <div style={{marginLeft: '24px'}}>
        <label>End Date: </label>
        <DatePicker
          selected={endDate}
          onChange={handleEndDateChange}
          maxDate={currentDate} // Chỉ cho phép chọn ngày trở về trước
        />
      </div>
    </div>
  );
};

export default DatePickerRange;
