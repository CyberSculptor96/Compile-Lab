#!/bin/bash

# 编译Java文件，并将字符集设置为UTF-8
echo "Compiling Java files..."
javac -encoding UTF-8 taxcalculator/*.java

# 检查是否编译成功
if [ $? -eq 0 ]; then
    echo "Compilation successful. Running the program..."
    java taxcalculator.UIController
else
    echo "Compilation failed."
fi
