# qs_backend

#### THIS APPLICATION CAN ONLY BE USED WITH JAVA 17

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
 - Before running the application, you have to change the ip-addresse in @CrossOrigin("http://192.168.1.80:8081/") in these classes under src/main/java/ntnu/karolisw/project_backend/controller; CourseController, PersonController, QueueController and UserController, from:
    ```
   @CrossOrigin("http://192.168.1.80:8081/") 
   ```
   to
   ```
   @CrossOrigin("http://*ipv4 address in wifi to the computer of where the frontend is running*:8081/") 
   ```
 - Go to src/main/java/ntnu/karolisw/project_backend/ProjectBackendApplication.java
 - The application can be executed by pressing either of those red marked green "play" buttons (shown in the picture below). If you do not find these buttons then you can right-click anywhere in the class and press Run 'Main.main ()'.
![](../main-backend/Skjermbilde%202022-04-04%20kl.%2014.18.40.png)

## Swagger documentaion
 To check out the swagger documentation, you must go to this link after you have run the project:
```
http://localhost:8080/swagger-ui.html
```

## Database
The project backend uses MySql (local) database. Therefore, the database solution will have to be edited in the file "application.properties" in such a way that the user of the backend may persist/get/delete etc. using their own db. Other DBMS are also usable with this solution, but one must account for this by adding the correct dependencies to the POM.xml file.

### Back-end methods
The backend consists of a multitude of methods. Only those present in the four controllers are implemented in the solution. Those that aren't are not purposfully, bbut part of the future work of the queue application. All methods in the controllers work and are tested. 

#### Hierarchy explained
- Repositories connect to the database.
- The services consist of methods were each service utilized upwards of 4 repositories. Gathering the mess of multiple dependency injections at the service level helps maintain a high level of cohesion at the controller level.
- The services are abstracted away through the use of interfaces. This lowers the coupling of the connection between controller and service, meaning less work has to be done upon changes to methods at the service-layer. 
- Each controller only autowires one service, namingly the service interface that corresponds to the name of the given controller. The controller holds the endpoints.
- The endpoints are the only ways the backend connect to the frontend, effectively using all layers of a RESTful application.
