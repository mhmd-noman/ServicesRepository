GOTO Author
	@author: Muhammad Noman
	@creation date: Jan, 11, 2021
:Author

copy ..\CommonProjects\libraries\*.jar ..\APIs\libraries
copy ..\CommonProjects\dep_lib\*.jar ..\APIs\dep_lib

set JAVA_HOME=C:\Program Files\Java\jdk-14
set ANT_HOME=C:\ant
set PATH=%JAVA_HOME%\bin;%ANT_HOME%\bin
call ant -f build-business-services.xml

echo "Common and Business Services jars Build Complete..."
pause
exit

:exitFailed
echo "!!Build Failed!!"
pause
exit

