// LineChartComponent.js
import React from 'react';
import { Line } from 'react-chartjs-2';
import '../css/LineChartComponent.css';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  BarElement,
  Title,
  Tooltip,
  Legend,
  Filler,
  ArcElement,
  LineElement,
  TimeScale,
} from 'chart.js';

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  BarElement,
  Title,
  Tooltip,
  Legend,
  Filler,
  ArcElement,
  LineElement,
  TimeScale,
);

const LineChartComponent = ({ data }) => {

  return (
    <div className="line-chart">
      <Line data={data}  />
    </div>
  );
};

export default LineChartComponent;
