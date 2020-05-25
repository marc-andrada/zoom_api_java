@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  zoom_api_java startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and ZOOM_API_JAVA_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\zoom_api_java-1.0-SNAPSHOT.jar;%APP_HOME%\lib\gson-2.8.5.jar;%APP_HOME%\lib\ngrok-java-client-0.1-SNAPSHOT.jar;%APP_HOME%\lib\unirest-java-1.4.9.jar;%APP_HOME%\lib\httpasyncclient-4.1.1.jar;%APP_HOME%\lib\httpmime-4.5.2.jar;%APP_HOME%\lib\httpclient-4.5.12.jar;%APP_HOME%\lib\ini4j-0.5.1.jar;%APP_HOME%\lib\httpcore-nio-4.4.4.jar;%APP_HOME%\lib\httpcore-4.4.13.jar;%APP_HOME%\lib\org.apache.oltu.oauth2.client-1.0.2.jar;%APP_HOME%\lib\commons-logging-1.2.jar;%APP_HOME%\lib\org.apache.oltu.oauth2.common-1.0.2.jar;%APP_HOME%\lib\commons-codec-1.11.jar;%APP_HOME%\lib\slf4j-api-1.7.7.jar;%APP_HOME%\lib\json-20160212.jar

@rem Execute zoom_api_java
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %ZOOM_API_JAVA_OPTS%  -classpath "%CLASSPATH%" bots.TestBot3 %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable ZOOM_API_JAVA_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%ZOOM_API_JAVA_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
