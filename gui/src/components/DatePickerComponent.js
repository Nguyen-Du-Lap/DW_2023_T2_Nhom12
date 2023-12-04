// DatePickerComponent.js
import React from 'react';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import '../css/DatePickerComponent.css';

const DatePickerComponent = ({ selectedDate, handleDateChange }) => {
  return (
    <div className="date">
      <DatePicker
        selected={selectedDate}
        onChange={handleDateChange}
        dateFormat="dd/MM/yyyy"
        customInput={<CustomDatePickerInput />}
      />
    </div>
  );

};

const CustomDatePickerInput = ({ value, onClick }) => (
  <button className="custom-datepicker-input" onClick={onClick}>
    <span role="img" aria-label="calendar">
      📅
    </span>
    {value}
  </button>
);

export default DatePickerComponent;
