CREATE USER IF NOT EXISTS 'root'@'%' IDENTIFIED BY 'rootPassword'; --Replace with actual passwords for db.
CREATE USER IF NOT EXISTS 'mysql'@'%' IDENTIFIED BY 'mysqlPassword';

GRANT ALL PRIVILEGES ON JMDict.* TO 'root'@'%';
GRANT ALL PRIVILEGES ON JMDict.* TO 'mysql'@'%';

FLUSH PRIVILEGES;