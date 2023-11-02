FROM gradle:8.2.1-jdk17 as build
# Устанавливаем рабочую директорию в контейнере
WORKDIR /home/gradle/src

# Копируем файлы Gradle и исходный код приложения в контейнер
COPY build.gradle.kts settings.gradle.kts ./
COPY src ./src

# Собираем приложение
RUN gradle build --no-daemon


FROM openjdk:18-jdk

EXPOSE 8080
# Копируем скомпилированный WAR файл из контейнера сборки в новый контейнер
COPY --from=build /home/gradle/src/build/libs/telegram-0.0.1-SNAPSHOT.war /app/telegram-0.0.1-SNAPSHOT.war


# Запускаем приложение
CMD ["java", "-jar", "/app/telegram-0.0.1-SNAPSHOT.war"]