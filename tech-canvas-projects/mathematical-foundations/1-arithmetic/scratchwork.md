# Arithmetic

## Matrix Arithemtic

Given matrices:

A = [[2, 3], 
     [5, 7]]

B = [[1, 4], 
     [2, 6]]

C = [[3, 5], 
     [7, 9]]

### Matrix Multiplication

To multiply matrices A and B, we use the formula:
(AB)ᵢⱼ = ∑ Aᵢₖ * Bₖⱼ

For the given matrices:
AB = [[2*1 + 3*2, 2*4 + 3*6],
      [5*1 + 7*2, 5*4 + 7*6]]

So,
AB = [[2 + 6, 8 + 18],
      [5 + 14, 20 + 42]]
   = [[8, 26],
      [19, 62]]

### Matrix Addition

Add matrix C to the result of AB:
AB + C = [[8 + 3, 26 + 5],
          [19 + 7, 62 + 9]]

So,
AB + C = [[11, 31],
          [26, 71]]

### Scalar Multiplication

Scalar multiplication involves multiplying each element of a matrix by a scalar.

Given scalar: 3

To perform scalar multiplication on matrix A:
Result = [[3*2, 3*3],
          [3*5, 3*7]]

So,
Result = [[6, 9],
          [15, 21]]

### Transpose Calculation

Transposing a matrix involves swapping its rows and columns.

To transpose matrix A:
Result = [[2, 5],
          [3, 7]]

### Determinant Calculation

The determinant of a 2x2 matrix [[a, b], [c, d]] is calculated as ad - bc.

For matrix A:
Determinant = 2*7 - 3*5

So,
Determinant = 14 - 15
            = -1

### Inverse Calculation

The inverse of a 2x2 matrix [[a, b], [c, d]] is calculated as:
1 / (ad - bc) * [[d, -b], [-c, a]]

For matrix A:
Determinant = 2*7 - 3*5

As the determinant is non-zero, the inverse exists.

Inverse = 1 / -1 * [[7, -3], [-5, 2]]

So,
Inverse = [[-7, 3],
           [5, -2]]

### Eigenvalue Calculation

The eigenvalues of a matrix M are found by solving the characteristic equation:
det(M - λI) = 0
where I is the identity matrix and λ are the eigenvalues.

For matrix M:
M = [[11, 31],
     [26, 71]]

The characteristic equation is:
det([[11 - λ, 31],
     [26, 71 - λ]]) = 0

This expands to:
(11 - λ)(71 - λ) - (31 * 26) = 0
(11 - λ)(71 - λ) - 806 = 0

Expanding and simplifying:
λ² - 82λ + (781 - 806) = 0
λ² - 82λ - 25 = 0

Solving this quadratic equation for λ gives the eigenvalues:
λ = (82 ± √(82² - 4*(-25))) / 2
λ = (82 ± √(6724 + 100)) / 2
λ = (82 ± √6824) / 2
λ = (82 ± 82.6) / 2
λ₁ = (82 + 82.6) / 2 = 82.6 / 2 ≈ 41.3
λ₂ = (82 - 82.6) / 2 = -0.6 / 2 ≈ -0.3

Thus, the eigenvalues of the matrix M are approximately 41.3 and -0.3.

### Practical Relevance

Matrix operations and eigenvalue calculations are fundamental in many fields. They are crucial in machine learning for tasks like principal component analysis (PCA), which is used for dimensionality reduction, and in solving systems of linear equations which appear in optimization problems. Eigenvalues and eigenvectors play a key role in understanding the properties of matrices, such as stability in systems of differential equations, and in the development of algorithms for data analysis and processing.

- **Matrix Multiplication** is essential in transforming data, combining linear transformations, and is widely used in neural networks.
- **Matrix Addition** is used in the superposition of linear transformations.
- **Scalar Multiplication** allows scaling of matrices, which is useful in adjusting the magnitude of transformations.
- **Transpose** is used in switching between row and column vectors, and in various algorithms like solving linear systems.
- **Determinant** provides insights into matrix properties such as invertibility and volume scaling.
- **Inverse** is critical for solving linear systems, undoing transformations, and in optimization algorithms.
- **Eigenvalues** are pivotal in understanding system dynamics, stability analysis, and in techniques like PCA.

