@echo off

echo Compiling Java files...
javac -encoding UTF-8 taxcalculator\*.java

if %ERRORLEVEL% == 0 (
    echo Compilation successful. Running the program...
    java -classpath . taxcalculator.UIController
) else (
    echo Compilation failed.
)
pause
