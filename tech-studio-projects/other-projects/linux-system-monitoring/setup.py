from setuptools import setup, find_packages

setup(
    name='monitoring_system',
    version='0.1',
    packages=find_packages(),
    install_requires=[
        'Flask==2.0.3',
        'Flask-Cors==3.0.10',
        'paramiko==2.7.2',
    ],
    entry_points={
        'console_scripts': [
            'monitoring_system = api:app',
        ],
    },
)
