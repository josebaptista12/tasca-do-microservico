# Dockerfile — product-service
# Fase 1: imagem base com Java 17
FROM eclipse-temurin:17-jre-alpine
# Cria uma pasta /app dentro do container
WORKDIR /app
# Copia o JAR para dentro do container
COPY target/product-service-0.0.1-SNAPSHOT.jar app.jar
# Expõe a porta 8080 (informativo — não abre a porta sozinho)
EXPOSE 8080
# Comando que corre quando o container arranca
ENTRYPOINT ["java", "-jar", "app.jar"]