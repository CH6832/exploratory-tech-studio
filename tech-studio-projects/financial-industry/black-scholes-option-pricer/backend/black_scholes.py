import math
import logging

def black_scholes_call(S, K, T, sigma, r):
    try:
        # Calculation logic
        d1 = (math.log(S / K) + (r + 0.5 * sigma ** 2) * T) / (sigma * math.sqrt(T))
        d2 = d1 - sigma * math.sqrt(T)
        call_price = (S * norm.cdf(d1) - K * math.exp(-r * T) * norm.cdf(d2))
        return call_price
    except Exception as e:
        logging.error(f"Error in black_scholes_call: {e}")
        raise

def black_scholes_put(S, K, T, sigma, r):
    try:
        # Calculation logic
        d1 = (math.log(S / K) + (r + 0.5 * sigma ** 2) * T) / (sigma * math.sqrt(T))
        d2 = d1 - sigma * math.sqrt(T)
        put_price = (K * math.exp(-r * T) * norm.cdf(-d2) - S * norm.cdf(-d1))
        return put_price
    except Exception as e:
        logging.error(f"Error in black_scholes_put: {e}")
        raise
