#include "Circle.h"
#include <cmath>

Circle::Circle(double radius) : radius(radius) {}

double Circle::area() const {
    return M_PI * radius * radius;
}

double Circle::perimeter() const {
    return 2 * M_PI * radius;
}
