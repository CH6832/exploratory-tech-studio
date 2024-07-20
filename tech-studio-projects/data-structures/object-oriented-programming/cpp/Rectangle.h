#ifndef RECTANGLE_H
#define RECTANGLE_H

#include "Shape.h"

class Rectangle : public Shape {
private:
    double length;
    double width;

public:
    Rectangle(double length, double width);

    // Implementing virtual functions
    double area() const override;
    double perimeter() const override;
};

#endif // RECTANGLE_H
