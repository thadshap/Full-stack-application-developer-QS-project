package ntnu.karolisw.project_backend.controller;

import ntnu.karolisw.project_backend.dto.in.PersonIn;
import ntnu.karolisw.project_backend.service.interfaces.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
@CrossOrigin("http://192.168.1.80:8081/")
public class PersonController {

    @Autowired
    UserServiceI userService;

    /**
     * TypeOfUser int can hold numbers 1-3 where:
     *          1 = student
     *          2 = teacher
     *          3 = administrator
     * @param dto is the whole data transfer object. It can hold one or all of its attributes
     */
    @PostMapping("/getPronouns")
    public ResponseEntity<Object> getPronouns(@RequestBody PersonIn dto) {
        System.out.println(userService.getPronouns(dto));
        return userService.getPronouns(dto);
    }

    @PostMapping("/postPronouns")
    public ResponseEntity<Object> postPronouns(@RequestBody PersonIn dto) {
        return userService.setPronouns(dto);
    }

    // create new student
    @PostMapping("/newStudent")
    public ResponseEntity<Object> addNewStudent(@RequestBody PersonIn dto) {
        System.out.println("Adding student...");
        return userService.addNewStudent(dto);
    }
    // create new teacher
    @PostMapping("/newTeacher")
    public ResponseEntity<Object> addNewTeacher(@RequestBody PersonIn dto) {
        return userService.addNewTeacher(dto);
    }
    // create new admin
    @PostMapping("/newAdministrator")
    public ResponseEntity<Object> addNewAdministrator(@RequestBody PersonIn dto) {
        return userService.addNewAdministrator(dto);
    }
}
