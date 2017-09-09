set DIRNAME=%~dp0
set CP="%DIRNAME%dist\AdmissionSystemUOS.jar"
rem start /B javaw -cp %CP% admission.run.MainSelection
rem java -jar -Xmx256m dist/AdmissionSystemUOS.jar YOUGESH
java -Xmx256m -cp %CP% admission.run.MainSelection YOUGESH
