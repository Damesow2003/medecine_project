# Étape 1 : Construction de l'application Angular SSR
FROM node:18 AS builder

# Définir le répertoire de travail
WORKDIR /app

# Copier les fichiers du projet et installer les dépendances
COPY package.json package-lock.json ./
RUN npm install

# Copier le reste du projet et construire l'application
COPY . .
RUN npm run build --configuration=production --no-prerender

# Étape 2 : Création de l'image finale pour exécuter l'application
FROM node:18

# Définir le répertoire de travail
WORKDIR /app

# Copier les fichiers générés par la phase de build
COPY --from=builder /app/dist/medecine_front /app/dist/medecine_front

# Installer les dépendances de production
COPY package.json package-lock.json ./
RUN npm install --omit=dev

# Exposer le port de l'application
EXPOSE 4000

# Démarrer le serveur Angular SSR
CMD ["node", "dist/medecine_front/server/server.mjs"]
