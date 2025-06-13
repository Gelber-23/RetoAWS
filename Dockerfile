FROM public.ecr.aws/amazoncorretto/amazoncorretto:21-alpine
WORKDIR /app
COPY build/libs/app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]