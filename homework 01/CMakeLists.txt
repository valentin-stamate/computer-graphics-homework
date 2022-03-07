cmake_minimum_required(VERSION 3.21)
project(homework_01)

set(CMAKE_CXX_STANDARD 14)

add_executable(homework_01 main.cpp)
target_link_libraries(homework_01 -lOpenGL32 -lfreeGLUT)
