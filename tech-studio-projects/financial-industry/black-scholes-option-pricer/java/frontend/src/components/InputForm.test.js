
import React from 'react';
import { render, screen } from '@testing-library/react';
import InputForm from './InputForm';

test('renders input fields', () => {
    render(<InputForm />);
    expect(screen.getByLabelText(/Stock Price/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/Strike Price/i)).toBeInTheDocument();
});

Integration Tests with React Testing Library

    Test Interaction Between Components

frontend/src/components/Integration.test.js:

jsx

import React from 'react';
import { render, fireEvent, screen } from '@testing-library/react';
import App from '../App';

test('calculates and displays Call and Put values', async () => {
    render(<App />);
    
    fireEvent.change(screen.getByLabelText(/Stock Price/i), { target: { value: '100' } });
    fireEvent.change(screen.getByLabelText(/Strike Price/i), { target: { value: '100' } });
    fireEvent.change(screen.getByLabelText(/Time to Maturity/i), { target: { value: '1' } });
    fireEvent.change(screen.getByLabelText(/Volatility/i), { target: { value: '0.2' } });
    fireEvent.change(screen.getByLabelText(/Risk Free Rate/i), { target: { value: '0.05' } });

    fireEvent.click(screen.getByText(/Calculate/i));

    expect(await screen.findByText(/Call Value/i)).toBeInTheDocument();
    expect(await screen.findByText(/Put Value/i)).toBeInTheDocument();
});

