FROM openjdk:22-jdk-slim AS build

RUN apt-get update && apt-get install -y maven && apt-get clean

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:22-jdk-slim AS test

RUN apt-get update && apt-get install -y maven && apt-get clean

WORKDIR /app

COPY --from=build /app /app

RUN mvn test -Dtest=**/*Test > result.log; tail -f result.log

FROM openjdk:22-jdk-slim

WORKDIR /app

COPY --from=build /app/target /app/target

EXPOSE 8080

CMD ["java", "-jar", "target/viacep-api-0.0.1-SNAPSHOT.jar"]
