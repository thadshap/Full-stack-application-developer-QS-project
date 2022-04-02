package ntnu.karolisw.project_backend.controller;

import ntnu.karolisw.project_backend.dto.in.PersonIn;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
@CrossOrigin("http://localhost:8081/") // Vue address
public class PersonController {

    @GetMapping("/pronouns/{personId}")
    public void getPronouns(@PathVariable("personId") long personId) {
        // retrieve the pronouns of this person (if they have any)
    }

    @PostMapping("/pronouns")
    public void postPronouns(PersonIn dto) {
        // dto contains userId
    }

}
