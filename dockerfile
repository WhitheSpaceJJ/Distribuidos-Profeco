#IMAGEN BASE JDK A UTILIZAR
FROM eclipse-temurin:17-jdk

# Establecer el directorio de trabajo en /usr/src/myapp
WORKDIR /usr/src/myapp

# Copiar el archivo JAR al directorio de trabajo
COPY api-gateway/target/api-gateway-0.0.1-SNAPSHOT.jar app.jar

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]