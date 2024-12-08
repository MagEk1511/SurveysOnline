# Этап 1: Сборка JAR файла
FROM gradle:8.3-jdk17 as builder
WORKDIR /app

# Копируем все файлы проекта
COPY . .

# Собираем проект и создаем JAR файл
RUN gradle build -x test

RUN ls build/libs

# Этап 2: Создание легковесного образа
FROM eclipse-temurin:17-jre
WORKDIR /app

# Добавляем метаданные
LABEL maintainer="ekamagerramov@gmail.com" \
      name="SurveyOnlineApp" \
      description="Spring Boot application built with Gradle" \
      version="1.0.0"


# Копируем JAR файл из предыдущего этапа
COPY --from=builder /app/build/libs/*SNAPSHOT.jar app.jar

# Указываем команду для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]
