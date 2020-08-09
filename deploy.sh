mvn -f $HOME/devel/workspace/projects/com.project.contract/ clean install
mvn -f $HOME/devel/workspace/projects/com.project.slugify/ clean install

mvn clean install package -DskipTests -Pprod

scp target/api-alfa.jar devops@94.237.97.137:/var/www/jee_projects/api