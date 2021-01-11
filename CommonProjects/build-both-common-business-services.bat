GOTO Author
	@author: Muhammad Noman
	@creation date: Jan, 11, 2021
:Author

set JAVA_HOME=C:\Program Files\Java\jdk-14
set ANT_HOME=C:\ant
set PATH=%JAVA_HOME%\bin;%ANT_HOME%\bin
call ant -f build-common-projects.xml
if not %ERRORLEVEL% == 0 goto :exitFailed


echo "Common Projects jars Build Complete ..."
pause
exit

:exitFailed
echo "!!Build Failed!!"
pause
exit

