# Utilisez l'image openjdk11 depuis Docker Hub
FROM openjdk:11

# Exposez le port de votre application Spring Boot
EXPOSE 8082

# Ajoutez le fichier JAR généré par votre projet Spring Boot
ADD target/achat-1.0.jar achat-1.0.jar

# Commande pour exécuter l'application Spring Boot
ENTRYPOINT ["java", "-jar", "/achat-1.0.jar"]
