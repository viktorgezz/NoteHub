FROM nginx:alpine

# Копируется код в директорию для статических файлов Nginx. Точка обозначает curr директорию
COPY . /usr/share/nginx/html

# Открываем порт 80, чтобы доступ к серверу был возможен извне
EXPOSE 80

# Запускаем Nginx в фоновом режиме
CMD ["nginx", "-g", "daemon off;"]
