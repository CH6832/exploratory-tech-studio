#include "Rectangle.h"

Rectangle::Rectangle(double length, double width) : length(length), width(width) {}

double Rectangle::area() const {
    return length * width;
}

double Rectangle::perimeter() const {
    return 2 * (length + width);
}
