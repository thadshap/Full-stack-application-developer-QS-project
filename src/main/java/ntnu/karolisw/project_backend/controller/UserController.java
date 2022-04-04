package ntnu.karolisw.project_backend.controller;

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


    @PostMapping("/login")
    public ResponseEntity<Object> validateUserLogin(@RequestBody Login login) {
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

    @PostMapping("/register")
    public ResponseEntity<Object> createNewUser(@RequestBody Login login) {
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
