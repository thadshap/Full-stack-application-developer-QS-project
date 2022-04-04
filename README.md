# qs

## Add maven
- If your pom.xml file does not look like this (Project-QS/pom.xml)
   ![](../main-backend/Skjermbilde%202022-04-04%20kl.%2014.38.58.png)
  then you have to right click on the pom.xml file and click on 'Add as Maven Project'

## Build maven
 - To ways to build maven
   - Build the project by writing this on terminal:
   ```
    mvn build
   ```
   OR 
   ![](../main-backend/Skjermbilde%202022-04-04%20kl.%2014.48.10.png)
   - first click on the 'Maven' tag on the right 
   - click on the arrow beside the 'Lifecycle' option
   - right click anywere inside that option
   - click on 'Run maven build'

## Clean maven
 - Two ways to clean maven
   - Write this on terminal:
   ```
    mvn clean
   ```
   OR
   - Click on the 'Maven' tag on the right, click on the arrow beside the 'Lifecycle' option and double click 'clean'
   
## Run application
 - Go to src/main/java/ntnu/karolisw/project_backend/ProjectBackendApplication.java
 - The application can be executed by pressing either of those red marked green "play" buttons (shown in the picture below). If you do not find these buttons then you can right-click anywhere in the class and press Run 'Main.main ()'.
![](../main-backend/Skjermbilde%202022-04-04%20kl.%2014.18.40.png)

## Swagger documentaion
 To check out the swagger documentation, you must go to this link after you have run the project:
```
http://localhost:3306/swagger-ui.html
```
