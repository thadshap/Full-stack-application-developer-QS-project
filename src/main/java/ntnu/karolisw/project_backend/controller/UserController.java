package ntnu.karolisw.project_backend.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ntnu.karolisw.project_backend.dto.in.Login;
import ntnu.karolisw.project_backend.dto.out.PersonOut;
import ntnu.karolisw.project_backend.service.interfaces.CourseServiceI;
import ntnu.karolisw.project_backend.service.interfaces.UserServiceI;
import ntnu.karolisw.project_backend.service.interfaces.QueueServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
@CrossOrigin("http://192.168.1.80:8081/") // Vue address

public class UserController {

    @Autowired
    CourseServiceI courseService;

    @Autowired
    UserServiceI userService;

    @Autowired
    QueueServiceI queueService;


    @ApiOperation(value = "Checking if the email and password is present in database",
            notes = "Sending back a boolean whether the login try was successful or not, as well as the userId",
            response = UserController.class)
    @PostMapping("/login")
    public ResponseEntity<Object> validateUserLogin(@ApiParam(value = "email, password and type of user(student, teacher or administrator)")
                                                        @RequestBody Login login) {
        try{
            PersonOut result = userService.validatePassword(login.getEmail(),
                    login.getPassword(),login.getTypeOfUser());

            // If the password was valid
            if(result.isLoggedIn()) {
                return new ResponseEntity<>(result,HttpStatus.OK);
            }
            // If the password was invalid
            else {
                return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "creating a new user in the database",
            notes = "adding a new user to the database, can be student, teacher or administrator",
            response = UserController.class)
    @PostMapping("/register")
    public ResponseEntity<Object> createNewUser(@ApiParam(value = "email, password and type of user(student, teacher or administrator)")
                                                    @RequestBody Login login) {
        try {
            userService.saveNewUser(login.getEmail(), login.getPassword(),
                    login.getPersonId(), login.getTypeOfUser());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
