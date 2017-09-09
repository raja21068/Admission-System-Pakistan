rem set PATH=C:\Program Files (x86)\Java\jdk1.7.0_45\bin;
set DIRNAME=%~dp0
set CP="%DIRNAME%dist/AdmissionSystemUOS.jar"
rem start /B javaw -cp %CP% admission/view/ConfigFrame
java -cp %CP% admission/view/ConfigurationDialog
rem java -jar -Xmx256m dist/AdmissionSystemUOS.jar
pause