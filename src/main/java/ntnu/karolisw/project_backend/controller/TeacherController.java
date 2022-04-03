package ntnu.karolisw.project_backend.controller;

import ntnu.karolisw.project_backend.service.interfaces.CourseServiceI;
import ntnu.karolisw.project_backend.service.interfaces.UserServiceI;
import ntnu.karolisw.project_backend.service.interfaces.QueueServiceI;
import org.springframework.beans.factory.annotation.Autowired;

public class TeacherController {

    @Autowired
    CourseServiceI courseService;

    @Autowired
    UserServiceI loginService;

    @Autowired
    QueueServiceI queueService;


    // Updates to GroupOfAssignment can be done by a teacher and an admin
        // update order_nr
        // update number of assignment --> must also update order_nr for all the other objects
        // update number of approved assignments

    // queue methods
        // set queue to active --> student assistant?
        // remove queue when the course archived == true --> activate this method when course is archived!

}
