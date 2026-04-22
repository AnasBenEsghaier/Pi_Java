@echo off
echo Starting JavaFX App...
echo Make sure MySQL (XAMPP) is running!
echo.
set JAVA_HOME=C:\Users\Anas\.jdks\ms-21.0.8-1
"C:\Program Files\JetBrains\IntelliJ IDEA 2025.2.1\plugins\maven\lib\maven3\bin\mvn.cmd" javafx:run
pause
