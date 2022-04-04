package ntnu.karolisw.project_backend.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ntnu.karolisw.project_backend.dto.in.CourseIn;
import ntnu.karolisw.project_backend.dto.in.GroupIn;
import ntnu.karolisw.project_backend.dto.in.PersonIn;
import ntnu.karolisw.project_backend.service.interfaces.CourseServiceI;
import ntnu.karolisw.project_backend.service.interfaces.UserServiceI;
import ntnu.karolisw.project_backend.service.interfaces.QueueServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
@CrossOrigin
//@CrossOrigin(origins = {"http://192.168.1.80:8081/", "192.168.1.139:8081/" })



public class CourseController {

    @Autowired
    CourseServiceI courseService;

    @Autowired
    UserServiceI userService;

    @Autowired
    QueueServiceI queueService;


    /**
     * Retrieves all courses that are not archived
     * name, code, how many students take the course
     */
    @ApiOperation(value = "Gets all registered courses",
                  notes = "All courses registered in database",
                  response = CourseController.class)
    @GetMapping()
    public ResponseEntity<Object> getAllCourses() {
        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
    }

    @ApiOperation(value = "Gets all the courses to a teacher",
            notes = "Gets all the courses that a teacher is teaching in",
            response = CourseController.class)
    @GetMapping("/teachers/{teacherId}")
    public ResponseEntity<Object> getAllCoursesForTeacher(@ApiParam(value = "ID value for the teacher you need to retrieve all courses to") @PathVariable("teacherId") long id) {
        return courseService.getAllCoursesByTeacherId(id);
    }

    @ApiOperation(value = "Gets all the courses to a student",
            notes = "Gets all the courses that a student is registered in",
            response = CourseController.class)
    @GetMapping("/student/{studentId}")
    public ResponseEntity<Object> getAllCoursesForStudent(@PathVariable("studentId") long id) {
        return courseService.getAllCoursesForStudent(id);
    }

    @GetMapping("/students/{courseId}")
    public ResponseEntity<Object> getAllStudentsInCourse(@PathVariable("courseId") long courseId){
        return courseService.getAllStudentsInCourse(courseId);
    }

    @ApiOperation(value = "Gets all the courses to a student assistant",
            notes = "Gets all the courses that a student is student assistant in",
            response = CourseController.class)
    @GetMapping("/studentAssistants/{studentId}")
    public ResponseEntity<Object> getAllCoursesForStudentAssistant(@PathVariable("studentId") long id) {
        return courseService.getAllCoursesForStudentAssistant(id);
    }

    // assignmentNr, approved
    @ApiOperation(value = "Gets all assignments in a course for a student",
            notes = "When a student enters a course, the student will receive all the assignment in the course and whether they are approved or not",
            response = CourseController.class)
    @PostMapping("/assignments")
    public ResponseEntity<Object> getAllAssignmentsInCourseForStudentAndIfApproved(@RequestBody CourseIn dto){
        return courseService.getAllAssignmentsForStudentInCourse(dto.getPersonId(),dto.getCourseId());
    }

    @ApiOperation(value = "Gets course by id",
            notes = "Gets all the info about a course when sending its id to the method",
            response = CourseController.class)
    @GetMapping("/{courseId}")
    public ResponseEntity<Object> getCourseById(@PathVariable("courseId") long courseId){
        return courseService.getCourse(courseId);
    }

    @ApiOperation(value = "Register new course",
            notes = "Post method to register a new course in database",
            response = CourseController.class)
    @PostMapping("/addNew")
    public ResponseEntity<Object> postNewCourse(@RequestBody CourseIn dto) {
        return courseService.createCourse(dto);
    }


    /**
     * While it is possible to create underGroups upon creation of course, it is also possible
     * to add underGroups after a course is created
     *
     * @param dto contains the assignments that are to be placed in the group
     */
    @ApiOperation(value = "Register a set of assignment under a course",
            notes = "A course can have a lot of assignments and those assignments can be divided in groups. " +
                    "This is due to that for example a course can have ten assignments and to pass that course you have " +
                    "to get three out of the four first assignment approved. Then the other assignment group will " +
                    "be from the fifth assignment to the eighth assignment, and two out of thee assignments need to be " +
                    "approved and so goes on. So that's why under group of assignment is needed",
            response = CourseController.class)
    @PostMapping("/newGroup")
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
    @ApiOperation(value = "Delete course",
            notes = "Deletes a course from the database by the course id",
            response = CourseController.class)
    @DeleteMapping("/{courseId}")
    public ResponseEntity<Object> deleteCourse(@PathVariable("courseId") long courseId){
        return courseService.deleteCourse(courseId);
    }

    /**
     * To be able to archive a course, there must be both a course and a queue created
     * @param dto
     * @return
     */
    @ApiOperation(value = "Archive a course",
            notes = "If a semester is over, the course will be archived which means that the attribute under course called archive set to true",
            response = CourseController.class)
    @PostMapping("/archive")
    public ResponseEntity<Object> archiveCourse(@RequestBody CourseIn dto){
        return courseService.archiveCourse(dto.getCourseId());
    }

    @ApiOperation(value = "Archive a course",
            notes = "If a semester is over, the course will be archived which means that the attribute under course called archive set to true",
            response = CourseController.class)
    @PostMapping("/addStudent")
    public ResponseEntity<Object> addStudentToCourse(@RequestBody PersonIn dto){
        return courseService.addStudentToCourse(dto);
    }

    @ApiOperation(value = "Add teacher to course",
            notes = "Adds a teacher to a specific course",
            response = CourseController.class)
    @PostMapping("/addTeacher")
    public ResponseEntity<Object> addTeacherToCourse(@RequestBody PersonIn dto){
        return courseService.addTeacherToCourse(dto);
    }

    @ApiOperation(value = "Add student assistant to course",
            notes = "Adds a student assistant to a specific course",
            response = CourseController.class)
    @PostMapping("/addStudentAssistant")
    public ResponseEntity<Object> addStudentAssistantToCourse(@RequestBody PersonIn dto){
        return courseService.addStudentAssistantToCourse(dto);
    }

    @ApiOperation(value = "Remove student from course",
            notes = "Removes a student from a specific course",
            response = CourseController.class)
    @DeleteMapping("/removeStudent")
    public ResponseEntity<Object> deleteStudentFromCourse(@RequestBody PersonIn student){
        return courseService.removeStudentFromCourse(student.getCourseId(), student.getEmail());
    }
}
