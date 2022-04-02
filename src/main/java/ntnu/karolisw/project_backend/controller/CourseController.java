package ntnu.karolisw.project_backend.controller;

import ntnu.karolisw.project_backend.dto.in.CourseIn;
import ntnu.karolisw.project_backend.dto.in.GroupIn;
import ntnu.karolisw.project_backend.dto.in.PersonIn;
import ntnu.karolisw.project_backend.service.interfaces.CourseServiceI;
import ntnu.karolisw.project_backend.service.interfaces.LoginServiceI;
import ntnu.karolisw.project_backend.service.interfaces.QueueServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
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
    @GetMapping()
    public ResponseEntity<Object> getAllCourses() {
        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<Object> getAllCoursesForTeacher(@PathVariable("teacherId") long id) {
        return courseService.getAllCoursesByTeacherId(id);
    }


    @PostMapping("/addNew")
    public ResponseEntity<Object> postNewCourse(@RequestBody CourseIn dto) {
        // DTO contains: courseCode, courseName,
        // startDate, endDate, numOfPractices, numOfApprovedPractices, numOfUnderGroups
        return courseService.createCourse(dto);
    }


    /**
     * While it is possible to create underGroups upon creation of course, it is also possible
     * to add underGroups after a course is created
     *
     * @param dto contains the assignments that are to be placed in the group
     */
    @PostMapping("/underGroup")
    public ResponseEntity<Object> postUnderGroupCourse(@RequestBody GroupIn dto) {
        try {
            courseService.addGroupOfAssignmentToCourse(dto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Deletes a course
     * @param courseId is the course to delete
     *
     * @return HttpStatus OK or NOT_FOUND
     */
    @DeleteMapping("/{courseId}")
    public ResponseEntity<Object> deleteCourse(@PathVariable("courseId") long courseId){
        return courseService.deleteCourse(courseId);
    }

    @PostMapping("/archive")
    public ResponseEntity<Object> archiveCourse(@RequestBody CourseIn dto){
        return courseService.archiveCourse(dto.getCourseId());
    }
    @PostMapping("/addStudent")
    public ResponseEntity<Object> addStudentToCourse(@RequestBody PersonIn dto){
        return courseService.addStudent(dto.getCourseId(), dto.getEmail());
    }

    @PostMapping("/addTeacher")
    public ResponseEntity<Object> addTeacherToCourse(@RequestBody PersonIn dto){
        return courseService.addTeacher(dto.getCourseId(), dto.getEmail());
    }

    @PostMapping("/addStudentAssistant")
    public ResponseEntity<Object> addStudentAssistantToCourse(@RequestBody PersonIn dto){
        return courseService.addStudentAssistant(dto.getCourseId(), dto.getEmail());
    }

    @DeleteMapping("/deleteStudent") // todo maybe this is not courseIn but studentIn?
    public ResponseEntity<Object> deleteStudentFromCourse(@RequestBody PersonIn student){
        // use long courseId
        return courseService.removeStudent(student.getCourseId(), student.getPersonId());
    }
}