These operations form the backbone of many algorithms and methods used in practical applications across engineering, physics, computer science, and economics.

## Vector Arithemtic

Given vectors:

v1 = [2, 3, 4]

v2 = [5, 6, 7]

### Vector Addition

To add vectors v1 and v2, we simply add corresponding elements:

v1 + v2 = [2 + 5, 3 + 6, 4 + 7]

So,

v1 + v2 = [7, 9, 11]

### Vector Subtraction

To subtract vector v2 from vector v1, we subtract corresponding elements:

v1 - v2 = [2 - 5, 3 - 6, 4 - 7]

So,

v1 - v2 = [-3, -3, -3]

### Scalar Multiplication

Scalar multiplication involves multiplying each element of a vector by a scalar.

Given scalar: 3

To perform scalar multiplication on vector v1:

Result = [32, 33, 3*4]

So,

Result = [6, 9, 12]

### Dot Product

The dot product of two vectors v1 and v2 is the sum of the products of their corresponding elements:

v1 ⋅ v2 = (25) + (36) + (4*7)

So,

v1 ⋅ v2 = 10 + 18 + 28 = 56

### Cross Product

The cross product of two 3-dimensional vectors v1 and v2 is a vector perpendicular to both v1 and v2. The magnitude of the cross product represents the area of the parallelogram formed by the two vectors.

For vectors v1 and v2:

v1 × v2 = [(37) - (46), (45) - (27), (26) - (35)]

So,

v1 × v2 = [3, -8, 3]

### Practical Relevance

Vector arithmetic is fundamental in many fields, especially in physics, engineering, and computer graphics. Here's how each operation is relevant:

- **Vector Addition and Subtraction** are used in physics for representing forces, velocities, and displacements. In computer graphics, they are used to represent translations and movements.
- **Scalar Multiplication** is used to scale vectors, adjusting their magnitude. It is commonly used in physics for scaling forces or velocities.
- **Dot Product** is used in physics for calculating work done and in computer graphics for determining the angle between vectors.
- **Cross Product** is used in physics for calculating torque and in computer graphics for determining the direction of rotation.

These operations are foundational in many algorithms and applications, including physics simulations, computer graphics rendering, and machine learning algorithms. Understanding vector arithmetic is essential for anyone working in these fields.

## Floating-point Arithmetic

### Floating-point Addition

Floating-point addition involves adding two floating-point numbers together. It's important to consider issues like precision and rounding errors.

### Floating-point Subtraction

Floating-point subtraction involves subtracting one floating-point number from another. Like addition, it's susceptible to precision and rounding errors.

### Floating-point Multiplication

Floating-point multiplication involves multiplying two floating-point numbers together. Precision loss can occur if the result exceeds the precision of the floating-point format.

### Floating-point Division

Floating-point division involves dividing one floating-point number by another. It's essential to handle cases where the divisor is zero or very small to avoid division by zero errors or precision issues.

### Rounding

Rounding is crucial in floating-point arithmetic to manage precision and reduce errors. It's essential to choose the appropriate rounding method based on the application's requirements.

### Precision and Accuracy Considerations

Precision refers to the number of significant digits in a floating-point number, while accuracy refers to how close the result is to the true value. Floating-point arithmetic may introduce errors due to limited precision and rounding.

Considerations include:

- **Machine Epsilon**: The smallest floating-point number that can be added to 1.0 and produce a result different from 1.0. It determines the precision of floating-point arithmetic.
  
- **Overflow and Underflow**: Operations that result in values too large or too small to be represented in the floating-point format may lead to overflow or underflow errors.

- **Rounding Errors**: Rounding introduces errors when representing real numbers with finite precision. Different rounding methods may affect the accuracy of calculations.

- **Cancellation**: Subtracting two nearly equal floating-point numbers can lead to loss of significant digits, reducing accuracy.

- **Numerical Stability**: Some algorithms may be more prone to numerical instability, leading to larger errors in floating-point calculations.

- **Error Propagation**: Errors in one part of a calculation may propagate to subsequent calculations, affecting the overall accuracy of the result.

Understanding these considerations is essential for writing robust numerical algorithms and ensuring the accuracy of floating-point computations in real-world applications.