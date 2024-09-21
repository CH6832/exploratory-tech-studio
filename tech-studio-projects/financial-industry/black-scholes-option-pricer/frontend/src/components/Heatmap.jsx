import React from 'react';
import { Chart } from 'react-chartjs-2';
import { Chart as ChartJS, LinearScale, CategoryScale, BarElement, Title, Tooltip, Legend } from 'chart.js';

ChartJS.register(LinearScale, CategoryScale, BarElement, Title, Tooltip, Legend);

const generateHeatmapData = (minSpotPrice, maxSpotPrice, minVolatility, maxVolatility) => {
  // Generate synthetic data for the heatmap
  let data = [];
  for (let spot = minSpotPrice; spot <= maxSpotPrice; spot += 1) {
    for (let vol = minVolatility; vol <= maxVolatility; vol += 0.1) {
      // Calculate PnL or other metrics here
      data.push({ x: spot, y: vol, value: Math.random() }); // Replace Math.random() with actual PnL calculation
    }
  }
  return data;
};

function Heatmap() {
  // Example parameters, ideally these should be passed as props or managed via state
  const minSpotPrice = 50;
  const maxSpotPrice = 150;
  const minVolatility = 0.1;
  const maxVolatility = 1.0;
  
  const heatmapData = generateHeatmapData(minSpotPrice, maxSpotPrice, minVolatility, maxVolatility);

  const data = {
    datasets: [{
      label: 'PnL Heatmap',
      data: heatmapData,
      backgroundColor: 'rgba(255, 99, 132, 0.5)',
      borderColor: 'rgba(255, 99, 132, 1)',
      borderWidth: 1,
    }]
  };

  const options = {
    plugins: {
      legend: { display: true },
      tooltip: { callbacks: { label: (context) => `Value: ${context.raw.value}` } }
    },
    scales: {
      x: { title: { display: true, text: 'Spot Price' } },
      y: { title: { display: true, text: 'Volatility' } }
    }
  };

  return (
    <div className="heatmap">
      <h2>Heatmap Visualization</h2>
      <Chart type="scatter" data={data} options={options} />
    </div>
  );
}