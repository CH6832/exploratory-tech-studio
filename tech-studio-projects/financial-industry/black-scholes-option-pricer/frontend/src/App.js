import React, { useState } from 'react';
import InputForm from './components/InputForm';
import CallPutDisplay from './components/CallPutDisplay';
import Heatmap from './components/Heatmap';

function App() {
  const [callValue, setCallValue] = useState(null);
  const [putValue, setPutValue] = useState(null);

  const handleCalculate = async (inputs) => {
    try {
      const response = await fetch('/calculate', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(inputs),
      });

      if (!response.ok) {
        throw new Error('Network response was not ok.');
      }

      const data = await response.json();
      setCallValue(data.call_price);
      setPutValue(data.put_price);
      console.log('Calculation successful', data);
    } catch (error) {
      console.error('Error during calculation:', error);
    }
  };

  return (
    <div>
      <InputForm onCalculate={handleCalculate} />
      <CallPutDisplay callValue={callValue} putValue={putValue} />
      <Heatmap />
    </div>
  );
}
