rem set DIRNAME=%~dp0
rem set CP="%DIRNAME%dist/AdmissionSystemUOS.jar"
rem start /B javaw -cp %CP% Main
java -jar -Xmx256m dist/AdmissionSystemUOS.jar
pause