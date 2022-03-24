cmake_minimum_required(VERSION 3.21)
project(homework_05)

set(CMAKE_CXX_STANDARD 14)

#add_executable(homework_05 t5p1.cpp)
add_executable(homework_05 main.cpp)
target_link_libraries(homework_05 -lOpenGL32 -lfreeGLUT)