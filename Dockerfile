FROM openjdk:17
LABEL authors="LaaLkA"
COPY out/artifacts/JavaJunior_lesson04_jar/JavaJunior_lesson04.jar /tmp/JavaJunior_lesson04.jar
WORKDIR /tmp
CMD ["java" , "-jar", "/tmp/JavaJunior_lesson04.jar"]