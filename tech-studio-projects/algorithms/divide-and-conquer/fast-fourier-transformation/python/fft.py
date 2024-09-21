import numpy as np
import matplotlib.pyplot as plt

# Sampling parameters
sampling_rate = 1000  # Hz
T = 1.0 / sampling_rate  # Sample interval
t = np.arange(0, 1, T)  # Time vector, 1 second duration

# Create a signal composed of 50 Hz and 120 Hz sine waves
freq1 = 50  # Frequency of the first sine wave
freq2 = 120  # Frequency of the second sine wave
signal = 0.7 * np.sin(2 * np.pi * freq1 * t) + np.sin(2 * np.pi * freq2 * t)

# Compute the FFT
fft_values = np.fft.fft(signal)
fft_freqs = np.fft.fftfreq(len(fft_values), T)

# Get the positive frequencies
positive_freqs = fft_freqs[:len(fft_values)//2]
positive_fft_values = np.abs(fft_values[:len(fft_values)//2])

# Plot the original signal
plt.figure(figsize=(12, 6))
plt.subplot(2, 1, 1)
plt.plot(t, signal)
plt.title('Original Signal')
plt.xlabel('Time [s]')
plt.ylabel('Amplitude')

# Plot the FFT results
plt.subplot(2, 1, 2)
plt.plot(positive_freqs, positive_fft_values)
plt.title('FFT of the Signal')
plt.xlabel('Frequency [Hz]')
plt.ylabel('Amplitude')

plt.tight_layout()
plt.show()
