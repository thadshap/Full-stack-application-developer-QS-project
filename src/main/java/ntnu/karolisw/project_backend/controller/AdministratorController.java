package ntnu.karolisw.project_backend.controller;

import ntnu.karolisw.project_backend.service.interfaces.CourseServiceI;
import ntnu.karolisw.project_backend.service.interfaces.LoginServiceI;
import ntnu.karolisw.project_backend.service.interfaces.QueueServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdministratorController {

    @Autowired
    CourseServiceI courseService;

    @Autowired
    LoginServiceI loginService;

    @Autowired
    QueueServiceI queueService;


    public void addAdministrator() {

    }

}
