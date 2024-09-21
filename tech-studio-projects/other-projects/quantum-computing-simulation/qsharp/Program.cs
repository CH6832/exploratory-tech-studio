using System;
using Microsoft.Quantum.Simulation.Core;
using Microsoft.Quantum.Simulation.Simulators;
using System.Diagnostics;

namespace MyQuantumProject
{
    class Program
    {
        static void Main(string[] args)
        {
            using (var sim = new QuantumSimulator())
            {
                // Start timing
                var stopwatch = Stopwatch.StartNew();

                // Run the Q# operation
                ApplyQuantumGates.Run(sim).Wait();

                // Stop timing
                stopwatch.Stop();
                Console.WriteLine($"Quantum simulation completed in {stopwatch.Elapsed.TotalMilliseconds} ms");
            }
        }
    }
}
