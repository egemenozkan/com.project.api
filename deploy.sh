mvn -f $HOME/devel/workspace/projects/com.project.contract/ clean install
mvn -f $HOME/devel/workspace/projects/com.project.slugify/ clean install

mvn clean install package -DskipTests -Pprod

scp target/api-alfa.jar webuser@192.168.0.20:/var/www/vantalii/api