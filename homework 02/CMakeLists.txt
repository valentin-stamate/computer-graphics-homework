cmake_minimum_required(VERSION 3.21)
project(homework_02)

set(CMAKE_CXX_STANDARD 23)

add_executable(homework_02 main.cpp)
target_link_libraries(homework_02 -lOpenGL32 -lfreeGLUT)
