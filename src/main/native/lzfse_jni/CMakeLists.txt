cmake_minimum_required(VERSION 3.23)
project(lzfse_jni)

set(CMAKE_CXX_STANDARD 14)
set(LZFSE_BUNDLE_MODE OFF)

add_subdirectory(lzfse)

find_package(JNI REQUIRED)
if (JNI_FOUND)
    message(STATUS "JNI_INCLUDE_DIRS=${JNI_INCLUDE_DIRS}")
    message(STATUS "JNI_LIBRARIES=${JNI_LIBRARIES}")
endif ()

include_directories(${JNI_INCLUDE_DIRS})

add_library(lzfse_jni SHARED io_github_blackbeard334_lzfse47j_LzfseUtil.cpp)
target_link_libraries(lzfse_jni PRIVATE lzfse)
