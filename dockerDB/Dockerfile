FROM mysql:8.0.40

ENV MYSQL_ROOT_PASSWORD=password

COPY katsuyouDB.sql /docker-entrypoint-initdb.d/
COPY katsuyouMakeUsers-Prod.sql /docker-entrypoint-initdb.d/

EXPOSE 3306