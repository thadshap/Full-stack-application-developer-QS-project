package ntnu.karolisw.project_backend.controller;

import ntnu.karolisw.project_backend.service.interfaces.CourseServiceI;
import ntnu.karolisw.project_backend.service.interfaces.LoginServiceI;
import ntnu.karolisw.project_backend.service.interfaces.QueueServiceI;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentController {
    @Autowired
    CourseServiceI courseService;

    @Autowired
    LoginServiceI loginService;

    @Autowired
    QueueServiceI queueService;

    // legg in en ny student i et fag OK
    // get how many assignments must be approved (minimum) OK

    // set approved where studentId = x
    // remove student from number of waiting students in students in queue when student exits queue
    // add number of waiting students in queue when student enters queue --> get queue and increment number
    // set statusInQueue where studentInQueue id = id -->
    // upon remove student from queue --> remove student from studentInQueue class as well --> delete?


}
