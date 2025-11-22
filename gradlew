#!/bin/sh

# Определяем текущую директорию
APP_BASE_DIR=$(cd "$(dirname "$0")" && pwd)
GRADLE_USER_HOME=${GRADLE_USER_HOME:-"$HOME/.gradle"}

# Запускаем Gradle Wrapper
exec java -cp "$APP_BASE_DIR/gradle/wrapper/gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain "$@"
