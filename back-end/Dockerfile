FROM eclipse-temurin:17-jre-focal

WORKDIR /app

# Copier le fichier JAR généré
COPY target/*.jar app.jar

# Exposer le port de l'application
EXPOSE 9000

# Lancer l'application
CMD ["java", "-jar", "app.jar"]
