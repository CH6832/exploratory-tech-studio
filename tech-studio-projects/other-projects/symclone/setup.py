from setuptools import setup, find_packages

setup(
    name='symclone',
    version='0.1.0',
    author='Christoph Hartleb',
    author_email='kristovhar@gmail.com.com',
    description='A package for symbolic mathematics, modeled after SymPy, offering comprehensive CAS functionality with simple, extensible code.',
    long_description=open('README.md',encoding="utf-8").read(),
    long_description_content_type='text/markdown',
    url='',
    packages=find_packages(),
    install_requires=[],
    python_requires='>=3.6',
    classifiers=[
        'Programming Language :: Python :: 3',
        'License :: OSI Approved :: MIT License',
        'Operating System :: OS Independent',
    ],
)
