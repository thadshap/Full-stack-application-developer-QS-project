package ntnu.karolisw.project_backend.controller;

import ntnu.karolisw.project_backend.dto.in.CourseIn;
import ntnu.karolisw.project_backend.dto.in.GroupIn;
import ntnu.karolisw.project_backend.model.Course;
import ntnu.karolisw.project_backend.service.interfaces.CourseServiceI;
import ntnu.karolisw.project_backend.service.interfaces.LoginServiceI;
import ntnu.karolisw.project_backend.service.interfaces.QueueServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
@CrossOrigin("http://localhost:8081/") // Vue address

public class CourseController {

    @Autowired
    CourseServiceI courseService;

    @Autowired
    LoginServiceI loginService;

    @Autowired
    QueueServiceI queueService;


    /**
     * Retrieves all courses that are not archived
     * name, code, how many students take the course
     */
    @GetMapping
    public void getAllCourses() {

    }

    @GetMapping("/{teacherId}")
    public void getAllCoursesForTeacher(@PathVariable("teacherId") long id) {

    }


    @PostMapping("/addNew")
    public void postNewCourse(@RequestBody CourseIn dto) {
        // DTO contains: courseCode, courseName,
        // startDate, endDate, numOfPractices, numOfApprovedPractices, numOfUnderGroups
    }


    @PostMapping("/underGroup")
    public void postUnderGroupCourse(@RequestBody GroupIn dto) {
        // DTO contains: orderNumber, numOfPractices, minimumNumApproved, courseId
    }
}
