#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""utils.py"""

# pylint settings (https://docs.pylint.org/)
# pylint: disable=line-too-long, trailing-whitespace, multiple-statements, fixme, locally-disabled

import logging
import time
import cProfile
import pstats
from io import StringIO
from typing import Callable, Any


def setup_logging(level: int = logging.INFO) -> None:
    """Sets up the logging configuration for the application.

    This function initializes the logging configuration, setting the logging level 
    and format. It ensures that all log messages are formatted with a timestamp, 
    the log level, and the message.

    Args:
        level (int): The logging level (default is logging.INFO).
    """
    logging.basicConfig(level=level, format='%(asctime)s - %(levelname)s - %(message)s')
    logging.info("Logging is set up.")

    return None


def track_execution_time(func: Callable[..., Any]) -> Callable[..., Any]:
    """Decorator to measure the execution time of a function."""
    def wrapper(*args, **kwargs):
        start_time = time.time()
        result = func(*args, **kwargs)
        end_time = time.time()
        logging.info("Execution time of %s: %s seconds", func.__name__, str(end_time - start_time))
        return result
    
    return wrapper


def profile_function(func: Callable[..., Any]) -> Callable[..., Any]:
    """Decorator to measure the execution time of a function.

    This decorator logs the time it takes for the decorated function to execute. 
    It can be useful for performance monitoring and optimization.

    Args:
        func (Callable[..., Any]): The function to be decorated.
    """
    def wrapper(*args, **kwargs):
        pr = cProfile.Profile()
        pr.enable()
        result = func(*args, **kwargs)
        pr.disable()
        s = StringIO()
        sortby = pstats.SortKey.CUMULATIVE
        ps = pstats.Stats(pr, stream=s).sort_stats(sortby)
        ps.print_stats()
        logging.info(s.getvalue())
        return result
    
    return wrapper

def pretty_print_dict(d: dict) -> None:
    """Decorator to profile a function using cProfile.

    This decorator profiles the decorated function's execution using the cProfile 
    module, providing detailed performance statistics. The profiling results are 
    logged for further analysis.

    Args:
        func (Callable[..., Any]): The function to be decorated.
    """
    for key, value in d.items():
        logging.info("%s: %s", key, value)
    
    return None
