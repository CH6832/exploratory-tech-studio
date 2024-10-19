import cProfile
import pstats
from black_scholes import black_scholes_call, black_scholes_put

def profile_black_scholes():
    profiler = cProfile.Profile()
    profiler.enable()

    # Example of profiling Black-Scholes calculations
    for _ in range(1000):
        black_scholes_call(100, 100, 1, 0.2, 0.05)
        black_scholes_put(100, 100, 1, 0.2, 0.05)

    profiler.disable()
    stats = pstats.Stats(profiler).sort_stats(pstats.SortKey.TIME)
    stats.print_stats()

if __name__ == '__main__':
    profile_black_scholes()
