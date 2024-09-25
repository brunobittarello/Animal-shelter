FROM nginx:alpine
ENV TZ America/Sao_Paulo
COPY Nginx/nginx.conf /etc/nginx/nginx.conf
EXPOSE 80 443
ENTRYPOINT ["nginx"]
# Parametros extras para o entrypoint
CMD ["-g", "daemon off;"]