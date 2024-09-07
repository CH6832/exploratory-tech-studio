import React, { useState } from 'react';

function InputForm({ onCalculate }) {
  const [inputs, setInputs] = useState({
    stockPrice: '',
    strikePrice: '',
    timeToMaturity: '',
    volatility: '',
    interestRate: ''
  });
  const [error, setError] = useState(null);

  const handleSubmit = (event) => {
    event.preventDefault();
    setError(null);

    if (!inputs.stockPrice || !inputs.strikePrice || !inputs.timeToMaturity || !inputs.volatility || !inputs.interestRate) {
      setError('Please fill in all fields');
      return;
    }

    onCalculate(inputs).catch(err => {
      setError('An error occurred while calculating.');
      console.error('Form submission error:', err);
    });
  };

  return (
    <form onSubmit={handleSubmit}>
      {/* Input fields here */}
      <button type="submit">Calculate</button>
      {error && <p className="error">{error}</p>}
    </form>
  );
}
