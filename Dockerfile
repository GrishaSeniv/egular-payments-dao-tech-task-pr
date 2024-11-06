FROM eclipse-temurin:21

WORKDIR /app

COPY target/regular-payments-dao-0.0.1-SNAPSHOT.jar /app/regular-payments-dao-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "regular-payments-dao-0.0.1-SNAPSHOT.jar"]
