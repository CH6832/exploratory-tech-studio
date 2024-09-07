cmake_minimum_required(VERSION 3.8)

add_library(orders STATIC
    ModifyOrder.cpp
    OrderCore.cpp
    OrderStatusCreator.cpp
    RejectCreator.cpp
    Rejection.cpp
    RejectionReason.cpp
)

target_include_directories(orders PUBLIC
    ${CMAKE_CURRENT_SOURCE_DIR}/include
)
