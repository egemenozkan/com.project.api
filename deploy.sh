mvn -f $HOME/devel/workspace/projects/com.project.contract/ clean install
mvn -f $HOME/devel/workspace/projects/com.project.slugify/ clean install

mvn clean install package -DskipTests -Pprod

scp target/api-alfa.jar root@94.237.94.131:/var/www/jee_projects