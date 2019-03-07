mvn -f /Devel/workplace/com.project.contract/ clean install
mvn clean package -DskipTests
scp target/api-alfa.jar root@do.sevais.com:/opt/www/api