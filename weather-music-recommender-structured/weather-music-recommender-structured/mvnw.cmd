@ECHO OFF
IF NOT "%JAVA_HOME%" == "" (
  SET JAVA_EXE="%JAVA_HOME%\bin\java"
) ELSE (
  SET JAVA_EXE="java"
)

SET MAVEN_PROJECTBASEDIR=%~dp0..

"%JAVA_EXE%" -classpath "%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.jar" org.apache.maven.wrapper.MavenWrapperMain %*