FROM mysql:8.0.40

COPY katsuyouDB.sql /docker-entrypoint-initdb.d/
COPY katsuyouMakeUsers.sql /docker-entrypoint-initdb.d/

EXPOSE 3306